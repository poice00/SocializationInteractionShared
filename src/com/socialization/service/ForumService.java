package com.socialization.service;

import java.util.List;

import com.socialization.base.DaoSupport;
import com.socialization.domain.Forum;

public interface ForumService extends DaoSupport<Forum> {

	void moveUp(Long id);

	void moveDown(Long id);

	List<Forum> findforumRankList();

}
