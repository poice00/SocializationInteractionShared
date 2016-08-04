<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">
   a:link,a:visited{text-decoration:none;}
   a:hover{color:orange;text-decoration:underline;}
   .test{color:greed;text-decoration:underline;}
    .subMenu{float:left;padding-top:2px;width:100%;}
    .subMenu li{ display:inline;}
    .subMenu li a{ display:inline-block; color:blue; height:25px;line-height:25px;padding:0 5px;margin-left:2px;}
    .subMenu .thisStyle{background:lightblue;color:red;}
</style>
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
						<li><a href="myresource_showupload.action" id="upload">我要上传</a></li>		
						<s:if test='#session.user !=null'>
						<li><a  href="myresource_myRes.action" style="padding-left: 20px;">+我的资源</a></li>
						</s:if>
					</ul>
				</div>
			</div>
</div>