<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<s:iterator value="#talkList" status="status">
			<tr>+++++++++++++++
					<td>${id}&nbsp;</td>
					<td>${content}&nbsp;</td>
					<td><s:a action="talk_delete?id=%{id}" 
							 onClick="return window.confirm('您确定删除吗?')">删除</s:a>
					</td>
			</tr>
</s:iterator>
</body>
</html>