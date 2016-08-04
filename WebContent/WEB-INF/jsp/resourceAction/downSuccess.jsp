<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
<jsp:include page="/WEB-INF/jsp/public/style.jsp" flush="true"></jsp:include>
</head>
<body>
<!-- 导航栏 -->
<%@ include file="/WEB-INF/jsp/public/nav.jsp" %>
<center>
<div class="nav-bar">
        	<s:a action="resource_homePage" >首页</s:a>
        	<s:a action="resource_resTypeUI" >资源分类</s:a>
			<s:a action="resource_resRankUI">排行榜</s:a>
			<s:a action="resource_resHelpUI" >帮助</s:a>
			<s:a action="resource_highsearchUI">高级搜索</s:a>
			<s:a action="myresource_uplist">我的资源</s:a>
			<s:a action="myresource_showupload">我要上传</s:a>	
</div>
恭喜你，下载成功，继续去<s:a action="resource_homePage">下载</s:a>吧~
</center>
<!-- 页脚 -->
<%@ include file="/WEB-INF/jsp/public/footer.jsp" %>
</body>
</html>