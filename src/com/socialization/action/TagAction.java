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
public class TagAction extends BaseAction<Tag>{

	/** 列表 */
	public String list() throws Exception {
		List<Tag> tagList = tagService.findAll();
		ActionContext.getContext().put("tagList", tagList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		tagService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		tagService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		Tag tag = tagService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(tag);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		Tag tag = tagService.getById(model.getId());
		
		tag.setName(model.getName());
		tag.setDescription(model.getDescription());
		
		tagService.update(tag);
		return "toList";
	}
	
	

}
