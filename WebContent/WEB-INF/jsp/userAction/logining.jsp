<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<h2>欢迎你登录SISP</h2>
<s:form action="user_login">
    <s:textfield name="loginName" label="登录名"></s:textfield>
	<s:password name="password" label="密码"/>
	<s:submit value="提交"></s:submit>
	</s:form>
	</center>
</body>
</html>