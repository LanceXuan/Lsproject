
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

import com.lesso.beans.IMUser;
import com.lesso.dao.IMUserDao;
import com.lesso.util.DBUtils;
import com.lesso.util.DateWorkUtil;
import com.lesso.util.StringUtil;



public class IMUserDaoImpl extends BaseDaoImpl<IMUser> implements IMUserDao{
    public IMUser findIMUser(String username){
        try{
     	   Connection conn = DBUtils.createConn();
     	   String sql ="select *, (SELECT departname FROM IMDepart WHERE id = IMUser.departId) AS departname from IMUser where status ='0' and name= '"+username+"'";
     	   PreparedStatement ps = DBUtils.getPs(conn, sql);
 			ResultSet rs = ps.executeQuery();
 			IMUser mp = null;
 			while(rs.next()){
 				mp = new IMUser();
 				mp.setCreated(Long.parseLong(rs.getString("Created")));
 				mp.setSex(Integer.parseInt(rs.getString("sex")));
 				mp.setId(Integer.parseInt(rs.getString("id")));
 				mp.setName(rs.getString("name"));
 				mp.setNick(rs.getString("nick"));
 				mp.setPassword(rs.getString("Password"));
 				mp.setSalt(rs.getString("Salt"));
 				mp.setDomain(rs.getString("domain"));
 				mp.setUpdated(Long.parseLong(rs.getString("updated")));
 				mp.setDepartId(Integer.parseInt(rs.getString("departId")));
 				mp.setPhone(rs.getString("phone"));
 				mp.setEmail(rs.getString("email"));
 				mp.setAvatar(rs.getString("avatar"));
 				mp.setStatus(Integer.parseInt(rs.getString("status")));
 				mp.setPush_shield_status(Integer.parseInt(rs.getString("push_shield_status")));
 				mp.setSign_info(rs.getString("sign_info"));
 				mp.setType(Integer.parseInt(rs.getString("type")));
 				mp.setDepartname(rs.getString("departname"));
 				mp.setSap_vkorg(rs.getString("sap_vkorg"));
 				mp.setSap_kunnr(rs.getString("sap_kunnr"));
 				mp.setSap_kunnr_father(rs.getString("sap_kunnr_father"));
 				mp.setSapbm(rs.getString("sapbm"));
 				mp.setSapgh(rs.getString("sapgh"));
 			}
 			DBUtils.close(ps);
 			DBUtils.close(conn);
 			return mp;
        }catch(Exception e){
     	   e.printStackTrace();
    		   return null;
        }
     }
     
     public int saveIMUser(IMUser imuser){
     	try{
     		Connection conn = DBUtils.createConn();
     		String sql ="insert into IMUser (sex,name,domain,nick,password,salt,phone,email,avatar,departId,status,"
     				+ "created,updated,push_shield_status,sign_info,type,sap_vkorg,sap_kunnr,sap_kunnr_father)"
     				+ " values('"+imuser.getSex()+"','"+imuser.getName()+"','"+imuser.getDomain()+"','"
     						+ imuser.getNick()+"','"+imuser.getPassword()+"','"+imuser.getSalt()+"','"+imuser.getPhone()+"','"+imuser.getEmail()+"','"
     								+imuser.getAvatar()+"','"+imuser.getDepartId()+"','"+imuser.getStatus()+"','"+imuser.getCreated() +"',"
     										+ "'"+imuser.getUpdated()+"','"+imuser.getPush_shield_status()+"','"+imuser.getSign_info()+"',"
     												+ "'"+imuser.getType()+"','"+imuser.getSap_vkorg()+"','"+imuser.getSap_kunnr()+"','"+imuser.getSap_kunnr_father()+"')";
     		Statement st=conn.createStatement();		    				
 			int ds = st.executeUpdate(sql);

 			DBUtils.close(conn);
 			if(st != null){
 				st.close();
 			}
 			return ds;
     	}catch(Exception e){
     		e.printStackTrace();
     		return 0;
     	}
 		
     	
     }
     
     
 	/**
 	 * 分页查询列表信息 
 	 */
 	public List<IMUser> findByPagination(int currentPage, int pageSize  ,Map<String ,Object> m)
 			throws Exception {
 		Connection conn = DBUtils.createConn();
 		String sql = " select *, (SELECT departname FROM IMDepart WHERE id = IMUser.departId) AS departname from IMUser  where 1=1 ";
 		
 		Set<Entry<String, Object>> set = m.entrySet();
 		Iterator io = set.iterator();
 		while (io.hasNext()) {
 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
 			if("name".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
 			}
 			if("nick".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
 			}
 			if("phone".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
 			}
 			if("email".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
 			}
 			if("domain".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("status".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("id".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
 			}
 			if("departId".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("type".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("sap_vkorg".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("sap_kunnr".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("sap_kunnr_father".equals(me.getKey()) && !"".equals(me.getValue())){
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
 		
 		List<IMUser> ulist = new ArrayList<IMUser>();
 		while(rs.next()){
 			IMUser mp = new IMUser();
 			String ds = rs.getString("nick");
 			mp.setCreated(Long.parseLong(rs.getString("Created")));
				mp.setSex(Integer.parseInt(rs.getString("sex")));
				mp.setId(Integer.parseInt(rs.getString("id")));
				mp.setName(rs.getString("name"));
				mp.setNick(rs.getString("nick"));
				mp.setPassword(rs.getString("Password"));
				mp.setSalt(rs.getString("Salt"));
				mp.setDomain(rs.getString("domain"));
				mp.setUpdated(Long.parseLong(rs.getString("updated")));
				mp.setDepartId(Integer.parseInt(rs.getString("departId")));
				mp.setPhone(rs.getString("phone"));
				mp.setEmail(rs.getString("email"));
				mp.setAvatar(rs.getString("avatar"));
				mp.setStatus(Integer.parseInt(rs.getString("status")));
				mp.setPush_shield_status(Integer.parseInt(rs.getString("push_shield_status")));
				mp.setSign_info(rs.getString("sign_info"));
				mp.setType(Integer.parseInt(rs.getString("type")));
				mp.setDepartname(rs.getString("departname"));
 				mp.setSap_vkorg(rs.getString("sap_vkorg"));
 				mp.setSap_kunnr(rs.getString("sap_kunnr"));
 				mp.setSap_kunnr_father(rs.getString("sap_kunnr_father"));
 				mp.setSapbm(rs.getString("sapbm"));
 				mp.setSapgh(rs.getString("sapgh"));
				ulist.add(mp);
 		}
 		return ulist;
 	}
 	
 	/**
 	 * 查询表中的所有记录数 
 	 */
 	public int getTotal(Map<String ,Object> m) throws Exception {
 		
 		Connection conn = DBUtils.createConn();
 		String sql = " select count(*) from IMUser  where 1=1 ";
 		
 		Set<Entry<String, Object>> set = m.entrySet();
 		Iterator io = set.iterator();
 		while (io.hasNext()) {
 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
 			if("name".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
 			}
 			if("nick".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
 			}
 			if("phone".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
 			}
 			if("email".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
 			}
 			if("domain".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("type".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("id".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
 			}
 			if("sap_vkorg".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("sap_kunnr".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("sap_kunnr_father".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("status".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("departId".equals(me.getKey()) && !"".equals(me.getValue())){
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
	public boolean updateIMUser(IMUser imuser) {
		try {
			this.update(imuser);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
	}

	@Override
	public boolean deleteIMUser(int id) {
		// TODO Auto-generated method stub
		try {
			this.delete(id);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updatestate(int id) {
		try {
			Connection conn = DBUtils.createConn();
	 		String sql = " update IMUser set status = '3' where id="+id;
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
	public boolean updateIMUserPwd(String pwd,int id) {
		try {
			Connection conn = DBUtils.createConn();
	 		String sql = "update IMUser set password = '"+pwd+"' where id="+id;
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
	public boolean updateIMUser(String sign_info, String sex, String nick,
			String phone, String email, String departId, String photo,int id,int type) {
		try {
			Connection conn = DBUtils.createConn();
	 		String sql = "update IMUser set status = '0',";
	 		if(StringUtil.isNotEmpty(sign_info)){
	 			sql += " sign_info = '"+sign_info+"',";
	 		}
	 		if(StringUtil.isNotEmpty(sex)){
	 			sql += " sex = '"+sex+"',";
	 		}
	 		if(StringUtil.isNotEmpty(nick)){
	 			sql += " nick = '"+nick+"',";
	 		}
	 		if(StringUtil.isNotEmpty(phone)){
	 			sql += " phone = '"+phone+"',";
	 		}
	 		if(StringUtil.isNotEmpty(email)){
	 			sql += " email = '"+email+"',";
	 		}
	 		if(StringUtil.isNotEmpty(departId)){
	 			sql += " departId = '"+departId+"',";
	 		}
	 		if(StringUtil.isNotEmpty(photo)){
	 			sql += " avatar = '"+photo+"',";
	 		}
	 		if(type >=0){
	 			sql += " type = '"+type+"',";
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

	@Override
	public IMUser findIMUserById(int id) {
		 try{
	     	   Connection conn = DBUtils.createConn();
	     	   String sql ="select * from IMUser where id= '"+id+"'";
	     	   PreparedStatement ps = DBUtils.getPs(conn, sql);
	 			ResultSet rs = ps.executeQuery();
	 			IMUser mp = new IMUser();
	 			while(rs.next()){
	 				mp.setCreated(Long.parseLong(rs.getString("Created")));
	 				mp.setSex(Integer.parseInt(rs.getString("sex")));
	 				mp.setId(Integer.parseInt(rs.getString("id")));
	 				mp.setName(rs.getString("name"));
	 				mp.setNick(rs.getString("nick"));
	 				mp.setPassword(rs.getString("Password"));
	 				mp.setSalt(rs.getString("Salt"));
	 				mp.setDomain(rs.getString("domain"));
	 				mp.setUpdated(Long.parseLong(rs.getString("updated")));
	 				mp.setDepartId(Integer.parseInt(rs.getString("departId")));
	 				mp.setPhone(rs.getString("phone"));
	 				mp.setEmail(rs.getString("email"));
	 				mp.setAvatar(rs.getString("avatar"));
	 				mp.setStatus(Integer.parseInt(rs.getString("status")));
	 				mp.setPush_shield_status(Integer.parseInt(rs.getString("push_shield_status")));
	 				mp.setSign_info(rs.getString("sign_info"));
	 				mp.setType(Integer.parseInt(rs.getString("type")));
	 				mp.setSap_vkorg(rs.getString("sap_vkorg"));
	 				mp.setSap_kunnr(rs.getString("sap_kunnr"));
	 				mp.setSap_kunnr_father(rs.getString("sap_kunnr_father"));
	 				mp.setSapbm(rs.getString("sapbm"));
	 				mp.setSapgh(rs.getString("sapgh"));
	 			}
	 			DBUtils.close(ps);
	 			DBUtils.close(conn);
	 			return mp;
	        }catch(Exception e){
	     	   e.printStackTrace();
	    		   return null;
	        }
	}

	@Override
	public boolean updateIMUserpwd(String name, String pwd, int id, int status) {
		try {
			Connection conn = DBUtils.createConn();
	 		String sql = "update IMUser set password = '"+pwd+"' where id='"+id+"'"
	 				+ " and status='"+status+"' and name='"+name+"'";
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
	public boolean uodateavatar(String username, String photo) {
		try {
			Connection conn = DBUtils.createConn();
	 		String sql = "update IMUser set avatar = '"+photo+"' where name='"+username+"'";
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
	public List<IMUser> findIMUserBydepartId(int departId) {
		Connection conn = DBUtils.createConn();
 		String sql = " select * from IMUser  where status = '0' and DepartId='"+departId+"'";
 		try{
 		PreparedStatement ps = DBUtils.getPs(conn, sql);
 		ResultSet rs = ps.executeQuery(); 		
 		List<IMUser> ulist = new ArrayList<IMUser>();
 		while(rs.next()){
 			IMUser mp = new IMUser();
 			String ds = rs.getString("nick");
 			mp.setCreated(Long.parseLong(rs.getString("Created")));
				mp.setSex(Integer.parseInt(rs.getString("sex")));
				mp.setId(Integer.parseInt(rs.getString("id")));
				mp.setName(rs.getString("name"));
				mp.setNick(rs.getString("nick"));
				mp.setPassword(rs.getString("Password"));
				mp.setSalt(rs.getString("Salt"));
				mp.setDomain(rs.getString("domain"));
				mp.setUpdated(Long.parseLong(rs.getString("updated")));
				mp.setDepartId(Integer.parseInt(rs.getString("departId")));
				mp.setPhone(rs.getString("phone"));
				mp.setEmail(rs.getString("email"));
				mp.setAvatar(rs.getString("avatar"));
				mp.setStatus(Integer.parseInt(rs.getString("status")));
				mp.setPush_shield_status(Integer.parseInt(rs.getString("push_shield_status")));
				mp.setSign_info(rs.getString("sign_info"));
				mp.setType(Integer.parseInt(rs.getString("type")));
 				mp.setSap_vkorg(rs.getString("sap_vkorg"));
 				mp.setSap_kunnr(rs.getString("sap_kunnr"));
 				mp.setSap_kunnr_father(rs.getString("sap_kunnr_father"));
 				mp.setSapbm(rs.getString("sapbm"));
 				mp.setSapgh(rs.getString("sapgh"));
				ulist.add(mp);
 		     }
 		    return ulist;
 		}catch(Exception e){
 			e.printStackTrace();
 			return null;
 		}
	}

	@Override
	public List<IMUser> findIMuserByUserName(String searchText) {
		// TODO Auto-generated method stub
		Connection conn = DBUtils.createConn();
 		String sql = " select * from IMUser  where status = '0' and nick like '%"+searchText+"%'";
 		try{
 		PreparedStatement ps = DBUtils.getPs(conn, sql);
 		ResultSet rs = ps.executeQuery(); 		
 		List<IMUser> ulist = new ArrayList<IMUser>();
 		while(rs.next()){
 			IMUser mp = new IMUser();
 			mp.setCreated(Long.parseLong(rs.getString("Created")));
			mp.setSex(Integer.parseInt(rs.getString("sex")));
			mp.setId(Integer.parseInt(rs.getString("id")));
			mp.setName(rs.getString("name"));
			mp.setNick(rs.getString("nick"));
			mp.setPassword(rs.getString("Password"));
			mp.setSalt(rs.getString("Salt"));
			mp.setDomain(rs.getString("domain"));
			mp.setUpdated(Long.parseLong(rs.getString("updated")));
			mp.setDepartId(Integer.parseInt(rs.getString("departId")));
			mp.setPhone(rs.getString("phone"));
			mp.setEmail(rs.getString("email"));
			mp.setAvatar(rs.getString("avatar"));
			mp.setStatus(Integer.parseInt(rs.getString("status")));
			mp.setPush_shield_status(Integer.parseInt(rs.getString("push_shield_status")));
			mp.setSign_info(rs.getString("sign_info"));
			mp.setType(Integer.parseInt(rs.getString("type")));
				mp.setSap_vkorg(rs.getString("sap_vkorg"));
				mp.setSap_kunnr(rs.getString("sap_kunnr"));
				mp.setSap_kunnr_father(rs.getString("sap_kunnr_father"));
 				mp.setSapbm(rs.getString("sapbm"));
 				mp.setSapgh(rs.getString("sapgh"));
				ulist.add(mp);
 		     }
 		    return ulist;
 		}catch(Exception e){
 			e.printStackTrace();
 			return null;
 		}
	}

	@Override
	public int syncuser(int sex, String name, String domain, String nick,
			String password, String salt, String phone, String email,
			int departId, int status, int push_shield_status, String sign_info,
			int type, String sap_vkorg, String sap_kunnr,
			String sap_kunnr_father, String sapbm, String sapgh,String oanum) {
		try{
     		Connection conn = DBUtils.createConn();
     		long dds = DateWorkUtil.timechuo();
     		String sql ="insert into IMUser (sex,name,domain,nick,password,salt,phone,email,departId,status,"
     				+ "created,updated,push_shield_status,sign_info,type,sap_vkorg,sap_kunnr,sap_kunnr_father,sapbm,sapgh,oanum)"
     				+ " values('"+sex+"','"+name+"','"+domain+"','"+nick+"',"
     						+ "'"+password+"','"+salt+"','"+phone+"','"+email+"','"+departId+"',"
     						+ "'"+status+"','"+dds+"','"+dds+"','"+push_shield_status+"','"+sign_info+"','"+type+"',"
     						+ "'"+sap_vkorg+"','"+sap_kunnr+"','"+sap_kunnr_father+"','"+sapbm+"','"+sapgh+"','"+oanum+"')";
     		Statement st=conn.createStatement();		    				
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
	public boolean syncupdateuser(Map<String, Object> m, int id) {
		try{
	 		long dds = DateWorkUtil.timechuo();
			Connection conn = DBUtils.createConn();
	 		String sql = "update IMUser set updated = '"+dds+"',";
	 		
    		Set<Entry<String, Object>> set = m.entrySet();
	 		Iterator io = set.iterator();
	 		while (io.hasNext()) {
	 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
	 			if("sapbm".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("sapgh".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("sex".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("nick".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("phone".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("email".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("domain".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("status".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("departId".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("type".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("sap_vkorg".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("sap_kunnr".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("sap_kunnr_father".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("name".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("password".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("push_shield_status".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("type".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("sign_info".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 			if("oanum".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += me.getKey() + " = '" + me.getValue() +"',";
	 			}
	 		}
	 		
           sql = sql.substring(0, sql.length()-1);
	 		
	 		sql += " where id = "+id;
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
