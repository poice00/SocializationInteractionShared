package com.socialization.service;

import java.util.List;

import com.socialization.base.DaoSupport;
import com.socialization.domain.User;

public interface UserService extends DaoSupport<User> {

	User findByLoginNameAngPassword(String loginName, String password);
	

	/**
	 * @desc 判断用户名是否存在
	 * @param loginName 用户名
	 * @return 如果存在，返回true，否则返回false
	 * @author yanbaobin@yeah.net
	 * @date Aug 3, 2015 11:55:26 AM
	 */
	boolean isExist(String loginName);
	
	/**
	 * @desc 根据登录名获取用户实体类
	 * @param loginName 登录名
	 * @return 用户类对象
	 * @author yanbaobin@yeah.net
	 * @date Aug 7, 2015 9:53:32 AM
	 */
	User findByLoginName(String loginName);
	
	/**
	 * @desc 对用户名进行模糊查询
	 * @param str
	 * @return 符合条件的User实体集合
	 * @author yanbaobin@yeah.net
	 * @date Aug 7, 2015 8:22:40 PM
	 */
	List<User> fuzzySearch(String str);
	
	List<User> OrderByupCount(String st);


	List<User> findFansCount();
	

}
