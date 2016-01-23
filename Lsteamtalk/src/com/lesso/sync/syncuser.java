package com.lesso.sync;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lesso.beans.IMDepart;
import com.lesso.beans.IMUser;
import com.lesso.dao.IMDepartDao;
import com.lesso.dao.IMUserDao;
import com.lesso.daoImpl.IMDepartDaoImpl;
import com.lesso.daoImpl.IMUserDaoImpl;
import com.lesso.util.DateWorkUtil;
import com.lesso.util.SqlserverDBUtils;
import com.lesso.util.StringUtil;

/** * @author  Lance 
 * @date 创建时间：2016年1月13日 上午9:09:55 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class syncuser {
    
	private IMUserDao dao = new IMUserDaoImpl();
	private IMDepartDao daoa = new IMDepartDaoImpl();
	public static void main(String[] args) {
		syncuser syn = new syncuser();
		syn.syncdepart();

	}

	public boolean syncAllUser(){
		Log logs = LogFactory.getLog(syncuser.class);
		try{
			Connection conn = SqlserverDBUtils.createConn();
			String sql = "select * ,(select OAZH from mst_oa_account i where i.SAPGH = m.SAPGH)"
					+ " as oazh from MST_SAP_PERNR m where GSDM ='1010' and STAT2 = 3 "
					+ "and RSZFW in('1001','1002','1003','0001','0002','0003')";
			PreparedStatement ps = SqlserverDBUtils.getPs(conn, sql);
 			ResultSet rs = ps.executeQuery();
 			while(rs.next()){
 				String xb = rs.getString("xb"); 
 				String name = "6"+rs.getString("oazh");  
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
 				if(StringUtil.isNotEmpty(rs.getString("oazh"))){
 				   int df= dao.syncuser(sex, name, domain, nick, password, salt, phone, email, departId, status, 
 				    		push_shield_status, sign_info, type, sap_vkorg, sap_kunnr, sap_kunnr_father, sapbm, sapgh,"");
 				   logs.info("生成ID是"+df);
 				   System.out.println("生成ID是"+df);
 			    }
 		   }

		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
		
	}
	
	public void syncdepart(){
		try{
			Map<String ,Object> m = new LinkedHashMap<String ,Object>();
			m.put("status", "0");
			List<IMUser> list = dao.findByPagination(-1, -1, m);
			for(int i=0;i<list.size();i++){
				IMDepart imdp = daoa.findbySapcode(list.get(i).getSapbm());
				if(imdp !=null){
					dao.updateIMUser("", "", "", "", "", String.valueOf(imdp.getId()), "", list.get(i).getId(), -1);
					System.out.println("ID是"+list.get(i).getId()+"的用户,部门id是"+imdp.getId());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
