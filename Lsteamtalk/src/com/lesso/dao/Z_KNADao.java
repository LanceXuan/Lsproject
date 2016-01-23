package com.lesso.dao;

import java.util.List;
import java.util.Map;

import com.lesso.beans.Z_KNA;

/** * @author  Lance 
 * @date 创建时间：2016年1月5日 上午9:09:46 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public interface Z_KNADao extends BaseDao<Z_KNA>{
    public List<Z_KNA> getZ_KNAList(int currentPage, int pageSize  ,Map<String ,Object> m);
    public int getTotal(Map<String ,Object> m) throws Exception;
    public Z_KNA findbyKunrAndVkorg(String kunr,String vkorg);
    public boolean updatekua(Map<String,Object> m,String kunr,String vkorg);
    public boolean deletekua(String kunr,String vkorg);
    public boolean savekua(String kunr,String vkorg,String name,
    		String Login_id,String pwd,String area,String area_name);
}
