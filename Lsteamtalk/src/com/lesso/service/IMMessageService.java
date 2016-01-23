package com.lesso.service;

import java.util.List;
import java.util.Map;

import com.lesso.beans.IMMessage;

public interface IMMessageService {
	public List<IMMessage> getIMMessageList(int currentPage, int pageSize,
			Map<String, Object> m);
	   public int getTotal(Map<String, Object> m);
}
