package com.lesso.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lesso.service.Z_KNAService;
import com.lesso.service.Z_User_Power_UserService;
import com.lesso.service.Z_User_VkorgService;
import com.lesso.service.Z_VkorgService;
import com.lesso.serviceImpl.Z_KNAServiceImpl;
import com.lesso.serviceImpl.Z_User_Power_UserServiceImpl;
import com.lesso.serviceImpl.Z_User_VkorgServiceImpl;
import com.lesso.serviceImpl.Z_VkorgServiceImpl;

/** * @author  Lance 
 * @date 创建时间：2016年1月5日 上午11:09:07 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class VkorgListAction extends HttpServlet{
	private Z_VkorgService service = new Z_VkorgServiceImpl();
	private Z_User_VkorgService servicea = new Z_User_VkorgServiceImpl();
	private Z_KNAService serviceb = new Z_KNAServiceImpl();
	private Z_User_Power_UserService servicec = new Z_User_Power_UserServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method") == null?"":request.getParameter("method");
		if("getVkorgList".equals(method)){
			service.getAll(request, response);
		}else if("saveUserVkorg".equals(method)){
			servicea.saveUserVkorg(request, response);
		}else if("getKNAList".equals(method)){
			serviceb.getZ_KNAListchose(request, response);
		}else if("saveUserPowerUser".equals(method)){
			servicec.saveUPUChose(request, response);
		}else if("copycontrol".equals(method)){
			servicea.copyusers(request, response);
		}
	}
}
