<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>高级搜索</title>
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
		<div class="row">
			<div class="span9" style="float:left;">
				<div class="row" style="float:left;">
				<div style="font-size: 20px;margin-top: 50px;width: 600px;margin-left: 300px;"><s:if test="#highresLevelList.isEmpty()">
				"大侠，暂无你想要的资源，还请换个条件再搜一次吧~"
				</s:if>
				</div>
				<s:else>
				<s:iterator value="#highresLevelList" status="activity">
					<div class="span3">
						<img src="${activityImg }" /> <br>
					</div>
					<div class="span6">
						<h4><s:a action="myresource_showSingleRes?idd=%{id}">${name }</s:a></h4>
						<div>
							<strong>上传时间:</strong>
							<s:date format="yyyy-MM-dd hh:mm:ss" name="%{postTime }"></s:date>
							<br> <strong>下载次数:</strong> ${downCount }<br>
							<div id="user-actions">
							<s:if test="%{description.length() >= 30}">
							${description.substring(0,30) }...<br>
								</s:if>
								<s:else>
								${description }<br>
							</s:else>
						   </div>
						   <div>
								资源类型：${resType.name }
						   </div>
						   <div>
								资源标签：${resTags }
						   </div>
					   </div>
				</div>
				<hr>
			 </s:iterator>
			 </s:else>
			</div>
			</div>
			<div class="span3" style="float:left;margin-left: 0px;margin-top: 30px;">
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