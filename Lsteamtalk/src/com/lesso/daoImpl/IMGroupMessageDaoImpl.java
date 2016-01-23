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

import com.lesso.beans.IMGroupMessage;
import com.lesso.dao.IMGroupMessageDao;
import com.lesso.util.DBUtils;
import com.lib.Security;

public class IMGroupMessageDaoImpl extends BaseDaoImpl<IMGroupMessage> implements IMGroupMessageDao{

	@Override
	public List<IMGroupMessage> getIMGroupMessageList(int currentPage,
			int pageSize, Map<String, Object> m) throws Exception {
		try{
			Connection conn = DBUtils.createConn();
			
	 		String Id= (String) m.get("groupId");
	 		int a = Integer.parseInt(Id)%8;
	 	    String tablename = "IMGroupMessage_"+a;
	 	    System.out.println(tablename);
	 		String sql = " select *,(SELECT nick FROM IMUser WHERE id= "+tablename+".userId) AS nick  from "+tablename+"  where 1=1 ";

	 		Set<Entry<String, Object>> set = m.entrySet();
	 		Iterator io = set.iterator();
	 		
	 		while (io.hasNext()) {
	 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
	 			if("id".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
	 			}
	 			if("groupId".equals(me.getKey()) && !"".equals(me.getValue())){
	 				sql += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
	 			}
	 			if("userId".equals(me.getKey()) && !"".equals(me.getValue())){
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
	 		
	 		List<IMGroupMessage> ulist = new ArrayList<IMGroupMessage>();
	 		String content ="";
	 		while(rs.next()){
	 			IMGroupMessage mp = new IMGroupMessage();
	 			content=Security.getInstance().DecryptMsg(rs.getString("content"));
                mp.setContent(content);
                mp.setCreated(Long.parseLong(rs.getString("created")));
                mp.setGroupId(Integer.parseInt(rs.getString("groupId")));
                mp.setId(Integer.parseInt(rs.getString("id")));
                mp.setMsgId(Integer.parseInt(rs.getString("msgId")));
                mp.setStatus(Integer.parseInt(rs.getString("status")));
                mp.setType(Integer.parseInt(rs.getString("type")));
                mp.setUpdated(Long.parseLong(rs.getString("updated")));
                mp.setUserId(Integer.parseInt(rs.getString("userId")));
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
 		String Id= (String) m.get("groupId");
 		int a = Integer.parseInt(Id)%8;
 	    String tablename = "IMGroupMessage_"+a;
 	    System.out.println(tablename);
 		String sql = " select count(*) from "+tablename+"  where 1=1 ";
 		
 		
 		Set<Entry<String, Object>> set = m.entrySet();
 		Iterator io = set.iterator();
 		while (io.hasNext()) {
 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
 			if("id".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
 			}
 			if("groupId".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
 			}
 			if("userId".equals(me.getKey()) && !"".equals(me.getValue())){
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

}
