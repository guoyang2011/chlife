/**     
 * @Title: AFamilyMsgBoardService.java  
 * @Package cn.changhong.chcare.core.webapi.server  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-9-28 下午4:00:22  
 * @version V1.0     
*/  
package cn.changhong.chcare.core.webapi.server;  

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import cn.changhong.chcare.core.webapi.AbstractChCareWebApi;
import cn.changhong.chcare.core.webapi.bean.FamilyNote;
import cn.changhong.chcare.core.webapi.bean.FamilyNoteComment;
import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;
  
/**  
 * @ClassName: AFamilyMsgBoardService  
 * @Description: TODO  
 * @author guoyang2011@gmail.com  
 * @date 2014-9-28 下午4:00:22  
 *     
 */
public abstract class AFamilyMsgBoardService extends AbstractChCareWebApi implements IService{
	/**
	 * 获取家庭留言板
	 * [5.1.留言信息查询]
	 * @Title: getFamilyNote  
	 * @Description: TODO  
	 * @param @param familyID 家庭ID
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<List<FamilyNote>>}  
	 * @throws
	 */
	public abstract ResponseBean<?> getFamilyNote(int familyID) throws HttpRequestException;
	/**
	 * 获取家庭留言板
	 * [5.1.留言信息查询]
	 * @Title: getFamilyNote  
	 * @Description: TODO  
	 * @param @param familyID 家庭ID
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<List<FamilyNote>>}  
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> getFamilyNote(final int familyID,final AsyncResponseCompletedHandler<T> handler){
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {

					@Override
					public ResponseBean<?> call() {
						ResponseBean<?> bean = null;
						try {
							bean = getFamilyNote(familyID);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FamilyMsgBoard_getFamilyNote_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FamilyMsgBoard_getFamilyNote_Service);
						}
						return bean;
					}
				});
		return future;
	}
	/**
	 * 留言信息上传
	 * [5.2.留言信息上传]
	 * @Title: uploadFamilyNote  
	 * @Description: TODO  
	 * @param @param note 
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<NULL>} 
	 * @throws 
	 */
	public abstract ResponseBean<?> uploadFamilyNote(FamilyNote note) throws HttpRequestException;
	/**
	 * 留言信息上传
	 * [5.2.留言信息上传]
	 * @Title: uploadFamilyNote  
	 * @Description: TODO  
	 * @param @param note 
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<NULL>} 
	 * @throws 
	 */
	public <T> Future<ResponseBean<?>> uploadFamilyNote(final FamilyNote note,final AsyncResponseCompletedHandler<T> handler){
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {

					@Override
					public ResponseBean<?> call() {
						ResponseBean<?> bean = null;
						try {
							bean = uploadFamilyNote(note);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FamilyMsgBoard_uploadFamilyNote_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FamilyMsgBoard_uploadFamilyNote_Service);
						}
						return bean;
					}
				});
		return future;
	}
	/**
	 * 家庭留言信息评论
	 * [5.3.留言信息评论]
	 * @Title: uploadFamilyNoteComment  
	 * @Description: TODO  
	 * @param @param noteComment
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<NULL>}  
	 * @throws
	 */
	public abstract ResponseBean<?> uploadFamilyNoteComment(FamilyNoteComment noteComment) throws HttpRequestException;
	/**
	 * 家庭留言信息评论
	 * [5.3.留言信息评论]
	 * @Title: uploadFamilyNoteComment  
	 * @Description: TODO  
	 * @param @param noteComment
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<NULL>}  
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> uploadFamilyNoteComment(final FamilyNoteComment noteComment,final AsyncResponseCompletedHandler<T> handler){
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {

					@Override
					public ResponseBean<?> call() {
						ResponseBean<?> bean = null;
						try {
							bean = uploadFamilyNoteComment(noteComment);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FamilyMsgBoard_uploadFamilyNoteComment_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FamilyMsgBoard_uploadFamilyNoteComment_Service);
						}
						return bean;
					}
				});
		return future;
	}
	
	/**
	 * 删除留言信息
	 * [5.4.留言信息删除]
	 * @Title: deleteFamilyNote  
	 * @Description: TODO  
	 * @param @param familyNoteID 留言ID
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<NULL>}  
	 * @throws
	 */
	public abstract ResponseBean<?> deleteFamilyNote(int familyNoteID) throws HttpRequestException;
	/**
	 * 删除留言信息
	 * [5.4.留言信息删除]
	 * @Title: deleteFamilyNote  
	 * @Description: TODO  
	 * @param @param familyNoteID 留言ID
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<NULL>}  
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> deleteFamilyNote(final int familyNoteID,final AsyncResponseCompletedHandler<T> handler){
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {

					@Override
					public ResponseBean<?> call() {
						ResponseBean<?> bean = null;
						try {
							bean = deleteFamilyNote(familyNoteID);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FamilyMsgBoard_deleteFamilyNote_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FamilyMsgBoard_deleteFamilyNote_Service);
						}
						return bean;
					}
				});
		return future;
	}
	/**
	 * 删除留言评论
	 * [5.5.删除留言评论]
	 * @Title: deleteFamilyNoteComment  
	 * @Description: TODO  
	 * @param @param familyNoteCommentID
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return ResponseBean<?>  
	 * @throws
	 */
	public abstract ResponseBean<?> deleteFamilyNoteComment(int familyNoteID,int familyNoteCommentID) throws HttpRequestException;
	/**
	 * 删除留言评论
	 * [5.5.删除留言评论]
	 * @Title: deleteFamilyNoteComment  
	 * @Description: TODO  
	 * @param @param familyNoteCommentID
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return ResponseBean<?>  
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> deleteFamilyNoteComment(final int familyNoteID,final int familyNoteCommentID,final AsyncResponseCompletedHandler<T> handler){
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {

					@Override
					public ResponseBean<?> call() {
						ResponseBean<?> bean = null;
						try {
							bean = deleteFamilyNoteComment(familyNoteID,familyNoteCommentID);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FamilyMsgBoard_deleteFamilyNoteComment_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FamilyMsgBoard_deleteFamilyNoteComment_Service);
						}
						return bean;
					}
				});
		return future;
	}
}
