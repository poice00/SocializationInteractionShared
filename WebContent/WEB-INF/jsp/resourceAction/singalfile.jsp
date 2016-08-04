<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>资源详情</title>
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
		<div class="row" style="float:left;width: 800px;">
					<h3>${name}</h3>
					<div>
						<s:a href="information_list.action?visitedId=%{user.id }">${user.name }</s:a>
						于
						<s:date format="yyyy-MM-dd hh:mm:ss" name="%{postTime }"></s:date>
						上传
					</div>
					<div>${name}</div>
					<div>
						<ul>
							<li><strong>资源得分：</strong><span>${score}</span></li>
							<li><strong>下载次数：</strong><span>${downCount}</span></li>
							<li><strong>资源类型：</strong><span>${resType.name}</span></li>
							<li><strong>资源大小：</strong><span>${resSize}</span></li>
							<li><span>(${repCount}位用户参与评分)</span></li>
						</ul>
						<ul>
							<li>标签：${resTags}</li>
						</ul>
					</div>
					<div style="margin-top: 10px;border-bottom-width: 10px;margin-bottom: 20px;">
						<p style="font-size: 20px;">
						<s:if test="%{warnMessage eq 'notCollect'}">
							<div id="my-interest" style="float:left"><a id="interested"  href="#" onclick="addInterest()">收藏</a>&nbsp;</div>
						</s:if>
						<s:if test="%{warnMessage eq 'collected'}">
							<div id="my-interest" style="float:left"><i class="icon-ok"></i>&nbsp;我已收藏&nbsp;<a href="#"
											onclick="removeInterest()" >取消</a>&nbsp;</div>
						</s:if>
						<s:a action="resourcedownload?idd=%{id}">下载</s:a>						
					    <s:a action="myresource_delete?idd=%{id}">删除该资源</s:a></p>
					</div>
					<div>
						<s:if test="recordList.isEmpty()">
							<p>下面还没有回复</p>
						</s:if>
						<s:else>
							<s:iterator value="recordList" status="actTopicReply">
								<div class="media">
									<a href="#" class="pull-left"> <img alt="网络不佳，无发显示呢"
									width="100px" height="100px"  src="${user.headImage }" />
									</a>
									<div class="media-body">
										<div>
											<strong class="media-heading">${user.name }于</strong>
											<s:date format="yyyy-MM-dd hh:mm:ss" name="%{postTime }"></s:date>
											回复到：<s:a action="resReply_resRepDelete?resRepId=%{id}">删除该评论</s:a>
										</div>
										${content }
									</div>
								</div>
							</s:iterator>
						
						<!--分页信息-->
						<%@ include file="/WEB-INF/jsp/activityAction/pageView.jspf"%>
						<s:form id="page" action="myresource_showSingleRes?idd=%{id}"></s:form>
						</s:else>
					</div>
					<div style="margin-top: 100px;">
					<s:form class="form-horizontal" method="post" action="resReply_add?idd=%{id}">
							<div class="media">
									<a href="#" class="pull-left"> <img alt="网络不佳，无发显示呢"
										width="176px" height="110px" style="margin-top: 10px;" src="${sessionScope.user.headImage}" />
									</a>
					
									<div class="media-body">
										<div>
											<strong class="media-heading">请选择评分（默认5分）：</strong>
											<s:select name="resSource" list="{'5','4','3','2','1','0'}" ></s:select>
											<button type="submit" class="btn" style="background-color: #9A9CC1;">发表评论</button>
											<br/>
										</div>
										<textarea id="editor" class="input-xxlarge" rows="4" name="content" placeholder="评论内容"></textarea>
									</div>	
							</div>
					</s:form>
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
	    <s:a action="resource_singalresTagUI?resTg=其他">其他(${resTagUseToShow })</s:a>
	    </div>
		</div>		
	</div>
</div>
<!-- 页脚 -->
<div style="height:20px"></div>
<%@ include file="/WEB-INF/jsp/public/footer.jsp" %>
<script>
function addInterest() {
		$.ajax({
					type : "post",
					url : "myresource_collectadd.action",
					data : {
						idd : "${id }"
					},
					success : function(response) {
							$("#user-actions")
									.html("<i class='icon-ok'></i>")
									.append("&nbsp;我已收藏&nbsp;")
									.append(
											"<a href='#' id='removeInterest' onclick='removeInterest()'>取消</a>&nbsp;");
							alert(response);
							window.location.href="myresource_showSingleRes.action?idd=${id}";
					}
				});
	};
function removeInterest() {
		$.ajax({
					type : "post",
					url : "myresource_collectRemove.action",
					data : {
						idd : "${id }"
					},
					success : function(response) {
							$("#user-actions")
									.html(
											"<button id='interested' class='btn btn-primary btn-small' type='button' onclick='addInterest()'>收藏</button>&nbsp;");
							alert(response);
							window.location.href="myresource_showSingleRes.action?idd=${id}";
					}
				});
	};

function addReport() {
		$.ajax({
			type : "post",
			url : "addReport.htm",
			data : {
				"reportedId" : "${activity.activityId }",
				"reportReason" : $("#reportReason").val(),
				"reportType" : "activity"
			},
			success : function(msg) {
				if (msg === "success") {
					$("#report-span").html("已举报");
					$('#reportModal').modal('hide')
				}
			}

		});
	};
</script>
</body>
</html>