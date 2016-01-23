package com.lesso.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static boolean isEmpty(String str){
    	if("".equals(str)||str==null){
			return true;
		}else{
			return false;
		}
    }
    
    
    public static boolean isNotEmpty(String str){
		if(!"".equals(str)&&str!=null &&!"null".equals(str)){
			return true;
		}else{
			return false;
		}
	}
    
    public static boolean IsinStringArr(String[] arr, String targetValue) {
        for(String s: arr){
            if(s.equals(targetValue))
                return true;
        }
        return false;
    }
    
    public static String removeHtmlTag(String content) {
        Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>");
        Matcher m = p.matcher(content);
        if (m.find()) {
            content = content.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");
            content = removeHtmlTag(content);
        }
        content = content.replaceAll("<br>", "");
        content = content.replaceAll("<br/>", "");
        return content;
    }
}
