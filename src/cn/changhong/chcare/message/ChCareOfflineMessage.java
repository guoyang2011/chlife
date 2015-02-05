package cn.changhong.chcare.message;

import cn.changhong.chcare.core.webapi.bean.OfflineMessageBean;

public class ChCareOfflineMessage<T> {
	private RouterType router;
	private OfflineMessageBean<T> message;

	public RouterType getRouter() {
		return router;
	}

	public void setRouter(RouterType router) {
		this.router = router;
	}

	public OfflineMessageBean<T> getMessage() {
		return message;
	}

	public void setMessage(OfflineMessageBean<T> message) {
		this.message = message;
	}
}
