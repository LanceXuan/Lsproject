package com.lesso.dao;

import java.util.List;
import java.util.Map;

import com.lesso.beans.IMMessage;

public interface IMMessageDao extends BaseDao<IMMessage> {
	public List<IMMessage> getIMMessageList(int currentPage,
			int pageSize  ,Map<String ,Object> m) throws Exception;
    public int getTotal(Map<String ,Object> m) throws Exception;
    public List<IMMessage> UNIONALLIMMessageList(int currentPage, int pageSize,
    		Map<String, Object> m)throws Exception;
}
