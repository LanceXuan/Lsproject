package com.lesso.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.lesso.beans.ErrorMsg;
import com.lesso.beans.IMDiscovery;
import com.lesso.service.IMDiscoveryService;
import com.lesso.serviceImpl.IMDiscoveryServiceImpl;
import com.lesso.util.DateWorkUtil;
import com.lesso.util.ResponseUtil;

public class DiscoveryAction extends HttpServlet {
	private IMDiscoveryService service = new IMDiscoveryServiceImpl();
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
			   throws ServletException, IOException{
		     doPost(request,response);
	   }
	   public void doPost(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException {
		   String method = request.getParameter("method") == null?"":request.getParameter("method");
		   if("".equals(method)){
			   getDiscovery(request,response);
		   }else if("save".equals(method)){
			   saveDiscovery(request,response);
		   }else if("update".equals(method)){
			   updateDiscovery(request,response);
		   }else if("delete".equals(method)){
			   deleteDiscovery(request,response);
		   }else if("searh".equals(method)){
			   searhDiscovery(request,response);
		   }
	   }
	private void searhDiscovery(HttpServletRequest request,
			HttpServletResponse response) {

		
	}
	private void deleteDiscovery(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			String id = request.getParameter("id") == null?"":request.getParameter("id");
			//删除个人信息
			boolean isok =service.delete(Integer.parseInt(id));
//			boolean isok = service.updatestate(Integer.parseInt(id));
			//删除曾经的群组信息
			
			 ErrorMsg msg = new ErrorMsg();
	           if(isok){
					msg.setHasError(false);
					msg.setLogMsg("success delete");
				}else{
					msg.setHasError(true);
					msg.setLogMsg("fail delete");
				}
				response.setContentType("text/html;charset=utf-8");
			    response.getWriter().write(JSONArray.fromObject(msg).toString());
		}catch(Exception e){
			ResponseUtil.ReMsg(response, true, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}
		
	}
	private void updateDiscovery(HttpServletRequest request,
			HttpServletResponse response) {
           
		
	}
	private void saveDiscovery(HttpServletRequest request,
			HttpServletResponse response) {
		try{
	   		long dds = DateWorkUtil.timechuo();
			String itemName = request.getParameter("itemName") == null?"":request.getParameter("itemName");
			String itemPriority = request.getParameter("itemPriority") == null?"":request.getParameter("itemPriority");
			String itemUrl = request.getParameter("itemUrl") == null?"":request.getParameter("itemUrl");
//			String status = request.getParameter("status") == null?"":request.getParameter("status");
			IMDiscovery imu = new IMDiscovery();
			imu.setCreated(dds);
			imu.setItemName(itemName);
			imu.setItemPriority(Integer.parseInt(itemPriority));
			imu.setItemUrl(itemUrl);
			imu.setStatus(0);
			imu.setUpdated(dds);
			boolean isok = service.saveIMDiscovery(imu);
	           ErrorMsg msg = new ErrorMsg();
	           if(isok){
					msg.setHasError(false);
					msg.setLogMsg("success login");
				}else{
					msg.setHasError(true);
					msg.setLogMsg("fail login");
				}
				response.setContentType("text/html;charset=utf-8");
			    response.getWriter().write(JSONArray.fromObject(msg).toString());
		}catch(Exception e){
			ResponseUtil.ReMsg(response, true, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}
		
	}
	private void getDiscovery(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			String page = request.getParameter("page") == null?"":request.getParameter("page");
			String rows = request.getParameter("rows") == null?"":request.getParameter("rows");
			Map<String,Object> m = new HashMap<String,Object>();
			List<IMDiscovery> list = service.getIMDiscovery(Integer.parseInt(page), Integer.parseInt(rows), m);
			int total = service.getTotal(m);
			ResponseUtil.toPaginationJson(response, list, total);
		}catch(Exception e){
			ResponseUtil.ReMsg(response, true, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}
		
	}
}
