/**     
 * @Title: CHCareWebApiPhotoWallApi.java  
 * @Package cn.changhong.chcare.core.webapi  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-9-19 上午10:00:28  
 * @version V1.0     
*/  
package cn.changhong.chcare.core.webapi;  

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.changhong.chcare.core.webapi.bean.Location;
import cn.changhong.chcare.core.webapi.bean.OfflineMessageBean;
import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.photowalll.bean.PhotoView;
import cn.changhong.chcare.core.webapi.server.APhotowallWebApiService;
import cn.changhong.chcare.core.webapi.server.ChCareWebApiRequestErrorType;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;
import cn.changhong.chcare.core.webapi.util.MultipartUtility;
import cn.changhong.chcare.core.webapi.util.TokenManager;

import com.google.gson.reflect.TypeToken;
  
/**  
 * @ClassName: CHCareWebApiPhotoWallApi  
 * @Description: TODO  
 * @author guoyang2011@gmail.com  
 * @date 2014-9-19 上午10:00:28  
 *   j   
 */
public class CHCareWebApiPhotoWallApi extends APhotowallWebApiService {

	/**  
	 *   
	 *   
	 * @param familyId
	 * @return
	 * @throws HttpRequestException  
	 * @see cn.changhong.chcare.core.webapi.server.APhotowallWebApiService#getFamilyPhotosInfo(long)  
	*/  
	
	@Override
	public ResponseBean<?> getFamilyPhotosInfo(long familyId)
			throws HttpRequestException {
		String url =BASE_URL+"photos?fid="+familyId;
		String response=this.baseGetRequestUtil(url);
		ResponseBean<?> result=this.transToBean(response);
		if(result.getState()==0){
			Type type = new TypeToken<ResponseBean<List<PhotoView>>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
		return result;
	}


	
	/**  
	 *   
	 *   
	 * @param familyId
	 * @param desc
	 * @param instream
	 * @return
	 * @throws HttpRequestException  
	 * @see cn.changhong.chcare.core.webapi.server.APhotowallWebApiService#uploadFamilyPhoto(long, java.lang.String, java.io.InputStream)  
	*/  
	
	@Override
	public ResponseBean<?> uploadFamilyPhoto(long familyId, String desc,
			InputStream instream,String filename) throws HttpRequestException {
		if(filename == null || filename.trim().length()==0){
			throw new HttpRequestException(
					"Illegal Input Args,FileName Is NUll!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}
		
		String url= BASE_URL+"photo";
		Map<String,String> params=new HashMap<String, String>();
		params.put("fid", ""+familyId);
		params.put("desc", desc);
		String response=this.doPostSingleFileUsedFormType(url, instream, params, filename);
		ResponseBean<?> result=this.transToBean(response);
		if(result!=null&&result.getState()==0){
			Type type = new TypeToken<ResponseBean<PhotoView>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
 		return result;
	}
	/**  
	 * 
	 *   
	 * @param photoID
	 * @return
	 * @throws HttpRequestException  
	 * @see cn.changhong.chcare.core.webapi.server.APhotowallWebApiService#deleteFamilyPhoto(long)  
	*/  
	
	@Override
	public ResponseBean<?> deleteFamilyPhoto(long photoID)
			throws HttpRequestException {
		String url=BASE_URL+"photo?pid="+photoID;
		
		return this.deleteRequestUtil(url, "");
	}



	/**  
	 *   
	 *   
	 * @param familyId
	 * @param desc
	 * @param file
	 * @return
	 * @throws HttpRequestException  
	 * @see cn.changhong.chcare.core.webapi.server.APhotowallWebApiService#uploadFamilyPhoto(long, java.lang.String, java.io.File)  
	*/  
	
	@Override
	public ResponseBean<?> uploadFamilyPhoto(long familyId, String desc,
			File file) throws HttpRequestException {
		if(!file.exists() || !file.isFile()){
			throw new HttpRequestException("File Not Exits!["+file.getAbsolutePath(), ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}
		InputStream instream=null;
		ResponseBean<?> res=null;
		try {
			instream=new FileInputStream(file);
			res=this.uploadFamilyPhoto(familyId, desc, instream, file.getName());
		} catch (FileNotFoundException e) {
			throw new HttpRequestException("Open File Stream Failed!Error Stack["+e.getLocalizedMessage()+"]", ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}finally{
			try {
				instream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block  
				e.printStackTrace();
			}
		}
		return res;
	}
}
