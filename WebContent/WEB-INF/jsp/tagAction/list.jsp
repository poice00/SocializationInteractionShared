<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
            	<td width="250px" >标签名称</td>
                <td width="300px">版块说明</td>
                <td>相关操作</td>
     </tr>
     
        <s:iterator value="#tagList" status="status">
			<tr>
				<td>${name}&nbsp;</td>
				<td>${description}&nbsp;</td>
				<td><s:a action="tag_delete?id=%{id}" onClick="return window.confirm('您确定删除吗?')">删除</s:a>
					<s:a action="tag_editUI?id=%{id}">修改</s:a>
					
				</td>
			</tr>
			</s:iterator>
			<tr>
			 <s:a action="tag_addUI">添加标签</s:a>
			 </tr>
</table>
</body>
</html>