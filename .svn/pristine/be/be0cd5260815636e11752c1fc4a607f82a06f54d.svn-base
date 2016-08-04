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
<script>
	KindEditor.ready(function(K) {
		K.create('textarea[name="content"]', {
			uploadJson : 'file_upload.action',
			fileManagerJson : 'kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			allowImageUpload : true,
			autoHeightMode : true,
			afterCreate : function() {
				this.loadPlugin('autoheight');
			},
			afterBlur : function() {
				this.sync();
			} //Kindeditor下获取文本框信息
		});
	});
	
</script>
</head>
<body style="background: url('img/back.jpg')">
<%@ include file="/WEB-INF/jsp/public/nav.jsp"%>
 <!-- 收信箱 -->
 <div class="panel panel-default" style="background: url('img/back.jpg')">
	<div class="panel-heading" style="background: url('img/back.jpg')">
		<ol class="breadcrumb" style="background: url('img/back.jpg')">
		  <li><s:a action="forum_index">首页</s:a></li>
		  <li>收件箱</li>
		</ol>
	</div>
	 <div class="container">
		  <div class="tab-pane" id="msgs" style="margin-bottom:200px;">
		  
		  <table  class="table table-striped">
							
				<tr>
					<td>发送人</td>
					<td>状态</td>
					<td>操作</td>
			   </tr>		
			  <s:iterator value="#user.anotherMsgs">
				<s:if test="sendStatus==1">
					<tr>
						<td> ${user.name }</td>
						<td>
							
								<s:if test="status==0">
							    		<s:a action="message_show?id=%{id}">
							    		<img style="width: 30px;height: 30px" alt="未读" src="${pageContext.request.contextPath}/style/images/nolook.png">
							    		<label style="color: red">未读</label>
							    		</s:a>
							    </s:if>
							    	<s:else>
							    	<img  style="width: 30px;height: 30px" alt="已读" src="${pageContext.request.contextPath}/style/images/look.png">已读
							    	</s:else>
							
							    </td>
							    
						<td>
								<s:a action="message_replyUI?id=%{id}&friendId=%{user.id}">
									<button class="btn btn-default">回复</button>
								</s:a>
								
								<s:a action="message_show?id=%{id}">
									<button class="btn btn-default">查看</button>
								</s:a>
								
								
								<s:a action="message_removeMsg?id=%{id}" onClick="return confirm('确定删除吗？')">
									<button class="btn btn-default">删除</button>
								</s:a>
						
						</td>
				 	 </tr>		
			 	 </s:if>
			  </s:iterator>
			</table>
		  </div>
		  
	  </div>
	  

</div>

	
	<%@ include file="/WEB-INF/jsp/public/commons_foot.jspf"%>
</body>
</html>