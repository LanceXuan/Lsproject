package com.lesso.beans;

public class IMDepart {
  private int id;
  private String departName;
  private int priority;
  private Integer parentid;
  private int status;
  private long created;
  private long updated;
  private int leader;
  private String sapcode;
  private String parentsapcode;
  //不存在数据库的字段
  private String leadername;
  private String parentname;
  
  
public String getParentsapcode() {
	return parentsapcode;
}
public void setParentsapcode(String parentsapcode) {
	this.parentsapcode = parentsapcode;
}
public String getSapcode() {
	return sapcode;
}
public void setSapcode(String sapcode) {
	this.sapcode = sapcode;
}
public String getLeadername() {
	return leadername;
}
public void setLeadername(String leadername) {
	this.leadername = leadername;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDepartName() {
	return departName;
}
public void setDepartName(String departName) {
	this.departName = departName;
}
public int getPriority() {
	return priority;
}
public void setPriority(int priority) {
	this.priority = priority;
}

public Integer getParentid() {
	return parentid;
}
public void setParentid(Integer parentid) {
	this.parentid = parentid;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
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
public int getLeader() {
	return leader;
}
public void setLeader(int leader) {
	this.leader = leader;
}
public String getParentname() {
	return parentname;
}
public void setParentname(String parentname) {
	this.parentname = parentname;
}
  
  
}
