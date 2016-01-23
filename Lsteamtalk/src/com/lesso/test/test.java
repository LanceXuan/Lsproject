package com.lesso.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lesso.util.DateWorkUtil;
import com.lesso.util.SqlserverDBUtils;
import com.lesso.util.StringUtil;

public class test {

	public static void main(String[] args) {
//		long currentTime=System.currentTimeMillis();  
//		System.out.println(currentTime);
//		String d =currentTime+"";
//		d = d.substring(0,d.length()-3);
//		System.out.println(d);
//		long dds = Long.parseLong(d);
//		System.out.println(dds);
//		SimpleDateFormat df =new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		String s = df.format(new Date());
//	 	long epoch = new Date().parse("01/01/1970 01:00:00");
//	 	System.out.println(currentTime);
	System.out.println(DateWorkUtil.stringTotimechuo("2015-12-01"));	
	
	System.out.println(StringUtil.removeHtmlTag("<p>sdfrfegfdv<br></p>"));
	
//	try{
//		Connection conn = SqlserverDBUtils.createConn();
//		String sql =" select * from IFS_DEPARTMENT order by ORDINAL";
// 		PreparedStatement ps = SqlserverDBUtils.getPs(conn, sql);
// 		ResultSet rs = ps.executeQuery();
// 		while(rs.next()){
// 			System.out.println("部门是"+rs.getString("name"));
// 		}
// 		}catch(Exception e){
// 			e.printStackTrace();
// 		}
	
	String i="3";
	switch(i)
	{
	case "1":
	System.out.println(1);
	break;

	case "2":
	System.out.println(2);
	break;
	case "3":

	System.out.println(3);
	break;
	default:

	System.out.println("default");
	break;
	} 
	
	}

}
