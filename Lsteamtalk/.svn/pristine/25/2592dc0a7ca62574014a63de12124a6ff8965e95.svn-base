package com.lesso.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.lesso.beans.IMAdmin;
import com.lesso.dao.IMAdminDao;
import com.lesso.util.DBUtils;
import com.lesso.util.StringUtil;

public class IMAdminDaoImpl extends BaseDaoImpl<IMAdmin> implements IMAdminDao{
    public IMAdmin findIMAdmin(String username){
       try{
    	   Connection conn = DBUtils.createConn();
    	   String sql ="select * from IMAdmin where uname= '"+username+"'";
    	   PreparedStatement ps = DBUtils.getPs(conn, sql);
			ResultSet rs = ps.executeQuery();
			IMAdmin mp = new IMAdmin();
			while(rs.next()){
				mp.setCreated(Integer.parseInt(rs.getString("Created")));
				mp.setId(Integer.parseInt(rs.getString("id")));
				mp.setPwd(rs.getString("pwd"));
				mp.setStatus(Integer.parseInt(rs.getString("status")));
				mp.setUname(rs.getString("uname"));
				mp.setUpdated(Integer.parseInt(rs.getString("updated")));
				if(StringUtil.isNotEmpty(rs.getString("iMUserId"))){
				  mp.setIMUserId(Integer.parseInt(rs.getString("iMUserId")));
				}else{
					mp.setIMUserId(0);
				}
			}
			DBUtils.close(ps);
			DBUtils.close(conn);
			return mp;
       }catch(Exception e){
    	   e.printStackTrace();
   		   return null;
       }
    }
    
    public boolean saveIMAdmin(IMAdmin imadmin){
    	try{
    		Connection conn = DBUtils.createConn();
    		String sql ="insert into IMADmin (uname,pwd,status,created,updated)"
    				+ " values('"+imadmin.getUname()+"','"+imadmin.getPwd()+"','"+imadmin.getStatus()+"','"
    						+ imadmin.getCreated()+"','"+imadmin.getUpdated()+"')";
    		Statement st=conn.createStatement();		    				
			boolean ds = st.execute(sql);

			DBUtils.close(conn);
			if(st != null){
				st.close();
			}
			return ds;
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
		
    	
    }
}
