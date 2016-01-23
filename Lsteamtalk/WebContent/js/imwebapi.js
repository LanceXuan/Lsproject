
//修改头像
//ch_id:修改人id
//str_url:图片地址
function changepic(ch_id,str_url){


//	var jsonData = "{\"ch_id\":25,\"str_url\":\"http://im2.lesso.com:8181/Lsteamtalk/upload/201512301655546154.jpg\"}";	
	var json = {
			ch_id:parseInt(ch_id),
			str_url:str_url
	};
	
	var jsonData = JSON.stringify(json);
	$.ajax({
		type: "post",
		url: "http://192.168.4.152:8400/cpic/changepic",
		data: jsonData,
		dataType: "json",
		success: function(data) {					
		//	alert("ok");					
		}
	});
}

//编辑资料
//user_id:修改人id
//gender:性别  1：男，2：女
//csNickName:昵称
//email:邮箱
//telephone:电话
//signature:个性签名
function editdata(userid,gender,csNickName,email,telephone,signature){

//	var jsonData = "{\"user_id\":25,\"gender\":1,\"csNickName\":\"veilen\",\"email\":\"123456@qq.com\",\"telephone\":\"12345678910\",\"signature\":\"goodmood\"}";	
	
var json = {
		user_id:parseInt(userid),
		gender:parseInt(gender),
		csNickName:csNickName,
		email:email,
		telephone:telephone,
		signature:signature
};
var jsonData = JSON.stringify(json);
$.ajax({
		type: "post",
		url: "http://192.168.4.152:8400/edata/editdata",
		data: jsonData,
		dataType: "json",
		success: function(data) {					
//			alert("ok");					
		}
	 });

}


//发送消息
//req_id:发送人id
//to_id:接收人id
//msg_type:发送类型
//msg_content:内容
function sendmsgapi(reqid,toid,content){


//	var jsonData = "{\"req_id\":25,\"to_id\":13,\"msg_type\":1,\"msg_content\":\"o123\"}";	
	var json = {
			req_id:parseInt(reqid),
			to_id:parseInt(toid),
			msg_type:1,
			msg_content:content
	};
	var jsonData = JSON.stringify(json);
	$.ajax({
        	type: "post",
		url: "http://192.168.4.152:8400/api/sendmsg",
		data: jsonData,
		dataType: "json",
		success: function(data) {					
			alert("ok");					
		}
	});
}
