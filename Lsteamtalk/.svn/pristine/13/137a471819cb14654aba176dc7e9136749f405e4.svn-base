package com.lesso.serviceImpl;

import java.util.List;
import java.util.Map;

import com.lesso.beans.IMDiscovery;
import com.lesso.dao.IMDiscoveryDao;
import com.lesso.daoImpl.IMDiscoveryDaoImpl;
import com.lesso.service.IMDiscoveryService;

public class IMDiscoveryServiceImpl implements IMDiscoveryService{
       
	private IMDiscoveryDao dao = new IMDiscoveryDaoImpl();
	@Override
	public List<IMDiscovery> getIMDiscovery(int currentPage, int pageSize,
			Map<String, Object> m) {
		try {
			return dao.findByPagination(currentPage, pageSize, m);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public boolean saveIMDiscovery(IMDiscovery imu) {
		try{
		 int isok = dao.saveIMDiscovery(imu);
		 if(isok>0){
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
	public boolean update(IMDiscovery obj) {
		try{
              return dao.updateIMDiscovery(obj);
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
	}

	@Override
	public boolean delete(int id) {
		try{
            return dao.deleteIMDiscovery(id);
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
	}

	@Override
	public int getTotal(Map<String, Object> m) {
		try {
			return dao.getTotal(m);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
