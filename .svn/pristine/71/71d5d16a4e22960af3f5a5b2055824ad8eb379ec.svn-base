<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

</head>
<body style="background: url('img/back.jpg')">
<%@ include file="/WEB-INF/jsp/public/nav.jsp"%>
<div class="panel panel-default" style="background: url('img/back.jpg')">
	<div class="panel-heading" style="background: url('img/back.jpg')">
		<ol class="breadcrumb" style="background: url('img/back.jpg')">
		  <li><s:a action="forum_index">首页</s:a></li>
		  <li>我的发布</li>
		</ol>
	</div>
<div class="container">
 	<!-- 我的发布-->
 	
	  <div class="tab-pane" id="myTopics" style="margin-bottom:200px;">
	  
	  		<table class="table table-striped">
				  <tr align="center">
				  
					  <td>讨论版</td>
					  <td>文章标题</td>	
					  <td>浏览次数</td>
					  <td>发布时间</td>
					  <td>操作</td>	
				  
				  </tr>
			  
				  <s:iterator value="#MyTopicList">
					  <tr align="center">
					  	<td><s:a action="forum_show?id=%{forum.id}">${forum.name }</s:a></td>
						<td><s:a action="topic_show?id=%{id}">${title }</s:a></td>	
						<td>${count }</td>
						<td><s:date name="postTime"/></td>
						<td><s:a action="topic_delete?id=%{id}&forumId=%{forum.id}" onClick="return confirm('确定把本主题删除吗？')"><button class="btn btn-default">删除</button></s:a></td>	
					  </tr>
				  </s:iterator>
				  
			</table>
	  </div>
</div>
	
	<%@ include file="/WEB-INF/jsp/public/commons_foot.jspf"%>
</body>
</html>