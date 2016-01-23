<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../jqueryeasyui/jquery.min.js"></script>
<script type="text/javascript" >
$(function(){
	$('#fsdf').click(function(){
		$.ajax({
			type : 'post',
			url : '../privilegemgmt/imme.action',
			cache : false,
			dataType : 'json',
			data : {
				type : $('#dkujh').val(),
				encmsg:$('#encmsg').val(),
				method:'testdec'
			},
			success: function(data) {
				$('#neirong').val(data[0].remarka);
			},
			error : function(r) {
				alert("内部错误,请与信息管理中心联系,谢谢！");
			}
		});
	});
});
</script>
<title>Insert title here</title>
</head>
<body>
<form> 
 
消息<textarea name="encmsg" id="encmsg" style="width:100%;; height:100%;"></textarea>
<br/>
结果显示<textarea name="neirong" id="neirong" style=" width:100%;; height:100%;"></textarea> 
<br/>
类型:<select name='type' id="dkujh" style='width:58px'>
<option value='1' selected>加密</option>
<option value='2'>解密</option>
</select>
<br/>
<input type="button" name="dec" id="fsdf" value="提交" 

height=15 width=120>

</form> 
</body>
</html>