package cn.changhong.chcare.core.webapi;

import java.util.HashMap;
import java.util.Map;

import cn.changhong.chcare.core.webapi.bean.BloodPressure;
import cn.changhong.chcare.core.webapi.bean.RequestHealthParams;
import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.server.IHealthService;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;

public class ChCareWebApiHealthApi extends IHealthService {
	@Override
	public ResponseBean<?> addUserNewHealthInfo(int type, BloodPressure obj)
			throws HttpRequestException {// post,type/health
		String url = BASE_URL + "health";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bp", obj);
		params.put("type", type);

		return this.postRequestUtil(url, this.gson.toJson(params));
	}

	@Override
	public ResponseBean<?> searchUserHealthInfos(
			RequestHealthParams requestParams) throws HttpRequestException {// get//healthresult
		String url = BASE_URL + "health";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("param", requestParams);
		return this.postRequestUtil(url, this.gson.toJson(params));
	}

	@Override
	public ResponseBean<?> searchUserHealthInfo(String username, int type)
			throws HttpRequestException {
		String url = BASE_URL + "health?to_uid=" + username + "&type=" + type;
		return this.getRequestUtil(url);
	}

}
