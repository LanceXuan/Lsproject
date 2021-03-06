package com.lesso.action;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.lesso.beans.ErrorMsg;
import com.lesso.beans.IMUser;
import com.lesso.service.IMUserService;
import com.lesso.serviceImpl.IMUserServiceImpl;
import com.lesso.util.DateWorkUtil;
import com.lesso.util.MD5Util;
import com.lesso.util.ResponseUtil;
import com.lesso.util.SmsCodeUtil;
import com.lesso.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;

public class UsersAction extends HttpServlet {
	private IMUserService service = new IMUserServiceImpl();
	Log logs = LogFactory.getLog(UsersAction.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method") == null ? "" : request
				.getParameter("method");
		if (method.equals("")) {
			getusersList(request, response);
		} else if (method.equals("save")) {
			saveuser(request, response);
		} else if ("update".equals(method)) {
			updateuser(request, response);
		} else if ("delete".equals(method)) {
			delete(request, response);
		} else if ("search".equals(method)) {
			search(request, response);
		} else if ("xgmima".equals(method)) {
			updatepwd(request, response);
		} else if ("getdepart".equals(method)) {
			getdepList(request, response);
		} else if ("setuserinfo".equals(method)) {
			setuserinfo(request, response);
		} else if ("getdepartUser".equals(method)) {
			getdepartUser(request, response);
		} else if ("login".equals(method)){
			login(request, response);
		}else if ("getSearchtUser".equals(method)){
			getSearchtUser(request, response);
		}

	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username") == null?"":request.getParameter("username");
		String password = request.getParameter("password") == null?"":request.getParameter("password");
		try{
			ErrorMsg msg= service.login(request,username, password);
			response.setContentType("text/html;charset=utf-8");
		    response.getWriter().write(JSONArray.fromObject(msg).toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private void setuserinfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String name = request.getParameter("name") == null ? "" : request
					.getParameter("name");
			String id = request.getParameter("id") == null ? "" : request
					.getParameter("id");
			IMUser user = service.findIMUser(name);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSONArray.fromObject(user).toString());
		} catch (Exception e) {
			this.ReMsg(response, true, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}

	}

	private void getdepList(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String departid = request.getParameter("departid") == null ? ""
					: request.getParameter("departid");
			List<IMUser> list = service.findIMUserBydepartId(Integer
					.parseInt(departid));
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
			logs.info("usersAction-getdepList exception is " + e.getMessage());
		}

	}

	private void updatepwd(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String name = request.getParameter("loginame") == null ? ""
					: request.getParameter("loginame");
			String oldpassword = request.getParameter("oldpassword") == null ? ""
					: request.getParameter("oldpassword");
			String newpassword = request.getParameter("newpassword") == null ? ""
					: request.getParameter("newpassword");
			ErrorMsg msg = service.updateIMUserpwd(name, oldpassword,
					newpassword);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSONArray.fromObject(msg).toString());
		} catch (Exception e) {
			this.ReMsg(response, true, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}

	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("name") == null ? "" : request
					.getParameter("name");
			String type = request.getParameter("type") == null ? "0" : request
					.getParameter("type");
			String departId = request.getParameter("departId") == null ? ""
					: request.getParameter("departId");
			String seaname = request.getParameter("seaname") == null ? ""
					: request.getParameter("seaname");
			Map<String, Object> m = new HashMap<String, Object>();
			if (StringUtil.isNotEmpty(name)) {
				m.put("nick", name);
			}
			if (StringUtil.isNotEmpty(type)) {
				if (Integer.parseInt(type) > 0) {
					m.put("type", type);
				}
			}
			if (StringUtil.isNotEmpty(departId)) {
				m.put("departId", departId);
			}
			if (StringUtil.isNotEmpty(seaname)) {
				m.put("nick", seaname);
			}
			m.put("status", 0);
			String page = request.getParameter("page") == null ? "" : request
					.getParameter("page");
			String rows = request.getParameter("rows") == null ? "" : request
					.getParameter("rows");
			List<IMUser> list = service.findByPagination(
					Integer.parseInt(page), Integer.parseInt(rows), m);
			int total = service.getTotal(m);
			ResponseUtil.toPaginationJson(response, list, total);
		} catch (Exception e) {
			this.ReMsg(response, true, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}

	}

	private void updateuser(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String id = request.getParameter("id") == null ? "" : request
					.getParameter("id");
			String sign_info = request.getParameter("sign_info") == null ? ""
					: request.getParameter("sign_info");
			String sex = request.getParameter("sex") == null ? "" : request
					.getParameter("sex");
			String nick = request.getParameter("nick") == null ? "" : request
					.getParameter("nick");
			String phone = request.getParameter("phone") == null ? "" : request
					.getParameter("phone");
			String email = request.getParameter("email") == null ? "" : request
					.getParameter("email");
			String departId = request.getParameter("departId") == null ? ""
					: request.getParameter("departId");
			String type = request.getParameter("type") == null ? "-1" : request
					.getParameter("type");
			boolean isok = service.updateIMUser(sign_info, sex, nick, phone,
					email, departId, "", Integer.parseInt(id),
					Integer.parseInt(type));
			ErrorMsg msg = new ErrorMsg();
			if (isok) {
				// HttpSession session = request.getSession();
				msg.setHasError(false);
				msg.setLogMsg("success updateuser");
			} else {
				msg.setHasError(true);
				msg.setLogMsg("fail updateuser");
			}
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSONArray.fromObject(msg).toString());
		} catch (Exception e) {
			this.ReMsg(response, true, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}

	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id") == null ? "" : request
					.getParameter("id");
			// 删除个人信息
			// boolean isok =service.deleteIMUser(Integer.parseInt(id));
			boolean isok = service.updatestate(Integer.parseInt(id));
			// 删除曾经的群组信息

			ErrorMsg msg = new ErrorMsg();
			if (isok) {
				msg.setHasError(false);
				msg.setLogMsg("success delete");
			} else {
				msg.setHasError(true);
				msg.setLogMsg("fail delete");
			}
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSONArray.fromObject(msg).toString());
		} catch (Exception e) {
			this.ReMsg(response, true, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}

	}

	public void getusersList(HttpServletRequest request,
			HttpServletResponse response) {
		String page = request.getParameter("page") == null ? "" : request
				.getParameter("page");
		String rows = request.getParameter("rows") == null ? "" : request
				.getParameter("rows");
		try {
			Map<String, Object> m = new LinkedHashMap<String, Object>();
			m.put("status", 0);
			List<IMUser> list = service.findByPagination(
					Integer.parseInt(page), Integer.parseInt(rows), m);
			int total = service.getTotal(m);
			ResponseUtil.toPaginationJson(response, list, total);
		} catch (Exception e) {
			this.ReMsg(response, true, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}

	}

	public void saveuser(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter("name") == null ? "" : request
				.getParameter("name");
		String sex = request.getParameter("sex") == null ? "" : request
				.getParameter("sex");
		String nick = request.getParameter("nick") == null ? "" : request
				.getParameter("nick");
		String phone = request.getParameter("phone") == null ? "" : request
				.getParameter("phone");
		String email = request.getParameter("email") == null ? "" : request
				.getParameter("email");
		String departId = request.getParameter("departId") == null ? ""
				: request.getParameter("departId");
		String password = request.getParameter("password") == null ? ""
				: request.getParameter("password");
		String type = request.getParameter("type") == null ? "0" : request
				.getParameter("type");
		try {
			ErrorMsg msg = new ErrorMsg();
			IMUser users = service.findIMUser(name);
			if (users == null) {
				long dds = DateWorkUtil.timechuo();
				String salt = SmsCodeUtil.createRandom(true, 4);
				IMUser user = new IMUser();
				// 头像地址 user.setAvatar(avatar);
				user.setCreated(dds);
				user.setDepartId(Integer.parseInt(departId));
				user.setDomain("0");
				user.setEmail(email);
				user.setName(name);
				user.setNick(nick);
				user.setPassword(MD5Util.md5(MD5Util.md5(password) + salt));
				user.setPhone(phone);
				user.setPush_shield_status(0);
				user.setSalt(salt);
				user.setSex(Integer.parseInt(sex));
				user.setUpdated(dds);
				user.setSign_info("");
				user.setStatus(0);
				user.setType(Integer.parseInt(type));
				user.setAvatar("");
				
				boolean isok = service.saveIMUser(user);
				if (isok) {
					// HttpSession session = request.getSession();
					msg.setHasError(false);
					msg.setLogMsg("success login");
				} else {
					msg.setHasError(true);
					msg.setLogMsg("fail login");
				}
			} else {
				msg.setHasError(true);
				msg.setLogMsg("fail login");
				msg.setExceptionMag("登录名已经存在,请重新输入");
			}

			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSONArray.fromObject(msg).toString());
		} catch (Exception e) {
			this.ReMsg(response, true, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}
	}

	public void ReMsg(HttpServletResponse response, boolean HasError,
			String errormsg, String logmsg) {
		try {
			ErrorMsg msg = new ErrorMsg();
			msg.setHasError(HasError);
			msg.setExceptionMag(errormsg);
			msg.setLogMsg(logmsg);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSONArray.fromObject(msg).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getdepartUser(HttpServletRequest request,
			HttpServletResponse response)

	{
		// TODO Auto-generated method stub
		String page = request.getParameter("page") == null ? "" : request
				.getParameter

				("page");
		String rows = request.getParameter("rows") == null ? "" : request
				.getParameter

				("rows");
		String departId = request.getParameter("departId") ==

		null ? "" : request.getParameter("departId");
		try {
			Map<String, Object> m = new LinkedHashMap<String, Object>();
			m.put("status", 0);
			m.put("departId", departId);
			List<IMUser> list = service.findByPagination(Integer.parseInt(page)

			, Integer.parseInt(rows), m);
//			int total = service.getTotal(m);
//			ResponseUtil.toPaginationJson(response, list, total);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			this.ReMsg(response, true, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void getSearchtUser(HttpServletRequest request,
			HttpServletResponse response)

	{
		// TODO Auto-generated method stub
		String searchUser = request.getParameter("searchUser") == null ? "" : request.getParameter("searchUser");
		try {
			List<IMUser> list = service.findIMUserByUserName(searchUser);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			this.ReMsg(response, true, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}
	}
}
