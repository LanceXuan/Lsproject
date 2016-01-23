package com.lesso.quartz;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lesso.test.quartztest;

/** * @author  Lance 
 * @date 创建时间：2016年1月12日 上午10:54:15 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class Initdata implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("tomcat is down");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try{
			String job_name = "dsf";
			String job = "com.lesso.quartz.SyncDepartJob";
			
			String job_nameA="otr";
			String jobA= "com.lesso.quartz.SyncUserJob";  
			
			String job_nameB="khj";
			String jobB= "com.lesso.quartz.SyncVkorgJob";  
			
			String job_nameC="wwe";
			String jobC= "com.lesso.quartz.syncZknaJob"; 
		    System.out.println("/n【再次添加定时任务】开始(每10秒输出一次)...");  
//		    QuartzManager.addJob(job_name, job, "0 15 20 ? * MON-FRI");  
//		    QuartzManager.addJob(job_nameA, jobA, "0 20 20 ? * MON-FRI"); 
//		    QuartzManager.addJob(job_nameB, jobB, "0 25 20 ? * MON-FRI"); 
//		    QuartzManager.addJob(job_nameC, jobC, "0 30 20 ? * MON-FRI");  
			}catch(Exception e){
				e.printStackTrace();
			}
		
	}

}
