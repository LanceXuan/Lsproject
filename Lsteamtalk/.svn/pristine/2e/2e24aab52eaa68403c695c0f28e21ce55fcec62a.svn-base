package com.lesso.dao;

import java.util.List;
import java.util.Map;

import com.lesso.beans.IMUser;

public interface IMUserDao extends BaseDao<IMUser> {
    public IMUser findIMUser(String username);
    public IMUser findIMUserById(int id);
    public int saveIMUser(IMUser imuser);
    public List<IMUser> findByPagination(int currentPage, int pageSize  ,Map<String ,Object> m)
 			throws Exception;
    public int getTotal(Map<String ,Object> m) throws Exception;
    public boolean updateIMUser(IMUser imuser);
    public boolean deleteIMUser(int id);
    public boolean updatestate(int id);
    public boolean uodateavatar(String username,String photo);
    public boolean updateIMUserPwd(String pwd,int id);
    public boolean updateIMUser(String sign_info,String sex,String nick,String phone,
    		String email,String departId,String photo,int id,int type);
    public boolean updateIMUserpwd(String name,String pwd,int id,int status);
    public List<IMUser> findIMUserBydepartId(int departId);
    public List<IMUser> findIMuserByUserName(String searchText);
    public int syncuser(int sex,String name,String domain,String nick,String password,
    		String salt,String phone,String email,int departId,int status,
    		int push_shield_status,String sign_info,int type,String sap_vkorg,String sap_kunnr,String sap_kunnr_father,
    		String sapbm,String sapgh,String oanum);
    
    public boolean syncupdateuser(Map<String ,Object> m,int id);
    
}
