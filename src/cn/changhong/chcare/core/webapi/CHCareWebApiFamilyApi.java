package cn.changhong.chcare.core.webapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import cn.changhong.chcare.core.webapi.bean.CHCareFileInStream;
import cn.changhong.chcare.core.webapi.bean.Family;
import cn.changhong.chcare.core.webapi.bean.FamilyMemberInfo;
import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.bean.User;
import cn.changhong.chcare.core.webapi.server.AFamilyService;
import cn.changhong.chcare.core.webapi.server.ChCareWebApiRequestErrorType;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;

public class CHCareWebApiFamilyApi extends AFamilyService {

	@Override
	public ResponseBean<?> createFamily(String familyName, String description)
			throws HttpRequestException {
		String url = BASE_URL + "CreateFamily";
		Map<String, String> params = new HashMap<String, String>();
		params.put("FamilyName", familyName);
		params.put("Description", description);

		String response = this.basePostRequestUtil(url,
				this.gson.toJson(params));
		ResponseBean<?> result = this.transToBean(response);
		if (result!=null&&result.getState() == 0) {
			Type type = new TypeToken<ResponseBean<Family>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
		return result;
	}

	@Override
	public ResponseBean<?> inviteJoinFamily(int familyID, String username,String reason)
			throws HttpRequestException {
		String url = BASE_URL + "InviteJoinFamily";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("FamilyID", familyID);
		params.put("UserName", username);
		params.put("Reason", reason);

		return this.postRequestUtil(url, this.gson.toJson(params));
	}

	@Override
	public ResponseBean<?> searchFamilys(String condition, int index, int count)
			throws HttpRequestException {
		String url = BASE_URL + "searchfamily";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("FamilyNameID", condition);
		params.put("Index", index);
		params.put("Count", count);

		String response = this.basePostRequestUtil(url,
				this.gson.toJson(params));
		ResponseBean<?> result = this.transToBean(response);
		if (result!=null&&result.getState() >= 0) {
			Type type = new TypeToken<ResponseBean<List<Family>>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
		return result;
	}

	@Override
	public ResponseBean<?> applyJoinFamily(int familyID, String username,String reason)
			throws HttpRequestException {
		String url = BASE_URL + "ApplyJoinFamily";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("FamilyID", familyID);
		params.put("UserName", username);
		params.put("Reason", reason);
		return this.postRequestUtil(url, this.gson.toJson(params));
	}

	@Override
	public ResponseBean<?> joinFamilyAllowByMaster(int familyID, int userId,
			String nickName, boolean allow, String reason)
			throws HttpRequestException {
		String url = BASE_URL + "HostAllowJoinFamily";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("FamilyID", familyID);
		params.put("UserID", userId);

		params.put("MemberName", nickName);
		params.put("Allow", allow);
		params.put("Reason", reason);
		return this.postRequestUtil(url, this.gson.toJson(params));
	}

	@Override
	public ResponseBean<?> getAllFamilyInfo() throws HttpRequestException {
		String url = BASE_URL + "GetFamilyInfoList";
		System.out.println(url);
		String response = this.baseGetRequestUtil(url);

		ResponseBean<?> result = this.transToBean(response);
		if (result!=null&&result.getState() >= 0) {
			Type type = new TypeToken<ResponseBean<List<Family>>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
		return result;
	}

	@Override
	public ResponseBean<?> changeUserFamilyMemberNickName(int familyID,
			int userID, String nickname) throws HttpRequestException {
		String url = BASE_URL + "updatefamilynote";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("FamilyID", familyID);
		params.put("UserID", userID);
		params.put("NickName", nickname);

		return this.postRequestUtil(url, this.gson.toJson(params));
	}

	@Override
	public ResponseBean<?> getFamilyMembers(int familyID)
			throws HttpRequestException {
		String url = BASE_URL + "GetFamilyMember?familyID=" + familyID;

		String response = this.baseGetRequestUtil(url);
		ResponseBean<?> result = this.transToBean(response);
		if (result!=null&&result.getState() >= 0) {
			Type type = new TypeToken<ResponseBean<List<FamilyMemberInfo>>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
		return result;
	}

	@Override
	public ResponseBean<?> removeUserByMaster(int userID, int familyID)
			throws HttpRequestException {
		String url = BASE_URL + "hostremovemember";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("MemberID", userID);
		params.put("FamilyID", familyID);
		return this.postRequestUtil(url, this.gson.toJson(params));
	}

	@Override
	public ResponseBean<?> updateFamilyInfo(int familyID, int userID,
			String familyName, String familySign) throws HttpRequestException {
		String url = BASE_URL + "updatefamilyinfo";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("FamilyID", familyID);
		params.put("UserID", userID);
		params.put("FamilyName", familyName);
		params.put("FamilySign", familySign);

		return this.postRequestUtil(url, this.gson.toJson(params));
	}

	/**  
	 *   
	 *   
	 * @param familyID
	 * @return  
	 * @throws HttpRequestException 
	 * @see cn.changhong.chcare.core.webapi.server.AFamilyService#destroyFamily(java.lang.String)  
	*/  
	
	@Override
	public ResponseBean<?> destroyFamily(int familyID) throws HttpRequestException {
		String url= BASE_URL+"destroyfamily";
		Map params=new HashMap<String,Object>();
		params.put("familyID", familyID);
		
		return this.postRequestUtil(url, this.gson.toJson(params));
	}

	/**  
	 *   
	 *   
	 * @param insteam
	 * @param filename
	 * @return  
	 * @see cn.changhong.chcare.core.webapi.server.AFamilyService#uploadFamilyIcon(java.io.InputStream, java.lang.String)  
	*/  
	
	@Override
	public ResponseBean<?> uploadFamilyIcon(int familyID,InputStream instream, String filename) throws HttpRequestException{
		 String url=BASE_URL+"FamilyAvatar";
		 Map<String,String> params=new HashMap<String, String>();
		 params.put("fid", familyID+"");
		 String response=this.doPostSingleFileUsedFormType(url, instream, params, filename);
		return this.transToBean(response);
	}

	/**  
	 *   
	 *   
	 * @param familyID
	 * @return
	 * @throws HttpRequestException  
	 * @see cn.changhong.chcare.core.webapi.server.AFamilyService#getFamilyIcon(int)  
	*/  
	
	@Override
	public ResponseBean<?> getFamilyIcon(int familyID)
			throws HttpRequestException {
		String url=BASE_URL+"FamilyAvatar?pid="+familyID;
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
	 * @param familyID
	 * @param file
	 * @return
	 * @throws HttpRequestException  
	 * @see cn.changhong.chcare.core.webapi.server.AFamilyService#uploadFamilyIcon(int, java.io.File)  
	*/  
	
	@Override
	public ResponseBean<?> uploadFamilyIcon(int familyID, File file)
			throws HttpRequestException {
		if(file==null || !file.exists() || !file.isFile() ){
			throw new HttpRequestException("Not Find File!", ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}
		InputStream instream=null;
		ResponseBean<?> result=null;
		try{
			instream=new FileInputStream(file);
			result=this.uploadFamilyIcon(familyID, instream, file.getName());
		}catch(IOException ex){
			throw new HttpRequestException("Not Find File!", ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}finally{
			if(instream != null){
				try {
					instream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block  
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
