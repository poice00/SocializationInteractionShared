package com.socialization.serviceImpl.resource;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialization.base.DaoSupportImpl;
import com.socialization.domain.ActTopicReply;
import com.socialization.service.resource.ActTopicReplyService;

@Service
@Transactional
public class ActTopicReplyImpl extends DaoSupportImpl<ActTopicReply> implements ActTopicReplyService{

	@SuppressWarnings("unchecked")
	@Override
	public List<ActTopicReply> findByActTopId(Long id) {
		
		return getSession().createQuery("FROM ActTopicReply a WHERE a.actTopic.id= ? ")
				.setParameter(0, id)
				.list();
	}

}
