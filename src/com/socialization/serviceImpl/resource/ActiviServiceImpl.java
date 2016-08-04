package com.socialization.serviceImpl.resource;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialization.base.DaoSupportImpl;
import com.socialization.domain.Activity;
import com.socialization.domain.ActivityType;
import com.socialization.domain.User;
import com.socialization.service.resource.ActiviService;


@Service
@Transactional
public class ActiviServiceImpl extends DaoSupportImpl<Activity> implements ActiviService{

	//搜索方法
	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> findByName(String activiName) {
		String sql="FROM Activity a WHERE a.activityName LIKE '%"+activiName+"%' OR a.activitydescription LIKE '%"+activiName+"%'";
		return getSession().createQuery(sql).list();
	}
	@Override
	public void save(Activity activity){
	getSession().save(activity);
	//维护特殊数据
	ActivityType activityType=activity.getActivityType();
	if (activityType.getActivityCount()==0) {
		activityType.setActivityCount(1);
	} else {
		activityType.setActivityCount(activityType.getActivityCount()+1);
		getSession().update(activityType);
	}
	}
	
	//未通过时删除相应的申请表
	@Override
	public void deleteUserWant(User user, Activity activityDetails) {
      getSession().createSQLQuery("delete from user_wantinacty where userId = ? and activityId = ?").setParameter(0, user.getId()).setParameter(1, activityDetails.getId()).executeUpdate();	
	}

	//更新感兴趣的列表
	@Override
	public void save(User currentUser, Activity activityDetails) {
	 String sql="insert into user_interacty(userId,activityId) values ("+currentUser.getId()+","+activityDetails.getId()+")";
	 getSession().createSQLQuery(sql).executeUpdate();
	}
	//更新想参加的人员列表
	@Override
	public void insertWantIn(User currentUser, Activity activityDetails) {
    String sql="insert into user_wantInacty(userId,activityId) values ("+currentUser.getId()+","+activityDetails.getId()+")";
	getSession().createSQLQuery(sql).executeUpdate();
	}
	//查询该活动想参加的人员
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getByAcWant(Activity activityDetails) {
		String sql="select * from user where id in (select userId from user_wantinacty where activityId = "+activityDetails.getId()+")";
		return getSession().createSQLQuery(sql).addEntity(User.class).list();
	}
	//查询用户感兴趣的活动
	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> findInterAct(User user) {
		String sql="select * from activity where id in (select activityId from user_interacty where userId = "+user.getId()+")";
		return getSession().createSQLQuery(sql).addEntity(Activity.class).list();
	}
	//用户取消感兴趣的活动
	@Override
	public void deleteUserInterest(User user, Activity activityDetails) {
		String sql="delete from user_interacty where userId = "+user.getId()+" and activityId = "+activityDetails.getId();
		getSession().createSQLQuery(sql).executeUpdate();
		activityDetails.setInterestUserCount(activityDetails.getInterestUserCount()-1);
		getSession().update(activityDetails);
	}
	//查询用户参加的活动
	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> findUserPartAct(User user) {
		String sql="select * from activity where id in (select activityId from user_activity where userId = "+user.getId()+")";
		return getSession().createSQLQuery(sql).addEntity(Activity.class).list();
	}
	//查询用户创建的活动
	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> findUserCreateAct(User user) {
		String sql="select * from activity where userId = "+user.getId();
		return getSession().createSQLQuery(sql).addEntity(Activity.class).list();
	}
	//查询某一类型的活动列表并按发布时间的降序排序
	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> findByAcTypeIdOrderByStAsc(Long id) {
		String sql="select * from activity where activityTypeId = "+id+" order by postTime desc";
		return getSession().createSQLQuery(sql).addEntity(Activity.class).list();
	}
	
	// 查找某个类型的活动，不按时间排序
	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> findByAcType(ActivityType activityType) {
		return getSession().createQuery("FROM Activity a WHERE a.activityType=?").setParameter(0, activityType).list();
	}
	//查找3个最热门的活动
	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> getHostTen() {
		String sql="select * from activity order by activityMemNum desc";
		return getSession().createSQLQuery(sql).addEntity(Activity.class).setMaxResults(3).list();
	}
	
	//查询某一类型的活动列表并按参加人数降序排列
	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> findByAcTypeIdOrderByNumDesc(Long id) {
		String sql="select * from activity where activityTypeId = "+id+" order by activityMemNum desc";
		return getSession().createSQLQuery(sql).addEntity(Activity.class).setMaxResults(3).list();
	}
	//检查是否已经感兴趣
	@Override
	public boolean checkInterestExit(Activity activity, User currentUser) {
		if (currentUser==null) {
			return false;
		}
        String sql="select * from  user_interacty where userId = "+currentUser.getId()+" and activityId = "+activity.getId();
        if (getSession().createSQLQuery(sql).list().size()==0) {
        	return false;
		} else {
			return true;
		}		
	}
	//检查是否已经在参加列表
	@Override
	public boolean checkPartInExit(Activity activity, User currentUser) {
		 String sql="select * from  user_activity where userId = "+currentUser.getId()+" and activityId = "+activity.getId();
	        if (getSession().createSQLQuery(sql).list().size()==0) {
	        	return false;
			} else {
				return true;
			}	
	}
	//检查是否已经在申请列表里面
	@Override
	public boolean checkWantInExit(Activity activity, User currentUser) {
		String sql="select * from  user_wantinacty where userId = "+currentUser.getId()+" and activityId = "+activity.getId();
        if (getSession().createSQLQuery(sql).list().size()==0) {
        	return false;
		} else {
			return true;
		}
	}
	//查询参加的人员列表
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findPartInMem(Activity activityDetails) {
		String sql="select * from user where id in (select userId from user_interacty where activityId = "+activityDetails.getId()+" )";
		return getSession().createSQLQuery(sql).addEntity(User.class).setMaxResults(6).list();
	}	
	//查询申请的活动列表
	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> findApply(Long id) {
		String sql="select * from activity where id in (select activityId from actapply where userId = "+id+")";
		return getSession().createSQLQuery(sql).addEntity(Activity.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> findByUserTags(User user) {
		String sql="select * from activity where activityName like '%"+user.getUsTags()+"%'";
		return getSession().createSQLQuery(sql).addEntity(Activity.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * @desc 根据关键字进行模糊查询
	 * @param str 关键字
	 * @return activity集合
	 * @author yanbaobin@yeah.net
	 * @date Aug 14, 2015 9:14:41 AM
	 */
	public List<Activity> fuzzySearch(String str) {
		if(str.length() == 0)
			return null;
		
		/*生成模糊字符串*/
		StringBuilder sb = new StringBuilder("%");
		
		for (int i = 0; i < str.length(); i++)
			sb.append(str.charAt(i)).append("%");
		
		return getSession().createQuery(
				"FROM Activity WHERE activityName like ?")
				.setParameter(0, sb.toString())
				.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	/**
	 * @desc order by the number of interests,get top 10
	 * @return activity set
	 * @author yanbaobin@yeah.net
	 * @date Aug 14, 2015 10:58:25 AM
	 */
	public List<Activity> getTop10() {
		return getSession().createQuery(
				"FROM Activity ORDER BY interestUserCount DESC")
				.setMaxResults(10)
				.list();
	}
}
