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

import com.lesso.beans.CcNotice;
import com.lesso.dao.CcNoticeDao;
import com.lesso.util.DBUtils;
import com.lesso.util.DateWorkUtil;
import com.lesso.util.StringUtil;

public class CcNoticeDaoImpl extends BaseDaoImpl<CcNotice> implements
		CcNoticeDao {

	@Override
	public int saveCcNotice(CcNotice ccNotice) {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtils.createConn();
			String sql = "insert into Notices (title,content,FirstImg,Sendor,Reviceor,readStatus,created,state)"
					+ " values('"
					+ ccNotice.getTitle()
					+ "','"
					+ ccNotice.getContent()
					+ "','"
					+ ccNotice.getFirstImg()
					+ "',"
					+ "'"
					+ ccNotice.getSendor()
					+ "','"
					+ ccNotice.getReviceor()
					+ "','"
					+ ccNotice.getReadStatus()
					+ "','" + ccNotice.getCreated() + "','0')";
			Statement st = conn.createStatement();
			int ds = st.executeUpdate(sql);
			int id = 0;
			ResultSet rs = st.getGeneratedKeys(); 
			if (rs != null&&rs.next()) {  
			    id=rs.getInt(1);  
			}  
			DBUtils.close(conn);
			if (st != null) {
				st.close();
			}
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public CcNotice getCcNotice(int id) {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtils.createConn();
			String sql = "select * from Notices where id=" + id;
			PreparedStatement ps = DBUtils.getPs(conn, sql);
			ResultSet rs = ps.executeQuery();
			CcNotice mp = null;
			while (rs.next()) {
				mp = new CcNotice();
				mp.setCreated(rs.getString("Created"));
				mp.setTitle(rs.getString("title"));
				mp.setId(Integer.parseInt(rs.getString("id")));
				mp.setContent(rs.getString("content"));
				mp.setSendor(rs.getString("sendor"));
				mp.setReviceor(rs.getString("reviceor"));
				mp.setReadStatus(Integer.parseInt(rs.getString("readStatus")));
				mp.setUpdated(rs.getString("updated"));
				mp.setMarkRead(rs.getString("markRead"));
				String state = rs.getString("state") == null ? "-1" : rs.getString("state");
				mp.setState(Integer.parseInt(state));
				
			}
			DBUtils.close(ps);
			DBUtils.close(conn);
			if (rs != null) {
				rs.close();
			}
			return mp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CcNotice> findByPagination(int currentPage, int pageSize,
			Map<String, Object> m) throws Exception {
		Connection conn = DBUtils.createConn();
		String sql = " select * from Notices  where 1=1 and state = '0'";

		Set<Entry<String, Object>> set = m.entrySet();
		Iterator io = set.iterator();
		while (io.hasNext()) {
			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
			if ("title".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " like '%" + me.getValue()
						+ "%'";
			}
			if ("createdA".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and created  >= '" + me.getValue() + "'";
			}
			if ("createdB".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and created  <= '" + me.getValue() + "'";
			}
			if ("sort".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " order by " + me.getValue();
			}
			if ("order".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " " + me.getValue();
			}
		}
		if (currentPage > 0 && pageSize > 0) {
			sql = sql + " limit " + (currentPage - 1) * pageSize + " , "
					+ pageSize;
		}
		PreparedStatement ps = DBUtils.getPs(conn, sql);
		ResultSet rs = ps.executeQuery();

		List<CcNotice> ulist = new ArrayList<CcNotice>();
		while (rs.next()) {
			CcNotice mp = new CcNotice();
			mp.setCreated(rs.getString("Created"));
			mp.setTitle(rs.getString("title"));
			mp.setId(Integer.parseInt(rs.getString("id")));
			// mp.setContent(rs.getString("content"));
			mp.setSendor(rs.getString("sendor"));
			mp.setReviceor(rs.getString("reviceor"));
			mp.setReadStatus(Integer.parseInt(rs.getString("readStatus")));
			mp.setUpdated(rs.getString("updated"));
			mp.setMarkRead(rs.getString("markRead"));
			String state = rs.getString("state") == null ? "-1" : rs.getString("state");
			mp.setState(Integer.parseInt(state));
			ulist.add(mp);
		}
		return ulist;
	}

	@Override
	public int getTotal(Map<String, Object> m) throws Exception {
		Connection conn = DBUtils.createConn();
		String sql = " select count(*) from Notices  where 1=1 ";

		Set<Entry<String, Object>> set = m.entrySet();
		Iterator io = set.iterator();
		while (io.hasNext()) {
			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io
					.next();
			if ("title".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " like '%" + me.getValue()
						+ "%'";
			}
			if ("createdA".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and created  >= '" + me.getValue() + "'";
			}
			if ("createdB".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and created  <= '" + me.getValue() + "'";
			}
			if ("sort".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " order by " + me.getValue();
			}
			if ("order".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " " + me.getValue();
			}
		}
		PreparedStatement ps = DBUtils.getPs(conn, sql);
		ResultSet rs = ps.executeQuery();
		int count = 0;
		if (rs.next()) {

			count = rs.getInt(1);
		}
		return count;
	}

	@Override
	public boolean updateNotice(String Reviceor, String Title, String FirstImg,
			String content, String Sendor,int id) {
		Log logs = LogFactory.getLog(CcNoticeDaoImpl.class);
		try{
			Connection conn = DBUtils.createConn();
			String now = DateWorkUtil.stringToDatenow();
			String sql="Update Notices set updated ='"+now+"',";
			if(StringUtil.isNotEmpty(FirstImg)){
				sql +=" FirstImg='"+FirstImg+"',";
			}
            if(StringUtil.isNotEmpty(Reviceor)){
            	sql +=" Reviceor='"+Reviceor+"',";
			}
            if(StringUtil.isNotEmpty(Title)){
            	sql +=" Title='"+Title+"',";
			}
            if(StringUtil.isNotEmpty(content)){
            	sql +=" content='"+content+"',";
			}
            if(StringUtil.isNotEmpty(Sendor)){
            	sql +=" Sendor='"+Sendor+"',";
			}
            sql = sql.substring(0, sql.length()-1);
	 		sql += " where id = "+id;
	 		logs.info("sql is "+sql);
	 		PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ps.executeUpdate();
			DBUtils.close(ps);
			DBUtils.close(conn);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			logs.info("方法updateNotice报错,原因是"+e.getMessage());
			return false;
		}

	}

	@Override
	public boolean updateState(int id, int state) {
		Log logs = LogFactory.getLog(CcNoticeDaoImpl.class);
		try{
			Connection conn = DBUtils.createConn();
			String now = DateWorkUtil.stringToDatenow();
			String sql="Update Notices set updated ='"+now+"',state ='"+state+"' where id="+id;
			logs.info("sql is "+sql);
			PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ps.executeUpdate();
			DBUtils.close(ps);
			DBUtils.close(conn);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			logs.info("方法updateNotice报错,原因是"+e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateMarkRead(int id, String MarkRead) {
		Log logs = LogFactory.getLog(CcNoticeDaoImpl.class);
		try{
			Connection conn = DBUtils.createConn();
			String now = DateWorkUtil.stringToDatenow();
//			String sql="Update Notices set updated ='"+now+"',MarkRead =CONCAT(MarkRead,'"+MarkRead+"') where id="+id;
			String sql="Update Notices set updated ='"+now+"',MarkRead ='"+MarkRead+"' where id="+id;
			logs.info("sql is "+sql);
			PreparedStatement ps = DBUtils.getPs(conn, sql);
	 		ps.executeUpdate();
			DBUtils.close(ps);
			DBUtils.close(conn);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			logs.info("方法updateNotice报错,原因是"+e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteByid(int id) {
		// TODO Auto-generated method stub
		Log logs = LogFactory.getLog(CcNoticeDaoImpl.class);
		try {
			Connection conn = DBUtils.createConn();
			String sql="update Notices set state = '3' where id="+id;
			logs.info("sql is "+sql);
			PreparedStatement ps = DBUtils.getPs(conn, sql);
			ps.executeUpdate();
			DBUtils.close(ps);
			DBUtils.close(conn);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

}
