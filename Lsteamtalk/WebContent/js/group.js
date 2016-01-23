$(function(){
	InitPage();
	search();
});

function InitPage(){
   $('#t_group').datagrid({
	   idField:'id',
	   title:'群组管理',
	   url:'../privilegemgmt/Group.action',
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
	            	 field:'name',
	            	 title:'名称',
	            	 width:100,
	            	 align:'center'
	             },{
	            	 field:'creator',
	            	 title:'群主id',
	            	 width:100,
	            	 align:'center',
	            	 hidden:"true"
	             },{
	            	 field:'nick',
	            	 title:'群主',
	            	 width:100,
	            	 align:'center'
	             },{
	            	 field:'userCnt',
	            	 title:'人数',
	            	 width:100,
	            	 align:'center'
	             },{
	            	 field:'created',
	            	 title:'创建时间',
	            	 width:180,
	            	 align:'center',
	            	 formatter:function(value,record){
	            		 return getLocalTime(value);
	            	 }
	             },{
	            	 field:'lastchated',
	            	 title:'最后聊天时间',
	            	 width:180,
	            	 align:'center',
	            	 formatter:function(value,record){
	            		 if(value !=0){
	            		 return getLocalTime(value);
	            		 }else{
	            			 return "";
	            		 }
	            	 }
	             }
	             
	          ]],
	    pageSize:10,
	    pageList:[5,10,15,20,50],
	    toolbar :'#tb'
	   
   });
   
   
   $('#depCut').click(function(){
	   var  node  = $('#t_group').datagrid('getSelections');
	   alert(node[0].id);
   });
}

//js时间戳处理
function getLocalTime(nS) {     
    return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");      
 }   



function search(){
	$("#searchbtn").click(function(){
		var df = $("#namesearch").val();
		var queryParams = $('#t_group').datagrid('options').queryParams;
	    queryParams["name"]=df;
	    queryParams["method"]="search";
	    $('#t_group').datagrid('options').queryParams = queryParams;
	    $('#t_group').datagrid('load');
	});
}
