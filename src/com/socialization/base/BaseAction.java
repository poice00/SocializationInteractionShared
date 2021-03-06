package com.socialization.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.socialization.domain.User;
import com.socialization.service.ForumService;
import com.socialization.service.InformationService;
import com.socialization.service.MessageService;
import com.socialization.service.PrivilegeService;
import com.socialization.service.ReplyService;
import com.socialization.service.RoleService;
import com.socialization.service.SocialService;
import com.socialization.service.TagService;
import com.socialization.service.TalkReplyService;
import com.socialization.service.TalkService;
import com.socialization.service.TopicService;
import com.socialization.service.UserService;
import com.socialization.service.resource.ActAlbumPhotoService;
import com.socialization.service.resource.ActAlbumReplyService;
import com.socialization.service.resource.ActAlbumService;
import com.socialization.service.resource.ActApplyService;
import com.socialization.service.resource.ActTopicReplyService;
import com.socialization.service.resource.ActTopicService;
import com.socialization.service.resource.ActiviService;
import com.socialization.service.resource.ActiviTypeService;
import com.socialization.service.resource.ResReplyService;
import com.socialization.service.resource.ResStyleService;
import com.socialization.service.resource.TagResourceService;
import com.socialization.service.resource.UpdowncollectResService;



@SuppressWarnings({ "unchecked", "serial" })
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	// =========================================================
	protected T model;
	
	// =========分页用的参数=============
	protected int currentPage = 1;
	protected int pageSize = 5;

	public BaseAction() {
		try {
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			model = (clazz.newInstance());
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public T getModel() {

		return model;
	}
	
	
	/**
	 * 获取当前登陆用户
	 */
	protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// =============================================================
	@Resource
	protected UserService userService;
	
	@Resource
	protected RoleService roleService;
	
	@Resource
	protected PrivilegeService privilegeService;

	@Resource
	protected ForumService forumService;
	
	@Resource
	protected TopicService topicService;
	
	@Resource
	protected TagService tagService;
	
	@Resource
	protected TalkService talkService;
	@Resource
	protected TalkReplyService talkreplyService;
	@Resource
	protected SocialService socialService;
	
	@Resource
	protected ReplyService replyService;
	@Resource
	protected UpdowncollectResService updowncollectResService;
	@Resource
	protected ResReplyService resReplyService;
    @Resource
    protected TagResourceService tagResourceService;
	
	@Resource
	protected ResStyleService resStyleService;
	@Resource
	protected MessageService messageService;

	@Resource
	protected ActiviService activiService;

	@Resource
	protected InformationService informationService;

	@Resource
	protected ActiviTypeService activiTypeService;
	@Resource
	protected ActApplyService actApplyService;
	@Resource
	protected ActAlbumService actAlbumService;
	@Resource
	protected ActAlbumReplyService actAlbumReplyService;
	@Resource
	protected ActAlbumPhotoService actAlbumPhotoService;
	@Resource
	protected ActTopicService actTopicService;
	@Resource
	protected ActTopicReplyService actTopicReplyService;
}
