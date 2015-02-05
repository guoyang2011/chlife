package cn.changhong.chcare.core.webapi.server;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import cn.changhong.chcare.core.webapi.AbstractChCareWebApi;
import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;

public abstract class IOfflineMessageService extends AbstractChCareWebApi
		implements IService {

	/**
	 * 获取消息
	 * [7.1.获取消息(消息轮询)]
	 * GET <domain>/api/msg 参数(可选)
	 * ?startIndex={startIndex}&endIndex={endIndex}&count={count}&type={type}
	 * startIndex: {int} (可选)开始查询的信息id(不含), 为null则表示从第一条开始 endIndex: {int}
	 * (可选)结束查询的信息id, &gt;startIndex, 用于count查询一段信息后用户继续拖动,
	 * 为null则表示到最近一条结束ugfdgfdgfdgfdgfdgfdgfdgfdnbvnbv count: {int}
	 * (可选)只查询最近的count条, 为null则查询所有 type: {MsgType:int} (可选)消息类型, 为null则查询所有
	 * 
	 * @Title: getUserOfflineMessage  
	 * @Description: TODO  
	 * @param @param startIndex
	 * @param @param endIndex
	 * @param @param count
	 * @param @param type
	 * @param @return
	 * @param @throws HttpRequestException      
	 * @return {@code List<ChCareOfflineMessage<OfflineMessageBean<T>>>} T的类型根据消息类型决定  {@link OfflineMessageContent}或者相应基本数据的实体类如{@link Family},{@link User}等
	 * @throws
	 */
	public abstract ResponseBean getUserOfflineMessage(long startIndex,
			long endIndex, int count, int type) throws HttpRequestException;
	/**
	 * 获取消息
	 * [7.1.获取消息(消息轮询)]
	 * @Title: getUserOfflineMessage  
	 * @Description: 本地定时调用更新消息，每次获取到消息后保存消息最大ID,下次调用时 
	 * @param @param startIndex
	 * @param @param endIndex
	 * @param @param count
	 * @param @param type
	 * @param @param handler
	 * @param @return      
	 * @return {@code Future<List<ChCareOfflineMessage<OfflineMessageBean<T>>>>} T的类型根据消息类型决定  
	 * @throws
	 */
	public <T> Future<ResponseBean> getUserOfflineMessage(final long startIndex,
			final long endIndex, final int count, final int type,
			final AsyncResponseCompletedHandler<T> handler) {
		Future<ResponseBean> future = executorProvider
				.doTask(new Callable<ResponseBean>() {

					@Override
					public ResponseBean call() {
						ResponseBean bean = null;
						try {
							bean = getUserOfflineMessage(startIndex, endIndex,
									count, type);
							handler.onCompleted(bean,ChCareWepApiServiceType.WebApi_OfflineMsg_getUserOfflineMessage_Service);
						} catch (HttpRequestException e) {
							handler.onThrowable(e,ChCareWepApiServiceType.WebApi_OfflineMsg_getUserOfflineMessage_Service);
						}
						return bean;
					}
				});
		return future;
	}
}
