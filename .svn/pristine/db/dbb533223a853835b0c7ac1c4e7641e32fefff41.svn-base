<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>说说回复</title>
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
	  <li><s:a action="talk_list">我的说说</s:a>
	  <li>说说回复</li>
	</ol>
	</div>
	<div class="panel-body">
	<s:form action="talkReply_add">
		<s:hidden name="talkId"></s:hidden>
		<s:hidden name="id"></s:hidden>
		
			
			昵称说说内容：【${talk.content}】<br>
			??昵称:<s:textfield name="content" value="回复：%{id==null?#talk.content:#talkreply.user.name}" cssStyle="width:600px;"/><br>
			内容:<s:textarea name="content"></s:textarea><br>
		
			<button type="submit" class="btn btn-default">回复</button>
		
		
	</s:form>
			<a href="javascript:history.go(-1);"><button class="btn btn-default">返回上一级</button></a>
	</div>
	</div>
	<%@ include file="/WEB-INF/jsp/public/commons_foot.jspf"%>
</body>
</html>