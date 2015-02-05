package cn.changhong.chcare.core.webapi.bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	/**  
	 * @Fields serialVersionUID : TODO  
	*/
	private static final long serialVersionUID = 1L;
	private int ID;
	private String Name;
	private String Passwd;
	private boolean Gender;
	private Date Birthday;
	private double Height;
	private double Weight;
	private String Area;
	private String NickName;
	private String PhotoUrl;
	private String Sign;
	private int Status;
	private int Type;
	
	
	public Date getBirthDay() {
		return Birthday;
	}

	public void setBirthDay(Date birthDay) {
		Birthday = birthDay;
	}
	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	private Date RegTime;
	private Date LoginTime;
	private int Level;
	private int LoginCount;

	public User() {
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPasswd() {
		return Passwd;
	}

	public void setPasswd(String passwd) {
		Passwd = passwd;
	}

	public boolean isGender() {
		return Gender;
	}

	public void setGender(boolean gender) {
		Gender = gender;
	}

	public double getHeight() {
		return Height;
	}

	public void setHeight(double height) {
		Height = height;
	}

	public double getWeight() {
		return Weight;
	}

	public void setWeight(double weight) {
		Weight = weight;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getPhotoUrl() {
		return PhotoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		PhotoUrl = photoUrl;
	}

	public String getSign() {
		return Sign;
	}

	public void setSign(String sign) {
		Sign = sign;
	}

	public Date getRegTime() {
		return RegTime;
	}

	public void setRegTime(Date regTime) {
		RegTime = regTime;
	}

	public Date getLoginTime() {
		return LoginTime;
	}

	public void setLoginTime(Date loginTime) {
		LoginTime = loginTime;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}

	public int getLoginCount() {
		return LoginCount;
	}

	public void setLoginCount(int loginCount) {
		LoginCount = loginCount;
	}

}
