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
public class ActTopicAction extends BaseAction<ActTopic>{
	
	
	private Long activityId;
	private Long idd;
	
	/*---活动话题创建----*/
	public String addUI() throws Exception{		
		Activity activity=this.activiService.getById(activityId);
		ActionContext.getContext().getValueStack().push(activity);
		return "actTopicAddUI";
	}
	/*---活动话题创建成功----*/
	public String add() throws Exception{
		Activity activity=this.activiService.getById(activityId);
		model.setActivity(activity);
		model.setPostTime(new Date());
		model.setUser(getCurrentUser());
		model.setReplyCount(0);
		String str=model.getContent();
		str=str.replaceAll("\r\n", "<br/>");
		str=str.replaceAll(" ", "&nbsp;");
		model.setContent(str);
		this.actTopicService.save(model);
		return "add";
	}
	/*---活动话题显示----*/
	public String showActTopic() throws Exception{
		ActTopic actTopic=this.actTopicService.getById(idd);
	    ActionContext.getContext().getValueStack().push(actTopic);
	    List<ActTopicReply> actTopicRepliesList=this.actTopicReplyService.findByActTopId(idd);
	    System.out.println(actTopicRepliesList.size());
	    ActionContext.getContext().put("actTopicRepliesList", actTopicRepliesList);
		return "showActTopic";
	}	
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public Long getIdd() {
		return idd;
	}
	public void setIdd(Long idd) {
		this.idd = idd;
	}

}
