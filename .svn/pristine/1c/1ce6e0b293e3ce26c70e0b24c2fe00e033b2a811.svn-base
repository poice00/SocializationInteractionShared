package com.socialization.service.resource;

import java.util.List;

import com.socialization.base.DaoSupport;
import com.socialization.domain.ResReply;
import com.socialization.domain.Resource;

public interface ResReplyService extends DaoSupport<ResReply>{

	List<ResReply> getbyResID(Resource resource);
	
	void deleteByResId(Resource model);

	void delete(ResReply resReply);

	void save(ResReply resReply, float currentscore);

	List<ResReply> getbyResIDDesc(Resource byId);
}
