<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<h2>欢迎你注册SISP</h2>
<s:form action="user_add">
    <s:textfield name="loginName" label="登录名"></s:textfield>
	<s:textfield name="name" label="昵称"></s:textfield>
	<s:password name="password" label="密码"/>
	<s:select name="education" label="学历" list="{'高中及以下','大学专科','大学本科','研究生','博士生及以上'}"/>
	<s:textfield name="email" label="邮箱"></s:textfield>
	<s:textfield name="telphone" label="电话号码"></s:textfield>
	<s:textfield name="qqhaoma" label="QQ号码"></s:textfield>
	<s:textfield name="borndate" label="出生日期"></s:textfield>
	<s:select name="usTags" label="爱好" list="{'JAVA','c++','C#','C'}"/>
	<s:submit value="提交"></s:submit>
</s:form>
</center>
</body>
</html>