package com.lesso.dao;

import java.util.List;
import java.util.Map;

import com.lesso.beans.IMDepart;
import com.lesso.model.TreeDTO;

public interface IMDepartDao extends BaseDao<IMDepart>{
	   public IMDepart findIMDepart(int id);
	    public int saveIMDepart(IMDepart obj);
	    public List<IMDepart> findByPagination(int currentPage, int pageSize  ,Map<String ,Object> m)
	 			throws Exception;
	    public int getTotal(Map<String ,Object> m) throws Exception;
	    public boolean updateIMAdmin(IMDepart imdep);
	    public boolean deleteIMAdmin(int id);
	    public List<TreeDTO> getTreeDTO(Map<String ,Object> m);
	    public List<IMDepart> getChildren(int pid) throws Exception;
	    public boolean updateleader(int id,int leader);
	    public boolean saveDepart(String departName,int priority,int parentid,int status,long created,long updated,int leader,String sapcode);
	    public boolean SyncParentDepart(int id,int parentid);
	    public int SyncAllDepart(String departName, int priority, int parentid,
				int status, long created, long updated, int leader,String sapcode,String parentsapcode);
	    public IMDepart findbyParentSapcode(String sapcode);
	    public IMDepart findbySapcode(String sapcode);
	    public boolean UpdateDepart(String departName, int priority, int parentid,
				int status, int leader,String sapcode,String parentsapcode,int id);
}
