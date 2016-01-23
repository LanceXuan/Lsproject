package com.lesso.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lesso.beans.IMUser;
import com.lesso.beans.Z_Depart_Type_Right;
import com.lesso.dao.IMUserDao;
import com.lesso.dao.Z_Depart_Type_RightDao;
import com.lesso.dao.Z_User_Power_UserDao;
import com.lesso.dao.Z_User_VkorgDao;
import com.lesso.daoImpl.IMUserDaoImpl;
import com.lesso.daoImpl.Z_Depart_Type_RightDaoImpl;
import com.lesso.daoImpl.Z_User_Power_UserDaoImpl;
import com.lesso.daoImpl.Z_User_VkorgDaoImpl;
import com.lesso.service.Z_Depart_Type_RightService;
import com.lesso.util.ResponseUtil;

/** * @author  Lance 
 * @date 创建时间：2016年1月5日 上午10:01:34 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class Z_Depart_Type_RightServiceImpl implements Z_Depart_Type_RightService{
    private Z_Depart_Type_RightDao dao = new Z_Depart_Type_RightDaoImpl();
    private Z_User_Power_UserDao daoa = new Z_User_Power_UserDaoImpl();
    private Z_User_VkorgDao daob = new Z_User_VkorgDaoImpl();
    private IMUserDao daoc = new IMUserDaoImpl();
	@Override
	public void initAndSearch(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			String page = request.getParameter("page") == null?"0":request.getParameter("page");
			String rows = request.getParameter("rows") == null?"0":request.getParameter("rows");
			Map<String,Object> m = new HashMap<String,Object>();
			List<Z_Depart_Type_Right> list = dao.findByPagination(Integer.parseInt(page), Integer.parseInt(rows), m);
			int total = dao.getTotal(m);
			ResponseUtil.toPaginationJson(response, list, total);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Override
	public void saveorupdate(HttpServletRequest request,
			HttpServletResponse response) {
		try{
		     String depname = request.getParameter("depname") == null?"":request.getParameter("depname");
		     String departId = request.getParameter("departId") == null?"0":request.getParameter("departId");
		     String ztype = request.getParameter("ztype") == null?"0":request.getParameter("ztype");
		     String type = request.getParameter("type") == null?"":request.getParameter("type");
		     String ztypetext = "";
		     if("2".equals(ztype)){
		    	 ztypetext="经销商";
		     }else if("3".equals(ztype)){
		    	 ztypetext="供应商";
		     }
		     boolean isok = false;
		     if("1".equals(type)){
		    	 isok = dao.saveDepartTypeRight(Integer.parseInt(departId), depname, Integer.parseInt(ztype), ztypetext);
		     }else if("2".equals(type)){
		    	 isok = dao.updateDepartTypeRight(Integer.parseInt(departId), depname, Integer.parseInt(ztype), ztypetext);
		     }
		     ResponseUtil.ReMsg(response, !isok, "", "");
		}catch(Exception e){
			e.printStackTrace();
			ResponseUtil.ReMsg(response, true, e.getMessage(), e.getMessage());
		}
	}
	@Override
	public void deleteZDepartRight(HttpServletRequest request,
			HttpServletResponse response) {
		Log logs = LogFactory.getLog(Z_Depart_Type_RightServiceImpl.class);
		try{
		     String departId = request.getParameter("departId") == null?"0":request.getParameter("departId");
		     String ztype = request.getParameter("ztype") == null?"0":request.getParameter("ztype");
			 logs.info("deleteZDepartRight is begin and departId is "+departId+ "and ztype is "+ztype);
		     boolean isok = dao.deleteDepartTypeRight(Integer.parseInt(departId), Integer.parseInt(ztype));
		     logs.info("deleteDepartTypeRight is "+isok);
		     
		     boolean ipo = true;
		     List<IMUser> list = daoc.findIMUserBydepartId(Integer.parseInt(departId));
		     for(int i = 0; i<list.size();i++){
		    	boolean deleteupu = daoa.deleteZ_User_Power_User(list.get(i).getName(), "1", "", "");
		    	logs.info(list.get(i).getName()+" deleteZ_User_Power_User is" +deleteupu);
		    	boolean deleteuv = daob.deleteZ_User_Vkorg(list.get(i).getName(), "", ztype);
		    	logs.info(list.get(i).getName()+" deleteZ_User_Vkorg is" +deleteupu);
		    	if(deleteupu == false || deleteuv == false){
		    		ipo = false;
		    	}
		     }
		     ResponseUtil.ReMsg(response, !isok&&ipo , "", "");
		}catch(Exception e){
			e.printStackTrace();
			logs.info("Error deleteZDepartRight Exception is "+e.getMessage());
			ResponseUtil.ReMsg(response, true, e.getMessage(), e.getMessage());
		}
		
	}

}
