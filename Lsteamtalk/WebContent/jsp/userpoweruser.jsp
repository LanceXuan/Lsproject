<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="com.lesso.beans.Z_Depart_Type_Right" %>
<%@ page import="com.lesso.dao.Z_Depart_Type_RightDao" %>
<%@ page import="com.lesso.daoImpl.Z_Depart_Type_RightDaoImpl" %>

<%
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
<script type="text/javascript" src="../js/userpoweruser.js"></script>
<link href="../jqueryeasyui/themes/default/easyui.css" rel="stylesheet">
<link href="../jqueryeasyui/themes/icon.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../multiselect/jquery.multiselect2side.css" />
<title>用户对用户的权限设置</title>
</head>
<body>
<h3>联塑员工对客户权限设置</h3>
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
	    <input type="hidden" id="txtGender" /> 
  </div>
  
  <!-- 左右选择开始 -->
  <div id="divsearchose" style="display:none;">
  客户编号:<input type="text" id="txtkun" />客户名:<input type="text" id="txtkuname" /><input type="button" id="searchchose" value="查询"/>
  
  </div>   
		<div class="selectPersion" id="selectPersion" >
				<div id="sel">
				</div>
		</div>
  <!-- 左右选择结束 --> 

<div>
<input type="button" id="confirmbtn" value="确认修改" style="display:none;"/>
</div>
</body>
</html>