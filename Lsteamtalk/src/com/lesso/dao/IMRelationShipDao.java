package com.lesso.dao;

import com.lesso.beans.IMRelationShip;

/** * @author  Lance 
 * @date 创建时间：2015年12月21日 下午4:12:01 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public interface IMRelationShipDao extends BaseDao<IMRelationShip>{
	
	public IMRelationShip findbyfromto(int fromid,int toid);

}
