<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新建话题</title>
<style>
#footer{position:relative;}
</style>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/interaction.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/jsp/public/style.jsp" flush="true"></jsp:include>
</head>
<body>
<!-- 导航栏 -->
<%@ include file="/WEB-INF/jsp/public/nav.jsp" %>
<div class="text2">
</div>
<div class="container">
		<div class="row">
			<div class="span12" style="padding-top: 10px;">
				<div class="span2" style="width: 200px;float:left;margin-left: 100px;">
					<h3>线下活动</h3>
				</div>
				<div class="span6" style="padding-top: 15px;">
					<ul class="nav nav-pills">
						<li class="active"><a href="activity_activityHomePage.action" style="margin-right: 100px;">所有活动</a></li>	
						<li><a class="btn btn-primary" href="activity_createUI.action" style="padding-left: 20px;">+创建活动</a></li>
						<s:if test='#session.user !=null'>
						<li><a  href="activity_myAct.action" style="padding-left: 20px;">+我的活动</a></li>
						</s:if>
					</ul>
				</div>
			</div>
		</div>
      <div class="row">
			<div class="span9">
				<h4 align="center" style="font-size: 20px;">你在&nbsp;&nbsp;${activityName }&nbsp;&nbsp;下发表话题</h4>
				<s:form class="form-horizontal" method="post" action="actTopic_add">
					<input type="hidden" name="activityId" value="${id }">
					<div class="control-group">
						<label class="control-label">标题</label>
						<div class="controls">
							<input id="postTitle" class="input-xxlarge" size="100"
								name="title" placeholder="标题" type="text" style="height: 30px;">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">内容</label>
						<div class="controls">
							<textarea id="editor" class="input-xxlarge" rows="15"
								name="content" placeholder="内容"></textarea>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn">创建</button>
						</div>
					</div>
				</s:form>
				<hr>

			</div>
			<div class="span3" style="margin-left: 0px;">
				<form class="form-search" action="searchActivity.htm" method="get">
					<div class="input-append">
						<input name="keyWord" class="span2 search-query" type="text" style="height: 34px;"/>
						<button type="submit" class="btn">查找</button>
					</div>
				</form>
				<p>最新招聘</p>
				<ul>
					<li>新闻资讯</li>
					<li>体育竞技</li>
					<li>娱乐八卦</li>
					<li>前沿科技</li>
					<li>环球财经</li>
					<li>天气预报</li>
					<li>房产家居</li>
					<li>网络游戏</li>
				</ul>
			</div>
		</div>		
	</div>
<div style="height:20px"></div>
<!-- 页脚 -->
<div style="height:20px"></div>
<%@ include file="/WEB-INF/jsp/public/footer.jsp" %>
<script>
</script>
</body>
</html>