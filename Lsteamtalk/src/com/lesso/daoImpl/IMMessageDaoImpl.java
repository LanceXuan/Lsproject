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

import com.lesso.beans.IMMessage;
import com.lesso.dao.IMMessageDao;
import com.lesso.util.DBUtils;
import com.lib.Security;

public class IMMessageDaoImpl extends BaseDaoImpl<IMMessage> implements IMMessageDao{

	@Override
	public List<IMMessage> getIMMessageList(int currentPage, int pageSize,
			Map<String, Object> m) throws Exception {
		
		try{
			Connection conn = DBUtils.createConn();
			
	 		int Id= (int) m.get("relateId");
	 		int a = Id%8;
	 	    String tablename = "IMMessage_"+a;
	 	    System.out.println(tablename);
	 		String sql = " select *,(SELECT nick FROM IMUser WHERE id= "+tablename+".fromId) AS fromp,(SELECT nick FROM IMUser WHERE id= "+tablename+".toId) AS topeo from "+tablename+"  where 1=1 ";

	 		Set<Entry<String, Object>> set = m.entrySet();
	 		Iterator io = set.iterator();
	 		
	 		while (io.hasNext()) {
	 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
	 			if("id".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
	 			}
	 			if("relateId".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
	 			}
	 			if("fromId".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
	 			}
	 			if("toId".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
	 			}
	 			if("type".equals(me.getKey()) && !"".equals(me.getValue())){
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
	 		
	 		List<IMMessage> ulist = new ArrayList<IMMessage>();
	 		String content ="";
	 		
	 		while(rs.next()){
	 			IMMessage mp = new IMMessage();
	 			content=Security.getInstance().DecryptMsg(rs.getString("content"));
                mp.setContent(content);
                mp.setCreated(Long.parseLong(rs.getString("created")));
                mp.setRelateId(Integer.parseInt(rs.getString("relateId")));
                mp.setId(Integer.parseInt(rs.getString("id")));
                mp.setMsgId(Integer.parseInt(rs.getString("msgId")));
                mp.setStatus(Integer.parseInt(rs.getString("status")));
                mp.setType(Integer.parseInt(rs.getString("type")));
                mp.setUpdated(Long.parseLong(rs.getString("updated")));
                mp.setFromId(Integer.parseInt(rs.getString("fromId")));
                mp.setToId(Integer.parseInt(rs.getString("toId")));
                mp.setFrompeople(rs.getString("fromp"));
                mp.setTopeople(rs.getString("topeo"));
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
 		int Id= (int) m.get("relateId");
 		int a = Id%8;
 	    String tablename = "IMMessage_"+a;
 	    System.out.println(tablename);
 		String sql = " select count(*) from "+tablename+"  where 1=1 ";
 		
 		
 		Set<Entry<String, Object>> set = m.entrySet();
 		Iterator io = set.iterator();
 		while (io.hasNext()) {
 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
 			if("id".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
 			}
 			if("relateId".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
 			}
 			if("fromId".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
 			}
 			if("toId".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
 			}
 			if("type".equals(me.getKey()) && !"".equals(me.getValue())){
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
 		PreparedStatement ps = DBUtils.getPs(conn, sql);
 		ResultSet rs = ps.executeQuery();		
 		int count = 0 ;
 		if(rs.next()){
 			
 			count = rs.getInt(1);
 		}
 		return count;
	}

	
	public List<IMMessage> UNIONALLIMMessageList(int currentPage, 
			int pageSize,Map<String, Object> m)throws Exception{
		   try{
			   Connection conn = DBUtils.createConn();
			   String sqlwhere = " where 1=1 ";
		 		Set<Entry<String, Object>> set = m.entrySet();
		 		Iterator io = set.iterator();
		 		
		 		while (io.hasNext()) {
		 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
		 			if("id".equals(me.getKey()) && !"".equals(me.getValue())){
		 				sqlwhere += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
		 			}
		 			if("relateId".equals(me.getKey()) && !"".equals(me.getValue())){
		 				sqlwhere += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
		 			}
		 			if("fromId".equals(me.getKey()) && !"".equals(me.getValue())){
		 				sqlwhere += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
		 			}
		 			if("toId".equals(me.getKey()) && !"".equals(me.getValue())){
		 				sqlwhere += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
		 			}
		 			if("fromto".equals(me.getKey()) && !"".equals(me.getValue())){
		 				sqlwhere += " and fromId = '" + me.getValue() +" OR toId ='"+me.getValue()+"'" ;
		 			}
		 			if("type".equals(me.getKey()) && !"".equals(me.getValue())){
		 				sqlwhere += " and " + me.getKey() + " = '" + me.getValue() +"'";
		 			}
		 			if("status".equals(me.getKey()) && !"".equals(me.getValue())){
		 				sqlwhere += " and " + me.getKey() + " = '" + me.getValue() +"'";
		 			}
		 			if("createdA".equals(me.getKey()) && !"".equals(me.getValue())){
		 				sqlwhere += " and created  >= '" + me.getValue() +"'";
		 			}
		 			if("createdB".equals(me.getKey()) && !"".equals(me.getValue())){
		 				sqlwhere += " and created  <= '" + me.getValue() +"'";
		 			}
		 			if("sort".equals(me.getKey()) && !"".equals(me.getValue())){
		 				sqlwhere += " order by " + me.getValue() ;
		 			}
		 			if("order".equals(me.getKey()) && !"".equals(me.getValue())){
		 				sqlwhere += " " + me.getValue();
		 			}	
		 		}
		 		if(currentPage >0 && pageSize>0){
		 			sqlwhere = sqlwhere +" limit " + (currentPage-1)*pageSize +" , "  + pageSize ;
		 		}
		   }catch(Exception e){
			   
		   }
		   
		return null;
	}
}
