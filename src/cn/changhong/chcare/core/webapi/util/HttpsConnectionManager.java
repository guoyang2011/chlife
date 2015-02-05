package cn.changhong.chcare.core.webapi.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import cn.changhong.chcare.core.webapi.bean.CHCareFileInStream;
import cn.changhong.chcare.core.webapi.server.ChCareWebApiRequestErrorType;

public class HttpsConnectionManager implements IHttpRestApi {
	String DEFAULT_BOUNDRY = "******";
	String END_FLAG = "\r\n";
	String TWO_HYPHENS = "--";
	int timeout = -1;

	public static class Self {
		private static HttpsConnectionManager instance = null;

		public static HttpsConnectionManager defaultInstance() {
			if (instance == null) {
				instance = new HttpsConnectionManager();
			}
			return instance;
		}
	}

	private enum HttpRequestType {
		GET("GET"), PUT("PUT"), DELETE("DELETE"), POST("POST"), OPTIONS(
				"OPTIONS"), HEAD("HEAD"), TRACE("TRACE"), CONNECT("CONNECT");
		private String value;

		private HttpRequestType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	private enum HttpContentType {
		JSON("application/json"), STREAM("application/octet-stream");
		private String value;

		private HttpContentType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	private String requestHandler(HttpRequestType type, String url,
			String jsonObject) throws HttpRequestException {
		String result = null;
		System.out.println(url + "," + jsonObject.toString());
		byte[] buffer = jsonObject.toString().getBytes();
		HttpURLConnection conn = this.createHttpConn(type, url);
		if (conn != null) {
			System.out.println(buffer.length+">>>>>>>>>>>>>>>>>>>");
			

			OutputStream out = null;
			InputStream in = null;
			try {
				if (type == HttpRequestType.DELETE)
					conn.setDoOutput(false);
				else {
//					conn.setRequestProperty("Content-Length", buffer.length + "");
					conn.setDoOutput(true);
					try {
						out = conn.getOutputStream();
						out.write(buffer);
					} catch (IOException e) {
						
						throw new HttpRequestException(
								"Create Http OutputStream Error!Full Msg["+e.getMessage()+"]",
								ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
					}
				}
				int responseCode = -1;
				try {
					responseCode = conn.getResponseCode();
				} catch (IOException e) {
					throw new HttpRequestException(
							"Get Response Code Failed!Full Msg["+e.getMessage()+"]",
							ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
				}
				System.out.println(responseCode);

				if (responseCode == 200) {
					ByteArrayOutputStream buffers=new ByteArrayOutputStream();
					try {
						in = conn.getInputStream();
//						StringBuffer sBuffer = new StringBuffer();
						byte[] buf = new byte[1024];
						for (int n; (n = in.read(buf)) != -1;) {
							buffers.write(buf, 0, n);
						}
						result = new String(buffers.toByteArray(),"UTF-8");
					} catch (IOException e) {
						throw new HttpRequestException(
								"Read Response Stream Failed!Full Msg["+e.getMessage()+"]",
								ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
					}finally{
						try {
							buffers.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block  
							e.printStackTrace();
						}
					}
				} else {
					throw new HttpRequestException(
							"Http Response Error,Error Code[" + responseCode
									+ "]",
							ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
				}
			} finally {
				this.closeStream(in);
				this.closeStream(out);
				conn.disconnect();
			}
		}
		return result;
	}

	private HttpURLConnection createHttpConn(HttpRequestType type, String url)
			throws HttpRequestException {
		return this.createHttpConn(type, url, HttpContentType.JSON);
	}

	private HttpURLConnection createHttpConn(HttpRequestType type, String url,
			HttpContentType contentType) throws HttpRequestException {
		HttpURLConnection httpConn = null;
		try {
			URL u = new URL(url);
			httpConn = (HttpURLConnection) u.openConnection();
			httpConn.setDoInput(true);
			httpConn.setRequestMethod(type.getValue());
			if (timeout > 0) {
				httpConn.setConnectTimeout(timeout);
				httpConn.setReadTimeout(timeout);
			} else {
				timeout = 3000;
				httpConn.setConnectTimeout(5000);
				httpConn.setReadTimeout(5000);
			}
			httpConn.setRequestProperty("Content-Type", contentType.getValue()
					+ "; charset=utf-8");
			if (TokenManager.getToken() != null) {
				System.out.println(TokenManager.getToken());
				httpConn.setRequestProperty("Authorization", "CAuth "
						+ TokenManager.getToken());
			}
		} catch (IOException e) {
			if(httpConn != null){
				httpConn.disconnect();
			}
			throw new HttpRequestException(
					"Create Http Request Connection Failed!Full Msg["+e.getMessage()+"]",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}
		return httpConn;
	}

	@Override
	public String get(String url) throws HttpRequestException {
		String result = null;
		System.out.println(url);
		HttpURLConnection conn = this.createHttpConn(HttpRequestType.GET, url);
		if (conn != null) {
			InputStream in = null;
			
			int responseCode = -1;
			try {
				try {
					responseCode = conn.getResponseCode();
				} catch (IOException e) {
					throw new HttpRequestException(
							"Get Response Code Failed!Full Msg["+e.getMessage()+"]",
							ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
				}
				if (responseCode == 200) {
					
					byte[] buf = new byte[1024];
					ByteArrayOutputStream buffer=new ByteArrayOutputStream();
					try {
						in = conn.getInputStream();
						for (int n; (n = in.read(buf)) != -1;) {
							buffer.write(buf,0,n);
//							sBuffer.append(new String(buf, 0, n, "UTF-8"));
						}
						result = new String(buffer.toByteArray(),"UTF-8");
						buffer.close();
					} catch (IOException e) {
						throw new HttpRequestException(
								"Read Response Stream Failed!Full Msg["+e.getMessage()+"]",
								ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
					}finally{
						try {
							buffer.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block  
							e.printStackTrace();
						}
					}

				} else {
					throw new HttpRequestException(
							"Http Response Error,Error Code[" + responseCode
									+ "]",
							ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
				}
				// } catch (FileNotFoundException e1) {
				// e1.printStackTrace();
				// } catch (Exception e) {
				// throw new HttpRequestException("",
				// ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
			} finally {
				this.closeStream(in);
				conn.disconnect();
			}
		}
		return result;
	}

	@Override
	public String post(String url, String params) throws HttpRequestException {
		return this.requestHandler(HttpRequestType.POST, url, params);
	}

	@Override
	public void closeStream(Closeable stream) throws HttpRequestException {
		if (stream != null)
			try {
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public String put(String url, String params) throws HttpRequestException {
		return this.requestHandler(HttpRequestType.PUT, url, params);
	}

	@Override
	public String delete(String url, String params) throws HttpRequestException {

		return this.requestHandler(HttpRequestType.DELETE, url, params);
	}

	@Override
	public String postFile(String url, InputStream inStream, String params)
			throws HttpRequestException {

		String result = null;
		DataOutputStream outStream = null;
		InputStream responseStream = null;
		HttpURLConnection conn = null;
		if (inStream != null) {
			conn = this.createHttpConn(HttpRequestType.POST, url,
					HttpContentType.STREAM);
			try {
				conn.setRequestMethod(HttpRequestType.POST.getValue());
				conn.setUseCaches(false);
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setConnectTimeout(5000);
				conn.setReadTimeout(5000);
				conn.setRequestProperty("connection", "Keep-Alive");

				conn.setRequestProperty("Content-Type",
						"multipart/form-data;boundary=" + DEFAULT_BOUNDRY);

				outStream = new DataOutputStream(conn.getOutputStream());
				outStream.writeBytes(this.TWO_HYPHENS + this.DEFAULT_BOUNDRY
						+ this.END_FLAG);
				outStream.writeBytes("Content-Disposition: form-data;"+params + this.END_FLAG);
				outStream.writeBytes("Content-Type:application/octet-stream"
						+ this.END_FLAG + this.END_FLAG);
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				while ((bytesRead = inStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
					System.out.println(bytesRead);
				}
				outStream.writeBytes(this.END_FLAG);
				outStream.writeBytes(this.TWO_HYPHENS + this.DEFAULT_BOUNDRY+this.TWO_HYPHENS
						+ this.END_FLAG);
				outStream.flush();
				this.closeStream(outStream);
				int status = conn.getResponseCode();
				System.out.println(status);
				if (status == 200) {
					responseStream = conn.getInputStream();
					StringBuffer sBuffer = new StringBuffer();
					byte[] buf = new byte[1024];
					for (int n; (n = responseStream.read(buf)) != -1;) {
						sBuffer.append(new String(buf, 0, n, "utf-8"));
					}
					result = sBuffer.toString();
				}
			} catch (ProtocolException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				this.closeStream(outStream);
				this.closeStream(inStream);
				this.closeStream(responseStream);
				conn.disconnect();
			}

		}
		return result;
	}

	/**
	 * 
	 * 
	 * @param url
	 * @return
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.util.IHttpRestApi#getFile(java.lang.String)
	 */

	@Override
	public CHCareFileInStream getPhotoFile(String url)
			throws HttpRequestException {
		CHCareFileInStream result = null;
		HttpURLConnection conn = this.createHttpConn(HttpRequestType.GET, url);
		conn.setReadTimeout(5000);
		conn.setConnectTimeout(5000);
		if (conn != null) {
			InputStream in = null;
			int responseCode = -1;
			try {
				try {
					responseCode = conn.getResponseCode();
				} catch (IOException e) {
					throw new HttpRequestException(
							"Get Response Code Failed!Full Msg["+e.getMessage()+"]",
							ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
				}
				if (responseCode == 200) {

					System.out.println("\n\n"
							+ conn.getHeaderField("Content-Length") + "\n\n");
					String fileType = null;
					try {
						fileType = conn.getHeaderField("Content-Type")
								.toString().split("/")[1];
					} catch (Exception e) {
						throw new HttpRequestException(
								"GET Response Photo Stream Type Error!Full Msg["+e.getMessage()+"]",
								ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
					}

					ByteArrayOutputStream outStram = new ByteArrayOutputStream();
					try {
						in = conn.getInputStream();
						byte[] buffer = new byte[1024];
						for (int n=-1; (n = in.read(buffer)) != -1;) {
							System.out.println(n);
							outStram.write(buffer, 0, n);
						}
					} catch (IOException e) {
						throw new HttpRequestException(
								"GET Response File Stream Error!Full Msg["+e.getMessage()+"]",
								ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
					}
					result = new CHCareFileInStream(outStram, fileType);
				} else {
					throw new HttpRequestException(
							"Http Response Error,Error Code[" + responseCode
									+ "]",
							ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
				}
			} finally {
				this.closeStream(in);
				conn.disconnect();
			}
		}
		return result;
	}

	/**  
	 *   
	 *   
	 * @param url
	 * @param out
	 * @return
	 * @throws HttpRequestException  
	 * @see cn.changhong.chcare.core.webapi.util.IHttpRestApi#getPhotoFile(java.lang.String, java.io.OutputStream)  
	*/  
	
	@Override
	public Boolean getPhotoFile(String url, OutputStream out)
			throws HttpRequestException {
		if(out == null || url == null ){
			throw new HttpRequestException("IllegalArgumentException:Url Or Stream Is Null", ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}
		Boolean result = false;
		HttpURLConnection conn = this.createHttpConn(HttpRequestType.GET, url);
		conn.setReadTimeout(5000);
		conn.setConnectTimeout(5000);
		if (conn != null) {
			InputStream in = null;
			int responseCode = -1;
			try {
				try {
					responseCode = conn.getResponseCode();
				} catch (IOException e) {
					throw new HttpRequestException(
							"Get Response Code Failed!Full Msg["+e.getMessage()+"]",
							ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
				}
				if (responseCode == 200) {
					try {
						in = conn.getInputStream();
						byte[] buffer = new byte[1024];
						for (int n=-1; (n = in.read(buffer)) != -1;) {
							System.out.println(n);
							out.write(buffer, 0, n);
						}
					} catch (IOException e) {
						throw new HttpRequestException(
								"GET Response File Stream Error!Full Msg["+e.getMessage()+"]",
								ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
					}
					result = true;
				} else {
					throw new HttpRequestException(
							"Http Response Error,Error Code[" + responseCode
									+ "]",
							ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
				}
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block  
					e.printStackTrace();
				}
				this.closeStream(in);
				conn.disconnect();
			}
		}
		return result;
	}


}
