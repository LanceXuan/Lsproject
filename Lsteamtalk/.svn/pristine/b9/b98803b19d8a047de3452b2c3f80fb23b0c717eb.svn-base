package com.lesso.sync;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;








import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lesso.beans.IMDepart;
import com.lesso.dao.IMDepartDao;
import com.lesso.daoImpl.IMDepartDaoImpl;
import com.lesso.util.DateWorkUtil;
import com.lesso.util.SqlserverDBUtils;
import com.lesso.util.StringUtil;

/** * @author  Lance 
 * @date 创建时间：2016年1月12日 下午2:28:41 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class syncdept {
    private IMDepartDao dao = new IMDepartDaoImpl();
	
	public  void syncdeptfrom177(){
		Log logs = LogFactory.getLog(syncdept.class);
		try{
			Map<String ,Object> m = new LinkedHashMap<String ,Object>();
			m.put("status", "0");
			List<IMDepart> list = dao.findByPagination(-1, -1, m);
			if(list != null){
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
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public  boolean syncAllDepart(){
		Log logs = LogFactory.getLog(syncdept.class);
		try{
			Connection conn = SqlserverDBUtils.createConn();
			String sql = "select * ,(select dept_code from IFS_DEPARTMENT i where i.GUID =IFS_DEPARTMENT.PARENT_ID ) AS df from IFS_DEPARTMENT order by ORDINAL";
			PreparedStatement ps = SqlserverDBUtils.getPs(conn, sql);
 			ResultSet rs = ps.executeQuery();
 			boolean isok = true;
 			while(rs.next()){
 				String departname = rs.getString("name");
 				String sapgsdm = rs.getString("dept_code");
 				String parentsapcode = rs.getString("df");
 				logs.info("部门名:"+departname);
 				logs.info("部门代码:"+sapgsdm);
 				long dds = DateWorkUtil.timechuo();
 				int dfs = dao.SyncAllDepart(departname, 0, 0, 0, dds, dds, 0, sapgsdm,parentsapcode);
 				logs.info("dsk:"+dfs);
 				if(dfs>0){
 					isok = false;
 				}
 			}
 			return isok;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

		
	}
	
	public static void main(String[] args) {
		syncdept sc = new syncdept();
		sc.syncdeptfrom177();
	}
	
}
