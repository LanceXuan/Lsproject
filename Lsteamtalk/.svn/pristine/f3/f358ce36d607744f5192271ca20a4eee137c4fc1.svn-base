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
<script type="text/javascript" src="../js/json2.js"></script>
<script type="text/javascript"  src="../js/dept.js" charset="utf-8"></script> 

<link href="../jqueryeasyui/themes/default/easyui.css" rel="stylesheet">
<link href="../jqueryeasyui/themes/icon.css" rel="stylesheet">
<link href="../css/selector.css" rel="stylesheet">

<title>部门树</title>
</head>
<body>
<div>
<table id="tree_org"></table>
    <div id="tb" style="padding:5px;height:auto">    
        <div style="margin-bottom:5px">    
            <a href="#" id="depAdd" class="easyui-linkbutton" iconCls="icon-add" plain="true">增加</a>
            <a href="#" id="depEdit" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>     
            <a href="#" id="depRemove" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>    
            <a href="#" id="depleaderset"  class="easyui-linkbutton" iconCls="icon-cut" plain="true">设置部门负责人</a>
            <a href="#" id="refreshdep"  class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新页面</a>        
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" style="display:none">Search</a>    
        </div>    
    </div>    
</div>
  		<div id="div1" title="组合机构" class="easyui-dialog" style="width:400px;" closed=true modal=true >
  				<form id="myform" method="post">
  							<input type="hidden" name="id" value="" />
  							<input type="hidden" name="pid" value=""/>
  						<table>
  							<tr>
  								<td>新部门名称:</td>
  								<td><input name="name" value=""/>
  								</td>
  								<td><em id="errorAdd" style="color:red;"></em></td>
  							</tr>
   							<tr>
  								<td>部门优先级:</td>
  								<td><input name="priority" value="1" />
  								
  								</td>
  								<td><em id="errorparicpl" style="color:red;"></em></td>
  							</tr>
  							<tr id="sjbmtr" style="display:none">
  							   <td>上级部门</td>
  							   <td>
  							      <input name="chadepId" id="chadepId" />
  							   </td>
  							   <td><em id="errordepid" style="color:red;"></em></td>
  							</tr>
  							<tr align="center" style="text-align:center;">
  								<td colspan="2">
  									<a id="btn1" class="easyui-linkbutton">确定</a>
  									<a id="btn2" class="easyui-linkbutton">取消</a>
  								</td>
  							</tr>  							  							  							 							
  						</table>
  				</form>
  		</div>
  		
  		<div id="div2" title="编辑部门负责人" class="easyui-dialog" style="width:400px;" closed=true modal= true>
  		  <form id="editleader" method="post">
  		      <input type="hidden" name="leaderid" value="" />
  		      <table>
  							<tr>
  								<td>部门负责人:</td>
  								<td>
	  								<SELECT id="bmfzr" name="bmfzr" style='width:220px;'>
							            <OPTION value="0">请选择</OPTION>
							        </SELECT>
	  								<em id="errorbmfzr" style="color:red;"></em>
	  								
  								</td>
  							</tr>
  							<tr align="center">
  								<td colspan="2">
  									<a id="btn3" class="easyui-linkbutton">确定</a>
  									<a id="btn4" class="easyui-linkbutton">取消</a>
  								</td>
  							</tr>  							  							  							 							
  						</table>
  		  </form>
  		</div>
  		
  		
		<div id="mm" class="easyui-menu" style="width:120px;">
			<div onclick="append()">新增组织机构</div>
			<div onclick="update()">修改组织机构</div>
			<div onclick="remove()">删除组织机构</div>
		</div> 
</body>
</html>