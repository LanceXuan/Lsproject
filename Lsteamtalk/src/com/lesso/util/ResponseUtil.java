package com.lesso.util;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.lesso.beans.ErrorMsg;

public class ResponseUtil {
	public static void write(HttpServletResponse response,Object o)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(o.toString());
		out.flush();
		out.close();
	}
	  /**
	   * 将excel以流的形式输出
	   * @param response
	   * @param wb 工作簿
	   * @param fileName 表名字
	   * @throws Exception
	   * 
	   */
	  
	  public static void export(HttpServletResponse response,Workbook wb,String fileName)throws Exception{
			response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"iso8859-1"));
			response.setContentType("application/ynd.ms-excel;charset=UTF-8");	//固定格式
			OutputStream out=response.getOutputStream();
			wb.write(out);
			out.flush();
			out.close();
	  }
	  
	  
	    //转化为Json格式
      public static void toPaginationJson(HttpServletResponse response,List list,int total) throws Exception{
    	  try{
       
           JSONObject jobj = new JSONObject();//new一个JSON
           jobj.accumulate("total",total );//total代表一共有多少数据
           jobj.accumulate("rows", list);//row是代表显示的页的数据
           response.setContentType("text/html;charset=utf-8");
           response.setCharacterEncoding("utf-8");//指定为utf-8
           
           response.getWriter().write(jobj.toString());//转化为JSOn格式
           }catch(Exception ex){
        	   ex.printStackTrace();
           }
      }
      
      public static void ReMsg(HttpServletResponse response,boolean HasError,String errormsg,String logmsg){
			try{
			    ErrorMsg msg = new ErrorMsg();
				msg.setHasError(HasError);
				msg.setExceptionMag(errormsg);
				msg.setLogMsg(logmsg);
				response.setContentType("text/html;charset=utf-8");
			    response.getWriter().write(JSONArray.fromObject(msg).toString());
			 }catch(Exception e){
				 e.printStackTrace();
			 }
		}

}
