package com.socialization.service;

import java.util.List;

import com.socialization.base.DaoSupport;
import com.socialization.domain.Role;;

public interface RoleService extends DaoSupport<Role> {

	/**
	 * @desc 根据角色名获取其id
	 * @param name 角色名
	 * @return 角色id
	 * @author yanbaobin@yeah.net
	 * @date Aug 10, 2015 3:17:57 PM
	 */
	public long getIdByName(String name);
	
	/**
	 * @desc 判断角色名是否已经存在
	 * @param name 角色名 
	 * @return 存在:true,不存在false
	 * @author yanbaobin@yeah.net
	 * @date Aug 11, 2015 8:58:28 PM
	 */
	public boolean nameIsExist(String name);
}
