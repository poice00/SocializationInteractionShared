package com.socialization.service;

import java.util.List;

import com.socialization.base.DaoSupport;
import com.socialization.domain.Social;
import com.socialization.domain.Talk;
import com.socialization.domain.User;

public interface SocialService extends DaoSupport<Talk>{

	List<Social> findByContent(Talk content);

	List<Social> findByFriends(User user);
}
