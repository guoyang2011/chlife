/**     
 * @Title: FuncFamNumView.java  
 * @Package cn.changhong.chcare.core.webapi.fphone.bean  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-9-17 上午10:32:49  
 * @version V1.0     
 */
package cn.changhong.chcare.core.webapi.fphone.bean;

import java.io.Serializable;

/**
 * @ClassName: FuncFamNumView
 * @Description: TODO
 * @author guoyang2011@gmail.com
 * @date 2014-9-17 上午10:32:49
 * 
 */
public class FuncFamNumView implements Serializable{
	private long DevId;
	private int Seq;
	private String Name;
	private String Phone;

	public long getDevId() {
		return DevId;
	}

	public void setDevId(long devId) {
		DevId = devId;
	}

	public int getSeq() {
		return Seq;
	}

	public void setSeq(int seq) {
		Seq = seq;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}
}
