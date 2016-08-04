<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>社会化互动分享平台</title>
	<%@ include file="/WEB-INF/jsp/public/style.jsp"%>
	
	<style>
		#footer{
			position:relative;
		}
		
		.top{
			margin-left:20px;
		}
	</style>
</head>
<body>
	<!-- 引入导航栏 -->
	<%@ include file="/WEB-INF/jsp/public/nav.jsp"%>
	
	<!-- 图片轮播 -->
	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
	  <!-- Indicators -->
	  <ol class="carousel-indicators">
	    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
	    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
	    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
	    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
	  </ol>
	
	  <!-- Wrapper for slides -->
	  <div class="carousel-inner" role="listbox">
	    <div class="item active">
	      <img src="img/forum.jpg" style="height:400px;" alt="程序员">
	      <div class="carousel-caption">
	      	<!-- <h1>程序员</h1>
		    <p>程序员(英文Programmer)是从事程序开发、维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，但两者的界限并不非常清楚，特别是在中国。软件从业人员分为
		        初级程序员、高级程序员、系统分析员，系统架构师，测试工程师五大类。
		    </p> -->
	      </div>
	       </div>
	    <div class="item">
	      <img src="img/profile.jpg" style="height:400px;" alt="知网">
	      <div class="carousel-caption">
	      	<!-- <h1>中国知网</h1>
	        <p>中国知网知识发现网络平台—面向海内外读者提供中国学术文献、外文文献、学位论文、报纸、会议、年鉴、工具书等各类资源统一检索、统一导航、在线阅读和下载服务。</p> -->
	      </div>
	    </div>
	    <div class="item">
	    	<img src="img/resource.jpg" style="height:400px;" alt="Git">
	    	<div class="carousel-caption">
	    		<!-- <h1>GitHub</h1>
	    		<p>Git是一个分布式的版本控制系统，最初由Linus Torvalds编写，用作Linux内核代码的管理。在推出后，Git在其它项目中也取得了很大成功，
	    		尤其是在Ruby社区中。目前，包括Rubinius、Merb和Bitcoin在内的很多知名项目都使用了Git。Git同样可以被诸如Capistrano和Vlad the Deployer这样的部署工具所使用。
	    		</p> -->
	    	</div>
	    </div>
	    <div class="item">
	    	<img src="img/active.jpg" style="height:400px;" alt="...">
	    	
	    </div>
	  </div>
	
	  <!-- Controls -->
	  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
	    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
	    <span class="sr-only">Previous</span>
	  </a>
	  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
	    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
	    <span class="sr-only">Next</span>
	  </a>
	</div>
	
	<!-- 登录和注册 -->
	<%@ include file="/WEB-INF/jsp/public/sign.jsp" %>
	
	<!-- 搜索框 -->
	<div class="container" style="margin-bottom:20px;">
		<div id="search_box">
			<input id="search_content" type="text" name="search_content" placeholder="请输入关键词，如中国好声音第二季">
		</div>
		<div id="serarch_button">
			<input id="search_button" type="image" value="" src="img/btn_search.jpg" onclick="searchAll()">
		</div>
		<div id="hot">
			<ul class="list-unstyled list-inline">
				<li><b>大家都在搜:</b></li>
				<s:iterator value="topic1">
					<a href="topic_show.action?id=${id }"><b>${title }</b></a>
				</s:iterator>
				<s:iterator value="resource1">
					<a href="myresource_showSingleRes.action?idd=${id}" class="top"><b>${name }</b></a>
				</s:iterator>
				<s:iterator value="activity1">
					<a href="activity_activityDetails.action?idd=${id }" class="top"><b>${activityName }</b></a>
				</s:iterator>
			</ul>
		</div>
	</div>
	
	<!-- 分栏布局 -->
	<div class="container" style="height:447px;">
		<div class="row">
			<!-- 论坛 -->
			<s:if test="#session.navShow.forumShow == true">
				<div class="col-md-4">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>序号</th>
								<th>热点话题</th>
							</tr>
						</thead>
						<tbody id="topic_table">
							<s:iterator value="#topicList" status="status">
								<tr>
									<td>
										${status.count }
									</td>
									<td>
										<a href="topic_show.action?id=${id }">${title }</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</s:if>
			
			<!-- 资源 -->
			<s:if test="#session.navShow.resourceShow == true">
				<div class="col-md-4">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>序号</th>
								<th>热门资源</th>
							</tr>
						</thead>
						<tbody id="resource_table">
							<s:iterator value="#resourceList" status="status">
								<tr>
									<td>
										${status.count }
									</td>
									<td>
										<a href="myresource_showSingleRes.action?idd=${id}">${name }</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</s:if>
			
			<!-- 活动 -->
			<s:if test="#session.navShow.activityShow == true">
				<div class="col-md-4">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>序号</th>
								<th>热门活动</th>
							</tr>
						</thead>
						<tbody id="activity_table">
							<s:iterator value="#activityList" status="status">
								<tr>
									<td>
										${status.count }
									</td>
									<td>
										<a href="activity_activityDetails.action?idd=${id }">${activityName }</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</s:if>
		</div>
	</div>
	
	<!-- Tab -->
<!-- 	<div class="container">
		<ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">小组</a></li>
		    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">活动</a></li>
		    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">孙思禹</a></li>
		    <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a></li>
		 </ul>

		  Tab panes
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane active" id="home">
			    You can activate a tab or pill navigation without writing any JavaScript by simply specifying data-toggle="tab" or data-toggle="pill" on an element. 
			    Adding the nav and nav-tabs classes to the tab ul will apply the Bootstrap tab styling, while adding the nav and nav-pills classes will apply pill styling.
		    </div>
		    <div role="tabpanel" class="tab-pane" id="profile">...</div>
		    <div role="tabpanel" class="tab-pane" id="messages">闫宝彬喜欢吃屎</div>
		    <div role="tabpanel" class="tab-pane" id="settings">...</div>
		  </div>
	</div> -->
	
	<!-- 引入footer -->
	<%@ include file="/WEB-INF/jsp/public/footer.jsp" %>
</body>

</html>
