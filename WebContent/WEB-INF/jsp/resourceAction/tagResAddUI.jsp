<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加资源标签</title>
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
	<s:form action="tagRes_add" method="post">
		<div class="left">
			<div class="bor_box_1 upload_left">
				<table border="0" cellspacing="0" cellpadding="0" class="upload">
				<tr>
				<th>资源名称：</th>
				<td><s:textfield name="name"  /></td>
				</tr>
				<tr>
				<th valign="top">资源描述：</th>
				<td><s:textarea name="description"></s:textarea></td>
				</tr>
				<tr>
				<td><s:submit value="添加"></s:submit> <s:reset value="重置"></s:reset></td>
				</tr>
				</table>
			</div>
		</div>
	</s:form>
</center>
<!-- 页脚 -->
<%@ include file="/WEB-INF/jsp/public/footer.jsp" %>
</body>
</html>