<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 解决长时间不登录，再次登录页面嵌套问题 -->
<!-- 	<script type="text/javascript">
		$(function(){
			document.forms[0].loginName.focus();
		// 在被嵌套时就刷新上级窗口
		 if(window.parent != window){
			//window.parent.location.reload(true);
			window.parent.location = window.location;
		 } 
		 
		});
		
	</script> -->
	
	<script>
    $(function(){
        $('#myModal').modal({
        show:true,
        backdrop:true
        })
    });
</script>
</head>
<body>
	<%-- <s:form action="user_login">
		<s:textfield name="loginName"></s:textfield><br>
		<s:password name="password"></s:password><br>
		<s:submit value="提交"></s:submit>
		<font color="red"><s:fielderror/></font>
	</s:form> --%>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 400px;top:30%;left:40%">
		<div class="modal-content">
		
			<div class="modal-header">
        		 <a href="javascript:history.go(-1);"><button type="button" class="close"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></a>
       			 <h4 class="modal-title" id="myModalLabel">登陆页面</h4>
     		 </div>
		
		
			<div class="modal-body">
    
				<s:form action="user_login" cssClass="form" >
						<s:textfield name="loginName" cssClass="sign form-control" placeholder="用户名"></s:textfield><br>
						<s:password name="password" cssClass="sign form-control" placeholder="密码"></s:password><br>
						<s:submit cssClass="sign btn btn-success" value="提交"></s:submit><br>
						<font color="red"><s:fielderror/></font>	
						<label class="checkbox">
							<input type="checkbox" checked="checked" value="" >
							<span class="in_sign">下次自动登录</span>
						</label>
						<a class="in_sign" style="margin-left:15px;" href="#">忘记密码？</a>
						<a style="font-size:12px; margin-left:30px;" href="#" onclick="toggle()">立即注册</a>
						<hr style="margin-top:15px; margin-bottom:5px;">
						<span class="in_sign">使用其他方式登录</span>
						<a class="icon-24 icon-24-account-qq" href="http://www.liepin.com/connect/?open_account=3&user_kind=0" target="_blank"></a>
			            <a class="icon-24 icon-24-account-weixin" href="http://www.liepin.com/connect/?open_account=4&user_kind=0" target="_blank"></a>
			            <a class="icon-24 icon-24-account-weibo" href="http://www.liepin.com/connect/?open_account=1&user_kind=0" target="_blank"></a>
				</s:form>
			  </div>
			 <div class="modal-footer">
				 <a href="javascript:history.go(-1);"><button class="btn btn-default">取消</button></a>
       			 <!-- <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> -->
             </div>
		</div>
	</div>

</body>
</html>