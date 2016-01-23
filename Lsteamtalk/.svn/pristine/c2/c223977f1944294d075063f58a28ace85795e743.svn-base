package com.lesso.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lesso.beans.Z_User_Power_User;
import com.lesso.beans.Z_User_Vkorg;
import com.lesso.dao.Z_User_Power_UserDao;
import com.lesso.dao.Z_User_VkorgDao;
import com.lesso.daoImpl.Z_User_Power_UserDaoImpl;
import com.lesso.daoImpl.Z_User_VkorgDaoImpl;
import com.lesso.service.Z_User_VkorgService;
import com.lesso.util.ResponseUtil;
import com.lesso.util.StringUtil;

/** * @author  Lance 
 * @date 创建时间：2016年1月5日 上午10:05:23 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class Z_User_VkorgServiceImpl implements Z_User_VkorgService {
       private Z_User_VkorgDao dao = new Z_User_VkorgDaoImpl();
       private Z_User_Power_UserDao daoa = new Z_User_Power_UserDaoImpl();
	@Override
	public void saveUserVkorg(HttpServletRequest request,
			HttpServletResponse response) {
		Log logs = LogFactory.getLog(Z_User_VkorgServiceImpl.class);
		try{
			logs.info("Z_User_VkorgServiceImpl.saveUserVkorg is begin..");
			String userid = request.getParameter("userid") == null?"":request.getParameter("userid");
			String listValue = request.getParameter("listValue") == null?"":request.getParameter("listValue");
			String ztype = request.getParameter("ztype") == null?"":request.getParameter("ztype");
			logs.info("userid is "+userid);
			logs.info("listvalue is "+listValue);
			logs.info("ztype is "+ztype);
			String[] arr = null;
			if(StringUtil.isNotEmpty(listValue)){
				arr = listValue.split(",");
			}
			//更新前，先把原来的数据删除掉
			boolean iscan = dao.deleteZ_User_Vkorg(userid, "", ztype);
			logs.info("first delete is "+iscan);
			//删除以后，重新把数据插入
			boolean allsave = true;
				if(arr != null){
				for(int i=0;i<arr.length;i++){
					boolean hao = dao.saveZ_User_Vkorg(userid, arr[i], ztype);
					if(!hao){
						allsave = false;
						logs.info("插入错误的数据是account-->"+userid+"@vkorg-->"+arr[i]+"ztype-->"+ztype);
					}
				}
			}
			if(allsave){
				ResponseUtil.ReMsg(response, !allsave, "操作成功", "操作成功");
			}else{
				ResponseUtil.ReMsg(response, !allsave, "操作失败", "内部错误A");
			}
			 
		}catch(Exception e){
			e.printStackTrace();
			logs.info("错误B是"+e.getMessage());
			ResponseUtil.ReMsg(response, true, "操作失败", e.getMessage());
		}
		
	}
	@Override
	public void copyusers(HttpServletRequest request,
			HttpServletResponse response) {
		Log logs = LogFactory.getLog(Z_User_VkorgServiceImpl.class);
		try{
			logs.info("Z_User_VkorgServiceImpl.copyusers");
			String userid = request.getParameter("userid") == null?"":request.getParameter("userid");
			String listValue = request.getParameter("listValue") == null?"":request.getParameter("listValue");
			String ztype = request.getParameter("ztype") == null?"":request.getParameter("ztype");
			logs.info("userid is "+userid);
			logs.info("listvalue is "+listValue);
			logs.info("ztype is "+ztype);
			String[] arr = null;
			if(StringUtil.isNotEmpty(listValue)){
				arr = listValue.split(",");
			}
			//更新前，先把原来的数据删除掉
			for(int i=0;i<arr.length;i++){
			  boolean df= dao.deleteZ_User_Vkorg(arr[i], "", ztype);
			  boolean ds = daoa.deleteZ_User_Power_User(arr[i], "1", "", "");
			  logs.info("用户"+arr[i]+"deleteZ_User_Vkorg is "+df+" deleteZ_User_Power_User is "+ds);
			}
			//删除结束
			//查询权限开始
			List<Z_User_Vkorg> uservkorglist = dao.getZ_User_VkorgList(userid, "", ztype, -1, -1);
			Map<String,Object> m = new HashMap<String,Object>();
	        m.put("account_source", userid);
	        m.put("account_source_type", 1);
			List<Z_User_Power_User> userpoweruserlist = daoa.getZUPUList(-1, -1, m);
			boolean allsave = true;
			//查询结束
			//复制权限开始
			if(uservkorglist !=null && arr != null){
				for(int i=0;i<arr.length;i++){
					for(int j=0;j<uservkorglist.size();j++){
						boolean hao = dao.saveZ_User_Vkorg(arr[i], uservkorglist.get(j).getVkorg(),uservkorglist.get(j).getZtype());
						if(!hao){
							allsave = false;
							logs.info("插入错误的数据是account-->"+arr[i]+"@vkorg-->"+uservkorglist.get(j).getVkorg()+"ztype-->"+uservkorglist.get(j).getZtype());
						}
				    }
				}
			}
			if(userpoweruserlist !=null && arr != null){
				for(int a=0;a<arr.length;a++){
					for(int b=0; b<userpoweruserlist.size();b++){
						boolean haoa = daoa.saveZ_User_Power_User(arr[a], userpoweruserlist.get(b).getAccount_source_type(),
								userpoweruserlist.get(b).getAccount_target(), userpoweruserlist.get(b).getAccount_target_type());
						if(!haoa){
							allsave = false;
							logs.info("插入错误的数据是Account_source-->"+arr[a]+"@Account_source_type-->"+userpoweruserlist.get(b).getAccount_source_type()+""
									+ "Account_target-->"+userpoweruserlist.get(b).getAccount_target());
						}
					}
				}
			}
			//复制权限结束
			
			if(allsave){
				ResponseUtil.ReMsg(response, !allsave, "操作成功", "操作成功");
			}else{
				ResponseUtil.ReMsg(response, !allsave, "操作失败", "内部错误A");
			}
			 
		}catch(Exception e){
			e.printStackTrace();
			logs.info("错误B是"+e.getMessage());
			ResponseUtil.ReMsg(response, true, "操作失败", e.getMessage());
		}
		
	}

}
