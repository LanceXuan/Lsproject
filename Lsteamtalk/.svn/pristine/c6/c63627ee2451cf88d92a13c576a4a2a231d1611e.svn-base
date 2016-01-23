package com.lesso.serviceImpl;

import java.util.List;
import java.util.Map;







import com.lesso.beans.IMGroupMessage;
import com.lesso.dao.IMGroupMessageDao;
import com.lesso.daoImpl.IMGroupMessageDaoImpl;
import com.lesso.service.IMGroupMessageService;

public class IMGroupMessageServiceImpl implements IMGroupMessageService{
    private IMGroupMessageDao dao = new IMGroupMessageDaoImpl();
	@Override
	public List<IMGroupMessage> getIMGroupMessageList(int currentPage, int pageSize,
			Map<String, Object> m) {
		try {
			List<IMGroupMessage> list = dao.getIMGroupMessageList(currentPage, pageSize, m);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int getTotal(Map<String, Object> m)  {
	  try {
			return dao.getTotal(m);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
