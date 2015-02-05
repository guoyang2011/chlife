/**     
 * @Title: ChCareWepApiServiceType.java  
 * @Package cn.changhong.chcare.core.webapi.server  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-9-27 下午2:07:16  
 * @version V1.0     
*/  
package cn.changhong.chcare.core.webapi.server;  
  
/**  
 * @ClassName: ChCareWepApiServiceType  
 * @Description: TODO  
 * @author guoyang2011@gmail.com  
 * @date 2014-9-27 下午2:07:16  
 *     
 */
public enum ChCareWepApiServiceType {
	WebApi_Family_applyJoinFamily_Service(1),
	WebApi_Family_changeUserFamilyMemberNickName_Service(2),
	WebApi_Family_createFamily_Service(3),
	WebApi_Family_destroyFamily_Service(4),
	WebApi_Family_getAllFamilyInfo_Service(5),
	WebApi_Family_getFamilyIcon_Service(6),
	WebApi_Family_getFamilyMembers_Service(7),
	WebApi_Family_inviteJoinFamily_Service(8),
	WebApi_Family_joinFamilyAllowByMaster_Service(9),
	WebApi_Family_removeUserByMaster_Service(10),
	WebApi_Family_searchFamilys_Service(11),
	WebApi_Family_updateFamilyInfo_Service(12),
	WebApi_Family_uploadFamilyIcon_Service(13),
	
	WebApi_FPhone_deleteBindFPhone_Service(21),
	WebApi_FPhone_deleteBindFPhoneFence_Service(22),
	WebApi_FPhone_getAllBindFPhonePosition_Service(23),
	WebApi_FPhone_getAllBindFPhones_Service(24),
	WebApi_FPhone_getBindFPhoneAllPhoneNum_Service(24),
	WebApi_FPhone_getBindFPhoneFence_Service(25),
	WebApi_FPhone_getBindFPhoneHistoryPositions_Service(26),
	WebApi_FPhone_getBindFPhonePosition_Service(27),
	WebApi_FPhone_getBindFPhonePositionMode_Service(28),
	WebApi_FPhone_updateBindFPhone_Service(29),
	WebApi_FPhone_updateBindFPhoneFence_Service(30),
	WebApi_FPhone_updateBindFPhoneNickInfo_Service(31),
	WebApi_FPhone_updateBindFPhonePositionMode_Service(32),
	WebApi_FPhone_unBindPhone_Service(33),

	WebApi_PhotoWall_deleteFamilyPhoto_Service(41),
	WebApi_PhotoWall_getFamilyPhotosInfo_Service(42),
	WebApi_PhotoWall_uploadFamilyPhoto_Service(43),
	
	WebApi_Account_getUserIcon_Service(51),
	WebApi_Account_login_Service(52),
	WebApi_Account_logout_Service(53),
	WebApi_Account_registerStage1_Service(54),
	WebApi_Account_registerStage2_Service(55),
	WebApi_Account_registerStage3_Service(56),
	WebApi_Account_searchUserInfo_Service(57),
	WebApi_Account_searchUsers_Service(58),
	WebApi_Account_updateFamilyWealth_Service(59),
	WebApi_Account_updateNewPassword_Service(60),
	WebApi_Account_updateUserInfo_Service(61),
	WebApi_Account_uploadUserPhoto_Service(62),
	WebApi_Account_isSignIn_Service(63),
	WebApi_Account_setNewPassword_Service(64),
	WebApi_Account_getUserInfoById_Service(65),
	
	
	WebApi_Location_searchUserHistoryPosition_Service(71),
	WebApi_Location_searchUsersLastLocation_Service(72),
	WebApi_Location_updateUserCurrentLocation_Service(73),
	WebApi_Location_uploadShareMsgToThirdPlatformLog_Service(74),
	
	WebApi_OfflineMsg_getUserOfflineMessage_Service(81),
	WebApi_Health_addUserNewHealthInfo_Service(91),
	WebApi_Health_searchUserHealthInfo_Service(92),
	WebApi_Health_searchUserHealthInfos_Service(93), 
	
	WebApi_FamilyMsgBoard_getFamilyNote_Service(101), 
	WebApi_FamilyMsgBoard_uploadFamilyNote_Service(102),
	WebApi_FamilyMsgBoard_uploadFamilyNoteComment_Service(103), 
	WebApi_FamilyMsgBoard_deleteFamilyNote_Service(104),
	WebApi_FamilyMsgBoard_deleteFamilyNoteComment_Service(105),
	
	WebApi_AppManager_putFeedback_Service(201),
	WebApi_getAppLastVersionInfo_putFeedback_Service(202);
	 private int value=0;
	 private ChCareWepApiServiceType(int value){
		 this.value=value;
	 }
	 public int getValue(){
		 return value;
	 }
	
	
}
