package com.socialization.service;

import java.util.List;

import com.socialization.base.DaoSupport;
import com.socialization.domain.Talk;
import com.socialization.domain.TalkReply;

public interface TalkReplyService extends DaoSupport<TalkReply>{

	TalkReply getLastReplyByTalk(Talk talk, TalkReply tr);
	List<TalkReply> findByTalk(Talk talk);
	int getCountByTalk(Talk talk);

}
