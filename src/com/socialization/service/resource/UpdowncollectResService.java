package com.socialization.service.resource;

import java.util.List;
import java.util.Map;
import com.socialization.base.DaoSupport;
import com.socialization.domain.Resource;
import com.socialization.domain.Topic;
import com.socialization.domain.User;

public interface UpdowncollectResService extends DaoSupport<Resource>{

	List<Resource> getByuserupId(User user);
	List<Resource> getByuserdownId(User user);
	List<Resource> getByusercolleId(User user);
	void downsave(Resource resource, User user);
	void collectsave(Resource resource, User user);
	void delete(Resource resource);
	List<Resource> OrderByCount(String st);
	List<Resource> OrderByCollect(String st);
	List<Resource> findAllDesc();
	boolean checkCollectExist(Resource resource, User user);
	boolean checkDownExist(Resource resource, User currentUser);
	List<Resource> highLevelSearch(String resDescription, String resname, String resourceTags,String seaDate,Long resTyId,int kz);
	List<Resource> findByTag(String resTg);
	List<Resource> findByResMap(Map<String, Integer> resTagMap);
	void deleteCollect(Long idd, Long id);
	List<Resource> findByName(String searchName);
	
	/**
	 * @desc 根据关键字进行模糊查询
	 * @param str 关键字
	 * @return resource集合
	 * @author yanbaobin@yeah.net
	 * @date Aug 14, 2015 9:11:27 AM
	 */
	List<Resource> fuzzySearch(String str);
	
	/**
	 * @desc 按下载次数排序，取前十条
	 * @return resource集合
	 * @author yanbaobin@yeah.net
	 * @date Aug 14, 2015 10:52:01 AM
	 */
	List<Resource> getTop10();
}
