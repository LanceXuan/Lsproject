package com.lesso.dao;

import com.lesso.beans.IMAdmin;

public interface IMAdminDao extends BaseDao<IMAdmin>{
	
	public IMAdmin findIMAdmin(String username);
	public boolean saveIMAdmin(IMAdmin imadmin);

}
