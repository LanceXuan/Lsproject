package com.lesso.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lesso.beans.CcNotice;
import com.lesso.beans.IMUser;
import com.lesso.model.NoticeRead;

public interface CcNoticeService {
	
	public int saveCcNotice(CcNotice ccNotice);
	
	public CcNotice getCcNotice(int noticeId);
	
	public List<CcNotice> findByPagination(int currentPage, int pageSize  ,Map<String ,Object> m)
	 			throws Exception;
	
	public int getTotal(Map<String ,Object> m) throws Exception;
	
	public boolean updateMarkRead(String uid,int id);
	
	public void getNoticeReadList(HttpServletRequest request,
			HttpServletResponse response);
	//根据IDString获取人员信息
	public List<IMUser> getIMUserListByIdString(String idString);
	
	public boolean deleteCcNoticeByIds(String ids);
}
