package com.socialization.serviceImpl.resource;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialization.base.DaoSupportImpl;
import com.socialization.domain.ActAlbumReply;
import com.socialization.service.resource.ActAlbumReplyService;

@Service
@Transactional
public class ActAlbumReplyServiceImpl extends DaoSupportImpl<ActAlbumReply> implements ActAlbumReplyService{

	@SuppressWarnings("unchecked")
	@Override
	public List<ActAlbumReply> findByPhotoId(Long id) {
		return getSession().createQuery(
				"FROM ActAlbumReply a WHERE a.actAlbumPhoto.id = ? ")
				.setParameter(0, id)
				.list();
	}

	@Override
	public void deleteByPhoId(Long photoId) {
		System.out.println(photoId);
		getSession().createSQLQuery(
				"delete from actalbumreply where photoId  = ? ")
		.setParameter(0, photoId).executeUpdate();
	}
}
