/**     
 * @Title: FamilyMemberInfo.java  
 * @Package cn.changhong.chcare.core.webapi.bean  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-9-19 下午1:56:23  
 * @version V1.0     
*/  
package cn.changhong.chcare.core.webapi.bean;  

import java.io.Serializable;
  
/**  
 * @ClassName: FamilyMemberInfo  
 * @Description: TODO  
 * @author guoyang2011@gmail.com  
 * @date 2014-9-19 下午1:56:23  
 *     
 */
public class FamilyMemberInfo implements Serializable{
	/**  
	 * @Fields serialVersionUID : TODO  
	*/
	private static final long serialVersionUID = 1L;
	private String MemberName;
	private int MemberState;
	private double UserExp;
	private User UserInfo;
	public String getMemberName() {
		return MemberName;
	}
	public void setMemberName(String memberName) {
		MemberName = memberName;
	}
	public int getMemberState() {
		return MemberState;
	}
	public void setMemberState(int memberState) {
		MemberState = memberState;
	}
	public double getUserExp() {
		return UserExp;
	}
	public void setUserExp(double userExp) {
		UserExp = userExp;
	}
	public User getUserInfo() {
		return UserInfo;
	}
	public void setUserInfo(User userInfo) {
		UserInfo = userInfo;
	}
}
