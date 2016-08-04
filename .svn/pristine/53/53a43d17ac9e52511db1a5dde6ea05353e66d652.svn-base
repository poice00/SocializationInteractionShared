package com.socialization.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class ActAlbumPhoto implements Serializable{
	private Long id;
	private String name;
	private String description; //照片描述
	private Date postTime;   //上传时间
	private User user;      //上传作者
	private ActAlbum actAlbum; //属于哪个相册
	private String photoUrl;
	private Set<ActAlbumReply> actAlbumRepies=new HashSet<ActAlbumReply>();
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	@ManyToOne
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name = "actAlbumId")
	public ActAlbum getActAlbum() {
		return actAlbum;
	}
	public void setActAlbum(ActAlbum actAlbum) {
		this.actAlbum = actAlbum;
	}
	@OneToMany(mappedBy="actAlbumPhoto",cascade=CascadeType.REMOVE)
	public Set<ActAlbumReply> getActAlbumRepies() {
		return actAlbumRepies;
	}
	public void setActAlbumRepies(Set<ActAlbumReply> actAlbumRepies) {
		this.actAlbumRepies = actAlbumRepies;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

}
