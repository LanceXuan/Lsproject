package com.lesso.quartz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.lesso.beans.Z_Vkorg;
import com.lesso.dao.Z_VkorgDao;
import com.lesso.daoImpl.Z_VkorgDaoImpl;
import com.lesso.util.SqlserverDBUtils;

/** * @author  Lance 
 * @date 创建时间：2016年1月13日 下午4:08:16 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class SyncVkorgJob implements Job{
    private Z_VkorgDao dao = new Z_VkorgDaoImpl();
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Log logs = LogFactory.getLog(SyncVkorgJob.class);
		try{
			logs.info("同步Z_Vkorg开始");
			//先删除全部，再全部同步过来
			boolean dkf = dao.deleteAllZ_Vkorg();
			logs.info("先删除全部，再全部同步过来是否成功"+dkf);
			Connection conn = SqlserverDBUtils.createConn();
			String sql = "select * from MST_VKORG order by ORDINAL";
			PreparedStatement ps = SqlserverDBUtils.getPs(conn, sql);
 			ResultSet rs = ps.executeQuery();
 			while(rs.next()){
 				String vkorg = rs.getString("vkorg");
 				String vkorgname = rs.getString("butxt");
 				logs.info("公司代码是"+vkorg);
 				logs.info("公司名称是"+vkorgname);
 //				Z_Vkorg zk = dao.findZ_Vkorg(vkorg, "");
// 				if(zk !=null){
//	 				if(!vkorgname.equals(zk.getVkorg_name())){
//	 				    dao.updateZ_Vkorg(vkorg, zk.getVkorg_name());
//	 				}else{
//	 					
//	 				}
// 				}else{
// 					dao.saveZ_Vkorg(vkorg, vkorgname);
// 				}
 				dao.saveZ_Vkorg(vkorg, vkorgname);
 			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Z_VkorgDao dao = new Z_VkorgDaoImpl();
		Log logs = LogFactory.getLog(SyncVkorgJob.class);
		try{
			logs.info("同步Z_Vkorg开始");
			//先删除全部，再全部同步过来
			boolean dkf = dao.deleteAllZ_Vkorg();
			logs.info("先删除全部，再全部同步过来是否成功"+dkf);
			Connection conn = SqlserverDBUtils.createConn();
			String sql = "select * from MST_VKORG order by ORDINAL";
			PreparedStatement ps = SqlserverDBUtils.getPs(conn, sql);
 			ResultSet rs = ps.executeQuery();
 			while(rs.next()){
 				String vkorg = rs.getString("vkorg");
 				String vkorgname = rs.getString("butxt");
 				logs.info("公司代码是"+vkorg);
 				logs.info("公司名称是"+vkorgname);
 //				Z_Vkorg zk = dao.findZ_Vkorg(vkorg, "");
// 				if(zk !=null){
//	 				if(!vkorgname.equals(zk.getVkorg_name())){
//	 				    dao.updateZ_Vkorg(vkorg, zk.getVkorg_name());
//	 				}else{
//	 					
//	 				}
// 				}else{
// 					dao.saveZ_Vkorg(vkorg, vkorgname);
// 				}
 				dao.saveZ_Vkorg(vkorg, vkorgname);
 			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
