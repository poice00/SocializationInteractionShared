package com.socialization.service.resource;

import java.util.Date;
import java.util.List;

import com.socialization.base.DaoSupport;
import com.socialization.domain.ActApply;
import com.socialization.domain.Activity;
import com.socialization.domain.User;

public interface ActApplyService extends DaoSupport<ActApply>{

	boolean checkApplyExit(Activity activityDetails, User currentUser);

	void saveActApply(Activity activity, User user, Date date, String string);

	void deleteUserApply(User user, Activity activityDetails);

	int checkPartInExit(Activity activityDetails, User currentUser);

	List<ActApply> findByUserId(Long id);

	List<User> findUsers(Long id);

	List<User> findMemUsers(Long idd);

}
