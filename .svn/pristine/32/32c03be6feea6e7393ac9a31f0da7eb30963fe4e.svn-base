package com.socialization.action.activity;
import java.util.Date;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.socialization.base.BaseAction;
import com.socialization.domain.ActAlbumReply;
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ActAlbumReplyAction extends BaseAction<ActAlbumReply>{
	private Long photoId;
	/*---添加照片评论----*/
	public String add() {
		model.setActAlbumPhoto(this.actAlbumPhotoService.getById(photoId));
		model.setPostTime(new Date());
		model.setUser(getCurrentUser());
		this.actAlbumReplyService.save(model);
		return "add";
	}
	public Long getPhotoId() {
		return photoId;
	}
	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}
}
