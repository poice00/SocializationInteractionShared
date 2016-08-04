package com.socialization.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialization.base.DaoSupportImpl;
import com.socialization.domain.Social;
import com.socialization.domain.Talk;
import com.socialization.domain.User;
import com.socialization.service.SocialService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class SocialServiceImpl extends DaoSupportImpl<Talk> implements SocialService{

	@Override
	public List<Social> findByContent(Talk content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Social> findByFriends(User user) {
		return getSession().createSQLQuery("select * from talk where userId in (select friendId from user_friend where userId = ?)").addEntity(Talk.class).setParameter(0, user.getId()).list();
	}

}
