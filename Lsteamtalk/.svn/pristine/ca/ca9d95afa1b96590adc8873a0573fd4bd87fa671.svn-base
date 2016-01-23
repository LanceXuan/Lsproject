package com.lesso.service;

import java.util.List;
import java.util.Map;

import com.lesso.beans.IMGroup;



public interface IMGroupService {
	 public List<IMGroup> getIMGroupList(int currentPage, int pageSize,
				Map<String, Object> m);
	   public boolean saveIMGroup(String name,String avatar,
			   int creator,int type,int userCnt,int status,int version,
			   long lastchated,long created,long updated);
	   public boolean update(int id,String name,String avatar,
			   int creator,int type,int userCnt,int status,int version,
			   long lastchated);
	   public boolean update(IMGroup obj);
	   public boolean delete(int id);
	   public int getTotal(Map<String, Object> m);
}
