package com.lesso.action;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;










import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONArray;

import com.lesso.beans.ErrorMsg;
import com.lesso.beans.IMGroup;
import com.lesso.service.IMGroupService;
import com.lesso.serviceImpl.IMGroupServiceImpl;
import com.lesso.util.ResponseUtil;
import com.lesso.util.StringUtil;

public class GroupAction extends HttpServlet{
	private IMGroupService service = new IMGroupServiceImpl();
   public void doGet(HttpServletRequest request,HttpServletResponse response) 
		   throws ServletException, IOException{
	   doPost(request,response);
   }
   public void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	   String method = request.getParameter("method") == null?"":request.getParameter("method");
	   if("".equals(method)){
		   getGroupList(request,response);
	   }else if("combolist".equals(method)){
		   combolist(request,response);
	   }else if("search".equals(method)){
		   search(request,response);  
	   }
   }
   
   
  private void search(HttpServletRequest request, HttpServletResponse response) {
		Log logs = LogFactory.getLog(GroupAction.class);	
	  try{
		String name = request.getParameter("name") == null?"":request.getParameter("name");
		String page = request.getParameter("page") == null?"":request.getParameter("page");
		String rows = request.getParameter("rows") == null?"":request.getParameter("rows");
		Map<String ,Object> m = new LinkedHashMap<String ,Object>();
		m.put("status", 0);
		if(StringUtil.isNotEmpty(name)){
			m.put("name", name);
		}
		List<IMGroup> list = service.getIMGroupList(Integer.parseInt(page) , Integer.parseInt(rows) , m);
		int total = service.getTotal(m);
		logs.info("搜索群组总数为"+total);
		ResponseUtil.toPaginationJson(response, list, total);
	}catch(Exception e){
    	this.ReMsg(response, true, e.getMessage(), e.getMessage());
    	logs.info("搜索群组error is "+e.getMessage());
		e.printStackTrace();
	}
	
}
private void combolist(HttpServletRequest request, HttpServletResponse response) {
	  try{
			Map<String ,Object> m = new LinkedHashMap<String ,Object>();
			m.put("status", 0);
			List<IMGroup> list = service.getIMGroupList(-1 , -1 , m);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json");
		    response.getWriter().write(JSONArray.fromObject(list).toString());
	  }catch(Exception e){
		  this.ReMsg(response, true, e.getMessage(), e.getMessage());
			e.printStackTrace();
	  }
	
}
private void getGroupList(HttpServletRequest request,HttpServletResponse response) {
    try{
		String page = request.getParameter("page") == null?"":request.getParameter("page");
		String rows = request.getParameter("rows") == null?"":request.getParameter("rows");
		Map<String ,Object> m = new LinkedHashMap<String ,Object>();
		m.put("status", 0);
		List<IMGroup> list = service.getIMGroupList(Integer.parseInt(page) , Integer.parseInt(rows) , m);
		int total = service.getTotal(m);
		ResponseUtil.toPaginationJson(response, list, total);
    }catch(Exception e){
    	this.ReMsg(response, true, e.getMessage(), e.getMessage());
		e.printStackTrace();
    }   
	
  }
  
  
  
  
  
  
	public void ReMsg(HttpServletResponse response,boolean HasError,String errormsg,String logmsg){
		try{
		    ErrorMsg msg = new ErrorMsg();
			msg.setHasError(HasError);
			msg.setExceptionMag(errormsg);
			msg.setLogMsg(logmsg);
			response.setContentType("text/html;charset=utf-8");
		    response.getWriter().write(JSONArray.fromObject(msg).toString());
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
}
