package com.socialization.service.resource;

import java.util.List;

import com.socialization.base.DaoSupport;
import com.socialization.domain.ActTopicReply;

public interface ActTopicReplyService extends DaoSupport<ActTopicReply>{

	List<ActTopicReply> findByActTopId(Long id);

}
