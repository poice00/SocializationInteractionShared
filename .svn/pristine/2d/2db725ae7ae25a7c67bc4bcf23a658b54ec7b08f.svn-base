package com.socialization.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialization.base.DaoSupportImpl;
import com.socialization.domain.Talk;
import com.socialization.domain.User;
import com.socialization.service.TalkService;
@Service
@Transactional
@SuppressWarnings("unchecked")
public class TalkServiceImpl extends DaoSupportImpl<Talk> implements TalkService{

	@Override
	public List<Talk> findByContent() {
		return (List<Talk>) getSession().createQuery(//
				"FROM Talk r WHERE r.content=? ORDER BY r.postTime DESC")//
				.list();
	}

	@Override
	public Talk findByContent(String content) {
		return null;
	}

	@Override
	public List<Talk> findByFriends(User user) {
		return getSession().createSQLQuery("select * from talk where userId in (select friendId from user_friend where userId = ?) order by postTime desc").addEntity(Talk.class).setParameter(0, user.getId()).list();
	}

	@Override
	public List<Talk> getTalkListById(Long id) {
		return getSession().createQuery(//
				"FROM Talk r WHERE r.user.id=? ORDER BY r.postTime DESC")//
				.setParameter(0, id)//
				.list();
	}
	

	@Override
	public Talk getbeforeTalkById(Long id, String content) {
		return (Talk) getSession().createQuery(//
				"FROM Talk t WHERE t.id<? AND t.content=? ORDER BY t.id DESC")//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.setParameter(0, id)//
				.setParameter(1, content)////
				.uniqueResult();
	}


	@Override
	public Talk getnextTalkById(Long id, String content) {
		return (Talk) getSession().createQuery(//
				"FROM Talk t WHERE t.id>? AND t.content=? ORDER BY t.id ASC")//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.setParameter(0, id)//
				.setParameter(1, content)//
				.uniqueResult();
	}

	@Override
	public void insertZan(Long id, Long id2) {
		getSession().createSQLQuery("insert into user_talkzan(userId,talkId) values(?,?)").setParameter(0, id).setParameter(1, id2).executeUpdate();
		
	}

	@Override
	public void deleteZan(Long id, Long id2) {
		getSession().createSQLQuery("delete from user_talkzan where userId = ? and talkId = ? ").setParameter(0, id).setParameter(1, id2).executeUpdate();
	}

	@Override
	public List<User> findByZan(Long talkId) {
		String sql="select * from user where id in (select userId from user_talkzan where talkId = "+talkId+")";
		return getSession().createSQLQuery(sql).addEntity(User.class).list();
	}

	@Override
	public boolean CheckZanExit(Long id, Long id2) {
		if (getSession().createSQLQuery("select * from user_talkzan where userId = ? and talkId = ?").setParameter(0, id2).setParameter(1, id).list().size()==0) {
			return true;
		}
		return false;
	}
}
