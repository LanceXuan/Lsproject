package com.lesso.dao;

import java.util.List;
import java.util.Map;

import com.lesso.beans.tbl_NoticesReceived;
import com.lesso.model.NoticeReadTime;

/** * @author  Lance 
 * @date 创建时间：2015年12月26日 下午3:12:50 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public interface tbl_NoticesReceivedDao extends BaseDao<tbl_NoticesReceived> {
     public List<tbl_NoticesReceived> getNoticesReceivedList(int currentPage, int pageSize  ,Map<String ,Object> m)
  			throws Exception;
     public List<NoticeReadTime> getNoticeReadTimeList(int currentPage, int pageSize  ,Map<String ,Object> m)
  			throws Exception;
     public boolean updateNoticesReceived(int noticeid,int userid,int status,String receivetime);
     public int getTotal(Map<String ,Object> m) throws Exception;
}
