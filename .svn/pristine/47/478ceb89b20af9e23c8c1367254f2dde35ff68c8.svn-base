<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<jsp:include page="/WEB-INF/jsp/public/style.jsp" flush="true"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>好友动态</title>
<link rel="stylesheet" type="text/css" href="css/lrtk.css" />
<script src="js/modernizr.custom.js"></script>
<link href="bootstrap/css/interaction.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/jsp/public/style.jsp" flush="true"></jsp:include>
<style>
#footer{position:relative;}
</style>
</head>
<body>
	<!-- 导航栏 -->
	<%@ include file="/WEB-INF/jsp/public/nav.jsp"%>
	<div class="container" style="background-color: #EAD3A7;">
		<div class="row">
			<div class="span12" style="padding-top: 10px;">
				<div class="span2"
					style="width: 200px; float: left; margin-left: 100px;">
						<h3>好友动态</h3>
				</div>
			</div>
		</div>
		<div class="row">
			 <div >
			 <s:if test="#talkList.isEmpty()">
							<p>还没有好友发布动态呢</p>
						</s:if>
						<s:else>
					<input id="userforTest" name="${currentuser }" type="hidden">
					<s:iterator value="#talkList" status="talk">
					<div class="media" style="margin-top: 0px;margin-bottom: 20px;">
						<div class="media" style="margin-left: 300px;">
							<a href="information_list.action?visitedId=${user.id }" class="pull-left"> <img alt="网络不佳，无发显示呢"
								width="100px" height="100px" src="${user.headImage }" />
							</a>
							<div class="media-body">
							    <div>
									<strong class="media-heading">${user.name }</strong>
									<s:date format="yyyy-MM-dd hh:mm:ss" name="%{postTime }"></s:date>
								</div>
								<s:if test="%{content.length() >= 30}">
													${content.substring(0,30) }...<br>
									<a data-toggle="modal" data-target="#myModal" class="jq_link" rel="${content }" >查看全文</a>
								</s:if>
								<s:else>
									${content }
							</s:else>
						  </div>
						</div>
						<p align="center" id="${id }">
						
						<s:if test="users.contains(user)"><a href='#' rel="${id }" onclick='removezan(this)'>取消点赞</a>&nbsp;</s:if>
						<s:else>
							<a href="#" rel="${id }" onclick="addzan(this)">赞</a> &nbsp;&nbsp;
						</s:else>
						<a  href="#" rel="${id }"  data-toggle="modal" data-target="#myModalpin" 
									class="jq_link2">评论</a>&nbsp;
						</p>
						
						<div id="${id }zan" style="margin-left: 330px;">
						<div style="float:left">点赞的：</div>
						<s:if test="%{users.isEmpty()}"></s:if>
						<s:else>
										
						<s:iterator value="users" status="talk">
						<div style="float:left">${name }&nbsp;</div>
						</s:iterator>
						</s:else>
						</div><br>
						<div id="${id }pinlin" style="margin-left: 330px;">	
						<s:iterator value="talkReplys" status="talk">
						<div id="${id }pinlin" >						
						${name }:&nbsp;${content }
						</div>
						</s:iterator>
						</div>
					</div>						
					</s:iterator>
				</s:else>			 
		</div>					
	</div>
	<!-- 基本資料 -->
<!-- 用于增加和修改的模态对话框 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <!-- 标题 -->
		      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		        <h4 class="modal-title" id="myModalLabel">查看全文</h4>
		      </div>
		      <!-- 内容 -->
		      <div class="modal-body" style="text-align:center;">
		      <div id="content">		       
		      </div>
		      </div>
		      <!-- 操作 -->
		    </div>
		  </div>
		</div>
		<div class="modal fade" id="myModalpin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <!-- 标题 -->
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">评论</h4>
		      </div>
		      <!-- 内容 -->
		      <div class="modal-body" style="text-align:center;">
		        	<div class="well">
		        		<div class="input-group">
					      <div class="input-group-addon">内容</div>
					      <input name="content" type="text" style="width:200px;height:350px" class="form-control" id="content_add" placeholder="Amount">
					    </div>
		        	</div>
		      </div>
		      <!-- 操作 -->
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <a type="button" id="content_addId" class="btn btn-primary"  data-dismiss="modal" onclick="addReply(this)">保存</a>
		      </div>
		    </div>
		  </div>
		</div>
		</div>
<!-- 页脚 -->
<div style="height:30px"></div>
<%@ include file="/WEB-INF/jsp/public/footer.jsp" %>
<script>            
function jsDateDiff(publishTime){       
    var d_minutes,d_hours,d_days;       
    var timeNow = parseInt(new Date().getTime()/1000);       
    var d;       
    d = timeNow - publishTime;       
    d_days = parseInt(d/86400);       
    d_hours = parseInt(d/3600);       
    d_minutes = parseInt(d/60);       
    if(d_days>0 && d_days<4){       
        return d_days+"天前";       
    }else if(d_days<=0 && d_hours>0){       
        return d_hours+"小时前";       
    }else if(d_hours<=0 && d_minutes>0){       
        return d_minutes+"分钟前";       
    }else{       
        var s = new Date(publishTime*1000);       
        // s.getFullYear()+"年";
        return (s.getMonth()+1)+"月"+s.getDate()+"日";       
    }       
}       
$(document).ready(function() {
	  $(".jq_link").click(function() {
	    var id = $(this).attr('rel');	    
	    $("#content").html("<p>"+id+"</p>");
	    $("#Modal").modal();
	  });
	  $(".jq_link2").click(function() {
		    var id = $(this).attr('rel');	    
		    $("#content_addId").attr('rel',id);
		    $("#Modal").modal();
		  });
	});
function addzan(obj) {
	alert($("#userforTest").attr('name'));
	$.ajax({
		type : "post",
		url : "social_addzan.action",
		data : {
			talkId :  $(obj).attr('rel')
			
		},
		success : function(response) {
				 $("#"+response)
						.html("<a href='#' rel="+response+" onclick='removezan(this)'>取消点赞</a>&nbsp;")
						.append("<a data-toggle='modal' data-target='#myModalpin' class='jq_link' href='#' rel="+response+" >评论</a>&nbsp;"); 
				 $("#"+response+"zan")
					.append("<div id='myremove' style='float:left;'>${user.name}&nbsp;</div>")
                   }
	});
	};
	function removezan(obj) {
		$.ajax({
			type : "post",
			url : "social_removezan.action",
			data : {
				talkId : $(obj).attr('rel')
			},
			success : function(response) {
				$("#"+response)
				.html("<a href='#' rel="+response+" onclick='addzan(this)'>赞</a>&nbsp;")
				.append("<a data-toggle='modal' data-target='#myModalpin' class='jq_link' href='#' rel="+response+" >评论</a>&nbsp;"); 
				$("#myremove").remove();
			}
		});
		};
		function addReply(obj) {
			$("#"+$(obj).attr('rel')+"pinlin").html("<p><font color='red'>${user.name }</font>&nbsp;:&nbsp;"+$("#content_add").val()+"</p>");
			$.ajax({
				type : "post",
				url : "talkReply_addReply.action",
				data : {
					talkId : $(obj).attr('rel'),
					divcontext:$("#content_add").val()
				},
				success : function(response) {
					window.location.href="social_list.action";        
				}
			});
};
</script>
</body>
</html>