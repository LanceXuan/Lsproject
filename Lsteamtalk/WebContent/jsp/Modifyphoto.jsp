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
  String avatar = "";
  if(kuser !=null){
	  nick = kuser.getNick();
	  avatar = kuser.getAvatar();
  }
  
  String decname=Security.getInstance().DecryptMsg(request.getParameter("name"));
  String decidu=Security.getInstance().DecryptMsg(request.getParameter("idu"));
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../css/Common2.css"/>
<link rel="stylesheet" type="text/css" href="../css/Header.css"/>
<link rel="stylesheet" type="text/css" href="../css/Footer.css"/>
<link rel="stylesheet" type="text/css" href="../css/ModifyLoginPassword_Success.css"/>
<script type="text/javascript" src="../jqueryeasyui/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript"  src="../js/ajaxfileupload.js" charset="utf-8"></script> 
<script type="text/javascript" src="../js/upload.js"></script>
<script type="text/javascript" src="../js/checkSystem.js"></script>
<script type="text/javascript" src="../js/imwebapi.js"></script>
<script type="text/javascript">
$(function(){
    var imghid = $('#imghidden').val();
    var dec_name = '<%=decname%>';
    var dec_id = '<%=decidu%>';

    if(imghid != null && imghid != undefined){
    	 var objUrl = getObjectURL(imghid) ;
    	    console.log("objUrl = "+objUrl) ;
    	    if (objUrl) {
    	        $("#img0").attr("src", objUrl) ;
    	    }
    	    var imghid = $('#imghidden');
    	    if(imghid != null && imghid != undefined){
    	    	imghid.val(value.files[0]);
    	    }
    	    changeImagesSize();
    }
});
	function ajaxFileUpload() {

		
		var dk = $("#fileToUpload");
		var value = $("#fileToUpload")[0].files.length;
		if(value >0){
			var  mima = $('#userpwd').val();
			if(mima == null || mima == undefined || mima ==""){
				alert("请输入密码,验证身份!");
				return false;
			}
			if(size == null || size == undefined || size>2048){
				alert("图片大小超过了2M");
				return false;
			}
			$("#loading").ajaxStart(function() {
				$(this).show();
			}).ajaxComplete(function() {
				$(this).hide();
			});
	     var dks = getQueryString("name");
	     var ch_id = '<%=idu%>';
			$.ajaxFileUpload({
				url : '../upload?username='+dks+'&&pwd='+mima,
				secureuri : false,
				fileElementId : 'fileToUpload',
				dataType : 'json',
				data : {username1 : 111},
				success : function(data, status) {
					if(data.status == 1) {
						if(!IsPC()){
							window.avatar.jsFinishAvatar();
//							 myObj.jsFinishPassword("1");  
						}
						alert("修改头像成功");
						var str_url = data.photourl;
						changepic(ch_id,str_url);
					}else{
						alert("修改头像失败,原因是"+data.msg);
					}
				},
				error : function(data, status, e) {
					alert('上传出错');
				}
			});
		}else{
			alert("请选择图片!");
		    return false;
		}
		   return false;
	}
	
	function getQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return unescape(r[2]); return null;
	} 
</script>
<title>修改图像</title>
</head>
<body>
<!--top-->
<div id="top">
	<dl>
        <dt>你好,<%=nick %> 欢迎使用LessoCC！</dt>
    </dl>
</div> 

<!--header-->
<div id="header">
	<dl>
        <dt><a href="#" title="LSTeamTalk"></a></dt>
        <dd>&nbsp;</dd>
    </dl>
</div>
<!--Main-->
<div id="Main">
	<dl class="inner" style="width:840px">
    	<dt>修改头像</dt>
		<dd>
			<img id="loading" src="loading.gif" style="display: none;">
			<form name="form" action="" method="POST" enctype="multipart/form-data" style="padding:12px">
				<div id="dsf" class="img" style="text-align:center;float:left;padding-bottom:5px">
		            <img src="<%=avatar %>" id="img0" width="110" height="110" style="border:1px solid #ccc"><br />
		            <span style="line-height:24px;font: 10px "微软雅黑",sans-serif;">温馨提示:头像的最佳尺寸为150×160,最大2M.</span>
		        </div>
				
				<div style="float:left;margin-left:10px;padding-bottom:10px">
					<input id="fileToUpload" type="file" size="45" name="fileToUpload" class="input" onchange="hjg(this)">
					<br>
					<br>
					密码：<input type="password" id="userpwd" name="userpwd" size="30" style="width:170px" placeholder="请输入登录密码" />
					<br>
					<br>
					<button class="button" id="ConfirmBtn" onclick="return ajaxFileUpload();" style="background-color:#0AC;width:100px;height:36px;line-height:32px;text-align: center;font-family:'微软雅黑','宋体',Arial; font-size:16px; border:0; padding:0;color:#FFF;-moz-border-radius: 4px;-webkit-border-radius: 4px;border-radius: 4px;-moz-opacity:1;-khtml-opacity:1;opacity:1;">上 传</button>
				</div>
			</form>

    	<input type="hidden" id="imghidden" name="imghidden"/>
      </dd>
	</dl>
</div>
</body>
</html>