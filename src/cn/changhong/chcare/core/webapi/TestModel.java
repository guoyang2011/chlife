package cn.changhong.chcare.core.webapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.changhong.chcare.core.webapi.bean.AppVersion;
import cn.changhong.chcare.core.webapi.bean.CHCareFileInStream;
import cn.changhong.chcare.core.webapi.bean.Family;
import cn.changhong.chcare.core.webapi.bean.FamilyMemberInfo;
import cn.changhong.chcare.core.webapi.bean.FeedbackBean;
import cn.changhong.chcare.core.webapi.bean.Location;
import cn.changhong.chcare.core.webapi.bean.OfflineMessageBean;
import cn.changhong.chcare.core.webapi.bean.ResponseBean;
import cn.changhong.chcare.core.webapi.bean.ThirdShare;
import cn.changhong.chcare.core.webapi.bean.User;
import cn.changhong.chcare.core.webapi.fphone.bean.FuncFamNumView;
import cn.changhong.chcare.core.webapi.fphone.bean.FuncFenceView;
import cn.changhong.chcare.core.webapi.fphone.bean.FuncPhoneView;
import cn.changhong.chcare.core.webapi.photowalll.bean.PhotoView;
import cn.changhong.chcare.core.webapi.server.AAppManagerService;
import cn.changhong.chcare.core.webapi.server.AFPhoneWebApiService;
import cn.changhong.chcare.core.webapi.server.AFamilyService;
import cn.changhong.chcare.core.webapi.server.APhotowallWebApiService;
import cn.changhong.chcare.core.webapi.server.CHCareWebApiProvider;
import cn.changhong.chcare.core.webapi.server.CHCareWebApiProvider.WebApiServerType;
import cn.changhong.chcare.core.webapi.server.IAccountService;
import cn.changhong.chcare.core.webapi.server.ILocationService;
import cn.changhong.chcare.core.webapi.server.IOfflineMessageService;
import cn.changhong.chcare.core.webapi.util.HttpRequestException;
import cn.changhong.chcare.core.webapi.util.TokenManager;
import cn.changhong.chcare.message.ChCareOfflineMessage;
import cn.changhong.chcare.message.RouterType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestModel {
	private static SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
	public static class UserInfo {
		private String username;
		private String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	}

	public static class UserDetail {
		private String context;

		public String getContext() {
			return context;
		}

		public void setContext(String context) {
			this.context = context;
		}

	}

	static String filepath = "D:\\app.jpg";

	public static void main(String[] args) throws HttpRequestException, MalformedURLException {
		S1 s1=new S1();
//		s1.setUsername("11");
		S2 s2=new S2();	
		// UserInfo<UserDetail> userInfo=new UserInfo();
		// userInfo.setUsername("username");
		// userInfo.setPassword("password");
		// UserDetail detail=new UserDetail();
		// detail.setContext("test");
		// userInfo.setDetail(detail);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
				.create();
		// String userGsonStr=gson.toJson(userInfo);
		// System.out.println(userGsonStr);

		// String
		// str="{\"Data\":[{\"ID\":1467,\"Type\":100,\"Val\":\"{\"familyID\":100344,\"userName\":\"13402858512\"}\",\"SendTime\":\"2014-09-15 10:12:17\",\"SUID\":10021,\"RID\":10026,\"RType\":1},{\"ID\":1468,\"Type\":100,\"Val\":\"{\"familyID\":100344,\"userName\":\"13402858512\"}\",\"SendTime\":\"2014-09-15 10:31:00\",\"SUID\":10021,\"RID\":10026,\"RType\":1},{\"ID\":1469,\"Type\":100,\"Val\":\"{\"familyID\":100344,\"userName\":\"13402858512\"}\",\"SendTime\":\"2014-09-15 10:35:03\",\"SUID\":10021,\"RID\":10026,\"RType\":1}],\"State\":0,\"Desc\":\"操作成功\"}";
		// Type type=new
		// TypeToken<ResponseBean<List<OfflineMessageBean>>>(){}.getType();
		// ResponseBean<List<OfflineMessageBean>> bean=gson.fromJson(str, type);
		// System.out.println(gson.toJson(bean.getData().get(0).getVal()));

		// UserInfo<UserDetail> user=gson.fromJson(userGsonStr, type);
		// System.out.println(user.getDetail().getContext());
		// System.out.println(System.currentTimeMillis());
		// IAccountService
		// accountService=(IAccountService)CHCareWebApiProvider.Self.defaultInstance().getDefaultWebApiService(WebApiServerType.CHCARE_ACCOUNT_SERVER);
		// System.out.println(accountService);
		// String
		// jsonStr="{\"Data\":{\"ID\":56185,\"UserID\":10007,\"Type\":0,\"Lng\":100.2,\"Lat\":100.2,\"Addr\":\"sichuan\",\"GPSTime\":\"2014-09-05T09:18:49\",\"CurTime\":\"2014-09-05T09:18:53\",\"IsVisible\":true},\"State\":0,\"Desc\":\"操作成功\"}";
		// ResponseBean<Location> bb=gson.fromJson(jsonStr, type);
		// System.out.println(bb.getData().getLng()+""+bb.getData().getCurTime());
		// // Location loc=new Location();
		// Gson gson = new
		// GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
		// String str="2014-09-04T15:21:45";
		// gson.fromJson(str, Date.class);
		// // Location locc=gson.fromJson(jsonStr, Location.class);
		// jsonStr=gson.toJson(loc);
		// System.out.println(jsonStr);
		// // Location lll=gson.fromJson(jsonStr, Location.class);
		// // System.out.println(gson.toJson(loc));
		// //// System.out.println(gsons);
		// Location locc=gson.fromJson(gsons, Location.class);

		// if(args == null || args.length<3){
		// System.out.println("please enter username and password!");
		// }
		// System.out.println(args[1]+","+args[2]);

		new AccountServiceTest().testAccountHandler("3", "3");
		// DateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		// try {
		// Date date=format.parse(data);
		// System.out.println(date);
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		//
		// // Gson gson= new
		// GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
		// //
		// // String date = "\"2013-02-10T13:45:30\"";
		// // Date test = gson.fromJson(date, Date.class);
		// // System.out.println("date:" + test);
		//
		// String json =
		// "{\"ID\":51484,\"UserID\":10007,\"Type\":0,\"Lng\":100.2,\"Lat\":100.2,\"Addr\":\"sichuan\",\"GPSTime\":\"2014-09-04T15:37:14\",\"CurTime\":\"2014-09-04T15:37:18\",\"IsVisible\":true}";
		// Gson gson= new
		// GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
		// Location locc=gson.fromJson(json, Location.class);
		// //
	}

	private static void accountServiceTest() throws HttpRequestException {
		IAccountService loginApi = new ChCareWebApiAccountnApi();
		String username = "18981955051";
		String password = "123456";
		ResponseBean loginBean = loginApi.login(username, password);
		print(loginBean);
		if (loginBean.getState() == 0) {
			TokenManager.setToken(loginBean.getData().toString());
			ResponseBean photoBean = loginApi.uploadUserPhoto(filepath);
			print(photoBean);
		}
	}

	private static class AccountServiceTest {
		IAccountService accountService = (IAccountService) CHCareWebApiProvider.Self
				.defaultInstance().getDefaultWebApiService(
						WebApiServerType.CHCARE_ACCOUNT_SERVER);
		AFamilyService familyService = (AFamilyService) CHCareWebApiProvider.Self
				.defaultInstance().getDefaultWebApiService(
						WebApiServerType.CHCARE_FAMILY_SERVER);
		private boolean isLogin = false;

		public boolean isLogin() {
			return isLogin;
		}

		public void setLogin(boolean isLogin) {
			this.isLogin = isLogin;
		}

		public void login(String username, String password)
				throws HttpRequestException {
			this.accountService.login(username, password);
//					,new AsyncResponseCompletedHandler<String>(){
//
//				@Override
//				public String onCompleted(ResponseBean<?> response) {
//					print("login", response);
//
//					if (response.getState() == 0) {
//						TokenManager.setToken(response.getData().toString());
//						isLogin = true;
//
//						// ResponseBean<List<Family>>
//						// familyBean=this.familyService.getAllFamilyInfo();
//						// this.accountService.updateFamilyWealth(familyBean.getData().get(0).getID(),
//						// 0, "");
//					}
//					return null;
//				}
//
//				@Override
//				public void onThrowable(HttpRequestException t) {
//					// TODO Auto-generated method stub  
//					
//				}});
			
		}
		public void isSign(int familyID){
			try {
				this.accountService.isSignIn(familyID);
			} catch (HttpRequestException e) {
				// TODO Auto-generated catch block  
				e.printStackTrace();
			}
		}
	

		public ResponseBean getUserInfo(String username)
				throws Exception {
			ResponseBean<User> bean = (ResponseBean<User>) this.accountService
					.searchUserInfo(username);
			System.out.println(bean.getData().getBirthDay());
			this.print("getUserInfo", bean);
			return bean;
		}

		public void updateUserInfo() throws Exception {
			User user = new User();
			user.setHeight(999.9);
			user.setWeight(999.9);
			this.print("updateUserInfo",
					this.accountService.updateUserInfo(user));
		}

		public void uploadPhoto(String path) throws Exception {
			System.out.println("上传头像1");
			this.print("uploadPhoto", this.accountService.uploadUserPhoto(path));
//			Future<ResponseBean> bean=this.accountService.uploadUserPhoto(path, new AsyncResponseCompletedHandler<String>() {
//
//				@Override
//				public String onCompleted(ResponseBean<?> response) {
//					System.out.println(response.getState()+","+response.getDesc());
//					return null;
//				}
//
//				@Override
//				public void onThrowable(HttpRequestException t) {
//					// TODO Auto-generated method stub  
//					
//				}
//			});
			System.out.println("上传头像2");
			File file=new File(path);
			FileInputStream instearm=new FileInputStream(file);
			
			ResponseBean bean=this.accountService.uploadUserPhoto(instearm, file.getName());//, new AsyncResponseCompletedHandler<String>() {
//
//				@Override
//				public String onCompleted(ResponseBean<?> response) {
//					System.out.println(response.getState()+","+response.getDesc()); 
//					return null;
//				}
//				@Override
//				public void onThrowable(HttpRequestException t) {
//					// TODO Auto-generated method stub  
//					
//				}
//			});
		}


		public void logout() throws Exception {
			this.print("logout", this.accountService.logout());
		}

		private static void print(String name, ResponseBean bean) throws Exception {
			if (bean == null){
				throw new Exception("");
			}
			System.out.println("serverMethod:" + name + ",data:"
					+ bean.getData() + ",state:" + bean.getState() + ",desc:"
					+ bean.getDesc());
		}
		public void download(String url) throws HttpRequestException{
			System.out.println("下载头像");
			ResponseBean<User> user=(ResponseBean<User>)this.accountService.searchUserInfo("");
			final User u=user.getData();
			System.out.println(u.getPhotoUrl()+">>>>>>>>>>>>>>>>>>>>>");
			ResponseBean<CHCareFileInStream> ss=(ResponseBean<CHCareFileInStream>)this.accountService.getUserIcon(u.getPhotoUrl());//, new AsyncResponseCompletedHandler<String>(){
			System.out.println(ss.getData().getFileType());
//
//				@Override
//				public String onCompleted(ResponseBean<?> response) {
//					System.out.println(response.getState()+","+response.getDesc()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"); 
//					return null;
//				}
//
//				@Override
//				public void onThrowable(HttpRequestException t) {
//					System.out.println(t.getMessage()+","+t.getErrorType()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");   
//					
//				}
//				
//			});
		}
		public void AddFamilyBalance(int id) throws HttpRequestException{
			System.out.println("签到领取金币");
			this.accountService.updateFamilyWealth(id, 0, "签到");
		}
		public void changePassword() {
			// this.accountService.re
		}

		private void searchUsers() {
			try {
				ResponseBean<List<User>> res = this.accountService.searchUsers(
						"1", -1, -20);
				System.out.println(">>>>>>>>>>>>>>>>>>>>" + res.getState()
						+ "," + res.getData().size());
			} catch (HttpRequestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void testAccountHandler(String username, String password) throws MalformedURLException {
			// String username = "1";//"12345678912";//18681746415,123
			// String password = "1";//"123456";
			String filepath = "D:\\app.jpg";
			 username = "18981955051";
			 password = "123456";
			 final AAppManagerService appManager=(AAppManagerService)CHCareWebApiProvider.newInstance().getDefaultWebApiService(WebApiServerType.CHCARE_CHCAREAPPMANAGERAPI_SERVER);
			 
			 final ILocationService locationService=(ILocationService)CHCareWebApiProvider.newInstance().getDefaultWebApiService(WebApiServerType.CHCARE_LOCATION_SERVER);
			 
//			 appManager.setServerUrl(new URL("http://10.9.52.75:4683/api/"));
			final IOfflineMessageService offlineMessageService = (IOfflineMessageService) CHCareWebApiProvider.Self
					.defaultInstance().getDefaultWebApiService(
							WebApiServerType.CHCARE_OFFLINEMESSAGE_SERVER);
			
			// AFamilyService
			// familyService=(AFamilyService)CHCareWebApiProvider.Self.defaultInstance().getDefaultWebApiService(WebApiServerType.CHCARE_FAMILY_SERVER);

			LocationTest lt = new LocationTest();
			FamilyTest ft = new FamilyTest();
			PhotoWallTest pwt=new PhotoWallTest();
			
			AFPhoneWebApiService fphoneWebService=(AFPhoneWebApiService)CHCareWebApiProvider.newInstance().getDefaultWebApiService(WebApiServerType.CHCARE_FUNCPHONE_SERVER);
			
//		 while(true)
			{
				try {
					FeedbackBean feedback=new FeedbackBean();
					feedback.setAPPID(1);
					feedback.setAppOS(1);
					feedback.setDevVer(1);
//					feedback.setOSVer("123");
					feedback.setUserID(1212);
					feedback.setUserSug("nothing");
					this.login(username, password);
					
					ResponseBean appBean=appManager.getAppLastVersionInfo(1, 1, 1);
//					if(appBean.getState()>=0){
//						AppVersion versionInfo=(AppVersion) appBean.getData();
//						System.out.println(versionInfo.getAppName());
//					}
//					appManager.putFeedback(feedback);
//					System.out.println(">>>>>>>>>>>>>>>>>>>>>>\n\n\n");
//					
//					ResponseBean<List<ChCareOfflineMessage<OfflineMessageBean>>> bean=(ResponseBean<List<ChCareOfflineMessage<OfflineMessageBean>>>)offlineMessageService.getUserOfflineMessage(0, 0,0, 0);
//					if(bean.getState() == 0){
//						List<ChCareOfflineMessage<OfflineMessageBean>> list=bean.getData();
//						for(ChCareOfflineMessage<OfflineMessageBean> m:list){
//							if(m.getRouter() == RouterType.FAMILY_PHOTOWALL_SERVICE_ROUTER){
//								OfflineMessageBean<?> msg=m.getMessage();
//								if(msg.getType() == 201){
//									System.out.println(msg.getVal()+"》》》？？？？");
//								}
//							}
//						}
//					}
//					System.out.println(">>>>>>>>>>>>>>>>>>>>>>\n\n\n");
//					
					
//					 ft.init("2", 10015);
					 
					 ResponseBean location=locationService.searchUserHistoryPosition("18981955051", format.parse("20141107"), format.parse("20141108"), "0");
					 
//					 long devid=2751;
//					 System.out.println("\n\n\n");
//					 ResponseBean<Integer> res=(ResponseBean<Integer>) fphoneWebService.getBindFPhonePositionMode(devid);
//					 
//					 ResponseBeanWithRange<List<FuncFamNumView>> beans=(ResponseBeanWithRange<List<FuncFamNumView>>) fphoneWebService.getBindFPhoneAllPhoneNum(devid);
//					 System.out.println(beans.getData().get(0).getPhone());
//					 
//					 System.out.println(res.getData()+">>>>>>>>>>>>>>>>>>>");
//					this.AddFamilyBalance(ft.family.getID());
//					System.out.println("签到 领取金币");
//					this.isSign(ft.family.getID());
////					Thread.sleep(1000);
//					this.uploadPhoto("D:\\app.jpg");
////					System.out.println("\n\n\n");
//					this.download("");
//					System.out.println("\n\n\n");
//					// this.searchUsers();
//					new FuncPhoneTest().init();
					// this.updateUserInfo();
					// ResponseBean
					// message=offlineMessageService.getUserOfflineMessage(0, 0,
					// 100, 0);
					// List<ChCareOfflineMessage>
					// chcareBeans=(List<ChCareOfflineMessage>)message.getData();
					// System.out.println(chcareBeans.size()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					// ChCareOfflineMessage chcareBean=chcareBeans.get(0);
					// OfflineMessageBean offbean=chcareBean.getMessage();
					// OfflineMessageContent
					// content=(OfflineMessageContent)offbean.getVal();
					// System.out.println(offbean.getID()+","+offbean.getRType()+","+offbean.getSUID()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+content.getFamilyID()+content.getUserName());
					// ResponseBean<User> userBean=this.getUserInfo("1");
					// Thread.sleep(5000);
					// // lt.test();
					
					
//					 pwt.init(100009);
					// System.out.println("\n\n\n");
					this.logout();

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}

			// System.out.println(userBean.getData().getID()+">>>>>");
			// ResponseBean<CHCareFileInStream>
			// fileInStream=this.accountService.getUserIcon(userBean.getData().getPhotoUrl());
			// CHCareFileInStream instream=fileInStream.getData();
			// try {
			// // FileOutputStream stream=new FileOutputStream(new
			// File("C:\\Users\\Administrator\\Desktop\\长虹关爱\\11111."+instream.getFileType()));
			// // stream.write(instream.getInputStream().toByteArray());
			// new FamilyTest().init("2", 10015);
			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

			// this.getUserInfo(username);
			// LocationTest lt=new LocationTest();
			// lt.test();
			// System.out.println(familyService.createFamily("魏书家庭",
			// "魏书的地盘魏书做主！").getState());
			// ResponseBean<List<Family>>
			// familyBean=familyService.getAllFamilyInfo();
			// System.out.println(familyBean.getData().get(0).getName());
			// ResponseBean<List<OfflineMessageBean>>
			// bean=offlineMessageService.getUserOfflineMessage(0, 100, 10, 0);
			// System.out.println(bean.getData().size()+">>>>>>>>>>>>>>>");
			// new Thread(new Runnable() {
			// @Override
			// public void run() {
			// double index=0;
			// Location loc=new Location();
			// LocationTest lt=new LocationTest();
			// // try {
			// // ResponseBean
			// bean=offlineMessageService.getUserOfflineMessage(0, 100, 10, -1);
			// // if(bean==null){System.out.println("null >>>>>");}
			// // else AccountServiceTest.print("offlinemessage",bean);
			// // } catch (HttpRequestException e1) {
			// // // TODO Auto-generated catch block
			// // e1.printStackTrace();
			// // }
			// // while(true){
			// // loc.setLng(index++);
			// // try {
			// // lt.currentLocation(loc);
			// //
			// // } catch (HttpRequestException e) {
			// // // TODO Auto-generated catch block
			// // e.printStackTrace();
			// // }
			// // try {
			// // Thread.sleep(100);
			// // } catch (InterruptedException e) {
			// // // TODO Auto-generated catch block
			// // e.printStackTrace();
			// // }
			// //
			// // }
			// }
			// }).start();
			// catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

			// this.logout();
			// } catch (HttpRequestException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}

		public static class FamilyTest {
			private AFamilyService familyService = (AFamilyService) CHCareWebApiProvider.Self
					.defaultInstance().getDefaultWebApiService(
							WebApiServerType.CHCARE_FAMILY_SERVER);
			public Family family;

			public FamilyTest testCreateFamily() {
				ResponseBean<Family> bean;
				try {
					bean = this.familyService.createFamily("小小长毛象之家", "长毛象睡觉了");
					this.family = bean.getData();
					if (bean.getState() == 0)
						System.out.println("1>>>>>>>>>>testCreateFamily:"
								+ bean.getData().getSign() + ","
								+ bean.getState());
				} catch (HttpRequestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return this;
			}

			public FamilyTest testIniteJoinFamily(String username) {
				ResponseBean bean;
				try {
					bean = this.familyService.inviteJoinFamily(family.getID(),
							username,"");
					System.out.println("2>>>>>>>>>>testIniteJoinFamily:"
							+ bean.getState());
				} catch (HttpRequestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return this;
			}

			public FamilyTest testSearchFamilys() {
				ResponseBean<List<Family>> bean;
				try {
					bean = this.familyService.searchFamilys("1", 0, 50);
					List<Family> familys = bean.getData();
					System.out.println("3>>>>>>>>>>testSearchFamilys:"
							+ bean.getState() + "," + bean.getData().size()
							+ ",");
				} catch (HttpRequestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return this;
			}

			public FamilyTest testApplyJoinFamily(String username) {
				ResponseBean bean;
				try {
					bean = this.familyService.applyJoinFamily(
							this.family.getID(), username,"");
					System.out.println("4>>>>>>>>>>testApplyJoinFamily:"
							+ bean.getState());
				} catch (HttpRequestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return this;
			}

			public FamilyTest testJoinFamilyAllowByMaster(int userID) {
				ResponseBean bean;
				try {
					bean = this.familyService.joinFamilyAllowByMaster(
							this.family.getID(), userID, "小小象", true, null);
					System.out
							.println("5>>>>>>>>>>testJoinFamilyAllowByMaster:"
									+ bean.getState());
				} catch (HttpRequestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return this;
			}

			public FamilyTest testGetAllFamilyInfo() {
				ResponseBean<List<Family>> bean;
				try {
					bean = this.familyService.getAllFamilyInfo();
					if (bean.getState() == 0)
						System.out.println("6>>>>>>>>>>testGetAllFamilyInfo:"
								+ bean.getState() + "," + bean.getData().size()
								+ ">>>>>>>>>>>>>>>>>>>"
								+ bean.getData().get(0).getBalance()+","+bean.getData().get(0).getPhotoUrl());
					this.family=bean.getData().get(0);
				} catch (HttpRequestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return this;
			}
			public FamilyTest testUploadFamilyIcon() throws HttpRequestException, FileNotFoundException{
				ResponseBean bean=null;
				String filepath = "D:\\app.jpg";
				File file=new File(filepath);
				System.out.println(filepath);
				FileInputStream instream=new FileInputStream(file);
				try{
					if(this.familyService == null) System.out.println("FamilyService null");
				bean=this.familyService.uploadFamilyIcon(this.family.getID(), instream, file.getName());
				if(bean == null) System.out.println("Bean >>>>>>>");
				else System.out.println("BBBBdsadsadsa>>>>>>>>>>>>>>>>>>>>>>");
				}catch(Exception e){
					e.getLocalizedMessage();
				}
				System.out.println(new Gson().toJson(bean)+"?>>>>>>上传图片大小>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+file.length()+",>>>>>>>");
				return this;
			}

			public FamilyTest testChangeUserFamilyMemberNickName(int userID) {
				ResponseBean bean;
				try {
					bean = this.familyService.changeUserFamilyMemberNickName(
							this.family.getID(), userID, "NEwNIckName");
					System.out
							.println("7>>>>>>>>>>testChangeUserFamilyMemberNickName:"
									+ bean.getState());
				} catch (HttpRequestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return this;
			}

			public FamilyTest testGetFamilyMembers() {
				ResponseBean<List<FamilyMemberInfo>> bean = null;
				try {
					System.out.println(this.family.getID()+"<><><><><><><>><><><><><><><><>");
					bean = this.familyService.getFamilyMembers(this.family
							.getID());
					
//					System.out.println("8>>>>>>>>>>testGetFamilyMembers:"
//							+ bean.getState() + "," + bean.getData().size()+",,,,,,"+bean.getData().get(0).getUserExp()+";"+bean.getData().get(0).getUserInfo().getID());
//					
				} catch (HttpRequestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return this;
			}

			public FamilyTest testUpdateFamilyInfo(int userID) {
				ResponseBean bean;
				try {
					bean = this.familyService.updateFamilyInfo(
							this.family.getID(), userID, "长虹关爱之家", "长虹关爱无处不在");
					System.out.println("9>>>>>>>>>>testRemoveUserByMaster:"
							+ bean.getState());
				} catch (HttpRequestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return this;
			}

			public FamilyTest testRemoveUserByMaster(int userID) {
				ResponseBean bean;
				try {
					bean = this.familyService.removeUserByMaster(userID,
							this.family.getID());
					System.out.println("10>>>>>>>>>>testRemoveUserByMaster:"
							+ bean.getState());
				} catch (HttpRequestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return this;
			}

			private FamilyTest space() {
				System.out.println("\n");
				return this;
			}
			public FamilyTest tesstSearchFamilys() throws HttpRequestException{
				System.out.println("搜索家庭");
				this.familyService.searchFamilys("1", 0, 20);
				return this;
			}
			public void init(String username, int userID)
					throws HttpRequestException, FileNotFoundException {
//				this.testCreateFamily().space().testGetAllFamilyInfo().space();
//				// .testIniteJoinFamily(username).space()
//						.testSearchFamilys().space();
//				// .testApplyJoinFamily(username).space()
//				// .testJoinFamilyAllowByMaster(userID).space()
//				// .testChangeUserFamilyMemberNickName(userID).space()
//				testGetFamilyMembers();
//				// .testUpdateFamilyInfo(userID).space()
//				// .testRemoveUserByMaster(userID);
//				System.out.println("获取家庭信息");
				this.testGetAllFamilyInfo();
				this.testGetFamilyMembers();
//				System.out.println("搜索家庭");
//				this.testSearchFamilys();
				
				System.out.println("上传家庭图片");
				this.testUploadFamilyIcon();
				System.out.println("下载家庭图片");
				this.testGetFamilyPhotoICon();
				

			}
			private void testGetFamilyPhotoICon(){
				try {
					ResponseBean bean=this.familyService.getFamilyIcon(this.family.getID());
					if(bean.getState() == 0){
						CHCareFileInStream stream=(CHCareFileInStream) bean.getData();
						System.out.println("下载图片大小："+stream.getInputStream().size());
					}
					System.out.println(bean.getState()+","+bean.getDesc());
				} catch (HttpRequestException e) {
					// TODO Auto-generated catch block  
					e.printStackTrace();
				}
			}
		}
		public static class PhotoWallTest{
			APhotowallWebApiService photowallService=(APhotowallWebApiService)CHCareWebApiProvider.newInstance().getDefaultWebApiService(WebApiServerType.CHCARE_PHOTOWALL_SERVER);
			public void init(int familyId) throws HttpRequestException, FileNotFoundException{
				String filepath = "D:\\app.jpg";
				File file=new File(filepath);
				FileInputStream instream=new FileInputStream(file);
				System.out.println("\n\n\n>>???????????????????");
				
				ResponseBean<List<PhotoView>> photos=(ResponseBean<List<PhotoView>>) this.photowallService.getFamilyPhotosInfo(familyId);
				System.out.println("\n\n\n");
				System.out.println(this.photowallService.uploadFamilyPhoto(familyId, "no desc", instream, file.getName()).getState()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				
//				this.photowallService.downloadFile(url)
			}
		}
		public static class FuncPhoneTest {
			AFPhoneWebApiService fphoneService = (AFPhoneWebApiService) CHCareWebApiProvider
					.newInstance().getDefaultWebApiService(
							WebApiServerType.CHCARE_FUNCPHONE_SERVER);

			public void init() throws HttpRequestException {
				ResponseBean<List<FuncPhoneView>> fps = (ResponseBean<List<FuncPhoneView>>) this.fphoneService
						.getAllBindFPhones();
				Date date = new Date();
				if (fps.getData().size() > 0) {
					long devId = fps.getData().get(0).getId();
					this.fphoneService.getAllBindFPhonePosition(0);
					this.fphoneService.getBindFPhonePosition(devId, 0);
					this.fphoneService.getBindFPhoneHistoryPositions(devId,
							new Date(date.getTime() - 24 * 60 * 60 * 1000),
							date, 0, 0);
					this.fphoneService.getBindFPhoneFence(devId);
					FuncFenceView func = new FuncFenceView();
					func.setDevId(devId);
					func.setLat("1000");
					func.setLng("11111");
					func.setType(0);
					func.setSeq(1);
					func.setName("长虹关爱");

					this.fphoneService.updateBindFPhoneFence(func);

					this.fphoneService.deleteBindFPhoneFence(devId, 1);
					this.fphoneService.getBindFPhoneAllPhoneNum(devId);
					FuncFamNumView funcfam = new FuncFamNumView();
					funcfam.setDevId(devId);
					funcfam.setSeq(2);
					funcfam.setName("长虹关爱");
					funcfam.setPhone("phone number");
					this.fphoneService.updateBindFPhone(funcfam);
					this.fphoneService.deleteBindFPhone(devId, 1);
					this.fphoneService.getBindFPhonePositionMode(devId);
					this.fphoneService.updateBindFPhonePositionMode(devId, 0);

					this.fphoneService.updateBindFPhoneNickInfo(devId,
							"测试测试测试测试");

				}
			}
		}

		public static class LocationTest {
			ILocationService locationServer = new ChCareWebApiLocationApi();

			public void currentLocation(Location loc)
					throws Exception {

				AccountServiceTest.print("CurrentLocation",
						this.locationServer.updateUserCurrentLocation(loc));
			}

			public void uploadSHareMsg() throws Exception {
				ThirdShare share = new ThirdShare();
				AccountServiceTest.print("uploadSHareMsg", this.locationServer
						.uploadShareMsgToThirdPlatformLog(share));
			}

			public void userHistoryPosition(String username, Date st, Date et)
					throws Exception {
				ResponseBean<List<Location>> bean = this.locationServer
						.searchUserHistoryPosition(username, st, et, "0");
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
						+ bean.getData().size());
				AccountServiceTest.print("userHistoryPosition", bean);
			}

			public void userLastLocation(List<String> users)
					throws Exception {//
				ResponseBean<Location> bean = this.locationServer
						.searchUsersLastLocation(users.get(0));
				System.out.println("\n\n\n\n\n\n\n\n\n\n" + bean.getDesc()
						+ bean.getState() + ">>>>>>>>>>>>>>>>>>>>>>>>>"
						+ bean.getData().getCurTime());

				// System.out.println(bean.getData().getCurTime());
				AccountServiceTest.print("userLastLocation", bean);

				// System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n"+bean.getData().getClass().getName());
				// // LinkedTreeMap<String, Object> map=(LinkedTreeMap<String,
				// Object>)bean.getData();
				// // for(Iterator<Entry<String, Object>> it=
				// map.entrySet().iterator();it.hasNext();){
				// // Entry<String, Object> entry=it.next();
				// //
				// System.out.println(entry.getKey()+"-"+entry.getValue().getClass().getName());
				// // }
				// Location loc=gson.fromJson(gson.toJson(bean.getData()),
				// Location.class);
				// System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n????????????????????????"+gson.toJson(bean.getData()));
			}

			public void test() throws Exception {
				System.out.println("\n\n\n");
				this.uploadSHareMsg();
				this.currentLocation(new Location());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dt = null;
				try {
					dt = sdf.parse("2014-8-1");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.userHistoryPosition("18681746415", dt,
						new Date(System.currentTimeMillis()));
				List<String> users = new ArrayList<String>();
				users.add("18681746415");
				this.userLastLocation(users);
			}
		}

	}

	private static void uploadFile(String path) {
		if (path != null) {
			File file = new File(path);
		}
	}

	private static void print(ResponseBean bean) {
		System.out.println(bean.getData() + "," + bean.getState() + ","
				+ bean.getDesc());
	}
}
