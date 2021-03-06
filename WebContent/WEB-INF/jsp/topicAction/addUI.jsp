<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here发表新主题</title>
<link rel="stylesheet" href="kindeditor/themes/default/default.css" />
<script charset="utf-8" type="text/javascript" language="javascript"
	src="kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" type="text/javascript" language="javascript"
	src="kindeditor/lang/zh_CN.js"></script>
<script>
	KindEditor.ready(function(K) {
		K.create('textarea[name="content"]', {
			uploadJson : 'file_upload.action',
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
<body style="background: url('img/back.jpg')">
<%@ include file="/WEB-INF/jsp/public/nav.jsp" %>
	
 

<div class="panel panel-default" style="background: url('img/back.jpg')">
	<div class="panel-heading" style="background: url('img/back.jpg')">
		<ol class="breadcrumb" style="background: url('img/back.jpg')">
		  <li><s:a action="forum_index">首页</s:a></li>
		  <li><s:a action="forum_list">版块列表</s:a></li>
		  <li><s:a action="forum_show?id=%{#forum.id}">${forum.name }</s:a> </li>
		  <li>发表新主题</li>
		</ol>
	</div>
	<div class="container">
	<div class="panel-body" align="center">
		<s:form action="topic_add">
			
			<s:if test="forumId==null">
			请选择版块：
				<s:select name="forumId" list="forumList" listKey="id" listValue="name" headerKey="" headerValue="===请选择到板块===" >
				</s:select><br>
			</s:if>
			<s:else>
				<s:hidden name="forumId"></s:hidden>
			
			</s:else>
			标题:<s:textfield name="title" cssStyle="width:600px;"></s:textfield><br>
			内容:<s:textarea name="content"></s:textarea><br>
			<button type="submit" class="btn btn-default">发表</button>
		</s:form>
			<a href="javascript:history.go(-1);"><button class="btn btn-default">返回上一级</button></a>
	
	</div>
	</div>
</div>




	
	<%@ include file="/WEB-INF/jsp/public/commons_foot.jspf"%>
</body>
</html>