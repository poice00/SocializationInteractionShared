package com.socialization.action.zone;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.socialization.base.BaseAction;
import com.socialization.domain.Forum;
import com.socialization.domain.Reply;
import com.socialization.domain.Talk;
import com.socialization.domain.TalkReply;
import com.socialization.domain.Topic;
import com.socialization.domain.User;
import com.socialization.util.QueryHelper;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TalkAction extends BaseAction<Talk>{
	
	private Long talkId;
	private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	/** 列表 */
	public String list() throws Exception {
		User user = userService.getById(getCurrentUser().getId());
		System.out.println(getCurrentUser().getId());
		List<Talk> talkList = talkService.getTalkListById(user.getId());
		ActionContext.getContext().put("talkList", talkList);
		return "list";
	}

	//查找当前用户好友的说说
	public String listfriendTalk() throws Exception {
		List<Talk> talkList = talkService.findByFriends(getCurrentUser());
		ActionContext.getContext().put("talkList", talkList);
		return "listfriendTalk";
	}
	
	/** 删除 */
	public String delete() throws Exception {
		talkService.delete(model.getId());
		return "toList";
	}
	
	/** 删除 *//*
	public String delete() throws Exception {
		
		Talk talk = talkService.getById(talkId);
		
		Talk t = talkService.getById(model.getId());//待删除的说说说
		TalkReply talkreply = talk.getLastReply(); //当前说说的最新的回复
		
		if(t==talk){
			Talk other = talkService.getLastTalk(talk,t);  //从数据库查到的最后回复前一条
			talk.setLastReply(other);//把最新主题设置为最新主题
		}
		
		talkService.delete(model.getId()); //删除
		
		talk.setTalkCount(talk.getTalkCount() - 1);
		talkService.update(talk);
		
		return "toList";
	}*/

	/** 添加页面 */
	public String addUI() throws Exception {
		talkService.save(model);
		return "saveUI";
	}
		

	/** 添加 */
    public String add() throws Exception {
		model.setPostTime(new Date());
		String  str= new String(model.getContent().getBytes("iso-8859-1"),"utf-8");
		User user=this.userService.getById(getCurrentUser().getId());
		model.setUser(user);
		model.setContent(str);
		talkService.save(model);
		return "toList";
	}
    
    public String update() throws Exception {
    	String  str= new String(model.getContent().getBytes("iso-8859-1"),"utf-8");
    	Talk talk=this.talkService.getById(model.getId());
    	talk.setPostTime(new Date());
    	talk.setContent(str);
		this.talkService.update(talk);
		return "toList";
	}

	/** 发表说说 *//*
	public String add() throws Exception {
		Talk content = talkService.findByContent(model.getContent());
		if(content==null){
			addFieldError("fail", "内容不能为空！");
			return "add";
		}else{
			ActionContext.getContext().getSession().put("talk", content);
			return "toList";
		}
	}*/
    
	public String show() throws Exception {
		//准备数据主贴
		Talk talk = this.talkService.getById(model.getId());
		
		int count = talk.getCount() ;
		talk.setCount(++count);
		
		talkService.update(talk);
		ActionContext.getContext().put("talk", talk);
		
		//准备上一篇帖子
		Talk beforeTalk = this.talkService.getbeforeTalkById(model.getId(),talk.getContent());
		ActionContext.getContext().put("beforeTalk", beforeTalk);
		
		//准备下一篇帖子
		Talk nextTalk = this.talkService.getnextTalkById(model.getId(),talk.getContent());
		ActionContext.getContext().put("nextTalk", nextTalk);
		
		
		//准备回复楼层数据（父回复）
		new QueryHelper(TalkReply.class, "tr") //From TalkReply tr
		.addCondition("tr.talk=?", talk) //
		.addCondition("tr.parent is null")
		.addOrderProperty("tr.postTime", true)
		.perparePageBean(talkService, currentPage, pageSize);
		
		/*List<Reply> childrenList = new  ArrayList<Reply>();
		//准备子回复数据
		for (Reply reply : replyList) {
			if(reply.getParent()!=null){
						
				//List<Reply> childrenList = ReplyUtil.getAllReplys(reply,replyList); //获取主回复对应的子回复
				
				childrenList.add(reply);	
				
			}		
		}
		for (Reply r : childrenList) {
			System.out.println("我是附属ID：" +r.getId());
		}
		ActionContext.getContext().put("childrenList", childrenList);*/
		
		return "show";
	}
	
	public void bacthDelete(){
		String[] userId = getIds().split(",");
		int count = userId.length;
		
		try {
			for (int i = 0; i < count; i++) {
				if(userId[i].equals(""))
					throw new NullPointerException();
				
				talkService.delete((long)Integer.parseInt(userId[i]));
			} 
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		
		try {
			HttpServletResponse response =ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("删除成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
    
}
