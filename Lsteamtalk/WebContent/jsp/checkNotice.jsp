<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
 <link type="text/css" rel="stylesheet" href="../css/checkNotice.css"/>
<title>通知公告</title>
</head>
<body>
<!-- 
<div id="Layer1" style="position:absolute; width:100%; height:100%; z-index:-1">    
<img src="../Images/139.jpg" height="100%" width="100%"/>    
</div>    
 -->
<div class="container">
	<div  class="newsTex">
		<h1>${notice.title}</h1>
		 <div class="msgbar">通知时间:${notice.created}</div>
	</div>
	<div class="newsCon">
		${notice.content}
	</div>
</div>

</body>
</html>