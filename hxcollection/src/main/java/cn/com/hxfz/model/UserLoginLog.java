package cn.com.hxfz.model;

import java.util.Date;

public class UserLoginLog {
	
	private String id;
	private String nickName;
	private Integer loginNum;
	private String version;
	private String equipmentInformation;
	private Date loginTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getLoginNum() {
		return loginNum;
	}
	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getEquipmentInformation() {
		return equipmentInformation;
	}
	public void setEquipmentInformation(String equipmentInformation) {
		this.equipmentInformation = equipmentInformation;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	
}
