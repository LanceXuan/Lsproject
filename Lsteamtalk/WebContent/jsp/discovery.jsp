<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@ page import="com.lesso.beans.IMAdmin" %>
    <%
  String username = "";
    IMAdmin user = (IMAdmin)session.getAttribute("userinfo");
  if(user==null){ 
	  response.sendRedirect("sign.jsp");
   }else{
	   username=user.getUname();
   }
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../jqueryeasyui/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript"  src="../jqueryeasyui/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript"  src="../jqueryeasyui/locale/easyui-lang-zh_CN.js" charset="utf-8" type="text/javascript"></script>
<script type="text/javascript"  src="../js/discovery.js" charset="utf-8"></script> 
<link href="../jqueryeasyui/themes/default/easyui.css" rel="stylesheet">
<link href="../jqueryeasyui/themes/icon.css" rel="stylesheet">
<title>发现管理</title>
</head>
<body>
 <div id="tb" style="padding:5px;height:auto">    
        <div style="margin-bottom:5px">        
                                    标题:     
            <input class="easyui-validatebox" style="width:100px" id="namesearch"        
                    valueField="namesearch" textField="text">  
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" name="searchbtn" >查询</a>
        </div> 
    </div> 
		<div id="cc" class="easyui-layout" fit=true style="width:100%;height:100%;">  
		    <div region="center" style="padding:5px;">
		    <table id="t_dis"></table>
		    </div>  
		</div> 
		
		<div id="div1" title="发现管理" class="easyui-dialog" style="width:400px;" closed=true modal=true >
  				<form id="myform" method="post">
  							<input type="hidden" name="id" value="" />
  						<table>
  							<tr>
  								<td>标题:</td>
  								<td><input name="dianame" value=""/><em id="errordianame" style="color:red;"></em></td>
  							</tr>
  							<tr>
  								<td>url:</td>
  								<td><input name="diaurl" value=""/><em id="errordiaurl" style="color:red;"></em></td>
  							</tr>
   							<tr>
  								<td>优先级:</td>
  								<td><input name="diapriority" value="" /><em id="errordiapriority" style="color:red;"></em></td>
  							</tr>
  							<tr align="center">
  								<td colspan="2">
  									<a id="btn1" class="easyui-linkbutton">确定</a>
  									<a id="btn2" class="easyui-linkbutton">取消</a>
  								</td>
  							</tr>  							  							  							 							
  						</table>
  				</form>
  		</div>
  		
</body>
</html>