package com.socialization.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialization.base.DaoSupportImpl;
import com.socialization.domain.Activity;
import com.socialization.domain.Forum;
import com.socialization.domain.Reply;
import com.socialization.domain.Topic;
import com.socialization.domain.User;
import com.socialization.service.ForumService;
import com.socialization.service.TopicService;
@Service
@Transactional
@SuppressWarnings("unchecked")
public class TopicServiceImpl extends DaoSupportImpl<Topic> implements TopicService{

	@Override
	public List<Topic> findByForm(Forum forum) {
		
		return getSession().createQuery(//
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.list();
	}
	
	
	@Override
	public void save(Topic topic) {
		//1.设置相关属性
		topic.setType(topic.TYPE_NORMAL);
		topic.setLastReply(null);
		topic.setLastUpdateTime(topic.getPostTime());
		topic.setReplyCount(0);
		getSession().save(topic);
		//2.维护相关属性
		Forum forum = topic.getForum();
		forum.setTopicCount(forum.getTopicCount() + 1);
		forum.setLastTopic(topic);
		
		getSession().update(forum);
	}


	@Override
	public Topic getLastTopicByForum(Forum forum, Topic t) {
		Topic topic =  (Topic) getSession().createQuery(//
				"FROM Topic t WHERE t.forum=? AND t.postTime<? ORDER BY t.postTime DESC")//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.setParameter(0, forum)//
				.setParameter(1, t.getPostTime())//
				.uniqueResult();
		return topic;
	}


	@Override
	public List<Topic> findLastestTopics() {
				
		return getSession().createQuery(//
					"FROM Topic t ORDER BY t.postTime DESC")//
					.setFirstResult(0)//
					.setMaxResults(30)//
					.list();
	}


	@Override
	public List<Topic> findhotTopics() {
		return getSession().createQuery(//
				"FROM Topic t ORDER BY t.replyCount DESC")//
				.setFirstResult(0)//
				.setMaxResults(10)//
				.list();
	}


	@Override
	public List<Topic> getMyTopics(Long id) {
		
		return getSession().createQuery(//
				"FROM Topic t WHERE t.user.id = ? ORDER BY t.postTime DESC")//
				.setParameter(0, id)//
				.list();
	}


	@Override
	public Topic getbeforeTopicById(Long id,Forum forum) {
		return (Topic) getSession().createQuery(//
				"FROM Topic t WHERE t.id<? AND t.forum=? ORDER BY t.id DESC")//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.setParameter(0, id)//
				.setParameter(1, forum)////
				.uniqueResult();
	}


	@Override
	public Topic getnextTopicById(Long id,Forum forum) {
		return (Topic) getSession().createQuery(//
				"FROM Topic t WHERE t.id>? AND t.forum=? ORDER BY t.id ASC")//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.setParameter(0, id)//
				.setParameter(1, forum)//
				.uniqueResult();
	}


	@Override
	public List<Topic> findLookTopics() {
		return getSession().createQuery(//
				"FROM Topic t ORDER BY t.count DESC")//
				.setFirstResult(0)//
				.setMaxResults(3)//
				.list();
	}

	@Override
	public List<Topic> findByUserTags(User user) {
		String sql="select * from topic where title like '%"+user.getUsTags()+"%'";
		return getSession().createSQLQuery(sql).addEntity(Topic.class).list();
	}

	@Override
	/**
	 * @desc 根据关键字进行模糊查询
	 * @param str 关键字
	 * @return Topic集合
	 * @author yanbaobin@yeah.net
	 * @date Aug 14, 2015 9:08:28 AM
	 */
	public List<Topic> fuzzySearch(String str) {
		if(str.length() == 0)
			return null;
		
		/*生成模糊字符串*/
		StringBuilder sb = new StringBuilder("%");
		
		for (int i = 0; i < str.length(); i++)
			sb.append(str.charAt(i)).append("%");
		
		return getSession().createQuery(
				"FROM Topic WHERE title like ?")
				.setParameter(0, sb.toString())
				.list();
	}








	


}
