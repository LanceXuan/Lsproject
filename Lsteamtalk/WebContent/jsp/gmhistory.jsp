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
<script type="text/javascript"  src="../js/gmhistory.js" charset="utf-8"></script> 
<link href="../jqueryeasyui/themes/default/easyui.css" rel="stylesheet">
<link href="../jqueryeasyui/themes/icon.css" rel="stylesheet">
<title>群组历史记录</title>
</head>
<body>      
    <div id="tb" style="padding:5px;height:auto">    
        <div style="margin-bottom:5px">    
            Date From: <input class="easyui-datebox" style="width:120px" id="begintime">    
            To: <input class="easyui-datebox" style="width:120px" id="endtime">
            <!--   
                                    内容:     
            <input class="easyui-validatebox" style="width:100px" id="namesearch"        
                    valueField="namesearch" textField="text">  
                     -->  
                                    群组名称 :
            <input name="groupId" id="groupId" class="easyui-combobox" url="../privilegemgmt/Group.action?method=combolist" valueField="id" textField="name"  value="" />
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" name="searchbtn" id="searchbtn" >查询</a>
        </div> 
    </div> 
		<div id="cc" class="easyui-layout" fit=true style="width:100%;height:100%;">  
		    <div region="center" style="padding:5px;">
		    <table id="t_gm"></table>
		    </div>  
		</div> 
</body>
</html>