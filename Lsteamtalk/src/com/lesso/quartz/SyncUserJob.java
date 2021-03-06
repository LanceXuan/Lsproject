package com.lesso.quartz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.lesso.beans.IMDepart;
import com.lesso.beans.IMUser;
import com.lesso.dao.IMDepartDao;
import com.lesso.dao.IMUserDao;
import com.lesso.daoImpl.IMDepartDaoImpl;
import com.lesso.daoImpl.IMUserDaoImpl;
import com.lesso.util.SqlserverDBUtils;
import com.lesso.util.StringUtil;

/** * @author  Lance 
 * @date 创建时间：2016年1月13日 上午9:08:10 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class SyncUserJob implements Job{
	private IMUserDao dao = new IMUserDaoImpl();
	private IMDepartDao daoa = new IMDepartDaoImpl();
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Log logs = LogFactory.getLog(SyncUserJob.class);
		try{
			Connection conn = SqlserverDBUtils.createConn();
//			String sql = "select * ,(select OAZH from mst_oa_account i where i.SAPGH = m.SAPGH)"
//					+ " as oazh from MST_SAP_PERNR m where GSDM ='1010' and STAT2 = 3 "
//					+ "and RSZFW in('1001','1002','1003','0001','0002','0003')";
			String sql ="select * ,(select OAZH from mst_oa_account i where i.SAPGH = m.SAPGH)"
					+ " as oazh from MST_SAP_PERNR m where STAT2 = 3 ";
			PreparedStatement ps = SqlserverDBUtils.getPs(conn, sql);
 			ResultSet rs = ps.executeQuery();
			List<IMUser> list = new ArrayList<IMUser>();
 			while(rs.next()){
 				String xb = rs.getString("xb"); 
 				String name = "6"+rs.getString("oazh");
 				String oanum = rs.getString("oazh");
 				String domain = "0"; 
 				String nick = rs.getString("xm"); 
 				String password = "8ef6ab5f0b3a51001d2c5707c96ba3d8"; 
 				String salt = "2128"; 
 				String phone = ""; 
 				String email = ""; 
 				int departId = 0; 
 				int status = 0; 
 				int push_shield_status = 0; 
 				String sign_info = "";  
 				int type = 1;  
 				String sap_vkorg = rs.getString("gsdm"); 
 				String sap_kunnr = "";  
 				String sap_kunnr_father= ""; 
 				String sapbm = rs.getString("bm"); 
 				String sapgh = rs.getString("sapgh"); 
 				int sex =0;
 				if("男".equals(xb)){
 					sex=1;
 				}else if("女".equals(xb)){
 					sex=2;
 				}
 				logs.info("OA帐号是"+name);
 				logs.info("sap工号是"+sapgh);
 				IMUser imuser =null;
 				if(StringUtil.isNotEmpty(oanum)){
 				   imuser = dao.findIMUser(name);
 				}else{
 				   imuser = dao.findIMUser(sapgh);
 				}
 				if(imuser !=null ){
 				  if(!nick.equals(imuser.getNick()) || !(sex==imuser.getSex()) || !sap_vkorg.equals(imuser.getSap_vkorg())
 						  || !sapbm.equals(imuser.getSapbm()) || !sapgh.equals(imuser.getSapgh()) || (0==imuser.getDepartId())){
 					  list.add(imuser);
 					 Map<String ,Object> m = new LinkedHashMap<String ,Object>();
 					 m.put("nick", nick);
 					 m.put("sex", sex);
 					 m.put("sap_vkorg", sap_vkorg);
 					 m.put("sapbm", sapbm);
 					 m.put("sapgh", sapgh);
 					if(StringUtil.isNotEmpty(oanum)){
 					m.put("oanum", oanum);
 					}
 					  dao.syncupdateuser(m, imuser.getId());
 					 logs.info(nick+"sap工号"+sapgh+"更新完毕");
 				  }else{
 					  logs.info(nick+"sap工号"+sapgh+"不需要更新");
 				  }
 			    }else if(StringUtil.isNotEmpty(oanum)){
 			    	 int df= dao.syncuser(sex, name, domain, nick, password, salt, phone, email, departId, status, 
  				    		push_shield_status, sign_info, type, sap_vkorg, sap_kunnr, sap_kunnr_father, sapbm, sapgh,oanum);
  				   logs.info("生成ID是"+df);
  				   System.out.println("生成ID是"+df);
  				   if(df>0){
  					   IMUser imu = new IMUser();
  					   imu.setId(df);
  					   imu.setSapbm(sapbm);
  					   imu.setSapgh(sapgh);
  					   imu.setName(name);
  					   imu.setNick(nick);
  					   list.add(imu);
  				   }else{
						logs.info("新插入失败！！！人员:"+nick+",代码是"+sapgh);
					}
 			    }else{
 			    	int df= dao.syncuser(sex, sapgh, domain, nick, password, salt, phone, email, departId, status, 
  				    		push_shield_status, sign_info, type, sap_vkorg, sap_kunnr, sap_kunnr_father, sapbm, sapgh,"");
  				   logs.info("生成ID是"+df);
  				   System.out.println("生成ID是"+df);
  				   if(df>0){
  					   IMUser imu = new IMUser();
  					   imu.setId(df);
  					   imu.setSapbm(sapbm);
  					   imu.setSapgh(sapgh);
  					   imu.setName(name);
  					   imu.setNick(nick);
  					   list.add(imu);
  				   }else{
						logs.info("新插入失败！！！人员:"+nick+",代码是"+sapgh);
					}
 			    }
 		   }
             for(int i=0;i<list.size();i++){
            		IMDepart imdp = daoa.findbySapcode(list.get(i).getSapbm());
    				if(imdp !=null){
    					dao.updateIMUser("", "", "", "", "", String.valueOf(imdp.getId()), "", list.get(i).getId(), -1);
    					logs.info("ID是"+list.get(i).getId()+"的用户,部门id是"+imdp.getId());
    					System.out.println("ID是"+list.get(i).getId()+"的用户,部门id是"+imdp.getId());
    				}
             }
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
	public static void main(String[] args) {
		IMUserDao dao = new IMUserDaoImpl();
		 IMDepartDao daoa = new IMDepartDaoImpl();
			Log logs = LogFactory.getLog(SyncUserJob.class);
			try{
				Connection conn = SqlserverDBUtils.createConn();
				String sql ="select * ,(select OAZH from mst_oa_account i where i.SAPGH = m.SAPGH)"
						+ " as oazh from MST_SAP_PERNR m where STAT2 = 3 ";
				PreparedStatement ps = SqlserverDBUtils.getPs(conn, sql);
	 			ResultSet rs = ps.executeQuery();
				List<IMUser> list = new ArrayList<IMUser>();
	 			while(rs.next()){
	 				String xb = rs.getString("xb"); 
	 				String name = "6"+rs.getString("oazh");
	 				String oanum = rs.getString("oazh");
	 				String domain = "0"; 
	 				String nick = rs.getString("xm"); 
	 				String password = "8ef6ab5f0b3a51001d2c5707c96ba3d8"; 
	 				String salt = "2128"; 
	 				String phone = ""; 
	 				String email = ""; 
	 				int departId = 0; 
	 				int status = 0; 
	 				int push_shield_status = 0; 
	 				String sign_info = "";  
	 				int type = 1;  
	 				String sap_vkorg = rs.getString("gsdm"); 
	 				String sap_kunnr = "";  
	 				String sap_kunnr_father= ""; 
	 				String sapbm = rs.getString("bm"); 
	 				String sapgh = rs.getString("sapgh"); 
	 				int sex =0;
	 				if("男".equals(xb)){
	 					sex=1;
	 				}else if("女".equals(xb)){
	 					sex=2;
	 				}
	 				logs.info("OA帐号是"+name);
	 				logs.info("sap工号是"+sapgh);
	 				IMUser imuser =null;
	 				if(StringUtil.isNotEmpty(oanum)){
	 				   imuser = dao.findIMUser(name);
	 				}else{
	 				   imuser = dao.findIMUser(sapgh);
	 				}
	 				if(imuser !=null ){
	 				  if( (0==imuser.getDepartId()) && 1==imuser.getType()){
	 					  list.add(imuser);
	 					 System.out.println(nick+"sap工号"+sapgh+"更新完毕");
	 				  }else{
	 					  logs.info(nick+"sap工号"+sapgh+"不需要更新");
	 					 System.out.println(nick+"sap工号"+sapgh+"不需要更新");
	 				  }
	 			    }
	 		   }
	             for(int i=0;i<list.size();i++){
	            		IMDepart imdp = daoa.findbySapcode(list.get(i).getSapbm());
	    				if(imdp !=null){
	    					dao.updateIMUser("", "", "", "", "", String.valueOf(imdp.getId()), "", list.get(i).getId(), -1);
	    					logs.info("ID是"+list.get(i).getId()+"的用户,部门id是"+imdp.getId());
	    					System.out.println("ID是"+list.get(i).getId()+"的用户,部门id是"+imdp.getId());
	    				}
	             }
			}catch(Exception e){
				e.printStackTrace();
			}
		
	}
}
