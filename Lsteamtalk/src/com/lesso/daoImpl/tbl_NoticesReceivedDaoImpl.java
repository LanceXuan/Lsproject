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

import com.lesso.beans.tbl_NoticesReceived;
import com.lesso.dao.tbl_NoticesReceivedDao;
import com.lesso.model.NoticeReadTime;
import com.lesso.util.DBUtils;
import com.lesso.util.StringUtil;

/** * @author  Lance 
 * @date 创建时间：2015年12月28日 上午10:06:38 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class tbl_NoticesReceivedDaoImpl extends BaseDaoImpl<tbl_NoticesReceived> implements tbl_NoticesReceivedDao {

	@Override
	public List<tbl_NoticesReceived> getNoticesReceivedList(int currentPage,
			int pageSize, Map<String, Object> m) throws Exception {

 		Connection conn = DBUtils.createConn();
 		String sql = " select * from tbl_NoticesReceived  where 1=1 ";
 		
 		Set<Entry<String, Object>> set = m.entrySet();
 		Iterator io = set.iterator();
 		while (io.hasNext()) {
 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
 			if("NoticeId".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("Status".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("id".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
 			}
 			if("UserId".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("ReceivetimeA".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and created  >= '" + me.getValue() +"'";
 			}
 			if("ReceivetimeB".equals(me.getKey()) && !"".equals(me.getValue())){
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
 		
 		List<tbl_NoticesReceived> ulist = new ArrayList<tbl_NoticesReceived>();
 		while(rs.next()){
 			tbl_NoticesReceived mp = new tbl_NoticesReceived();
 			mp.setCreated(rs.getString("created"));
 			mp.setId(Integer.parseInt(rs.getString("id")));
 			mp.setNoticeId(Integer.parseInt(rs.getString("noticeId")));
 			mp.setReceivetime(rs.getString("receivetime"));
 			String status = rs.getString("status") == null ? "-1" : rs.getString("status");
 			mp.setStatus(Integer.parseInt(status));
 			mp.setUserId(Integer.parseInt(rs.getString("userId")));   
			ulist.add(mp);
 		}
 		return ulist;
	}

	@Override
	public boolean updateNoticesReceived(int noticeid, int userid,
			int status, String receivetime) {
		try {
			Connection conn = DBUtils.createConn();
	 		String sql = "update tbl_NoticesReceived set ";
	 		boolean isuse = false;
	 		if(StringUtil.isNotEmpty(receivetime)){
	 			sql += " receivetime = '"+receivetime+"',";
	 			isuse = true;
	 		}
	 		if(status >0){
	 			sql += " status = '"+status+"',";
	 			isuse = true;
	 		}
	 		if(isuse){
	 		  sql = sql.substring(0, sql.length()-1);
	 		
	 		sql += " where noticeid = "+noticeid+" and userid ="+userid;
	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ps.executeUpdate();
			DBUtils.close(ps);
	 		}
			DBUtils.close(conn);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int getTotal(Map<String, Object> m) throws Exception{
		Connection conn = DBUtils.createConn();
 		String sql = " select count(*) from tbl_NoticesReceived  where 1=1 ";
 		
 		Set<Entry<String, Object>> set = m.entrySet();
 		Iterator io = set.iterator();
 		while (io.hasNext()) {
 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
 			if("NoticeId".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("Status".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("id".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
 			}
 			if("UserId".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("ReceivetimeA".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and created  >= '" + me.getValue() +"'";
 			}
 			if("ReceivetimeB".equals(me.getKey()) && !"".equals(me.getValue())){
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
	public List<NoticeReadTime> getNoticeReadTimeList(int currentPage,
			int pageSize, Map<String, Object> m) throws Exception {
 		Connection conn = DBUtils.createConn();
 		String sql = " select *,(SELECT nick FROM IMUser WHERE id= tbl_NoticesReceived.UserId) as nick"
 				+ " from tbl_NoticesReceived  where 1=1 ";
 		
 		Set<Entry<String, Object>> set = m.entrySet();
 		Iterator io = set.iterator();
 		while (io.hasNext()) {
 			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
 			if("NoticeId".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("Status".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("id".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " in ("+ me.getValue()  +")" ;
 			}
 			if("UserId".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and " + me.getKey() + " = '" + me.getValue() +"'";
 			}
 			if("ReceivetimeA".equals(me.getKey()) && !"".equals(me.getValue())){
 				sql += " and created  >= '" + me.getValue() +"'";
 			}
 			if("ReceivetimeB".equals(me.getKey()) && !"".equals(me.getValue())){
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
 		
 		List<NoticeReadTime> ulist = new ArrayList<NoticeReadTime>();
 		while(rs.next()){
 			NoticeReadTime mp = new NoticeReadTime();
 			mp.setCreated(rs.getString("created"));
 			mp.setId(Integer.parseInt(rs.getString("id")));
 			mp.setNoticeId(Integer.parseInt(rs.getString("noticeId")));
 			mp.setReceivetime(rs.getString("receivetime"));
 			String status = rs.getString("status") == null ? "-1" : rs.getString("status");
 			mp.setStatus(Integer.parseInt(status));
 			mp.setUserId(Integer.parseInt(rs.getString("userId")));
 			mp.setNick(rs.getString("nick"));
			ulist.add(mp);
 		}
 		return ulist;
	}

}
