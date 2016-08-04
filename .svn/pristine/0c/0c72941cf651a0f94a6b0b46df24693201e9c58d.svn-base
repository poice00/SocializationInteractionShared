<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<title>权限管理</title>
		<jsp:include page="/WEB-INF/jsp/public/style.jsp" flush="true"></jsp:include>
		<style>
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
			
			#footer{
				position:relative;
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
					<!-- 角色和权限 -->
					<div class="col-md-8">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">角色</h3>
							</div>
							<div class="panel-body">
								<table class="table table-striped table-hover table-bordered">
									<thead>
										<tr>
											<th>角色ID</th>
											<th>角色名</th>
											<th>角色描述</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="#roleList">
											<s:if test="%{name !=  '游客'}">
												<tr>
													<td>${id }</td>
													<td>${name }</td>
													<td>${description }</td>
													<td style="width:186px;">
														<button class="btn btn-primary btn-xs" data-backdrop="static" data-toggle="modal"
															data-target="#privilege_operation" onClick="add_privilege()">
															<span class="glyphicon glyphicon-plus"></span> 增加
														</button>
														<button class="btn btn-info btn-xs" data-backdrop="static" data-toggle="modal"
															data-target="#privilege_operation" onClick="modify_privilege(this)">
															<span class="glyphicon glyphicon-pencil"></span> 修改
														</button>
														<s:a class="btn btn-danger btn-xs" href="role_delete.action?id=%{id}" onClick="return window.confirm('确定要删除吗？')">
															<span class="glyphicon glyphicon-remove"></span> 删除
														</s:a>
													</td>
												</tr>
											</s:if>
										</s:iterator>
									</tbody>
								</table>
							</div>
							
						</div>
					
						<!-- 权限 -->	
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">权限</h3>
							</div>
							<div class="panel-body">
								<table class="table table-striped table-hover table-bordered">
									<thead>
										<tr>
											<th>序号</th>
											<th>权限ID</th>
											<th>权限名</th>
											<th>URL</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="#privilegeList" status="status">
											<tr>
												<td>${status.count }</td>
												<td>${id }</td>
												<td>${name }</td>
												<td>${url }</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					
					<!-- 分配权限 -->
					<div class="col-md-4">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">赋予权限</h3>
							</div>
							<div class="panel-body">
								<div class="input-group">
							      <div class="input-group-addon">角色</div>
								      <select class="form-control" id="sel_role"
								      	onchange="read_privilege(this.options[this.options.selectedIndex].value)">
								      	<s:iterator value="#roleList">
											<option value="${id }" >${name }</option>
										</s:iterator>
									  </select>
							     </div>
		        			</div>
		        			<div class="panel-footer">
		        				<label class="checkbox" style="font-size:20px; color:red;">
									<input id="fuck" type="checkbox" /> 勾选权限
								</label><br/>
								<s:iterator value="#privilegeList">
									<label class="checkbox">
										<input id="${id }" name="check" type="checkbox" /> ${name }
									</label><br/>
								</s:iterator>
								<br/>
								<button class="btn btn-primary" onclick="update_role_privilege()">更新</button>
		        			</div>
						</div>
					</div>	
					</div>
				</div>
			</div>
		</div>
		
		<!-- 用于增加和修改的模态对话框 -->
		<div class="modal fade" id="privilege_operation" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <!-- 标题 -->
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="title">增加角色</h4>
		      </div>
		      
		      <!-- 内容 -->
		      <div class="modal-body" style="text-align:center;">
		        	<div class="well">
		        		<div class="input-group">
					      <div class="input-group-addon">角色名</div>
					      <input type="text" class="form-control" id="roleName" placeholder="角色名">
					    </div>
		        		<div class="input-group">
					      <div class="input-group-addon">描述</div>
  					      <input type="text" class="form-control" id="desc" placeholder="角色描述">
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