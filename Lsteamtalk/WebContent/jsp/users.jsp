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
<script type="text/javascript"  src="../js/ajaxfileupload.js" charset="utf-8"></script> 
<script type="text/javascript"  src="../js/users.js" charset="utf-8"></script> 
<link href="../jqueryeasyui/themes/default/easyui.css" rel="stylesheet">
<link href="../jqueryeasyui/themes/icon.css" rel="stylesheet">

<title>用户管理</title>
</head>
<body>
      <div id="tb" style="padding:5px;height:auto">    
        <div style="margin-bottom:5px">    
            <a href="#" id="depAdd" class="easyui-linkbutton" iconCls="icon-add" plain="true">增加</a>
            <a href="#" id="depEdit" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>       
            <a href="#" id="depRemove" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>       
                                       真名:     
            <input class="easyui-validatebox" style="width:100px" id="chaname"        
                    valueField="id" textField="text">            
                                   类型：
            <select name="chalei" id="chalei">
                <option value="0">全部</option>
				<option value="1">联塑人员</option>
				<option value="2">客户</option>
			</select> 
			部门：
			 <input name="chadepId" id="chadepId" />
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" name="searchbtn" id="searchbtn" >查询</a>
        </div> 
    </div> 



		<div id="cc" class="easyui-layout" fit=true style="width:100%;height:100%;">  
		    <div region="center" style="padding:5px;">
		    <table id="t_user"></table>
		    </div>  
		</div> 
		
		<!-- enctype="multipart/form-data" -->
		<div id="mydialog"  title="新增用户" modal=true  draggable=true class="easyui-dialog" closed=true style="width:500px;">
	    		<form id="myform" action="" method="post">
	    				<input type="hidden" name="id" value=""/>
	    				<table>
	    					<tr>	    
	    						<td>登录名:</td>
	    						<td><input type="text" name="empname" class="easyui-validatebox" required=true  missingMessage="用户名必填!"  value="" />
	    						<em id="errorempname" style="color:red;"></em></td>
	    					</tr>
	    					<tr id="tdpwd" style="display:none">
	    						<td >密码:</td>
	    						<td><input type="password" name="password" class="easyui-validatebox" required=true  missingMessage="密码必填!" value="" />
	    						<em id="errorpassword" style="color:red;"></em>
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>真实姓名:</td>
	    						<td><input type="text" id="nicktxt" name="nicktxt" class="easyui-validatebox" required=true  missingMessage="真实姓名必填!"  value="" />
	    						<em id="errornicktxt" style="color:red;"></em>
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>部门:</td>
	    						<td>
	    						<input name="depId" id="depId" />
	    						<em id="errordepId" style="color:red;"></em></td>
	    					</tr>
	    					<tr>
	    						<td>性别:</td>
	    						<td>
	    							<select name="empsex" id="empsex">
									<option value="1">男</option>
									<option value="0">女</option>
									</select>
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>用户类型:</td>
	    						<td>
	    							<select name="leixing" id="leixing">
										<option value="1">联塑人员</option>
										<option value="2">客户</option>
									</select> 
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>手机:</td>
	    						<td>
                                   <input id="phonetxt" type="text" name="phonetxt" value="" />
                                   <em id="errorphonetxt" style="color:red;"></em>
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>邮箱:</td>
	    						<td><input id="emailtxt" type="text" name="emailtxt" value="" />
	    						<em id="erroremailtxt" style="color:red;"></em>
	    						</td>
	    					</tr>

	    					<tr align="center">
	    						<td colspan="2">
	    							<a id="btn1" class="easyui-linkbutton">确定</a>
	    							<a id="btn2" class="easyui-linkbutton">关闭</a>
	    						</td>
	    					</tr> 		    					    					    					    					    					    					    					
	    				</table>
	    		</form> 			
 			</div>
		</body>
</html>