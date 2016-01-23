package com.lesso.beans;

public class IMAdmin {
	 private int id;
     private String uname;
     private String pwd;
     private int status;
     private int created;
     private int updated;
     private int IMUserId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int satatus) {
		this.status = satatus;
	}
	public int getCreated() {
		return created;
	}
	public void setCreated(int created) {
		this.created = created;
	}
	public int getUpdated() {
		return updated;
	}
	public void setUpdated(int updated) {
		this.updated = updated;
	}
	public int getIMUserId() {
		return IMUserId;
	}
	public void setIMUserId(int iMUserId) {
		IMUserId = iMUserId;
	}
	
}
