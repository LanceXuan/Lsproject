package com.lesso.service;

import java.util.List;
import java.util.Map;

import com.lesso.beans.IMGroupMessage;



public interface IMGroupMessageService {
	public List<IMGroupMessage> getIMGroupMessageList(int currentPage, int pageSize,
			Map<String, Object> m);
	   public int getTotal(Map<String, Object> m);
}
