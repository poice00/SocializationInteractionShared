<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<title>好友管理</title>
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
			
			table{
				margin-top:20px;
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
						<li class="active"><a href="#">好友管理</a></li>
					</ul>
				</div>
				<div class="col-md-9">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">好友管理</h3>
						</div>
						<div class="panel-body">
							<!-- 操作按钮 -->
							<button class="btn btn-primary">
								<span class="glyphicon glyphicon-plus"></span> 增加
							</button>
							<button class="btn btn-danger">
								<span class="glyphicon glyphicon-remove"></span> 删除
							</button>
							
							<!-- 按条件筛选 -->
							<input id="select" type="text" class="form-control pull-right" placeholder="请输入要查询的内容"
								style="display:inline; width:200px;" >		<!-- 样式是有优先级的 -->
							
							<table class="table table-striped table-hover table-bordered">
								<thead>
									<tr >
										<th>
											<label class="checkbox">
												<input type="checkbox"> 序号
											</label>
										</th>
										<th align="center">用户名</th>
										<th align="center">注册时间</th>
										<th align="center">操作</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="#userList" status="status">
										<tr class="data">
											<td>
												<label class="checkbox">
													<input type="checkbox"> ${status.count }
												</label>
											</td>
											<td>${id }</td>
											<td>${loginName } </td>
											<td>${name } </td>
											<td style="width:123px;">
												<s:a class="btn btn-danger btn-xs" href="user_delete.action?id=%{id}" onClick="return window.confirm('确定要删除吗？')">
													<span class="glyphicon glyphicon-remove"></span> 删除
												</s:a>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						<div class="panel-footer" style="text-align:center;">
							<nav>
								<ul class="pager">
									<li class="previous"><a>首页</a></li>
									<li>
										<ul class="pagination">
											<li><a href="#">上一页</a></li>
											<li class="active"><a href="#">1</a></li>
											<li><a href="#">2</a></li>
											<li><a href="#">3</a></li>
											<li><a href="#">4</a></li>
											<li><a href="#">5</a></li>
											<li><a href="#">下一页</a></li>
										</ul>
									</li>
									<li class="next"><a>尾页</a></li>
								</ul>
							</nav>
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
		        <h4 class="modal-title" id="myModalLabel">修改</h4>
		      </div>
		      <!-- 内容 -->
		      <div class="modal-body" style="text-align:center;">
		        	<div class="well">
		        		<div class="input-group">
					      <div class="input-group-addon">用户名</div>
					      <input type="text" class="form-control" id="userName" placeholder="Amount" readonly>
					    </div>
		        		<div class="input-group">
					      <div class="input-group-addon">注册时间</div>
					      <input type="text" class="form-control" id="registTime" placeholder="Amount">
					    </div>
		        	</div>
		      </div>
		      <!-- 操作 -->
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <a type="button" class="btn btn-primary" href="">保存</a>
		      </div>
		    </div>
		  </div>
		</div>
	</body>
</html>