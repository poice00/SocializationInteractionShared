<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
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
	<div>
		<s:iterator value="resTagMap" status="status">
		 <s:a action="resource_singalresTagUI?resTg=%{key}">${key }(${value })</s:a><br />
		——————————————————————————————————————————————————————————————<br />
	    </s:iterator>
	    <s:a action="resource_singalresTagUI?resTg=其他">其他(${resTvalue })</s:a>	<br />
	    ——————————————————————————————————————————————————————————————<br />
	</div>
</center>
<!-- 页脚 -->
<%@ include file="/WEB-INF/jsp/public/footer.jsp" %>
</body>
</html>