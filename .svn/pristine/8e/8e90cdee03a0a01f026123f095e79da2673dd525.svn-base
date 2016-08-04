package com.socialization.action.login;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialization.base.BaseAction;
import com.socialization.domain.User;


@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User>{
	
	
	//用户列表页面
	public String list() throws Exception {
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);
		return "list";
	}
	
	//用户登录页面
	public String logining() throws Exception {
		return "logining";
	}
	
	//用户注册页面
	public String register() throws Exception{
		return "registerUI";
	}
	
	//用户注册成功页面
	public String registerSuc() throws Exception{
		return "registerSuc";
	}
	
	/** 登陆 */
	public String login() {
		User user = this.userService.findByLoginNameAngPassword(model.getLoginName(), model.getPassword());
		if (user == null) {  //返回登录页面，并返回登录失败信息
			addActionMessage("用户名或密码错误");
			return "loginUI";
		} else {
			// 登陆用户
			ActionContext.getContext().getSession().put("user", user);
			addActionMessage("欢迎来到SISP");
			return "toLogin";
		}
	}

	/** 注销 */
	public String logout() {
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
}
