package com.socialization.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialization.base.DaoSupportImpl;
import com.socialization.domain.Forum;
import com.socialization.service.ForumService;
@Service
@Transactional
@SuppressWarnings("unchecked")
public class ForumServiceImpl extends DaoSupportImpl<Forum> implements ForumService{

	@Override
	public void moveUp(Long id) {
		//需要移动的板块
		Forum forum = getById(id);
		//上一个板块
		Forum other = (Forum) getSession().createQuery(//
					"FROM Forum f WHERE f.position<? ORDER BY f.position DESC")//
					.setParameter(0, forum.getPosition())//
					.setFirstResult(0)//
					.setMaxResults(1)//
					.uniqueResult();
		
		if(other==null) return ;
		
		//交换位置号
		int tmp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(tmp);
	}

	@Override
	public void moveDown(Long id) {
		//需要移动的板块
		Forum forum = getById(id);
		//下一个板块
		Forum other = (Forum) getSession().createQuery(//
					"FROM Forum f WHERE f.position>? ORDER BY f.position ASC")//
					.setParameter(0, forum.getPosition())//
					.setFirstResult(0)//
					.setMaxResults(1)//
					.uniqueResult();
		
		if(other==null) return ;
		
		//交换位置号
		int tmp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(tmp);
		
	}
	
	
	@Override
	public void save(Forum entity) {
		super.save(entity);
		entity.setPosition(entity.getId().intValue());
	}
	
	
	@Override
	public List<Forum> findAll() {
		return getSession().createQuery(//
					"FROM Forum f ORDER BY f.position")//
					.list();
	}

	@Override
	public List<Forum> findforumRankList() {
		return getSession().createQuery(//
				"FROM Forum f ORDER BY f.topicCount DESC")//
				.list();
	}

}
