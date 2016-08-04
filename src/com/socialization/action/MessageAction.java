package com.socialization.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.socialization.base.BaseAction;
import com.socialization.domain.Forum;
import com.socialization.domain.Message;
import com.socialization.domain.Tag;
import com.socialization.domain.Topic;
import com.socialization.domain.User;

/**
 * 标签
 * @author ssy
 *
 */
@Controller
@Scope("prototype")
public class MessageAction extends BaseAction<Message>{

	private Long friendId;
	
	/** 我的收件箱*/
	public String receiveUI() throws Exception {
		if(getCurrentUser()!=null){
			User user =userService.getById(getCurrentUser().getId());
			ActionContext.getContext().put("user", user);
		
		}
		return "receiveUI";
	}
	/** 我的发件箱*/
	public String sendUI() throws Exception {
		if(getCurrentUser()!=null){
			User user =userService.getById(getCurrentUser().getId());
			ActionContext.getContext().put("user", user);
		
		}
		return "sendUI";
	}
	/** 我的发布*/
	public String MyTopicListUI() throws Exception {
		if(getCurrentUser()!=null){
			//我的收藏
			User user =userService.getById(getCurrentUser().getId());
			ActionContext.getContext().put("user", user);
		
			//我的发布
			List<Topic> MyTopicList =topicService.getMyTopics(user.getId());
			ActionContext.getContext().put("MyTopicList", MyTopicList);
		}
		return "MyTopicListUI";
	}
	/** 我的收藏夹*/
	public String CollectionUI() throws Exception {
		if(getCurrentUser()!=null){
			//我的收藏
			User user =userService.getById(getCurrentUser().getId());
			ActionContext.getContext().put("user", user);
		}
		
		return "CollectionUI";
	}
	
	/** 查看 收件*/
	public String show() throws Exception {
		Message message = messageService.getById(model.getId());
		message.setStatus(1);
		messageService.update(message);
		
		ActionContext.getContext().put("message", message);
		return "showMessageReceive";
	}
	/** 查看 发件*/
	public String showSend() throws Exception {
		Message message = messageService.getById(model.getId());
		ActionContext.getContext().put("message", message);
		return "showMessage";
	}

	/** 删除 */
	public String removeMsg() throws Exception {
		
		messageService.delete(model.getId());
		
		
		return "toForumIndex";
	}


	/** 发送私信*/
	public String add() throws Exception {
		System.out.println("MessageAction.add()");
		//需要发给的好友
		User friend = userService.getById(getFriendId());
		
		//当前登陆用户
		User user = userService.getById(getCurrentUser().getId());
		
		model.setSendStatus(1);
		model.setStatus(0);
		//发送的内容
		model.setPostTime(new Date());
		model.setUser(user);
		model.setFriend(friend);
		
		messageService.save(model);
		return "toshowTip";
	}
	
	/** 保存草稿*/
	public String save() throws Exception {
		System.out.println("MessageAction.save()");
		//需要发给的好友
		User friend = userService.getById(getFriendId());
		
		//当前登陆用户
		User user = userService.getById(getCurrentUser().getId());
		
		model.setSendStatus(0);
		model.setStatus(0);
		//发送的内容
		model.setPostTime(new Date());
		model.setUser(user);
		model.setFriend(friend);
		
		messageService.save(model);
		return "toshowTip";
	}	
	/** 编辑草稿*/
	public String edit() throws Exception {
		Message message = messageService.getById(model.getId());	
		message.setContent(model.getContent());
		message.setPostTime(new Date());
		System.out.println("==========" + model.getContent());
		messageService.update(message);
		return "toshowTip";
	}
	/** 编辑发送*/
	public String editAdd() throws Exception {
		Message message = messageService.getById(model.getId());	
		message.setContent(model.getContent());
		message.setPostTime(new Date());
		
		message.setSendStatus(1);
		
		messageService.update(message);
		return "toshowTip";
	}

	/** 发送页面 */
	public String addUI() throws Exception {
		User user = userService.getById(getCurrentUser().getId());
		ActionContext.getContext().put("user", user);
		
		User friend = userService.getById(friendId);
		ActionContext.getContext().put("friend", friend);
		
		return "addUI";
	}
	/** 编辑页面 */
	public String editUI() throws Exception {
		User friend = userService.getById(friendId);
		ActionContext.getContext().put("friend", friend);
		
		Message message = messageService.getById(model.getId());
		System.out.println("===id===" + model.getId());
		ActionContext.getContext().put("message", message);
		return "editUI";
	}
	/** 回复页面 */
	public String replyUI() throws Exception {
		Message message = messageService.getById(model.getId());
		System.out.println("===id===" + model.getId());
		ActionContext.getContext().put("message", message);
		return "replyUI";
	}

	//=================
	public Long getFriendId() {
		return friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}
	
	

}
