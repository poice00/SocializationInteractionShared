package com.socialization.serviceImpl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialization.base.DaoSupportImpl;
import com.socialization.domain.Forum;
import com.socialization.domain.Reply;
import com.socialization.domain.Topic;
import com.socialization.service.ForumService;
import com.socialization.service.ReplyService;
import com.socialization.service.TopicService;
@Service
@Transactional
@SuppressWarnings("unchecked")
public class ReplyServiceImpl extends DaoSupportImpl<Reply> implements ReplyService{

	@Override
	public List<Reply> findByTopic(Topic topic) {
		return (List<Reply>) getSession().createQuery(//
				"FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//
				.setParameter(0, topic)//
				.list();
	}
	
	@Override
	public void save(Reply reply) {
		//保存
				getSession().save(reply);
				
				//维护特殊数据
				Topic topic = reply.getTopic();

				topic.setLastReply(reply);
				topic.setReplyCount(topic.getReplyCount() + 1);
				topic.setLastUpdateTime(reply.getPostTime());

				getSession().update(topic);
	}

	@Override
	public int getCountByTopic(Topic topic) {
		Long count = (Long) getSession().createQuery(//
				"SELECT COUNT(*) FROM Reply r WHERE r.topic=?")//
				.setParameter(0, topic)//
				.uniqueResult();
		return count.intValue();
	}

	@Override
	public Reply getLastReplyByTopic(Topic topic,Reply r) {
		Reply reply =  (Reply) getSession().createQuery(//
				"FROM Reply r WHERE r.topic=? AND r.postTime<? ORDER BY r.postTime DESC")//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.setParameter(0, topic)//
				.setParameter(1, r.getPostTime())//
				.uniqueResult();
		return reply;
	}



}
