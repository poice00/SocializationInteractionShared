package com.socialization.service;

import java.util.List;

import com.socialization.base.DaoSupport;
import com.socialization.domain.Talk;
import com.socialization.domain.User;

public interface TalkService extends DaoSupport<Talk>{

	List<Talk> findByFriends(User user);

	List<Talk> getTalkListById(Long id);

	List<Talk> findByContent();

	Talk findByContent(String content);


	Talk getnextTalkById(Long id, String content);

	Talk getbeforeTalkById(Long id, String content);

	void insertZan(Long id, Long id2);

	void deleteZan(Long id, Long id2);

	List<User> findByZan(Long talkId);

	boolean CheckZanExit(Long id, Long id2);


	
}
