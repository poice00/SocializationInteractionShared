package com.socialization.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@SuppressWarnings("serial")
public class ActTopicReply implements Serializable{
	private Long id;	
	private String content; //回复内容
	private Date postTime;   //回复时间
	private User user;      //回复作者
	private ActTopic actTopic; //回复属于哪个帖子
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	@ManyToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name="actTopicId")
	public ActTopic getActTopic() {
		return actTopic;
	}
	public void setActTopic(ActTopic actTopic) {
		this.actTopic = actTopic;
	}
	
	
}
