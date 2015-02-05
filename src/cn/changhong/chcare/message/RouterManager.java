package cn.changhong.chcare.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RouterManager<T> {
	private final RouterContext routers = new RouterContext();

	public RouterManager() {
		this.initRouterContext();
	}

	public void initRouterContext() {
		this.routers.addRouter(RouterType.FAMILY_PHOTOWALL_SERVICE_ROUTER,
				PhotoWallServiceProvider.Self.instance());

	}

	public RouterContext getRouters() {
		return this.routers;
	}

	public static enum RouterType {
		FAMILY_PHOTOWALL_SERVICE_ROUTER("fpsr"), FAMILY_MEMBER_SERVICE_ROUTER(
				"fmsr"), FAMILY_MESSAGEBOARD_SERVICE_ROUTER("fmsr"), FAMILY_HEALTHMANAGER_SERVICE_ROUTER(
				"fhsr"), FAMILY_SYSTEM_SERVICE_ROUTER("fssr");
		private String value;

		private RouterType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	private class RouterContext {
		private final Map<String, ArrayList<IRouter>> routers = new ConcurrentHashMap<String, ArrayList<IRouter>>();

		public void addRouter(RouterType type, IRouter router) {
			if (type == null || router == null) {
				return;
			}
			if (routers.containsKey(type.getValue())) {
				List items = routers.get(type.getValue());
				if (!items.contains(router)) {
					items.add(router);
				}
			} else {
				ArrayList<IRouter> items = new ArrayList<IRouter>();
				items.add(router);
				routers.put(type.getValue(), items);
			}
		}

		public List<IRouter> getRouter(RouterType type) {
			return routers.get(type.getValue());
		}

	}
}
