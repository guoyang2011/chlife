/**     
 * @Title: PhotoView.java  
 * @Package cn.changhong.chcare.core.webapi.fphone.bean  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-9-19 上午10:04:05  
 * @version V1.0     
*/  
package cn.changhong.chcare.core.webapi.photowalll.bean;  

import java.util.Date;

import cn.changhong.chcare.core.webapi.bean.CHCareFileInStream;
  
/**  
 * @ClassName: PhotoView  
 * @Description: TODO  
 * @author guoyang2011@gmail.com  
 * @date 2014-9-19 上午10:04:05  
 *     
 */
public class PhotoView {
	private int ID;
	private int UID;
	private String Url;
	private Date Time;
	private String Desc;
	private CHCareFileInStream photoStream;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getUID() {
		return UID;
	}
	public void setUID(int uID) {
		UID = uID;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public Date getTime() {
		return Time;
	}
	public void setTime(Date time) {
		Time = time;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}
	public CHCareFileInStream getPhotoStream() {
		return photoStream;
	}
	public void setPhotoStream(CHCareFileInStream photoStream) {
		this.photoStream = photoStream;
	}
}
