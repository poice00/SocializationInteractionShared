package com.socialization.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialization.base.DaoSupportImpl;
import com.socialization.domain.Role;
import com.socialization.service.RoleService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class RoleServiceImpl extends DaoSupportImpl<Role> implements RoleService {

	/**
	 * @desc 根据角色名获取其id
	 * @param name 角色名
	 * @return 角色id
	 * @author yanbaobin@yeah.net
	 * @date Aug 10, 2015 3:17:57 PM
	 */
	@Override
	public long getIdByName(String name) {
		
		long roleId = 0;
		
		roleId = (long) getSession().createQuery(
				"SELECT id FROM Role WHERE name = ?")	//from 实体类，而不是表名
				.setParameter(0, name)
				.uniqueResult();
		
		return roleId;
	}

	/**
	 * @desc 判断角色名是否已经存在
	 * @param name 角色名 
	 * @return 存在:true,不存在false
	 * @author yanbaobin@yeah.net
	 * @date Aug 11, 2015 8:58:28 PM
	 */
	@Override
	public boolean nameIsExist(String name) {
		long count = (long)getSession().createQuery(
				"SELECT COUNT(*) FROM Role WHERE name = ?")
				.setParameter(0, name)
				.uniqueResult();
		
		if (count == 1) 
			return true;
		
		return false;
	}

}
