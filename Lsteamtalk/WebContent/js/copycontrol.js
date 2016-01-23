$(function(){
	InitBB();
	InitRR();
	confirmbtnclick();
	searchbtnclick();
});

function InitCG(idk){
	
    $('#cg').combogrid({  
        panelWidth: 400,  
        idField: 'id',        //ID字段    
        textField: 'nick',    //显示的字段    
        url: "../privilegemgmt/users.action?method=search&&departId="+idk,  
        fitColumns: true,  
        striped: true,  
        editable: true,  
        pagination: true,  //是否分页
        pageSize:20,
        pageList:[5,10,15,20,50],
        rownumbers: true,           //序号  
        collapsible: false,         //是否可折叠的  
        fit: true,                  //自动大小  
        method: 'post',  
        columns: [[  
                    { field: 'id', title: 'id', width: 80, hidden: true },  
                    { field: 'name', title: '登录名', width: 150 },  
                    { field: 'nick', title: '真实名', width: 150 }  
                ]],  
        onSelect: function () {              //选中处理  
            $('#txtGender').val($('#cg').combogrid('grid').datagrid('getSelected').id);  
            $('#hddkj').val($('#cg').combogrid('grid').datagrid('getSelected').name); 
        }  
    });  
}


function InitBB(){
	$('#ee').combo({
		required:true,
		editable:true
//		multiple:true
		});

		$('#sq').appendTo($('#ee').combo('panel'));
		
		$("#sq input").click(function(){
		var _value="";
		var _text="";
		$("[name=langradio]:input:checked").each(function(){
		_value+=$(this).val();
		_text+=$(this).next("span").text();
		});
		//设置下拉选中值
		$('#ee').combo('setValue', _value).combo('setText', _text).combo('hidePanel');
		InitCG(_value);
		$("#divcg").show();
		});	
}


function InitRR(){
	$('#rr').combo({
		required:true,
		editable:true
//		multiple:true
		});

		$('#rty').appendTo($('#rr').combo('panel'));
		
		$("#rty input").click(function(){
		var _value="";
		var _text="";
		$("[name=langra]:input:checked").each(function(){
		_value+=$(this).val();
		_text+=$(this).next("span").text();
		});
		//设置下拉选中值
		$('#rr').combo('setValue', _value).combo('setText', _text).combo('hidePanel');
		getUserList(_value);
		});	
}


function getUserList(deptid) {
	$.ajax({
				type : 'post',
				url : '../privilegemgmt/users.action',
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
						str += "<option value='" + data[i].name + "'>"
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
					 $("#confirmbtn").show();
				},
				error : function(r) {
					alert("内部错误,请与信息管理中心联系,谢谢！");
				}
			});
}



function  getChoseList(){
	var list = "";
	$("#liOptionms2side option").each(function() {    
		list += $(this).val()+",";
	});
	return list.substring(0, list.length-1);
}

function confirmbtnclick(){
	$("#confirmbtn").click(function(){
		var listvalue = getChoseList();
		var kp = $('#cg').combogrid('grid').datagrid('getSelected').name;
		$.ajax({
			type : 'post',
			url : '../control/Vkorg.action',
			cache : false,
			dataType : 'json',
			data : {
				method : "copycontrol",
				userid:kp,
				listValue:listvalue,
				ztype:2
			},
			success : function(data) {
				if(data[0]==null){
					alert("内部错误,请与信息管理中心联系,谢谢！");
				}else{
					var da = data[0];
					if(!da.hasError){
                         alert(da.logMsg);
					}else{
						alert("操作失败,错误医院是"+da.exceptionMag);
					}
				}
			},
			error : function(r) {
				alert("内部错误,请与信息管理中心联系,谢谢！");
			}
		});
		
	});
}


function searchbtnclick(){
    $('#searchbtn').click(function(){
    	var txtGe = $('#txtGe').val();
    	 //设置查询参数  
        var queryParams = $('#cg').combogrid("grid").datagrid('options').queryParams;  
        queryParams["seaname"]=txtGe;  
        $('#cg').combogrid("grid").datagrid('options').queryParams = queryParams;  
        //重新加载  
        $('#cg').combogrid("grid").datagrid("reload");  

        $('#cg').combogrid("setValue", keyword);  
        //将查询条件存入隐藏域  
        $('#hdKeyword').val(keyword);  
    });
}