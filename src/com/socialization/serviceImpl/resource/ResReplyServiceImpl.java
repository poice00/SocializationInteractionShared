package com.socialization.serviceImpl.resource;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialization.base.DaoSupportImpl;
import com.socialization.domain.ResReply;
import com.socialization.domain.Resource;
import com.socialization.service.resource.ResReplyService;

@Service
@Transactional
public class ResReplyServiceImpl extends DaoSupportImpl<ResReply> implements ResReplyService {
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ResReply> getbyResID(Resource resource) {
		return getSession().createQuery(
				"FROM ResReply r WHERE r.resource = ? order by postTime desc)")
				.setParameter(0, resource)
				.list();
	}
	
	@Override
	public void save(ResReply resReply,float currentscore) {
		//保存
		getSession().save(resReply);		
		//维护特殊数据
		Resource resource=resReply.getResource();
		if (resource.getScore()==0) {
			resource.setScore(currentscore);
			resource.setTotalScore((int)currentscore);
			resource.setRepCount(1);
			getSession().update(resource);
		}else
		{
			resource.setScore((resource.getTotalScore()+(int)currentscore)/(resource.getRepCount()+1));
			resource.setTotalScore(resource.getTotalScore()+(int)currentscore);
			resource.setRepCount(resource.getRepCount()+1);
			getSession().update(resource);
		}
	}

	@Override  //删除资源时要删除相应的评论
	public void deleteByResId(Resource resource) {
      getSession().createSQLQuery(
    		  "delete from resreply where resourceId=?")
              .setParameter(0, resource.getId())
              .executeUpdate();
	}
	
	@Override
	public void delete(ResReply resReply) {
		getSession().delete(resReply);
		// 维护特殊数据
		Resource resource = resReply.getResource(); // 资源的得分修正，资源的评论数-1
		if ((resource.getRepCount() - 1) == 0) {
			resource.setScore(0);
			resource.setTotalScore(0);
			resource.setRepCount(0);
			getSession().update(resource);
		} else {
			resource.setScore((resource.getTotalScore() - (int) resReply.getScore()) / (resource.getRepCount() - 1));
			resource.setTotalScore(resource.getTotalScore() - (int) resReply.getScore());
			resource.setRepCount(resource.getRepCount() - 1);
			getSession().update(resource);
		}
	}

	@Override
	public List<ResReply> getbyResIDDesc(Resource byId) {
		// TODO Auto-generated method stub
		return null;
	}
}
