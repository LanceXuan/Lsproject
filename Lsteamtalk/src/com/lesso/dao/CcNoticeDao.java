package com.lesso.dao;

import java.util.List;
import java.util.Map;

import com.lesso.beans.CcNotice;
import com.lesso.beans.IMUser;

public interface CcNoticeDao extends BaseDao<CcNotice> {
	
	public int saveCcNotice(CcNotice ccNotice);
	
	public CcNotice getCcNotice(int id);
	
	public List<CcNotice> findByPagination(int currentPage, int pageSize  ,Map<String ,Object> m)
	 			throws Exception;
	public int getTotal(Map<String ,Object> m) throws Exception;
	    
	public boolean updateNotice(String Reviceor,String Title,String FirstImg,String content,String Sendor,int id);
	
	public boolean updateState(int id,int state);
	
	public boolean updateMarkRead(int id,String MarkRead);
	
	public boolean deleteByid(int id);
	
}
