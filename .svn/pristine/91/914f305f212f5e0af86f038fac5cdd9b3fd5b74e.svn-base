<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="kindeditor/themes/default/default.css" />
<script charset="utf-8" type="text/javascript" language="javascript"
	src="kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" type="text/javascript" language="javascript"
	src="kindeditor/lang/zh_CN.js"></script>
</head>
<body style="background: url('img/back.jpg')">
<%@ include file="/WEB-INF/jsp/public/nav.jsp"%>


	<!-- 发信箱 -->
<div class="panel panel-default" style="background: url('img/back.jpg')">
	<div class="panel-heading" style="background: url('img/back.jpg')">
		<ol class="breadcrumb" style="background: url('img/back.jpg')">
		  <li><s:a action="forum_index">首页</s:a></li>
		  <li>发件箱</li>
		</ol>
	</div>
	<div class="container">
	
	<div class="panel-body" align="center">
	  
	  <div class="tab-pane" id="sendmsgs" style="margin-bottom:200px;">
	  
	  <table  class="table table-striped">
						
			<tr>
				<td>发送人</td>
				<td>收件人</td>
				<td>状态</td>
				<td>操作</td>
		   </tr>		
		  <s:iterator value="#user.oneMsgs">
				<tr>
					<td> ${user.name }</td>
					<td> ${friend.name }</td>
						<s:if test="sendStatus==0">
							<td>
									<img  style="width: 30px;height: 30px" alt="草稿" src="${pageContext.request.contextPath}/style/images/write.png">
									<label style="color: red">草稿</label>
							</td>
							<td>
									<s:a action="message_editUI?id=%{id}&friendId=%{friend.id}">
										<button class="btn btn-default">编辑</button>
									</s:a>
									
									
									<s:a action="message_removeMsg?id=%{id}" onClick="return confirm('确定删除吗？')">
										<button class="btn btn-default">删除</button>
									</s:a>
							</td>
						</s:if>
						<s:else>
								<td>
									<img  style="width: 30px;height: 30px" alt="已发送" src="${pageContext.request.contextPath}/style/images/send.png">
									已发送
								</td>
								<td>
									
								
								<s:a action="message_showSend?id=%{id}">
									<button class="btn btn-default">查看</button>
								</s:a>
								<s:a action="message_removeMsg?id=%{id}" onClick="return confirm('确定删除吗？')">
									<button class="btn btn-default">删除</button>
								</s:a>
							</td>
						</s:else>
					    	
					
			 	 </tr>		
		  </s:iterator>
		</table>
	  </div>
	  </div>
	  </div>

</div>

	
	<%@ include file="/WEB-INF/jsp/public/commons_foot.jspf"%>
</body>
</html>