package com.lesso.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lesso.beans.Z_User_Vkorg;
import com.lesso.dao.Z_User_VkorgDao;
import com.lesso.util.DBUtils;
import com.lesso.util.StringUtil;


/** * @author  Lance 
 * @date 创建时间：2016年1月5日 上午9:40:20 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class Z_User_VkorgDaoImpl extends BaseDaoImpl<Z_User_Vkorg> implements Z_User_VkorgDao {

	@Override
	public List<Z_User_Vkorg> getZ_User_VkorgList(String account, String vkorg,
			String ztype, int page, int rows) {
		try{
			Connection conn = DBUtils.createConn();
	 		String sql = " select * from Z_USER_VKORG where 1=1";
	 		if(StringUtil.isNotEmpty(account)){
	 			sql +=" and account ='"+account+"' ";
	 		}
	 		if(StringUtil.isNotEmpty(vkorg)){
	 			sql +=" and vkorg ='"+vkorg+"' ";
	 		}
	 		if(StringUtil.isNotEmpty(ztype)){
	 			sql +=" and ztype ='"+ztype+"' ";
	 		}
	 		if(page >0 && rows>0){
	 		    sql = sql +" limit " + (page-1)*rows +" , "  + rows ;
	 		}
	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ResultSet rs = ps.executeQuery();
	 		
	 		List<Z_User_Vkorg> ulist = new ArrayList<Z_User_Vkorg>();
	 		while(rs.next()){
	 			Z_User_Vkorg mp = new Z_User_Vkorg();
	 			mp.setAccount(rs.getString("account"));
	 			mp.setVkorg(rs.getString("vkorg"));
	 			mp.setZtype(rs.getString("ztype"));
			    ulist.add(mp);
	 		}
	 		return ulist;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getTotal(String account, String vkorg, String ztype) {
		try{
			Connection conn = DBUtils.createConn();
	 		String sql = " select count(*) from Z_USER_VKORG where 1=1";
	 		if(StringUtil.isNotEmpty(account)){
	 			sql +=" and account ='"+account+"' ";
	 		}
	 		if(StringUtil.isNotEmpty(vkorg)){
	 			sql +=" and vkorg ='"+vkorg+"' ";
	 		}
	 		if(StringUtil.isNotEmpty(ztype)){
	 			sql +=" and ztype ='"+ztype+"' ";
	 		}
	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ResultSet rs = ps.executeQuery();
	 		int count = 0 ;
	 		if(rs.next()){
	 			
	 			count = rs.getInt(1);
	 		}
	 		return count;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean deleteZ_User_Vkorg(String account, String vkorg, String ztype) {
		try{
			Connection conn = DBUtils.createConn();
	 		String sql = " delete from Z_USER_VKORG where 1=1";
	 		if(StringUtil.isNotEmpty(vkorg)){
	 			sql +=" and vkorg ='"+vkorg+"' ";
	 		}
	 		if(StringUtil.isNotEmpty(ztype)){
	 			sql +=" and ztype ='"+ztype+"' ";
	 		}	 		
	 		if(StringUtil.isNotEmpty(account)){
	 			sql +=" and account ='"+account+"' ";
		 		PreparedStatement ps = DBUtils.getPs(conn, sql);
		 		int isok = ps.executeUpdate(sql);
				DBUtils.close(ps);
				DBUtils.close(conn);
				if(isok>0){
				    return true;
				}else{
					return false;
				}
	 		}else{
	 			return false;
	 		}
            
		}catch(Exception e){
			e.printStackTrace();
		    return false;
		}
	}

	@Override
	public boolean saveZ_User_Vkorg(String account, String vkorg, String ztype) {
		try{
			if(StringUtil.isNotEmpty(account) && StringUtil.isNotEmpty(vkorg) && StringUtil.isNotEmpty(ztype)){
				Connection conn = DBUtils.createConn();
				String sql = "insert into Z_USER_VKORG (account, vkorg, ztype)"
						+ " values('"+account+"','"+vkorg+"','"+ztype+"')";
				Statement st = conn.createStatement();
				int ds = st.executeUpdate(sql);
				DBUtils.close(conn);
				if (st != null) {
					st.close();
				}
				if (ds>0) {  
					return true;
				}else{
					return false;
				} 
			}else{
				return false;
			}
		}catch(Exception e){
		    e.printStackTrace();
		    return false;
		}
	}

}
