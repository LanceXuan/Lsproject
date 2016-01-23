package com.lesso.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.lesso.beans.IMDepart;
import com.lesso.dao.IMDepartDao;
import com.lesso.daoImpl.IMDepartDaoImpl;
import com.lesso.model.TreeDTO;
import com.lesso.service.IMDepartService;
import com.lesso.util.DBUtils;
import com.lesso.util.DateWorkUtil;
import com.lesso.util.StringUtil;

public class IMDepartServiceImpl implements IMDepartService{
     private IMDepartDao dao = new IMDepartDaoImpl();
	@Override
	public List<IMDepart> getDepList(int currentPage, int pageSize,
			Map<String, Object> m) {
		try {
			List<IMDepart> list = dao.findByPagination(currentPage, pageSize, m);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public boolean saveDepart(String departName, int priority, int parentid,
			int status, long created, long updated,int leader,String sapcode) {
		return dao.saveDepart(departName, priority, parentid, status, created, updated, leader,sapcode);   
	}

	@Override
	public boolean update(int id,String departName, int priority, int parentid,
			int status,int leader) {
		try{
			long time =DateWorkUtil.timechuo();
			String sql = "update IMDepart set updated = '"+time+"' ,";
			if(StringUtil.isNotEmpty(departName)){
				sql += "departName = '"+ departName + "',";
			}
			if(priority >= 0){
				sql += "priority ="+priority+",";
			}
			if(parentid >= 0){
				sql += "parentid ="+parentid+",";
			}
			if(status >= 0){
				sql += "status ="+status+",";
			}
			if(leader > 0){
				sql += "leader ="+leader+",";
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
	public boolean update(IMDepart obj) {
       boolean isok = dao.updateIMAdmin(obj);  
		return isok;
	}

	@Override
	public boolean delete(int id) {
		boolean isok = dao.deleteIMAdmin(id);
		return isok;
	}

	@Override
	public List<TreeDTO> getTreeDTO(Map<String, Object> m) {
		try {
			List<TreeDTO> list = dao.getTreeDTO(m);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateleader(int id, int leader) {
		return dao.updateleader(id, leader);
	}

}
