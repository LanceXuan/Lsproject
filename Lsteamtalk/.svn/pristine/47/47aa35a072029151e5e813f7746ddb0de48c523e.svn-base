<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
          <%@ page import="com.lesso.beans.IMUser" %>
  <%@ page import="com.lesso.service.IMUserService" %>
    <%@ page import="com.lesso.serviceImpl.IMUserServiceImpl" %>
        <%@ page import="com.lib.*" %>
    <%
  String username = request.getParameter("name");
  String idu =request.getParameter("idu");
  IMUserService sevice = new IMUserServiceImpl();
  IMUser kuser = sevice.findIMUser(username);
  String nick = "";
  if(kuser !=null){
	  nick = kuser.getNick();
  }
  String decname=Security.getInstance().DecryptMsg(request.getParameter("name"));
  String decidu=Security.getInstance().DecryptMsg(request.getParameter("idu"));
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>设置用户资料-LessoCC</title>
<script type="text/javascript" src="../jqueryeasyui/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript"  src="../js/json2.js" charset="utf-8"></script> 
<script type="text/javascript"  src="../js/modifyuserinfo.js" charset="utf-8"></script> 
<script type="text/javascript"  src="../js/checkSystem.js" charset="utf-8"></script> 
<script type="text/javascript" src="../js/imwebapi.js"></script>
<link rel="stylesheet" type="text/css" href="../css/Common2.css"/>
<link rel="stylesheet" type="text/css" href="../css/Header.css"/>
<link rel="stylesheet" type="text/css" href="../css/Register_FillUserInfo.css"/>
</head>
<body>
<!--top-->
<div id="top" style="min-width:320px;">
	<dl>
        <dt>你好,<%=nick %>  欢迎使用LessoCC!</dt>
    </dl>
</div> 

<!--header-->
<div id="header" style="min-width:320px;">
	<dl>
        <dt><a href="#" title="LSTeamTalk"></a></dt>
        <dd>&nbsp;</dd>
    </dl>
</div>

<!--Main-->

<div class="content_div">
	<div class="content_main">
		<p class="content_title">编辑用户资料</p>
		
		<div class="content_middle">
		
			<div class="tipTitle">
				<h3>基本信息<i>让更多人认识你</i></h3>
			</div>
			<div class="content_form">
				<div class="content_row">
					<div class="left">性别</div>
					<div class="right">
						<select name="empsex" id="empsex" style="margin-top:5px">
							<option value="1">男</option>
							<option value="0">女</option>
						</select>
					</div>
					<br />
					<div class="errText"><em id="showsexError" style="display: none;"></em></div>
				</div>

				<div class="content_row">
					<div class="left">姓名</div>
					<div class="right"><input name="TrueName" id="TrueName" type="text" size="30" placeholder="填写真实姓名" /></div>
					<br />
					<div class="errText"><em id="showEmptyNameError" style="display: none;"></em></div>
				</div>
			</div>
			
			
			<div class="tipTitle">
				<h3>设置联系方式<i>沟通从心开始</i></h3>
			</div>
			<div class="content_form">
				<div class="content_row">
					<div class="left">手机号码</div>
					<div class="right"><input name="txtphone" id="txtphone" type="text" size="30" placeholder="输入手机号码" /></div>
					<br />
					<div class="errText"><em id="showphoneError" style="display: none;"></em></div>
				</div>
				<br />
				<div class="content_row">
					<div class="left">邮箱地址</div>
					<div class="right"><input name="txtemail" id="txtemail" type="text" size="30" placeholder="输入邮箱地址" /></div>
					<br />
					<div class="errText"><em id="showemailError" style="display: none;"></em></div>
				</div>
			</div>
			
			
			<div class="tipTitle">
				<h3>个性化设置<i>你的座右铭</i></h3>
			</div>
			<div class="content_form">
				<div class="content_row">
					<div class="left">个人签名</div>
					<div class="right"><textarea name="txtgxqm" id="txtgxqm" type="text" style="width:243px;height:48px;resize:none;" placeholder="输入个人签名"></textarea></div>
					<br />
					<div class="errText"><em id="showgxqmError" style="display: none;"></em></div>
				</div>
				<br />
				<div class="content_button">
					<p style="width:162px;height:50px;margin:0 auto"><input name="ConfirmBtn" type="button" id="ConfirmBtn"  value="确认修改" /></p>
				</div>
			</div>
			
		</div>
	</div>
</div>
</body>
</html>