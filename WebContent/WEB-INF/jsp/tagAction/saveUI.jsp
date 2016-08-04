<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:form action="tag_%{id==null?'add':'edit'}">
	<s:hidden name="id"></s:hidden>
	标签名称:<s:textfield name="name"></s:textfield><br>
	标签說明:<s:textarea name="description"></s:textarea><br>
	<s:submit value="保存"></s:submit>
	 <a href="javascript:history.go(-1);">返回</a>
	</s:form>
</body>
</html>