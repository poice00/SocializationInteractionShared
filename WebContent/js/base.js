
/**
 * 网站前端控制、验证等基础逻辑的实现
 * @author yanbaobin@yeah.net
 */

/*======================登录和注册======================*/

$().ready(function(){
	/*初始化所有弹出框*/
	$(function () {
		  $('[data-toggle="popover"]').popover()
	});
	
	
	/*判断用户是否已登录*/
	$.ajax({
		url:'user_getUser.action',
		type:'get',
		success:function(response){
			if(response != "")
				update(response);
			else
				$("#sign").show();
		}
	});
	
	/*获取系统管理员的权限*/
	read_privilege($("#sel_role").val());
});

/**
 * 用户已登录的情况下，执行以下操作
 * 隐藏登录面板
 * 修改头像路径
 * 修改显示的用户名
 */
function update(nick_name){
	$("#img_user").attr("src","img/user/" + nick_name + ".jpg");
	$("#nick_name").html("<b>" + nick_name + "</b><span class='caret'></span>");
	$("#info").show();
}

/**
 * 登录和注册面板的切换
 * 把显示的隐藏，隐藏的显示
 */
function toggle(){
	if(document.getElementById("up").style.display != "block"){
		$("#in").hide();
		
		createCaptcha();		//生成验证码
		$("#up").show();
	}
	else{
		//$("#up").style.display = "none";
		//$("#in").style.display = "block";
		$("#up").hide();
		$("#in").show("slow");
	}
}

var isNameMeet = false;		//标记用户所填写的用户名是否否符合要求
var isPwdMeet = false;		//标记用户所填写的密码否符合要求
var isCaptchaMeet = false;		//标记验证码是否正确

/**
 * 验证用户名
 * 1.非空
 * 2.不符合规则
 * 3.已被注册
 */
function validateName(){
	var user_name = $("#up_user_name").val();
	var error = "";
	if(user_name == ""){
		error = "用户名不能为空";
		isNameMeet = false;
	}
	else if(!(/^[a-zA-Z]{1}[\w]{4,19}$/).test(user_name)){
		error = "不符合要求";
		isNameMeet = false;
	}
	/*判断用户名是否被注册*/
	else{
		$.ajax({
			url:'user_isExist.action',
			data:{
				loginName:user_name
			},
			type:'post',
			async:false,	//默认为true，这里需要改为false
			success:function(response){
				if(response == "用户名已被注册"){
					error = response;
					isNameMeet = false;
				}
			}
		});
	}
	
	if(error != ""){
		//顺序不能颠倒
		$("#up_user_name").popover("show");
		$("#popover_regist_name").html(error);
	}
	else{
		$("#up_user_name").popover("hide");
		isNameMeet = true;
	}
}

/** 
 * 验证密码
 * 1.非空
 * 2.不符合规则
 */
function validatePwd(){
	var pwd = $("#up_password").val();
	var error = "";
	
	if(pwd == ""){
		error = "密码不能为空";
		isPwdMeet = false;
	}
	else if(!(/^[\w]{6,16}$/).test(pwd)){
		error = "不符合要求";
		isPwdMeet = false;
	}
	
	if(error != ""){
		$("#up_password").popover("show");
		$("#popover_regist_pwd").html(error);
	}
	else{
		$("#up_password").popover("hide");
		isPwdMeet = true;
	}
}

/**
 * 检查验证码
 * 1.非空
 * 2.不正确
 */
function validateCaptcha(){
	var captcha = $("#code").val();
	var error = "";
	
	if(captcha == ""){
		error = "请输入验证码";
		isCaptchaMeet = false;
	}
	else if(captcha != sum){
		error = "验证码不正确";
		isCaptchaMeet = false;
	}
	
	if(error != ""){
		$("#code").popover("show");
		$("#popover_regist_captcha").html(error);
	}
	else{
		$("#code").popover("hide");
		isCaptchaMeet = true;
	}
}

var sum;	//验证码之和

/**
 * 生成验证码
 * 随机生成两个两位数，验证码的值为两数之和
 * 验证码显示在一个只读型从文本框里
 */
function createCaptcha() {
  code = "";
  function RndNum(n) {
    var rnd = "";
    for (var i = 0; i < n; i++)
      rnd += Math.floor(Math.random() * 10);
    return rnd;
  }

  var num = RndNum(2);
  var num2 = RndNum(2);

  code = num + " + " + num2 + " = ?";
  sum = parseInt(num) + parseInt(num2);
  $("#captcha").val(code);
}

/**
 * 注册
 * 用户名和密码是否符合规范
 * 验证码是否正确
 */
function sign_up(){
	if(isNameMeet && isPwdMeet && isCaptchaMeet){
		$.ajax({
			url:'user_regist.action',
			data:{
				loginName: $("#up_user_name").val(),
				password: $("#up_password").val(),		//就因为写错，又耽误了几个小时
														//不过还好，对异常有了新的了解
				roleId:3
			},
			type:'post',
			success:function(data){
				if(data == "注册成功"){
					$("#regist_result_title").html("注册成功");
					$("#reigst_result_body").html("<img src='img/success.jpg' alt='success'>"
							+ " 恭喜您，注册成功！"
							+ "<a href='javascript:toLogin()'>"
							+ "现在就去登录</a>");
					
					/*注册成功后，清空历史数据，重新生成验证码*/
					$("#up_user_name").val("");
					$("#up_password").val("");
					$("#code").val("");
					createCaptcha();
				}
				else{
					$("#regist_result_title").html("注册失败");
					$("#reigst_result_body").html("<img src='img/fail.jpg' alt='fail'>"
							+ " 很抱歉，服务器端发生异常,注册失败！请"
							+ "<a href='javascript:toRegist()'>"
							+ "重新注册</a>");
					
					createCaptcha();
				}
				
				$("#regist_result").modal({
					backdrop:"static"				//禁止点击时关闭模态框
				});
			}
		});
	}
}

/**
 * 注册成功后，去登录
 * 关闭模态框、
 * 隐藏注册界面，显示登录界面
 */
function toLogin(){
	$(".bs-example-modal-sm").modal("hide");
	$("#up").hide();
	$("#in").show();
}

/**
 * 妈蛋，非要我重新定义一个方法
 */
function toRegist(){
	$(".bs-example-modal-sm").modal("hide");
}


/**
 * 登录
 * 1.用户名为空
 * 2.用户名不存在
 * 3.密码错误
 */
function sign_in(login){
	var user_name = $("#in_user_name").val();
	if(user_name == ""){
		
		/*先显示，后修改，顺序不能颠倒*/
		$("#in_user_name").popover("show");
		$("#popover_name").html("用户名不能为空");
	}
	else{
		var password = $("#in_password").val();
		if(password == ""){
			$("#in_password").popover("show");
			$("#popover_pwd").html("密码不能为空");
		}
		else{
			/*发送登录请求*/
			$.ajax({
				url:'user_signIn.action',
				data:{
					loginName:user_name,
					password:password
				},
				type:'post',
				success:function(data){
					if(data == "用户名不存在"){
						$("#in_user_name").popover("show");
						$("#popover_name").html(data);
					}
					else if(data == "密码错误"){
						$("#in_password").popover("show");
						$("#popover_pwd").html(data);
					}
					else{
						$(login).val("正在登录...");
						
						setTimeout("sign_in_request()",1000);
					}
				}
			});
		}
	}
	
}

/**
 * 发送登录请求
 */
function sign_in_request(){
	$.ajax({
		url:'user_getPrePage.action',
		type:'post',
		async:false,
		success:function(data){
			/*访问被拦截，被迫登录*/
			if(data != "")
				window.location.href = data.substring(0, data.length - 1);
			
			/*直接在首页的登录界面登录*/
			else{
				$("#sign").hide();
				$("#in_user_name").popover("hide");
				$("#in_password").popover("hide");
				
//				update($("#in_user_name").val());	//显示用户登录信息
				window.location.href = "home_index.action";
			}
		}
	});
}



/*======================后台管理======================*/

var id;			//各种实体的id
var sel_role;	//用户当前的角色
var btn_edit;	//修改按钮，当前被修改的行

/**
 * 修改按钮的点击事件的响应函数，
 * 在弹出的模态框里回显数据
 */
function modify(button){
	
	btn_edit = button;
	
	/*获取数据用于回显*/
	id = get_cell_content(button,1);
	var user_name = get_cell_content(button,2);
	sel_role = get_cell_content(button,3);
	
	$("#myModalLabel").html("修改用户角色");					//修改标题
	
	/*回显数据*/
	$("#userName").val(user_name);
	$("#sel_role").val(sel_role);
	
	$("#userName").attr("readonly","readonly");			//添加只读属性
	$("#save").attr("href","javascript:edit()");		//保存的链接为edit()
}

/**
 * 修改用户角色
 */
function edit(){
	if(sel_role != $("#sel_role").val()){
		if(confirm("确定修改该用户的角色吗？")){
			
			/*设置用户角色*/
			var role_id = 1;
			if($("#sel_role").val() == "普通管理员")
				role_id = 2;
			else if($("#sel_role").val() == "用户")
				role_id = 3;
			
			/*发送修改请求*/
			$.ajax({
				url:'user_edit.action',
				data:{
					id:id,
					roleId:role_id
				},
				type:'post',
				success:function(){
					$("#myModal").modal("hide");
					set_cell_content(btn_edit,3,$("#sel_role").val());
				}
			});
		}
	}
	//没有做修改，直接关闭模态框
	else
		$("#myModal").modal("hide");
}

/**
 * 获取table中指定单元格的内容
 * @param row 当前行
 * @param col 单元格的序号，从0开始
 * @returns 单元格的内容
 */
function get_cell_content(row,col){
	
	return $(row).parent().parent()
		.children("td").get(col)
		.innerHTML.trim();
}

/**
 * 修改table表中指定单元格的内容
 * @param row 行号
 * @param col 列号 
 * @param content 希望修改成的内容
 */
function set_cell_content(row,col,content){
	$(row).parent().parent()
	.children("td").get(col)
	.innerHTML = content;
}

/**
 * 增加按钮的响应事件
 */
function add(){
	$("#myModalLabel").html("增加用户");				//修改标题
	$("#userName").val("");							//清空内容
	$("#userName").removeAttr("readonly");			//移除只读属性
	$("#save").attr("href","javascript:save()");	//保存的链接为save()
}

/**
 * 新增用户
 * 验证非空和已注册
 */
function save(){
	var user_name = $("#userName").val();
	
	if(user_name == "")
		alert("用户名不能为空");
	else if(!(/^[a-zA-Z]{1}[\w]{4,19}$/).test(user_name))
		alert("用户名应该为5-20位字符的组合，不含空格！");
	else{
		$.ajax({
			url:'user_isExist.action',
			data:{
				loginName:user_name
			},
			success:function(data){
				if(data == "用户名已被注册")
					alert("该用户名已被注册！")
				else{
					/*设置用户角色*/
					var role_id = 1;
					if($("#sel_role").val() == "普通管理员")
						role_id = 2;
					else if($("#sel_role").val() == "用户")
						role_id = 3;
					
					$.ajax({
						url:'user_add.action',
						data:{
							loginName:user_name,
							roleId:role_id
						},
						contentType:'json',
						success:function(data){
							alert("用户:" + user_name + " 添加成功，初始密码为111111.");
							
							//更新DOM操作先放着
							
							$("#myModal").modal("hide");
							window.location.href = "user_list.action";
						}
					});
				}
			}
		});
	}
}

/*=======================批量删除=======================*/

/**
 * 全选和反选
 * 不需要写在加载完成从函数里
 * 
 * 彻底被JQuery打败，获取$("#fuck").attr("checked")的值始终未undefined
 * 用doument方法，获取checked值，只能正常两次，后面始终选不上
 * attr就是贱人，耽误这么长时间!!!
 */
$(function(){
	$("#fuck").click(function(){
//		var checked = document.getElementById("fuck").checked;
//		alert($(this).prop("checked"));
		
		$("[name=check]:checkbox").prop("checked",$(this).prop("checked"));
	});
});

/**
 * 批量删除
 * 
 * 今晚见鬼了吗？各种奇怪的情况
 * 不让定义为delete，定义成remove，竟然直接把按钮隐藏了！！！
 */
function batch_delete(){
	var ids = [];
	$("input[name='check']:checked").each(function(){
		ids.push($(this).val());
	});
	
	if(ids.length == 0)
		alert("请选择要删除记录");
	else{
		var set = "";
		for(var i = 0; i < ids.length; i++)
			set += ids[i] + ",";
			
//		$.modal.confirm("确认删除这 " + ids.length + " 条记录吗？");
		
		if(confirm("确认删除这 " + ids.length + " 条记录吗？")){
		$.ajax({
			url:'user_bacthDelete.action',
			data:{
				ids:set
			},
			success:function(data){
				if(data == "删除成功")
					alert(data);
				else
					//早写上这句，就可以判断是不是代码有bug了
					alert("服务器发生异常，删除失败");
			}
		});
		}
	}
}

function batch_delete_talk(){
	var ids = [];
	$("input[name='check']:checked").each(function(){
		ids.push($(this).val());
	});
	
	if(ids.length == 0)
		alert("请选择要删除记录");
	else{
		var set = "";
		for(var i = 0; i < ids.length; i++)
			set += ids[i] + "," 
			
//		$.modal.confirm("确认删除这 " + ids.length + " 条记录吗？");
		
		if(confirm("确认删除这 " + ids.length + " 条记录吗？")){
		$.ajax({
			url:'talk_bacthDelete.action',
			data:{
				ids:set
			},
			success:function(data){
				if(data == "删除成功"){
					window.location.href="talk_list.action";
					alert(data);
				}
				else
					//早写上这句，就可以判断是不是代码有bug了
					alert("服务器发生异常，删除失败");
			}
		});
		}
	}
}

/**
 * 对用户名进行模糊查询
 * @param obj 搜索框
 */
function fuzzy_search(obj){
	if($(obj).val() != ""){
		$.ajax({
			url:'user_search.action',
			data:{
				loginName:$(obj).val()
			},
			success:function(data){
				$("#user").html(data);
				
				//在后台直接加onclick事件，会遇到引号的问题
				$("a.btn-danger").click(function(){
					return confirm("确定删除吗？");
				});
			}
		});
	}
	else
		window.location.href="user_list.action";
}

/*=======================权限管理=======================*/
/**
 * 角色表格中增加按钮的响应函数
 */
function add_privilege(){
	$("#title").html("增加角色");
	$("#roleName").val("");
	$("#desc").val("");
	$("#save").attr("href","javacript:create_privilege();");
}

/**
 * 创建新角色
 */
function create_privilege(){
	var roleName = 	$("#roleName").val();
	if(roleName == "")
		alert("角色名不能为空");
	else{
		
	}
}

/**
 * 角色表格中修改按钮的响应函数
 */
function modify_privilege(obj){
	$("#title").html("修改角色");
	
	var roleName = get_cell_content(obj,1);
	var desc = get_cell_content(obj,2);
	
	$("#roleName").val(roleName);
	$("#desc").val(desc);
	$("#save").attr("href","javacript:update_privilege();");

}

/**
 * 获取角色所拥有的权限，并勾选相应的复选框
 * @param roleId 角色
 */
function read_privilege(roleId){
	var oldIds = new Array();
	
	$("input[name=check]:checked").each(function(){
		oldIds.push($(this).attr("id"));
	});
	
	$.ajax({
		url:'privilege_readPrivilege.action',
		data:{
			roleId:roleId
		},
		type:'get',
		success:function(data){
			if(data != ""){
				var ids = new Array();
				ids = data.split(",");
				
				/*旧角色有，新角色没有*/
				for(var i = 0; i < oldIds.length; i++)
					if(!contains(ids,oldIds[i]))
						$("#" + oldIds[i] + ":checkbox").prop("checked",false);
				
				/*旧角色没有，而新角色有*/
				for(var j = 0; j < ids.length; j++)
					if(!contains(oldIds,ids[j]))
						$("#" + ids[j] + ":checkbox").prop("checked",true);
			}
			else{
				$("input[name=check]").prop("checked",false);
			}
		}
	});
}

/**
 * 判断数组是否包含某个元素
 */
function contains(a,obj){
	for(var i = 0; i < a.length; i++)
		if(a[i] == obj)
			return true;
	
	return false;
}

/**
 * 更新角色权限
 */
function update_role_privilege(){
	var ids = [];
	$("input[name=check]:checked").each(function(){
		ids.push($(this).attr("id"));
	});
	
	var set = "";
	for(var i = 0; i < ids.length; i++)
		set += ids[i] + ",";
		
	$.ajax({
		url:'privilege_update.action',
		data:{
			roleId:$("#sel_role").val(),
			ids:set
		},
		type:'get',
		success:function(data){
			if(data == '更新成功')
				alert(data);
			else
				alert("服务器发生异常，更新失败");
		}
	});
	
}

/**
 * 首页的全站搜素
 */
function searchAll(){
	if($("#search_content").val() == "")
		window.location.href = "home_index.action";
	else{
		$.ajax({
			url:'home_fuzzySearch.action',
			data:{
				keywords:$("#search_content").val()
			},
			type:'post',							//默认为get，post可以避免中午乱码
			success:function(data){
				var table = new Array();
				table = data.split(",");
				
				
				$("#topic_table").html(table[0]);
				$("#resource_table").html(table[1]);
				$("#activity_table").html(table[2]);
			}
		});
	}
}

/**
 * 首页的搜索框被删除为空的时候
 * @param obj 搜索框
 */
function empty(obj){
	if($(obj).val() == "")
		window.location.href = "home_index.action";
}