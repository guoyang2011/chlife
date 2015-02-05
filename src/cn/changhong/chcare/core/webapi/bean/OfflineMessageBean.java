package cn.changhong.chcare.core.webapi.bean;

import java.io.Serializable;
import java.util.Date;

public class OfflineMessageBean<T> implements Serializable{
	/**  
	 * @Fields serialVersionUID : TODO  
	*/
	private static final long serialVersionUID = 1L;
	private int ID;
	private int Type;
	private T Val;
	private Date SendTime;
	private int SUID;
	private int RID;
	private int RType;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public T getVal() {
		return Val;
	}

	public void setVal(T val) {
		Val = val;
	}

	public Date getSendTime() {
		return SendTime;
	}

	public void setSendTime(Date sendTime) {
		SendTime = sendTime;
	}

	public int getSUID() {
		return SUID;
	}

	public void setSUID(int sUID) {
		SUID = sUID;// 13730123457 123
	}

	public int getRID() {
		return RID;
	}

	public void setRID(int rID) {
		RID = rID;
	}

	public int getRType() {
		return RType;
	}

	public void setRType(int rType) {
		RType = rType;
	}
}
