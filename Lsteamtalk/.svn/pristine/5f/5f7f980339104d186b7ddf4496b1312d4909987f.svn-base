package com.lesso.serviceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lesso.beans.ErrorMsg;
import com.lesso.beans.IMAdmin;
import com.lesso.beans.IMUser;
import com.lesso.daoImpl.IMAdminDaoImpl;
import com.lesso.service.IMAdminService;
import com.lesso.util.MD5Util;

public class IMAdminServiceImpl implements IMAdminService{
     private IMAdminDaoImpl dao = new IMAdminDaoImpl();
	@Override
	public IMAdmin findIMAdmin(String username) {
   	
		return dao.findIMAdmin(username);
	}

	@Override
	public boolean saveIMAdmin(IMAdmin imadmin) {
		// TODO Auto-generated method stub
		return dao.saveIMAdmin(imadmin);
	}

	@Override
	public ErrorMsg login(HttpServletRequest request,String username, String pwd) {

		ErrorMsg msg = new ErrorMsg();
		try{
			IMAdmin user = dao.findIMAdmin(username);
			String str = MD5Util.md5(pwd);
			if(user!=null && user.getId()>0){
					if(str.equals(user.getPwd())){ 
						HttpSession session = request.getSession();
						session.setAttribute("userinfo", user);
						msg.setHasError(false);
						msg.setLogMsg("success login");
						msg.setExceptionMag("success login");
					}else{
						
						throw new Exception("密码错误!");
					}
				}else{
					throw new Exception("不存在该用户!请确认！");
				}
			
			return msg;
		}catch(Exception e){
			msg.setHasError(true);
		    msg.setLogMsg("fail login");
		    msg.setExceptionMag(e.getMessage());
			return msg;
		}
	}



}
