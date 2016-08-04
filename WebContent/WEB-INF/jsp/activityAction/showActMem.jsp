<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>活动成员列表</title>
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
				<h4>${activityName }下的成员</h4>
				<div class="member-list">
					<ul class="inline">
					<s:iterator value="#usersList" status="activity">
							<li>
								<div class="picture">
									<a href="personalInfo.htm?userId=${id }"><img
										src="${headImage }" style="height: 40px; width: 40px;"></a>
								</div>
								<div class="name" align="center">
									<small><a
										href="personalInfo.htm?userId=${id }">${name }</a></small>
								</div>
							</li>
					</s:iterator>
					</ul>
				</div>
				<hr>
			</div>
			<div class="span3" style="margin-left: 0px;">
				<p>热门帖子</p>
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
				<p>相关推荐</p>
			</div>
		</div>
	</div>
<div style="height:20px"></div>
<!-- 页脚 -->
<%@ include file="/WEB-INF/jsp/public/footer.jsp" %>
</body>
</html>