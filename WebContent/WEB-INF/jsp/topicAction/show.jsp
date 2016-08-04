<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帖子阅读</title>
<script type="text/jscript" src="ckplayer/ckplayer.js"></script>
<link rel="stylesheet" href="kindeditor/themes/default/default.css" />
<script charset="utf-8" type="text/javascript" language="javascript"
	src="kindeditor/kindeditor-reply.js"></script>
<script charset="utf-8" type="text/javascript" language="javascript"
	src="kindeditor/lang/zh_CN.js"></script>
<script>
	KindEditor.ready(function(K) {
		K.create('textarea[name="content"]', {
			uploadJson : 'kindeditor/jsp/upload_json.jsp',
			fileManagerJson : 'kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			allowImageUpload : true,
			autoHeightMode : true,
			afterCreate : function() {
				this.loadPlugin('autoheight');
			},
			afterBlur : function() {
				this.sync();
			} //Kindeditor下获取文本框信息
		});
	});
	
	
</script>
<style type="text/css">
	.backToTop {
	 display: none;
	 width: 18px;
	 line-height: 1.2;
	 padding: 5px 0;
	 background-color: #000;
	 color: #fff;
	 font-size: 12px;
	 text-align: center;
	 position: fixed;
	 _position: absolute;
	 right: 10px;
	 bottom: 100px;
	 _bottom: "auto";
	 cursor: pointer;
	 opacity: .6;
	 filter: Alpha(opacity=60);
}
</style>
 <div></div>
<script type="text/javascript">
	(function() {
	    var $backToTopTxt = "返回顶部", $backToTopEle = $('<div class="backToTop"></div>').appendTo($("body"))
	        .text($backToTopTxt).attr("title", $backToTopTxt).click(function() {
	            $("html, body").animate({ scrollTop: 0 }, 120);
	    }), $backToTopFun = function() {
	        var st = $(document).scrollTop(), winh = $(window).height();
	        (st > 0)? $backToTopEle.show(): $backToTopEle.hide();
	        //IE6下的定位
	        if (!window.XMLHttpRequest) {
	            $backToTopEle.css("top", st + winh - 166);
	        }
	    };
	    $(window).bind("scroll", $backToTopFun);
	    $(function() { $backToTopFun(); });
	})();
</script>

<style type="text/css">
#a1 {
	height: 400px;
	width: 600px;
	float: left;
}
</style>



</head>
<body>
<%@ include file="/WEB-INF/jsp/public/nav.jsp" %>
<div class="panel panel-default" >
	<div class="panel-heading" >
		<ol class="breadcrumb" >
		  <li><s:a action="forum_index">首页</s:a></li>
		  <li><s:a action="forum_list">版块列表</s:a></li>
		  <li><s:a action="forum_show?id=%{#topic.forum.id}">${ topic.forum.name}</s:a> </li>
		  <li>帖子阅读</li>
		</ol>
	</div>
	<div class="container">
	<div class="panel-body" >
		<div class="panel panel-default">
	
			<div class="panel-heading">
			
				<table align="center">
					<tr>
						<td>
					  	<h1>${topic.title }</h1>
					  	</td>
					  	<td>
						  	<ul style="list-style-type: none;" >
								<!--操作列表-->
								<li class="TopicFuncLi">
								<s:a action="topic_moveUI?id=%{id}&forumId=%{#topic.forum.id}">
								<img border="0" src="${pageContext.request.contextPath}/style/images/edit.gif" />移动到其他版块</s:a>
								
								<s:a action="topic_setBest?id=%{id}&forumId=%{#topic.forum.id}" onClick="return confirm('要把本主题设为精华吗？')">
								<img border="0" src="${pageContext.request.contextPath}/style/images/forum_hot.gif" />精华</s:a>
								
								<s:a action="topic_setTop?id=%{id}&forumId=%{#topic.forum.id}" onClick="return confirm('要把本主题设为置顶吗？')">
								<img border="0" src="${pageContext.request.contextPath}/style/images/forum_top.gif" />置顶</s:a>
								
								<s:a action="topic_setNormal?id=%{id}&forumId=%{#topic.forum.id}" onClick="return confirm('要把本主题设为普通吗？')">
								<img border="0" src="${pageContext.request.contextPath}/style/images/forum_comm.gif" />普通</s:a>
								
								<s:a action="topic_setCollection?id=%{id}&forumId=%{#topic.forum.id}" onClick="return confirm('要把本主题收藏吗？')">
								<img border="0" src="${pageContext.request.contextPath}/style/images/save.gif" />收藏</s:a>
								
								<s:a action="topic_delete?id=%{id}&forumId=%{#topic.forum.id}" onClick="return confirm('确定要删除本帖吗？')">删除</s:a>
								</li>
							</ul>
								浏览次数：${topic.count }
						</td>
				  	</tr>
					</table>
			</div>
		
			<div class="panel-body">
		  		<div class="panel-body">
		  			<!-- 内容 -->
		  			<s:if test="#topic.forum.name=='视频专区'">
						<div id="a1"></div>
						<script type="text/javascript">
							var flashvars={
								f:'<s:property value='#topic.content'/>',
								c:0,
								b:1
								};
							var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
							CKobject.embedSWF('ckplayer/ckplayer.swf','a1','ckplayer_a1','600','400',flashvars,params);
					  </script>
					  
						
		  			</s:if>
		  			
		  			<s:else>
		  			
		  			<div align="left" style="float: left;">${topic.content }</div>
		  			</s:else>
		  			
		  			
		  			
		  			<!-- 收藏此帖的用户 -->
		  			<div align="right" style="  position: inherit;">
		  				<table class="table table-striped" style="width: 150px">
		  					<tr><td>收藏此帖的用户</td></tr>
		  					<s:if test="#topic.users == null">
			  					<tr><td>无</td></tr>
		  					</s:if>
		  					
		  					<s:else>
			  					<s:iterator value="#topic.users">
				  					<tr>
				  						<td><a href=""><img  border="0" width="50" height="50" src="${headImage}">${name }</a></td>
				  					</tr>
			  					</s:iterator>
			  				</s:else>
		  				</table>
		  			
		  			
		  			
		  			</div>
		  		</div>
			</div>
			
		</div>
		
		<!-- 上一篇 下一篇 -->
		<div>
				 <s:if test="#topic.forum.topicCount==1">
				
				</s:if>
				<!-- 如果是第一页 -->
				<s:elseif test="#topic.id==#beforeTopic.id">
					<s:iterator value="#nextTopic">
						<div><s:a action="topic_show?id=%{id}"><h3> 下一篇:${title }</h3></s:a></div>
					</s:iterator>
				</s:elseif>
				
				<!--如果是最后一页 -->
				<s:elseif test="#topic.id==#nextTopic.id">
					<s:iterator value="#beforeTopic">
						<div><s:a action="topic_show?id=%{id}"><h3>上一篇${title }</h3></s:a></div>
					</s:iterator>
				</s:elseif>
				
				<s:else>	 
					<s:iterator value="#beforeTopic">
						<div><s:a action="topic_show?id=%{id}"><h3>上一篇${title }</h3></s:a></div>
					</s:iterator>
					
					<s:iterator value="#nextTopic">
						<div><s:a action="topic_show?id=%{id}"><h3>下一篇${title }</h3></s:a></div>
					</s:iterator>
					
				 </s:else> 
		</div>
		
		<div>
		<s:if test="currentPage == 1">
			<table>
				<tr>
					<td>
						<!--作者头像-->
						<div>
							<img border="0" width="110" height="110"
								src="${topic.user.headImage}"
								/>
						</div> 
					</td>
					<td>
						<div>
							<a id="showInfo" onclick="">${ topic.user.name}</a>
							<button class="btn btn-default" data-toggle="modal" data-target="#sendMsgs">发私信</button><br>
							关注-${ topic.user.attentionCount}<br>
							粉丝-${ topic.user.fansCount}<br>
						</div> 
						
						<!-- Modal -->
					<div class="modal fade" id="sendMsgs" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  			<div class="modal-dialog">
			  				<s:form action="message_add">
								<s:hidden name="friendId" value="%{#topic.user.id}"></s:hidden>
									<div>
										<table> 
												<s:textfield value="发私信给：%{#topic.user.name}" readonly="true" cssStyle="width:600px"/>
												<s:textarea
														name="content" cssStyle="width: 100%; height: 100px;"></s:textarea>
												 <!-- Button trigger modal -->
											
										</table>
												<button type="submit" class="btn btn-default">发送</button>
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									</div>
							</s:form>
						</div>
					</div>
						
						
						
					</td>
					
				</tr>
				<tr>
					<td>
						<s:if test="#session.user.id==#topic.user.id">
						
						</s:if>
						<s:else> 
						<s:iterator value="#user.friends"></s:iterator>
							<s:if test="id==#topic.user.id">
								已关注
							</s:if>
							
							
							<s:else>
								<font color=#C30000>
								<s:a action="user_addAttention?friendId=%{#topic.user.id}">+加关注</s:a>
								</font>
							</s:else>
						 </s:else> 
					</td>
				</tr>
				<tr>
					<!--显示楼层等信息-->
					<td><font color=#C30000>[楼主]</font>  <s:date name="postTime"/> </td>
				</tr>
			</table>
			</s:if>
			</div>
			
		<!-- ~~~~~~~~~~~~~~~ 显示主帖 ~~~~~~~~~~~~~~~ -->
		<table>
		
			<s:iterator value="recordList" status="status">
				<tr>
					<td>
						<!--作者头像-->
							<img border="0" width="110" height="110"
								src="${user.headImage}"
								 />
					</td>
					<td>
							<!-- 文章标题 -->
							<%-- 回复主题：${title}<br> --%>
							【${user.name }】 说:${content}
							<s:a action="reply_addUI?topicId=%{#topic.id}&id=%{id}">回复</s:a>
							<s:a action="reply_delete?id=%{id}&topicId=%{#topic.id}" onClick="return confirm('确定要删除本条回复吗？')">删除</s:a><br>
							
							<!-- 子回复 -->
							<s:iterator value="childrens">
									【${user.name}】 回复 【${parent.user.name}】 : ${content}
								<s:a action="reply_addUI?topicId=%{#topic.id}&id=%{id}">回复</s:a>
								<s:a action="reply_delete?id=%{id}&topicId=%{#topic.id}" onClick="return confirm('确定要删除本条回复吗？')">删除</s:a><br>
									<s:iterator value="childrens">
										【${user.name}】 回复 【${parent.user.name}】 : ${content}
										<s:a action="reply_addUI?topicId=%{#topic.id}&id=%{id}"><button class="btn btn-default">回复</button></s:a>
										<s:a action="reply_delete?id=%{id}&topicId=%{#topic.id}" onClick="return confirm('确定要删除本条回复吗？')"><button type="submit" class="btn btn-default">删除</button></s:a><br>
											<s:iterator value="childrens">
													【${user.name}】 回复 【${parent.user.name}】 : ${content}
												<s:a action="reply_addUI?topicId=%{#topic.id}&id=%{id}"><button class="btn btn-default">回复</button></s:a>
												<s:a action="reply_delete?id=%{id}&topicId=%{#topic.id}" onClick="return confirm('确定要删除本条回复吗？')"><button type="submit" class="btn btn-default">删除</button></s:a><br>
													<s:iterator value="childrens">
															【${user.name}】 回复 【${parent.user.name}】 : ${content}
														<s:a action="reply_addUI?topicId=%{#topic.id}&id=%{id}"><button class="btn btn-default">回复</button></s:a>
														<s:a action="reply_delete?id=%{id}&topicId=%{#topic.id}" onClick="return confirm('确定要删除本条回复吗？')"><button type="submit" class="btn btn-default">删除</button></s:a><br>
													</s:iterator>
											
											</s:iterator>
									
									</s:iterator>
							</s:iterator>
						</td>
				</tr>
				
				<tr>
					<td >
					【${user.name }】 
					</td>
				</tr>
				<tr>
					<!--显示楼层等信息-->
					<td >
							<font color=#C30000>[${(currentPage - 1) * pageSize + status.count}楼]</font>
							 <s:date name="postTime"/> 
					</td>
				</tr>
			</s:iterator>
		</table>
		<!-- ~~~~~~~~~~~~~~~ 显示回复列表结束 ~~~~~~~~~~~~~~~ -->
		
		<!--分页信息-->
		<%@ include file="/WEB-INF/jsp/public/pageView.jspf"%>
		<s:form id="page" action="topic_show?id=%{id}"></s:form>
		
		<!--快速回复-->
		<div>
			<s:if test="#session.user==null">
				<s:a action="reply_addUI?topicId=%{#topic.id}"><button class="btn btn-primary btn-lg">回复</button></s:a>
			</s:if>
			<s:else>
			  <!-- Button trigger modal -->
			<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
			 	 快速回复
			
			</button>
			
			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  			<div class="modal-dialog">
	  				<s:form action="reply_add">
						<s:hidden name="topicId" value="%{#topic.id}"></s:hidden>
							<div>
								<table> 
										<s:textfield name="title" value="回复：%{#topic.title}" readonly="true" cssStyle="width:600px"/>
										<s:textarea
												name="content" cssStyle="width: 100%; height: 100px;"></s:textarea>
										 <!-- Button trigger modal -->
									
								</table>
										<button type="submit" class="btn btn-default">回复</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>
					</s:form>
				</div>
			</div>
			</s:else>
		</div>
			<div style="margin-bottom: 200px;">	
				<hr></hr>
			</div>	
	</div>
	</div>
</div>
			
		<%@ include file="/WEB-INF/jsp/public/commons_foot.jspf"%>			
</body>
</html>