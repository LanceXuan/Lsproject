package com.lesso.test;

import com.lesso.quartz.QuartzManager;



/** * @author  Lance 
 * @date 创建时间：2016年1月12日 上午10:44:38 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class quartztest {

	public static void main(String[] args) {
		try{
               
			String job_name = "dsf";
			String job = "com.lesso.quartz.SyncDepartJob";
			
			String job_nameA="otr";
			String jobA= "com.lesso.quartz.SyncUserJob";  
			
			String job_nameB="khj";
			String jobB= "com.lesso.quartz.SyncVkorgJob";  
		    System.out.println("/n【再次添加定时任务】开始(每10秒输出一次)...");  
		    QuartzManager.addJob(job_name, job, "0 18/2 * * * ?");  
//		    QuartzManager.addJob(job_nameA, jobA, "0 20/15 * * * ?"); 
//		    QuartzManager.addJob(job_nameB, jobB, "0 0/15 * * * ?");  
			}catch(Exception e){
				e.printStackTrace();
			}

	}
	
	public static void sdk(){
		try{
			String job_name = "dsf";
			String job = "com.lesso.quartz.SyncDepartJob";
			
			String job_nameA="otr";
			String jobA= "com.lesso.quartz.SyncUserJob";  
			
			String job_nameB="khj";
			String jobB= "com.lesso.quartz.SyncVkorgJob";  
		    System.out.println("/n【再次添加定时任务】开始(每10秒输出一次)...");  
		    QuartzManager.addJob(job_name, job, "0 5/15 * * * ?");  
		    QuartzManager.addJob(job_nameA, jobA, "0 7/15 * * * ?"); 
		    QuartzManager.addJob(job_nameB, jobB, "0 0/15 * * * ?");  
			}catch(Exception e){
				e.printStackTrace();
			}
	}

}
