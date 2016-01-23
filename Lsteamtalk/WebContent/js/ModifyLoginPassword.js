//初始化
$(function () {
//	alert(IsPC());   
    InitPage();
    $("#ConfirmBtn").click(function () {
        var checkPayPwdIsValid = true;
        var checkPayNewPwdIsValid = CheckPayNewPwd();
        var checkPayPwdConfirmIsValid = CheckPayPwdConfirm();

        checkPayPwdIsValid = SetEmpty("#textPayPwd", "请输入原登录密码", "#labelPayPwd", checkPayPwdIsValid);
        checkPayNewPwdIsValid = SetEmpty("#textPayNewPwd", "请输入新的登录密码", "#labelPayNewPwd", checkPayNewPwdIsValid);
        checkPayPwdConfirmIsValid = SetEmpty("#textPayPwdConfirm", "请再次输入新的登录密码", "#labelPayPwdConfirm", checkPayPwdConfirmIsValid);

        if( checkPayPwdIsValid && checkPayNewPwdIsValid && checkPayPwdConfirmIsValid){
            var loginame = getQueryString("name");
			$.ajax({
				type:'post',
				url:'../privilegemgmt/users.action',
				cache:false , 
				dataType:'json',
				data:{
					method:'xgmima',
					loginame:loginame,
					oldpassword:$('#textPayPwd').val(),
				    newpassword:$('#textPayPwdConfirm').val()
				} ,
				success:function(r){
					if(r[0] ==null){
						alert("内部错误,请与信息管理中心联系,谢谢！");
					}else{
						var da = r[0];
						if(!da.hasError){
							if(!IsPC()){
								window.stub.jsFinishPassword();
//								 myObj.jsFinishPassword("1");  
							}
							var url ="../html/mp.html";
							url=encodeURI(encodeURI(url));
							window.location.href=url;
						}else{
							alert(da.exceptionMag);
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




});

function getQueryString(name) {
var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
var r = window.location.search.substr(1).match(reg);
if (r != null) return unescape(r[2]); return null;
} 

function SetEmpty(sendId, errorText, sendLabelId, isValid) {
    if (typeof ($(sendId).val()) == "undefined" || $(sendId).val() == "" || $(sendId).val() == null) {
        $(sendId).parent().parent().find(".error").text(errorText).show();
        isValid = false;
    }
    IsHidden(isValid, sendLabelId);
    return isValid;
}

function IsHidden(isValid, sendLabelId) {
    if (isValid) {
        $(sendLabelId).css("display", "none").text();
    }
    else {
        $(sendLabelId).css("display", "inline-block").text();
    }
}

function InitPage() {
    IsHidden(true, "#labelPayPwd");
    IsHidden(true, "#labelPayNewPwd");
    IsHidden(true, "#labelPayPwdConfirm");
    	$("#textPayPwd").keyup(function(){
	       ClickPayPwd();
	     });
		$("#textPayNewPwd").keyup(function(){
	       ClickPayNewPwd();
	     });
		$("#textPayPwdConfirm").keyup(function(){
	       ClickPayPwdConfirm();
	     });

}

function CheckPayNewPwd() {
    var $payNewPwd = $("#textPayNewPwd");
    var payNewPwdText = $("#textPayNewPwd").val();
    var isValid = true;
    $payNewPwd.parent().parent().parent().find(".error").text("");
    if (typeof ($("#textPayNewPwd").val()) != "undefined" && $("#textPayNewPwd").val() != "" && $("#textPayNewPwd").val() != null) {
        var Format = /^([\x21-\x7eA-Za-z0-9]){6,12}$/;
        var PureNum = /^(?!\d+$)/;
        if (Format.test(payNewPwdText) == false || PureNum.test(payNewPwdText) == false) {
            $payNewPwd.parent().parent().find(".error").text("与原登录密码区分，6-12位大小写字母、数字或符号的密码，勿用纯数字").show();
            isValid = false;
        }
    }
    IsHidden(isValid, "#labelPayNewPwd");
    return isValid;
}

function CheckPayPwdConfirm() {
    var $payPwdConfirm = $("#textPayPwdConfirm");
    var payPwdConfirmTxet = $("#textPayPwdConfirm").val();
    var isValid = true;
    $payPwdConfirm.parent().parent().find(".error").text("");
    if (typeof ($("#textPayPwdConfirm").val()) != "undefined" && $("#textPayPwdConfirm").val() != "" && $("#textPayPwdConfirm").val() != null) {
        if ($.trim($("#textPayPwdConfirm").val()) != $.trim($("#textPayNewPwd").val())) {
            $payPwdConfirm.parent().parent().find(".error").text("输入密码不一致").show();
            isValid = false;
        }
    }
    IsHidden(isValid, "#labelPayPwdConfirm");
    return isValid;
}

function ClickPayPwd() {
    $("#labelPayPwd").css("display", "none").text();
}

function ClickPayNewPwd() {
    $("#labelPayNewPwd").css("display", "none").text();
}

function ClickPayPwdConfirm() {
    $("#labelPayPwdConfirm").css("display", "none").text();
}

//for mobile
function IsPC() {
	//平台、设备和操作系统
    var system = {
        win: false,
        mac: false,
        xll: false,
        ipad:false
    };
    //检测平台
    var p = navigator.platform;
    system.win = p.indexOf("Win") == 0;
    system.mac = p.indexOf("Mac") == 0;
    system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);
    system.ipad = (navigator.userAgent.match(/iPad/i) != null)?true:false;
    //跳转语句，如果是手机访问就自动跳转到wap.baidu.com页面
    if (system.win || system.mac || system.xll||system.ipad) {
            return true;
    } else {

    	return false;
    }
}


