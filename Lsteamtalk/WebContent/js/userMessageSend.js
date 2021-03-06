$('#chadepId').combotree({
	url : 'privilegemgmt/dept.action?method=TreeDept',
	width : 360,
	hight : 22,
	checkbox : false,
	multiple : false,
	onSelect : function(node) {
		getUserList(node.id);
	}
});
$("#liOption").multiselect2side({
	selectedPosition : 'right',
	moveOptions : false,
	labelsx : '待选区',
	labeldx : '已选区'
});
$('#liOptionms2side').html($('#userList').html());

function getUserList(deptid) {
	$.ajax({
				type : 'post',
				url : 'privilegemgmt/users.action',
				cache : false,
				dataType : 'json',
				data : {
					method : "getdepartUser",
					departId : deptid,
					page : -1,
					rows : -1
				},
				success : function(data) {
					var ysValue = $('#liOptionms2side').html();
					var str = "<select name='liOption[]' id='liOption' multiple='multiple' size='10'>";
					//var data = r.rows;
					for (var i = 0; i < data.length; i++) {
						str += "<option value='" + data[i].id + "'>"
								+ data[i].nick + "</option>";
					}
					str += "</select>";
					$("#sel").empty();
					$("#sel").html(str);
					$("#liOption").multiselect2side({
						selectedPosition : 'right',
						moveOptions : false,
						labelsx : '待选区',
						labeldx : '已选区'
					});
					$('#liOptionms2side').html(ysValue);
				},
				error : function(r) {
					alert("内部错误,请与信息管理中心联系,谢谢！");
				}
			});
}

function praseHtml(){
	var firstImg = "";
	var dom=$('<div>').append(getContent());
	firstImg = dom.find("img").attr('src'); 
	return firstImg;
}

function  getChoseList(){
	var list = "";
	$("#liOptionms2side option").each(function() {    
		list += $(this).val()+",";
	});
	return list.substring(0, list.length-1);
}

function checkNotice(){
	if($("#title").val()==''){
		alert("请填写标题");
		return false;
	}
	if(getChoseList() == null ||getChoseList() == '') {
		alert("请选择接收人");
		return false;
	}
	if(getContent()==''){
		alert("请填写内容");
		return false;
	}
	return true;
}
function sendNotice(){
	if(checkNotice()){
		var reviceor = getChoseList();
		$.ajax({
			type : 'post',
			url : 'privilegemgmt/ccNotice.action?reviceor='+reviceor,
			cache : false,
			dataType : 'json',
			data : {
				method:"saveCcNotice",
				content : getContent(),
				firstImg : praseHtml(),
				title : $("#title").val()
			},
			success : function(r) {
				if(r[0].logMsg == "fail"){
					alert("内部错误,请与信息管理中心联系,谢谢！");
				}else{
					alert("发送成功！");
//					conSend(r[0].logMsg);
					conSend(r);
					window.setTimeout("closeWindow()",1200);
					
					
				}
			},
			error : function(r) {
				alert("内部错误,请与信息管理中心联系,谢谢！");
			}
		});
	}
}

function conSend(r){
	var list = getChoseList().split(",");
    var myDate = new Date();
    var mytime=myDate.toLocaleString();  
	for(var i = 0;i < r.length; i++) {
//		var dcontent = "msgtype:7[{:&$#@~^@:}]http://im2.lesso.com:8181/Lsteamtalk/privilegemgmt/ccNotice.action?method=getCcNotice&&iceId="+id+"&&suid="+parseInt(list[i])+"[{:&$#@~^@:}]"+$("#title").val()+"[{:&$#@~^@:}]"+mytime+"[{:&$#@~^@:}]"+sede();
		var data = {
			req_id:parseInt(getUserId()),
			to_id:parseInt(r[i].remarkb),
			msg_type:1,
			msg_content:r[i].remarka
		};
		var json_data = JSON.stringify(data);
	//	alert(json_data);
		$.ajax({
			type: "post",
			url: "http://192.168.4.152:8400/api/sendmsg",
			data: json_data,
			dataType: "json",
			success: function(data) {
				//alert("ok");
			}
	    });
		
	}
}

function   closeWindow()   { 
	  
	  window.open('','_parent',''); 
	  
	  window.close(); 
	  
	  }  

function sede(){
	var ds = getContentTxt();
	if(ds.length>20){
		var iu = ds.substr(0 ,20);
		return iu;
	}else{
		return ds.substr(0 ,6);
	}
}
/**
 * 查询用户
 */
function searchUser(){
	if($("#searchUser").val() == ""){
		return
	}
	$.ajax({
		type : 'post',
		url : 'privilegemgmt/users.action',
		cache : false,
		dataType : 'json',
		data : {
			method : "getSearchtUser",
			searchUser:$("#searchUser").val()
		},
		success : function(data) {
			var ysValue = $('#liOptionms2side').html();
			var str = "<select name='liOption[]' id='liOption' multiple='multiple' size='10'>";
			//var data = r.rows;
			for (var i = 0; i < data.length; i++) {
				str += "<option value='" + data[i].id + "'>"
						+ data[i].nick + "</option>";
			}
			str += "</select>";
			$("#sel").empty();
			$("#sel").html(str);
			$("#liOption").multiselect2side({
				selectedPosition : 'right',
				moveOptions : false,
				labelsx : '待选区',
				labeldx : '已选区'
			});
			$('#liOptionms2side').html(ysValue);
		},
		error : function(r) {
			alert("内部错误,请与信息管理中心联系,谢谢！");
		}
	});
}