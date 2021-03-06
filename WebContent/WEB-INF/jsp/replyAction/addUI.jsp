<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帖子回复</title>
<link rel="stylesheet" href="kindeditor/themes/default/default.css" />
<script charset="utf-8" type="text/javascript" language="javascript"
	src="kindeditor/kindeditor-reply.js"></script>
<script charset="utf-8" type="text/javascript" language="javascript"
	src="kindeditor/lang/zh_CN.js"></script>
<script>
	KindEditor.ready(function(K) {
		K.create('textarea[name="content"]', {
			uploadJson : 'kindeditor/jsp/upload_json.jsp',
			fileManagerJson : 'kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			allowImageUpload : true,
			autoHeightMode : true,
			afterCreate : function() {
				this.loadPlugin('autoheight');
			},
			afterBlur : function() {
				this.sync();
			} //Kindeditor下获取文本框信息
		});
	});
	
</script>

</head>
<%@ include file="/WEB-INF/jsp/public/nav.jsp" %>
<body>
<div class="panel panel-default">
	<div class="panel-heading">

	<ol class="breadcrumb">
	  <li><s:a action="forum_list">论坛</s:a>
	  <li><s:a action="forum_show?id=%{#topic.forum.id}">${ topic.forum.name}</s:a> </li>
	  <li>帖子回复</li>
	</ol>
	</div>
	<div class="container">
	<div class="panel-body" align="center">
	<s:form action="reply_add" >
		<s:hidden name="topicId"></s:hidden>
		<s:hidden name="id"></s:hidden>
		
			
			帖子主题：【${topic.title }】<br>
			标题:<s:textfield name="title" value="回复：%{id==null?#topic.title:#reply.user.name}" cssStyle="width:600px;"/><br>
			内容:<s:textarea name="content"></s:textarea><br>
		
			<button type="submit" class="btn btn-default">回复</button>
		
		
	</s:form>
			<a href="javascript:history.go(-1);"><button class="btn btn-default">返回上一级</button></a>
	</div>
	</div>
	</div>
	<%@ include file="/WEB-INF/jsp/public/commons_foot.jspf"%>
</body>
</html>