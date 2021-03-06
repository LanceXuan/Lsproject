package com.lesso.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.lesso.beans.IMGroup;
import com.lesso.dao.IMGroupDao;
import com.lesso.daoImpl.IMGroupDaoImpl;
import com.lesso.service.IMGroupService;
import com.lesso.util.DBUtils;
import com.lesso.util.StringUtil;

public class IMGroupServiceImpl implements IMGroupService{
        private IMGroupDao dao = new IMGroupDaoImpl();
	@Override
	public List<IMGroup> getIMGroupList(int currentPage, int pageSize,
			Map<String, Object> m) {
		try {
			List<IMGroup> list = dao.findByPagination(currentPage, pageSize, m);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}


	@Override
	public boolean update(int id,String name, String avatar, int creator, int type,
			int userCnt, int status, int version, long lastchated) {
		try{
			long time =	Calendar.getInstance().getTimeInMillis(); 
			String sql = "update IMDepart set updated = '"+time+"' ,";
			if(StringUtil.isNotEmpty(name)){
				sql += "name = '"+ name + "', ";
			}
			if(StringUtil.isNotEmpty(avatar)){
				sql += "avatar = '"+ avatar + "', ";
			}
			if(creator >= 0){
				sql += "creator ="+creator+", ";
			}
			if(type >= 0){
				sql += "type ="+type+", ";
			}
			if(userCnt >= 0){
				sql += "userCnt ="+userCnt+", ";
			}
			if(status >= 0){
				sql += "priority ="+status+", ";
			}
			if(version >= 0){
				sql += "version ="+version+", ";
			}
			if(lastchated >= 0){
				sql += "lastchated ="+lastchated+", ";
			}
			sql = sql.substring(0,sql.length()-1);
			if(id>0){
				sql+="  where id = "+id;
				Connection conn = DBUtils.createConn();
				PreparedStatement ps = DBUtils.getPs(conn, sql);
				ps.executeUpdate();
				DBUtils.close(ps);
				DBUtils.close(conn);	
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(IMGroup obj) {
		try {
			
			return dao.updateIMGroup(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
			
	}

	@Override
	public boolean delete(int id) {
		try {
			return dao.deleteIMGroup(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean saveIMGroup(String name, String avatar, int creator,
			int type, int userCnt, int status, int version, long lastchated,
			long created, long updated) {
		IMGroup mp = new IMGroup();
		mp.setCreated(created);
		mp.setName(name);
		mp.setAvatar(avatar);
		mp.setStatus(status);
		mp.setCreator(creator);
		mp.setLastchated(lastchated);
		mp.setType(type);
		mp.setUpdated(updated);
		mp.setUserCnt(userCnt);
		mp.setVersion(version);
		int ok = dao.saveIMGroup(mp);
		if(ok>0){
			return true;
		}else{
			return false;
		}
		
	}


	@Override
	public int getTotal(Map<String, Object> m) {
		try {
			
			return dao.getTotal(m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

}
