$(function(){
	//alert(123);
	$('#loginbtn').click(function(){
		if(check()){
			$.ajax({
				type:'post',
				url:'../privilegemgmt/login.action',
				cache:false , 
				dataType:'json',
				data:{
					username:$('#username').val(),
					password:$('#password').val()
				} ,
				success:function(r){
					if(r[0] ==null){
						alert("不存在该用户,如有疑问请与信息管理中心联系！");
					}else{
						var da = r[0];
						if(!da.hasError){
							var url ="Home.jsp";
//							url=encodeURI(encodeURI(url));
							window.location.href=url;
						}else{
							alert("登录失败，错误信息:"+da.exceptionMag);
						}
					}

				},
				error:function(r){
					alert("内部错误,请与信息管理中心联系,谢谢！");
				}
			});
        
		}
	});
});

function check(){
   var username = $('#username').val();
   var pwd = $('#password').val();
   var isok = true;
   if (typeof (username) == "undefined" || username == "" || username == null){
	   alert("请输入登录名");
	   isok = false;
   }
   if (typeof (pwd) == "undefined" || pwd == "" || pwd == null) {
	   isok = false;
       alert("请输入密码");
   }
   return isok;
}