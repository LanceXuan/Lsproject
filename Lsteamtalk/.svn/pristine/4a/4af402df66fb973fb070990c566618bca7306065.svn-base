package com.lesso.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONArray;

import com.lesso.beans.Z_User_Vkorg;
import com.lesso.beans.Z_Vkorg;
import com.lesso.dao.Z_User_VkorgDao;
import com.lesso.dao.Z_VkorgDao;
import com.lesso.daoImpl.Z_User_VkorgDaoImpl;
import com.lesso.daoImpl.Z_VkorgDaoImpl;
import com.lesso.model.UserVkorg;
import com.lesso.service.Z_VkorgService;

/** * @author  Lance 
 * @date 创建时间：2016年1月5日 上午10:05:59 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class Z_VkorgServiceImpl implements Z_VkorgService {
     private Z_VkorgDao dao = new Z_VkorgDaoImpl();
     private Z_User_VkorgDao daoa = new Z_User_VkorgDaoImpl();
	@Override
	public void getAll(HttpServletRequest request, HttpServletResponse response) {
		Log logs = LogFactory.getLog(Z_VkorgServiceImpl.class);
		try {
			String userid = request.getParameter("userid") == null?"":request.getParameter("userid");
			logs.info("Z_VkorgServiceImpl.getAll()方法开始");
            List<Z_Vkorg> list = dao.getList();
            String ztype = "2";
            List<Z_User_Vkorg> lista = daoa.getZ_User_VkorgList(userid, "", ztype, -1, -1);
            List<UserVkorg> listb = new ArrayList<UserVkorg>();
            for(int i=0;i<list.size();i++){
            	UserVkorg mp = new UserVkorg();
    			mp.setVkorg(list.get(i).getVkorg());
    			mp.setVkorg_name(list.get(i).getVkorg_name());
	    			if(lista != null){
	    				boolean ishave = false;
		            	for(int j=0;j<lista.size();j++){
		            		if(list.get(i).getVkorg().equals(lista.get(j).getVkorg())){
		            			ishave = true;
		            		}
		            	}
		            	if(ishave){
		            		mp.setStatus("1");
		            	}else{
		            		mp.setStatus("0");
		            	}
            	}
            	listb.add(mp);
            }
            
            response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSONArray.fromObject(listb).toString());
		} catch (Exception e) {
            e.printStackTrace();
            logs.info("Z_VkorgServiceImpl.getAll() has Error,Error is"+e.getMessage());
		}
	}
 
}
