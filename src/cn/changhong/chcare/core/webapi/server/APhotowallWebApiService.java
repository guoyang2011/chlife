/**     
 * @Title: APhotowallWebApiService.java  
 * @Package cn.changhong.chcare.core.webapi.server  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-9-19 上午9:55:20  
 * @version V1.0     
*/  
package cn.changhong.chcare.core.webapi.server;  

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import cn.changhong.chcare.core.webapi.AbstractChCareWebApi;
import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;
  
/**  
 * 与家庭墙功能相关接口定义
 * @ClassName: APhotowallWebApiService  
 * @Description: TODO  
 * @author guoyang2011@gmail.com  
 * @date 2014-9-19 上午9:55:20  
 *     
 */
public abstract class APhotowallWebApiService extends AbstractChCareWebApi
implements IService{
	/**
	 * 照片墙列表
	 * [6.1.照片墙列表]
	 * @Title: getFamilyPhotosInfo  
	 * @Description: TODO  
	 * @param @param familyId 家庭ID
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<List<PhotoView>>}
	 * @throws
	 */
	public abstract ResponseBean<?> getFamilyPhotosInfo(long familyId) throws HttpRequestException;

	/**
	 * 照片墙列表
	 * [6.1.照片墙列表]
	 * @Title: getFamilyPhotosInfo  
	 * @Description: TODO  
	 * @param @param familyId 家庭ID
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<List<PhotoView>>>} 
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> getFamilyPhotosInfo(final long familyId,final AsyncResponseCompletedHandler<T> handler){
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {

					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = getFamilyPhotosInfo(familyId);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_PhotoWall_getFamilyPhotosInfo_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_PhotoWall_getFamilyPhotosInfo_Service);
						}
						return bean;
					}
				});
		return future;
	}
	
	/**
	 * 照片上传
	 * [6.2.照片上传]
	 * @Title: uploadFamilyPhoto  
	 * @Description: TODO  
	 * @param @param familyId 家庭ID
	 * @param @param desc 照片描述 
	 * @param @param instream  照片输入流
	 * @param @param filename  文件名
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<PhotoView>}  
	 * @throws
	 */
	public abstract ResponseBean<?> uploadFamilyPhoto(long familyId,String desc,InputStream instream,String filename) throws HttpRequestException;
	
	public abstract ResponseBean<?> uploadFamilyPhoto(long familyId,String desc,File file) throws HttpRequestException;
	
	public <T> Future<ResponseBean<?>> uploadFamilyPhoto(final long familyId,final String desc,final File file ,final AsyncResponseCompletedHandler<T> handler){
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {
					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = uploadFamilyPhoto(familyId,desc,file);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_PhotoWall_uploadFamilyPhoto_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_PhotoWall_uploadFamilyPhoto_Service);
						}
						return bean;
					}
				});
		return future;
	}
	/**
	 * 照片上传
	 * [6.2.照片上传]
	 * @Title: uploadFamilyPhoto  
	 * @Description: TODO  
	 * @param @param familyId 家庭ID
	 * @param @param desc 照片描述 
	 * @param @param instream  照片输入流
	 * @param @param filename  文件名
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<PhotoView>>}  
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> uploadFamilyPhoto(final long familyId,final String desc,final InputStream instream,final String filename , final AsyncResponseCompletedHandler<T> handler){
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {

					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = uploadFamilyPhoto(familyId,desc,instream,filename);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_PhotoWall_uploadFamilyPhoto_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_PhotoWall_uploadFamilyPhoto_Service);
						}
						return bean;
					}
				});
		return future;
	}
	
	/**
	 * 删除照片
	 * [6.3.删除照片]
	 * @Title: deleteFamilyPhoto  
	 * @Description: TODO  
	 * @param @param photoID 照片ID
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<NULL>}  
	 * @throws
	 */
	public abstract ResponseBean<?> deleteFamilyPhoto(long photoID) throws HttpRequestException;
	/**
	 * 删除照片
	 * [6.3.删除照片]
	 * @Title: deleteFamilyPhoto  
	 * @Description: TODO  
	 * @param @param photoID 照片ID
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<NULL>>} 
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> deleteFamilyPhoto(final long photoID,final AsyncResponseCompletedHandler<T> handler){
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {

					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = deleteFamilyPhoto(photoID);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_PhotoWall_deleteFamilyPhoto_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_PhotoWall_deleteFamilyPhoto_Service);
						}
						return bean;
					}
				});
		return future;
	}
}
