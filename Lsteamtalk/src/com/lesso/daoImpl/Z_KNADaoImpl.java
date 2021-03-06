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

import com.lesso.beans.Z_KNA;
import com.lesso.beans.Z_Vkorg;
import com.lesso.dao.Z_KNADao;
import com.lesso.util.DBUtils;
import com.lesso.util.DateWorkUtil;
import com.lesso.util.StringUtil;

/** * @author  Lance 
 * @date 创建时间：2016年1月5日 上午9:32:03 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class Z_KNADaoImpl extends BaseDaoImpl<Z_KNA> implements Z_KNADao{

	@Override
	public List<Z_KNA> getZ_KNAList(int currentPage, int pageSize,
			Map<String, Object> m) {
		try{
			Connection conn = DBUtils.createConn();
	 		String sql = " select * from Z_KNA  where 1=1 ";

	 		Set<Entry<String, Object>> set = m.entrySet();
	 		Iterator io = set.iterator();
	 		while (io.hasNext()) {
	 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
	 			if("NAME".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
	 			}
	 			if("area_name".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
	 			}
	 			if("KUNNR".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("VKORG".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("LOGIN_ID".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("PWD".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("area".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("sort".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " order by " + me.getValue() ;
	 			}
	 			if("order".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " " + me.getValue();
	 			}			
	 		}
	 		if(currentPage >0 && pageSize>0){
	 		    sql = sql +" limit " + (currentPage-1)*pageSize +" , "  + pageSize ;
	 		}
	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ResultSet rs = ps.executeQuery();
	 		
	 		List<Z_KNA> ulist = new ArrayList<Z_KNA>();
	 		while(rs.next()){
	 			Z_KNA mp = new Z_KNA();
	 			mp.setArea(rs.getString("area"));
	 			mp.setArea_name(rs.getString("area_name"));
	 			mp.setKunnr(rs.getString("kunnr"));
	 			mp.setLogin_id(rs.getString("login_id"));
	 			mp.setName(rs.getString("name"));
	 			mp.setPwd(rs.getString("pwd"));
	 			mp.setVkorg(rs.getString("vkorg"));
				ulist.add(mp);
	 		}
	 		return ulist;
		}catch(Exception e){
			return null;
		}
		
	}

	@Override
	public int getTotal(Map<String, Object> m) throws Exception {
		Connection conn = DBUtils.createConn();
 		String sql = " select count(*) from Z_KNA  where 1=1 ";

 		Set<Entry<String, Object>> set = m.entrySet();
 		Iterator io = set.iterator();
 		while (io.hasNext()) {
 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
 			if("Name".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
 			}
 			if("area_name".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
 			}
 			if("KUNNR".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("VKORG".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("LOGIN_ID".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("PWD".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("area".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("sort".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " order by " + me.getValue() ;
 			}
 			if("order".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " " + me.getValue();
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

	@Override
	public Z_KNA findbyKunrAndVkorg(String kunr, String vkorg) {
		try{
			Connection conn = DBUtils.createConn();
	 		String sql = " select * from Z_KNA where 1=1 ";
	 		if(StringUtil.isNotEmpty(kunr)){
	 			sql += " and KUNNR='"+kunr+"' ";
	 		}
	        if(StringUtil.isNotEmpty(vkorg)){
	        	sql += " and VKORG = '"+vkorg+"' ";
	 		}
	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ResultSet rs = ps.executeQuery();
	 		Z_KNA mp = null;
	 		while(rs.next()){
	 			 mp = new Z_KNA();
	 			mp.setArea(rs.getString("area"));
	 			mp.setArea_name(rs.getString("area_name"));
	 			mp.setKunnr(rs.getString("kunnr"));
	 			mp.setLogin_id(rs.getString("login_id"));
	 			mp.setName(rs.getString("name"));
	 			mp.setPwd(rs.getString("pwd"));
	 			mp.setVkorg(rs.getString("vkorg"));
	 		}
	 		return mp;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updatekua(Map<String, Object> m, String kunr, String vkorg) {
		try{
	 		long dds = DateWorkUtil.timechuo();

	 		String sql = "update Z_KNA set ";
            boolean iswork = false;
    		Set<Entry<String, Object>> set = m.entrySet();
	 		Iterator io = set.iterator();
	 		while (io.hasNext()) {
	 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
	 			if("KUNNR".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 				iswork = true;
	 			}
	 			if("NAME".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 				iswork = true;
	 			}
	 			if("VKORG".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 				iswork = true;
	 			}
	 			if("LOGIN_ID".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 				iswork = true;
	 			}
	 			if("PWD".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 				iswork = true;
	 			}
	 			if("area".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 				iswork = true;
	 			}
	 			if("area_name".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 				iswork = true;
	 			}
	 			
	 		}
	 		if(iswork) {
				Connection conn = DBUtils.createConn();
	             sql = sql.substring(0, sql.length()-1);
		 		sql += " where KUNNR = '"+kunr+"' AND VKORG = '"+vkorg+"'" ;
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
	public boolean deletekua(String kunr, String vkorg) {
		try{
			Connection conn = DBUtils.createConn();
			String now = DateWorkUtil.stringToDatenow();
			String sql="delete from  Z_KNA  where 1=1 ";
			if(StringUtil.isNotEmpty(kunr)){
	 			sql += " and KUNNR='"+kunr+"' ";
	 		}
	        if(StringUtil.isNotEmpty(vkorg)){
	        	sql += " and VKORG = '"+vkorg+"' ";
	 		}
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
	public boolean savekua(String kunr, String vkorg, String name,
			String Login_id, String pwd, String area, String area_name) {
		try{
			Connection conn = DBUtils.createConn();
			String sql = "insert into Z_KNA (KUNNR,VKORG,NAME,LOGIN_ID,PWD,area,area_name) "
					+ " values ('"+kunr+"','"+vkorg+"','"+name+"','"+Login_id+"','"+pwd+"','"+area+"','"+area_name+"')";
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

}
