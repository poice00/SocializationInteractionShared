package com.socialization.action;

import java.util.List;

import javax.management.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.socialization.base.BaseAction;
import com.socialization.domain.Forum;
import com.socialization.domain.Topic;
import com.socialization.domain.User;
import com.socialization.util.QueryHelper;

/**
 * 版块
 * @author ssy
 *
 */
@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum>{
	/**
	 * 0表示查看全部主题<br>
	 * 1表示只查看精华贴
	 * 2表示只查看置顶帖
	 */
	private int viewType = 0;

	/**
	 * 0表示默认排序（按最后更新时间排序，但所有置顶帖都在前面）<br>
	 * 1表示按最后更新时间排序<br>
	 * 2表示按主题发表时间排序<br>
	 * 3表示按回复数量排序
	 */
	private int orderBy = 0;

	/**
	 * true 表示升序 false 表示降序
	 */
	private boolean asc = false;
	
	/** 论坛模块欢迎 */
	public String welcome() throws Exception {
		return "welcome";
	}
	/** 论坛模块首页 */
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
	/** 板块列表 */
	public String list() throws Exception {
		//准备版块列表
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		
		//准备帖子数量 和 最新发表的帖子
		return "list";
	}


	/** 显示单个版块（主题列表） */ 
	public String show() throws Exception {
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);
		

		/*// 准备数据topicList
		 List<Topic> topicList = this.topicService.findByForm(forum);
		 ActionContext.getContext().put("topicList", topicList);*/
		
		//准备分页数据
		new QueryHelper(Topic.class, "t")//
			// 查询条件
			.addCondition("t.forum=?", forum)//
			.addCondition((viewType==1), "t.type=?",Topic.TYPE_BEST)//
			.addCondition((viewType==2), "t.type=?",Topic.TYPE_TOP)//
			// 排序条件
			.addOrderProperty((orderBy == 1), "t.lastUpdateTime", asc)
			.addOrderProperty((orderBy == 2), "t.postTime", asc)
			.addOrderProperty((orderBy == 3), "t.replyCount", asc)
			.addOrderProperty((orderBy == 0), "(CASE t.type WHEN 2 THEN 2 ELSE 0 END)", false)
			.addOrderProperty((orderBy == 0), "t.lastUpdateTime", false)
			.perparePageBean(forumService, getCurrentPage(), getPageSize());
		
		return "show";
	}


	public int getViewType() {
		return viewType;
	}


	public void setViewType(int viewType) {
		this.viewType = viewType;
	}


	public int getOrderBy() {
		return orderBy;
	}


	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}


	public boolean isAsc() {
		return asc;
	}


	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	
	

}
