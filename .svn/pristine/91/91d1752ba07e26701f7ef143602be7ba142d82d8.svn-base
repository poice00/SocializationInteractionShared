<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>论坛管理</title>
</head>
<%@ include file="/WEB-INF/jsp/public/nav.jsp" %>
<body style="background: url('img/back.jpg')">
 <div class="panel panel-default" style="background: url('img/back.jpg')">
	<div class="panel-heading" style="background: url('img/back.jpg')">
		<ol class="breadcrumb" style="background: url('img/back.jpg')">
		  <li><s:a action="user_list">系统管理</s:a></li>
		  <li>论坛管理</li>
		</ol>
	</div>
	
 <div class="container">
 <div class="panel-body" align="center">
	  
	<table>
	<tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="250px" >版块名称</td>
                <td width="300px">版块说明</td>
                <td>相关操作</td>
     </tr>
     
        <s:iterator value="#forumList" status="status">
			<tr>
				<td>${name}&nbsp;</td>
				<td>${description}&nbsp;</td>
				<td><s:a action="forumManage_delete?id=%{id}" onClick="return window.confirm('您确定删除吗?')"><button class="btn btn-danger">
								<span class="glyphicon glyphicon-remove"></span> 删除
							</button></s:a>
					<s:a action="forumManage_editUI?id=%{id}"><button type="button" class="btn btn-primary">修改</button></s:a>
					
					<!-- 最上面的不能上移 -->
					<s:if test="#status.first">
						<span class="disabled"><button type="button" class="btn btn-default">上移</button></span>
					</s:if>
					<s:else>
						<s:a action="forumManage_moveUp?id=%{id}"><button type="button" class="btn btn-primary">上移</button></s:a>
					</s:else>
					
					<!-- 最下面的不能下移 -->
					<s:if test="#status.last">
						<span class="disabled"><button type="button" class="btn btn-default">下移</button></span>
					</s:if>
					<s:else>
						<s:a action="forumManage_moveDown?id=%{id}"><button type="button" class="btn btn-primary">下移</button></s:a>
					</s:else>
					
				</td>
			</tr>
			</s:iterator>
			<tr>
				<td align="right">
			 	<s:a action="forumManage_addUI"><button class="btn btn-primary">
								<span class="glyphicon glyphicon-plus"></span> 增加板块
							</button></s:a>
							</td>
			 </tr>
	</table>
	</div>
</div>
</div>
<%@ include file="/WEB-INF/jsp/public/commons_foot.jspf"%>
</body>
</html>