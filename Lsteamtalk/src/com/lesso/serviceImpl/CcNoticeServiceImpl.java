package com.lesso.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lesso.beans.CcNotice;
import com.lesso.beans.IMUser;
import com.lesso.dao.CcNoticeDao;
import com.lesso.dao.IMUserDao;
import com.lesso.daoImpl.CcNoticeDaoImpl;
import com.lesso.daoImpl.IMUserDaoImpl;
import com.lesso.model.NoticeRead;
import com.lesso.service.CcNoticeService;
import com.lesso.util.ResponseUtil;
import com.lesso.util.StringUtil;

public class CcNoticeServiceImpl implements CcNoticeService{
	private CcNoticeDao dao = new CcNoticeDaoImpl();
	private IMUserDao imuserdao = new IMUserDaoImpl();
	@Override
	public int saveCcNotice(CcNotice ccNotice) {
		// TODO Auto-generated method stub
		int isok = dao.saveCcNotice(ccNotice);
		//处理结果
        if(isok>0){
            System.out.println("操作成功");
        }else{
            System.out.println("操作失败");
        }
        return isok;
	}

	@Override
	public CcNotice getCcNotice(int noticeId) {
		// TODO Auto-generated method stub
		CcNotice notice = new CcNotice();
		try {
			notice = dao.getCcNotice(noticeId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notice;
	}

	@Override
	public List<CcNotice> findByPagination(int currentPage, int pageSize,
			Map<String, Object> m) throws Exception {
		return dao.findByPagination(currentPage, pageSize, m);
	}

	@Override
	public int getTotal(Map<String, Object> m) throws Exception {
		return dao.getTotal(m);
	}

	@Override
	public boolean updateMarkRead(String uid, int id) {
        Log logs = LogFactory.getLog(CcNoticeServiceImpl.class);
		try{
			CcNotice notice = dao.getCcNotice(id);
            logs.info("更新公告已读状态方法开始执行,公告Id为"+id+",已读人Id为"+uid);
			boolean iswork = false;
			if(notice != null){
				if(StringUtil.isNotEmpty(notice.getMarkRead())){
					String[] arr = notice.getMarkRead().split(",");
					if(!StringUtil.IsinStringArr(arr, uid)){
					   logs.info("多次阅读uid is"+uid);
					   uid =notice.getMarkRead()+","+uid;
					   iswork = dao.updateMarkRead(id, uid);
					}
				}else{
				   iswork = dao.updateMarkRead(id, uid);
				}
				logs.info("更新公告已读状态方法的成功状态是"+iswork);
			}
			return iswork;
		}catch(Exception e){
			e.printStackTrace();
			logs.info("更新公告已读状态方法异常:"+e.getMessage());
			return false;
		}
		
	}
	
	
	public void getNoticeReadList(HttpServletRequest request,
			HttpServletResponse response){
		Log logs = LogFactory.getLog(CcNoticeServiceImpl.class);
		try{
			String id =  request.getParameter("id") == null ? "": request.getParameter("id");
			CcNotice notice = dao.getCcNotice(Integer.parseInt(id));
            logs.info("更新公告已读列表,公告Id为"+id);
            String Reviceor = notice.getReviceor();
            String[] MarkRead = null;
            if(StringUtil.isNotEmpty(notice.getMarkRead())){
            	MarkRead = notice.getMarkRead().split(",");
            }
  //          String[] MarkRead = notice.getMarkRead().split(",");
            Map<String, Object> m = new HashMap<String, Object>();
			if (StringUtil.isNotEmpty(Reviceor)) {
				m.put("id", Reviceor);
			}
			m.put("status", 0);
            List<IMUser> list = imuserdao.findByPagination(-1, -1, m);
            int total = imuserdao.getTotal(m);
            List<NoticeRead> returnlist = new ArrayList<NoticeRead>();
            for(int i=0;i<list.size();i++){
            	NoticeRead nr = new NoticeRead();
            	if(MarkRead != null){
	            	if(StringUtil.IsinStringArr(MarkRead, String.valueOf(list.get(i).getId()))){
	                     nr.setId(list.get(i).getId());
	                     nr.setNick(list.get(i).getNick());
	                     nr.setStatus(1);
				     }else{
				    	 nr.setId(list.get(i).getId());
	                     nr.setNick(list.get(i).getNick());
	                     nr.setStatus(0);	
				     }
            	}else{
			    	 nr.setId(list.get(i).getId());
                     nr.setNick(list.get(i).getNick());
                     nr.setStatus(0);	
			     }
            	returnlist.add(nr);
            }
            ResponseUtil.toPaginationJson(response, returnlist, total);  
		}catch(Exception e){
			e.printStackTrace();
			logs.info("更新公告已读列表异常:"+e.getMessage());
		
		}
		
	}

	@Override
	public List<IMUser> getIMUserListByIdString(String idString) {
		// TODO Auto-generated method stub
		List<IMUser> userList = new ArrayList<IMUser>();
		try {
			Map<String ,Object> m = new HashMap<String, Object>();
			m.put("id", idString);
			userList = imuserdao.findByPagination(-1, -1, m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public boolean deleteCcNoticeByIds(String ids) {
		// TODO Auto-generated method stub
		String[] id = ids.split(",");
		try {
			for(int i=0;i<id.length;i++){
				dao.deleteByid(Integer.valueOf(id[i]));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
