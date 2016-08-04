<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<title>用户管理</title>
		<jsp:include page="/WEB-INF/jsp/public/style.jsp" flush="true"></jsp:include>
		<style>
			#footer{
				position:fixed;
				bottom:0px;
			}
			
			.pager{
				margin:0;
			}
			
			.pagination{
				margin:0;
			}
			
			.input-group{
				margin-top:10px;
			}
			
			.input-group-addon{
				width:80px;
			}
			
			.well{
				padding-left:150px;
			}
			
		</style>
	</head>
	<body>
		<!-- 导航栏 -->
		<%@ include file="/WEB-INF/jsp/public/nav.jsp" %>
		
		<div class="container">
			<div class="row">
				<!-- 侧边栏 -->
				<div class="col-sm-3 col-md-2 sidebar" style="background-color:#ddd;">
					<ul class="nav nav-sidebar">
						<s:if test="#session.navShow.userManage == true">
							<li><a href="user_list.action">用户管理</a></li>
						</s:if>
						<s:if test="#session.navShow.privilegManage == true">
							<li><a href="privilege_list.action">权限管理</a></li>
						</s:if>
						<s:if test="#session.navShow.forumManage == true">
							<li><a href="forumManage_list.action">论坛管理</a></li>
						</s:if>
						<s:if test="#session.navShow.resourceManage == true">
							<li><a href="#">资源管理</a></li>
						</s:if>
						<s:if test="#session.navShow.activityManage == true">
							<li><a href="#">活动管理</a></li>
						</s:if>
					</ul>
				</div>
				<div class="col-md-9">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">用户管理</h3>
						</div>
						<div class="panel-body">
							<!-- 操作按钮 -->
							<button class="btn btn-primary" data-toggle="modal" data-backdrop="static"
								data-target="#myModal" onClick="add()">
								<span class="glyphicon glyphicon-plus"></span> 增加
							</button>
							<button class="btn btn-danger" onClick="batch_delete()">
								<span class="glyphicon glyphicon-remove"></span> 删除
							</button>
							
							<!-- 按条件筛选 -->
							<input id="select" type="text" class="form-control pull-right" placeholder="请输入要查询的内容"
								style="display:inline; width:200px;" onkeyup="fuzzy_search(this)" >		<!-- 样式是有优先级的 -->
							
							<table class="table table-striped table-hover table-bordered">
								<thead>
									<tr >
										<th>
											<label class="checkbox">
												<input id="fuck" type="checkbox" /> 序号
											</label>
										</th>
										<th align="center">用户ID</th>
										<th align="center">用户名</th>
										<th align="center">角色</th>
										<th align="center">注册时间</th>
										<th align="center">操作</th>
									</tr>
								</thead>
								<tbody id="user">
									<s:iterator value="recordList" status="status">
										<tr class="data">
											<td>
												<label class="checkbox">
													<input name="check" value="${id }" type="checkbox"> ${(currentPage - 1) * pageSize + status.count}
												</label>
											</td>
											<td>${id }</td>
											<td>${loginName } </td>
											<!-- 竟然不能有空格，注释也算html里的内容，也是醉了-->
											<td>
												<s:if test="%{role.id == 1}">
													系统管理员
												</s:if>
												<s:elseif test="%{role.id == 2}">
													普通管理员
												</s:elseif>
												<s:else>
													用户
												</s:else>
											</td>
											<td>  <s:date name="registTime"/></td>
											<td style="width:123px;">
												<button class="btn btn-info btn-xs" data-backdrop="static" data-toggle="modal"
													data-target="#myModal" onClick="modify(this)">
													<span class="glyphicon glyphicon-pencil"></span> 修改
												</button>
												<s:a class="btn btn-danger btn-xs" href="user_delete.action?id=%{id}" onClick="return window.confirm('确定要删除吗？')">
													<span class="glyphicon glyphicon-remove"></span> 删除
												</s:a>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						
						<!-- 分页信息 -->
						<div class="panel-footer" style="text-align:center;">
							<!-- <nav>
								<ul class="pager">
									<li class="previous"><a href="user_list.action?page=0">首页</a></li>
									<li>
										<ul class="pagination">
											<li><a href="#">上一页</a></li>
											<li class="active"><a href="#">1</a></li>
											<li><a href="user_list.action?page=1">2</a></li>
											<li><a href="#">3</a></li>
											<li><a href="#">4</a></li>
											<li><a href="#">5</a></li>
											<li><a href="#">下一页</a></li>
										</ul>
									</li>
									<li class="next"><a>尾页</a></li>
								</ul>
							</nav> -->
							
							<%@ include file="/WEB-INF/jsp/public/pageView.jspf"%>
							<s:form id="page" action="user_list?id=%{id}"></s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 用于增加和修改的模态对话框 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <!-- 标题 -->
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">修改用户角色</h4>
		      </div>
		      
		      <!-- 内容 -->
		      <div class="modal-body" style="text-align:center;">
		        	<div class="well">
		        		<div class="input-group">
					      <div class="input-group-addon">用户名</div>
					      <input type="text" class="form-control" id="userName" placeholder="用户名" readonly>
					    </div>
		        		<div class="input-group">
					      <div class="input-group-addon">角色</div>
						      <select class="form-control" id="sel_role" style="width:177px;">
								<option value="系统管理员">系统管理员</option>
								<option value="普通管理员">普通管理员</option>
								<option value="用户">用户</option>
							  </select>
					     </div>
		        	</div>
		      </div>
		      
		      <!-- 操作 -->
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <a id="save" type="button" class="btn btn-primary" href="#">保存</a>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- 页脚 -->
		<%@ include file="/WEB-INF/jsp/public/footer.jsp" %>
		
	</body>
</html>