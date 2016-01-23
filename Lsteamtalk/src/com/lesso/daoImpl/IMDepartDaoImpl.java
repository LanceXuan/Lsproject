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

import com.lesso.beans.IMDepart;
import com.lesso.dao.IMDepartDao;
import com.lesso.model.TreeDTO;
import com.lesso.util.DBUtils;
import com.lesso.util.DateWorkUtil;
import com.lesso.util.StringUtil;

public class IMDepartDaoImpl extends BaseDaoImpl<IMDepart> implements IMDepartDao{

	@Override
	public IMDepart findIMDepart(int id) {
		try{
			IMDepart imdep = this.findById(id);
			 return imdep;
	        }catch(Exception e){
	     	   e.printStackTrace();
	    		   return null;
	        }
	}

	@Override
	public int saveIMDepart(IMDepart obj) {
		try{
            this.save(obj);
			 return 1;
	        }catch(Exception e){
	     	   e.printStackTrace();
	    		   return 0;
	        }
	}

	@Override
	public List<IMDepart> findByPagination(int currentPage, int pageSize,
			Map<String, Object> m) throws Exception {
		try{
			Connection conn = DBUtils.createConn();
	 		String sql = " select *, (SELECT nick FROM IMUser WHERE id= IMDepart.leader) "
	 				+ " AS nick, (SELECT departname FROM IMDepart  a WHERE a.id= IMDepart.parentId) AS parentname "
	 				+ " from IMDepart  where 1=1  ";
	 		
	 		Set<Entry<String, Object>> set = m.entrySet();
	 		Iterator io = set.iterator();
	 		while (io.hasNext()) {
	 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
	 			if("departName".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
	 			}
	 			if("priority".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("parentid".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("id".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("status".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
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
	 		
	 		List<IMDepart> ulist = new ArrayList<IMDepart>();
	 		while(rs.next()){
	 			IMDepart mp = new IMDepart();
	 			mp.setCreated(Long.parseLong(rs.getString("Created")));
				mp.setId(Integer.parseInt(rs.getString("id")));
				mp.setUpdated(Long.parseLong(rs.getString("updated")));
				mp.setDepartName(rs.getString("departName"));
				mp.setParentid(Integer.parseInt(rs.getString("parentid")));
				mp.setPriority(Integer.parseInt(rs.getString("priority")));
				mp.setStatus(Integer.parseInt(rs.getString("status")));
				if(StringUtil.isEmpty(rs.getString("leader"))){
					mp.setLeader(0);
				}else{
				mp.setLeader(Integer.parseInt(rs.getString("leader")));
				}
				mp.setLeadername(rs.getString("nick"));
				mp.setParentname(rs.getString("parentname"));
				mp.setSapcode(rs.getString("sapcode"));
				mp.setParentsapcode(rs.getString("parentsapcode"));
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
 		String sql = " select count(*) from IMDepart  where 1=1 ";
 		
 		Set<Entry<String, Object>> set = m.entrySet();
 		Iterator io = set.iterator();
 		while (io.hasNext()) {
 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
 			if("departName".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
 			}
 			if("priority".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("parentid".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}	
 			if("id".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
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
	public boolean updateIMAdmin(IMDepart imdep) {
		try{
            this.update(imdep);
			 return true;
	        }catch(Exception e){
	     	   e.printStackTrace();
	    		   return false;
	        }
	}

	@Override
	public boolean deleteIMAdmin(int id) {
		try{
            this.delete(id);
			 return true;
	        }catch(Exception e){
	     	   e.printStackTrace();
	    		   return false;
	        }
	}

	@Override
	public List<TreeDTO> getTreeDTO(Map<String, Object> m) {
		try{
			Connection conn = DBUtils.createConn();
	 		String sql = " select * from IMDepart  where 1=1 ";
	 		
	 		Set<Entry<String, Object>> set = m.entrySet();
	 		Iterator io = set.iterator();
	 		while (io.hasNext()) {
	 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
	 			if("departName".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
	 			}
	 			if("priority".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("parentid".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
	 			}
	 			if("id".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
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
	 		
	 		List<TreeDTO> ulist = new ArrayList<TreeDTO>();
	 		while(rs.next()){
	 			TreeDTO mp = new TreeDTO();
	 			mp.setId(Integer.parseInt(rs.getString("id")));
	 			mp.setText(rs.getString("departName"));
//	 			mp.setChecked(resource.getChecked());
//	 			mp.setIconCls(resource.getIcon());
	 			mp.setParent_id(Integer.parseInt(rs.getString("parentid")));
//	 			if(getChildren(Integer.parseInt(rs.getString("id"))).size() > 0){
//	 				mp.setState("closed");
//				} else {
					mp.setState("0");
//				}
	
				ulist.add(mp);
	 		}
	 		return ulist;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<IMDepart> getChildren(int pid) throws Exception {
		Connection conn = DBUtils.createConn();
		String sql = "select * from IMDepart where parentid = " + pid;
		PreparedStatement ps = DBUtils.getPs(conn, sql);
		ResultSet rs = ps.executeQuery();
		
		List<IMDepart> children = new ArrayList<IMDepart>();
		while(rs.next()){
 			IMDepart mp = new IMDepart();
 			mp.setCreated(Long.parseLong(rs.getString("Created")));
			mp.setId(Integer.parseInt(rs.getString("id")));
			mp.setUpdated(Long.parseLong(rs.getString("updated")));
			mp.setDepartName(rs.getString("departName"));
			mp.setParentid(Integer.parseInt(rs.getString("parentid")));
			mp.setPriority(Integer.parseInt(rs.getString("priority")));
			mp.setStatus(Integer.parseInt(rs.getString("status")));
			children.add(mp);
		}		
		
		
		return children;
	}

	@Override
	public boolean updateleader(int id,int leader) {
		try {
			Connection conn = DBUtils.createConn();
	 		String sql = " update IMDepart set leader = '"+leader+"' where id="+id;
	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ps.executeUpdate();
			DBUtils.close(ps);
			DBUtils.close(conn);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean saveDepart(String departName, int priority, int parentid,
			int status, long created, long updated, int leader,String sapcode) {
		try{
     		Connection conn = DBUtils.createConn();
     		String sql ="insert into IMDepart (departName,priority,parentid,status,created,updated,leader,sapcode)"
     				+ " values('"+departName+"','"+priority+"','"+parentid+"','"+status+"','"+created+"',"
     						+ "'"+updated+"','"+leader+"','"+sapcode+"')";
     		Statement st=conn.createStatement();
     		System.out.println(sql);
 			int ds = st.executeUpdate(sql);
 			DBUtils.close(conn);
 			if(st != null){
 				st.close();
 			}
 			return true;
     	}catch(Exception e){
     		e.printStackTrace();
     		return false;
     	}
	}

	@Override
	public boolean SyncParentDepart(int id, int parentid) {
		try {
			Connection conn = DBUtils.createConn();
	 		String sql = " update IMDepart set parentId = '"+parentid+"' where id="+id;
	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ps.executeUpdate();
			DBUtils.close(ps);
			DBUtils.close(conn);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int SyncAllDepart(String departName, int priority, int parentid,
			int status, long created, long updated, int leader, String sapcode,
			String parentsapcode) {
		try{
     		Connection conn = DBUtils.createConn();
     		String sql ="insert into IMDepart (departName,priority,parentid,status,created,updated,leader,sapcode,parentsapcode)"
     				+ " values('"+departName+"','"+priority+"','"+parentid+"','"+status+"','"+created+"',"
     						+ "'"+updated+"','"+leader+"','"+sapcode+"','"+parentsapcode+"')";
     		Statement st=conn.createStatement();
     		System.out.println(sql);
 			int ds = st.executeUpdate(sql);
 			int id = 0;
			ResultSet rs = st.getGeneratedKeys(); 
			if (rs != null&&rs.next()) {  
			    id=rs.getInt(1);  
			}  
 			DBUtils.close(conn);
 			if(st != null){
 				st.close();
 			}
 			return id;
     	}catch(Exception e){
     		e.printStackTrace();
     		return 0;
     	}
	}

	@Override
	public IMDepart findbyParentSapcode(String sapcode) {
		try{
			Connection conn = DBUtils.createConn();
	 		String sql = " select * from IMDepart where status ='0' and parentsapcode = '"+sapcode+"'";
	 		

	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ResultSet rs = ps.executeQuery();
	 		
	 		IMDepart mp =null;
	 		if(rs.next()){
	 		    mp = new IMDepart();
	 			mp.setCreated(Long.parseLong(rs.getString("Created")));
				mp.setId(Integer.parseInt(rs.getString("id")));
				mp.setUpdated(Long.parseLong(rs.getString("updated")));
				mp.setDepartName(rs.getString("departName"));
				mp.setParentid(Integer.parseInt(rs.getString("parentid")));
				mp.setPriority(Integer.parseInt(rs.getString("priority")));
				mp.setStatus(Integer.parseInt(rs.getString("status")));
				if(StringUtil.isEmpty(rs.getString("leader"))){
					mp.setLeader(0);
				}else{
				mp.setLeader(Integer.parseInt(rs.getString("leader")));
				}
				mp.setSapcode(rs.getString("sapcode"));
				mp.setParentsapcode(rs.getString("parentsapcode"));
	 		}
	 		return mp;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public IMDepart findbySapcode(String sapcode) {
		try{
			Connection conn = DBUtils.createConn();
	 		String sql = " select * from IMDepart where status ='0' and sapcode = '"+sapcode+"'";
	 		

	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ResultSet rs = ps.executeQuery();
	 		
	 		IMDepart mp =null;
	 		if(rs.next()){
	 		    mp = new IMDepart();
	 			mp.setCreated(Long.parseLong(rs.getString("Created")));
				mp.setId(Integer.parseInt(rs.getString("id")));
				mp.setUpdated(Long.parseLong(rs.getString("updated")));
				mp.setDepartName(rs.getString("departName"));
				mp.setParentid(Integer.parseInt(rs.getString("parentid")));
				mp.setPriority(Integer.parseInt(rs.getString("priority")));
				mp.setStatus(Integer.parseInt(rs.getString("status")));
				if(StringUtil.isEmpty(rs.getString("leader"))){
					mp.setLeader(0);
				}else{
				mp.setLeader(Integer.parseInt(rs.getString("leader")));
				}
				mp.setSapcode(rs.getString("sapcode"));
				mp.setParentsapcode(rs.getString("parentsapcode"));
	 		}
	 		return mp;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean UpdateDepart(String departName, int priority, int parentid,
			int status, int leader, String sapcode,
			String parentsapcode,int id) {
		
		try {
			Connection conn = DBUtils.createConn();
			long dds = DateWorkUtil.timechuo();
	 		String sql = "update IMDepart set updated = '"+dds+"',";
	 		if(StringUtil.isNotEmpty(departName)){
	 			sql += " departName = '"+departName+"',";
	 		}
	 		if(StringUtil.isNotEmpty(sapcode)){
	 			sql += " sapcode = '"+sapcode+"',";
	 		}
	 		if(StringUtil.isNotEmpty(parentsapcode)){
	 			sql += " parentsapcode = '"+parentsapcode+"',";
	 		}
	 		if(parentid >=0){
	 			sql += " parentId = '"+parentid+"',";
	 		}
	 		if(priority >=0){
	 			sql += " priority = '"+priority+"',";
	 		}
	 		if(status >=0){
	 			sql += " status = '"+status+"',";
	 		}
	 		if(leader >=0){
	 			sql += " leader = '"+leader+"',";
	 		}
	 		sql = sql.substring(0, sql.length()-1);
	 		
	 		sql += " where id = "+id;
	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ps.executeUpdate();
			DBUtils.close(ps);
			DBUtils.close(conn);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
