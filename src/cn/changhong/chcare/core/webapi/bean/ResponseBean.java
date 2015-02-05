package cn.changhong.chcare.core.webapi.bean;

import java.io.Serializable;

public class ResponseBean<T> implements Serializable{
	/**  
	 * @Fields serialVersionUID : TODO  
	*/
	private static final long serialVersionUID = 1L;
	private int State;
	private String Desc;
	private T Data;

	public T getData() {
		return Data;
	}

	public void setData(T data) {
		Data = data;
	}

	public int getState() {
		return State;
	}

	public void setState(int state) {
		State = state;
	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

}
