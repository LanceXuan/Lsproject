<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.lesso.beans.Z_Vkorg" %>
<%@ page import="com.lesso.dao.Z_VkorgDao" %>
<%@ page import="com.lesso.daoImpl.Z_VkorgDaoImpl" %>
<%@ page import="com.lesso.beans.Z_Depart_Type_Right" %>
<%@ page import="com.lesso.dao.Z_Depart_Type_RightDao" %>
<%@ page import="com.lesso.daoImpl.Z_Depart_Type_RightDaoImpl" %>

<%
Z_VkorgDao dao = new Z_VkorgDaoImpl();
List<Z_Vkorg> list = dao.getList();
Z_Depart_Type_RightDao daoa = new Z_Depart_Type_RightDaoImpl();
List<Z_Depart_Type_Right> lista = daoa.getZ_Depart_Type_RightList(2);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type="text/javascript" src="../multiselect/jquery-1.8.1.min.js"></script>
<script type="text/javascript"  src="../jqueryeasyui/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript"  src="../jqueryeasyui/locale/easyui-lang-zh_CN.js" charset="utf-8" type="text/javascript"></script>
<script type="text/javascript" src="../js/json2.js"></script>
<script type="text/javascript" src="../multiselect/jquery.multiselect2side.js"></script>
<script type="text/javascript" src="../js/usertovkorg.js"></script>
<link href="../jqueryeasyui/themes/default/easyui.css" rel="stylesheet">
<link href="../jqueryeasyui/themes/icon.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../multiselect/jquery.multiselect2side.css" />
<style type="text/css"> 
.align-center{ 
margin:0 auto; /* 居中 这个是必须的，，其它的属性非必须 */ 
width:1200px; /* 给个宽度 顶到浏览器的两边就看不出居中效果了 */ 
text-align:center; 
} 
</style> 
<title>用户对话公司</title>
</head>
<body>
<div class="align-center">
	<h3>联塑员工对客户公司权限设置</h3>
	权限部门:
	<select id="ee" data-options="editable:true,width:180"></select>
	<div id="sq">
	<div style="color:#99BBE8;background:#fafafa;padding:5px;">请选择公司</div>
	<%
	if(lista !=null){
	for(int i=0;i<lista.size();i++){
	%>
	<input type="radio" name="langradio" value="<%=lista.get(i).getDepartId() %>"><span><%=lista.get(i).getDepartId_Text() %></span><br/>
	<%
	}
	}
	%>
	</div>
	<br/>




	<div id="divcg" style="display:none;">
	工作人员: <input id="cg" style="width:180px" /><input type="text" id="txtGe" /><input type="button" id="searchbtn" value="查询"/>
	    <input type="hidden" id="hdKeyword" />  
	    <input type="hidden" id="hddkj" />  
	    <input type="hidden" id="txtGender" />  
	</div>



		<div class="selectPersion" id="selectPersion" >
				<div id="sel">
				</div>
		</div>






	<div style="display:none;">
	<select id="cc" data-options="editable:true,width:180"></select>
	<div id="sp">
	<div style="color:#99BBE8;background:#fafafa;padding:5px;">请选择权限公司</div>
	<%
	if(list !=null){
	for(int i=0;i<list.size();i++){
	%>
	<input type="checkbox" name="lang" value="<%=list.get(i).getVkorg() %>"><span><%=list.get(i).getVkorg_name() %></span><br/>
	<%
	}
	}
	%>
	</div>

</div>

	<div>
	<input type="button" id="confirmbtn" value="确认修改" style="display:none;"/>
	</div>
	
</div>
</body>
</html>