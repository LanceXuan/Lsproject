package com.lesso.serviceImpl;

import java.util.List;
import java.util.Map;

import com.lesso.beans.IMMessage;
import com.lesso.dao.IMMessageDao;
import com.lesso.daoImpl.IMMessageDaoImpl;
import com.lesso.service.IMMessageService;

public class IMMessageServiceImpl implements IMMessageService{
         private IMMessageDao dao = new IMMessageDaoImpl();
	@Override
	public List<IMMessage> getIMMessageList(int currentPage, int pageSize,
			Map<String, Object> m) {
		try{
			return  dao.getIMMessageList(currentPage, pageSize, m);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	
	}

	@Override
	public int getTotal(Map<String, Object> m) {
		try{
			return dao.getTotal(m);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		
	}

}
