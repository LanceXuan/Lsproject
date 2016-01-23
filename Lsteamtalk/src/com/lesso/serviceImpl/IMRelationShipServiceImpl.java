package com.lesso.serviceImpl;

import com.lesso.beans.IMRelationShip;
import com.lesso.dao.IMRelationShipDao;
import com.lesso.daoImpl.IMRelationShipDaoImpl;
import com.lesso.service.IMRelationShipService;

/** * @author  Lance 
 * @date 创建时间：2015年12月21日 下午4:30:29 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class IMRelationShipServiceImpl implements IMRelationShipService{
     private IMRelationShipDao dao = new IMRelationShipDaoImpl();
	@Override
	public IMRelationShip findbyfromto(int fromid, int toid) {
		if(fromid<toid)
		return dao.findbyfromto(fromid, toid);
		else
	    return dao.findbyfromto(fromid, toid);	
	}

}
