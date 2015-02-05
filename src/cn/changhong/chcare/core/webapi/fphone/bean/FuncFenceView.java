/**     
 * @Title: FuncFenceView.java  
 * @Package cn.changhong.chcare.core.webapi.fphone.bean  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-9-17 上午11:30:38  
 * @version V1.0     
 */
package cn.changhong.chcare.core.webapi.fphone.bean;

import java.io.Serializable;

/**
 * @ClassName: FuncFenceView
 * @Description: TODO
 * @author guoyang2011@gmail.com
 * @date 2014-9-17 上午11:30:38
 * 
 */
public class FuncFenceView implements Serializable{
	private long DevId;
	private int Seq;
	private int Type;
	private String Name;
	private double Radius;
	private String Lng;
	private String Lat;

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

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public double getRadius() {
		return Radius;
	}

	public void setRadius(double radius) {
		Radius = radius;
	}

	public String getLng() {
		return Lng;
	}

	public void setLng(String lng) {
		Lng = lng;
	}

	public String getLat() {
		return Lat;
	}

	public void setLat(String lat) {
		Lat = lat;
	}

}
