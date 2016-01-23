package com.lesso.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateWorkUtil {
   public static List<String> getTimeBucket(String begindate,String enddate){
	   try{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		    Date d1 = df.parse(enddate);
		    Date d2 = df.parse(begindate);
		    long diff = d1.getTime() - d2.getTime();
		    long days = diff / (1000 * 60 * 60 * 24);
           System.out.println(days);
           
           List<String> list = new ArrayList();
           String nexta =begindate;
           String nextb ="";
		   int a = (int) days;
		   for(int i=0;i<=a;i++){
			   if(i!=0){
				   nextb = AddDate(nexta);
				   nexta = nextb;
				   System.out.println(nextb);
				   list.add(nexta);
			   }else{
				   System.out.println(begindate);
				   list.add(begindate);
			   }   
		   }
		   return list;
	   }catch(Exception e){
		   e.printStackTrace();
			return null; 
	   }

   }
   
   
   public static String AddDate(String str){
	   DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
	   try {
		Date d1 = df.parse(str);
		String nextday = df.format(new Date(d1.getTime() + 1 * 24 * 60 * 60 * 1000));
//		System.out.println(nextday);
//		   System.out.println("今天的日期："+df.format(d));  
//		   System.out.println("两天前的日期：" + df.format(new Date(d.getTime() - 2 * 24 * 60 * 60 * 1000)));  
//		   System.out.println("三天后的日期：" + df.format(new Date(d.getTime() + 3 * 24 * 60 * 60 * 1000)));
		return nextday;
	} catch (ParseException e) {
		e.printStackTrace();
		return "";
	}
}
   
   public static long timechuo(){
       long time =	Calendar.getInstance().getTimeInMillis();
       String d =time+"";
   		d = d.substring(0,d.length()-3);
//   		System.out.println(d);
   		long dds = Long.parseLong(d);
   		return dds;
   }
   
   public static String stringToDatenow(){
	   try {
		   DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
		   String we = sdf.format(new Date());
		   return we;
		} catch (Exception e) {
			e.printStackTrace();
			   return null;
		} 
   }
   
// 将字符串转为时间戳  
	public static String stringTotimechuo(String user_time) {  
		String re_time = null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		Date d;  
		try {  
		d = sdf.parse(user_time);  
		long l = d.getTime();  
		String str = String.valueOf(l);  
		re_time = str.substring(0, 10);  
		} catch (ParseException e) {  
		e.printStackTrace();  
		}  
		return re_time;  
		}  
  
}
