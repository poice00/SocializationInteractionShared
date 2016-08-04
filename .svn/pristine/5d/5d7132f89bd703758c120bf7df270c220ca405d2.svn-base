package com.socialization.serviceImpl.resource;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialization.base.DaoSupportImpl;
import com.socialization.domain.ActAlbum;
import com.socialization.service.resource.ActAlbumService;

@Service
@Transactional
public class ActAlbumServiceImpl extends DaoSupportImpl<ActAlbum> implements ActAlbumService{

	@SuppressWarnings("unchecked")
	@Override
	public List<ActAlbum> findByActId(Long idd) {	
		return getSession().createQuery(
				"FROM ActAlbum a WHERE a.activity.id = ?")
				.setParameter(0, idd)
				.list();
	}

}
