package com.lesso.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.lesso.beans.Z_Vkorg;
import com.lesso.dao.Z_VkorgDao;
import com.lesso.util.DBUtils;
import com.lesso.util.DateWorkUtil;
import com.lesso.util.StringUtil;

/** * @author  Lance 
 * @date 创建时间：2016年1月5日 上午9:43:55 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class Z_VkorgDaoImpl extends BaseDaoImpl<Z_Vkorg> implements Z_VkorgDao{

	@Override
	public List<Z_Vkorg> getList() {
		try{
			Connection conn = DBUtils.createConn();
	 		String sql = " select * from Z_VKORG";
	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ResultSet rs = ps.executeQuery();
	 		
	 		List<Z_Vkorg> ulist = new ArrayList<Z_Vkorg>();
	 		while(rs.next()){
	 			Z_Vkorg mp = new Z_Vkorg();
	            mp.setVkorg(rs.getString("vkorg"));
	            mp.setVkorg_name(rs.getString("vkorg_name"));
			    ulist.add(mp);
	 		}
	 		return ulist;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
 		
 		
	}

	@Override
	public boolean saveZ_Vkorg(String vkorg, String vkorgname) {
		try{
			Connection conn = DBUtils.createConn();
			String sql = "insert into Z_VKORG (VKORG,VKORG_NAME) values ('"+vkorg+"','"+vkorgname+"')";
			Statement st = conn.createStatement();
			int ds = st.executeUpdate(sql);
			DBUtils.close(conn);
			if (st != null) {
				st.close();
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Z_Vkorg findZ_Vkorg(String vkorg, String vkorgname) {
		try{
			Connection conn = DBUtils.createConn();
	 		String sql = " select * from Z_VKORG where 1=1 and";
	 		if(StringUtil.isNotEmpty(vkorg)){
	 			sql += " and VKORG='"+vkorg+"' ";
	 		}
	        if(StringUtil.isNotEmpty(vkorgname)){
	        	sql += " and VKORG_NAME like '%"+vkorgname+"%' ";
	 		}
	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ResultSet rs = ps.executeQuery();
	 		Z_Vkorg mp = null;
	 		while(rs.next()){
	 			 mp = new Z_Vkorg();
	            mp.setVkorg(rs.getString("vkorg"));
	            mp.setVkorg_name(rs.getString("vkorg_name"));
	 		}
	 		return mp;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateZ_Vkorg(String vkorg,String vkorgname) {
		try{
			Connection conn = DBUtils.createConn();
			String now = DateWorkUtil.stringToDatenow();
			String sql="Update Z_VKORG set ";
            if(StringUtil.isNotEmpty(vkorgname)){
            	sql +=" VKORG_NAME='"+vkorgname+"'";
	 		sql += " where VKORG = '"+vkorg+"'";
	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ps.executeUpdate();
			DBUtils.close(ps);
			DBUtils.close(conn);
			return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteZ_Vkorg(String vkorg) {
		try{
			Connection conn = DBUtils.createConn();
			String sql="delete from  Z_VKORG  where VKORG = '"+vkorg+"'";
	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ps.executeUpdate();
			DBUtils.close(ps);
			DBUtils.close(conn);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteAllZ_Vkorg() {
		try{
			Connection conn = DBUtils.createConn();
			String sql="delete from  Z_VKORG ";
	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ps.executeUpdate();
			DBUtils.close(ps);
			DBUtils.close(conn);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
