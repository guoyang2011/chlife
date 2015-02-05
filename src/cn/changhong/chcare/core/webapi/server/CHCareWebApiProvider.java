package cn.changhong.chcare.core.webapi.server;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import cn.changhong.chcare.core.webapi.CHCareWebApiFamilyApi;
import cn.changhong.chcare.core.webapi.CHCareWebApiPhotoWallApi;
import cn.changhong.chcare.core.webapi.ChCareAppManagerApi;
import cn.changhong.chcare.core.webapi.ChCareWebApiAccountnApi;
import cn.changhong.chcare.core.webapi.ChCareWebApiFPhoneApi;
import cn.changhong.chcare.core.webapi.ChCareWebApiFamilyMsgBoardApi;
import cn.changhong.chcare.core.webapi.ChCareWebApiHealthApi;
import cn.changhong.chcare.core.webapi.ChCareWebApiLocationApi;
import cn.changhong.chcare.core.webapi.ChCareWebApiOfflineMessageApi;

public class CHCareWebApiProvider {
	
	private static final Map<WebApiServerType, IService> currentServerMap = new WeakHashMap<WebApiServerType, IService>();
	private static final Map<WebApiServerType, Class<? extends IService>> defaultServiceMap = new HashMap<WebApiServerType, Class<? extends IService>>();

	private CHCareWebApiProvider() {
	}

	public static class Self {
		private static CHCareWebApiProvider instance = null;

		public static CHCareWebApiProvider defaultInstance() {
			if (instance == null) {
				instance = new CHCareWebApiProvider();
			}
			return instance;
		}
	}

	static {
		defaultServiceMap.put(WebApiServerType.CHCARE_ACCOUNT_SERVER,
				ChCareWebApiAccountnApi.class);
		defaultServiceMap.put(WebApiServerType.CHCARE_LOCATION_SERVER,
				ChCareWebApiLocationApi.class);
		defaultServiceMap.put(WebApiServerType.CHCARE_HEALTH_SERVER,
				ChCareWebApiHealthApi.class);
		defaultServiceMap.put(WebApiServerType.CHCARE_OFFLINEMESSAGE_SERVER,
				ChCareWebApiOfflineMessageApi.class);
		defaultServiceMap.put(WebApiServerType.CHCARE_FAMILY_SERVER,
				CHCareWebApiFamilyApi.class);
		defaultServiceMap.put(WebApiServerType.CHCARE_FUNCPHONE_SERVER,
				ChCareWebApiFPhoneApi.class);
		defaultServiceMap.put(WebApiServerType.CHCARE_PHOTOWALL_SERVER, CHCareWebApiPhotoWallApi.class);
		defaultServiceMap.put(WebApiServerType.CHCARE_FAMILYMSGBOARD_SERVER, ChCareWebApiFamilyMsgBoardApi.class);
		defaultServiceMap.put(WebApiServerType.CHCARE_CHCAREAPPMANAGERAPI_SERVER, ChCareAppManagerApi.class);
	}

	public static CHCareWebApiProvider newInstance() {
		return Self.defaultInstance();
	}

	public static enum WebApiServerType {
		CHCARE_ACCOUNT_SERVER("accountservice"), CHCARE_HEALTH_SERVER(
				"healthservice"), CHCARE_LOCATION_SERVER("locationservice"), CHCARE_FAMILY_SERVER(
				"familyservice"), CHCARE_PHOTOWALL_SERVER("photowallservice"), CHCARE_OFFLINEMESSAGE_SERVER(
				"offlinemessageservice"), CHCARE_FUNCPHONE_SERVER(
				"funcphoneservice"),CHCARE_FAMILYMSGBOARD_SERVER("familymgsboardserver"), CHCARE_CHCAREAPPMANAGERAPI_SERVER("appmanagerserver");
		private String value;

		private WebApiServerType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
	
	public IService getDefaultWebApiService(WebApiServerType type) {
		IService result = currentServerMap.get(type);
		if (result == null) {
			Class<?> serviceClass = defaultServiceMap.get(type);
			if (serviceClass != null) {
				try {
					result = (IService) serviceClass.getConstructors()[0]
							.newInstance(null);
					synchronized (currentServerMap) {
						currentServerMap.put(type, result);
					}
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
