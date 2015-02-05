/**     
 * @Title: ss.java  
 * @Package cn.changhong.chcare.core.webapi.util  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-9-19 下午5:51:36  
 * @version V1.0     
 */
package cn.changhong.chcare.core.webapi.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import cn.changhong.chcare.core.webapi.server.ChCareWebApiRequestErrorType;

/**
 * @ClassName: MultipartUtility
 * @Description: TODO
 * @author guoyang2011@gmail.com
 * @date 2014-9-19 下午5:51:36
 * 
 */
public class MultipartUtility {
	private final String boundary;
	private static final String LINE_FEED = "\r\n";
	private HttpURLConnection httpConn;
	private String charset;
	private OutputStream outputStream;
	private PrintWriter writer;
	
	private volatile boolean isCancel=false;

	public boolean isCancel() {
		return isCancel;
	}

	public synchronized void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}

	/**
	 * This constructor initializes a new HTTP POST request with content type is
	 * set to multipart/form-data
	 * 
	 * @param requestURL
	 * @param charset
	 * @throws HttpRequestException
	 * @throws IOException
	 */
	public MultipartUtility(String requestURL, String charset)
			throws HttpRequestException {
//		System.out.println(requestURL);
		try {
			this.charset = charset;

			// creates a unique boundary based on time stamp

			boundary = "---" + System.currentTimeMillis() + "---";

			URL url = new URL(requestURL);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setUseCaches(false);
			httpConn.setDoOutput(true); // indicates POST method
			httpConn.setDoInput(true);
			httpConn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + boundary);
			// httpConn.setRequestProperty("User-Agent", "CodeJava Agent");
			httpConn.setRequestProperty("Test", "Bonjour");
			httpConn.setRequestProperty("Authorization", "CAuth "
					+ TokenManager.getToken());
			outputStream = httpConn.getOutputStream();
			writer = new PrintWriter(new OutputStreamWriter(outputStream,
					charset), true);
		} catch (IOException ex) {
			throw new HttpRequestException(
					"Create Http Request Connection Failed!Full Msg["
							+ ex.getMessage() + "]",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}
	}

	/**
	 * Adds a form field to the request
	 * 
	 * @param name
	 *            field name
	 * @param value
	 *            field value
	 */
	public void addFormField(String name, String value) {
		writer.append("--" + boundary).append(LINE_FEED);
		writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
				.append(LINE_FEED);
		writer.append("Content-Type: text/plain; charset=" + charset).append(
				LINE_FEED);
		writer.append(LINE_FEED);
		writer.append(value).append(LINE_FEED);
		writer.flush();
	}

	/**
	 * Adds a upload file section to the request
	 * 
	 * @param fieldName
	 *            name attribute in <input type="file" name="..." />
	 * @param uploadFile
	 *            a File to be uploaded
	 * @throws IOException
	 * @throws HttpRequestException
	 */
	public void addFilePart(String fileName, InputStream inputStream)
			throws HttpRequestException {
		if (fileName == null || fileName.trim().length() == 0) {
			throw new HttpRequestException("Illegal Args!FileName Is Null!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}
		if(fileName.contains("\"")){
			throw new HttpRequestException("Illegal Args!FileName Is Null!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}
		try {
			String fieldName = fileName.substring(0, fileName.lastIndexOf('.'));
			writer.append("--" + boundary).append(LINE_FEED);
			writer.append(
					"Content-Disposition: form-data; name=\""
							+ fieldName
							+ "\"; filename=\""
							+ fileName + "\"")
					.append(LINE_FEED);
			writer.append(
					"Content-Type: "
							+ URLConnection.guessContentTypeFromName(URLEncoder.encode(fileName)))
					.append(LINE_FEED);
			writer.append("Content-Transfer-Encoding: binary")
					.append(LINE_FEED);
			writer.append(LINE_FEED);
			writer.flush();

			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1 ) {
				if(isCancel())
					throw new IOException("Cancel upload file!");
				outputStream.write(buffer, 0, bytesRead);
			}
			outputStream.flush();
			inputStream.close();

			writer.append(LINE_FEED);
			writer.flush();

		} catch (IOException e) {
			writer.close();
			httpConn.disconnect();
			throw new HttpRequestException(
					"Create Http Request Stream Failed!Full Msg["
							+ e.getMessage() + "]",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}
	}

	/**
	 * Adds a header field to the request.
	 * 
	 * @param name
	 *            - name of the header field
	 * @param value
	 *            - value of the header field
	 */
	public void addHeaderField(String name, String value) {
		writer.append(name + ": " + value).append(LINE_FEED);
		writer.flush();
	}

	/**
	 * Completes the request and receives response from the server.
	 * 
	 * @return a list of Strings as response in case the server returned status
	 *         OK, otherwise an exception is thrown.
	 * @throws IOException
	 * @throws HttpRequestException
	 */
	public List<String> finish() throws HttpRequestException {
		List<String> response = new ArrayList<String>();

//		writer.append(LINE_FEED).flush();
		writer.append("--" + boundary + "--").append(LINE_FEED);
		writer.close();

		// checks server's status code first
		int status = -1;
		try {
			status = httpConn.getResponseCode();
		} catch (IOException e) {
			throw new HttpRequestException("Get Response Code Failed!Full Msg["
					+ e.getMessage() + "]",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
		}
		if (status == HttpURLConnection.HTTP_OK) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(

				httpConn.getInputStream()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					response.add(line);
				}
			} catch (IOException ex) {
				throw new HttpRequestException(
						"Get Response File Stream Failed!Full Msg["
								+ ex.getMessage() + "]",
						ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				httpConn.disconnect();
			}
		} else {
			throw new HttpRequestException("Http Response Error,Error Code["
					+ status + "]",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
		}

		return response;
	}
}