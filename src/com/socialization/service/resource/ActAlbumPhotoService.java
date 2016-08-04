package com.socialization.service.resource;

import java.util.Date;
import java.util.List;

import com.socialization.base.DaoSupport;
import com.socialization.domain.ActAlbum;
import com.socialization.domain.ActAlbumPhoto;
import com.socialization.domain.User;

public interface ActAlbumPhotoService extends DaoSupport<ActAlbumPhoto>{

	List<ActAlbumPhoto> findByActAlbId(Long idd);

	void insert(Long id, Long userId, String name, String string, Date date);

	ActAlbumPhoto findOneForCover(Long id);

}
