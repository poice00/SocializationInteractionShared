package com.socialization.service;

import java.util.List;

import com.socialization.base.DaoSupport;
import com.socialization.domain.Forum;
import com.socialization.domain.Reply;
import com.socialization.domain.ResReply;
import com.socialization.domain.Resource;
import com.socialization.domain.Topic;

public interface ReplyService extends DaoSupport<Reply> {

	List<Reply> findByTopic(Topic topic);
	int getCountByTopic(Topic topic);
	Reply getLastReplyByTopic(Topic topic, Reply r);
}
