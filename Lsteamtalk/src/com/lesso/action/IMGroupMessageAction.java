package com.lesso.action;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.lesso.beans.IMGroupMessage;
import com.lesso.service.IMGroupMessageService;
import com.lesso.serviceImpl.IMGroupMessageServiceImpl;
import com.lesso.util.DateWorkUtil;
import com.lesso.util.ResponseUtil;

public class IMGroupMessageAction extends HttpServlet{
	
    private IMGroupMessageService service = new IMGroupMessageServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method") == null?"":request.getParameter("method");
		if("".equals(method)){
			getList(request, response);
		}else if("search".equals(method)){
			search(request, response);
		}
	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		try{
			String groupId = request.getParameter("groupId") == null?"":request.getParameter("groupId");
			if(!"".equals(groupId)){
				String page = request.getParameter("page") == null?"":request.getParameter("page");
				String rows = request.getParameter("rows") == null?"":request.getParameter("rows");
				String begindate = request.getParameter("begindate") == null?"":request.getParameter("begindate");
				String enddate = request.getParameter("enddate") == null?"":request.getParameter("enddate");
				Map<String ,Object> m = new LinkedHashMap<String ,Object>();
				m.put("groupId", groupId);
				if(!"".equals(begindate)){
					begindate = DateWorkUtil.stringTotimechuo(begindate);
					m.put("createdA", begindate);
				}
				if(!"".equals(enddate)){
					enddate = DateWorkUtil.stringTotimechuo(enddate);
					m.put("createdB", enddate);
				}
				m.put("sort", "created");
				m.put("order", "desc");
				List <IMGroupMessage> list = service.getIMGroupMessageList(Integer.parseInt(page) , Integer.parseInt(rows), m);
				int total = service.getTotal(m);
				ResponseUtil.toPaginationJson(response, list, total);
			}else{
				
			}
		}catch(Exception e){
			e.printStackTrace();
			 ResponseUtil.ReMsg(response, true, e.getMessage(), e.getMessage());
		}
		
	}

	private void getList(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			String groupId = request.getParameter("groupId") == null?"":request.getParameter("groupId");
			if(!"".equals(groupId)){
				String page = request.getParameter("page") == null?"":request.getParameter("page");
				String rows = request.getParameter("rows") == null?"":request.getParameter("rows");
				Map<String ,Object> m = new LinkedHashMap<String ,Object>();
				m.put("groupId", groupId);
				m.put("sort", "created");
				m.put("order", "desc");
				List <IMGroupMessage> list = service.getIMGroupMessageList(Integer.parseInt(page) , Integer.parseInt(rows), m);
				int total = service.getTotal(m);
				ResponseUtil.toPaginationJson(response, list, total);
			}else{
				
			}
		}catch(Exception e){
			e.printStackTrace();
			 ResponseUtil.ReMsg(response, true, e.getMessage(), e.getMessage());
		}
		
	}
}
