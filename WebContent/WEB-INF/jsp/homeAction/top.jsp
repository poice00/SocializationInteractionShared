<%@ page language="java" pageEncoding="UTF-8"%>
  <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<body style="background: #B4CDE6" magin="0px">
<div style="margin: 0px;padding: 0px">
	<a href="http://www.baidu.com/" target="mid" >首页</a>
	<a href="${pageContext.request.contextPath}/forum_list.action" target="mid" >论坛</a>
	<s:a action="myresource_showupload" target="mid" >资源库</s:a>
	<s:a action="myresource_uplist" target="mid" >我的资源库</s:a>
	<a href="http://www.sohu.com/" target="mid" >个人空间</a>
	<a href="http://www.hao123.com/nba" target="mid" >系统管理</a>
	<a href="${pageContext.request.contextPath}/tag_list.action" target="mid" >标签管理</a>
	<a href="${pageContext.request.contextPath}/forumManage_list.action" target="mid" >论坛管理</a>
	你好：${user.name }
	<a href="user_logout.action" target="_parent">注销</a>
	<a href="javascript: window.parent.mid.location.reload(true);">刷新</a>
</div>
</body>
</html>