package com.lesso.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lesso.beans.IMRelationShip;
import com.lesso.dao.IMRelationShipDao;
import com.lesso.util.DBUtils;

/** * @author  Lance 
 * @date 创建时间：2015年12月21日 下午4:17:12 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class IMRelationShipDaoImpl extends BaseDaoImpl<IMRelationShip> implements IMRelationShipDao {

	@Override
	public IMRelationShip findbyfromto(int fromid, int toid) {
		Connection conn = DBUtils.createConn();
		try{
		  String sql = "select * from IMRelationShip where smallId="+fromid+" and bigId ="+toid;
		  PreparedStatement ps = DBUtils.getPs(conn, sql);
		  ResultSet rs = ps.executeQuery();
		  IMRelationShip mp = null;
		  while(rs.next()){
			  mp = new IMRelationShip();
			  mp.setBigId(Integer.parseInt(rs.getString("bigId")));
			  mp.setCreated(Integer.parseInt(rs.getString("created")));
			  mp.setId(Integer.parseInt(rs.getString("id")));
			  mp.setSmallId(Integer.parseInt(rs.getString("smallId")));
			  mp.setStats(Integer.parseInt(rs.getString("status")));
			  mp.setUpdated(Integer.parseInt(rs.getString("updated")));
		  }
		  DBUtils.close(ps);
			DBUtils.close(conn);
		  return mp;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
				
		
	}

}
