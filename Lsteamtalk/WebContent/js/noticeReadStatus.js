$(function(){
	InitPage();
});
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null;
	} 

function InitPage(){
	
	var id = getQueryString("id");
	var status = getQueryString("status");
	   $('#t_gm').datagrid({
		   idField:'id',
		   title:'公告阅读状态',
		   url:'../privilegemgmt/NoticesReceivedAction.action?method=getNoticesReceivedList&&id='+id+'&&status='+status,
		   fit:true,
		   height:'auto',
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
		            	 align:'center',
		            	 hidden:true
		             },{
		            	 field:'nick',
		            	 title:'接收人',
		            	 width:150,
		            	 align:'center'
		             },{
		            	 field:'Status',
		            	 title:'阅读状态',
		            	 width:150,
		            	 align:'center',
		            	 formatter:function(value,record){
	                		   if(record.status == "1"){
	                			   return "已读";
	                		   }else{
	                			   return "未读";
	                		   }
	                	 }
		             },{
		            	 field:'Receivetime',
		            	 title:'阅读时间',
		            	 width:200,
		            	 align:'center',
		            	 formatter:function(value,record){
                            // alert(record.receivetime);
                             return record.receivetime;
	                	 }
		             }
		             
		          ]],
		    pageSize:20,
		    pageList:[5,10,15,20,50]
		  //  toolbar :'#tb'
		   
	   });
}
