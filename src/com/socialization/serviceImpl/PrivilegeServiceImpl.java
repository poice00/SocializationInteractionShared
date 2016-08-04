package com.socialization.serviceImpl;


import java.util.List;

import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.socialization.base.DaoSupportImpl;
import com.socialization.domain.Privilege;
import com.socialization.service.PrivilegeService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService {
	
	/**
	 * @desc 获取真正有作用的权限，即没有子权限的权限
	 * @return 权限的集合
	 * @author yanbaobin@yeah.net
	 * @date Aug 10, 2015 5:02:45 PM
	 */
	@Override
	@Test
	public List<Privilege> findRealPrivilege() {
		
		return (List<Privilege>) getSession().createQuery(
				  "FROM Privilege "
				+ "WHERE id NOT IN "
				+ 		"("
				+ 			"SELECT DISTINCT parent "		//from实体类，字段也是实体类的属性
				+ 			"FROM Privilege "
				+			"WHERE parent IS NOT NULL"		//集合里有null是，not in不起作用
				+       ")"
				)
				.list();
		
	}
	
}
