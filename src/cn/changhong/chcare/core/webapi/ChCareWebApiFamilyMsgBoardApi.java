/**     
 * @Title: ChCareWebApiFamilyMsgBoardApi.java  
 * @Package cn.changhong.chcare.core.webapi  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-9-28 下午4:17:36  
 * @version V1.0     
*/  
package cn.changhong.chcare.core.webapi;  

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import cn.changhong.chcare.core.webapi.bean.FamilyNote;
import cn.changhong.chcare.core.webapi.bean.FamilyNoteComment;
import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.server.AFamilyMsgBoardService;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;
  
/**  
 * @ClassName: ChCareWebApiFamilyMsgBoardApi  
 * @Description: TODO  
 * @author guoyang2011@gmail.com  
 * @date 2014-9-28 下午4:17:36  
 *     
 */
public class ChCareWebApiFamilyMsgBoardApi extends AFamilyMsgBoardService{

	/**  
	 *   
	 *   
	 * @param familyID
	 * @return
	 * @throws HttpRequestException  
	 * @see cn.changhong.chcare.core.webapi.server.AFamilyMsgBoardService#getFamilyNote(int)  
	*/  
	
	@Override
	public ResponseBean<?> getFamilyNote(int familyID)
			throws HttpRequestException {
		String url=BASE_URL+"note?fid="+familyID;
		String response=this.baseGetRequestUtil(url);
		ResponseBean<?> result=this.transToBean(response);
		if(result != null && result.getState()>=0){
			Type type = new TypeToken<ResponseBean<FamilyNote>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
		return result;
	}

	/**  
	 *   
	 *   
	 * @param note
	 * @return
	 * @throws HttpRequestException  
	 * @see cn.changhong.chcare.core.webapi.server.AFamilyMsgBoardService#uploadFamilyNote(cn.changhong.chcare.core.webapi.bean.FamilyNote)  
	*/  
	
	@Override
	public ResponseBean<?> uploadFamilyNote(FamilyNote note)
			throws HttpRequestException {
		String url=BASE_URL+"note";
		return this.postRequestUtil(url, this.gson.toJson(note));
	}

	/**  
	 *   
	 *   
	 * @param noteComment
	 * @return
	 * @throws HttpRequestException  
	 * @see cn.changhong.chcare.core.webapi.server.AFamilyMsgBoardService#uploadFamilyNoteComment(cn.changhong.chcare.core.webapi.bean.FamilyNoteComment)  
	*/  
	
	@Override
	public ResponseBean<?> uploadFamilyNoteComment(FamilyNoteComment noteComment)
			throws HttpRequestException {
		String url=BASE_URL+"notecomment"; 
		return this.postRequestUtil(url, this.gson.toJson(noteComment));
	}

	/**  
	 *   
	 *   
	 * @param familyNoteID
	 * @return
	 * @throws HttpRequestException  
	 * @see cn.changhong.chcare.core.webapi.server.AFamilyMsgBoardService#deleteFamilyNote(int)  
	*/  
	
	@Override
	public ResponseBean<?> deleteFamilyNote(int familyNoteID)
			throws HttpRequestException {
		 String url=BASE_URL+"deletenote?nid="+familyNoteID;
		return this.deleteRequestUtil(url, "");
	}

	/**  
	 *   
	 *   
	 * @param familyNoteID
	 * @param familyNoteCommentID
	 * @return
	 * @throws HttpRequestException  
	 * @see cn.changhong.chcare.core.webapi.server.AFamilyMsgBoardService#deleteFamilyNoteComment(int, int)  
	*/  
	
	@Override
	public ResponseBean<?> deleteFamilyNoteComment(int familyNoteID,
			int familyNoteCommentID) throws HttpRequestException {
		String url=BASE_URL+"deletenotecomment?ncid="+familyNoteID+"&to_nid="+familyNoteCommentID;
		return this.deleteRequestUtil(url, "");
	}

	
}
