package com.lesso.model;
/** * @author  Lance 
 * @date 创建时间：2016年1月6日 下午2:03:47 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class UserPowerUser {
	private String Kunnr;
	private String name;
	private String vkorg;
	private String Login_id;
	private String pwd;
	private String area;
	private String area_name;
	private String status;

	   public String getKunnr() {
		return Kunnr;
	}
	public void setKunnr(String kunnr) {
		Kunnr = kunnr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVkorg() {
		return vkorg;
	}
	public void setVkorg(String vkorg) {
		this.vkorg = vkorg;
	}
	public String getLogin_id() {
		return Login_id;
	}
	public void setLogin_id(String login_id) {
		Login_id = login_id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	   
	   
}
