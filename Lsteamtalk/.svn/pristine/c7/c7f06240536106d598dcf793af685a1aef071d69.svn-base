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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lesso.beans.IMDiscovery;
import com.lesso.beans.Z_Depart_Type_Right;
import com.lesso.dao.Z_Depart_Type_RightDao;
import com.lesso.util.DBUtils;
import com.lesso.util.DateWorkUtil;
import com.lesso.util.StringUtil;

/** * @author  Lance 
 * @date 创建时间：2016年1月5日 上午9:29:37 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class Z_Depart_Type_RightDaoImpl extends BaseDaoImpl<Z_Depart_Type_Right> 
 implements Z_Depart_Type_RightDao {

	@Override
	public List<Z_Depart_Type_Right> getZ_Depart_Type_RightList(int ztype) {
		Log logs = LogFactory.getLog(Z_Depart_Type_RightDaoImpl.class);
		try{
			logs.info("Z_Depart_Type_RightDaoImpl.getZ_Depart_Type_RightList is begin");
			Connection conn = DBUtils.createConn();
	 		String sql = " select * from Z_DEPART_TYPE_RIGHT ";
	 		if(ztype>0){
	 			sql +=" where ztype ="+ztype;
	 		}
	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ResultSet rs = ps.executeQuery();
	 		
	 		List<Z_Depart_Type_Right> ulist = new ArrayList<Z_Depart_Type_Right>();
	 		while(rs.next()){
	 			Z_Depart_Type_Right mp = new Z_Depart_Type_Right();
	            mp.setDepartId(Integer.parseInt(rs.getString("departId")));
	            mp.setDepartId_Text(rs.getString("departId_Text"));
	            mp.setZtype(Integer.parseInt(rs.getString("ztype")));
	            mp.setZtype_text(rs.getString("ztype_text"));
			    ulist.add(mp);
	 		}
	 		return ulist;
		}catch(Exception e){
			e.printStackTrace();
			logs.info("Z_Depart_Type_RightDaoImpl.getZ_Depart_Type_RightList Error is"+e.getMessage());
			return null;
		}
	}

	@Override
	public boolean saveDepartTypeRight(int departId, String departId_Text,
			int ztype, String ztype_text) {
		try{
			Connection conn = DBUtils.createConn();
			String sql = "insert into Z_DEPART_TYPE_RIGHT (departId,departId_TEXT,ZTYPE,ZTYPE_TEXT) "
					+ "values ('"+departId+"','"+departId_Text+"','"+ztype+"','"+ztype_text+"')";
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
	public boolean updateDepartTypeRight(int departId, String departId_Text,
			int ztype, String ztype_text) {
		try{
			Connection conn = DBUtils.createConn();
			String sql="Update Z_DEPART_TYPE_RIGHT set ";
			boolean can  = false;
			if(StringUtil.isNotEmpty(departId_Text)){
				sql +=" departId_TEXT='"+departId_Text+"',";
				can  = true;
			}
			if(ztype>=0){
				sql +=" ztype='"+ztype+"',";
				can  = true;
			}
			if(StringUtil.isNotEmpty(ztype_text)){
				sql +=" ZTYPE_TEXT='"+ztype_text+"',";
				can  = true;
			}
			if(departId>=0 && can){
				sql = sql.substring(0, sql.length()-1);
				sql += " where departId = '"+departId+"'";
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
	public boolean deleteDepartTypeRight(int departId, int ztype) {
		try{
			Connection conn = DBUtils.createConn();
			String sql="delete from Z_DEPART_TYPE_RIGHT where departId = '"+departId+"' and ztype = '"+ztype+"'";
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
	public List<Z_Depart_Type_Right> findByPagination(int currentPage,
			int pageSize, Map<String, Object> m) throws Exception {
		try{
			Connection conn = DBUtils.createConn();
	 		String sql = " select * from Z_DEPART_TYPE_RIGHT  where 1=1 ";
	 		
	 		Set<Entry<String, Object>> set = m.entrySet();
	 		Iterator io = set.iterator();
	 		while (io.hasNext()) {
	 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
	 			if("departId".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("departId_TEXT".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
	 			}
	 			if("ZTYPE".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("ZTYPE_TEXT".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
	 			}
	 			
	 		}
	 		if(currentPage >0 && pageSize>0){
	 		    sql = sql +" limit " + (currentPage-1)*pageSize +" , "  + pageSize ;
	 		}
	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ResultSet rs = ps.executeQuery();
	 		
	 		List<Z_Depart_Type_Right> ulist = new ArrayList<Z_Depart_Type_Right>();
	 		while(rs.next()){
	 			Z_Depart_Type_Right mp = new Z_Depart_Type_Right();
                mp.setDepartId(Integer.parseInt(rs.getString("departId")));
                mp.setDepartId_Text(rs.getString("departId_TEXT"));
                mp.setZtype(Integer.parseInt(rs.getString("ZTYPE")));
                mp.setZtype_text(rs.getString("ZTYPE_TEXT"));
				ulist.add(mp);
	 		}
	 		return ulist;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getTotal(Map<String, Object> m) throws Exception {
		Connection conn = DBUtils.createConn();
 		String sql = " select count(*) from Z_DEPART_TYPE_RIGHT  where 1=1 ";
 		
 		Set<Entry<String, Object>> set = m.entrySet();
 		Iterator io = set.iterator();
 		while (io.hasNext()) {
 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
 			if("departId".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("departId_TEXT".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
 			}
 			if("ZTYPE".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("ZTYPE_TEXT".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
 			}
 		}
 		PreparedStatement ps = DBUtils.getPs(conn, sql);
 		ResultSet rs = ps.executeQuery();		
 		int count = 0 ;
 		if(rs.next()){
 			
 			count = rs.getInt(1);
 		}
 		return count;
	}

}
