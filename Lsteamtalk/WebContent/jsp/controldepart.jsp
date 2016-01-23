<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../jqueryeasyui/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript"  src="../jqueryeasyui/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript"  src="../jqueryeasyui/locale/easyui-lang-zh_CN.js" charset="utf-8" type="text/javascript"></script>
<script type="text/javascript"  src="../js/controldepart.js" charset="utf-8"></script> 
<link href="../jqueryeasyui/themes/default/easyui.css" rel="stylesheet">
<link href="../jqueryeasyui/themes/icon.css" rel="stylesheet">
<title>权限部门设置</title>
</head>
<body>
 <div id="tb" style="padding:5px;height:auto">    
        <div style="margin-bottom:5px"> 
            <a href="#" id="depAdd" class="easyui-linkbutton" iconCls="icon-add" plain="true">增加</a>
            <a href="#" id="depEdit" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>     
            <a href="#" id="depRemove" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>        
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
		
		
				<div id="mydialog" title="权限部门" class="easyui-dialog" style="width:400px;" closed=true modal=true >
  				<form id="myform" method="post">
  							<input type="hidden" name="id" value="" />
  						<table>
  							<tr>
  								<td>部门:</td>
  								<td><input name="chadepId" id="chadepId" /><em id="errordep" style="color:red;"></em></td>
  							</tr>
  							<tr>
  								<td>名称:</td>
  								<td><input name="depname" id="depname" /><em id="errordepname" style="color:red;"></em></td>
  							</tr>
   							<tr>
  								<td>客户类型:</td>
  								<td>
	    							<select name="deptype" id="deptype">
									<option value="2" selected="selected">经销商</option>
									<option value="3">供应商</option>
									</select>
	    						<em id="errordeptype" style="color:red;"></em></td>
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