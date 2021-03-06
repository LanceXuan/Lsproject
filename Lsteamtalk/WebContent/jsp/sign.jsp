<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <title>LSTeamTalk 登录</title>
  <link type="text/css" rel="stylesheet" href="../css/font-awesome.min.css"/>
  <!--[if IE 7]>
  <link type="text/css" rel="stylesheet" href="css/font-awesome-ie7.min.css"/>
  <![endif]-->
  <link type="text/css" rel="stylesheet" href="../css/base.css"/>
  <link type="text/css" rel="stylesheet" href="../css/login.css"/>
      <!--javascript start-->
    <script type="text/javascript" src="../jqueryeasyui/jquery.min.js"></script>
    <script type="text/javascript" src="../js/sign.js"></script>
    <!--javascript end-->
</head>
<body>
    <div id="login" class="group">
        <div id="login-inner" data-active="login">
            <h1 class="logo-mini"><span>LSTeamTalk</span> Content Admin System</h1>
            <div id="login-box" class="box">
            	<div class="hd">
            		<h2>用户登录</h2>
            	</div>
            	<div class="bd">
	                <form id="login-form"  name="login" novalidate="true">
	                    <p class="control-group">
	                        <label for="username"><i class="icon-user"></i><span>用户名：</span></label>
	                        <input class="ipt w278" type="text" placeholder="用户名" id="username" name="username" value="admin"/>
	                    </p>
	                    <p class="control-group">
	                        <label for="password"><i class="icon-lock"></i><span>密码：</span></label>
	                        <input class="ipt w278" type="password" placeholder="密码" id="password" name="password" value="admin"/>
	                    </p>
	                    <!-- 
	                    <p class="control-group">
	                        <label for="verifyCode"><i class="icon-key"></i><span>验证码：</span></label>
	                        <input class="ipt w191" type="text" placeholder="验证码" id="verifyCode" name="verifyCode" required min="1" max="4" maxlength="4"/>
	                        <img id="img_vcode" alt="..." src="captcha?complexity=99&size=30&length=4" width="80" height="30" onclick="javascript:_rvi()"/>
	                        <script language='javascript'>
	                          function _rvi(){document.getElementById('img_vcode').src = 'captcha?complexity=80&size=36&length=4&t='+Math.random(1000);}
	                        </script>
	                    </p>
	                     -->
	                    <p class="form-actions tc">
	                        <button class="btn" type="button" id="loginbtn">现在登录</button>
	                    </p>
	                </form>
            	</div>
            </div>
        </div><!-- /#login-inner -->
    </div><!-- /#login -->

</body>
</html>