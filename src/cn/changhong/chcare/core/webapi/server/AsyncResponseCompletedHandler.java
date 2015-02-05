package cn.changhong.chcare.core.webapi.server;

import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;
/**
 * shdjsahdjs
 * dsaddfdsfdhgfhgf
 * @ClassName: AsyncResponseCompletedHandler  
 * @Description: TODO  
 * @author guoyang2011@gmail.com  
 * @date 2014-9-24 下午5:10:04  
 *   
 * @param <T>
 */
public abstract class AsyncResponseCompletedHandler<T> {
	/**
	 * ad
	 * @Title: onCompleted  
	 * @Description: TODO  
	 * @param @param response
	 * @param @return      
	 * @return TTTTTTTTT  
	 * @throws
	 */
	public abstract T onCompleted(ResponseBean<?> response,ChCareWepApiServiceType servieType);

	public abstract void onThrowable(HttpRequestException t,ChCareWepApiServiceType servieType);
	
	
}
