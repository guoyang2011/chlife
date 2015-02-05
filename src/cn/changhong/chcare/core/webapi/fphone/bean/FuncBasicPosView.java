/**     
 * @Title: FuncBasicPosView.java  
 * @Package cn.changhong.chcare.core.webapi.fphone.bean  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-9-17 上午10:26:39  
 * @version V1.0     
 */
package cn.changhong.chcare.core.webapi.fphone.bean;

import java.io.Serializable;

/**
 * @ClassName: FuncBasicPosView
 * @Description: TODO
 * @author guoyang2011@gmail.com
 * @date 2014-9-17 上午10:26:39
 * 
 */
public class FuncBasicPosView implements Serializable{
	private long DevId;
	private double Lat;
	private double Lng;

	public long getDevId() {
		return DevId;
	}

	public void setDevId(long devId) {
		DevId = devId;
	}

	public double getLat() {
		return Lat;
	}

	public void setLat(double lat) {
		Lat = lat;
	}

	public double getLng() {
		return Lng;
	}

	public void setLng(double lng) {
		Lng = lng;
	}

}
