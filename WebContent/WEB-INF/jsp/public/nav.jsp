<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- 导航栏 -->
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display 折叠按钮 -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="index.jsp"><b>社会化互动分享平台</b></a>
    </div>
		
    <!-- Collect the nav links, forms, and other content for toggling 折叠按钮的内容 -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      	<s:if test="#session.navShow.forumShow == true">
      		<li class="active"><a href="forum_welcome.action">论坛<span class="sr-only">(current)</span></a></li>
      	</s:if>
      	
      	<s:if test="#session.navShow.resourceShow == true">
        	<li><a href="resource_homePage.action">资源</a></li>
     	</s:if>
		
		<s:if test="#session.navShow.activityShow == true">
			<li><a href="activity_activityHomePage.action">活动</a></li>
		</s:if>
		
		<s:if test="#session.navShow.sysShow == true">
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
					系统管理
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<s:if test="#session.navShow.userManage == true">
				    	<li><a href="user_list.action">用户管理</a></li>
				    </s:if>
				    <s:if test="#session.navShow.privilegManage == true">
				    	<li><a href="privilege_list.action">权限管理</a></li>
				    </s:if>
				    <s:if test="#session.navShow.forumManage == true">
				    	<li><a href="forumManage_list.action">论坛管理</a></li>
				    </s:if>
				    <s:if test="#session.navShow.resourceManage == true">
				    	<li><a href="#">资源管理</a></li>
				    </s:if>
				    <s:if test="#session.navShow.activityManage == true">
				    	<li><a href="#">活动管理</a></li>
				    </s:if>
				</ul>
			</li>
		</s:if>
      </ul> 
      
      <!-- 已登录用户的信息 -->
	  <div id="info" style="margin-top:10px; display:none;" class="pull-right">
		<img id="img_user" src="img/app.jpg" alt="nim" class="img-circle" style="height:30px; width:30px;">
		&nbsp;
		<span class="dropdown">
			<a id="nick_name" href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			</a>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				<li><a href="information_list.action">个人信息</a></li>
			    <li><a href="social_list.action">好友动态</a></li>
			    <li><a href="talk_list.action">我的说说</a></li>
			</ul>
		</span>
		&nbsp;&nbsp;
		<a href="user_logout.action">退出</a>
	  </div>
   </div> 
</div>
	
</nav>