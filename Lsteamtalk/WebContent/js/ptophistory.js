$(function(){
	InitPage();
	search();
    $('#chadepId').combotree({
		url:'../privilegemgmt/dept.action?method=TreeDept' ,
		width:220 ,
		checkbox:false ,
		multiple:false,
		onSelect:function(node){
			var url = '../privilegemgmt/users.action?method=getdepartUser&&page=-1&&rows=-1&&departId='+node.id;    
			$('#recviceId').combobox('reload', url);
	     }
});
    $('#chbdepId').combotree({
		url:'../privilegemgmt/dept.action?method=TreeDept' ,
		width:220 ,
		checkbox:false ,
		multiple:false,
		onSelect:function(node){
			var url = '../privilegemgmt/users.action?method=getdepartUser&&page=-1&&rows=-1&&departId='+node.id;    
			$('#sendId').combobox('reload', url);
	     }
});
});


function InitPage(){
	   $('#t_ptop').datagrid({
		   idField:'id',
		   title:'个人聊天历史记录',
		   url:'../privilegemgmt/imme.action',
		   fit:true,
		   height:'550',
		   striped:true,
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
		            	 field:'id',
		            	 title:'Id',
		            	 width:50,
		            	 align:'center'
		             },{
		            	 field:'relateId',
		            	 title:'关系ID',
		            	 width:100,
		            	 align:'center',
		            	 hidden:true
		             },{
		            	 field:'fromId',
		            	 title:'发送用户',
		            	 width:100,
		            	 align:'center',
		            	 hidden:true
		             },{
		            	 field:'frompeople',
		            	 title:'发送用户',
		            	 width:100,
		            	 align:'center'
		             },{
		            	 field:'toId',
		            	 title:'接收用户',
		            	 width:100,
		            	 align:'center',
		            	 hidden:true
		             },{
		            	 field:'topeople',
		            	 title:'接收用户',
		            	 width:100,
		            	 align:'center'
		             },{
		            	 field:'content',
		            	 title:'内容',
		            	 width:500,
		            	 align:'center'
		             },{
		            	 field:'type',
		            	 title:'消息类型',
		            	 width:100,
		            	 align:'center',
		            	 hidden:true
		             },{
		            	 field:'created',
		            	 title:'创建时间',
		            	 width:180,
		            	 align:'center',
		            	 formatter:function(value,record){
		            		 return getLocalTime(value);
		            	 }
		             }
		             
		          ]],
		    pageSize:10,
		    pageList:[5,10,15,20,50],
		    toolbar :'#tb',
		    onLoadSuccess: function(data){
		    	var  da = data.rows;
		    	if(da.length==1 && da[0].hasError){
		    		if(da[0].exceptionMag =="outSessionA"){
		    			location.reload();
		    		}
		    	}
		    },
		    onLoadError:function(data){  
           }
		   
	   });
}


//js时间戳处理
function getLocalTime(nS) {     
    return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");      
 }  



function search(){
	$("#searchbtn").click(function(){
		var fromId = $("#sendId").combobox('getValue');
		var toId = $("#recviceId").combobox('getValue');
		var begindate = $("#begintime").datebox('getValue');
		var enddate = $("#endtime").datebox('getValue');
	//	if(toId !=null &&  toId != undefined && toId !=""){
			var queryParams = $('#t_ptop').datagrid('options').queryParams;
			queryParams["fromId"]=fromId;
		    queryParams["toId"]=toId;
		    queryParams["begindate"]=begindate;
		    queryParams["enddate"]=enddate;
		    queryParams["method"]="search";
		    $('#t_ptop').datagrid('options').queryParams = queryParams;
		    $('#t_ptop').datagrid('load');
//		}else{
//			alert("请选择部门,用户");
//		}
	});
}