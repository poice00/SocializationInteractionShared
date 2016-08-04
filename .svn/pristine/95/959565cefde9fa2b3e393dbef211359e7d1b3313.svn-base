<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<jsp:include page="/WEB-INF/jsp/public/style.jsp" flush="true"></jsp:include>
<head>
<style>
#footer{position:relative;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人空间</title>
<link rel="stylesheet" type="text/css" href="css/lrtk.css" />
<script src="js/modernizr.custom.js"></script>
<script type="text/javascript">
	function viewPic() {
		var oFReader = new FileReader();
		oFReader.onload = function(e) {
			document.getElementById("uploadPreview").src = e.target.result;
		}
		if (document.getElementById("uploadImage").files.length === 0) {
			return;
		}
		var oFile = document.getElementById("uploadImage").files[0];
		oFReader.readAsDataURL(oFile);
		function picHidden() {
			document.getElementById("head").style.display = "none";
		}
		function picShow() {
			document.getElementById("head").style.display = "block";
		}
	}
</script>
<link href="bootstrap/css/interaction.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/jsp/public/style.jsp" flush="true"></jsp:include>
</head>
<body>
	<!-- 导航栏 -->
	<%@ include file="/WEB-INF/jsp/public/nav.jsp"%>
	<div class="container" style="background-color: #EAD3A7;">
		<div class="row">
			<div class="span12" style="padding-top: 10px;">
				<div class="span2"
					style="width: 600px; float: left; margin-left: 100px;">
					<s:if test="#user.id==#session.user.id">
						<h3>我的个人空间</h3>
					</s:if>
					<s:else>
						<h3>${user.name }的个人空间</h3>
					</s:else>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="span9">
				<div style="margin-top: 10%; margin-left: 200px">
					<table class="table">
						<tr>
							<td><font color="#003399">昵称：</font></td>
							<td>${user.name}</td>
						</tr>

						<tr>
							<td><font color="#003399">生日：</font></td>
							<td><s:date name="#user.birthday" format="yyyy-MM-dd" ></s:date></td>
						</tr>
						<tr>
							<td><font color="#003399">学历：</font></td>
							<td>${user.education}</td>
						</tr>

						<tr>
							<td><font color="#003399">邮箱：</font></td>
							<td>${user.email}</td>
						</tr>

						<tr>
							<td><font color="#003399">电话：</font></td>
							<td>${user.telephone}</td>
						</tr>

						<tr>
							<td><font color="#003399">QQ：</font></td>
							<td>${user.qq}</td>
						</tr>

						<tr>
							<td><font color="#003399">注册时间：</font></td>
							<td><s:date name="#user.registTime" format="yyyy-MM-dd HH:mm:ss"></s:date></td>
						</tr>
						<tr>
							<td><font color="#003399">爱好：</font></td>
							<td>${user.usTags}</td>
						</tr>
						
							<tr>
							<td><font color="#003399">自我描述：</font></td>
							<td><s:if test="#user.description==null">这家伙很懒，什么都没有留下~</s:if>
							<s:else>${user.description}</s:else></td>
						    </tr>
					</table>
				</div>
			</div>
				<div style="margin-top: 1%; margin-left: 80%">
					<table class="table">
						<tr>
							<td>
								<ul class="grid">
									<li class="cs-style-3" style="width: 180px; height: 180px">
										<figure> <s:if test="#user.headImage==null">
											<div>
												<img
													src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif"
													alt="">
											</div>
										</s:if> <s:else>
											<div>
												<img src="${user.headImage }" alt="">
											</div>
										</s:else> <s:if test="#user.id==#session.user.id">
											<figcaption> <img data-toggle="modal"
												data-target="#myModal" id="head" class="img-circle"
												src="${pageContext.request.contextPath}/style/images/pic.jpg">
											</figcaption>
										</s:if> </figure>
									</li>
								</ul> <!-- <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">上传头像</button> -->

								<!-- 上傳圖像彈出框 -->
								<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
									aria-labelledby="myModalLabel" aria-hidden="true"
									style="width: 800px; top: 30%; left: 20%; height: 500px">
									<div class="modal-content">
										<s:form action="upload" enctype="multipart/form-data"
											method="post">

											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<h4 class="modal-title" id="myModalLabel">上传图片</h4>
												<input id="uploadImage" type="file" name="imgFile"
													onchange='viewPic();' />
												<s:submit value="提交"></s:submit>
											</div>
										</s:form>


										<div class="modal-body">
											<img id="uploadPreview" src="" width="180px" height="180px" />
										</div>

										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">关闭</button>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td>关注:${user.attentionCount }<br> 粉丝:${user.fansCount }<br>
							浏览次数：${user.zoneVisitCount }
							</td>
						</tr>
						<tr>
							<td>资源上传数量：${user.resUpCount}</td>
						</tr>
						<s:if test="#user.id==#session.user.id">
							<tr>
								<td colspan="2"><s:a action="information_editUI?id=%{id}">
										<button class="btn btn-default">修改个人资料</button>
									</s:a> <s:a action="information_changePasswordUI?id=%{id}">
										<button class="btn btn-default">修改密码</button>
									</s:a></td>
							</tr>
							</s:if>
					</table>
					<s:if test="#user.id==#session.user.id">
					 <div class="member-list">
					<h5 style="font-size: 18px;"><a style="color: #E8061E;" href="information_index.action?">
													我的主题</a>
												</h5>
												<h5 style="font-size: 18px;"><a style="color: #E8061E;" href="myresource_myRes.action?">
													我的资源</a>
												</h5>
												<h5 style="font-size: 18px;"><a style="color: #E8061E;" href="activity_myAct.action?">
													我的活动</a>
												</h5>
				       </div>
					<p>根据爱好推荐的活动</p>
					</s:if>
					<s:else><div align="center">
					<s:a action="user_addAttention?friendId=%{#user.id}" onclick="show();">+加关注</s:a>
					</div></s:else>
				    <div class="member-list">
				
					<s:iterator value="activitiesList">
					<h5 style="font-size: 18px;"><a style="color: #E8061E;" href="activity_activityDetails.action?idd=${id}">
												<s:if test="%{activityName.length() >= 15}">
													${activityName.substring(0,10) }...
													</s:if>
													<s:else>
													${activityName }
													</s:else>
													</a>
												</h5>
					</s:iterator>
				</div>
				
				<s:if test="#user.id==#session.user.id"><p>根据爱好推荐的话题</p></s:if>
				<div class="member-list">
				
					<s:iterator value="topicsList">
					<h5 style="font-size: 18px;"><a style="color: #E8061E;" href="topic_show.action?id=${id}">
												<s:if test="%{title.length() >= 15}">
													${title.substring(0,10) }...
													</s:if>
													<s:else>
													${title }
													</s:else>
													</a>
												</h5>
					</s:iterator>
				</div>
				</div>
		</div>
	</div>
	<!-- 基本資料 -->

	<!-- 页脚 -->
<%@ include file="/WEB-INF/jsp/public/footer.jsp"%>
</body>
</html>
