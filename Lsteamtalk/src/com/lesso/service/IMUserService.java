package com.lesso.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lesso.beans.ErrorMsg;
import com.lesso.beans.IMUser;

public interface IMUserService {
	public IMUser findIMUser(String username);
	public boolean saveIMUser(IMUser imuser);
	public List<IMUser> findByPagination(int currentPage, int pageSize  ,Map<String ,Object> m)
 			throws Exception;
	public int getTotal(Map<String ,Object> m) throws Exception ;
	public boolean deleteIMUser(int id);
	public boolean updatestate(int id);
    public boolean updateIMUserPwd(String pwd,int id);
    public boolean updateIMUser(String sign_info,String sex,String nick,String phone,
    		String email,String departId,String photo,int id,int type);
    public ErrorMsg updateIMUserpwd(String name,String oldpassword,String newpassword);
    public boolean uodateavatar(String username,String photo);
    public List<IMUser> findIMUserBydepartId(int departId);
    public ErrorMsg login(HttpServletRequest request,String username, String pwd);
    public List<IMUser> findIMUserByUserName(String search);
    //根据idString获取人员列表
    public List<IMUser> findIMUserByIdString(String idString);
}
