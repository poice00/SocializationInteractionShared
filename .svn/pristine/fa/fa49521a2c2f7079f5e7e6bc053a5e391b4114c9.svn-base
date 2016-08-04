package com.socialization.action.resource;



import java.util.Date;
import java.util.List;


import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionContext;
import com.socialization.base.BaseAction;
import com.socialization.domain.ResReply;
import com.socialization.domain.Resource;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ResReplyAction extends BaseAction<ResReply> {

	/** 列表 */
	private String resSource;
    private Long idd;  //资源的ID
    private Long resRepId;  //回复的ID
	/*---回复列表----*/
	public String list() throws Exception {
		List<ResReply> resreplies = resReplyService.getbyResID(this.updowncollectResService.getById(idd));
		ActionContext.getContext().put("resreplies", resreplies);
		return "list";
	}
	public String add() throws Exception{
		//封装
	    //-->>表单参数
		//--〉〉直接获取的信息
		model.setPostTime(new Date());
        model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
        model.setResource(this.updowncollectResService.getById(idd));
		model.setUser(getCurrentUser());
		model.setScore(Float.parseFloat(resSource));
		//保存		
		this.resReplyService.save(model,Float.parseFloat(resSource));
		return "toShow";
	}
	
	public String resRepDelete() {
		ResReply resReply=this.resReplyService.getById(resRepId);
		Resource resource=resReply.getResource();
		idd=resource.getId();
        this.resReplyService.delete(this.resReplyService.getById(resRepId));
		return "resRepDelete";
	}
	
	public Long getIdd() {
		return idd;
	}
	public void setIdd(Long idd) {
		this.idd = idd;
	}

	public String getResSource() {
		return resSource;
	}

	public void setResSource(String resSource) {
		this.resSource = resSource;
	}
	public Long getResRepId() {
		return resRepId;
	}
	public void setResRepId(Long resRepId) {
		this.resRepId = resRepId;
	}
}
