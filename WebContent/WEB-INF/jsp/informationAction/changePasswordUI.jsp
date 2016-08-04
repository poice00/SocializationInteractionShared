<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>修改密码</title>
	
</head>
<body>
<!-- 导航栏 -->
		<%@ include file="/WEB-INF/jsp/public/nav.jsp" %>
	<div align="center" style="margin-top: 5%">
		<s:form action="information_changePassword">
			<s:hidden name="id"></s:hidden>
			<table class="table">
			<tr><td>输入旧密码:<s:password name="password"></s:password></td></tr>
			<tr><td>输入新密码:<s:password name="passwordOne"></s:password></td></tr>
			<tr><td>再次输入新密码:<s:password name="newPassword"></s:password></td></tr>
			<tr><td><font color="red"><s:fielderror/></font></td></tr>
			<tr><td><s:submit value="提交"></s:submit></td></tr>
			
			
			</table>
		</s:form>
	</div>
		<!-- 页脚 -->
	
		
	<%@ include file="/WEB-INF/jsp/public/commons_foot.jspf"%>
</html>