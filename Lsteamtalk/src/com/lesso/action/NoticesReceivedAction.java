package com.lesso.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lesso.service.tbl_NoticesReceivedService;
import com.lesso.serviceImpl.tbl_NoticesReceivedServiceImpl;

/** * @author  Lance 
 * @date 创建时间：2015年12月28日 上午11:27:51 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class NoticesReceivedAction  extends HttpServlet{
	private tbl_NoticesReceivedService service = new tbl_NoticesReceivedServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method") == null ? "" : request
				.getParameter("method");
		if ("getNoticesReceivedList".equals(method)) {
			getNoticesReceivedList(request, response);
		} else if ("getCcNotice".equals(method)) {
			//getCcNotice(request, response);
		} 
	}

	private void getNoticesReceivedList(HttpServletRequest request,
			HttpServletResponse response) {
		service.getNoticesReceivedList(request, response);
		
	}
}
