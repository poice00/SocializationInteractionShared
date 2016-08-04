package com.socialization.action.zone;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.socialization.base.BaseAction;
import com.socialization.domain.Activity;
import com.socialization.domain.Forum;
import com.socialization.domain.Topic;
import com.socialization.domain.User;
import com.socialization.service.InformationService;
import com.socialization.util.MD5Util;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class InformationAction extends BaseAction<User>{
	
	
	private String passwordOne;
	private String newPassword;
	private Long visitedId;

	/** 列表 */
	public String index() throws Exception {
		//准备版块列表
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		
		//准备版块排行
		List<Forum> forumRankList = forumService.findforumRankList();
		ActionContext.getContext().put("forumRankList", forumRankList);
		
		
		//准备最新帖子（30条）
		List<Topic> lastestTopicList = topicService.findLastestTopics();
		ActionContext.getContext().put("lastestTopicList", lastestTopicList);
		
		//准备最热帖子（10条）
		List<Topic> hotTopicList = topicService.findhotTopics();
		ActionContext.getContext().put("hotTopicList", hotTopicList);

		//排行fansCount lookTopicList
		
		List<Topic> lookTopicList = topicService.findLookTopics();
		ActionContext.getContext().put("lookTopicList", lookTopicList);
		
		List<User> fansCountList = userService.findFansCount();
		ActionContext.getContext().put("fansCountList", fansCountList);
		
		
		if(getCurrentUser()!=null){
			//我的收藏
			User user =userService.getById(getCurrentUser().getId());
			ActionContext.getContext().put("user", user);
		
			//我的发布
			List<Topic> MyTopicList =topicService.getMyTopics(user.getId());
			ActionContext.getContext().put("MyTopicList", MyTopicList);
		}
		return "index";
	}
	/** 列表 */
	public String list() throws Exception {
		if (visitedId==null) {
			User user = informationService.getById(getCurrentUser().getId());
			ActionContext.getContext().put("user", user);
			List<Activity> activitiesList=this.activiService.findByUserTags(user);
			ActionContext.getContext().put("activitiesList", activitiesList);
			List<Topic> topicsList=this.topicService.findByUserTags(user);
			ActionContext.getContext().put("topicsList", topicsList);
		}else
		{
			User user=informationService.getById(visitedId);
			user.setZoneVisitCount(user.getZoneVisitCount()+1);
			this.informationService.update(user);
			ActionContext.getContext().put("user", user);
		}		
		return "list";
	}

	/** 修改密码页面 */
	public String changePasswordUI() throws Exception {
		return "changePasswordUI";
	}
	
	/** 修改密码 */
	public String changePassword() throws Exception {
		User user = informationService.getById(model.getId());
		String password = MD5Util.MD5(model.getPassword());
		System.out.println("======oldPassword=========" + user.getPassword());
		System.out.println("======newPassword=========" + password);
		if(newPassword.equals(passwordOne)){	//判断两次密码是否输入一致
			if(password.equals(user.getPassword())){	//判断旧密码是否输入正确
				user.setPassword(MD5Util.MD5(newPassword));
				System.out.println("++++++++++++++++++" + newPassword);
				ActionContext.getContext().getSession().remove("user");
				informationService.update(user);
				return "changePassword";
			}else{
				addFieldError("fail", "原密码错误");
				return "changePasswordUI";
			}
		}else{
			addFieldError("fail", "两次输入不一致！");
			return "changePasswordUI";
		}
			
		
	}
	/** 修改页面 */
	public String editUI() throws Exception {
		User information = informationService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(information);
		return "saveUI";
	}
	
	/** 修改 */
	public String edit() throws Exception {
		System.out.println("=======1=========");
		User information = informationService.getById(model.getId());
		System.out.println("=============model:" + model.getId());
		
		information.setName(model.getName()); //
		information.setBirthday(model.getBirthday());
		information.setEducation(model.getEducation());//
		information.setEmail(model.getEmail());
		information.setTelephone(model.getTelephone());
		information.setQq(model.getQq());
		
		informationService.update(information);
		
		return "toList";
	}

	public String getPasswordOne() {
		return passwordOne;
	}

	public void setPasswordOne(String passwordOne) {
		this.passwordOne = passwordOne;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public Long getVisitedId() {
		return visitedId;
	}

	public void setVisitedId(Long visitedId) {
		this.visitedId = visitedId;
	}
}
