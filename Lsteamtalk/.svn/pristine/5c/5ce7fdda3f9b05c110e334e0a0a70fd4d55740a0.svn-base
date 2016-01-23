<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@ page import="com.lesso.beans.IMUser" %>
  <%@ page import="com.lesso.service.IMUserService" %>
    <%@ page import="com.lesso.serviceImpl.IMUserServiceImpl" %>
    <%
  String username = request.getParameter("name");
  String idu =request.getParameter("idu");
  IMUserService sevice = new IMUserServiceImpl();
  IMUser kuser = sevice.findIMUser(username);
  String nick = "";
  if(kuser !=null){
	  nick = kuser.getNick();
  }
  %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改登录密码-LessoCC</title>
<script type="text/javascript" src="../jqueryeasyui/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript"  src="../js/json2.js" charset="utf-8"></script> 
<script type="text/javascript"  src="../js/ModifyLoginPassword.js" charset="utf-8"></script> 
<link rel="stylesheet" type="text/css" href="../css/Common2.css"/>
<link rel="stylesheet" type="text/css" href="../css/Header.css"/>
<link rel="stylesheet" type="text/css" href="../css/ModifyLoginPassword_SetNewLoginPassword.css"/>
</head>
<body>
<!--top-->
<div id="top" style="min-width:320px">
	<dl>
        <dt>你好,<%=nick %>  欢迎使用LessoCC！</dt>
        <dd>

        </dd>
    </dl>
</div> 

<!--header-->
<div id="header" style="min-width:320px">
	<dl>
        <dt><a href="#" title="LSTeamTalk"></a></dt>
        <dd>&nbsp;</dd>
    </dl>
</div>
<!--Main-->

<div class="content_div">
	<div class="content_main">
		<p class="content_title">修改登陆密码</p>
		
		<div class="content_middle">
			<div class="content_form">
				<div class="content_row">
					<div class="left">当前登录密码</div>
					<div class="right"><input name="PayPwd" id="textPayPwd" type="password" size="30" placeholder="输入原登录密码" /></div>
					<br />
					<div class="errText"><em id="labelPayPwd"  class="error"></em></div>
				</div>
				<br />
				<div class="content_row">
					<div class="left">新的登录密码</div>
					<div class="right"><input name="PayPwd" id="textPayNewPwd" type="password" size="30" placeholder="输入新的登录密码" /></div>
					<br />
					<div class="errText"><em id="labelPayNewPwd" class="error"></em></div>
				</div>
				<br />
				<div class="content_row">
					<div class="left">确认登录密码</div>
					<div class="right"><input name="PayPwdConfirm" id="textPayPwdConfirm" type="password" size="30" placeholder="确认登录密码" /></div>
					<br />
					<div class="errText"><em id="labelPayPwdConfirm" class="error"></em></div>
				</div>
				
				<div class="content_button">
					<p style="width:162px;height:50px;margin:0 auto"><input name="ConfirmBtn" type="button" id="ConfirmBtn" value="提交" /></p>
				</div>
			</div>
				
			<div class="content_tip">
				<h4>温馨提示：</h4>
				<p>
				· 定期更换密码可以让你的账户更加安全<br />
				· 建议密码采用字母和数字混合，并且不短于6位，不要使用自己的公开信息作为密码，如电话、生日、车牌等
				</p>
			</div>
		</div>
	</div>
</div>

</body>
</html>