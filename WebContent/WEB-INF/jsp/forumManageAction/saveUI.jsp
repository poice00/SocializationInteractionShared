<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>版块添加</title>
</head>
<%@ include file="/WEB-INF/jsp/public/nav.jsp" %>
<body style="background: url('img/back.jpg')">
 <div class="panel panel-default" style="background: url('img/back.jpg')">
	<div class="panel-heading" style="background: url('img/back.jpg')">
		<ol class="breadcrumb" style="background: url('img/back.jpg')">
		  <li><s:a action="user_list">系统管理</s:a></li>
		  <li><s:a action="forumManage_list">论坛管理</s:a></li>
		  <li>版块增改</li>
		</ol>
	</div>
	
 <div class="container">
 <div class="panel-body" align="center">
	<s:form action="forumManage_%{id==null?'add':'edit'}">
	<s:hidden name="id"></s:hidden>
	版块名称:<s:textfield name="name"></s:textfield><br>
	版块說明:<s:textarea name="description"></s:textarea><br>
	<button type="submit" class="btn btn-default">保存</button>
	 <a href="javascript:history.go(-1);"><button type="button" class="btn btn-default">返回</button></a>
	</s:form>
	<%@ include file="/WEB-INF/jsp/public/commons_foot.jspf"%>	
</div>
</div>
</div>

</body>
</html>