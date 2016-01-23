$(function(){
	Initpage();
    $('#chadepId').combotree({
		url:'../privilegemgmt/dept.action?method=TreeDept' ,
		width:220 ,
		checkbox:false ,
		multiple:false
});
});

function Initpage(){
	var flag;
	$("#t_dis").datagrid({
		   title:'权限部门',
		   url:'../control/DepartRightControlAction?method=init',
		   fit:true,
		   height:'550',
		   striped:true,
		   singleSelect: true,
		   loadMsg:'loading',
		   rownumbers:true,
		   collapsible:true,
		   remoteSort:false,
	       nowrap:false,
	       pagination:true,
		   rowStyler:function(index,record){
			   
		   },
		   frozenColumns:[[      //冻结列特性，不要与fitColumns特性一起使用
		                         {
		                             field:'ck',
		                             width:50,
		                             checkbox:true
		                         }
		                      ]],
		   columns:[[
		             {
		            	 field:'departId',
		            	 title:'部门Id',
		            	 width:80,
		            	 align:'center'
		             },{
		            	 field:'departId_Text',
		            	 title:'部门名称',
		            	 width:200,
		            	 align:'center'
		             },{
		            	 field:'ztype',
		            	 title:'客户类型',
		            	 width:100,
		            	 align:'center'
		             },{
		            	 field:'ztype_text',
		            	 title:'客户类型',
		            	 width:150,
		            	 align:'center'
		             }
		             
		          ]],
		    pageSize:20,
		    pageList:[5,10,15,20,50],
		    toolbar :'#tb'
	});
	
	
	$('#depAdd').click(function(){
		flag="save";
		//1清空表单数据
		$('#myform').form('clear');
		$('#deptype').val(2);
		$('#errordep').text("");
		 $('#errordepname').text("");
		 $('#errordeptype').text("");
		//3打开窗口
		$('#mydialog').dialog('open');
	});
	
	
	$('#btn1').click(function(){
	//  	alert(flag);
    	handlebtn1(flag);
	//	$('#mydialog').dialog('close');
	});
	
    $("#btn2").click(function(){
    	$('#mydialog').dialog('close');
    });
    
    
    
    //修改功能
    $('#depEdit').click(function(){
 	   flag="update";
 	 //1清空表单数据
 		$('#myform').form('clear');
 		//2填充表单回显数据
 		var  node  = $('#t_dis').datagrid('getSelections');
 		if(node == null || node == 'underdefined'|| node.length ==0)
 		{
 			  $.messager.alert('提示信息','请选择一个用户!');
 			  return false;
 		}else if(node.length >1){
 			$.messager.alert('提示信息','您已经选择了多个用户,请检查其他分页,并且选择一个用户!');
 			 return false;
 		}else{
 	      $('#chadepId').combotree('setValue', node[0].departId); 
 	      $('#depname').val(node[0].departId_Text); 
 	      $('#myform').find("select[name=deptype]").val(node[0].ztype);
		  //3打开窗口
		  $('#mydialog').dialog('open');
 		}
 		
    });
    
    //删除功能
    $('#depRemove').click(function(){
 	   remove();
    });
    
}



function handlebtn1(flag){
	if(checktion()){
		var type =0;
		if(flag == "save"){
			type =1;
		}else if (flag == "update"){
			type =2;
		}
		
		if(type >0){
			var requestobj ={
					depname:$('#myform').find("input[name=depname]").val(),
			        departId:$('#chadepId').combotree('getValue'),
			        ztype:$('#myform').find("select[name=deptype]").val(),
			        type:type,
			        method:'saveorupdate'
			}
			$.ajax({
				type:'post',
				url:'../control/DepartRightControlAction',
				cache:false , 
				dataType:'json',
				data:requestobj,
				success:function(r){
					  if(r[0] ==null){
						  alert("内部错误,请与信息管理中心联系,谢谢！");
						}else{
							var da = r[0];
							if(!da.hasError){
								$.messager.show({
									title:'提示信息',
									msg:'操作成功!'
								});
							}else{
								alert("操作失败,错误代码:"+da.exceptionMag);
							}
						}
				  
				  $('#t_dis').datagrid('reload');
				  if(!da.hasError){
					$('#mydialog').dialog('close');
				  }
				},
				error:function(r){
					alert("内部错误,请与信息管理中心联系,谢谢！");
				}
			});
		}
	}
}

function checktion(){
  var iscan = true;
  var departid = $('#chadepId').combotree('getValue');
  if(departid == undefined || departid=="" || departid ==null){
	  $('#errordep').text("请选择部门");
	  iscan = false;
  }
  var departname = $('#depname').val();
  if(departname == undefined || departname=="" || departname ==null){
	  $('#errordepname').text("请填写部门名称");
	  iscan = false;
  }
  var khtype = $('#deptype').val();
  if(khtype == undefined || khtype=="" || khtype ==null){
	  $('#errordeptype').text("请选择客户类型");
	  iscan = false;
  }
  
  return iscan;
}

function remove(){
	var  node  = $('#t_dis').datagrid('getSelections');
	if(node == null || node == 'underdefined'|| node.length >1)
	{
		  $.messager.alert('提示信息','请选择一个用户!');
		  return false;
	}
	
	$.messager.confirm("提示信息","请注意,操作会导致该部门下的所有员工对外客户的权限全部删除！确认删除?",function(r){
			if(r){
				$.ajax({
					type:'post',
					url:'../control/DepartRightControlAction?method=delete',
					cache:false,
					dateType:'json',
					data:{
						departId:node[0].departId,
						ztype : node[0].ztype
					},
					success:function(r){
						var d = JSON.parse(r);
						if(d[0]==null){
							alert("内部错误,请与信息管理中心联系,谢谢！");
						}else{
							var da = d[0];
							if(!da.hasError){
								
								$.messager.show({
									title:'提示信息',
									msg:'删除成功!'
								});
							}else{
								alert(da.exceptionMag);
							}
							$('#t_dis').datagrid('reload');
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