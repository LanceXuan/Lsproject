package com.lesso.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lesso.beans.IMUser;
import com.lesso.beans.Z_KNA;
import com.lesso.beans.Z_User_Power_User;
import com.lesso.dao.IMUserDao;
import com.lesso.dao.Z_KNADao;
import com.lesso.dao.Z_User_Power_UserDao;
import com.lesso.daoImpl.IMUserDaoImpl;
import com.lesso.daoImpl.Z_KNADaoImpl;
import com.lesso.daoImpl.Z_User_Power_UserDaoImpl;
import com.lesso.model.UserPowerUser;
import com.lesso.service.Z_KNAService;
import com.lesso.util.StringUtil;

/** * @author  Lance 
 * @date 创建时间：2016年1月5日 上午10:03:53 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class Z_KNAServiceImpl implements Z_KNAService{
    private Z_KNADao dao = new Z_KNADaoImpl(); 
    private Z_User_Power_UserDao daoa = new Z_User_Power_UserDaoImpl();
    private IMUserDao daob = new IMUserDaoImpl();
	@Override
	public void getZ_KNAListchose(HttpServletRequest request,
			HttpServletResponse response) {
		Log logs = LogFactory.getLog(Z_KNAServiceImpl.class);
		try {
			String userid = request.getParameter("userid") == null?"-1":request.getParameter("userid");
			String kunnr = request.getParameter("kunnr") == null?"":request.getParameter("kunnr");
			String kunnrname = request.getParameter("kunnrname") == null?"":request.getParameter("kunnrname");
			logs.info("Z_KNAServiceImpl.getZ_KNAListchose()方法开始");
			Map<String,Object> map = new HashMap<String,Object>();
			if(StringUtil.isNotEmpty(kunnr)){
			   map.put("KUNNR", kunnr);
			}
			if(StringUtil.isNotEmpty(kunnrname)){
			map.put("NAME", kunnrname);
			}
			IMUser imuser = daob.findIMUser(userid);
			if(StringUtil.isNotEmpty(imuser.getSap_vkorg())){
				map.put("VKORG", imuser.getSap_vkorg());
			}
            List<Z_KNA> list = dao.getZ_KNAList(-1, -1, map);
            Map<String,Object> m = new HashMap<String,Object>();
            m.put("account_source", userid);
            m.put("account_source_type", 1);
            List<Z_User_Power_User> lista = daoa.getZUPUList(-1, -1, m);
            List<UserPowerUser> listb = new ArrayList<UserPowerUser>();
            for(int i=0;i<list.size();i++){
            	UserPowerUser mp = new UserPowerUser();
                 mp.setArea(list.get(i).getArea());
                 mp.setArea_name(list.get(i).getArea_name());
                 mp.setKunnr(list.get(i).getKunnr());
                 mp.setLogin_id(list.get(i).getLogin_id());
                 mp.setName(list.get(i).getName());
                 mp.setPwd(list.get(i).getPwd());
                 mp.setVkorg(list.get(i).getVkorg());
	    			if(lista != null){
	    				boolean ishave = false;
		            	for(int j=0;j<lista.size();j++){
		            	//	if(list.get(i).getKunnr().equals(lista.get(j).getAccount_target())){
		            		if(list.get(i).getLogin_id().equals(lista.get(j).getAccount_target())){
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
