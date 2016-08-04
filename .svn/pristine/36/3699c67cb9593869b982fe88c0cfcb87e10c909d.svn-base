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
<script type="text/javascript">
		function saveInfo() {
			document.forms["me"].action="message_edit.action";
			document.forms["me"].submit();
		}
</script>
</head>
<body style="background: url('img/back.jpg')">
<%@ include file="/WEB-INF/jsp/public/commons_head.jspf"%>
	<div align="center" style="margin-top: 200px">
		<s:form action="message_editAdd" id="me">
			
			<s:hidden name="id"></s:hidden>
			<s:hidden name="friendId"></s:hidden><s:debug></s:debug>
			发私信给：<s:textfield cssStyle="width:600px; " value="%{#friend.name}" readonly="true"></s:textfield><br>
			内容:<s:textarea name="content" value="%{#message.content}"></s:textarea><br>
		
			<button type="submit" class="btn btn-default">发送</button>
			<button type="submit" class="btn btn-default" onclick="return saveInfo()">保存</button>
		</s:form>
			<a href="javascript:history.go(-1);"><button class="btn btn-default">返回上一级</button></a>
	
</div>




	
	<%@ include file="/WEB-INF/jsp/public/commons_foot.jspf"%>
</body>
</html>