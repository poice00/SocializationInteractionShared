package com.socialization.serviceImpl.resource;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.socialization.base.DaoSupportImpl;
import com.socialization.domain.Tag;
import com.socialization.service.resource.TagResourceService;

@Service
@Transactional
public class TagResourceServiceImpl extends DaoSupportImpl<Tag> implements TagResourceService {
	
	
}
