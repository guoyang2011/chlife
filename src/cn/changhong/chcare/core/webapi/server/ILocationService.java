package cn.changhong.chcare.core.webapi.server;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import cn.changhong.chcare.core.webapi.AbstractChCareWebApi;
import cn.changhong.chcare.core.webapi.bean.Location;
import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.bean.ThirdShare;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;

public abstract class ILocationService extends AbstractChCareWebApi implements
		IService {

	
	/**
	 * 查询用户的历史位置列表
	 * [3.3.获取历史位置信息]
	 * @Title: searchUserHistoryPosition  
	 * @Description: TODO  
	 * @param @param username
	 * @param @param st  开始时间
	 * @param @param et 结束时间
	 * @param @param type  0为地图，1为文字
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<List<Location>>}  
	 * @throws
	 */
	public abstract ResponseBean searchUserHistoryPosition(String username,
			Date st, Date et, String type) throws HttpRequestException;
	/**
	 * 查询用户的历史位置列表
	 * [3.3.获取历史位置信息]
	 * @Title: searchUserHistoryPosition  
	 * @Description: TODO  
	 * @param @param username
	 * @param @param st 开始时间
	 * @param @param et 结束时间
	 * @param @param type 0为地图，1为文字
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<List<Location>>>} 
	 * @throws
	 */
	public <T> Future<ResponseBean> searchUserHistoryPosition(
			final String username, final Date st, final Date et,
			final String type, final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean> future = executorProvider
				.doTask(new Callable<ResponseBean>() {
					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = searchUserHistoryPosition(username, st, et,
									type);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_Location_searchUserHistoryPosition_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_Location_searchUserHistoryPosition_Service);
						}
						return bean;
					}
				});

		return future;
	}

	/**
	 * 获取单个用户最新位置信息
	 * [3.2.获取位置信息]
	 * @Title: searchUsersLastLocation  
	 * @Description: TODO  
	 * @param @param username
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<Location>} 
	 * @throws
	 */
	public abstract ResponseBean searchUsersLastLocation(String username)
			throws HttpRequestException;
	/**
	 * 获取单个用户最新位置信息
	 * [3.2.获取位置信息]
	 * @Title: searchUsersLastLocation  
	 * @Description: TODO  
	 * @param @param username
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<Location>>}  
	 * @throws
	 */
	public <T> Future<ResponseBean> searchUsersLastLocation(
			final String username,
			final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean> future = executorProvider
				.doTask(new Callable<ResponseBean>() {
					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = searchUsersLastLocation(username);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_Location_searchUsersLastLocation_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_Location_searchUsersLastLocation_Service);
						}
						return bean;
					}
				});
		return future;//ChCareWepApiServiceType.WebApi_Location_ _Service
	}

	/**
	 * 上传用户信息
	 * [3.1.上传位置信息]
	 * @Title: updateUserCurrentLocation  
	 * @Description: TODO  
	 * @param @param loc
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<NULL>}
	 * @throws
	 */
	public abstract ResponseBean updateUserCurrentLocation(Location loc)
			throws HttpRequestException;
	/**
	 * 上传用户信息
	 * [3.1.上传位置信息]
	 * @Title: updateUserCurrentLocation  
	 * @Description: TODO  
	 * @param @param loc
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<NULL>>}  
	 * @throws
	 */
	public <T> Future<ResponseBean> updateUserCurrentLocation(
			final Location loc, final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean> future = executorProvider
				.doTask(new Callable<ResponseBean>() {

					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = updateUserCurrentLocation(loc);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_Location_updateUserCurrentLocation_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_Location_updateUserCurrentLocation_Service);
						}
						return bean;
					}
				});
		return future;
	}

	/**
	 * 上传第三方分享
	 * [3.4.第三方分享日志]
	 * @Title: uploadShareMsgToThirdPlatformLog  
	 * @Description: TODO  
	 * @param @param log
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<NULL>}
	 * @throws
	 */
	public abstract ResponseBean uploadShareMsgToThirdPlatformLog(ThirdShare log)
			throws HttpRequestException;
	/**
	 * 上传第三方分享
	 * [3.4.第三方分享日志]
	 * @Title: uploadShareMsgToThirdPlatformLog  
	 * @Description: TODO  
	 * @param @param log
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<NULL>>} 
	 * @throws
	 */
	public <T> Future<ResponseBean> uploadShareMsgToThirdPlatformLog(
			final ThirdShare log, final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean> future = executorProvider
				.doTask(new Callable<ResponseBean>() {

					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = uploadShareMsgToThirdPlatformLog(log);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_Location_uploadShareMsgToThirdPlatformLog_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_Location_uploadShareMsgToThirdPlatformLog_Service);
						}
						return bean;
					}
				});
		return future;
	}
}
