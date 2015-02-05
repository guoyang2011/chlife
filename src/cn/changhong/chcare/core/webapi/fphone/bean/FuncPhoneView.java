/**     
 * @Title: FuncPhoneView.java  
 * @Package cn.changhong.chcare.core.webapi.fphone.bean  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-9-17 上午10:25:28  
 * @version V1.0     
 */
package cn.changhong.chcare.core.webapi.fphone.bean;

import java.io.Serializable;

/**
 * @ClassName: FuncPhoneView
 * @Description: TODO
 * @author guoyang2011@gmail.com
 * @date 2014-9-12 上午9:49:00
 * 
 */
public class FuncPhoneView implements Serializable{
	private long Id;
	private String Imei;
	private String NickName;
	private short Type;
	private String Img;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getImei() {
		return Imei;
	}

	public void setImei(String imei) {
		Imei = imei;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public short getType() {
		return Type;
	}

	public void setType(short type) {
		Type = type;
	}

	public String getImg() {
		return Img;
	}

	public void setImg(String img) {
		Img = img;
	}

}
