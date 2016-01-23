$(function(){
   InitPage();	
   search();
});

function InitPage(){
	checkform();
    var flag;
    $('#t_user').datagrid({
        idField:'id',
        title:'用户列表',
        url:'../privilegemgmt/users.action',	
        fit:true,
		height:550,
		width:700,
	//	fitColumns:true,
		striped:true,
		loadMsg:'loading...',
		rownumbers:true,
		collapsible:true,
        remoteSort:false,
        nowrap:false,
        pagination:true,
//      singleSelect:true,
//      remoteSort:false,
        rowStyler:function(index,record){
//        	if(record.sex==0){
//        		return "background:green";
//        	}
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
		         	field:'id',
		        	title:'ID',
		        	width:50,
		        	align:'center'
		           },{
                	field:'name',
                	title:'登录名',
                	width:130,
                	align:'center',
                	styler:function(value,record){
                		if(value == ''){
                			
                		}
                	}
                   },{
                	   field:'sex',
                	   title:'性别',
                	   width:60,
                	   align:'center',
                	   formatter:function(value,record){
                		   if(value == "1"){
                			   return "男";
                		   }else{
                			   return "女";
                		   }
                	   }
                   },{
                	   field:'nick',
                	   title:'真实名字',
                	   width:150,
                	   align:'center',
                	   formatter:function(value,record){
                		
                			 //  alert(value);
                			   return value;
                	   }
                   },{
                	   field:'type',
                	   title:'用户类型',
                	   width:100,
                	   align:'center',
                	   formatter:function(value,record){
                		   if(value == "1"){
                			   return "联塑工作人员";
                		   }else{
                			   return "客户";
                		   }
                	   }
                   },{
                	   field:'phone',
                	   title:'手机',
                	   width:180,
                	   align:'center'
                   },{
                	   field:'email',
                	   title:'email',
                	   align:'center',
                	   width:180
                   },{
                	   field:'departId',
                	   title:'部门',
                	   width:150,
                	   align:'center',
  	            	  hidden:"true"
                   },{
                	   field:'departname',
                	   title:'部门',
                	   width:150,
                	   align:'center'
                   }
         ]],
         pageSize:20,
         pageList:[5,10,15,20,50],
            toolbar :'#tb'
    });
	
    
    $('#depId').combotree({
		url:'../privilegemgmt/dept.action?method=TreeDept' ,
		width:200 ,
		checkbox:false ,
		multiple:false
});
    $('#chadepId').combotree({
		url:'../privilegemgmt/dept.action?method=TreeDept' ,
		width:220 ,
		checkbox:false ,
		multiple:false
});
    
    $('#depAdd').click(function(){
    	flag="save";
    	append();
    });
     
    
    $("#btn1").click(function(){
  //  	alert(flag);
    	handlebtn1(flag);

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
		var  node  = $('#t_user').datagrid('getSelections');
		if(node == null || node == 'underdefined'|| node.length ==0)
		{
			  $.messager.alert('提示信息','请选择一个用户!');
			  return false;
		}else if(node.length >1){
			$.messager.alert('提示信息','您已经选择了多个用户,请检查其他分页,并且选择一个用户!');
			 return false;
		}else{
			$('#tdpwd').hide();
			$('#myform').find('input[name=id]').val(node[0].id);
			$('#myform').find('input[name=empname]').val(node[0].name);
			$('#myform').find('input[name=empname]').attr("readonly",true);
			$('#myform').find("select[name=empsex]").val(node[0].sex);
			$('#myform').find('input[name=nicktxt]').val(node[0].nick);
			$('#myform').find('input[name=phonetxt]').val(node[0].phone);
			$('#myform').find("input[name=emailtxt]").val(node[0].email);
	      $('#depId').combotree('setValue', node[0].departId); 
	      $('#depId').combotree('setText', node[0].departname); 
	      $('#myform').find("select[name=leixing]").val(node[0].type);
			//3打开窗口
			$('#mydialog').dialog('open');
		}
		
   });
   
   //删除功能
   $('#depRemove').click(function(){
	   remove();
   });
   
   
}

function search(){
	$("#searchbtn").click(function(){
		var df = $("#chaname").val();
		var df1 = $("#chalei").val();
		var df2 = $('#chadepId').combotree('getValue');
		var queryParams = $('#t_user').datagrid('options').queryParams;
	    queryParams["name"]=df;
	    queryParams["type"]=df1;
	    queryParams["departId"]=df2;
	    queryParams["method"]="search";
	    $('#t_user').datagrid('options').queryParams = queryParams;
	    $('#t_user').datagrid('load');
	});
}



function append(){
//	flag="save";
	$('#tdpwd').show();
	//1清空表单数据
	$('#myform').form('clear');
	//2
	$('#myform').find('input[name=empname]').attr("readonly",false);
	$('#depId').combotree({
		url:'../privilegemgmt/dept.action?method=TreeDept' ,
		width:200 ,
		checkbox:false ,
		multiple:false
});
	$('#empsex').val(1);
	$('#leixing').val(1);
	//3打开窗口
	$('#mydialog').dialog('open');
	
}


//form表单验证
function checkform(){
	//日期组件
	$('#depId').combobox({
		required:true , 
		missingMessage:'部门必选!' ,
		editable:false
	});
}


//建立一個可存取到該file的url
function getObjectURL(file) {
    var url = null ;
    if (window.createObjectURL!=undefined) { // basic
        url = window.createObjectURL(file) ;
    } else if (window.URL!=undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file) ;
    } else if (window.webkitURL!=undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file) ;
    }
    return url ;
}

function  changeImagesSize(){     
    var imageArray = document.getElementById("dsf").getElementsByTagName("img");    
    for(var i=0;i<imageArray.length;i++)    
    {   
       if (imageArray[i].width>80) {   
          var srcWidth  = imageArray[i].width;   
          var srcHeight = imageArray[i].height;   
          imageArray[i].style.width="80px";    
          imageArray[i].style.height=(srcHeight*imageArray[i].width)/srcWidth;    
       }     
        
    }   
}  


function hjg(value){
	alert(123);
    var objUrl = getObjectURL(value.files[0]) ;
    console.log("objUrl = "+objUrl) ;
    if (objUrl) {
        $("#img0").attr("src", objUrl) ;
    }
    changeImagesSize();
}

//js方法：序列化表单 			
function serializeForm(form){
	var obj = {};
	$.each(form.serializeArray(),function(index){
		if(obj[this['name']]){
			obj[this['name']] = obj[this['name']] + ','+this['value'];
		} else {
			obj[this['name']] =this['value'];
		}
	});
	return obj;
}



function ajaxFileUpload()  
{  
  
//$("#loading")  
//    .ajaxStart(function(){  
//        $(this).show();  
//    })//开始上传文件时显示一个图片  
//    .ajaxComplete(function(){  
//        $(this).hide();  
//    });//文件上传完成将图片隐藏起来  
      
   $.ajaxFileUpload({  
             url:"demo/test.action",             //需要链接到服务器地址  
             secureuri:false,  
             fileElementId:'uploadFile',                         //文件选择框的id属性  
             dataType: 'json',                                     //服务器返回的格式，可以是json  
             success: function (data, status)             //相当于java中try语句块的用法  
             {     
             //alert(data);       //data是从服务器返回来的值     
                 $('#result').html('上传图片成功!请复制图片地址<br/>'+data.src);  

             },  
             error: function (data, status, e)             //相当于java中catch语句块的用法  
             {  
                 $('#result').html('上传图片失败');  
             }  
           }  
         );  
}  


//删除方法
function remove(){
	var  node  = $('#t_user').datagrid('getSelections');
	if(node == null || node == 'underdefined'|| node.length >1)
	{
		  $.messager.alert('提示信息','请选择一个用户!');
		  return false;
	}
	
	$.messager.confirm("提示信息","确认删除?",function(r){
			if(r){
				// 1前台删除

	//			$('#t_user').datagrid('remove',node.id);
				
				$.ajax({
					type:'post',
					url:'../privilegemgmt/users.action?method=delete',
					cache:false,
					dateType:'json',
					data:{
						id:node[0].id
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
								 $('#t_user').datagrid('reload'); 
							}else{
								alert(da.exceptionMag);
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


function handlebtn1(type){
	if(checktion()){
		var requestobj ={
				name:$('#myform').find('input[name=empname]').val(),
				sex:$('#myform').find("select[name=empsex]").val(),
				nick:$('#myform').find('input[name=nicktxt]').val(),
				phone:$('#myform').find('input[name=phonetxt]').val(),
				email:$('#myform').find("input[name=emailtxt]").val(),
		        departId:$('#depId').combotree('getValue'),
		        password:$('#myform').find("input[name=password]").val(),
		        type:$('#myform').find("select[name=leixing]").val(),
		        id:$('#myform').find('input[name=id]').val()
		}
		$.ajax({
			type:'post',
			url:'../privilegemgmt/users.action?method='+type,
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
			  
			  $('#t_user').datagrid('reload');
			  if(!da.hasError){
				$('#mydialog').dialog('close');
			  }
			},
			error:function(r){
				alert("内部错误,请与信息管理中心联系,谢谢！");
			}
		});
	}else{
		return false;
	}
}


function checktion(){
	 var iscan = true;
	  var departid = $('#depId').combotree('getValue');
	  if(departid == undefined || departid=="" || departid ==null){
		  $('#errordepId').text("请选择部门");
		  iscan = false;
	  }
	  var nicktxt = $('#nicktxt').val();
	  if(nicktxt == undefined || nicktxt=="" || nicktxt ==null){
		  $('#errornicktxt').text("请填写真实姓名");
		  iscan = false;
	  }
	  var empname = $('#empname').val();
	  if(empname == undefined || empname=="" || empname ==null){
		  $('#errorempname').text("请选择登录名");
		  iscan = false;
	  }
	  var password = $('#password').val();
	  if(empname == undefined || empname=="" || empname ==null){
		  $('#errorpassword').text("请填写密码");
		  iscan = false;
	  }
		var phonetxt = $.trim($("#phonetxt").val());
		var telephoneExp = /^[1][3,5,8][0-9]{9}$/;
		if(phonetxt !=null && phonetxt != undefined && phonetxt !=""){
			if (telephoneExp.test(phonetxt) == false) {
		        $("#errorphonetxt").html("请输入正确手机号码").show();
		        iscan = false;
		    }
		}
		var emailtxt = $.trim($("#emailtxt").val());
		if(emailtxt !=null && emailtxt != undefined && emailtxt !=""){
		    var reg = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		    if (reg.test(emailtxt) == false) {
		        $("#erroremailtxt").html("请输入正确电子邮箱").show();
		        iscan = false;
		    }
		}
	  
	  return iscan;
}