package com.socialization.action.resource;



import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientException;

import com.opensymphony.xwork2.ActionContext;
import com.socialization.base.BaseAction;
import com.socialization.domain.ResType;
import com.socialization.domain.Tag;



@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TagResAction extends BaseAction<ResType> {


	public String addUI() throws Exception{
		return "addUI";
	}
	public String list() throws Exception{
		List<ResType> resTypesList=this.resStyleService.findAll();
		ActionContext.getContext().put("resTypesList", resTypesList);
		return "list";
	}
	public String add() throws Exception{
		model.setDescription(model.getDescription());
		model.setName(model.getName());
		this.resStyleService.save(model);
		return "addSuc";
	}

}
