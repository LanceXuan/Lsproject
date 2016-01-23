package com.lesso.dao;

import java.util.List;
import java.util.Map;


import com.lesso.beans.Z_Depart_Type_Right;

/** * @author  Lance 
 * @date 创建时间：2016年1月5日 上午8:57:30 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public interface Z_Depart_Type_RightDao extends BaseDao<Z_Depart_Type_Right>{
   public List<Z_Depart_Type_Right> getZ_Depart_Type_RightList(int ztype);
   public boolean saveDepartTypeRight(int departId,String departId_Text,int ztype,String ztype_text);
   public boolean updateDepartTypeRight(int departId,String departId_Text,int ztype,String ztype_text);
   public boolean deleteDepartTypeRight(int departId,int ztype);
   public List<Z_Depart_Type_Right> findByPagination(int currentPage, int pageSize  ,Map<String ,Object> m)
			throws Exception;
   public int getTotal(Map<String ,Object> m) throws Exception;
   
}
