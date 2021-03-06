package com.lesso.dao;

import java.util.List;
import java.util.Map;

import com.lesso.beans.IMGroup;

public interface IMGroupDao extends BaseDao<IMGroup>{
	public IMGroup findIMGroup(int id);
    public int saveIMGroup(IMGroup group);
    public List<IMGroup> findByPagination(int currentPage, int pageSize  ,Map<String ,Object> m)
 			throws Exception;
    public int getTotal(Map<String ,Object> m) throws Exception;
    public boolean updateIMGroup(IMGroup group);
    public boolean deleteIMGroup(int id);

}
