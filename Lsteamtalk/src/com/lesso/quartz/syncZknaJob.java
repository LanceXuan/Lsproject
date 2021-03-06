package com.lesso.quartz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.lesso.beans.IMUser;
import com.lesso.beans.Z_KNA;
import com.lesso.dao.IMUserDao;
import com.lesso.dao.Z_KNADao;
import com.lesso.daoImpl.IMUserDaoImpl;
import com.lesso.daoImpl.Z_KNADaoImpl;
import com.lesso.util.MD5Util;
import com.lesso.util.SqlserverDBUtils;
import com.lesso.util.StringUtil;


/** * @author  Lance 
 * @date 创建时间：2016年1月13日 下午4:58:38 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class syncZknaJob implements Job{
  private Z_KNADao dao = new Z_KNADaoImpl();
  private IMUserDao daoa = new IMUserDaoImpl();
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Log logs = LogFactory.getLog(syncZknaJob.class);
		try{
			Connection conn = SqlserverDBUtils.createConn();
			String sql = "select m.KUNNR,m.LOGIN_ID,m.PASSWORD,m.REGIO,"
					+ "m.NAME1,i.DQNAME,k.VKORG from MST_SAP_KNA1 m "
					+ "left join MST_DQ i "
					+ "on m.REGIO = i.DQNO "
					+ "left join MST_SAP_KNB1 k "
					+ "on m.KUNNR = k.KUNNR "
					+ "where m.LOGIN_ID !='null' and m.PASSWORD!= 'null'";
			PreparedStatement ps = SqlserverDBUtils.getPs(conn, sql);
			ResultSet rs = ps.executeQuery();
			List<Z_KNA> list = new ArrayList<Z_KNA>();
			List<Z_KNA> lista = new ArrayList<Z_KNA>();
			while(rs.next()){
				String Kunnr = rs.getString("kunnr");
				String name = rs.getString("name1"); 
				String vkorg = rs.getString("VKORG");
				String Login_id = rs.getString("login_id");
				String pwd = rs.getString("password");
				String area = rs.getString("regio");
				String area_name = rs.getString("dqname");
				Z_KNA kna = dao.findbyKunrAndVkorg(Kunnr, vkorg);
				if(kna !=null){
					 if(!name.equals(kna.getName()) || !Login_id.equals(kna.getLogin_id())
	 						  || !pwd.equals(kna.getPwd()) || !area.equals(kna.getArea()) || !area_name.equals(kna.getArea_name())){
	 					 Map<String ,Object> m = new LinkedHashMap<String ,Object>();
	 					 m.put("NAME", name);
	 					 m.put("LOGIN_ID", Login_id);
	 					 m.put("PWD", pwd);
	 					 m.put("area", area);
	 					 m.put("area_name", area_name);
	 					  dao.updatekua(m, Kunnr, vkorg);
	 					  list.add(kna);
	 					 logs.info("客户编码"+Kunnr+",所属公司"+vkorg+"更新完毕");
	 				  }else{
	 					  logs.info("客户编码"+Kunnr+",所属公司"+vkorg+"不需要更新");
	 				  }
				}else{
					if(StringUtil.isNotEmpty(Kunnr) && StringUtil.isNotEmpty(vkorg)){
					boolean isok = dao.savekua(Kunnr, vkorg, name, Login_id, pwd, area, area_name);
					logs.info("客户编码"+Kunnr+",所属公司"+vkorg+"新增完毕");
					if(isok){
						Z_KNA mp = new Z_KNA();
				 			mp.setArea(area);
				 			mp.setArea_name(area_name);
				 			mp.setKunnr(Kunnr);
				 			mp.setLogin_id(Login_id);
				 			mp.setName(name);
				 			mp.setPwd(pwd);
				 			mp.setVkorg(vkorg);
				 			lista.add(mp);
					}
				  }
			  }
			}
			for(int i =0;i<list.size();i++){
				IMUser imuser =daoa.findIMUser(list.get(i).getLogin_id());
				if(imuser !=null){
					String password = MD5Util.md5(MD5Util.md5(list.get(i).getPwd())+"2128");
					Map<String, Object> m = new LinkedHashMap<String, Object>();
					m.put("nick", list.get(i).getName());
					m.put("password", password);
					m.put("sap_kunnr", list.get(i).getKunnr());
				boolean fd= daoa.syncupdateuser(m, imuser.getId());
				logs.info("修改ID为"+imuser.getId()+"的用户,nick is"+list.get(i).getName()+"客户编码是"+list.get(i).getKunnr());
				logs.info("修改是否成功"+fd);
				}
			}
			
			for(int j=0;j<lista.size();j++){
				IMUser imuser =daoa.findIMUser(lista.get(j).getLogin_id());
				if(imuser == null){
					String password = MD5Util.md5(MD5Util.md5(lista.get(j).getPwd())+"2128");
					int dkl = daoa.syncuser(1, lista.get(j).getLogin_id(), "0", lista.get(j).getName(), password, "2128",
							"", "", 0, 0, 0, "", 2, "", lista.get(j).getKunnr(), "", "", "","");
					logs.info("新增用户,nick is"+lista.get(j).getName()+"客户编码是"+lista.get(j).getKunnr());
					logs.info("修改是否成功"+dkl);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Z_KNADao dao = new Z_KNADaoImpl();
		 IMUserDao daoa = new IMUserDaoImpl();
		Log logs = LogFactory.getLog(syncZknaJob.class);
		try{
			Connection conn = SqlserverDBUtils.createConn();
			String sql = "select m.KUNNR,m.LOGIN_ID,m.PASSWORD,m.REGIO,"
					+ "m.NAME1,i.DQNAME,k.VKORG from MST_SAP_KNA1 m "
					+ "left join MST_DQ i "
					+ "on m.REGIO = i.DQNO "
					+ "left join MST_SAP_KNB1 k "
					+ "on m.KUNNR = k.KUNNR "
					+ "where m.LOGIN_ID !='null' and m.PASSWORD!= 'null'";
			PreparedStatement ps = SqlserverDBUtils.getPs(conn, sql);
			ResultSet rs = ps.executeQuery();
			List<Z_KNA> list = new ArrayList<Z_KNA>();
			List<Z_KNA> lista = new ArrayList<Z_KNA>();
			while(rs.next()){
				String Kunnr = rs.getString("kunnr");
				String name = rs.getString("name1"); 
				String vkorg = rs.getString("VKORG");
				String Login_id = rs.getString("login_id");
				String pwd = rs.getString("password");
				String area = rs.getString("regio");
				String area_name = rs.getString("dqname");
				Z_KNA kna = dao.findbyKunrAndVkorg(Kunnr, vkorg);
				if(kna !=null){
					 if(!name.equals(kna.getName()) || !Login_id.equals(kna.getLogin_id())
	 						  || !pwd.equals(kna.getPwd()) || !area.equals(kna.getArea()) || !area_name.equals(kna.getArea_name())){
	 					 Map<String ,Object> m = new LinkedHashMap<String ,Object>();
	 					 m.put("NAME", name);
	 					 m.put("LOGIN_ID", Login_id);
	 					 m.put("PWD", pwd);
	 					 m.put("area", area);
	 					 m.put("area_name", area_name);
	 					  dao.updatekua(m, Kunnr, vkorg);
	 					  list.add(kna);
	 					 logs.info("客户编码"+Kunnr+",所属公司"+vkorg+"更新完毕");
	 					 System.out.println("客户编码"+Kunnr+",所属公司"+vkorg+"更新完毕");
	 				  }else{
	 					  logs.info("客户编码"+Kunnr+",所属公司"+vkorg+"不需要更新");
	 					 System.out.println("客户编码"+Kunnr+",所属公司"+vkorg+"不需要更新");
	 				  }
				}else{
					if(StringUtil.isNotEmpty(Kunnr) && StringUtil.isNotEmpty(vkorg)){
					boolean isok = dao.savekua(Kunnr, vkorg, name, Login_id, pwd, area, area_name);
					logs.info("客户编码"+Kunnr+",所属公司"+vkorg+"新增完毕");
					System.out.println("客户编码"+Kunnr+",所属公司"+vkorg+"新增完毕");
					if(isok){
						Z_KNA mp = new Z_KNA();
				 			mp.setArea(area);
				 			mp.setArea_name(area_name);
				 			mp.setKunnr(Kunnr);
				 			mp.setLogin_id(Login_id);
				 			mp.setName(name);
				 			mp.setPwd(pwd);
				 			mp.setVkorg(vkorg);
				 			lista.add(mp);
					}
				  }
			  }
			}
			for(int i =0;i<list.size();i++){
				IMUser imuser =daoa.findIMUser(list.get(i).getLogin_id());
				if(imuser !=null){
					String password = MD5Util.md5(MD5Util.md5(list.get(i).getPwd())+"2128");
					Map<String, Object> m = new LinkedHashMap<String, Object>();
					m.put("nick", list.get(i).getName());
					m.put("password", password);
					m.put("sap_kunnr", list.get(i).getKunnr());
				boolean fd= daoa.syncupdateuser(m, imuser.getId());
				logs.info("修改ID为"+imuser.getId()+"的用户,nick is"+list.get(i).getName()+"客户编码是"+list.get(i).getKunnr());
				logs.info("修改是否成功"+fd);
				}
			}
			
			for(int j=0;j<lista.size();j++){
				IMUser imuser =daoa.findIMUser(lista.get(j).getLogin_id());
				if(imuser == null){
					String password = MD5Util.md5(MD5Util.md5(lista.get(j).getPwd())+"2128");
					int dkl = daoa.syncuser(1, lista.get(j).getLogin_id(), "0", lista.get(j).getName(), password, "2128",
							"", "", 0, 0, 0, "", 2, "", lista.get(j).getKunnr(), "", "", "","");
					logs.info("新增用户,nick is"+lista.get(j).getName()+"客户编码是"+lista.get(j).getKunnr());
					logs.info("修改是否成功"+dkl);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
