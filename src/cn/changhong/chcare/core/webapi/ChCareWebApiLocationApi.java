package cn.changhong.chcare.core.webapi;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cn.changhong.chcare.core.webapi.bean.Location;
import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.bean.ThirdShare;
import cn.changhong.chcare.core.webapi.server.ILocationService;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;

public class ChCareWebApiLocationApi extends ILocationService {

	@Override
	public ResponseBean<?> searchUsersLastLocation(String username)
			throws HttpRequestException {
		String url = BASE_URL + "Location?userName=" + username;
		String response = this.baseGetRequestUtil(url);
		ResponseBean<?> result = this.transToBean(response);
		if (result!=null&&result.getState() >= 0) {
			Type type = new TypeToken<ResponseBean<Location>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
		return result;
	}

	@Override
	public ResponseBean<?> updateUserCurrentLocation(Location loc)
			throws HttpRequestException {
		String url = BASE_URL + "Location";

		return this.postRequestUtil(url, this.gson.toJson(loc));
	}

	@Override
	public ResponseBean<?> uploadShareMsgToThirdPlatformLog(ThirdShare log)
			throws HttpRequestException {
		String url = BASE_URL + "ShareLocation";

		return this.postRequestUtil(url, this.gson.toJson(log));
	}

	@Override
	public ResponseBean<?> searchUserHistoryPosition(String username,
			java.util.Date st, java.util.Date et, String type)
			throws HttpRequestException {
		Gson tempGson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
		String url = BASE_URL + "Hisloc?username=" + username + "&st="
				+ tempGson.toJson(st).replaceAll("\"", "") + "&et="
				+ tempGson.toJson(et).replaceAll("\"", "") + "&type=" + type;
		System.out.println(url);
		String response = this.baseGetRequestUtil(url);

		ResponseBean<?> result = this.transToBean(response);
		if (result!=null&&result.getState() >= 0) {
			Type beanType = new TypeToken<ResponseBean<List<Location>>>() {
			}.getType();
			result = this.transToBean(response, beanType);
		}

		return result;
	}

}
