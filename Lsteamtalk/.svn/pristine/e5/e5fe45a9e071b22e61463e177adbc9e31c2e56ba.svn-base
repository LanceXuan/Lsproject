$(function(){
	InitPage();
});

function InitPage(){
	$("#t_dis").datagrid({
		idField:'id',
		   title:'发现管理',
		   url:'../privilegemgmt/disca.action',
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
		            	 field:'itemName',
		            	 title:'标题',
		            	 width:100,
		            	 align:'center'
		             },{
		            	 field:'itemUrl',
		            	 title:'url',
		            	 width:100,
		            	 align:'center'
		             },{
		            	 field:'itemPriority',
		            	 title:'优先级',
		            	 width:500,
		            	 align:'center'
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
		    pageSize:20,
		    pageList:[5,10,15,20,50],
		    toolbar :'#tb'
	});
}


//js时间戳处理
function getLocalTime(nS) {     
    return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");      
 }  