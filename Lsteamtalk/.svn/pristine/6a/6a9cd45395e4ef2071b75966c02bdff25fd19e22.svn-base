package com.lesso.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lesso.beans.IMDepart;
import com.lesso.model.TreeDTO;

/** * @author  Lance  * @date 创建时间：2015年12月12日 下午3:23:51 * @version 1.0 * @parameter  * @since  * @return  */
public class TreeDtoUtil {
	/**
	   * 创建一颗树，以json字符串形式返回
	   * @param list 原始数据列表
	   * @return 树
	   */
	  public static String createTreeJson(List<TreeDTO> list) {
	  try{
	    JSONArray rootArray = new JSONArray();
	    for (int i=0; i<list.size(); i++) {
	    	TreeDTO resource = list.get(i);
	      if ( resource.getParent_id() ==0) {
	        JSONObject rootObj = createBranch(list, resource);
	        rootArray.add(rootObj);
	      }
	    }
	    
	    return rootArray.toString();
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return "error";
	    }
	  }
	  
	  /**
	   * 递归创建分支节点Json对象
	   * @param list 创建树的原始数据
	   * @param currentNode 当前节点
	   * @return 当前节点与该节点的子节点json对象
	   */
	  private static JSONObject createBranch(List<TreeDTO> list, TreeDTO currentNode) {
	     try{
		  /*
	     * 将javabean对象解析成为JSON对象
	     */
	    JSONObject currentObj = JSONObject.fromObject(currentNode);
	    JSONArray childArray = new JSONArray();
	    /*
	     * 循环遍历原始数据列表，判断列表中某对象的父id值是否等于当前节点的id值，
	     * 如果相等，进入递归创建新节点的子节点，直至无子节点时，返回节点，并将该
	     * 节点放入当前节点的子节点列表中
	     */
	    for (int i=0; i<list.size(); i++) {
	    	TreeDTO newNode = list.get(i);
	      if ( newNode.getParent_id().compareTo(currentNode.getId()) == 0) {
	        JSONObject childObj = createBranch(list, newNode);
	        childArray.add(childObj);
	      }
	    }
	    
	    /*
	     * 判断当前子节点数组是否为空，不为空将子节点数组加入children字段中
	     */
	    if (!childArray.isEmpty()) {
	      currentObj.put("children", childArray);
	    }
	    
	    return currentObj;
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return null;
	    }
	     
	  }
}
