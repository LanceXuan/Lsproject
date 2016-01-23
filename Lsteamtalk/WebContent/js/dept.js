var flag ; //判断走的是保存还是修改方法
$(function(){
	Inittreegrid();
	
    
    $('#myform').find('input[name=name]').keyup(function(){
    	$("#errorAdd").text("");
    });
    
    $('#myform').find('input[name=priority]').keyup(function(){
    	$("#errorparicpl").text("");
    });
    
    
    $('#refreshdep').click(function(){
    	$('#tree_org').treegrid('reload');	
    });
});

function Inittreegrid(){
	$('#tree_org').treegrid({
		title:'公司部门架构', 
		iconCls:'icon-ok',
		width:1200,
		height:650,
		nowrap: false,
		rownumbers: true,
		collapsible:true,
		url:'../privilegemgmt/dept.action?method=getdep',			
		idField:'id',				//数据表格要有主键	
		treeField:'departName',			//treegrid 树形结构主键 text
		fitColumns:true ,
		toolbar :'#tb', 
		columns:[[
            {   field:'id',
            	title:'ID',
            	width:80
            },{
            	field:'departName',
            	title:'机构名称',
            	width:250,
            	formatter:function(val,rec){
				//	return '<a href="http://120.24.162.217/cmp/'+rec.id+'" style="color:red;">('+val+')</a>';
            	return val;
					} 
            } ,{
            	field:'priority',
            	title:'部门优先级',
            	width:100
            } ,{
            	field:'parentid',
            	title:'父部门',
            	width:100,
            	hidden:true
            },{
            	field:'parentname',
            	title:'父部门',
            	width:100
            },{
            	field:'leadername',
            	title:'部门负责人',
            	width:120,
		    	formatter:function(value,record){
		   		   if(value == "" || value == undefined || value == null){
		   			   return "暂无负责人";
		   		   }else{
		   			   return value;
		   		   }
		   	   }
            },{
            	field:'leader',
            	title:'leader',
            	width:100,
            	hidden:true
            }
		]]
//	,
//		onContextMenu: function(e,row){
//			e.preventDefault();					//屏蔽浏览器的菜单
//			$(this).treegrid('unselectAll');	//清除所有选中项
//			$(this).treegrid('select', row.id);	//选中状态 
//			$('#mm').menu('show', {
//				left: e.pageX,
//				top: e.pageY
//			});
//		}
});

$('#btn1').click(function(){
	var checkname = ValidationName();
	if(checkname == false){
		return false;
	}
		if(flag == 'add'){
					//保存方法 
					//1 前台保存    注意: 没有保存id
					var node = $('#tree_org').treegrid('getSelected');
					var idu =0;
					if(node != null && node != undefined)
					{
						idu = node.id;
					}
					//2 后台保存 
					$.ajax({
						type:'post',
						url:'../privilegemgmt/dept.action?method=save',
						cache:false , 
						dataType:'json',
						data:{
								parentId:idu,
								name:$('#myform').find('input[name=name]').val(),
								priority:$('#myform').find('input[name=priority]').val(),
						} ,
						success:function(r){
							//刷新节点 : 刷新当前选中节点
							$('#tree_org').treegrid('reload');	
								$.messager.show({
									title:'提示信息' , 
									msg:'操作成功!'
								});
						}
					});
					//3关闭窗口
					$('#div1').dialog('close');
		} else {
//			var pids = 0;
//			if($('#chadepId').combotree('getValue') >0){
//				pids=$('#chadepId').combotree('getValue');
//			}else{
//				pids=$('#myform').find('input[name=pid]').val();
//			}
			
					$.ajax({
						type:'post',
						url:'../privilegemgmt/dept.action?method=updatedep',
						cache:false , 
						dataType:'json',
						data:{
								id:$('#myform').find('input[name=id]').val(),
								name:$('#myform').find('input[name=name]').val(),
								priority:$('#myform').find('input[name=priority]').val(),
								parentId:$('#chadepId').combotree('getValue')
						} ,
						success:function(data){
							//刷新节点  :如果当前选中的节点是叶子节点的话,刷新该节点的父亲 ,如果不是叶子节点,刷新当前选中节点即可
							var node = $('#tree_org').treegrid('getSelected');
							var parent =	$('#tree_org').treegrid('getParent' , node.id);
							$('#tree_org').treegrid('reload');
							$.messager.show({
								title:'提示信息',
								msg:'操作成功!'
							});
						},
						error:function(XMLResponse){
							alert('12345');
							
						}
					});
					//3关闭窗口
					$('#div1').dialog('close');
		}

			
			
});

//关闭窗口
$('#btn2').click(function(){
		$('#div1').dialog('close');
});

$('#depAdd').click(function(){
	append();
});
	
$('#depEdit').click(function(){
	update();
});

$('#depRemove').click(function(){
	remove();
});

$('#depleaderset').click(function(){
	//1 init the select
	var  node  = $('#tree_org').treegrid('getSelected');
	if(node == null || node == undefined)
	{
		  $.messager.alert('提示信息','请选择一个部门!');
		  return false;
	}
	
	$('#editleader').find('input[name=leaderid]').val(node.id)
	$.ajax({
		type:'post',
		url:'../privilegemgmt/users.action?method=getdepart',
		cache:false , 
		dataType:'json',
		data:{
				departid:node.id
		} ,
		success:function(data){
			$("#bmfzr").empty();
			$("#bmfzr").prepend("<option value='0'>请选择</option>");   
			for(var i = 0;i < data.length; i++) {
			    $("#bmfzr").append("<option value='"+data[i].id+"'>"+data[i].nick+"</option>"); 
			}
			//open dialog
			$('#div2').dialog('open');
		},
		error:function(XMLResponse){
			alert("内部错误,请与信息管理中心联系,谢谢！");
			
		}
	});
});

$('#btn3').click(function(){
	var lerd = $('#editleader').find('input[name=leaderid]').val();
	$.ajax({
		type:'post',
		url:'../privilegemgmt/dept.action?method=updateleader',
		cache:false , 
		dataType:'json',
		data:{
				departid:$('#editleader').find('input[name=leaderid]').val(),
				leader  :$('#bmfzr').val()
		} ,
		success:function(r){
			  if(r[0] ==null){
				  alert("内部错误,请与信息管理中心联系,谢谢！");
				}else{
					var da = r[0];
					if(!da.hasError){
						$('#tree_org').treegrid('reload');
						$.messager.show({
							title:'提示信息',
							msg:'操作成功!'
						});
					}else{
						alert("内部错误,请与信息管理中心联系,谢谢！");
					}
				}
			  $('#div2').dialog('close');
			  
		},
		error:function(XMLResponse){
			alert("内部错误,请与信息管理中心联系,谢谢！");
			
		}
	});
});

//关闭窗口
$('#btn4').click(function(){
		$('#div2').dialog('close');
});


}


function append(){
	var  node  = $('#tree_org').treegrid('getSelected');
	$('#sjbmtr').hide();
			flag='add';
			//1清空表单数据
			$('#myform').form('clear');
			$("#errorAdd").text("");
			$('#errorparicpl').text("");
			//2打开窗口
			$('#div1').dialog('open');
}

function update(){
			flag='edit';
			$("#errorAdd").text("");
			$('#errorparicpl').text("");
			//1清空表单数据
			$('#myform').form('clear');
			//2填充表单回显数据
			var  node  = $('#tree_org').treegrid('getSelected');
			if(node == null || node == undefined)
			{
				  $.messager.alert('提示信息','请选择一个部门!');
				  return false;
			}
			$('#myform').find('input[name=id]').val(node.id);
			$('#myform').find('input[name=pid]').val(node.parentid);
			$('#myform').find('input[name=name]').val(node.departName);
			$('#myform').find('input[name=priority]').val(node.priority);
			$('#sjbmtr').show();
		    $('#chadepId').combotree({
				url:'../privilegemgmt/dept.action?method=TreeDept' ,
				width:220 ,
				checkbox:false ,
				multiple:false
		   });
		    if(node.parentid != 0){
			    $('#chadepId').combotree('setValue', node.parentid); 
			    $('#chadepId').combotree('setText', node.parentname); 
		    }else{
//		    	$('#chadepId').combotree('setValue', 0); 
//			    $('#chadepId').combotree('setText', ""); 
		    }
			//3打开窗口
			$('#div1').dialog('open');
//			  $('#chadepId').combotree('setValue', node.parentid); 
//			    $('#chadepId').combotree('setText', node.parentname);
}

function remove(){
	var  node  = $('#tree_org').treegrid('getSelected');
	if(node == null || node == undefined)
	{
		  $.messager.alert('提示信息','请选择一个部门!');
		  return false;
	}
	
	$.messager.confirm("提示信息","确认删除?",function(r){
			if(r){
				$.ajax({
					type:'post',
					url:'../privilegemgmt/dept.action?method=delete',
					cache:false,
					dateType:'json',
					data:{
						id:node.id
					},
					success:function(r){
						var d = JSON.parse(r);
						if(d[0]==null){
							alert("内部错误,请与信息管理中心联系,谢谢！");
						}else{
							var da = d[0];
							if(!da.hasError){
								$('#tree_org').treegrid('reload');
								$.messager.show({
									title:'提示信息',
									msg:'删除成功!'
								});
							}else{
								$.messager.show({
									title:'提示信息',
									msg:da.exceptionMag
								});
							}
						}

					},
					error:function(data){
						$.messager.show({
							title:'提示信息',
							msg:'操作失败，错误信息为:'
						});
					}
				});
			} else {
				return ;
			}
			
	});
}

function ValidationName() {

    var iswork = true;
    var paricpl = $.trim($('#myform').find('input[name=name]').val());
    if (paricpl != '') {
//        var nameExp = /^([\u4e00-\u9fa5]{2,10}|([a-zA-Z]+\s?){2,10})$/;

//        if (nameExp.test(paricpl) == false) {
//        	$("#errorAdd").text("请填写2~10位汉字或字母");
//        	iswork = false;
//        }
    }
    else {
    	$("#errorAdd").text("请填写公司名称");
    	iswork = false;
    }
    
    var yxj = $.trim($('#myform').find('input[name=priority]').val());
    if(yxj !=""){
    	var yxjexp=/^\d+$/;
    	if(yxjexp.test(yxj) == false){
    	   $('#errorparicpl').text("请填写数字");
    	   iswork = false;
    	}
    } else {
        	$("#errorparicpl").text("请填写部门优先级");
        	iswork = false;
        }

    return iswork;
}
