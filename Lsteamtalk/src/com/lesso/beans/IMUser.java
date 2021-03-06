package com.lesso.beans;

public class IMUser {
	 private int id;
	 private int sex;
     private String name;
     private String domain;
     private String nick;
     private String password;
     private String salt;
     private String phone;
     private String email;
     private String avatar;
     private int departId;
     private int status;
     private long created;
     private long updated;
     private int push_shield_status;
     private String sign_info;
     private int type;
     private String sap_vkorg;
     private String sap_kunnr;
     private String sap_kunnr_father;
     private String sapbm;
     private String sapgh;
     private String oanum;
     
     //数据库不存在的字段
     private String departname;
     
     
	public String getOanum() {
		return oanum;
	}
	public void setOanum(String oanum) {
		this.oanum = oanum;
	}
	public String getSapbm() {
		return sapbm;
	}
	public void setSapbm(String sapbm) {
		this.sapbm = sapbm;
	}
	public String getSapgh() {
		return sapgh;
	}
	public void setSapgh(String sapgh) {
		this.sapgh = sapgh;
	}
	public long getCreated() {
		return created;
	}
	public void setCreated(long created) {
		this.created = created;
	}
	public long getUpdated() {
		return updated;
	}
	public void setUpdated(long updated) {
		this.updated = updated;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public int getPush_shield_status() {
		return push_shield_status;
	}
	public void setPush_shield_status(int push_shield_status) {
		this.push_shield_status = push_shield_status;
	}
	public String getSign_info() {
		return sign_info;
	}
	public void setSign_info(String sign_info) {
		this.sign_info = sign_info;
	}
	public int getDepartId() {
		return departId;
	}
	public void setDepartId(int departId) {
		this.departId = departId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDepartname() {
		return departname;
	}
	public void setDepartname(String departname) {
		this.departname = departname;
	}
	public String getSap_vkorg() {
		return sap_vkorg;
	}
	public void setSap_vkorg(String sap_vkorg) {
		this.sap_vkorg = sap_vkorg;
	}
	public String getSap_kunnr() {
		return sap_kunnr;
	}
	public void setSap_kunnr(String sap_kunnr) {
		this.sap_kunnr = sap_kunnr;
	}
	public String getSap_kunnr_father() {
		return sap_kunnr_father;
	}
	public void setSap_kunnr_father(String sap_kunnr_father) {
		this.sap_kunnr_father = sap_kunnr_father;
	}  
     
     
       
}
