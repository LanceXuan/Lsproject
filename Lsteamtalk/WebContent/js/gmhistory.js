$(function(){
	InitPage();
	search();
});


function InitPage(){
	   $('#t_gm').datagrid({
		   idField:'id',
		   title:'群组聊天历史记录',
		   url:'../privilegemgmt/gm.action',
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
		            	 field:'groupId',
		            	 title:'群组id',
		            	 width:100,
		            	 align:'center'
		             },{
		            	 field:'userId',
		            	 title:'发送用户',
		            	 width:100,
		            	 align:'center',
		            	 hidden:true
		             },{
		            	 field:'nick',
		            	 title:'发送用户',
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
		    toolbar :'#tb'
		   
	   });
}


//js时间戳处理
function getLocalTime(nS) {     
    return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");      
 }  


function search(){
	$("#searchbtn").click(function(){
		var gid = $("#groupId").combobox('getValue');
		var begindate = $("#begintime").datebox('getValue');
		var enddate = $("#endtime").datebox('getValue');
		if(gid !=null &&  gid != undefined && gid !=""){
		var queryParams = $('#t_gm').datagrid('options').queryParams;
	    queryParams["groupId"]=gid;
	    queryParams["begindate"]=begindate;
	    queryParams["enddate"]=enddate;
	    queryParams["method"]="search";
	    $('#t_gm').datagrid('options').queryParams = queryParams;
	    $('#t_gm').datagrid('load');
		}else{
			alert("请选择群组");
		}
	});
}



