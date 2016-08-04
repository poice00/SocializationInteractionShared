<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>修改个人信息</title>
	
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/DatePicker/WdatePicker.js" ></script>
</head>
<body>
<!-- 导航栏 -->
		<%@ include file="/WEB-INF/jsp/public/nav.jsp" %>
<div>
	<s:form action="information_edit">
		<s:hidden name="id"></s:hidden>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#e1e2e3">
			<tr height="50px">
				<td bgcolor="#ffffff" width="50%" align="right">
					<div class="style3">
						昵称：
					</div>
				</td>
				<td bgcolor="#ffffff">
					<div class="style5">
						<s:textfield  name="name"  cssStyle="width:200"/>
							<font color="#cc0033" size="2"><span id="result1">
							</span>
							</font><font color="red">*</font>
					</div>
				</td>
			</tr>
			<tr height="50px">
				<td bgcolor="#ffffff" width="50%" align="right">
					<div class="style3">
						生日：
					</div>
				</td>
				<td bgcolor="#ffffff">
					<div class="style5">
						<s:textfield  name="birthday" onClick="WdatePicker()"  cssStyle="width:200" readonly="true" />
							<font color="#cc0033" size="2"><span id="result1">
							</span>
							</font><font color="red">*</font>
					</div>
				</td>
			</tr>
				
			<tr height="50px">
				<td bgcolor="#ffffff" width="50%" align="right">
					<div class="style3">
						学历：
					</div>
				</td>
				<td bgcolor="#ffffff">
					<div class="style5">
						<s:textfield name="education"  cssStyle="width:200"/>
							<font color="#cc0033" size="2"><span id="result2">
							</span>
							</font><font color="red">*</font>
					</div>
				</td>
			</tr>
			<tr height="50px">
				<td bgcolor="#ffffff" width="50%" align="right">
					<div class="style3">
						邮箱：
					</div>
				</td>
				<td bgcolor="#ffffff">
					<div class="style5">
						<s:textfield name="email"  cssStyle="width:200"/>
							<font color="#cc0033" size="2"><span id="result3">
							</span>
							</font><font color="red">*</font>
					</div>
				</td>
			</tr>
			<tr height="50px">
				<td bgcolor="#ffffff" width="50%" align="right">
					<div class="style3">
						电话：
					</div>
				</td>
				<td bgcolor="#ffffff">
					<div class="style5">
						<s:textfield name="telephone" cssStyle="width:200"/>
							<font color="#cc0033" size="2"><span id="result4">
							</span>
							</font><font color="red">*</font>
					</div>
				</td>
			</tr>
			<tr height="50px">
				<td bgcolor="#ffffff" width="50%" align="right">
					<div class="style3">
						QQ：
					</div>
				</td>
				<td bgcolor="#ffffff">
					<div class="style5">
						<s:textfield name="qq"  cssStyle="width:200"/>
							<font color="#cc0033" size="2"><span id="result5">
							</span>
							</font><font color="red">*</font>
					</div>
				</td>
			</tr>
			<tr>
				<td bgcolor="#ffffff" width="50%" align="right">
				
					<s:submit value="提交"></s:submit>
				
				</td>
			</tr>
		</table>
		
	</s:form>
	</div>
		
	
	<%@ include file="/WEB-INF/jsp/public/commons_foot.jspf"%>
	
</body>
</html>