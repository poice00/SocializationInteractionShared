<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>资源频道-我的资源</title>
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
				<div class="tabbable" id="tabs-lectures">
					<ul class="nav nav-tabs">
					 <input type="hidden" id="panShow"  value="${warnMessage}" /> 
						<li id="addclick1" class="active"><a data-toggle="tab" href="#panel-mc" aria-expanded="true" onclick="addclick1()">我上传的资源</a></li>
						<li id="addclick2" class=""><a data-toggle="tab" href="#panel-ma" aria-expanded="false" onclick="addclick2()">我下载的资源</a></li>
						<li id="addclick3" class=""><a data-toggle="tab" href="#panel-mi" aria-expanded="false" onclick="addclick3()">我收藏的资源</a></li>	
					</ul>
					<div class="tab-content" style="height: 400px;">
						<div class="tab-pane active" id="panel-mc">
							<s:if test="#upresList.isEmpty()">
						暂时还没有上传资源呢~
				    </s:if>
							<s:else>
								<table class="table">
									<thead>
										<tr>
											<th>资源名称</th>
											<th>资源标签</th>
											<th>资源类型</th>
											<th>资源大小</th>
										</tr>
									</thead>
									<tbody>


										<s:iterator value="#upresList" status="upres">
											<tr class="info">
												<td><a href="myresource_showSingleRes.action?idd=${id}">${Name }</a></td>
												<td>${resTags }</td>
												<td>${resType.name }</td>
												<td>${resSize }</td>
											</tr>
										</s:iterator>
										</s:else>
									</tbody>
								</table>
						</div>

						
							<div class="tab-pane" id="panel-ma">
							<s:if test="#downReslist.isEmpty()">
						暂时还没有下载资源呢~
				    </s:if>
						<s:else>
								<table class="table">
									<thead>
										<tr>
											<th>资源名称</th>
											<th>资源标签</th>
											<th>资源类型</th>
											<th>资源大小</th>
										</tr>
									</thead>
									<tbody>


										<s:iterator value="#downReslist" status="downres">
											<tr class="info">
												<td><a href="myresource_showSingleRes.action?idd=${id}">${Name }</a></td>
												<td>${resTags }</td>
												<td>${resType.name }</td>
												<td>${resSize }</td>
											</tr>
										</s:iterator>
										</s:else>
									</tbody>
								</table>
							</div>

							<div class="tab-pane" id="panel-mi">
								<s:if test="#collResList.isEmpty()">
						暂时还没有收藏资源呢~
				    </s:if>
								<s:else>
									<table class="table">
										<thead>
											<tr>
												<th>资源名称</th>
												<th>资源标签</th>
												<th>资源类型</th>
												<th>资源大小</th>
												<th>取消收藏</th>
											</tr>
										</thead>
										<tbody>


											<s:iterator value="#collResList" status="collres">
												<tr class="info">
													<td><a
														href="myresource_showSingleRes.action?idd=${id}">${Name }</a></td>
													<td>${resTags }</td>
													<td>${resType.name }</td>
													<td>${resSize }</td>
													<td><s:a class="btn btn-danger btn-xs"
															href="myresource_CancelCollect.action?idd=%{id}">
															<span class="glyphicon glyphicon-remove"></span> 取消收藏
												</s:a></td>
												</tr>
											</s:iterator>

										</tbody>
									</table>
								</s:else>
							</div>
					</div>
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
<%@ include file="/WEB-INF/jsp/public/footer.jsp" %>

<script type="text/javascript">
   $().ready(function() {
	var ss=$("#panShow").attr("value");
    if(ss=="CancelCollect")
    	{
    	$("#addclick3").addClass("active");
    	$("#addclick1").removeClass("active");
    	$("#addclick2").removeClass("active");
    	$("#panel-mi").addClass("active");
    	$("#panel-mc").removeClass("active");	
    	$("#panel-ma").removeClass("active");	
    }
}); 
 function addclick1() {
	 $("#addclick1").addClass("active");
 	$("#addclick2").removeClass("active");
 	$("#addclick3").removeClass("active");
 	$("#panel-mc").addClass("active");
 	$("#panel-mi").removeClass("active");	
 	$("#panel-ma").removeClass("active");
	};
	 function addclick2() {
		 $("#addclick2").addClass("active");
	 	$("#addclick1").removeClass("active");
	 	$("#addclick3").removeClass("active");
	 	$("#panel-ma").addClass("active");
	 	$("#panel-mi").removeClass("active");	
	 	$("#panel-mc").removeClass("active");
		};
		 function addclick3() {
			$("#addclick3").addClass("active");
		 	$("#addclick2").removeClass("active");
		 	$("#addclick1").removeClass("active");
		 	$("#panel-mi").addClass("active");
		 	$("#panel-mc").removeClass("active");	
		 	$("#panel-ma").removeClass("active");
			};
</script>
</body>
</html>