package cn.changhong.chcare.core.webapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.changhong.chcare.core.webapi.bean.CHCareFileInStream;
import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.bean.User;
import cn.changhong.chcare.core.webapi.server.ChCareWebApiRequestErrorType;
import cn.changhong.chcare.core.webapi.server.IAccountService;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;
import cn.changhong.chcare.core.webapi.util.TokenManager;

import com.google.gson.reflect.TypeToken;

public class ChCareWebApiAccountnApi extends IAccountService {
	@Override
	public ResponseBean<?> registerStage1(String phoneNumber, int type)
			throws HttpRequestException {
		String url = BASE_URL + "VerifyCode?Username=" + phoneNumber
				+ "&&type=" + type;
		System.out.println(url);
		return this.getRequestUtil(url);
	}

	@Override
	public ResponseBean<?> registerStage2(String username, String verifyCode,
			int type) throws HttpRequestException {

		String url = BASE_URL + "VerifyCode";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Username", username);
		params.put("VerifyCode", verifyCode);
		params.put("Type", type);
		return this.postRequestUtil(url, this.gson.toJson(params));
	}

	@Override
	public ResponseBean<?> registerStage3(String username, String verifyCode,
			String newPassword) throws HttpRequestException {
		String url = BASE_URL + "SetPassword";

		System.out.println(url);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Username", username);
		params.put("Password", newPassword);
		params.put("VerifyCode", verifyCode);

		return this.postRequestUtil(url, this.gson.toJson(params));
	}

	@Override
	public ResponseBean<?> login(String username, String password)
			throws HttpRequestException {
		String url = BASE_URL + "Token";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Usr", username);
		params.put("Pwd", password);
		params.put("ClientId", "1");

		ResponseBean<?> loginBean = this.postRequestUtil(url,
				this.gson.toJson(params));
		if (loginBean.getState() == 0) {
			TokenManager.setToken(loginBean.getData().toString());
		}
		return loginBean;
	}

	@Override
	public ResponseBean<?> searchUserInfo(String username)
			throws HttpRequestException {

		String url = BASE_URL + "UserInfo";
		if (username == null) {
			url += "?Username=";
		} else {
			url += "?Username=" + username;
		}

		System.out.println(url);
		String response = this.baseGetRequestUtil(url);
		ResponseBean<?> result = this.transToBean(response);
		if (result!=null&&result.getState() >= 0) {
			Type type = new TypeToken<ResponseBean<User>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
		return result;
	}

	@Override
	public ResponseBean<?> updateUserInfo(User user)
			throws HttpRequestException {

		String url = BASE_URL + "UserInfo";
		return this.postRequestUtil(url, this.gson.toJson(user));
	}

	@Override
	public ResponseBean<?> updateNewPassword(String username,
			String newPassword, String verifyCode) throws HttpRequestException {
		String url = BASE_URL + "ChangePassword";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("UserName", username);
		params.put("NewPassword", newPassword);
		params.put("VerifyCode", verifyCode);
		return this.postRequestUtil(url, this.gson.toJson(params));
	}

	@Override
	public ResponseBean<?> uploadUserPhoto(String filepath)
			throws HttpRequestException {
		if (filepath == null) {
			throw new HttpRequestException("Enter File Path Is Null!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}
		File file = new File(filepath);
		if (!file.isFile()) {
			throw new HttpRequestException("Enter path=[" + filepath
					+ "] Is Not File!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}
		InputStream instream = null;
		try {
			instream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new HttpRequestException("Read File Stream Error!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}
		return this.uploadUserPhoto(instream,file.getName());
	}

	/**
	 * 
	 * 
	 * @param instream
	 * @return
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.server.IAccountService#uploadUserPhoto(java.io.InputStream)
	 */

	@Override
	public ResponseBean<?> uploadUserPhoto(InputStream instream,String filename)
			throws HttpRequestException {
		String url = BASE_URL + "UserAvatar";
		if(filename == null || filename.trim().length()==0){
			throw new HttpRequestException(
					"Illegal Input Args,FileName Is NUll!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}
		String param="name=\""+ filename.substring(0, filename.lastIndexOf('.'))+ "\";filename=\"" + filename + "\"";
		return this.transToBean(this.baseUsedFormUploadPhoto(url, instream, param));
	}

	@Override
	public ResponseBean<?> logout() throws HttpRequestException {
		String url = BASE_URL + "Token";
		Map<String, Object> params = new HashMap<String, Object>();
		return this.deleteRequestUtil(url, this.gson.toJson(params));
	}

	/**
	 * 
	 * 
	 * @param iconUrl
	 * @return
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.server.IAccountService#getUserIcon(java.lang.String)
	 */

	@Override
	public ResponseBean<CHCareFileInStream> getUserIcon(String iconUrl)
			throws HttpRequestException {
		String url = BASE_URL + "UserAvatar?photoUrl=" + iconUrl;
		System.out.println(url);
		// this.getRequestUtil(url);

		CHCareFileInStream data = this.httpRequestHandler.getPhotoFile(url);
		ResponseBean<CHCareFileInStream> result = new ResponseBean<CHCareFileInStream>();
		result.setState(0);
		result.setData(data);
		result.setDesc("操作成功");
		return result;
	}

	/**
	 * 
	 * 
	 * @param userID
	 * @param familyID
	 * @param amount
	 * @param comment
	 * @return
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.server.IAccountService#updateFamilyWealth(java.lang.String,
	 *      java.lang.String, int, java.lang.String)
	 */

	@Override
	public ResponseBean<?> updateFamilyWealth(int familyID, int type,
			String comment) throws HttpRequestException {
		String url = BASE_URL + "UserBalance";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("FamilyID", familyID);
		params.put("Type", type);
		params.put("Comment", comment);
		return this.postRequestUtil(url, this.gson.toJson(params));
	}

	/**
	 * 
	 * 
	 * @param keyword
	 * @param startIndex
	 * @param count
	 * @return
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.server.IAccountService#searchUsers(java.lang.String,
	 *      int, int)
	 */

	@Override
	public ResponseBean<?> searchUsers(String keyword, int startIndex, int count)
			throws HttpRequestException {
		String url = BASE_URL + "UserSearch?index=" + startIndex + "&count="
				+ count;
		if (keyword != null && keyword.trim().length() > 0) {
			url += "&keyWord=" + keyword;
		}
		System.out.println(url);
		String response = this.baseGetRequestUtil(url);
		ResponseBean<?> result = this.transToBean(response);
		if (result!=null&&result.getState() >= 0) {
			Type type = new TypeToken<ResponseBean<List<User>>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
		return result;
	}

	/**  
	 *   
	 *   
	 * @param url
	 * @return  
	 * @see cn.changhong.chcare.core.webapi.server.IAccountService#createUserPhotoAbstractUrl(java.lang.String)  
	*/  
	
	@Override
	public String createUserPhotoAbsoluteUrl(String url) {
		return BASE_URL + "UserAvatar?photoUrl=" + url;
	}

	/**  
	 *   
	 *   
	 * @return
	 * @throws HttpRequestException  
	 * @see cn.changhong.chcare.core.webapi.server.IAccountService#isSignIn()  
	*/  
	
	@Override
	public ResponseBean<?> isSignIn(int familyID) throws HttpRequestException {
		String url=BASE_URL+"SignState?fid="+familyID;
		
		return this.getRequestUtil(url);
	}

	/**  
	 *   
	 *   
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 * @throws HttpRequestException  
	 * @see cn.changhong.chcare.core.webapi.server.IAccountService#setNewPassword(java.lang.String, java.lang.String)  
	*/  
	
	@Override
	public ResponseBean<?> setNewPassword(String oldPwd, String newPwd)
			throws HttpRequestException {
		String url=BASE_URL+"SetNewPassword";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("OPassword", oldPwd);
		params.put("NPassword", newPwd);
		return this.postRequestUtil(url, this.gson.toJson(params));
	}

	/**  
	 *   
	 *   
	 * @param id
	 * @return
	 * @throws HttpRequestException  
	 * @see cn.changhong.chcare.core.webapi.server.IAccountService#getUserInfoById(int)  
	*/  
	
	@Override
	public ResponseBean<?> getUserInfoById(int id) throws HttpRequestException {
		String url = BASE_URL + "UserInfoByID?id="+id;
		System.out.println(url);
		String response = this.baseGetRequestUtil(url);
		ResponseBean<?> result = this.transToBean(response);
		if (result!=null&&result.getState() >= 0) {
			Type type = new TypeToken<ResponseBean<User>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
		return result;
	}

}
