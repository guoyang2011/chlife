/**     
 * @Title: s2.java  
 * @Package cn.changhong.chcare.core.webapi  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-10-9 下午4:55:47  
 * @version V1.0     
*/  
package cn.changhong.chcare.core.webapi;  

import java.io.ByteArrayInputStream;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.Future;

import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.server.AsyncResponseCompletedHandler;
import cn.changhong.chcare.core.webapi.server.CHCareWebApiProvider;
import cn.changhong.chcare.core.webapi.server.ChCareWepApiServiceType;
import cn.changhong.chcare.core.webapi.server.IAccountService;
import cn.changhong.chcare.core.webapi.server.CHCareWebApiProvider.WebApiServerType;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;
  
/**  
 * @ClassName: s2  
 * @Description: TODO  
 * @author guoyang2011@gmail.com  
 * @date 2014-10-9 下午4:55:47  
 *     
 */
public class S2{
	/**
	 * 
	 * @ClassName: Async  
	 * @Description: TODO  
	 * @author guoyang2011@gmail.com  
	 * @date 2014-10-15 上午9:05:05  
	 *{@code AsyncResponseCompletedHandler}&lt;String&gt;
	 */
	public static class Async extends AsyncResponseCompletedHandler<String> {

		@Override
		public String onCompleted(ResponseBean<?> response,
				ChCareWepApiServiceType servieType) {
			System.out.println(servieType);
			
			return null;
		}

		@Override
		public void onThrowable(HttpRequestException t,
				ChCareWepApiServiceType servieType) {
			System.out.println(servieType+","+t.getMessage()); 
			
		}

		
	};
	/**
	 * 
	 * @Title: main  
	 * @Description: TODO  
	 * @param @param {@code AsyncResponseCompletedHandler}&lt;String&gt;  
	 * @param @throws InterruptedException      
	 * @return 
	 * @throws
	 */
	public static void main(String[] agrs) throws InterruptedException{
		IAccountService accountService=(IAccountService)CHCareWebApiProvider.newInstance().getDefaultWebApiService(WebApiServerType.CHCARE_ACCOUNT_SERVER);
		AbstractChCareWebApi tt=(AbstractChCareWebApi)accountService;
		Async a=new Async();
		{
			try{
				accountService.login("1", "1",a);
		//		Thread.sleep(1000);
				accountService.uploadUserPhoto("dd",a);
			}catch(Exception ex){
				ex.printStackTrace();
				Thread.sleep(1000);
			}
		}
//		try {
//			Future<ResponseBean> bean=accountService.uploadUserPhoto(new ByteArrayInputStream(new byte[1024]),"xx.jpg",a);
//			if(bean.get() !=null){
//				System.out.println(bean.get().getDesc()+"????????????????????");
//			}else{
//				System.out.println("null");
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block  
//			e.printStackTrace();
//			System.out.println(">>>");
//		}
	}
}
