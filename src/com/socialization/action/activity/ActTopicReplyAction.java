package com.socialization.action.activity;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.socialization.base.BaseAction;
import com.socialization.domain.ActTopic;
import com.socialization.domain.ActTopicReply;
import com.socialization.domain.Activity;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ActTopicReplyAction extends BaseAction<ActTopicReply>{
	
	
	private Long actTopicId;
	private Long idd;
	
	/*---回复成功----*/
	public String add() throws Exception{
		ActTopic actTopic=this.actTopicService.getById(actTopicId);
		model.setPostTime(new Date());
		model.setActTopic(actTopic);
		model.setUser(getCurrentUser());
		String str=model.getContent();
		str=str.replaceAll("\r\n", "<br/>");
		str=str.replaceAll(" ", "&nbsp;");
		model.setContent(str);
		this.actTopicReplyService.save(model);
		actTopic.setReplyCount(actTopic.getReplyCount()+1);
		this.actTopicService.update(actTopic);
		return "add";
	}

	public Long getIdd() {
		return idd;
	}
	public void setIdd(Long idd) {
		this.idd = idd;
	}
	public Long getActTopicId() {
		return actTopicId;
	}
	public void setActTopicId(Long actTopicId) {
		this.actTopicId = actTopicId;
	}

}
