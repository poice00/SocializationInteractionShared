<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
#footer{position:relative;}
</style>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/interaction.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>活动首页</title>
<jsp:include page="/WEB-INF/jsp/public/style.jsp" flush="true"></jsp:include>
</head>
<body>
<!-- 导航栏 -->
<%@ include file="/WEB-INF/jsp/public/nav.jsp" %>
 <div class="container" >
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
		
     <div class="row"  style="float:left;">
			<div class="span9" style="float:left;width: 810px;margin-top: 20px;">
				<div class="tabbable" id="tabs-lectures">
				 <input type="hidden" id="panShow"  value="${activiName}" /> 
					<ul class="nav nav-tabs">
						<li id="myCreate"><a data-toggle="tab" href="#panel-mc" aria-expanded="false">我创建的活动</a></li>
						<li id="myApplyremove"><a data-toggle="tab" href="#panel-ma" aria-expanded="false">我申请的活动</a></li>
						<li id="myInterestActremove"><a data-toggle="tab" href="#panel-mi" aria-expanded="false">我感兴趣的活动</a></li>	
						<li id="activityExam"><a data-toggle="tab" href="#panel-ms" aria-expanded="false">申请列表</a></li>	
					</ul>					
					<div class="tab-content" style="height: 400px;">
						<div class="tab-pane active" id="panel-mc">
								<s:if test="#myCreateList.isEmpty()">
						暂时还没有创建活动呢~
				    </s:if>
				    	<s:else>
							<table class="table">
								<thead>
									<tr>
										<th>活动名称</th>
										<th>活动状态</th>
										<th>参加人数</th>
										<th>人数限制</th>
									</tr>
								</thead>
								<tbody>
							
								
										<s:iterator value="#myCreateList" status="myCreate">
											<tr class="info">
												<td><a href="activity_activityDetails.action?idd=${id }">${activityName }</a></td>
												<td>${activityState }</td>
												<td>${activityMemNum }</td>
												<td>${activityInteNum }</td>
											</tr>
										</s:iterator>
									</s:else>
								</tbody>
							</table>
						</div>
						
						<div class="tab-pane " id="panel-ma">
								<s:if test="#myApplyList.isEmpty()">
						暂时还没有申请的活动呢~
				    </s:if>
				    <s:else>
							<table class="table">
								<thead>
									<tr>
										<th>活动名称</th>
										<th>活动状态</th>
										<th>参加人数</th>
										<th>申请状态</th>
									</tr>
								</thead>
								<tbody>
							
									
										<s:iterator value="#myApplyList" status="myCreate">
											<tr class="info">
												<td><a href="activity_activityDetails.action?idd=${activity.id }">${activity.activityName }</a></td>
												<td>${activity.activityState}</td>
												<td>${activity.activityMemNum }</td>
												<td>${state }</td>
												<td>
												<s:if test="%{state eq '已拒绝'}">
												</s:if>
												<s:else>
												<s:a class="btn btn-danger btn-xs" href="activity_myApplyremove.action?actApplyId=%{id}" onClick="return window.confirm('确定要删除吗？')">
													<span class="glyphicon glyphicon-remove"></span> 取消申请
												</s:a>
												</s:else>
												</td>
											</tr>
										</s:iterator>
									</s:else>
								</tbody>
							</table>
						</div>

						<div class="tab-pane " id="panel-mi">
								<s:if test="#myInterestList.isEmpty()">
						暂时还没有感兴趣的活动呢~
				    </s:if>
				    <s:else>
							<table class="table">
								<thead>
									<tr>
										<th>活动名称</th>
										<th>活动状态</th>
										<th>参加人数</th>
										<th>人数限制</th>
										<th>取消</th>
									</tr>
								</thead>
								<tbody>									
										<s:iterator value="#myInterestList" status="myCreate">
											<tr class="info">
												<td><a href="activity_activityDetails.action?idd=${id }">${activityName }</a></td>
												<td>${activityState }</td>
												<td>${activityMemNum }</td>
												<td>${activityInteNum }</td>
												<td>
												<s:a class="btn btn-danger btn-xs" href="activity_myInterestActremove.action?idd=%{id}" onClick="return window.confirm('确定要删除吗？')">
													<span class="glyphicon glyphicon-remove"></span> 取消收藏
												</s:a>
												</td>
											</tr>
										</s:iterator>
									</s:else>
								</tbody>
							</table>
						</div>
						<div class="tab-pane " id="panel-ms">
							<s:if test="#applyUserList.isEmpty()">
						暂时还没有人申请呢
				    </s:if>
				    	<s:else>
							<table class="table">
								<thead>
									<tr>
										<th>活动名称</th>
										<th>申请人员</th>
										<th>参加人数</th>
										<th>人数限制</th>
										<th >操作</th>
									</tr>
								</thead>
								<tbody>
								
								
										<s:iterator value="#applyUserList" status="myCreate">
											<tr class="info">
												<td><a href="activity_activityDetails.action?idd=${activity.id }">${activity.activityName }</a></td>
												<td>${user.name }</td>
												<td>${activity.activityMemNum }</td>
												<td>${activity.activityInteNum }</td>
												<td style="width:123px;">
												<s:a class="btn btn-danger btn-xs" href="activity_activityExam.action?actApplyId=%{id}" >
													<span class="glyphicon glyphicon-remove"></span> 同意
												</s:a>
												<s:a class="btn btn-danger btn-xs" href="activity_activityNoPass.action?actApplyId=%{id}" onClick="return window.confirm('确定要拒绝吗？')">
													<span class="glyphicon glyphicon-remove"></span> 拒绝
												</s:a>
											</td>
											</tr>
										</s:iterator>
									</s:else>
								</tbody>
							</table>
						</div>
						</div>
					</div>
				</div>
       </div>
			<div class="span3" style="float:left;margin-left: 100px;width: 200px;margin-top: 50px;">
				<s:form class="form-search" action="activity_search.action" method="get">
					<div class="input-append">
						<input name="keyWord" class="span2 search-query" type="text" style="height: 34px;"/>
						<button type="submit" class="btn">查找</button>
					</div>
				</s:form>
				<p>热门标签</p>
				<s:iterator value="#activityTypesList" >
						<a href="activity_singalactivityPage.action?activityTypeId=${id }"><span class="badge badge-success" style="width: 54px;height: 30px;margin-bottom: 10px;font-size: 20px;background-color: #800080;">${Name }</span></a>
				</s:iterator>
			</div>
</div>
<div style="height:20px"></div>
<!-- 页脚 -->
<%@ include file="/WEB-INF/jsp/public/footer.jsp" %>
<script type="text/javascript">
 $().ready(function() {
	var ss=$("#panShow").attr("value");
    if(ss=="myInterestActremove")
    	{$("#myInterestActremove").attr("class","active");
    	$("#panel-mi").addClass("active");
    	$("#panel-ma").removeClass("active");
    	$("#panel-ms").removeClass("active");
    	$("#panel-mc").removeClass("active");
    	  }
    if(ss=="myApplyremove")
	{$("#myApplyremove").attr("class","active");
	$("#panel-ma").addClass("active");
	$("#panel-mi").removeClass("active");
	$("#panel-ms").removeClass("active");
	$("#panel-mc").removeClass("active");
	}
    if(ss=="activityExam")
	{$("#activityExam").attr("class","active");
	$("#panel-ms").addClass("active");
	$("#panel-ma").removeClass("active");
	$("#panel-mi").removeClass("active");
	$("#panel-mc").removeClass("active");
	   }
    if(ss=="")
	{$("#myCreate").attr("class","active");
	$("#panel-mc").addClass("active");
	$("#panel-ma").removeClass("active");
	$("#panel-ms").removeClass("active");
	$("#panel-mi").removeClass("active");
	   }
}); 
</script>
</body>
</html>