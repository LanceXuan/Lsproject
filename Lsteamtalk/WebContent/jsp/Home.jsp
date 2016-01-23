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
<title>LSTeamTalk WorkSystem</title>
<script src="../jqueryeasyui/jquery.min.js"></script>
<script src="../jqueryeasyui/jquery.easyui.min.js"></script>
<script src="../jqueryeasyui/locale/easyui-lang-zh_CN.js"></script>
<script src="../js/Home.js"></script> 
<link href="../jqueryeasyui/themes/default/easyui.css" rel="stylesheet">
<link href="../jqueryeasyui/themes/icon.css" rel="stylesheet">
</head>
<body onLoad="getTime()">
<div id="cc" class="easyui-layout" fit=true style="width:100%;height:100%;">  
		    <div region="north" title="LSTeamTalk"  split="false" style="height:100px;">
		    <span >欢迎
		     <em><%=username %></em>
		     <a href="javascript:void(0)" onclick="editUserPwd()" style="color: #fff">修改密码</a>
		     <a href="javascript:void(0)" onclick="logout()" style="color: #fff">安全退出</a>
    		</span>
		    <b id="bfortime"></b>
		    </div>  
		    <!-- 
		    <div region="south" title="South Title" split="true" style="height:100px;"></div>  
		    <div region="east" collapsed=true iconCls="icon-reload" title="East" split="true" style="width:100px;"></div>  
		     -->
		    <div region="west"  iconCls="icon-ok" split="true" title="菜单" style="width:200px;">
				<div id="aa" class="easyui-accordion" fit=true >  
				    <div title="用户管理"  selected="true"  style="overflow:auto;padding:10px;">  
				    	<a title="users.jsp" href="#" >用户列表</a> <br/>
				    	<a title="group.jsp" href="#" >群组管理</a> <br/>
				    	<a title="ptophistory.jsp" href="#" >用户间聊天历史记录</a> <br/>
				    	<a title="gmhistory.jsp" href="#" >群组聊天历史记录</a> <br/>
				    	<a title="notices.jsp" href="#">公告列表</a><br/>
				    	<a title="../ccNotice.jsp" href="#" >公告发布</a> <br/>
				    	<a title="https://www.baidu.com" >用户功能设定</a> 
				    </div>  
				    <div title="部门管理" style="padding:10px;">
				    <a title="Dept.jsp" href="#" >部门管理</a> <br/>  
				    </div> 
				    <div title="系统设置" style="overflow:auto;padding:10px;">  
				    <a title="discovery.jsp" href="#" >发现管理</a> <br/>  
				    </div>  
				    <div title="权限管理" style="overflow:auto;padding:10px;"> 
				     <a title="controldepart.jsp" href="#" >权限部门</a> <br/>
				    <a title="usertovkorg.jsp" href="#" >用户对公司权限</a> <br/>
			    	<a title="userpoweruser.jsp" href="#" >用户对客户权限</a> <br/>
			    	<a title="copycontrol.jsp" href="#" >用户间权限复制</a> <br/>
				    </div> 
				     
				</div>  
		    </div>  
		    <div region="center"  title="LSTeamTalk主界面" style="padding:5px;">
				<div id="tt" class="easyui-tabs" fit=true style="width:500px;height:250px;">  

				</div>  
		    </div>  
		</div>  
</body>
</html>