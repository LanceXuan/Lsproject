package com.lesso.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lesso.beans.tbl_NoticesReceived;
import com.lesso.dao.tbl_NoticesReceivedDao;
import com.lesso.daoImpl.tbl_NoticesReceivedDaoImpl;
import com.lesso.model.NoticeRead;
import com.lesso.model.NoticeReadTime;
import com.lesso.service.tbl_NoticesReceivedService;
import com.lesso.util.DateWorkUtil;
import com.lesso.util.ResponseUtil;

/** * @author  Lance 
 * @date 创建时间：2015年12月28日 上午11:25:42 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class tbl_NoticesReceivedServiceImpl implements tbl_NoticesReceivedService {
  private tbl_NoticesReceivedDao dao = new tbl_NoticesReceivedDaoImpl(); 
	@Override
	public void getNoticesReceivedList(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			String id =  request.getParameter("id") == null ? "-1": request.getParameter("id");
			String status =  request.getParameter("status") == null ? "-1": request.getParameter("status");
			Map<String ,Object> m = new HashMap<String, Object>();
			m.put("NoticeId", Integer.parseInt(id));
			m.put("Status", Integer.parseInt(status));
			String page = request.getParameter("page") == null ? "-1" : request.getParameter("page");
			String rows = request.getParameter("rows") == null ? "-1" : request.getParameter("rows");
		    List<NoticeReadTime> list = dao.getNoticeReadTimeList(Integer.parseInt(page), Integer.parseInt(rows), m);
		    int total = dao.getTotal(m);
            ResponseUtil.toPaginationJson(response, list, total);   
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateReceivedNotices(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			String noticeId = request.getParameter("iceId") == null ? ""
					: request.getParameter("iceId");
			String uid =  request.getParameter("suid") == null ? ""
					: request.getParameter("suid");
		    boolean isok=dao.updateNoticesReceived(Integer.parseInt(noticeId), Integer.parseInt(uid), 1, DateWorkUtil.stringToDatenow());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		try{
			
		}catch(Exception e){
			
		}
		
	}

	@Override
	public void save(tbl_NoticesReceived obj) {
		try {
			dao.save(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
