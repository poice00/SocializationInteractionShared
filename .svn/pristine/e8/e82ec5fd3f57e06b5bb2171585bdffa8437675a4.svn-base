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

@Entity
@SuppressWarnings("serial")
public class ActAlbum implements Serializable{
	private Long id;  //相册Id
	private String name;  //相册名字
	private String description; //相册描述
	private String tags; //相册标签
	private String albumCover; //相册封面
	private int imgNum; //照片数量
	private Date postTime; //创建时间
	private User user;
	private Activity activity;//属于那个活动
	private Set<ActAlbumPhoto> actAlbumPhotoes=new HashSet<ActAlbumPhoto>(); //相册和照片之间的关系
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
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public int getImgNum() {
		return imgNum;
	}
	public void setImgNum(int imgNum) {
		this.imgNum = imgNum;
	}
	@ManyToOne
	@JoinColumn(name = "activityId")
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	@ManyToOne
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAlbumCover() {
		return albumCover;
	}
	public void setAlbumCover(String albumCover) {
		this.albumCover = albumCover;
	}
	@OneToMany(mappedBy="actAlbum",cascade=CascadeType.REMOVE)
	public Set<ActAlbumPhoto> getActAlbumPhotoes() {
		return actAlbumPhotoes;
	}
	public void setActAlbumPhotoes(Set<ActAlbumPhoto> actAlbumPhotoes) {
		this.actAlbumPhotoes = actAlbumPhotoes;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	
}
