<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lesso CC-修改头像</title>

<script type="text/javascript" src="../jqueryeasyui/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript"  src="../js/ajaxfileupload.js" charset="utf-8"></script> 
<script type="text/javascript" src="../js/upload.js"></script>
<script type="text/javascript">
$(function(){
	var size;
    var imghid = $('#imghidden').val();
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
		var  mima = $('#userpwd').val();
		
		
		var dk = $("#fileToUpload");
		var value = $("#fileToUpload")[0].files.length;
		if(value >0){
			$("#loading").ajaxStart(function() {
				$(this).show();
			}).ajaxComplete(function() {
				$(this).hide();
			});
	     var dks = getQueryString("name");
			$.ajaxFileUpload({
				url : '../upload?username='+dks,
				secureuri : false,
				fileElementId : 'fileToUpload',
				dataType : 'json',
				data : {username1 : $("#username").val()},
				success : function(data, status) {
					if(data.msg) {
						alert(data.msg);
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
 <style> body{background-color:#FFE4B5} </style>
</head>

<body>
	<h1 style="text-align:center;">Lesso CC</h1>
	<img id="loading" src="loading.gif" style="display: none;">
	<form name="form" action="" method="POST" enctype="multipart/form-data" style="text-align:center;">
		<div id="dsf" class="img" style="text-align:center;">
            <img src="" id="img0" width="110" height="110">
        </div>

		<input id="fileToUpload" type="file" size="45" name="fileToUpload"
			class="input" onchange="hjg(this)"><br>
		<i>温馨说明:头像的最佳尺寸为150×160</i><br>
		密码:<input type="password" id="userpwd" name="userpwd" > <br>
		<button class="button" onclick="return ajaxFileUpload();">上传</button>
		
	</form>

    <input type="hidden" id="imghidden" name="imghidden"/>
</body>
</html>