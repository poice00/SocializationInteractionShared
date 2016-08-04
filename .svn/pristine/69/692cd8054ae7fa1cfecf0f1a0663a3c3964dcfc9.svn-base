package com.socialization.serviceImpl.resource;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialization.base.DaoSupportImpl;
import com.socialization.domain.ActTopic;
import com.socialization.service.resource.ActTopicService;
@Service
@Transactional
public class ActTopicServiceImpl extends DaoSupportImpl<ActTopic> implements ActTopicService{

	@SuppressWarnings("unchecked")
	@Override
	public List<ActTopic> findByActId(Long idd) {
		return getSession().createQuery(
				"FROM ActTopic a WHERE a.activity.id = ?").setParameter(0, idd)
				.list();
	}

}
