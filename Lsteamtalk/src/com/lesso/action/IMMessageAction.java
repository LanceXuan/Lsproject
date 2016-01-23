package com.lesso.action;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.lesso.beans.ErrorMsg;
import com.lesso.beans.IMMessage;
import com.lesso.beans.IMRelationShip;
import com.lesso.beans.IMUser;
import com.lesso.model.RemarkBack;
import com.lesso.service.IMMessageService;
import com.lesso.service.IMRelationShipService;
import com.lesso.serviceImpl.IMMessageServiceImpl;
import com.lesso.serviceImpl.IMRelationShipServiceImpl;
import com.lesso.util.DateWorkUtil;
import com.lesso.util.ResponseUtil;
import com.lesso.util.StringUtil;
import com.lib.Security;

public class IMMessageAction extends HttpServlet{

	 private IMMessageService service = new IMMessageServiceImpl();
	 private IMRelationShipService shipservice = new IMRelationShipServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method") == null?"":request.getParameter("method");
		if("".equals(method)){
			getIMMessageList(request, response);
		}else if("search".equals(method)){
			search(request, response);
		}else if("testdec".equals(method)){
			 String type = request.getParameter("type");
		       String msg = request.getParameter("encmsg");
		       String dfk ="";
		       if(StringUtil.isNotEmpty(type) && StringUtil.isNotEmpty(msg)){
		    	   if("1".equals(type)){
		    		   dfk =Security.getInstance().EncryptMsg(msg);
		    		   
		    	   }else if("2".equals(type)){
		    		   dfk =Security.getInstance().DecryptMsg(msg); 
		    	   }
		    	   RemarkBack fds = new RemarkBack();
		    	   fds.setRemarka(dfk);
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().write(JSONArray.fromObject(fds).toString());
		       }
		}
	}

	private void getIMMessageList(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			String relateId = request.getParameter("relateId") == null?"0":request.getParameter("relateId");
			if(!"".equals(relateId)){
				String page = request.getParameter("page") == null?"":request.getParameter("page");
				String rows = request.getParameter("rows") == null?"":request.getParameter("rows");
				Map<String ,Object> m = new LinkedHashMap<String ,Object>();
				m.put("relateId", Integer.parseInt(relateId));
				m.put("sort", "created");
				m.put("order", "desc");
				List <IMMessage> list = service.getIMMessageList(Integer.parseInt(page), Integer.parseInt(rows), m);
				int total = service.getTotal(m);
				ResponseUtil.toPaginationJson(response, list, total);
			}else{
				List <IMMessage> list = null;
				int total = -1;
				ResponseUtil.toPaginationJson(response, list, total);
			}
		}catch(Exception e){
			e.printStackTrace();
			 ResponseUtil.ReMsg(response, true, e.getMessage(), e.getMessage());
		}
		
	}
	
	
	private void search(HttpServletRequest request, HttpServletResponse response) {
		try{
			String toId = request.getParameter("toId") == null?"0":request.getParameter("toId");
			String fromId = request.getParameter("fromId") == null?"0":request.getParameter("fromId");
			String page = request.getParameter("page") == null?"":request.getParameter("page");
			String rows = request.getParameter("rows") == null?"":request.getParameter("rows");
			String begindate = request.getParameter("begindate") == null?"":request.getParameter("begindate");
			String enddate = request.getParameter("enddate") == null?"":request.getParameter("enddate");
			if(!"0".equals(toId) && !"0".equals(fromId)){
//				HttpSession session = request.getSession();
//				IMUser inadmin = (IMUser) session.getAttribute("userinfo");
//				if(inadmin == null){
//                    throw new Exception("outSessionA");
//				}else{
				    IMRelationShip sh=shipservice.findbyfromto(Integer.parseInt(fromId), Integer.parseInt(toId));
					if(sh != null){
						int relateId =sh.getId();
						Map<String ,Object> m = new LinkedHashMap<String ,Object>();
						m.put("relateId", relateId);
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
						List <IMMessage> list = service.getIMMessageList(Integer.parseInt(page) , Integer.parseInt(rows), m);
						int total = service.getTotal(m);
						ResponseUtil.toPaginationJson(response, list, total);
					 }else{
						 List <IMMessage> list = null;
							int total = 0;
							ResponseUtil.toPaginationJson(response, list, total);
					 }
			       
			}else if("0".equals(toId) && "0".equals(fromId)){
					Map<String ,Object> m = new LinkedHashMap<String ,Object>();
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
					List <IMMessage> list = service.getIMMessageList(Integer.parseInt(page) , Integer.parseInt(rows), m);
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
