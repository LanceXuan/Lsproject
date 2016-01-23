package com.lesso.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.lesso.beans.IMDiscovery;
import com.lesso.dao.IMDiscoveryDao;
import com.lesso.util.DBUtils;

public class IMDiscoveryDaoImpl extends BaseDaoImpl<IMDiscovery> implements IMDiscoveryDao{

	@Override
	public IMDiscovery findIMDiscovery(String username) {
		return null;
	}

	@Override
	public IMDiscovery findIMDiscoveryById(int id) {
		return null;
	}

	@Override
	public int saveIMDiscovery(IMDiscovery imd) {
		try{
            this.save(imd);
			 return 1;
	        }catch(Exception e){
	     	   e.printStackTrace();
	    		   return 0;
	        }
	}

	@Override
	public List<IMDiscovery> findByPagination(int currentPage, int pageSize,
			Map<String, Object> m) throws Exception {
		try{
			Connection conn = DBUtils.createConn();
	 		String sql = " select *  "
	 				+ "from IMDiscovery  where 1=1 ";
	 		
	 		Set<Entry<String, Object>> set = m.entrySet();
	 		Iterator io = set.iterator();
	 		while (io.hasNext()) {
	 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
	 			if("itemName".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
	 			}
	 			if("itemUrl".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
	 			}
	 			if("itemPriority".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("id".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("status".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("updatedA".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and updated  >= '" + me.getValue() +"'";
	 			}
	 			if("updatedB".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and updated  <= '" + me.getValue() +"'";
	 			}
	 			if("createdA".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and created  >= '" + me.getValue() +"'";
	 			}
	 			if("createdB".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and created  <= '" + me.getValue() +"'";
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
	 		
	 		List<IMDiscovery> ulist = new ArrayList<IMDiscovery>();
	 		while(rs.next()){
	 			IMDiscovery mp = new IMDiscovery();
	 			mp.setCreated(Long.parseLong(rs.getString("Created")));
				mp.setId(Integer.parseInt(rs.getString("id")));
				mp.setStatus(Integer.parseInt(rs.getString("status")));
				mp.setUpdated(Long.parseLong(rs.getString("updated")));
                mp.setItemName(rs.getString("itemName"));
                mp.setItemUrl(rs.getString("itemUrl"));
                mp.setItemPriority(Integer.parseInt(rs.getString("itemPriority")));
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
 		String sql = " select count(*) from IMDiscovery  where 1=1 ";
 		
 		Set<Entry<String, Object>> set = m.entrySet();
 		Iterator io = set.iterator();
 		while (io.hasNext()) {
 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
 			if("itemName".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
 			}
 			if("itemUrl".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
 			}
 			if("itemPriority".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("id".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("status".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("updatedA".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and updated  >= '" + me.getValue() +"'";
 			}
 			if("updatedB".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and updated  <= '" + me.getValue() +"'";
 			}
 			if("createdA".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and created  >= '" + me.getValue() +"'";
 			}
 			if("createdB".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and created  <= '" + me.getValue() +"'";
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
	public boolean updateIMDiscovery(IMDiscovery imu) {
		try{
            this.update(imu);
			 return true;
	        }catch(Exception e){
	     	   e.printStackTrace();
	    		   return false;
	        }
	}

	@Override
	public boolean deleteIMDiscovery(int id) {
		try{
            this.delete(id);
			 return true;
	        }catch(Exception e){
	     	   e.printStackTrace();
	    	   return false;
	        }
	}

	@Override
	public boolean updatestate(int id) {
		return false;
	}

}
