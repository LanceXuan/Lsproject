package com.lesso.serviceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lesso.dao.Z_User_Power_UserDao;
import com.lesso.daoImpl.Z_User_Power_UserDaoImpl;
import com.lesso.service.Z_User_Power_UserService;
import com.lesso.util.ResponseUtil;

/** * @author  Lance 
 * @date 创建时间：2016年1月5日 上午10:04:37 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public  class Z_User_Power_UserServiceImpl implements Z_User_Power_UserService {
    private Z_User_Power_UserDao dao = new Z_User_Power_UserDaoImpl();
	@Override
	public void saveUPUChose(HttpServletRequest request,
			HttpServletResponse response) {
		Log logs = LogFactory.getLog(Z_User_Power_UserServiceImpl.class);
	       try{
	    	   logs.info("Z_User_Power_UserServiceImpl.saveUPUChose is begin..");
	    	   String userid = request.getParameter("userid") == null?"":request.getParameter("userid");
				String listValue = request.getParameter("listValue") == null?"":request.getParameter("listValue");
				String ztype = request.getParameter("ztype") == null?"":request.getParameter("ztype");
				String account_source_type ="1";
				String account_target_type ="2";
				logs.info("userid is "+userid);
				logs.info("listvalue is "+listValue);
				logs.info("ztype is "+ztype);
				String[] arr = listValue.split(",");
				//更新前，先把原来的数据删除掉 20160114
				boolean iscan = dao.deleteZ_User_Power_User(userid, account_source_type, "", "");
				logs.info("first delete is "+iscan);
				//删除以后，重新把数据插入
				boolean allsave = true;
				for(int i=0;i<arr.length;i++){
					boolean hao = dao.saveZ_User_Power_User(userid, account_source_type, arr[i], account_target_type);
					if(!hao){
						allsave = false;
						logs.info("插入错误的数据是account_source-->"+userid+"@account_targe-->"+arr[i]+"ztype-->"+ztype);
					}
				}
				if(allsave){
					ResponseUtil.ReMsg(response, !allsave, "操作成功", "操作成功");
				}else{
					ResponseUtil.ReMsg(response, !allsave, "操作失败", "内部错误A");
				}
				 
	       }catch(Exception e){
	    	   
	       }
		
	}

}
