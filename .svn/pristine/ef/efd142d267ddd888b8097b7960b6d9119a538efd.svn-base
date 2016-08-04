<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>高级搜索界面</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/interaction.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<jsp:include page="/WEB-INF/jsp/public/style.jsp" flush="true"></jsp:include>
<style type="text/css"> 
.input1 {margin-bottom: 0px;height: 300px;}
</style> 
</head>
<body>
<!-- 导航栏 -->
<%@ include file="/WEB-INF/jsp/public/nav.jsp" %>
<div class="container">
<%@ include file="/WEB-INF/jsp/resourceAction/common.jsp" %>	
		<%-- <div class="row">
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
		</div> --%>
		<div class="row" style="margin-top: 20px;">
			<div class="span9" style="float:left;margin-left: 160px;width: 600px;">
				<div class="row" >
					<s:form action="resource_highsearch" method="get">
						<table border="0" cellspacing="0" cellpadding="0">
							<colgroup>
								<col width="330" />
								<col />
							</colgroup>
							<tr style="height: 50px;">
								<td  colspan="2" style="text-align: center;font-size: 18px;">${ResTvalue }</td>
							</tr>
							<tr style="height: 50px;">
								<th  style="text-align: right;font-size: 18px;">标题中包含：</th>
								<td><s:textfield name="resname" cssStyle= "height:30px;margin-bottom: 0px;" ></s:textfield></td>
							</tr>
							<tr style="height: 50px;font-size: 18px;">
								<th style="text-align: right;font-size: 18px;">简介中包含：</th>
								<td><s:textfield name="resDescription" cssStyle= "height:30px;margin-bottom: 0px;" ></s:textfield></td>
							</tr>
							<tr style="height: 50px;">
								<th style="text-align: right;font-size: 18px;">Tag中包含：</th>
								<td><s:textfield name="resourceTags" cssStyle= "height:30px;margin-bottom: 0px;"></s:textfield></td>
							</tr>
							<tr style="height: 50px;">
								<th style="text-align: right;font-size: 18px;">分类：</th>
								<td><s:select name="resTypeId" list="#resTypeList"
										listKey="id" listValue="name" headerKey="" headerValue="请选择类型">
									</s:select></td>
							</tr>
							<tr style="height: 50px;">
								<th style="text-align: right;font-size: 18px;">上传时间：</th>
								<td><s:select name="resUpTime"
										list="{'请选择日期','一周以内','一月以内','一年以内'}">
									</s:select></td>
							</tr>
							<tr style="height: 50px;">
								<td>&nbsp;</td>
								<td><input name="" type="image"
									src="resimg/btn_search_high.png" /></td>
							</tr>
						</table>
					</s:form>
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