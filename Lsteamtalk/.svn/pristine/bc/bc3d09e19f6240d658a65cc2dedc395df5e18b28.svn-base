package com.lesso.dao;

import java.util.List;
import java.util.Map;

import com.lesso.beans.IMDiscovery;


public interface IMDiscoveryDao extends BaseDao<IMDiscovery>{
	public IMDiscovery findIMDiscovery(String username);
    public IMDiscovery findIMDiscoveryById(int id);
    public int saveIMDiscovery(IMDiscovery imd);
    public List<IMDiscovery> findByPagination(int currentPage, int pageSize  ,Map<String ,Object> m)
 			throws Exception;
    public int getTotal(Map<String ,Object> m) throws Exception;
    public boolean updateIMDiscovery(IMDiscovery imu);
    public boolean deleteIMDiscovery(int id);
    public boolean updatestate(int id);
}
