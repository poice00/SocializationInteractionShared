package com.socialization.action.zone;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.socialization.base.BaseAction;
import com.socialization.domain.Social;
import com.socialization.domain.Talk;
import com.socialization.domain.User;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SocialAction extends BaseAction<Talk>{

	private Long friendId;
	private Long talkId;
	private String divcontext;
	
	//查找当前用户好友的说说
		public String list() throws Exception {
			List<Talk> talkList = talkService.findByFriends(getCurrentUser());
			ActionContext.getContext().put("talkList", talkList);
			User currentuser=userService.getById(getCurrentUser().getId());
			ActionContext.getContext().getValueStack().push(currentuser);
			return "list";
		}
	//赞好友的说说
	public void addzan() throws Exception {
		   // ActionContext.getContext().getSession().remove("usersList");
			User currentuser=userService.getById(getCurrentUser().getId());
			Talk talk=talkService.getById(talkId);
			ActionContext.getContext().getValueStack().push(currentuser);
			boolean checkResult=this.talkService.CheckZanExit(talk.getId(),currentuser.getId());
			/*if (checkResult) {
				
			} else {
				
			}*/
			this.talkService.insertZan(currentuser.getId(),talk.getId());
			httpSr(String.valueOf(talkId));
			return;
	}
	//取消赞好友的说说
		public void removezan() throws Exception {
			User user=userService.getById(getCurrentUser().getId());
			Talk talk=talkService.getById(talkId);
			this.talkService.deleteZan(user.getId(),talk.getId());
			httpSr(String.valueOf(talkId));			
			return;
		}
	/** 列表 *//*
	public String list() throws Exception {
		User friend = userService.getById(getFriendId());
		User user = userService.getById(getCurrentUser().getId());
		List<Social> socialList = socialService.findByFriends(getCurrentUser());
		ActionContext.getContext().put("socialList", socialList);
		return "list";
	}
*/
	public void httpSr(String str) throws Exception {
		HttpServletResponse hsr = ServletActionContext.getResponse();
		hsr.setContentType("test/html;charset=UTF-8");
		hsr.getWriter().write(str);
	}
	public Long getFriendId() {
		return friendId;
	}
	
	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}
	public Long getTalkId() {
		return talkId;
	}
	public void setTalkId(Long talkId) {
		this.talkId = talkId;
	}
	public String getDivcontext() {
		return divcontext;
	}
	public void setDivcontext(String divcontext) {
		this.divcontext = divcontext;
	}

}

