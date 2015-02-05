/**     
 * @Title: ChcareBaseCacheManager.java  
 * @Package cn.changhong.chcare.core.webapi.cache  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-9-23 下午2:29:03  
 * @version V1.0     
*/  
package cn.changhong.chcare.core.webapi.cache;  

import java.util.Map;

import cn.changhong.chcare.core.webapi.bean.Family;
import cn.changhong.chcare.core.webapi.bean.FamilyMemberInfo;
import cn.changhong.chcare.core.webapi.bean.User;
import cn.changhong.chcare.core.webapi.server.IAccountService;
  
/**  
 * @ClassName: ChcareBaseCacheManager  
 * @Description: TODO  
 * @author guoyang2011@gmail.com  
 * @date 2014-9-23 下午2:29:03  
 *     
 */
public class ChcareBaseCacheManager {
	private static User baseUser=null;
	private static Family baseFamily=null;
	private static Map<Integer,FamilyMemberInfo> familyMembers;
//	private static IAccountService iAccountService=(IAccountService)Ch

}
