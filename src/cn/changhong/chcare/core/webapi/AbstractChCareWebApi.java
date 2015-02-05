package cn.changhong.chcare.core.webapi;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import cn.changhong.chcare.core.webapi.bean.CHCareFileInStream;
import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.bean.ResponseBeanWithRange;
import cn.changhong.chcare.core.webapi.server.ChCareWebApiRequestErrorType;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;
import cn.changhong.chcare.core.webapi.util.HttpsConnectionManager;
import cn.changhong.chcare.core.webapi.util.IHttpRestApi;
import cn.changhong.chcare.core.webapi.util.MultipartUtility;
import cn.changhong.chcare.core.webapi.util.WebApiExecutorProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class AbstractChCareWebApi {
	protected final Gson gson = new GsonBuilder().setDateFormat(
			"yyyy-MM-dd HH:mm:ss").create();
	// protected final String BASE_URL ="http://10.9.52.22/WebAPIFamily/api/"
	// ;//"http://apis.changhongcare.com:9001/api/";10.9.52.178:8090
	protected final static String charset = "utf-8";
	public static String BASE_URL = "http://apis.changhongcare.com/api/";
//	 public static String BASE_URL = "http://10.9.52.178:8090/api/";
	public void setServerUrl(URL url){
		BASE_URL=url.toString();
	}
	public URL getServerUrl(){
		URL result=null;
		try {
			result= new URL(BASE_URL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
		return result;
	}
	protected IHttpRestApi httpRequestHandler = HttpsConnectionManager.Self
			.defaultInstance();
	protected WebApiExecutorProvider executorProvider = WebApiExecutorProvider.Self
			.defaultInstance();

	protected ResponseBean<?> transToBean(String jsonStr) throws HttpRequestException {
		ResponseBean<?> result = null;
		if (jsonStr != null) {
			Gson gson = new Gson();
			result = gson.fromJson(jsonStr, ResponseBean.class);
		}else{
			throw new HttpRequestException("Http Response Stream Is Null!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
		}
		return result;
	}

	protected ResponseBeanWithRange<?> transToRangeBean(String jsonStr) throws HttpRequestException {
		ResponseBeanWithRange<?> result = null;
		if (jsonStr != null) {
			Gson gson = new Gson();
			result = gson.fromJson(jsonStr, ResponseBeanWithRange.class);
		}else{
			throw new HttpRequestException("Http Response Stream Is Null!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
		}
		return result;
	}

	protected ResponseBean<?> transToBean(String jsonStr, Type type)
			throws HttpRequestException {
		ResponseBean<?> result = null;
		if (jsonStr != null) {
			try {
				result = gson.fromJson(jsonStr, type);
			} catch (Exception e) {
				String msg = "Transform JsonString Value[" + jsonStr
						+ "] To Type [" + type.toString() + "] Error!";
				throw new HttpRequestException(
						msg,
						ChCareWebApiRequestErrorType.CHCAREWEBAPI_TRANSFORM_DATA_ERROR);
			}
		}else{
			throw new HttpRequestException("Http Response Stream Is Null!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
		}
		return result;
	}

	protected ResponseBean<?> postRequestUtil(String url,
			String requestBodyparams) throws HttpRequestException {
		String response = this.httpRequestHandler.post(url, requestBodyparams);
		if (response == null) {
			throw new HttpRequestException("Http Response Stream Is Null!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
		}
		System.out.println(response);
		return transToBean(response);
	}

	protected String basePostRequestUtil(String url, String requestBodyparams)
			throws HttpRequestException {
		String response = this.httpRequestHandler.post(url, requestBodyparams);
		if (response == null) {
			throw new HttpRequestException("Http Response Stream Is Null!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
		}
		System.out.println(response);
		return response;
	}

	protected ResponseBean<?> getRequestUtil(String url)
			throws HttpRequestException {
		String response = this.httpRequestHandler.get(url);
		if (response == null) {
			throw new HttpRequestException("Http Response Stream Is Null!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
		}
		System.out.println(response);
		return transToBean(response);
	}

	protected String baseGetRequestUtil(String url) throws HttpRequestException {
		String response = this.httpRequestHandler.get(url);
		if (response == null) {
			throw new HttpRequestException("Http Response Stream Is Null!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
		}
		System.out.println(response);
		return response;
	}

	protected ResponseBean<?> deleteRequestUtil(String url,
			String requestBodyparams) throws HttpRequestException {

		String response = this.httpRequestHandler
				.delete(url, requestBodyparams);
		if (response == null) {
			throw new HttpRequestException("Http Response Stream Is Null!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
		}
		System.out.println(response);
		return transToBean(response);
	}

	protected String baseDeleteRequestUtil(String url, String requestBodyparams)
			throws HttpRequestException {
		String response = this.httpRequestHandler
				.delete(url, requestBodyparams);
		if (response == null) {
			throw new HttpRequestException("Http Response Stream Is Null!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
		}
		System.out.println(response);
		return response;
	}

	protected String baseUsedFormUploadPhoto(String url, InputStream instream,
			String params) throws HttpRequestException {
		if (instream == null) {
			throw new HttpRequestException(
					"Read InputStream Error,InputStream Is Null!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}
		System.out.println(url + "," + params);
		String response = null;
		try {
			response = this.httpRequestHandler.postFile(url, instream, params);
		} finally {
			this.httpRequestHandler.closeStream(instream);
		}
		if (response == null) {
			throw new HttpRequestException("Response Is Null!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}
		return response;
	}

	public CHCareFileInStream downloadFile(String url)
			throws HttpRequestException {
		return this.httpRequestHandler.getPhotoFile(url);
	}
	public Boolean downloadFile(String url,OutputStream out) throws HttpRequestException{
		return this.httpRequestHandler.getPhotoFile(url, out);
	}
	protected String doPostSingleFileUsedFormType(String url,
			InputStream instream, Map<String, String> formBodys, String filename)
			throws HttpRequestException {
		System.out.println(url+this.gson.toJson(formBodys));
		String response = null;
		MultipartUtility multipart = new MultipartUtility(url, charset);
		for (Map.Entry<String, String> body : formBodys.entrySet()) {
			multipart.addFormField(body.getKey(), body.getValue());
		}
		multipart.addFilePart(filename, instream);
		List<String> responses = multipart.finish();
		if (responses != null && responses.size() > 0) {
			response = responses.get(0);
		} else {
			throw new HttpRequestException("Http Response Stream Is Null!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_RESPONSE_ERROR);
		}
		System.out.println(response);
		return response;
	}
}
