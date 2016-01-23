<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>ueditor测试、展示</title>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<script type="text/javascript" src="<%=path %>/ud/editor_config.js"></script>
		<script type="text/javascript" src="<%=path %>/ud/editor_all.js"></script>
		<script type="text/javascript" src="<%=path %>/ud/uparse.js"></script>
		<script type="text/javascript" src="<%=path %>/ud/_examples/editor_api.js"></script>
		<script type="text/javascript" src="<%=path %>/js/EditorUtil.js"></script>
		<style type="text/css">
			.clear {
				clear: both;
			}
		</style>
	</head>
	<body>
		<div>
			<script id="editor" type="text/plain">
				${editnews.content};
			</script>
		</div>
		<div class="clear"></div>

	
	
		<div id="btns">
    <div>
        <button onclick="getAllHtml()">获得整个html的内容</button>
        <button onclick="getContent()">获得内容</button>
        <button onclick="setContent()">写入内容</button>
        <button onclick="setContent(true)">追加内容</button>
        <button onclick="getContentTxt()">获得纯文本</button>
        <button onclick="getPlainTxt()">获得带格式的纯文本</button>
        <button onclick="hasContent()">判断是否有内容</button>
        <button onclick="setFocus()">使编辑器获得焦点</button>
        <button onmousedown="isFocus(event)">编辑器是否获得焦点</button>
        <button onmousedown="setblur(event)" >编辑器失去焦点</button>

    </div>
    <div>
        <button onclick="getText()">获得当前选中的文本</button>
        <button onclick="insertHtml()">插入给定的内容</button>
        <button id="enable" onclick="setEnabled()">可以编辑</button>
        <button onclick="setDisabled()">不可编辑</button>
        <button onclick=" UE.getEditor('editor').setHide()">隐藏编辑器</button>
        <button onclick=" UE.getEditor('editor').setShow()">显示编辑器</button>
        <button onclick=" UE.getEditor('editor').setHeight(300)">设置高度为300默认关闭了自动长高</button>
    </div>

    <div>
        <button onclick="getLocalData()" >获取草稿箱内容</button>
        <button onclick="clearLocalData()" >清空草稿箱</button>
    </div>

</div>
		</body>
	<script type="text/javascript">
   //实例化编辑器
    var ue = new UE.ui.Editor();
   	ue.render("editor");
</script>
</html>
