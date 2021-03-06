package com.lesso.service;

import java.util.List;
import java.util.Map;

import com.lesso.beans.IMDepart;
import com.lesso.model.TreeDTO;

public interface IMDepartService {
   public List<IMDepart> getDepList(int currentPage, int pageSize,
			Map<String, Object> m);
   public boolean saveDepart(String departName,int priority,int parentid,int status,long created,
		   long updated,int leader,String sapcode);
   public boolean update(int id,String departName,int priority,int parentid,int status,int leader);
   public boolean update(IMDepart obj);
   public boolean delete(int id);
   public List<TreeDTO> getTreeDTO(Map<String ,Object> m);
   public boolean updateleader(int id,int leader);
}
