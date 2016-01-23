$(function () {
    Init();
    InitPage();
    $("#empsex").keyup(function(){
    	$("#showsexError").html("").hide();
    });
    $("#txtphone").keyup(function(){
    	$("#showphoneError").html("").hide();
    });
    $("#txtemail").keyup(function(){
    	$("#showemailError").html("").hide();
    });
});

function Init(){
	var name = getQueryString("name");
	var userid = getQueryString("idu");
	$.ajax({
		type:'post',
		url:'../privilegemgmt/users.action',
		cache:false , 
		dataType:'json',
		data:{
			method:'setuserinfo',
			id:userid,
			name:name,
		} ,
		success:function(r){
			if(r[0] !=null){
           //    alert(r[0].nick);
               $('#TrueName').val(r[0].nick);
               $('#empsex').val(r[0].sex);
               $('#txtphone').val(r[0].phone);
               $('#txtemail').val(r[0].email);
               $('#txtgxqm').val(r[0].sign_info);
			}

		},
		error:function(r){
			alert("内部错误,请与信息管理中心联系,谢谢！");
		}
	});
}

function InitPage() {
    $('#ConfirmBtn').click(function(){
       	$("#showsexError").html("").hide();
    	$("#showphoneError").html("").hide();
    	$("#showemailError").html("").hide();
    	if(Validation()){
    		var name = getQueryString("name");
    		var userid = getQueryString("idu");
			$.ajax({
				type:'post',
				url:'../privilegemgmt/users.action',
				cache:false , 
				dataType:'json',
				data:{
					method:'update',
					id:userid,
					name:name,
					nick:$('#TrueName').val(),
					sex:$('#empsex').val(),
					phone:$('#txtphone').val(),
					email:$('#txtemail').val(),
					sign_info:$('#txtgxqm').val()
				} ,
				success:function(r){
					if(r[0] ==null){
						alert("内部错误,请与信息管理中心联系,谢谢！");
					}else{
						var da = r[0];
						if(!da.hasError){
							if(!IsPC()){
								window.data.jsFinishData();
							}
							var gender = $('#empsex').val();
							var csNickName = $('#TrueName').val();
							var email = $('#txtemail').val();
							var telephone =$('#txtphone').val();
							var signature = $('#txtgxqm').val();
							editdata(userid,gender,csNickName,email,telephone,signature);
							var url ="../html/mp.html";
							url=encodeURI(encodeURI(url));
							window.location.href=url;
						}else{
							alert("内部错误,请与信息管理中心联系,谢谢！");
						}
					}

				},
				error:function(r){
					alert("内部错误,请与信息管理中心联系,谢谢！");
				}
			});
        
    	}else{
    		return false;
    	}
    });
}

//取url的参数
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null;
} 

//js检查数据的准确性
function Validation(){
	var isTrue = true;
	var phonetxt = $.trim($("#txtphone").val());
	var telephoneExp = /^[1][3,5,8][0-9]{9}$/;
	if(phonetxt !=null && phonetxt != undefined && phonetxt !=""){
		if (telephoneExp.test(phonetxt) == false) {
	        $("#showphoneError").html("请输入正确手机号码").show();
	        isTrue = false;
	    }
	}
	var emailtxt = $.trim($("#txtemail").val());
	if(emailtxt !=null && emailtxt != undefined && emailtxt !=""){
	    var reg = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	    if (reg.test(emailtxt) == false) {
	        $("#showemailError").html("请输入正确电子邮箱").show();
	        isTrue = false;
	    }
	}
    var nametxt = $.trim($("#TrueName").val());
    if(nametxt==undefined || nametxt== "" || name==null){
    	$("#showEmptyNameError").html("请输入真实姓名").show();
        isTrue = false;
    }else{
    	if(nametxt.length >10 || nametxt.length<2){
    		$("#showEmptyNameError").html("请输入2到10位的姓名").show();
            isTrue = false;
    	}
    }
    
    var sextxt = $('#empsex').val();
    var sdf = (sextxt==undefined || sextxt== "" || sextxt==null);
    if(sdf){
    	isTrue = false;
    	$("#showsexError").html("请输入性别").show();
    }
//    var gxqm = $.trim($("#txtgxqm").val());
//    if(gxqm==undefined || gxqm== "" || gxqm==null){
//    	$("#showgxqmError").html("请输入个人签名").show();
//        isTrue = false;
//    }
    
    return isTrue;
}









