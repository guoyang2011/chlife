/**     
 * @Title: s1.java  
 * @Package cn.changhong.chcare.core.webapi  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-10-9 下午4:55:22  
 * @version V1.0     
*/  
package cn.changhong.chcare.core.webapi;  

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.changhong.chcare.core.webapi.server.AsyncResponseCompletedHandler;
import cn.changhong.chcare.core.webapi.server.ChCareWepApiServiceType;
import cn.changhong.chcare.core.webapi.server.IService;
  
/**  
 * @ClassName: s1  
 * @Description: TODO  
 * @author guoyang2011@gmail.com  
 * @date 2014-10-9 下午4:55:22  
 *     
 */
public class S1 {
	private final static Map<ChCareWepApiServiceType,Method> allService=new ConcurrentHashMap<ChCareWepApiServiceType,Method>();
	static{
		
		List<Class<?>> clazzs=new ArrayList<Class<?>>();
		clazzs.add(ChCareWebApiAccountnApi.class.getSuperclass());
		clazzs.add(ChCareWebApiLocationApi.class.getSuperclass());
		clazzs.add(ChCareWebApiHealthApi.class.getSuperclass());
		clazzs.add(ChCareWebApiOfflineMessageApi.class.getSuperclass());
		clazzs.add(CHCareWebApiFamilyApi.class.getSuperclass());
		clazzs.add(ChCareWebApiFPhoneApi.class.getSuperclass());
		
		clazzs.add(CHCareWebApiPhotoWallApi.class.getSuperclass());
		clazzs.add(ChCareWebApiFamilyMsgBoardApi.class.getSuperclass());
		
		
		Map<String,Method> temp=new HashMap<String, Method>();
		
		String type=AsyncResponseCompletedHandler.class.getName();
		for(Class<?> clazz:clazzs){
			for(Method method:clazz.getMethods()){
				for(Class<?> param:method.getParameterTypes()){
					if(param.getName().equals(type)){
						temp.put(method.getName(), method);
					}
				}
			}
		}
		Class<?> clazz=ChCareWepApiServiceType.class;
		if(clazz.isEnum()){
			String name="";
			Method method=null;
			for(Object cla:clazz.getEnumConstants()){
				name=cla.toString().split("_")[2];
				method=temp.get(name);
				if(null != method){
					System.out.println(cla+"&"+method);
					allService.put((ChCareWepApiServiceType)cla, method);
				}
			}
		}
	}
	public static void main(String[] args){
		File file=new File("C:\\Users\\Administrator\\Desktop\\长虹关爱\\系统图\\NCHFamily\\CHFamily\\libs\\chcare-webapi-1.5.0-RELEASE.jar");
		System.out.println(URLConnection.guessContentTypeFromName(file.getName()));
//	 IAccountService accountService=(IAccountService)CHCareWebApiProvider.newInstance().getDefaultWebApiService(WebApiServerType.CHCARE_ACCOUNT_SERVER);
//	 System.out.println(accountService.getServerUrl().toString());
//	 try {
//		accountService.setServerUrl(new URL("http://"));
//	} catch (MalformedURLException e) {
//		// TODO Auto-generated catch block  
//		e.printStackTrace();
//		 System.out.println(accountService.getServerUrl().toString());
//	}
//	 exec(accountService,ChCareWepApiServiceType.WebApi_Account_login_Service,"1","1",new AsyncResponseCompletedHandler<String>() {
//
//		@Override
//		public String onCompleted(ResponseBean<?> response,
//				ChCareWepApiServiceType servieType) {
//			System.out.println(">>"); 
//			return null;
//		}
//
//		@Override
//		public void onThrowable(HttpRequestException t,
//				ChCareWepApiServiceType servieType) {
//			System.out.println(">>>>"+t.getMessage()+","+t.getErrorType()+","+servieType); 
//			
//		}
//	});
//		
//		
	}
	public static void exec(IService iService,ChCareWepApiServiceType serviceType,Object...params){
		Method method=allService.get(serviceType);
		if(method == null){
			return;
		}
		try {
			method.invoke(iService, params);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
