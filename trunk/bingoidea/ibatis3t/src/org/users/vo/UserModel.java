package org.users.vo;

public class UserModel implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4068772480759524122L;
	private Integer userId;
	private String userName;
	private String password;
	private String name;
	private String sex;
	private String email;
	private String contactPhone;
	private String lockedInd;
	private String defunctInd;
	private Integer version;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getLockedInd() {
		return lockedInd;
	}
	public void setLockedInd(String lockedInd) {
		this.lockedInd = lockedInd;
	}
	public String getDefunctInd() {
		return defunctInd;
	}
	public void setDefunctInd(String defunctInd) {
		this.defunctInd = defunctInd;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
}
