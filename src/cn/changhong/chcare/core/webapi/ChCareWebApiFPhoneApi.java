/**     
 * @Title: ChCareWebApiFPhoneApi.java  
 * @Package cn.changhong.chcare.core.webapi  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-9-17 上午10:41:43  
 * @version V1.0     
 */
package cn.changhong.chcare.core.webapi;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.bean.ResponseBeanWithRange;
import cn.changhong.chcare.core.webapi.fphone.bean.FuncBasicPosView;
import cn.changhong.chcare.core.webapi.fphone.bean.FuncCurPosView;
import cn.changhong.chcare.core.webapi.fphone.bean.FuncFamNumView;
import cn.changhong.chcare.core.webapi.fphone.bean.FuncFenceView;
import cn.changhong.chcare.core.webapi.fphone.bean.FuncHistPosView;
import cn.changhong.chcare.core.webapi.fphone.bean.FuncPhoneView;
import cn.changhong.chcare.core.webapi.server.AFPhoneWebApiService;
import cn.changhong.chcare.core.webapi.server.ChCareWebApiRequestErrorType;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @ClassName: ChCareWebApiFPhoneApi
 * @Description: TODO
 * @author guoyang2011@gmail.com
 * @date 2014-9-17 上午10:41:43
 * 
 */
public class ChCareWebApiFPhoneApi extends AFPhoneWebApiService {

	/**
	 * 获取绑定的功能机列表 Get <domain>/api/funcs
	 * 
	 * @return ResponseBean<List<FuncPhoneView>>
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.server.AFPhoneWebApiService#getAllBindFPhones()
	 */

	@Override
	public ResponseBean<?> getAllBindFPhones() throws HttpRequestException {
		String url = BASE_URL + "funcs";
		String response = this.baseGetRequestUtil(url);
		ResponseBean<?> result = this.transToBean(response);
		if (result!=null&&result.getState() == 0) {
			Type type = new TypeToken<ResponseBean<List<FuncPhoneView>>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
		return result;
	}

	/**
	 * 获取绑定的功能机当前位置的列表 Get
	 * <domain>/api/funcsloc?isNeedChangePoint={isNeedChangePoint}
	 * 
	 * @return
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.server.AFPhoneWebApiService#getAllBindFPhonePosition()
	 */

	@Override
	public ResponseBean<?> getAllBindFPhonePosition(int isNeedChangePoint)
			throws HttpRequestException {
		String url = BASE_URL + "funcsloc?isNeedChangePoint="
				+ isNeedChangePoint;
		String response = this.baseGetRequestUtil(url);
		ResponseBean<?> result = this.transToBean(response);
		if (result!=null&&result.getState() == 0) {
			Type type = new TypeToken<ResponseBean<List<FuncBasicPosView>>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
		return result;
	}

	/**
	 * 获取单个功能机当前位置 Get
	 * <domain>/api/funcloc?devId={devId}&isNeedChangePoint={isNeedChangePoint}
	 * 
	 * @param devID
	 * @param isNeedChangePoint
	 * @return
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.server.AFPhoneWebApiService#getBindFPhonePosition(java.lang.String,
	 *      boolean)
	 */

	@Override
	public ResponseBean<?> getBindFPhonePosition(long devID,
			int isNeedChangePoint) throws HttpRequestException {

		String url = BASE_URL + "funcloc?devId=" + devID
				+ "&isNeedChangePoint=" + isNeedChangePoint;
		String response = this.baseGetRequestUtil(url);
		ResponseBean<?> result = this.transToBean(response);
		if (result!=null&&result.getState() == 0) {
			Type type = new TypeToken<ResponseBean<FuncCurPosView>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
		return result;
	}

	/**
	 * 获取单个功能机历史位置 Get
	 * <domain>/api/funchistlocs?devId={devId}&beginTime={beginTime
	 * }&endTime={endTime
	 * }&isNeedLBS={isNeedLBS}&isNeedChangePoint={isNeedChangePoint}
	 * 
	 * @param devID
	 * @param startTime
	 * @param endTime
	 * @param isNeedLBS
	 * @param isNeedChangePoint
	 * @return ResponseBean<List<FuncHistPosView>>
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.server.AFPhoneWebApiService#getBindFPhoneHistoryPositions(java.lang.String,
	 *      java.util.Date, java.util.Date, boolean, boolean)
	 */

	@Override
	public ResponseBean<?> getBindFPhoneHistoryPositions(long devID,
			Date startTime, Date endTime, int isNeedLBS, int isNeedChangePoint)
			throws HttpRequestException {
		if (startTime == null || endTime == null) {
			throw new HttpRequestException(
					"Get Bind Phone History Position Failed!Enter params Illegal!",
					ChCareWebApiRequestErrorType.CHCAREWEBAPI_REQUEST_ERROR);
		}
		Gson tempgson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
		String url = BASE_URL + "funchistlocs?devId=" + devID + "&beginTime="
				+ tempgson.toJson(startTime).replaceAll("\"", "") + "&endTime="
				+ tempgson.toJson(endTime).replaceAll("\"", "") + "&isNeedLBS="
				+ isNeedLBS + "&isNeedChangePoint=" + isNeedChangePoint;
		// String
		// url=BASE_URL+"funchistlocs?devId="+devID+"&isNeedLBS="+isNeedLBS+"&isNeedChangePoint="+isNeedChangePoint;
		String response = this.baseGetRequestUtil(url);
		ResponseBean<?> result = this.transToBean(response);
		if (result!=null&&result.getState() == 0) {
			Type type = new TypeToken<ResponseBean<List<FuncHistPosView>>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
		return result;
	}

	/**
	 * 获取单个功能机围栏列表 Get <domain>/api/funcfence?devId={devId}
	 * 
	 * @param devID
	 * @return
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.server.AFPhoneWebApiService#getBindFPhoneFence(java.lang.String)
	 */

	@Override
	public ResponseBean<?> getBindFPhoneFence(long devID)
			throws HttpRequestException {
		String url = BASE_URL + "funcfence?devId=" + devID;
		String response = this.baseGetRequestUtil(url);
		ResponseBean<?> result = this.transToBean(response);
		if (result!=null&&result.getState() == 0) {
			Type type = new TypeToken<ResponseBeanWithRange<List<FuncFenceView>>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
		return result;
	}

	/**
	 * 设置单个功能机围栏 Post <domain>/api/funcfence
	 * 
	 * @param fphoneFence
	 * @return
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.server.AFPhoneWebApiService#updateBindFPhoneFence(java.lang.Object)
	 */

	@Override
	public ResponseBean<?> updateBindFPhoneFence(FuncFenceView fphoneFence)
			throws HttpRequestException {
		String url = BASE_URL + "funcfence";
		return this.postRequestUtil(url, this.gson.toJson(fphoneFence));
	}

	/**
	 * 移除单个功能机围栏 Delete <domain>/api/funcfence?DevId={DevId}&Seq={Seq}
	 * 
	 * @param devID
	 * @param fengceIndex
	 * @return
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.server.AFPhoneWebApiService#deleteBindFPhoneFence(java.lang.String,
	 *      int)
	 */

	@Override
	public ResponseBean<?> deleteBindFPhoneFence(long devID, int fengceIndex)
			throws HttpRequestException {
		String url = BASE_URL + "funcfence?DevId=" + devID + "&Seq="
				+ fengceIndex;

		return this.deleteRequestUtil(url, "");
	}

	/**
	 * 获取单个功能机亲情号列表 Get <domain>/api/funcfamnum?devId={devId}
	 * 
	 * @param devID
	 * @return
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.server.AFPhoneWebApiService#getBindFPhoneAllPhoneNum(java.lang.String)
	 */

	@Override
	public ResponseBean<?> getBindFPhoneAllPhoneNum(long devID)
			throws HttpRequestException {
		String url = BASE_URL + "funcfamnum?devId=" + devID;
		String response = this.baseGetRequestUtil(url);
		
		
		
		System.out.println("JHFJHDJHFJDHJFHDJHFKJHJKHJH"+response);
		
		ResponseBean<?> result = this.transToBean(response);
		if (result!=null&&result.getState() == 0) {
			Type type = new TypeToken<ResponseBeanWithRange<List<FuncFamNumView>>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
		return result;
	}

	/**
	 * 设置单个功能机亲情号 Post <domain>/api/funcfamnum
	 * 
	 * @param funcFamNumView
	 * @return
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.server.AFPhoneWebApiService#updateBindFPhone(java.lang.Object)
	 */

	@Override
	public ResponseBean<?> updateBindFPhone(FuncFamNumView funcFamNumView)
			throws HttpRequestException {
		String url = BASE_URL + "funcfamnum";

		return this.postRequestUtil(url, this.gson.toJson(funcFamNumView));
	}

	/**
	 * 移除单个功能机亲情号 Delete <domain>/api/funcfamnum?DevId={DevId}&Seq={Seq}
	 * 
	 * @param devID
	 * @param fphoneIndex
	 * @return
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.server.AFPhoneWebApiService#deleteBindFPhone(java.lang.String,
	 *      int)
	 */

	@Override
	public ResponseBean<?> deleteBindFPhone(long devID, int fphoneIndex)
			throws HttpRequestException {
		String url = BASE_URL + "funcfamnum?DevId=" + devID + "&Seq="
				+ fphoneIndex;

		return this.deleteRequestUtil(url, "");
	}

	/**
	 * 获取单个功能机定位模式 Get <domain>/api/funclocmode?devId={devId}
	 * 
	 * @param devID
	 * @return
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.server.AFPhoneWebApiService#getBindFPhonePositionMode(java.lang.String)
	 */

	@Override
	public ResponseBean<?> getBindFPhonePositionMode(long devID)
			throws HttpRequestException {
		String url = BASE_URL + "funclocmode?devId=" + devID;
		String response = this.baseGetRequestUtil(url);
		ResponseBean<?> result = this.transToBean(response);
		if (result!=null&&result.getState() == 0) {
			Type type = new TypeToken<ResponseBean<Integer>>() {
			}.getType();
			result = this.transToBean(response, type);
		}
		return result;
	}

	/**
	 * 设置单个功能机定位模式 Post <domain>/api/funclocmode
	 * 
	 * @param pmode
	 * @return
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.server.AFPhoneWebApiService#updateBindFPhonePositionMode(cn.changhong.chcare.core.webapi.fphone.bean.FuncFamNumView)
	 */

	@Override
	public ResponseBean<?> updateBindFPhonePositionMode(long devId, int locMode)
			throws HttpRequestException {
		String url = BASE_URL + "funclocmode";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("devId", devId);
		param.put("locMode", locMode);
		return this.postRequestUtil(url, this.gson.toJson(param));
	}

	/**
	 * 设置单个功能机昵称 Post <domain>/api/funcnick
	 * 
	 * @param devId
	 * @param nickName
	 * @return
	 * @throws HttpRequestException
	 * @see cn.changhong.chcare.core.webapi.server.AFPhoneWebApiService#updateBindFPhoneNickInfo(java.lang.String,
	 *      java.lang.String)
	 */

	@Override
	public ResponseBean<?> updateBindFPhoneNickInfo(long devId, String nickName)
			throws HttpRequestException {
		String url = BASE_URL + "funclocmode";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("devId", devId);
		param.put("nickName", nickName);
		return this.postRequestUtil(url, this.gson.toJson(param));
	}

	/**  
	 *   
	 *   
	 * @param devId
	 * @return
	 * @throws HttpRequestException  
	 * @see cn.changhong.chcare.core.webapi.server.AFPhoneWebApiService#unBindPhone(long)  
	*/  
	
	@Override
	public ResponseBean<?> unBindPhone(long devId) throws HttpRequestException {
		String url=BASE_URL+"func?devId="+devId;
		return this.deleteRequestUtil(url, "");
	}
}
