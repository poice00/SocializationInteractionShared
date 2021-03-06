package com.socialization.action;

import java.util.ArrayList;
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
import com.socialization.util.QueryHelper;
import com.socialization.util.ReplyUtil;

/**
 * 主题管理
 * @author ssy
 *
 */
@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic>{
	
	private Long forumId; //所属的论坛
	private Long otherForumId; //移动的目标板块
	
	
	
	/** 显示单个主题（主贴+回帖） */
	public String show() throws Exception {
		//准备数据主贴
		Topic topic = this.topicService.getById(model.getId());
		int count = topic.getCount() ;
		topic.setCount(++count);
		
		topicService.update(topic);
		ActionContext.getContext().put("topic", topic);
		
		//准备上一篇帖子
		Topic beforeTopic = this.topicService.getbeforeTopicById(model.getId(),topic.getForum());
		ActionContext.getContext().put("beforeTopic", beforeTopic);
		
		//准备下一篇帖子
		Topic nextTopic = this.topicService.getnextTopicById(model.getId(),topic.getForum());
		ActionContext.getContext().put("nextTopic", nextTopic);
		
		//准备当前用户
		if(getCurrentUser()!=null){
			User user =userService.getById(getCurrentUser().getId());
			ActionContext.getContext().put("user", user);
		}
		
		
//		List<Reply> replyList = this.replyService.findByTopic(topic);
//		List<Reply> topReplyList=new ArrayList<Reply>();
		//准备回复楼层数据（父回复）
		new QueryHelper(Reply.class, "r")
		.addCondition("r.topic=?", topic)
		.addCondition("r.parent is null")
		.addOrderProperty("r.postTime", true)
		.perparePageBean(topicService, currentPage, pageSize);
		
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
	

	/** 发表新主题页面 */
	public String addUI() throws Exception {
		//准备数据
		Forum forum = this.forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "addUI";
	}
	/** 删除主题 */
	public String delete() throws Exception {
		
		Forum forum = forumService.getById(forumId);
		
		Topic t = topicService.getById(model.getId());//待删除的帖子
		Topic topic = forum.getLastTopic(); //当前板块的最新的发帖
		
		if(t==topic){
			Topic other = topicService.getLastTopicByForum(forum,t);
			forum.setLastTopic(other);//把最新主题设置为最新主题
		}
		
		topicService.delete(model.getId()); //删除
		
		
		
		
		forum.setTopicCount(forum.getTopicCount() - 1);
		forumService.update(forum);
		
		return "toShowTopicList";
	}

	/** 发表新主题 */
	public String add() throws Exception {
		//封装
		//Topic topic =new Topic();
		//-->>表单参数
		//model.setContent(content);
		//model.setTitle(title);
		
		Forum forum = forumService.getById(getForumId());
		model.setForum(forum);
		
		/*Tag tag = new Tag();
		tag.setName(tagName);
		tagService.save(tag);

		model.setTags(new HashSet<Tag>(tag));*/
		//--〉〉直接获取的信息
		model.setUser(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());

		
		//保存
		this.topicService.save(model);
		return "toShow";
	}
	
	/** 设置精华帖*/
	public String setBest() throws Exception {
		Topic topic = this.topicService.getById(model.getId());
		topic.setType(Topic.TYPE_BEST);
		topicService.update(topic);
		return "toShowTopicList";
	}
	
	/** 设置置顶帖*/
	public String setTop() throws Exception {
		Topic topic = this.topicService.getById(model.getId());
		topic.setType(Topic.TYPE_TOP);
		topicService.update(topic);
		return "toShowTopicList";
	}
	
	/** 设置普通帖*/
	public String setNormal() throws Exception {
		Topic topic = this.topicService.getById(model.getId());
		topic.setType(Topic.TYPE_NORMAL);
		topicService.update(topic);
		return "toShowTopicList";
	}
	/** 移动到其他版块*/
	public String moveUI() throws Exception {
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "moveUI";
	}
	
	/** 设置我的收藏*/
	public String setCollection() throws Exception{
		//1.获取当前主题
		Topic topic = this.topicService.getById(model.getId());
		System.out.println("=========topic==========" + topic.getTitle());
		//2.获取当前用户
		User u = userService.getById(getCurrentUser().getId());
		System.out.println("============" + u.getName());
		//3.保存
		u.getCollectionTopics().add(topic);
		userService.update(u);
		return "toShowTip";
		
	}
	
	/** 移动*/
	public String move() throws Exception {
		Topic topic = topicService.getById(model.getId());
		Forum forum = topic.getForum();
		Forum other = forumService.getById(otherForumId);
		
		topic.setForum(forumService.getById(otherForumId));
		forum.setTopicCount(forum.getTopicCount() - 1);
		other.setTopicCount(other.getTopicCount() + 1);
		
		forumService.update(forum);
		forumService.update(other);
		

		
		return "toShowTopicList";
	}
	
	
	

	/** 删除收藏贴*/
	public String deleteCollection() throws Exception {
		Topic topic = topicService.getById(model.getId());
		
		User u = userService.getById(getCurrentUser().getId());
		u.getCollectionTopics().remove(topic);
		topicService.update(topic);
		
		return "toCollection";
	}
	
	//-------------------

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	public Long getOtherForumId() {
		return otherForumId;
	}

	public void setOtherForumId(Long otherForumId) {
		this.otherForumId = otherForumId;
	}


	
	

}
