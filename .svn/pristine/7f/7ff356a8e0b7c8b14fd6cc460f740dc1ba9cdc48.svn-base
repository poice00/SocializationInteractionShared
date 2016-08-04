package com.socialization.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialization.base.DaoSupportImpl;
import com.socialization.domain.Talk;
import com.socialization.domain.TalkReply;
import com.socialization.service.TalkReplyService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class TalkReplyServiceImpl extends DaoSupportImpl<TalkReply> implements TalkReplyService{

	@Override
	public List<TalkReply> findByTalk(Talk talk) {
		return (List<TalkReply>) getSession().createQuery(//
				"FROM TalkReply r WHERE tr.talk=? ORDER BY tr.postTime ASC")//
				.setParameter(0, talk)//
				.list();
	}
	
	@Override
	public void save(TalkReply talkreply) {
		//保存
				getSession().save(talkreply);
				
				//维护特殊数据
				Talk talk = talkreply.getTalk();

				talk.setLastReply(talkreply);
				talk.setReplyCount(talk.getReplyCount() + 1);
				talk.setLastUpdateTime(talkreply.getPostTime());

				getSession().update(talk);
	}

	@Override
	public int getCountByTalk(Talk talk) {
		Long count = (Long) getSession().createQuery(//
				"SELECT COUNT(*) FROM TalkReply r WHERE tr.talk=?")//
				.setParameter(0, talk)//
				.uniqueResult();
		return count.intValue();
	}

	@Override
	public TalkReply getLastReplyByTalk(Talk talk,TalkReply tr) {
		TalkReply talkreply =  (TalkReply) getSession().createQuery(//
				"FROM TalkReply r WHERE tr.talk=? AND tr.postTime<? ORDER BY tr.postTime DESC")//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.setParameter(0, talk)//
				.setParameter(1, tr.getPostTime())//
				.uniqueResult();
		return talkreply;
	}
}
