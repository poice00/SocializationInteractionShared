<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
#footer{position:relative;}
</style>
<title>${name }</title>
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
				<div class="row">
					<h4 class="span3">活动相册-${name }</h4>
					<div style="padding-top: 10px;" class="pull-right">
						<!-- <a href="#uploadModal" data-toggle="modal" data-target="#uploadModal">上传照片</a> -->
						<button class="btn btn-primary" data-toggle="modal" data-target="#uploadModal">
								<span class="glyphicon glyphicon-plus"></span> 上传照片
					    </button>
					</div>
					<!-- Modal -->
					<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: block; padding-left: 17px;margin-left: 270px;height: 302px;margin-top: 200px;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true" style="font-size:40px;">×</button>
							<h3 id="myModalLabel">上传照片</h3>
						</div>
						
						<s:form action="actAlbum_photoadd.action" method="post" enctype="multipart/form-data">
						<div class="modal-body">
							<input type="hidden" name="idd" value=${id }>
							<input type="file" name="files" multiple="multiple" accept="image/*" />
						</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
							<button type="submit" class="btn btn-primary">提交</button>
						</div>
						</s:form>
					</div>
				</div>
				<p>相册描述：${description }</p>
				<s:if test="#actAlbumPhotosList.isEmpty()">
						<p>还没有照片呢</p>
				    </s:if>
				    <s:else>
				    <div style="margin-top: 20px;">
				   <s:iterator value="#actAlbumPhotosList" status="actAlbumPhoto">
						<div class="thumbnail" style="float:left;width: 270px;height: 230px;"">
										<a href="actAlbum_ShowalbumPhoto.action?photoId=${id}&idd=${actAlbum.id}"><img
											alt="${name }" style="height: 180px;" src="${photoUrl }" /></a>
										<s:if test="%{name.length() >= 25}">
													<p align="center" style="margin-bottom: 0px">${name.substring(0,25) }...</p>
													</s:if>
													<s:else>
													<p align="center" style="margin-bottom: 0px">${name }</p>
													</s:else>	
											
										
										<div >
										 <p align="center">${description }</p>
										</div>
						</div>
				     </s:iterator>
				     </div>
				   </s:else>
				<hr>
			</div>
			<div class="span3" style="margin-left: 70px;width: 200px;margin-top: 100px;">
				<p>
					<a href="activity_activityDetails.action?idd=${activity.id }">返回活动</a>
				</p>
				<p>哎，还没想好放什么好</p>
			</div>
		</div>
	</div>
<!-- 页脚 -->
<%@ include file="/WEB-INF/jsp/public/footer.jsp" %>
<script>
	$().ready(function() {
		$("#uploadModal").hide();
		$("#myModalLabel").click(function() {
			$("#uploadModal").slideToggle("slow");
		});
	});
</script>
</body>
</html>