package com.lesso.quartz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.lesso.beans.IMDepart;
import com.lesso.dao.IMDepartDao;
import com.lesso.daoImpl.IMDepartDaoImpl;
import com.lesso.util.DateWorkUtil;
import com.lesso.util.SqlserverDBUtils;
import com.lesso.util.StringUtil;

/** * @author  Lance 
 * @date 创建时间：2016年1月12日 上午10:49:29 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class SyncDepartJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Log logs = LogFactory.getLog(SyncDepartJob.class);
		IMDepartDao dao = new IMDepartDaoImpl();
		try{
			Connection conn = SqlserverDBUtils.createConn();
			String sql = "select * ,(select dept_code from IFS_DEPARTMENT i where i.GUID =IFS_DEPARTMENT.PARENT_ID ) AS df from IFS_DEPARTMENT order by ORDINAL";
			PreparedStatement ps = SqlserverDBUtils.getPs(conn, sql);
 			ResultSet rs = ps.executeQuery();
 			List<IMDepart> list = new ArrayList<IMDepart>();
 			boolean isok = true;
 			while(rs.next()){
 				String departname = rs.getString("name");
 				String sapgsdm = rs.getString("dept_code");
 				String parentsapcode = "";
 				if(rs.getString("df") !=null){
 				 parentsapcode = rs.getString("df").equals("null")?"":rs.getString("df");
 				}
 				logs.info("部门名:"+departname);
 				logs.info("部门代码:"+sapgsdm);
 				long dds = DateWorkUtil.timechuo();
 				IMDepart idp = dao.findbySapcode(sapgsdm);
 				if(idp !=null){
 					if(!departname.equals(idp.getDepartName()) || !parentsapcode.equals(idp.getParentsapcode())){
 						list.add(idp);
 						dao.UpdateDepart(departname, -1, -1, -1, -1, sapgsdm, parentsapcode, idp.getId());
 					}else{
 						logs.info("部门"+sapgsdm+"不需要更新");
 					}
 				}else{
 					int idnew = dao.SyncAllDepart(departname, 0, 0, 0, dds, dds, 0, sapgsdm,parentsapcode);
 					if(idnew>0){
 					logs.info("新插入部门:"+departname+",代码是"+sapgsdm);
 					IMDepart idpk = new IMDepart();
 					idpk.setId(idnew);
 					idpk.setDepartName(departname);
 					idpk.setSapcode(sapgsdm);
 					idpk.setParentsapcode(parentsapcode);
 					list.add(idpk);
 					}else{
 						logs.info("新插入失败！！！部门:"+departname+",代码是"+sapgsdm);
 					}
 				}

 			}
            for(int i=0;i<list.size();i++){
				if(StringUtil.isNotEmpty(list.get(i).getParentsapcode()) && !"null".equals(list.get(i).getParentsapcode())){
                    IMDepart idp = dao.findbySapcode(list.get(i).getParentsapcode());
                    if(idp!=null){
                 	   dao.SyncParentDepart(list.get(i).getId(), idp.getId());
                 	   System.out.println("第"+i+"个:"+list.get(i).getId()+"的父亲是"+idp.getId());
                 	   logs.info("第"+i+"个:"+list.get(i).getId()+"的父亲是"+idp.getId());
                    }
                  }
            }
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Log logs = LogFactory.getLog(SyncDepartJob.class);
		IMDepartDao dao = new IMDepartDaoImpl();
		try{
			Connection conn = SqlserverDBUtils.createConn();
			String sql = "select * ,(select dept_code from IFS_DEPARTMENT i where i.GUID =IFS_DEPARTMENT.PARENT_ID ) AS df from IFS_DEPARTMENT order by ORDINAL";
			PreparedStatement ps = SqlserverDBUtils.getPs(conn, sql);
 			ResultSet rs = ps.executeQuery();
 			List<IMDepart> list = new ArrayList<IMDepart>();
 			boolean isok = true;
 			while(rs.next()){
 				String departname = rs.getString("name");
 				String sapgsdm = rs.getString("dept_code");
 				String parentsapcode = "";
 				if(rs.getString("df") !=null){
 				 parentsapcode = rs.getString("df").equals("null")?"":rs.getString("df");
 				}
 				logs.info("部门名:"+departname);
 				logs.info("部门代码:"+sapgsdm);
 				long dds = DateWorkUtil.timechuo();
 				IMDepart idp = dao.findbySapcode(sapgsdm);
 				if(idp !=null){
 					if(!departname.equals(idp.getDepartName()) || !parentsapcode.equals(idp.getParentsapcode())){
 						list.add(idp);
 						dao.UpdateDepart(departname, -1, -1, -1, -1, sapgsdm, parentsapcode, idp.getId());
 					}else{
 						logs.info("部门"+sapgsdm+"不需要更新");
 					}
 				}else{
 					int idnew = dao.SyncAllDepart(departname, 0, 0, 0, dds, dds, 0, sapgsdm,parentsapcode);
 					if(idnew>0){
 					logs.info("新插入部门:"+departname+",代码是"+sapgsdm);
 					IMDepart idpk = new IMDepart();
 					idpk.setId(idnew);
 					idpk.setDepartName(departname);
 					idpk.setSapcode(sapgsdm);
 					idpk.setParentsapcode(parentsapcode);
 					list.add(idpk);
 					}else{
 						logs.info("新插入失败！！！部门:"+departname+",代码是"+sapgsdm);
 					}
 				}

 			}
            for(int i=0;i<list.size();i++){
				if(StringUtil.isNotEmpty(list.get(i).getParentsapcode()) && !"null".equals(list.get(i).getParentsapcode())){
                    IMDepart idp = dao.findbySapcode(list.get(i).getParentsapcode());
                    if(idp!=null){
                 	   dao.SyncParentDepart(list.get(i).getId(), idp.getId());
                 	   System.out.println("第"+i+"个:"+list.get(i).getId()+"的父亲是"+idp.getId());
                 	   logs.info("第"+i+"个:"+list.get(i).getId()+"的父亲是"+idp.getId());
                    }
                  }
            }
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
