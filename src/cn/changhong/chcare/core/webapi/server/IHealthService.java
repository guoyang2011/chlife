package cn.changhong.chcare.core.webapi.server;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import cn.changhong.chcare.core.webapi.AbstractChCareWebApi;
import cn.changhong.chcare.core.webapi.bean.BloodPressure;
import cn.changhong.chcare.core.webapi.bean.RequestHealthParams;
import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;

public abstract class IHealthService extends AbstractChCareWebApi implements
		IService {
	/**
	 * 健康信息查询
	 * [4.1.健康信息查询]
	 * @Title: searchUserHealthInfo  
	 * @Description: TODO  
	 * @param @param username
	 * @param @param type  0 表示血压
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<BloodPressure>} 
	 * @throws
	 */
	public abstract ResponseBean searchUserHealthInfo(String username, int type)
			throws HttpRequestException;// GET,Data=BloodPressure//health
	/**
	 * 健康信息查询
	 * [4.1.健康信息查询]
	 * @Title: searchUserHealthInfo  
	 * @Description: TODO  
	 * @param @param username
	 * @param @param type : 0表示血压
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<BloodPressure>>} 
	 * @throws
	 */
	public <T> Future<ResponseBean> searchUserHealthInfo(final String username,
			final int type, final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean> future = executorProvider
				.doTask(new Callable<ResponseBean>() {

					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = searchUserHealthInfo(username, type);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_Health_searchUserHealthInfo_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_Health_searchUserHealthInfo_Service);
						}
						return bean;
					}
				});
		return future;
	}
	/**
	 * 健康信息上传
	 * [4.2.健康信息上传]
	 * @Title: addUserNewHealthInfo  
	 * @Description: TODO  
	 * @param @param type 0为血压
	 * @param @param obj 血压
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<NULL>}  
	 * @throws
	 */
	public abstract ResponseBean addUserNewHealthInfo(int type,
			BloodPressure obj) throws HttpRequestException;// post,type /health
	/**
	 * 健康信息上传
	 * [4.2.健康信息上传]
	 * @Title: addUserNewHealthInfo  
	 * @Description: TODO  
	 * @param @param type 0为血压
	 * @param @param obj 血压
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBean<NULL>>}  
	 * @throws
	 */
	public <T> Future<ResponseBean> addUserNewHealthInfo(final int type,
			final BloodPressure obj,
			final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean> future = executorProvider
				.doTask(new Callable<ResponseBean>() {

					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = addUserNewHealthInfo(type, obj);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_Health_addUserNewHealthInfo_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_Health_addUserNewHealthInfo_Service);
						}
						return bean;
					}
				});
		return future;
	}
	/**
	 * 健康历史信息点查询
	 * [4.3.健康历史信息点查询]
	 * @Title: searchUserHealthInfos  
	 * @Description: TODO  
	 * @param @param params
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code ResponseBean<List<BloodPressure>>}
	 * @throws
	 */
	public abstract ResponseBean searchUserHealthInfos(
			RequestHealthParams params) throws HttpRequestException;// get//healthresult
	/**
	 * 健康历史信息点查询
	 * [4.3.健康历史信息点查询]
	 * @Title: searchUserHealthInfos  
	 * @Description: TODO  
	 * @param @param params
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<ResponseBeanList<BloodPressure>>} 
	 * @throws
	 */
	public <T> Future<ResponseBean> searchUserHealthInfos(
			final RequestHealthParams params,
			final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean> future = executorProvider
				.doTask(new Callable<ResponseBean>() {

					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = searchUserHealthInfos(params);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_Health_searchUserHealthInfos_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_Health_searchUserHealthInfos_Service);
						}
						return bean;
					}
				});
		return future;
	}
}
