<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>排行榜</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/interaction.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<jsp:include page="/WEB-INF/jsp/public/style.jsp" flush="true"></jsp:include>
</head>
<body>
<!-- 导航栏 -->
<%@ include file="/WEB-INF/jsp/public/nav.jsp" %>
<div class="container">
		<div class="row">
			<div class="span12" style="padding-top: 10px;">
				<div class="span2" style="width: 200px;float:left;margin-left: 100px;">
					<h3>资源分享</h3>
				</div>
				<div class="span6" style="padding-top: 15px;">
					<ul class="nav nav-pills">
						<li><a href="resource_homePage.action" >所有资源</a></li>	
						<li><a href="resource_resRankUI.action" >排行榜</a></li>
						<li><a href="resource_highsearchUI.action" >高级搜索</a></li>	
						<li><a href="myresource_showupload.action" >我要上传</a></li>		
						<s:if test='#session.user !=null'>
						<li><a  href="myresource_myRes.action" style="padding-left: 20px;">+我的资源</a></li>
						</s:if>
					</ul>
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px;">
			<div class="span9" style="float:left;margin-left: 300px;width: 600px;">
				<div class="row" >
				<div style="float:left;"><p>下载量最多的资源：</p></div><div style="float:left;"><s:a action="resource_singalresRankUI?resTvalue=下载量">查看更多</s:a></div>
				<table class="table">
					<thead>
						<tr>
						<th>排名</th>
							<th>资源名称</th>
							<th>资源标签</th>
							<th>上传时间</th>
							<th>下载量</th>
						</tr>
					</thead>
					<tbody>
				   <s:iterator value="#resCounList" status="res">
				   <tr class="info">
				   <td>${res.index+1 }</td>	
				   <td><a href="myresource_showSingleRes.action?idd=%{id}">${name }</a></td>
							<td>${resTags }</td>
							<td><s:date format="yyyy-MM-dd hh:mm:ss" name="%{postTime }"></s:date></td>
							<td>${downCount }</td>
					</tr>
				     </s:iterator>
					</tbody>
				</table>
			  </div>
			  <div class="row" >
			  <p>收藏数最多的资源： <s:a action="resource_singalresRankUI.action?resTvalue=收藏量">查看更多</s:a></p>
			  <table class="table">
					<thead>
						<tr>
						     <th>排名</th>
							<th>资源名称</th>
							<th>资源标签</th>
							<th>上传时间</th>
							<th>收藏量</th>
						</tr>
					</thead>
					<tbody>
				   <s:iterator value="#resColleList" status="res">
				   <tr class="info">
				   <td>${res.index+1 }</td>	
				   <td><a href="myresource_showSingleRes.action?idd=%{id}">${name }</a></td>
							<td>${resTags }</td>
							<td><s:date format="yyyy-MM-dd hh:mm:ss" name="%{postTime }"></s:date></td>
							<td>${collectionCount }</td>
					</tr>
				     </s:iterator>
					</tbody>
				</table>
			   
			  </div>
			  <div class="row" >
			  <p>上传最多的用户：<s:a action="resource_singalresRankUI.action?resTvalue=上传量">查看更多</s:a></p>
			   <table class="table">
					<thead>
						<tr>
						    <th>排名</th>
							<th>用户ID</th>
							<th>用户昵称</th>
							<th>上传量</th>
						</tr>
					</thead>
					<tbody>
				   <s:iterator value="#userupCountList" status="res">
				   <tr class="info">
				   <td>${res.index+1 }</td>	
				   <td><a href="myresource_showSingleRes.action?idd=%{id}">${loginName }</a></td>
							<td>${name }</td>			
							<td>${resUpCount }</td>
					</tr>
				     </s:iterator>
					</tbody>
				</table>
			  </div>
			</div>
			<div class="span3" style="float:left;margin-left: 50px;margin-top: 10px;width: 200px;">
				<s:form class="form-search" action="myresource_normalSearch.action" method="get">
					<div class="input-append">
						<input name="searchName" class="span2 search-query" type="text" style="height: 34px;"/>
						<button type="submit" class="btn">查找</button>
					</div>
				</s:form>
				<p>相关推荐</p>
				<div>
		         <s:iterator value="#session.resTagMap" status="status">
		         <s:if test="%{}"></s:if>
		      <s:a action="resource_singalresTagUI?resTg=%{key}">${key }(${value })</s:a>
	       </s:iterator>
	    </div>
		</div>		
	</div>
</div>
<!-- 页脚 -->
<div style="height:20px"></div>
<%@ include file="/WEB-INF/jsp/public/footer.jsp" %>
</body>
</html>