package com.socialization.service;

import java.util.List;

import com.socialization.base.DaoSupport;
import com.socialization.domain.Forum;
import com.socialization.domain.Reply;
import com.socialization.domain.Topic;
import com.socialization.domain.User;

public interface TopicService extends DaoSupport<Topic> {

	List<Topic> findByForm(Forum forum);

	Topic getLastTopicByForum(Forum forum, Topic t);

	List<Topic> findLastestTopics();

	List<Topic> findhotTopics();

	List<Topic> getMyTopics(Long id);

	Topic getbeforeTopicById(Long id, Forum forum);

	Topic getnextTopicById(Long id, Forum forum);

	List<Topic> findLookTopics();

	List<Topic> findByUserTags(User user);

	/**
	 * @desc 根据关键字进行模糊查询
	 * @param str 关键字
	 * @return Topic集合
	 * @author yanbaobin@yeah.net
	 * @date Aug 14, 2015 9:08:28 AM
	 */
	List<Topic> fuzzySearch(String str);
}
