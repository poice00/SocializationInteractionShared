package com.socialization.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.socialization.base.BaseAction;
import com.socialization.domain.Forum;
import com.socialization.domain.Reply;
import com.socialization.domain.Tag;
import com.socialization.domain.Topic;
import com.socialization.domain.User;
import com.socialization.util.ReplyUtil;

/**
 * 回复管理
 * @author ssy
 *
 */
@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply>{
	
	private Long topicId; //所属的论坛
	
	/** 发表新回复页面 */
	public String addUI() throws Exception {
		//准备主贴数据
		Topic topic  = this.topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		
		//准备回复数据
		Reply reply = replyService.getById(model.getId());
		ActionContext.getContext().put("reply", reply);
		
		return "addUI";
	}
	
	
	
	
	/** 删除回复 */
	public String delete() throws Exception {
		Topic topic  = this.topicService.getById(topicId);
		
		Reply r = replyService.getById(model.getId()); //待删除的回复
		Reply reply = topic.getLastReply(); //当前帖子的最后恢复
		Reply other = replyService.getLastReplyByTopic(topic,r);  //从数据库查到的最后回复前一条
		
		
		if(r==reply){
			topic.setLastReply(other);
		}
		if(r.getChildrens()!=null){ //当子回复是最新回复时，递归删除子回复
			ReplyUtil.deleteRecursionReply(topic, reply, r, r.getChildrens(),other);//
		}
		
		replyService.delete(model.getId());//删除
		
		int count =replyService.getCountByTopic(topic); //从数据库查的回复数量

		//维护相关属性
		topic.setReplyCount(count);
		topicService.update(topic);
		return "toTopicShow";
	}

	

	/** 发表新回复*/
	public String add() throws Exception {
		
		System.out.println("回复编号："+model.getId());
		if(model.getId()!=null){ //如果是回复的回复内容
			System.out.println("用户的回复！");
			Reply reply = replyService.getById(model.getId());
			model.setParent(reply); //设置它的父贴
			
		}
		System.out.println("帖子的回复！");
		//封装
		model.setTopic(this.topicService.getById(topicId));
		model.setUser(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		
		//保存
		this.replyService.save(model);
		
		return "toTopicShow";
	}
	


	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}


	
	

}
