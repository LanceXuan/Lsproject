package com.lesso.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



















import com.lesso.beans.ErrorMsg;
import com.lesso.beans.IMDepart;
import com.lesso.beans.IMUser;
import com.lesso.model.TreeDTO;
import com.lesso.service.IMDepartService;
import com.lesso.service.IMUserService;
import com.lesso.serviceImpl.IMDepartServiceImpl;
import com.lesso.serviceImpl.IMUserServiceImpl;
import com.lesso.util.DateWorkUtil;
import com.lesso.util.MD5Util;
import com.lesso.util.SmsCodeUtil;
import com.lesso.util.TreeDtoUtil;

public class DepartAction extends HttpServlet{
	private IMDepartService service = new IMDepartServiceImpl();
	private IMUserService imuserService = new IMUserServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method") == null?"":request.getParameter("method");
		if("getdep".equals(method)){
			Map<String ,Object> m = new LinkedHashMap<String ,Object>();
			String id = request.getParameter("id") == null?"":request.getParameter("id");
			if(!"".equals(id)){
				if(Integer.parseInt(id)>0){
					m.put("parentid", id);
				}
			}
			List<IMDepart> list = service.getDepList(-1, -1, m);
			String json = this.createTreeJson(list);
			System.out.println(json);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json");
		    response.getWriter().write(JSONArray.fromObject(json).toString());
	    }else if("save".equals(method)){
	    	save(request, response);
	    }else if("delete".equals(method)){
	    	delete(request, response);
	    }else if("TreeDept".equals(method)){
	    	TreeDept(request, response);
	    }else if("updateleader".equals(method)){
	    	updateleader(request, response);
	    }else if("updatedep".equals(method)){
	    	updatedep(request, response);
	    }
	}
	
	
	
	
	
	private void updatedep(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			String id = request.getParameter("id") == null?"0":request.getParameter("id");
			String name = request.getParameter("name") == null?"":request.getParameter("name");
			String parentId = request.getParameter("parentId") == null?"-1":request.getParameter("parentId");
			String priority = request.getParameter("priority") == null?"":request.getParameter("priority");
			String leader = request.getParameter("leader") == null?"0":request.getParameter("leader");
			boolean isok = service.update(Integer.parseInt(id), name, Integer.parseInt(priority), Integer.parseInt(parentId), 0, Integer.parseInt(leader));
			 ErrorMsg msg = new ErrorMsg();
	           if(isok){
					msg.setHasError(false);
					msg.setLogMsg("success updateleader");
				}else{
					msg.setHasError(true);
					msg.setLogMsg("fail updateleader");
				}
				response.setContentType("text/html;charset=utf-8");
			    response.getWriter().write(JSONArray.fromObject(msg).toString());
		}catch(Exception e){
			this.ReMsg(response, true, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}
		
	}

	private void updateleader(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			String departid = request.getParameter("departid") == null?"":request.getParameter("departid");
			String leader = request.getParameter("leader") == null?"":request.getParameter("leader");
			boolean isok = service.updateleader(Integer.parseInt(departid),Integer.parseInt(leader));
			 ErrorMsg msg = new ErrorMsg();
	           if(isok){
					msg.setHasError(false);
					msg.setLogMsg("success updateleader");
				}else{
					msg.setHasError(true);
					msg.setLogMsg("fail updateleader");
				}
				response.setContentType("text/html;charset=utf-8");
			    response.getWriter().write(JSONArray.fromObject(msg).toString());
		}catch(Exception e){
			this.ReMsg(response, true, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}
		
	}

	private void TreeDept(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			Map<String ,Object> m = new LinkedHashMap<String ,Object>();
			String id = request.getParameter("id") == null?"":request.getParameter("id");
			if(!"".equals(id)){
				if(Integer.parseInt(id)>0){
					m.put("parentid", id);
				}
			}else{
				//m.put("parentid", 0);
			}
			List<TreeDTO> list = service.getTreeDTO(m);
////			String json = this.createTreeJson(list);
////			System.out.println(json);
//			response.setCharacterEncoding("UTF-8");
//			response.setContentType("text/json");
//			System.out.println(JSONArray.fromObject(list).toString());
//		    response.getWriter().write(JSONArray.fromObject(list).toString());
			
			String json = TreeDtoUtil.createTreeJson(list);
			System.out.println(json);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json");
		    response.getWriter().write(JSONArray.fromObject(json).toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		try{
		     String id = request.getParameter("id") == null?"0":request.getParameter("id");
		     if(!"".equals(id) && Integer.parseInt(id)>0){
		    	 Map<String ,Object> m = new LinkedHashMap<String ,Object>();
		    	 m.put("parentid", id);
		    	 List<IMDepart> list = service.getDepList(-1, -1, m);
		    	 List<IMUser> imuserlist = imuserService.findIMUserBydepartId(Integer.parseInt(id));
		    	 if(list.size()>0){
		    		 this.ReMsg(response, true, "此部门有子部门,不能删除", "此部门有子部门,不能删除");
		    	 }else if (imuserlist.size()>0){
		    		 this.ReMsg(response, true, "此部门有用户,不能删除", "此部门有用户,不能删除");
		    	 }else{
		    		boolean isok = service.delete(Integer.parseInt(id));
		    		this.ReMsg(response, false, "操作成功", "成功删除");
		    	 }
		     }else{
		    	 this.ReMsg(response, true, "id不能为空", "id不能为空");
		     }
		}catch(Exception e){
			this.ReMsg(response, true, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}
		
	}

	private void save(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name") == null?"":request.getParameter("name");
		String parentId = request.getParameter("parentId") == null?"":request.getParameter("parentId");
		String priority = request.getParameter("priority") == null?"":request.getParameter("priority");
		String leader = request.getParameter("leader") == null?"0":request.getParameter("leader");
		String sapcode = request.getParameter("sapcode") == null?"":request.getParameter("sapcode");
        try{
        	long dds = DateWorkUtil.timechuo();
           boolean isok = service.saveDepart(name, Integer.parseInt(priority), Integer.parseInt(parentId), 0, dds, dds,Integer.parseInt(leader),sapcode);
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
        	this.ReMsg(response, true, e.getMessage(), e.getMessage());
			e.printStackTrace();
        }	
		
	}

	
	
	
	
	
	
	
	
	/**
	   * 创建一颗树，以json字符串形式返回
	   * @param list 原始数据列表
	   * @return 树
	   */
	  private String createTreeJson(List<IMDepart> list) {
	  try{
	    JSONArray rootArray = new JSONArray();
	    for (int i=0; i<list.size(); i++) {
	    	IMDepart resource = list.get(i);
	      if ( resource.getParentid() ==0) {
	        JSONObject rootObj = createBranch(list, resource);
	        rootArray.add(rootObj);
	      }
	    }
	    
	    return rootArray.toString();
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return "error";
	    }
	  }
	  
	  /**
	   * 递归创建分支节点Json对象
	   * @param list 创建树的原始数据
	   * @param currentNode 当前节点
	   * @return 当前节点与该节点的子节点json对象
	   */
	  private JSONObject createBranch(List<IMDepart> list, IMDepart currentNode) {
	     try{
		  /*
	     * 将javabean对象解析成为JSON对象
	     */
	    JSONObject currentObj = JSONObject.fromObject(currentNode);
	    JSONArray childArray = new JSONArray();
	    /*
	     * 循环遍历原始数据列表，判断列表中某对象的父id值是否等于当前节点的id值，
	     * 如果相等，进入递归创建新节点的子节点，直至无子节点时，返回节点，并将该
	     * 节点放入当前节点的子节点列表中
	     */
	    for (int i=0; i<list.size(); i++) {
	    	IMDepart newNode = list.get(i);
	      if ( newNode.getParentid().compareTo(currentNode.getId()) == 0) {
	        JSONObject childObj = createBranch(list, newNode);
	        childArray.add(childObj);
	      }
	    }
	    
	    /*
	     * 判断当前子节点数组是否为空，不为空将子节点数组加入children字段中
	     */
	    if (!childArray.isEmpty()) {
	      currentObj.put("children", childArray);
	    }
	    
	    return currentObj;
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return null;
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
