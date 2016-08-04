package com.socialization.serviceImpl.resource;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.socialization.base.DaoSupportImpl;
import com.socialization.domain.ActAlbum;
import com.socialization.domain.ActAlbumPhoto;
import com.socialization.domain.User;
import com.socialization.service.resource.ActAlbumPhotoService;
@Service
@Transactional
public class ActAlbumPhotoServiceImpl extends DaoSupportImpl<ActAlbumPhoto> implements ActAlbumPhotoService{

	@SuppressWarnings("unchecked")
	@Override
	public List<ActAlbumPhoto> findByActAlbId(Long idd) {
		return getSession().createQuery("FROM ActAlbumPhoto a WHERE a.actAlbum.id = ? ORDER BY id ASC")
				.setParameter(0, idd)
				.list();
	}

	@Override
	public void insert(Long id, Long userId, String name, String string, Date date) {
       getSession().createSQLQuery("insert into actalbumphoto(actAlbumId,userId,name,photoUrl,postTime) values(?,?,?,?,?)")
       .setParameter(0, id)
       .setParameter(1, userId)
       .setParameter(2, name)
       .setParameter(3, string)
       .setParameter(4, date)
       .executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ActAlbumPhoto findOneForCover(Long id) {
		List<ActAlbumPhoto> actAlbumPhotos=getSession().createQuery("FROM ActAlbumPhoto a WHERE a.actAlbum.id = ?")
				.setParameter(0, id)
				.setMaxResults(1).list();
		return actAlbumPhotos.get(0);
	}

}
