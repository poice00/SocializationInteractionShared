package com.socialization.service.resource;

import java.util.List;

import com.socialization.base.DaoSupport;
import com.socialization.domain.ActAlbum;

public interface ActAlbumService extends DaoSupport<ActAlbum>{

	List<ActAlbum> findByActId(Long idd);

}
