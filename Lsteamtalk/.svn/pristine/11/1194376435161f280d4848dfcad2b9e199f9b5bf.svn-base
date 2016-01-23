package com.lesso.dao;

import java.util.List;
import java.util.Map;

import com.lesso.beans.Z_User_Power_User;

/** * @author  Lance 
 * @date 创建时间：2016年1月5日 上午9:12:45 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public interface Z_User_Power_UserDao extends BaseDao<Z_User_Power_User> {
    public List<Z_User_Power_User> getZUPUList(int currentPage, int pageSize  ,Map<String ,Object> m);
    public int getTotal(Map<String ,Object> m) throws Exception;
    public boolean deleteZ_User_Power_User(String account_source,String account_source_type,String account_target,String account_target_type);
    public boolean saveZ_User_Power_User(String account_source,String account_source_type,String account_target,String account_target_type);
}
