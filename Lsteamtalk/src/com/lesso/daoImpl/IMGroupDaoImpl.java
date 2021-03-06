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

import com.lesso.beans.IMGroup;
import com.lesso.dao.IMGroupDao;
import com.lesso.util.DBUtils;

public class IMGroupDaoImpl extends BaseDaoImpl<IMGroup> implements IMGroupDao {

	@Override
	public IMGroup findIMGroup(int id) {
		 try{
			 IMGroup imgroup = this.findById(id);
			 return imgroup;
	        }catch(Exception e){
	     	   e.printStackTrace();
	    		   return null;
	        }
	}

	@Override
	public int saveIMGroup(IMGroup group) {
		// TODO Auto-generated method stub
		try {
			this.save(group);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public List<IMGroup> findByPagination(int currentPage, int pageSize,
			Map<String, Object> m) throws Exception {
		try{
			Connection conn = DBUtils.createConn();
	 		String sql = " select *,(SELECT COUNT(1) FROM IMGroupMember WHERE GROUPID=IMGroup.ID) COUNTX, "
	 				+ "(SELECT nick FROM IMUser WHERE id=IMGroup.creator) AS nick "
	 				+ "from IMGroup  where 1=1 ";
	 		
	 		Set<Entry<String, Object>> set = m.entrySet();
	 		Iterator io = set.iterator();
	 		while (io.hasNext()) {
	 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
	 			if("name".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
	 			}
	 			if("avatar".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
	 			}
	 			if("creator".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("type".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("userCnt".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("status".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("version".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("lastchatedA".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and lastchated  >= '" + me.getValue() +"'";
	 			}
	 			if("lastchatedB".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and lastchated  <= '" + me.getValue() +"'";
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
	 		
	 		List<IMGroup> ulist = new ArrayList<IMGroup>();
	 		while(rs.next()){
	 			IMGroup mp = new IMGroup();
	 			mp.setCreated(Long.parseLong(rs.getString("Created")));
				mp.setId(Integer.parseInt(rs.getString("id")));
				mp.setName(rs.getString("name"));
				mp.setAvatar(rs.getString("avatar"));
				mp.setStatus(Integer.parseInt(rs.getString("status")));
				mp.setCreator(Integer.parseInt(rs.getString("creator")));
				mp.setLastchated(Long.parseLong(rs.getString("lastchated")));
				mp.setType(Integer.parseInt(rs.getString("type")));
				mp.setUpdated(Long.parseLong(rs.getString("updated")));
				mp.setUserCnt(Integer.parseInt(rs.getString("COUNTX")));
				mp.setVersion(Integer.parseInt(rs.getString("version")));
				mp.setNick(rs.getString("nick"));
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
 		String sql = " select count(*) from IMGroup  where 1=1 ";
 		
 		Set<Entry<String, Object>> set = m.entrySet();
 		Iterator io = set.iterator();
 		while (io.hasNext()) {
 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
 			if("name".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
 			}
 			if("avatar".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
 			}
 			if("creator".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("type".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("userCnt".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("status".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("version".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("lastchatedA".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and lastchated  >= '" + me.getValue() +"'";
 			}
 			if("lastchatedB".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and lastchated  <= '" + me.getValue() +"'";
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
	public boolean updateIMGroup(IMGroup group) {
		try {
			this.update(group);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteIMGroup(int id) {
		try {
			this.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
