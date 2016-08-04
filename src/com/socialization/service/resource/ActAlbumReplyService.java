package com.socialization.service.resource;

import java.util.List;

import com.socialization.base.DaoSupport;
import com.socialization.domain.ActAlbumReply;

public interface ActAlbumReplyService extends DaoSupport<ActAlbumReply>{

	List<ActAlbumReply> findByPhotoId(Long id);

	void deleteByPhoId(Long photoId);
}
