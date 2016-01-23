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
<script type="text/javascript"  src="../js/group.js" charset="utf-8"></script> 
<link href="../jqueryeasyui/themes/default/easyui.css" rel="stylesheet">
<link href="../jqueryeasyui/themes/icon.css" rel="stylesheet">
<title>群组管理</title>
</head>
<body>
      <div id="tb" style="padding:5px;height:auto">    
        <div style="margin-bottom:5px">    
            <a href="#" id="groupCut" style="display:none" class="easyui-linkbutton" iconCls="icon-cut" plain="true">编辑群成员</a>  
            <a href="#" id="groupLook" class="easyui-linkbutton" iconCls="icon-tip" plain="true">查看群成员</a>  
            name:     
            <input class="easyui-validatebox" style="width:100px" id="namesearch"        
                    valueField="namesearch" textField="text">    
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" name="searchbtn" id="searchbtn" >查询</a>
        </div> 
    </div> 



		<div id="cc" class="easyui-layout" fit=true style="width:100%;height:100%;">  
		    <div region="center" style="padding:5px;">
		    <table id="t_group"></table>
		    </div>  
		</div> 
		
		
		<div id="mydialog" title="编辑群成员" modal=true  draggable=true class="easyui-dialog" closed=true style="width:600px;">
	    		<div class="container-fluid" style="margin-top:100px;"> 
				<div class="row-fluid">
				    <div class="span5 text-right">
				        <select multiple="multiple" id="select1"> 
				                <option value="1">选项1</option> 
				                <option value="2">选项2</option> 
				                <option value="3">选项3</option> 
				                <option value="4">选项4</option> 
				                <option value="5">选项5</option> 
				                <option value="6">选项6</option> 
				                <option value="7">选项7</option> 
				            </select>
				        </div>
				        <div class="span2 text-center">
				        <div class="row-fluid">
				            <div class="span12">
				                <a class="btn" href="#"><i class=" icon-arrow-right " ></i><span id="add">选中右移</span></a>
				                </div>
				            </div>
				            <div class="row-fluid" style="margin-top:5px;">
				                <div class="span12">
				                    <a class="btn" href="#"><i class=" icon-arrow-left " ></i><span id="remove">选中左移</span></a>
				                </div>
				            </div>
				        </div>
				        <div class="span5">
				            <select multiple="multiple" id="select2"> 
				            </select>
				        </div>
				    </div>
				    <div class="row-fluid">
				    <div class="span12 text-center">
				        <input type="button" class="btn btn-primary" id="send" value="提    交"/> 
				        </div>
				    </div>
				</div>  
 			</div>
		</body>
</html>