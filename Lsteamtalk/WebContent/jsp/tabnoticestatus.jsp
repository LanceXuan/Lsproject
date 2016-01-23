<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <% 
    String id = request.getParameter("id");
    String ds = "noticeReadStatus.jsp?id="+id+"&&status=1";
    String df = "noticeReadStatus.jsp?id="+id+"&&status=0";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../jqueryeasyui/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript"  src="../jqueryeasyui/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript"  src="../jqueryeasyui/locale/easyui-lang-zh_CN.js" charset="utf-8" type="text/javascript"></script>
<link href="../jqueryeasyui/themes/default/easyui.css" rel="stylesheet">
<link href="../jqueryeasyui/themes/icon.css" rel="stylesheet">
<script type="text/javascript">
$(function(){ 
	
	
	var dk = document.body.scrollWidth;
	var kjdf = window.screen.height-200;
	 
	alert(dk);
	alert(kjdf);
	$("#tabs").tabs({ 
	width:dk, 
	height: kjdf 
	}); 
	
	

	}); 
</script>
<title>公告阅读状态</title>
</head>
<body>
<!-- 
	<h2>Basic Tabs</h2>
	<p>Click tab strip to swap tab panel content.</p> style="width:470px;height:290px"
	style="width:800px;height:590px"
	 	-->
	<div id="tabs" class="easyui-tabs" >
		<div title="已读" >
            <iframe scrolling="yes" frameborder="0"  src=<%=ds %> style="width:100%;height:100%;"></iframe>
		</div>
		<div title="未读" >
			<iframe scrolling="yes" frameborder="0"  src=<%=df %> style="width:100%;height:100%;"></iframe>
		</div>
		
	</div>

	
</div> 
</body>
</html>