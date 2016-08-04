<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>说说阅读</title>
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

</head>
<body>
<%@ include file="/WEB-INF/jsp/public/nav.jsp" %>
<div class="panel panel-default" >
	<div class="panel-heading" >
		<ol class="breadcrumb" >
		  <li><s:a action="talk_list">说说首页</s:a></li>
		  <li><s:a action="talk_show?id=%{#talk.id}">${talk.name}</s:a> </li>
		  <li>说说阅读</li>
		</ol>
	</div>
	<div class="panel-body" >
		<div class="panel panel-default">
	
			<div class="panel-heading">
			
				<table align="center">
					<tr>
						<td>
					  	<h1>${talk.content}</h1>
					  	</td>
					  	<td>
						  	<ul style="list-style-type: none;" >
							</ul>
								浏览次数：${talk.count }
						</td>
				  	</tr>
					</table>
			</div>
		</div>
		
		<div>
		<s:if test="currentPage == 1">
			<table>
				<tr>
					<td>
						<!--作者头像-->
						<div>
							<img border="0" width="110" height="110"
								src="${talk.user.headImage}"
								/>
						</div> 
					</td>
					<td>
						<div>
							<a id="showInfo" onclick="">${ talk.user.name}</a>
							<button class="btn btn-default" data-toggle="modal" data-target="#sendMsgs">发私信</button><br>
							关注-${ topic.user.attentionCount}<br>
							粉丝-${ topic.user.fansCount}<br>
						</div> 
					</td>
				</tr>
				<tr>
					<!--显示楼层等信息-->
					<td><font color=#C30000>[楼主]</font>  <s:date name="postTime"/> </td>
				</tr>
			</table>
			</s:if>
			</div>
			
		<table>
			<s:iterator value="recordList" status="status">
				<tr>
					<td>
						<!--作者头像-->
							<img border="0" width="110" height="110"
								src="${user.headImage}"/>
					</td>
					<td>
							回复说说：${content}<br>
							【${user.name }】 说:${content}
							<s:a action="talkreply_addUI?talkId=%{#talk.id}&id=%{id}"><button class="btn btn-default">回复</button></s:a>
							<s:a action="talkreply_delete?id=%{id}&talkId=%{#talk.id}" onClick="return confirm('确定要删除本条回复吗？')"><button type="submit" class="btn btn-default">删除</button></s:a><br>
							
							<!-- 子回复 -->
							<s:iterator value="childrens">
								${user.name} 对${parent.user.name} 说 :${content} 
								<s:a action="talk_addUI?talkId=%{#talk.id}&id=%{id}"><button class="btn btn-default">回复</button></s:a>
								<s:a action="talk_delete?id=%{id}&talkId=%{#talk.id}" onClick="return confirm('确定要删除本条回复吗？')"><button type="submit" class="btn btn-default">删除</button></s:a><br>
									<s:iterator value="childrens">
										${user.name} 对${parent.user.name} 说 :${content} 
										<s:a action="talk_addUI?talkId=%{#talk.id}&id=%{id}"><button class="btn btn-default">回复</button></s:a>
										<s:a action="talk_delete?id=%{id}&talkId=%{#talk.id}" onClick="return confirm('确定要删除本条回复吗？')"><button type="submit" class="btn btn-default">删除</button></s:a><br>
											<s:iterator value="childrens">
												${user.name} 对${parent.user.name} 说 :${content} 
												<s:a action="talk_addUI?talkId=%{#talk.id}&id=%{id}"><button class="btn btn-default">回复</button></s:a>
												<s:a action="talk_delete?id=%{id}&talkId=%{#talk.id}" onClick="return confirm('确定要删除本条回复吗？')"><button type="submit" class="btn btn-default">删除</button></s:a><br>
													<s:iterator value="childrens">
														${user.name} 对${parent.user.name} 说 :${content} 
														<s:a action="talk_addUI?talkId=%{#talk.id}&id=%{id}"><button class="btn btn-default">回复</button></s:a>
														<s:a action="talk_delete?id=%{id}&talkId=%{#talk.id}" onClick="return confirm('确定要删除本条回复吗？')"><button type="submit" class="btn btn-default">删除</button></s:a><br>
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
		<s:form id="page" action="talk_show?id=%{id}"></s:form>
		
		<!--快速回复-->
		<div>
			<s:if test="#session.user==null">
				<s:a action="talkreply_addUI?talkId=%{#talk.id}"><button class="btn btn-primary btn-lg">回复</button></s:a>
			</s:if>
			<s:else>
			  <!-- Button trigger modal -->
			<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
			 	 快速回复
			
			</button>
			
			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  			<div class="modal-dialog">
	  				<s:form action="talkreply_add">
						<s:hidden name="talkId" value="%{#talk.id}"></s:hidden>
							<div>
								<table> 
										<s:textfield name="title" value="回复：%{#talk.content}" readonly="true" cssStyle="width:600px"/>
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
			
		<%@ include file="/WEB-INF/jsp/public/commons_foot.jspf"%>			
</body>
</html>