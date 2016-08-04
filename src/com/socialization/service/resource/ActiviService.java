package com.socialization.service.resource;

import java.util.List;

import com.socialization.base.DaoSupport;
import com.socialization.domain.Activity;
import com.socialization.domain.ActivityType;
import com.socialization.domain.Topic;
import com.socialization.domain.User;

public interface ActiviService extends DaoSupport<Activity>{

	List<Activity> findByName(String activiName);

	void save(Activity activity);

	void deleteUserWant(User user, Activity activityDetails);

	List<Activity> findByAcType(ActivityType activityType);//这个方法不用

	void save(User currentUser, Activity activityDetails);

	void insertWantIn(User currentUser, Activity activityDetails);

	List<User> getByAcWant(Activity activityDetails);

	List<Activity> findInterAct(User user);

	void deleteUserInterest(User user, Activity activityDetails);

	List<Activity> findUserPartAct(User user);

	List<Activity> findUserCreateAct(User user);

	List<Activity> findByAcTypeIdOrderByStAsc(Long id);

	List<Activity> getHostTen();

	List<Activity> findByAcTypeIdOrderByNumDesc(Long id);

	boolean checkInterestExit(Activity activity, User currentUser);

	boolean checkPartInExit(Activity activity, User currentUser);

	boolean checkWantInExit(Activity activity, User currentUser);

	List<User> findPartInMem(Activity activityDetails);

	List<Activity> findApply(Long id);

	List<Activity> findByUserTags(User user);

	/**
	 * @desc 根据关键字进行模糊查询
	 * @param str 关键字
	 * @return activity集合
	 * @author yanbaobin@yeah.net
	 * @date Aug 14, 2015 9:14:41 AM
	 */
	List<Activity> fuzzySearch(String str);
	
	/**
	 * @desc order by the number of interests,get top 10
	 * @return activity set
	 * @author yanbaobin@yeah.net
	 * @date Aug 14, 2015 10:58:25 AM
	 */
	List<Activity> getTop10();
}
