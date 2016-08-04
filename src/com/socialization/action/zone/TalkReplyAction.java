package com.socialization.action.zone;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.socialization.base.BaseAction;
import com.socialization.domain.Reply;
import com.socialization.domain.Talk;
import com.socialization.domain.TalkReply;
import com.socialization.domain.Topic;
import com.socialization.util.TalkReplyUtil;

/**
 * 说说回复
 * @author 
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TalkReplyAction extends BaseAction<TalkReply>{
	
	private Long talkId;
	private String divcontext;
	
	/** 发表新回复页面 */
	public String addUI() throws Exception {
		//准备主贴数据
		Talk talk  = this.talkService.getById(talkId);
		ActionContext.getContext().put("talk", talk);
		
		//准备回复数据
		TalkReply talkreply = talkreplyService.getById(model.getId());
		ActionContext.getContext().put("talkreply", talkreply);
		return "addUI";
	}
	
	/** 删除回复 */
	public String delete() throws Exception {
		Talk talk  = this.talkService.getById(talkId);
		
		TalkReply tr = talkreplyService.getById(model.getId()); //待删除的回复
		TalkReply talkreply = talk.getLastReply(); //当前的最后回复
		TalkReply other = talkreplyService.getLastReplyByTalk(talk,tr);  //从数据库查到的最后回复前一条
		
		
		if(tr==talkreply){
			talk.setLastReply(other);
		}
		if(tr.getChildrens()!=null){ //当子回复是最新回复时，递归删除子回复
			TalkReplyUtil.deleteRecursionTalkReply(talk, talkreply, tr, tr.getChildrens(),other);//
		}
		
		talkreplyService.delete(model.getId());//删除
		
		int count =talkreplyService.getCountByTalk(talk); //从数据库查的回复数量

		//维护相关属性
		talk.setReplyCount(count);
		talkService.update(talk);
		return "toTalkShow";
	}

	

	/** 发表新回复*/
	public String add() throws Exception {
		
		System.out.println("回复编号："+model.getId());
		if(model.getId()!=null){ //如果是回复的回复内容
			System.out.println("用户的回复！");
			TalkReply talkreply = talkreplyService.getById(model.getId());
			model.setParent(talkreply); //设置它的父贴
			
		}
		System.out.println("说说的回复！");
		//封装
		model.setTalk(this.talkService.getById(talkId));
		model.setUser(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		
		//保存
		this.talkreplyService.save(model);
		
		return "toTalkShow";
	}
	
	/** 发表新回复*/
	public void addReply() throws Exception {
		model.setContent(divcontext);
		model.setTalk(this.talkService.getById(talkId));
		model.setUser(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		//保存
		this.talkreplyService.save(model);
		httpSr(String.valueOf(talkId));
		return ;
	}
	
	public void httpSr(String str) throws Exception {
		HttpServletResponse hsr = ServletActionContext.getResponse();
		hsr.setContentType("test/html;charset=UTF-8");
		hsr.getWriter().write(str);
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
