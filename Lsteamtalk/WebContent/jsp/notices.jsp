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
<script type="text/javascript"  src="../js/notice.js" charset="utf-8"></script> 
<link href="../jqueryeasyui/themes/default/easyui.css" rel="stylesheet">
<link href="../jqueryeasyui/themes/icon.css" rel="stylesheet">
<title>公告列表</title>
</head>
<body>
      <div id="tb" style="padding:5px;height:auto">    
        <div style="margin-bottom:5px">    
            <a href="#" id="depAdd" class="easyui-linkbutton" iconCls="icon-add" onclick="openLink()" plain="true">增加</a>
            <a href="#" id="depEdit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateLink()">修改</a>       
            <a href="#" id="depRemove" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteLink()">删除</a>       
                                      标题:     
            <input class="easyui-validatebox" style="width:100px" id="chaname"        
                    valueField="id" textField="text">            
            Date From: <input class="easyui-datebox" style="width:120px" id="begintime">    
            To: <input class="easyui-datebox" style="width:120px" id="endtime">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" name="searchbtn" id="searchbtn" >查询</a>
        </div> 
    </div> 

		<div id="cc" class="easyui-layout" fit=true style="width:100%;height:100%;">  
		    <div region="center" style="padding:5px;">
		    <table id="t_notice"></table>
		    </div>  
		</div> 
</body>
</html>