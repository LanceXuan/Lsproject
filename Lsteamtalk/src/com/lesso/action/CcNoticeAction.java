package com.lesso.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.dispatcher.Dispatcher;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lesso.beans.CcNotice;
import com.lesso.beans.ErrorMsg;
import com.lesso.beans.IMAdmin;
import com.lesso.beans.IMUser;
import com.lesso.beans.tbl_NoticesReceived;
import com.lesso.model.RemarkBack;
import com.lesso.service.CcNoticeService;
import com.lesso.service.tbl_NoticesReceivedService;
import com.lesso.serviceImpl.CcNoticeServiceImpl;
import com.lesso.serviceImpl.tbl_NoticesReceivedServiceImpl;
import com.lesso.util.DateWorkUtil;
import com.lesso.util.ResponseUtil;
import com.lesso.util.StringUtil;
import com.lib.Security;

public class CcNoticeAction extends HttpServlet {
	private CcNoticeService service = new CcNoticeServiceImpl();
	private tbl_NoticesReceivedService ntservice = new tbl_NoticesReceivedServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method") == null ? "" : request
				.getParameter("method");
		if ("saveCcNotice".equals(method)) {
			saveCcNotice(request, response);
		} else if ("getCcNotice".equals(method)) {
			getCcNotice(request, response);
		} else if ("".equals(method)) {
			getNoticeList(request, response);
		} else if ("getReadstatus".equals(method)){
			getReadstatus(request, response);
		} else if("updateCcNotice".equals(method)){
			updateCcNotice(request, response);
		} else if("deleteCcNotice".equals(method)){
			deleteCcNotice(request, response);
		}

	}

	private void getNoticeList(HttpServletRequest request,
			HttpServletResponse response) {
		Log logs = LogFactory.getLog(CcNoticeAction.class);
		try {
			logs.info("获取公告列表");
			String page = request.getParameter("page") == null ? "0" : request
					.getParameter("page");
			String rows = request.getParameter("rows") == null ? "0" : request
					.getParameter("rows");
			String begindate = request.getParameter("begindate") == null ? ""
					: request.getParameter("begindate");
			String enddate = request.getParameter("enddate") == null ? ""
					: request.getParameter("enddate");
			String title = request.getParameter("title") == null ? ""
					: request.getParameter("title");
             Map<String,Object> map = new HashMap<String,Object>();
             if(!"".equals(begindate)){
            	 map.put("createdA", begindate);
             }
             if(!"".equals(enddate)){
            	 map.put("createdB", begindate);
             }
             if(!"".equals(title)){
            	 map.put("title", title);
             }
             map.put("sort", "created");
             List<CcNotice> list = service.findByPagination(Integer.parseInt(page), Integer.parseInt(rows), map);
             int total = service.getTotal(map);
             ResponseUtil.toPaginationJson(response, list, total);            
		} catch (Exception e) {
             e.printStackTrace();
             logs.info("获取公告列表出错:"+e.getMessage());
		}

	}

	private void saveCcNotice(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String title = request.getParameter("title") == null ? "" : request
					.getParameter("title");
			String content = request.getParameter("content") == null ? ""
					: request.getParameter("content");
			String reviceor = request.getParameter("reviceor") == null ? ""
					: request.getParameter("reviceor");
			String firstImg = request.getParameter("firstImg") == null ? ""
					: request.getParameter("firstImg");
			CcNotice ccNotice = new CcNotice();
			HttpSession session = request.getSession();
			IMAdmin inadmin = (IMAdmin) session.getAttribute("userinfo");
			if(inadmin == null){
				throw(new Exception("会话过期,请刷新重新登录！"));
			}
			ccNotice.setTitle(title);
			ccNotice.setContent(content);
			ccNotice.setFirstImg(firstImg);
			ccNotice.setReviceor(reviceor);
			ccNotice.setSendor(String.valueOf(inadmin.getIMUserId()));
			ccNotice.setReadStatus(0);
			ccNotice.setCreated(DateWorkUtil.stringToDatenow());
			int isok = service.saveCcNotice(ccNotice);
			List<RemarkBack> listrb = new ArrayList<RemarkBack>();
			ErrorMsg msg = new ErrorMsg();
			if (isok>0) {
				// HttpSession session = request.getSession();
				//insert tbl_NoticesReceived
				 String[] arr = reviceor.split(",");
				 for(int i=0;i<arr.length;i++){
					 tbl_NoticesReceived mp = new tbl_NoticesReceived();
					 mp.setCreated(DateWorkUtil.stringToDatenow());
					 mp.setNoticeId(isok);
					 mp.setUserId(Integer.parseInt(arr[i]));
					 mp.setReceivetime(null);
					 mp.setStatus(0);
					 ntservice.save(mp);
					 
					 RemarkBack rb = new RemarkBack();
					 String s ="";
					 if(content.length()>18){
					   s = StringUtil.removeHtmlTag(content).substring(0,10);
					 }else{
						 s = StringUtil.removeHtmlTag(content);
					 }
					 String dcontent = "msgtype:7[{:&$#@~^@:}]http://im2.lesso.com:8181/Lsteamtalk/privilegemgmt/ccNotice.action?method=getCcNotice&&iceId="+isok+"&&suid"
					 		+ "="+arr[i]+"[{:&$#@~^@:}]"+title+"[{:&$#@~^@:}]"+DateWorkUtil.stringToDatenow()+"[{:&$#@~^@:}]"+s;
					 String encmsg =Security.getInstance().EncryptMsg(dcontent);
					 rb.setExceptionMag(String.valueOf(isok));
					 rb.setHasError(false);
					 rb.setLogMsg(String.valueOf(isok));
					 rb.setRemarka(encmsg);
					 rb.setRemarkb(arr[i]);
					 rb.setRemarkc("");	
					 listrb.add(rb);
				 }
				//insert end
				
				msg.setHasError(false);
				msg.setLogMsg(String.valueOf(isok));
			} else {
				msg.setHasError(true);
				msg.setLogMsg("fail");
				
				 RemarkBack rb = new RemarkBack();
				 rb.setExceptionMag(String.valueOf(isok));
				 rb.setHasError(true);
				 rb.setLogMsg("fail");
				 rb.setRemarka("");
				 rb.setRemarkb("");
				 rb.setRemarkc("");	
				 listrb.add(rb);
			}
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSONArray.fromObject(listrb).toString());
		} catch (Exception e) {
			e.printStackTrace();
			ResponseUtil.ReMsg(response, true, e.getMessage(), "fail");
		}
	}

	private void getCcNotice(HttpServletRequest request,
			HttpServletResponse response) {
		Log logs = LogFactory.getLog(CcNoticeAction.class);
		try {
			String noticeId = request.getParameter("iceId") == null ? ""
					: request.getParameter("iceId");
			String uid =  request.getParameter("suid") == null ? ""
					: request.getParameter("suid");
			logs.info("CcNoticeAction-getCcNotice is begin and iceid is "+noticeId+" and uid is "+uid);
			CcNotice notice = service.getCcNotice(Integer.valueOf(noticeId));
			if(StringUtil.isNotEmpty(uid)){
			    boolean isok = service.updateMarkRead(uid, Integer.valueOf(noticeId));
			    ntservice.updateReceivedNotices(request, response);
			}
			notice.setCreated(notice.getCreated());
			request.setAttribute("notice", notice);
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("../jsp/checkNotice.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			logs.info(e.getMessage());
		}

	}
	
	public void getReadstatus(HttpServletRequest request,
			HttpServletResponse response){
		Log logs = LogFactory.getLog(CcNoticeAction.class);
		try {
			String id =  request.getParameter("id") == null ? "": request.getParameter("id");
			System.out.println(id);
		  service.getNoticeReadList(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			logs.info(e.getMessage());
		}
	}
	
	public void updateCcNotice(HttpServletRequest request,
			HttpServletResponse response){
		Log logs = LogFactory.getLog(CcNoticeAction.class);
		try {
			String noticeId = request.getParameter("noticeId") == null ? ""
					: request.getParameter("noticeId");
			CcNotice notice = service.getCcNotice(Integer.valueOf(noticeId));
			List<IMUser> userList = service.getIMUserListByIdString(notice.getReviceor());
			request.setAttribute("userList", userList);
		    request.setAttribute("notice", notice);
		    request.setAttribute("operate", "update");
			response.setContentType("text/html;charset=UTF-8");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("ccNotice.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logs.info(e.getMessage());
		}
		
	}
	
	public void deleteCcNotice(HttpServletRequest request,
			HttpServletResponse response){
		try {
			String noticeIds = request.getParameter("noticeIds") == null ? ""
					: request.getParameter("noticeIds");
			service.deleteCcNoticeByIds(noticeIds);
			 RemarkBack rb = new RemarkBack();
			 //rb.setExceptionMag("成功删除");
			 rb.setHasError(true);
			 rb.setLogMsg("success");
			 rb.setRemarka("成功删除");
			 rb.setRemarkb("");
			 rb.setRemarkc("");	
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSONObject.fromObject(rb).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
