package com.lesso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** * @author  Lance 
 * @date 创建时间：2016年1月5日 上午9:52:26 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public interface Z_Depart_Type_RightService {
   public void initAndSearch(HttpServletRequest request, HttpServletResponse response);
   public void saveorupdate(HttpServletRequest request, HttpServletResponse response);
   public void deleteZDepartRight(HttpServletRequest request, HttpServletResponse response);
   
}
