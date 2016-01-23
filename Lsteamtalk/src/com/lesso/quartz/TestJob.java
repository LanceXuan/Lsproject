package com.lesso.quartz;

import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/** * @author  Lance 
 * @date 创建时间：2016年1月12日 上午10:43:29 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class TestJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(Calendar.getInstance().getTime().toLocaleString()+ "★★★★★★★★★★★");
		
	}
	
	

}
