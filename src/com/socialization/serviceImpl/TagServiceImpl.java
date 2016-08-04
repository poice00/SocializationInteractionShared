package com.socialization.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialization.base.DaoSupportImpl;
import com.socialization.domain.Tag;
import com.socialization.service.TagService;
@Service
@Transactional
@SuppressWarnings("unchecked")
public class TagServiceImpl extends DaoSupportImpl<Tag> implements TagService{


}
