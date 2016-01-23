package com.lesso.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jxl.write.Label;
import jxl.write.WritableSheet;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class ExcelUtil<T> {
	 /**
	   * 将结果集写入到工作薄的一个sheet中
	   * 
	   * @param rs 结果集
	   * @param wb 工作簿
	   * @param headers 表头
	   * @throws Exception
	   * */
		
		public static void fillExcelData(ResultSet rs,Workbook wb,String[] headers) throws Exception{
			int rowIndex =0;
			Sheet sheet = wb.createSheet();
			Row row = sheet.createRow(rowIndex++);
			for(int i =0;i<headers.length;i++){
				row.createCell(i).setCellValue(headers[i]);
			}
			while(rs.next()){//循环写入每一行
				row = sheet.createRow(rowIndex++);
				for(int i=0;i<headers.length;i++){
				   row.createCell(i).setCellValue(rs.getObject(i+1).toString());// 注意jdbc中的下标是从1开始	
				}
				
			}
		}
		
		
		public static<T> void fillExcelData(List<T> list,Workbook wb,String[] headers, Map<String, String> fieldMap) throws Exception{
			int rowIndex =0;
			Sheet sheet = wb.createSheet();
			Row row = sheet.createRow(rowIndex++);
			
			//定义存放英文字段名和中文字段名的数组 
	        String[] enFields=new String[fieldMap.size()]; 
	        String[] cnFields=new String[fieldMap.size()]; 
	           
	        //填充数组 
	        int count=0; 
	        for(Entry<String,String> entry:fieldMap.entrySet()){ 
	            cnFields[count]=entry.getKey(); 
	            enFields[count]=entry.getValue(); 
	            count++; 
	        } 
	        
			for(int i =0;i<headers.length;i++){
				row.createCell(i).setCellValue(headers[i]);
			}
			
			
			for(int a =0;a<list.size();a++){
				row = sheet.createRow(rowIndex++);
				T item = list.get(a);
				for(int i=0;i<headers.length;i++){
		                Object objValue=getFieldValueByNameSequence(enFields[i], item); 
		                String fieldValue=objValue==null ? "" : objValue.toString(); 
		                row.createCell(i).setCellValue(fieldValue);// 注意jdbc中的下标是从1开始
				}	
			}

		}
		
		/**
		 * 利用模版导出数据1.读入模版2.读取模版3.写入数据
		 * 
		 * @param rs
		 * @param templateFileName
		 * @return wb
		 * @throws Exception
		 *
		 * 
		*/
		public static Workbook fillExcelDataByTemplate(ResultSet rs, String templateFileName) throws Exception{
			InputStream inp = ExcelUtil.class.getResourceAsStream("/com/lance/template/"+templateFileName);
			POIFSFileSystem fs = new POIFSFileSystem(inp);
			Workbook wb = new HSSFWorkbook(fs);
			Sheet sheet = wb.getSheetAt(0);
			//获取列数
			int cellNums = sheet.getRow(0).getLastCellNum();
			int rowIndex=1;
			while(rs.next()){
				Row row = sheet.createRow(rowIndex++);
				for(int i=0;i<cellNums;i++){
					row.createCell(i).setCellValue(rs.getObject(i+1).toString());
				}
			}
			ExcelExtractor ee = new ExcelExtractor((HSSFWorkbook)wb);
			System.out.println(ee.getText());
			return wb;
			
		}
		
		
		public static<T> Workbook fillExcelDataByTemplate(List<T> list, String templateFileName,Map<String, String> fieldMap) throws Exception{
			InputStream inp = ExcelUtil.class.getResourceAsStream("/com/lance/template/"+templateFileName);
			POIFSFileSystem fs = new POIFSFileSystem(inp);
			Workbook wb = new HSSFWorkbook(fs);
			Sheet sheet = wb.getSheetAt(0);
			//获取列数
			int cellNums = sheet.getRow(0).getLastCellNum();
			int rowIndex=1;
			
			//定义存放英文字段名和中文字段名的数组 
	        String[] enFields=new String[fieldMap.size()]; 
	        String[] cnFields=new String[fieldMap.size()]; 
	           
	        //填充数组 
	        int count=0; 
	        for(Entry<String,String> entry:fieldMap.entrySet()){ 
	            cnFields[count]=entry.getKey(); 
	            enFields[count]=entry.getValue(); 
	            count++; 
	        } 

			
			for(int a =0;a<list.size();a++){
				Row row = sheet.createRow(rowIndex++);
				T item = list.get(a);
				for(int i=0;i<cellNums;i++){
		                Object objValue=getFieldValueByNameSequence(enFields[i], item); 
		                String fieldValue=objValue==null ? "" : objValue.toString(); 
		                row.createCell(i).setCellValue(fieldValue);// 注意jdbc中的下标是从1开始
				}	
			}
			
			ExcelExtractor ee = new ExcelExtractor((HSSFWorkbook)wb);
			System.out.println(ee.getText());
			return wb;
			
		}
		
	/*
	 * parms rowIndex是从第几行开始,下标是从0开始的
	 * parms cellNums是有几列数据需要填充
	 * parms rowIndex是从第几列，第几行开始,下标是从0开始的
	 * */
		public static<T> Workbook fillExcelDataByTemplate(List<T> list, String templateFileName,
				Map<String, String> fieldMap,int rowIndex,int cellNums,int cellbegin) throws Exception{
			try{
			InputStream inp = ExcelUtil.class.getResourceAsStream("/com/lesso/template/"+templateFileName);
			POIFSFileSystem fs = new POIFSFileSystem(inp);
			Workbook wb = new HSSFWorkbook(fs);
			Sheet sheet = wb.getSheetAt(0);
			//获取列数
//			int cellNums = sheet.getRow(0).getLastCellNum();
//			int rowIndex=1;
			
			//定义存放英文字段名和中文字段名的数组 
	        String[] enFields=new String[fieldMap.size()]; 
	        String[] cnFields=new String[fieldMap.size()]; 
	           
	        //填充数组 
	        int count=0; 
	        for(Entry<String,String> entry:fieldMap.entrySet()){ 
	            cnFields[count]=entry.getKey(); 
	            enFields[count]=entry.getValue(); 
	            count++; 
	        } 

			
			for(int a =0;a<list.size();a++){
				Row row = sheet.createRow(rowIndex++);
				T item = list.get(a);
				for(int i=cellbegin;i<cellNums;i++){
		                Object objValue=getFieldValueByNameSequence(enFields[i], item); 
		                String fieldValue=objValue==null ? "" : objValue.toString(); 
		                row.createCell(i).setCellValue(fieldValue);// 注意jdbc中的下标是从1开始
				}	
			}
			
			ExcelExtractor ee = new ExcelExtractor((HSSFWorkbook)wb);
			System.out.println(ee.getText());
			return wb;
			}catch(Exception e){
				return null;
			}
		}
		
		
		
		/**
		 * 设置格式
		 * @param hssfCell
		 * @return
		 */
		public static String formatCell(HSSFCell hssfCell){
			if(hssfCell==null){
				return "";
			}else{
				if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){
					return String.valueOf(hssfCell.getBooleanCellValue());
				}else if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
					return String.valueOf(hssfCell.getNumericCellValue());
				}else{
					return String.valueOf(hssfCell.getStringCellValue());
				}
			}
			
			
		}
		
		
		
		
		
		
		  /*<-------------------------辅助的私有方法----------------------------------------------->*/
	    /**
	     * @MethodName  : getFieldValueByName
	     * @Description : 根据字段名获取字段值
	     * @param fieldName 字段名
	     * @param o 对象
	     * @return  字段值
	     */ 
	    private static  Object getFieldValueByName(String fieldName, Object o) throws Exception{ 
	           
	        Object value=null; 
	        Field field=getFieldByName(fieldName, o.getClass()); 
	           
	        if(field !=null){ 
	            field.setAccessible(true); 
	            value=field.get(o); 
	        }else{ 
	            throw new ExcelException(o.getClass().getSimpleName() + "类不存在字段名 "+fieldName); 
	        } 
	           
	        return value; 
	    } 
	       
	    /**
	     * @MethodName  : getFieldByName
	     * @Description : 根据字段名获取字段
	     * @param fieldName 字段名
	     * @param clazz 包含该字段的类
	     * @return 字段
	     */ 
	    private static Field getFieldByName(String fieldName, Class<?>  clazz){ 
	        //拿到本类的所有字段 
	        Field[] selfFields=clazz.getDeclaredFields(); 
	           
	        //如果本类中存在该字段，则返回 
	        for(Field field : selfFields){ 
	            if(field.getName().equals(fieldName)){ 
	                return field; 
	            } 
	        } 
	           
	        //否则，查看父类中是否存在此字段，如果有则返回 
	        Class<?> superClazz=clazz.getSuperclass(); 
	        if(superClazz!=null  &&  superClazz !=Object.class){ 
	            return getFieldByName(fieldName, superClazz); 
	        } 
	           
	        //如果本类和父类都没有，则返回空 
	        return null; 
	    } 
	       
	       
	       
	    /**
	     * @MethodName  : getFieldValueByNameSequence
	     * @Description : 
	     * 根据带路径或不带路径的属性名获取属性值
	     * 即接受简单属性名，如userName等，又接受带路径的属性名，如student.department.name等
	     * 
	     * @param fieldNameSequence  带路径的属性名或简单属性名
	     * @param o 对象
	     * @return  属性值
	     * @throws Exception
	     */ 
	    private static  Object getFieldValueByNameSequence(String fieldNameSequence, Object o) throws Exception{ 
	           
	        Object value=null; 
	           
	        //将fieldNameSequence进行拆分 
	        String[] attributes=fieldNameSequence.split("\\."); 
	        if(attributes.length==1){ 
	            value=getFieldValueByName(fieldNameSequence, o); 
	        }else{ 
	            //根据属性名获取属性对象 
	            Object fieldObj=getFieldValueByName(attributes[0], o); 
	            String subFieldNameSequence=fieldNameSequence.substring(fieldNameSequence.indexOf(".")+1); 
	            value=getFieldValueByNameSequence(subFieldNameSequence, fieldObj); 
	        } 
	        return value;  
	           
	    }  
	       
	       
	    /**
	     * @MethodName  : setFieldValueByName
	     * @Description : 根据字段名给对象的字段赋值
	     * @param fieldName  字段名
	     * @param fieldValue    字段值
	     * @param o 对象
	     */ 
	    private static void setFieldValueByName(String fieldName,Object fieldValue,Object o) throws Exception{ 
	           
	            Field field=getFieldByName(fieldName, o.getClass()); 
	            if(field!=null){ 
	                field.setAccessible(true); 
	                //获取字段类型 
	                Class<?> fieldType = field.getType();   
	                   
	                //根据字段类型给字段赋值 
	                if (String.class == fieldType) {   
	                    field.set(o, String.valueOf(fieldValue));   
	                } else if ((Integer.TYPE == fieldType)   
	                        || (Integer.class == fieldType)) {   
	                    field.set(o, Integer.parseInt(fieldValue.toString()));   
	                } else if ((Long.TYPE == fieldType)   
	                        || (Long.class == fieldType)) {   
	                    field.set(o, Long.valueOf(fieldValue.toString()));   
	                } else if ((Float.TYPE == fieldType)   
	                        || (Float.class == fieldType)) {   
	                    field.set(o, Float.valueOf(fieldValue.toString()));   
	                } else if ((Short.TYPE == fieldType)   
	                        || (Short.class == fieldType)) {   
	                    field.set(o, Short.valueOf(fieldValue.toString()));   
	                } else if ((Double.TYPE == fieldType)   
	                        || (Double.class == fieldType)) {   
	                    field.set(o, Double.valueOf(fieldValue.toString()));   
	                } else if (Character.TYPE == fieldType) {   
	                    if ((fieldValue!= null) && (fieldValue.toString().length() > 0)) {   
	                        field.set(o, Character   
	                                .valueOf(fieldValue.toString().charAt(0)));   
	                    }   
	                }else if(Date.class==fieldType){ 
	                    field.set(o, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fieldValue.toString())); 
	                }else{ 
	                    field.set(o, fieldValue); 
	                } 
	            }else{ 
	                throw new ExcelException(o.getClass().getSimpleName() + "类不存在字段名 "+fieldName); 
	            } 
	    } 
	       
	       
	    /**
	     * @MethodName  : setColumnAutoSize
	     * @Description : 设置工作表自动列宽和首行加粗
	     * @param ws
	     */ 
	    private static void setColumnAutoSize(WritableSheet ws,int extraWith){ 
	        //获取本列的最宽单元格的宽度 
	        for(int i=0;i<ws.getColumns();i++){ 
	            int colWith=0; 
	            for(int j=0;j<ws.getRows();j++){ 
	                String content=ws.getCell(i,j).getContents().toString(); 
	                int cellWith=content.length(); 
	                if(colWith<cellWith){ 
	                    colWith=cellWith; 
	                } 
	            } 
	            //设置单元格的宽度为最宽宽度+额外宽度 
	            ws.setColumnView(i, colWith+extraWith); 
	        } 
	           
	    } 
	       
	    /**
	     * @MethodName  : fillSheet
	     * @Description : 向工作表中填充数据
	     * @param sheet     工作表 
	     * @param list  数据源
	     * @param fieldMap 中英文字段对应关系的Map
	     * @param firstIndex    开始索引
	     * @param lastIndex 结束索引
	     */ 
	    private static <T> void fillSheet( 
	            WritableSheet sheet, 
	            List<T> list, 
	            LinkedHashMap<String,String> fieldMap, 
	            int firstIndex, 
	            int lastIndex 
	            )throws Exception{ 
	           
	        //定义存放英文字段名和中文字段名的数组 
	        String[] enFields=new String[fieldMap.size()]; 
	        String[] cnFields=new String[fieldMap.size()]; 
	           
	        //填充数组 
	        int count=0; 
	        for(Entry<String,String> entry:fieldMap.entrySet()){ 
	            enFields[count]=entry.getKey(); 
	            cnFields[count]=entry.getValue(); 
	            count++; 
	        } 
	        //填充表头 
	        for(int i=0;i<cnFields.length;i++){ 
	            Label label=new Label(i,0,cnFields[i]); 
	            sheet.addCell(label); 
	        } 
	           
	        //填充内容 
	        int rowNo=1; 
	        for(int index=firstIndex;index<=lastIndex;index++){ 
	            //获取单个对象 
	            T item=list.get(index); 
	            for(int i=0;i<enFields.length;i++){ 
	                Object objValue=getFieldValueByNameSequence(enFields[i], item); 
	                String fieldValue=objValue==null ? "" : objValue.toString(); 
	                Label label =new Label(i,rowNo,fieldValue); 
	                sheet.addCell(label); 
	            } 
	               
	            rowNo++; 
	        } 
	           
	        //设置自动列宽 
	        setColumnAutoSize(sheet, 5); 
	    }   
}
