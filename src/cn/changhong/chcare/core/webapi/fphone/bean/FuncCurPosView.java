/**     
 * @Title: FuncCurPosView.java  
 * @Package cn.changhong.chcare.core.webapi.fphone.bean  
 * @Description: TODO  
 * @author guoyang2011@gmail.com    
 * @date 2014-9-17 上午10:28:20  
 * @version V1.0     
 */
package cn.changhong.chcare.core.webapi.fphone.bean;

import java.util.Date;

/**
 * @ClassName: FuncCurPosView
 * @Description: TODO
 * @author guoyang2011@gmail.com
 * @date 2014-9-17 上午10:28:20
 * 
 */
public class FuncCurPosView extends FuncBasicPosView {
	private Date GpsTime;
	private short Gsm;
	private short SatelliteNum;
	private short Power;
	private short Pointed;
	private byte Status;
	private String Alarm;

	public Date getGpsTime() {
		return GpsTime;
	}

	public void setGpsTime(Date gpsTime) {
		GpsTime = gpsTime;
	}

	public short getGsm() {
		return Gsm;
	}

	public void setGsm(short gsm) {
		Gsm = gsm;
	}

	public short getSatelliteNum() {
		return SatelliteNum;
	}

	public void setSatelliteNum(short satelliteNum) {
		SatelliteNum = satelliteNum;
	}

	public short getPower() {
		return Power;
	}

	public void setPower(short power) {
		Power = power;
	}

	public short getPointed() {
		return Pointed;
	}

	public void setPointed(short pointed) {
		Pointed = pointed;
	}

	public byte getStatus() {
		return Status;
	}

	public void setStatus(byte status) {
		Status = status;
	}

	public String getAlarm() {
		return Alarm;
	}

	public void setAlarm(String alarm) {
		Alarm = alarm;
	}
}
