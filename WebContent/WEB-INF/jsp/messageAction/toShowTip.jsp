<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<%@ include file="/WEB-INF/jsp/public/commons_head.jspf"%>	

	Response.Write("<script>alert('操作成功!');window.location.href="javascript:history.go(-1)"</script>");

	<!-- <a href="javascript:history.go(-1);location.reload()">返回上一页重载页面，本地刷新</a>  -->
	
	<%@ include file="/WEB-INF/jsp/public/commons_foot.jspf"%>
</body>
</html>