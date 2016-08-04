package com.socialization.service;

import java.util.List;

import com.socialization.base.DaoSupport;
import com.socialization.domain.Privilege;

public interface PrivilegeService extends DaoSupport<Privilege> {
	
	
	/**
	 * @desc 获取真正有作用的权限，即没有子权限的权限
	 * @return 权限的集合
	 * @author yanbaobin@yeah.net
	 * @date Aug 10, 2015 5:02:45 PM
	 */
	List<Privilege> findRealPrivilege();

}
