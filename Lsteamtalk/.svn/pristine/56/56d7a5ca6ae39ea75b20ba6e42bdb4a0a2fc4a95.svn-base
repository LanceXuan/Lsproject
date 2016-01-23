package com.lesso.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lesso.service.Z_Depart_Type_RightService;
import com.lesso.serviceImpl.Z_Depart_Type_RightServiceImpl;

/** * @author  Lance 
 * @date 创建时间：2016年1月18日 下午4:11:02 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class DepartRightControlAction extends HttpServlet {
	private Z_Depart_Type_RightService service = new Z_Depart_Type_RightServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method") == null?"":request.getParameter("method");
		if("init".equals(method)){
			service.initAndSearch(request, response);
		}else if("saveorupdate".equals(method)){
			service.saveorupdate(request, response);
		}else if("delete".equals(method)){
			service.deleteZDepartRight(request, response);
		}
	}
}
