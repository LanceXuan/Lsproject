<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../jqueryeasyui/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript"  src="../jqueryeasyui/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript"  src="../jqueryeasyui/locale/easyui-lang-zh_CN.js" charset="utf-8" type="text/javascript"></script>
<script type="text/javascript"  src="../js/noticeReadStatus.js" charset="utf-8"></script> 
<link href="../jqueryeasyui/themes/default/easyui.css" rel="stylesheet">
<link href="../jqueryeasyui/themes/icon.css" rel="stylesheet">
<title>公告阅读状态</title>
</head>
<body>
<!-- 
    <div id="tb" style="padding:5px;height:auto">    
        <div style="margin-bottom:5px">  
                                状态：
            <select name="chalei" id="chalei">
                <option value="0">全部</option>
				<option value="1">已读</option>
				<option value="2">未读  </option>
			</select>   
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" name="searchbtn" id="searchbtn" >查询</a>
        </div> 
-->
    </div> 
		<div id="cc" class="easyui-layout" fit=true style="width:auto;height:auto;">  
		    <div region="center" style="padding:5px;">
		    <table id="t_gm"></table>
		    </div>  
		</div> 
</body>
</html>