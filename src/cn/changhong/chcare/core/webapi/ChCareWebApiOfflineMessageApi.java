package cn.changhong.chcare.core.webapi;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import cn.changhong.chcare.core.webapi.bean.Family;
import cn.changhong.chcare.core.webapi.bean.OfflineMessageBean;
import cn.changhong.chcare.core.webapi.bean.OfflineMessageContent;
import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.photowalll.bean.PhotoView;
import cn.changhong.chcare.core.webapi.server.IOfflineMessageService;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;
import cn.changhong.chcare.message.ChCareOfflineMessage;
import cn.changhong.chcare.message.RouterType;

public class ChCareWebApiOfflineMessageApi extends IOfflineMessageService {
	@Override
	public ResponseBean getUserOfflineMessage(long startIndex, long endIndex,
			int count, int type) throws HttpRequestException {
		String url = BASE_URL + "msg";
		StringBuilder sb = new StringBuilder();
		if (startIndex >= 0) {
			sb.append("startIndex=").append(startIndex).append("&");
		}
		if (endIndex > 0) {
			sb.append("endIndex=").append(endIndex).append("&");
		}
		if (count > 0) {
			sb.append("count=").append(count).append("&");
		}
		if (type > 0) {
			sb.append("type=").append(type).append("&");
		}
		if (sb.length() > 0) {
			String params = sb.toString().substring(0, sb.length() - 1);
			url += "?" + params;
		}
		System.out.println("request url:" + url);
		String response = this.baseGetRequestUtil(url);

		ResponseBean<?> result = this.transToBean(response);
		if (result!=null&&result.getState() >= 0) {
			Type beanType = new TypeToken<ResponseBean<List<OfflineMessageBean>>>() {
			}.getType();
			ResponseBean responseBean = this.transToBean(response, beanType);
			result = transToLocalOfflineMessage(responseBean);
		}

		return result;
	}

	private ResponseBean transToLocalOfflineMessage(
			ResponseBean<List<OfflineMessageBean>> responseBean) {

		if (responseBean.getState() < 0) {
			return responseBean;
		}

		ResponseBean<List<ChCareOfflineMessage>> result = new ResponseBean<List<ChCareOfflineMessage>>();
		result.setDesc(responseBean.getDesc());
		result.setState(responseBean.getState());

		List<ChCareOfflineMessage> offlineMsgs = new ArrayList<ChCareOfflineMessage>();

		List<OfflineMessageBean> msgs = responseBean.getData();
		for (Iterator it = msgs.iterator(); it.hasNext();) {
			Object obj = it.next();
			ChCareOfflineMessage offlineMsg = null;
			if (obj instanceof OfflineMessageBean) {
				OfflineMessageBean tempBean = (OfflineMessageBean) obj;
//				System.out.println(""+tempBean.getType()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
//						+ tempBean.getVal().toString()+",");
				Object val = tempBean.getVal();
				if (val instanceof String) {
					// 判断消息类型解析成相应的消息对象
					String gsonStr = val.toString();

					offlineMsg = new ChCareOfflineMessage();

					int type = tempBean.getType();
					if (type >= 100 && type < 200) {
						tempBean.setVal(this.transToFamilyMemberContent(
								tempBean.getType(), gsonStr));
						offlineMsg
								.setRouter(RouterType.FAMILY_MEMBER_SERVICE_ROUTER);
						offlineMsg.setMessage(tempBean);
					} else if (type >= 200 && type < 300) {
						tempBean.setVal(this.transToPhotoWallContent(tempBean.getType(), gsonStr));
						offlineMsg
								.setRouter(RouterType.FAMILY_PHOTOWALL_SERVICE_ROUTER);
						offlineMsg.setMessage(tempBean);
					} else if (type >= 300 && type < 400) {
						offlineMsg
								.setRouter(RouterType.FAMILY_MESSAGEBOARD_SERVICE_ROUTER);
					} else if (type >= 400 && type < 500) {
						offlineMsg
								.setRouter(RouterType.FAMILY_HEALTHMANAGER_SERVICE_ROUTER);
					} else {
						offlineMsg
								.setRouter(RouterType.FAMILY_SYSTEM_SERVICE_ROUTER);
					}
				}

			} else if (obj instanceof LinkedTreeMap) {
				LinkedTreeMap<String, Object> mapObj = (LinkedTreeMap<String, Object>) it
						.next();
				System.out.println(this.gson.toJson(obj)
						+ obj.getClass().getName());
				offlineMsg = this.getMessage(mapObj);
			}
			offlineMsgs.add(offlineMsg);

		}
		result.setData(offlineMsgs);
		return result;
	}
	private Object transToPhotoWallContent(int type,String msg){
		Object obj=msg;
		switch(type){
		case 200:
			obj=this.gson.fromJson(msg, PhotoView.class);
			break;
		case 201:
			System.out.println(type+","+msg);
			try{
				obj=Integer.valueOf(msg.trim());
			}catch(NumberFormatException ex){
				obj=-1;
			}
			break;
		}
		return obj;
	}
	private Object transToFamilyMemberContent(int type, String msg) {
		Object obj = null;
		switch (type) {
		case 150:// Family
			obj = this.gson.fromJson(msg, Family.class);
			break;
		default:// OfflineMessageContent
			obj = this.gson.fromJson(msg, OfflineMessageContent.class);
			break;
		}
		return obj;
	}

	private OfflineMessageBean transToFamilyMemberContent(
			LinkedTreeMap<String, Object> msg, int type) {
		OfflineMessageBean result = null;
		Type beanType = null;
		switch (type) {
		case 150:// Family
			beanType = new TypeToken<OfflineMessageBean<Family>>() {
			}.getType();
			break;
		default:// OfflineMessageContent
			beanType = new TypeToken<OfflineMessageBean<OfflineMessageContent>>() {
			}.getType();
			break;
		}
		result = this.gson.fromJson(this.gson.toJson(msg), beanType);
		return result;
	}

	private ChCareOfflineMessage getMessage(LinkedTreeMap<String, Object> msg) {
		ChCareOfflineMessage result = new ChCareOfflineMessage();
		double type = Double.parseDouble(msg.get("Type").toString());

		if (type >= 100 && type < 200) {
			result.setRouter(RouterType.FAMILY_MEMBER_SERVICE_ROUTER);
			result.setMessage(transToFamilyMemberContent(msg, (int) type));// 转化为JSON
		} else if (type >= 200 && type < 300) {
			result.setRouter(RouterType.FAMILY_PHOTOWALL_SERVICE_ROUTER);
		} else if (type >= 300 && type < 400) {
			result.setRouter(RouterType.FAMILY_MESSAGEBOARD_SERVICE_ROUTER);
		} else if (type >= 400 && type < 500) {
			result.setRouter(RouterType.FAMILY_HEALTHMANAGER_SERVICE_ROUTER);
		} else {
			result.setRouter(RouterType.FAMILY_SYSTEM_SERVICE_ROUTER);
		}

		return result;
	}

	public static void main(String[] args) throws HttpRequestException {
		ChCareWebApiOfflineMessageApi offlineMessageHandler = new ChCareWebApiOfflineMessageApi();
		//
		String str = "{\"Data\":[{\"ID\":10,\"Type\":101,\"Val\":{\"userid\":007,\"familyID\":123456,\"userName\":\"hadoop\"},\"SendTime\":\"2014-08-29 14:45:22.017\",\"SUID\":10001,\"RID\":100001,\"RType\":2}],\"State\":0,\"Desc\":\"操作成功\"}";
		// // ResponseBean responseBean=offlineMessageHandler.transToBean(str);
		str = "{\"Data\":[{\"ID\":1467,\"Type\":100,\"Val\":\"{\"familyID\":100344,\"userName\":\"13402858512\"}\",\"SendTime\":\"2014-09-15 10:12:17.017\",\"SUID\":10021,\"RID\":10026,\"RType\":1},{\"ID\":1468,\"Type\":100,\"Val\":\"{\"familyID\":100344,\"userName\":\"13402858512\"}\",\"SendTime\":\"2014-09-15 10:31:00.071\",\"SUID\":10021,\"RID\":10026,\"RType\":1},{\"ID\":1469,\"Type\":100,\"Val\":\"{\"familyID\":100344,\"userName\":\"13402858512\"}\",\"SendTime\":\"2014-09-15 10:35:03.017\",\"SUID\":10021,\"RID\":10026,\"RType\":1}],\"State\":0,\"Desc\":\"操作成功\"}";
		// str="{\"Data\":[{\"ID\":1467,\"Type\":100,\"Val\":\"{\"familyID\":100344,\"userName\":\"13402858512\"}\",\"SendTime\":\"2014-09-15 10:12:17\",\"SUID\":10021,\"RID\":10026,\"RType\":1},{\"ID\":1468,\"Type\":100,\"Val\":\"{\"familyID\":100344,\"userName\":\"13402858512\"}\",\"SendTime\":\"2014-09-15 10:31:00\",\"SUID\":10021,\"RID\":10026,\"RType\":1},{\"ID\":1469,\"Type\":100,\"Val\":\"{\"familyID\":100344,\"userName\":\"13402858512\"}\",\"SendTime\":\"2014-09-15 10:35:03\",\"SUID\":10021,\"RID\":10026,\"RType\":1}],\"State\":0,\"Desc\":\"操作成功\"}";
		// //str="{\"Data\":[{\"ID\":13,\"Type\\":100,\"Val\":\"{\"userID\":0,\"familyID\":100001,\"userName\":\"12345678912\",\"nickName\":\"魏叔老婆\",\"familyName\":null,\"remark\":\"who care\"}","SendTime":"2014-09-09 17:57:21","SUID":10002,"RID":10001,"RType":1}],"State":1,"Desc":"操作成功"}";
		ResponseBean baseBean = offlineMessageHandler.transToBean(str);
		ResponseBean secondBean = offlineMessageHandler
				.transToLocalOfflineMessage(baseBean);

		List<ChCareOfflineMessage> chcareBeans = (List<ChCareOfflineMessage>) secondBean
				.getData();
		ChCareOfflineMessage chcareBean = chcareBeans.get(0);
		System.out.println(chcareBean.getRouter());
		OfflineMessageBean offlineBean = chcareBean.getMessage();
		OfflineMessageContent content = (OfflineMessageContent) offlineBean
				.getVal();
		System.out.println(offlineBean.getID()
				+ offlineBean.getVal().getClass().getName() + ","
				+ content.getFamilyID());

		// responseBean=offlineMessageHandler.transToLocalOfflineMessage(responseBean);
		// List<OfflineMessageBean>
		// offlineBean=(List<OfflineMessageBean>)responseBean.getData();
		// OfflineMessageContent
		// content=(OfflineMessageContent)offlineBean.get(0).getVal();
		// System.out.println(content.getUserid());
	}
}
