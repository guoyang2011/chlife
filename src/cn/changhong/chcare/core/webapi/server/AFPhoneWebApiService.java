/**     
 * @Title: AFPhoneWebApiService.java  
 * @Package cn.changhong.chcare.core.webapi.server  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-9-12 上午9:46:32  
 * @version V1.0     
 */
package cn.changhong.chcare.core.webapi.server;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import cn.changhong.chcare.core.webapi.AbstractChCareWebApi;
import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.bean.ResponseBeanWithRange;
import cn.changhong.chcare.core.webapi.fphone.bean.FuncFamNumView;
import cn.changhong.chcare.core.webapi.fphone.bean.FuncFenceView;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;

/**
 * 与功能机相关的接口定义
 * @ClassName: AFPhoneWebApiService
 * @Description: TODO
 * @author guoyang2011@gmail.com
 * @date 2014-9-12 上午9:46:32
 * 
 */
public abstract class AFPhoneWebApiService extends AbstractChCareWebApi
		implements IService {
	/**
	 * 获取绑定的功能机列表
	 * [8.1.获取绑定的功能机列表]
	 * @Title: getAllBindFPhones
	 * @Description: TODO
	 * @param @return
	 * @return {@code ResponseBean<List<FuncPhoneView>>}
	 * @throws
	 */
	public abstract ResponseBean<?> getAllBindFPhones()
			throws HttpRequestException;
	/**
	 * 获取绑定的功能机列表
	 * [8.1.获取绑定的功能机列表]
	 * @Title: getAllBindFPhones  
	 * @Description: TODO  
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<List<FuncPhoneView>>> }
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> gtAllBindFPhones(
			final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {

					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = getAllBindFPhones();
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FPhone_getAllBindFPhones_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FPhone_getAllBindFPhones_Service);
						}
						return bean;
					}
				});
		return future;
	}

	
	/**
	 * 获取绑定的功能机当前位置的列表
	 * [8.2.获取绑定的功能机当前位置的列表]
	 * @Title: getAllBindFPhonePosition  
	 * @Description: TODO  
	 * @param @param isNeedChangePoint 是否需要进行坐标转换[0:否, 1:WGS-84 -> GCJ-02]
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<List<FuncBasicPosView>>}
	 * @throws
	 */
	public abstract ResponseBean<?> getAllBindFPhonePosition(
			int isNeedChangePoint) throws HttpRequestException;
	/**
	 * 获取绑定的功能机当前位置的列表
	 * [8.2.获取绑定的功能机当前位置的列表]
	 * @Title: getAllBindFPhonePosition  
	 * @Description: TODO  
	 * @param @param isNeedChangePoint 是否需要进行坐标转换[0:否, 1:WGS-84 -> GCJ-02]
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<List<FuncBasicPosView>>>}
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> getAllBindFPhonePosition(
			final int isNeedChangePoint,
			final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {

					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = getAllBindFPhonePosition(isNeedChangePoint);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FPhone_getAllBindFPhonePosition_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FPhone_getAllBindFPhonePosition_Service);
						}
						return bean;
					}
				});
		return future;
	}

	/**
	 * 获取单个功能机当前位置
	 * [8.3.获取单个功能机当前位置]
	 * @Title: getBindFPhonePosition
	 * @Description: TODO
	 * @param @param devID
	 * @param @param isNeedChangePoint 是否需要进行坐标转换[0:否, 1:WGS-84 -> GCJ-02]
	 * @param @return
	 * @param @throws HttpRequestException
	 * @return {@code ResponseBean<FuncCurPosView>}
	 * @throws
	 */
	public abstract ResponseBean<?> getBindFPhonePosition(long devID,
			int isNeedChangePoint) throws HttpRequestException;
	/**
	 * 获取单个功能机当前位置
	 * [8.3.获取单个功能机当前位置]
	 * @Title: getBindFPhonePosition  
	 * @Description: TODO  
	 * @param @param devID
	 * @param @param isNeedChangePoint 是否需要进行坐标转换[0:否, 1:WGS-84 -> GCJ-02]
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<FuncCurPosView>>} 
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> getBindFPhonePosition(final long devID,
			final int isNeedChangePoint,
			final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {

					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = getBindFPhonePosition(devID,
									isNeedChangePoint);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FPhone_getBindFPhonePosition_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FPhone_getBindFPhonePosition_Service);
						}
						return bean;
					}
				});
		return future;
	}

	/**
	 * 获取单个功能机历史位置 
	 * [8.4.获取单个功能机历史位置]
	 * @Title: getBindFPhoneHistoryPositions
	 * @Description: TODO
	 * @param @param devID 功能机ID
	 * @param @param startTime (可选)起始日期时间
	 * @param @param endTime (可选)结束日期时间
	 * @param @param isNeedLBS (暂未实现)是否包含基站点[0:否, 1:是]
	 * @param @param isNeedChangePoint 是否需要进行坐标转换[0:否, 1:WGS-84 -> GCJ-02]
	 * @param @return
	 * @param @throws HttpRequestException
	 * @return {@code ResponseBean<List<FuncHistPosView>>}
	 * @throws
	 */
	public abstract ResponseBean<?> getBindFPhoneHistoryPositions(long devID,
			Date startTime, Date endTime, int isNeedLBS, int isNeedChangePoint)
			throws HttpRequestException;
	/**
	 * 获取单个功能机历史位置 
	 * [8.4.获取单个功能机历史位置]
	 * @Title: getBindFPhoneHistoryPositions  
	 * @Description: TODO  
	 * @param @param devID 功能机ID
	 * @param @param startTime (可选)起始日期时间
	 * @param @param endTime (可选)结束日期时间
	 * @param @param isNeedLBS (暂未实现)是否包含基站点[0:否, 1:是]
	 * @param @param isNeedChangePoint 是否需要进行坐标转换[0:否, 1:WGS-84 -> GCJ-02]
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<List<FuncHistPosView>>>}
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> getBindFPhoneHistoryPositions(
			final long devID, final Date startTime, final Date endTime,
			final int isNeedLBS, final int isNeedChangePoint,
			final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {
					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = getBindFPhoneHistoryPositions(devID,
									startTime, endTime, isNeedLBS,
									isNeedChangePoint);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FPhone_getBindFPhoneFence_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FPhone_getBindFPhoneFence_Service);
						}
						return bean;
					}
				});
		return future;
	}
	/**
	 * 获取单个功能机围栏列表
	 * [8.5.获取单个功能机围栏列表]
	 * @Title: getBindFPhoneFence  
	 * @Description: TODO  
	 * @param @param devID
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBeanWithRange<List<FuncFenceView>>}
	 * @throws
	 */
	public abstract ResponseBean<?> getBindFPhoneFence(long devID)
			throws HttpRequestException;
	/**
	 * 获取单个功能机围栏列表
	 * [8.5.获取单个功能机围栏列表]
	 * @Title: getBindFPhoneFence  
	 * @Description: TODO  
	 * @param @param devID
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBeanWithRange<List<FuncFenceView>>>}  
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> getBindFPhoneFence(final long devID,
			final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {
					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = getBindFPhoneFence(devID);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FPhone_getBindFPhoneFence_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FPhone_getBindFPhoneFence_Service);
						}
						return bean;
					}
				});
		return future;
	}
	/**
	 * 设置单个功能机围栏
	 * [8.6.设置单个功能机围栏]
	 * @Title: updateBindFPhoneFence  
	 * @Description: TODO  
	 * @param @param fphoneFence
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<NULL>}
	 * @throws
	 */
	public abstract ResponseBean<?> updateBindFPhoneFence(
			FuncFenceView fphoneFence) throws HttpRequestException;
	/**
	 * 设置单个功能机围栏
	 * [8.6.设置单个功能机围栏]
	 * @Title: updateBindFPhoneFence  
	 * @Description: TODO  
	 * @param @param fphoneFence
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<NULL>>}  
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> updateBindFPhoneFence(
			final FuncFenceView fphoneFence,
			final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {
					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = updateBindFPhoneFence(fphoneFence);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FPhone_updateBindFPhoneFence_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FPhone_updateBindFPhoneFence_Service);
						}
						return bean;
					}
				});
		return future;
	}
	/**
	 * 移除单个功能机围栏
	 * [8.7.移除单个功能机围栏]
	 * @Title: deleteBindFPhoneFence  
	 * @Description: TODO  
	 * @param @param devID
	 * @param @param fengceIndex
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<NULL>}  
	 * @throws
	 */
	public abstract ResponseBean<?> deleteBindFPhoneFence(long devID,
			int fengceIndex) throws HttpRequestException;
	/**
	 * 移除单个功能机围栏
	 * [8.7.移除单个功能机围栏]
	 * @Title: deleteBindFPhoneFence  
	 * @Description: TODO  
	 * @param @param devID 功能机ID
	 * @param @param fengceIndex 围栏序号[1~max]
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<NULL>>} 
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> deleteBindFPhoneFence(final long devID,
			final int fengceIndex,
			final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {
					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = deleteBindFPhoneFence(devID, fengceIndex);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FPhone_deleteBindFPhoneFence_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FPhone_deleteBindFPhoneFence_Service);
						}
						return bean;
					}
				});
		return future;
	}
	/**
	 * 获取单个功能机亲情号列表
	 * [8.8.获取单个功能机亲情号列表]
	 * @Title: getBindFPhoneAllPhoneNum  
	 * @Description: TODO  
	 * @param @param devID 功能机ID
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBeanWithRange<List<FuncFamNumView>>} 
	 * @throws
	 */
	public abstract ResponseBean<?> getBindFPhoneAllPhoneNum(long devID)
			throws HttpRequestException;
	/**
	 * 获取单个功能机亲情号列表
	 * [8.8.获取单个功能机亲情号列表]
	 * @Title: getBindFPhoneAllPhoneNum  
	 * @Description: TODO  
	 * @param @param devID 功能机ID
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBeanWithRange<List<FuncFamNumView>>>}  
	 * @throws
	 */
	
	public <T> Future<ResponseBean<?>> getBindFPhoneAllPhoneNum(
			final long devID, final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {
					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = getBindFPhoneAllPhoneNum(devID);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FPhone_getBindFPhoneAllPhoneNum_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FPhone_getBindFPhoneAllPhoneNum_Service);
						}
						return bean;
					}
				});
		return future;
	}
	/**
	 * 设置单个功能机亲情号
	 * [8.9.设置单个功能机亲情号]
	 * @Title: updateBindFPhone  
	 * @Description: TODO  
	 * @param @param funcFamNumView 设备亲情号信息
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<NULL>}  
	 * @throws
	 */
	public abstract ResponseBean<?> updateBindFPhone(
			FuncFamNumView funcFamNumView) throws HttpRequestException;
/**
 * 设置单个功能机亲情号
 * [8.9.设置单个功能机亲情号]
 * @Title: updateBindFPhone  
 * @Description: TODO  
 * @param @param funcFamNumView 设备亲情号信息
 * @param @param handler
 * @param @return      
 * @return {@code Future<ResponseBean<NULL>>} 
 * @throws
 */
	public <T> Future<ResponseBean<?>> updateBindFPhone(
			final FuncFamNumView funcFamNumView,
			final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {
					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = updateBindFPhone(funcFamNumView);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FPhone_updateBindFPhone_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FPhone_updateBindFPhone_Service);
						}
						return bean;
					}
				});
		return future;
	}
	/**
	 * 移除单个功能机亲情号
	 * [8.10.移除单个功能机亲情号]
	 * @Title: deleteBindFPhone  
	 * @Description: TODO  
	 * @param @param devID 功能机ID
	 * @param @param fphoneIndex  亲情号序号[1~max]
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<NULL>}
	 * @throws
	 */
	public abstract ResponseBean<?> deleteBindFPhone(long devID, int fphoneIndex)
			throws HttpRequestException;
	/**
	 * 移除单个功能机亲情号
	 * [8.10.移除单个功能机亲情号]
	 * @Title: deleteBindFPhone  
	 * @Description: TODO  
	 * @param @param devID 功能机ID
	 * @param @param fphoneIndex  亲情号序号[1~max]
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<NULL>>} 
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> deleteBindFPhone(final long devID,
			final int fphoneIndex,
			final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {
					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = deleteBindFPhone(devID, fphoneIndex);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FPhone_deleteBindFPhone_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FPhone_deleteBindFPhone_Service);
						}
						return bean;
					}
				});
		return future;
	}
	/**
	 * 获取单个功能机定位模式
	 * [8.11.获取单个功能机定位模式]
	 * @Title: getBindFPhonePositionMode  
	 * @Description: TODO  
	 * @param @param devID 功能机ID
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<Integer>}  
	 * @throws
	 */
	public abstract ResponseBean<?> getBindFPhonePositionMode(long devID)
			throws HttpRequestException;
	/**
	 * 获取单个功能机定位模式
	 * [8.11.获取单个功能机定位模式]
	 * @Title: getBindFPhonePositionMode  
	 * @Description: TODO  
	 * @param @param devID 功能机ID
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<Integer>>} 
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> getBindFPhonePositionMode(
			final long devID, final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {
					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = getBindFPhonePositionMode(devID);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FPhone_getBindFPhonePositionMode_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FPhone_getBindFPhonePositionMode_Service);
						}
						return bean;
					}
				});
		return future;
	}
	/**
	 * 设置单个功能机定位模式
	 * [8.12.设置单个功能机定位模式]
	 * @Title: updateBindFPhonePositionMode  
	 * @Description: TODO  
	 * @param @param devId 功能机ID
	 * @param @param locMode 定位模式信息
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<NULL>}  
	 * @throws
	 */
	public abstract ResponseBean<?> updateBindFPhonePositionMode(long devId,
			int locMode) throws HttpRequestException;
	/**
	 * 设置单个功能机定位模式
	 * [8.12.设置单个功能机定位模式]
	 * @Title: updateBindFPhonePositionMode  
	 * @Description: TODO  
	 * @param @param devId 功能机ID
	 * @param @param locMode 定位模式信息
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<NULL>>}
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> updateBindFPhonePositionMode(
			final long devId, final int locMode,
			final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {
					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = updateBindFPhonePositionMode(devId, locMode);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FPhone_updateBindFPhonePositionMode_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FPhone_updateBindFPhonePositionMode_Service);
						}
						return bean;
					}
				});
		return future;
	}
	/**
	 * 设置单个功能机昵称
	 * [8.13.设置单个功能机昵称]
	 * @Title: updateBindFPhoneNickInfo  
	 * @Description: TODO  
	 * @param @param devId 功能机ID
	 * @param @param nickName 昵称
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<NULL>}  
	 * @throws
	 */
	public abstract ResponseBean<?> updateBindFPhoneNickInfo(long devId,
			String nickName) throws HttpRequestException;
	/**
	 * 设置单个功能机昵称
	 * [8.13.设置单个功能机昵称]
	 * @Title: updateBindFPhoneNickInfo  
	 * @Description: TODO  
	 * @param @param devId 功能机ID
	 * @param @param nickName 昵称
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<NULL>>}
	 * @throws
	 */
	public <T> Future<ResponseBean<?>> updateBindFPhoneNickInfo(
			final long devId, final String nickName,
			final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {
					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = updateBindFPhoneNickInfo(devId, nickName);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FPhone_updateBindFPhoneNickInfo_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FPhone_updateBindFPhoneNickInfo_Service);
						}
						return bean;
					}
				});
		return future;
	}
	
	
	public abstract ResponseBean<?> unBindPhone(long devId) throws HttpRequestException;
	
	public <T> Future<ResponseBean<?>> unBindPhone(final long devId,final AsyncResponseCompletedHandler<T> handler){
		Future<ResponseBean<?>> future = executorProvider
				.doTask(new Callable<ResponseBean<?>>() {
					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = unBindPhone(devId);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_FPhone_unBindPhone_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_FPhone_unBindPhone_Service);
						}
						return bean;
					}
				});
		return future;
	}

}
