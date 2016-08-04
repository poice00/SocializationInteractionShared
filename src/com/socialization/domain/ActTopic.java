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
public class ActTopic implements Serializable{
	private Long id;
	private String title; //帖子名称
	private String content; //帖子内容
	private Date postTime;   //发帖时间
	private int replyCount; // 回复数量
	private User user;      //帖子作者
	private Activity activity; //帖子属于哪一个活动
	private Set<ActTopicReply> actTopicReplies = new HashSet<ActTopicReply>(); //帖子回复
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	@ManyToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@OneToMany(mappedBy="actTopic",cascade=CascadeType.REMOVE)
	public Set<ActTopicReply> getActTopicReplies() {
		return actTopicReplies;
	}
	public void setActTopicReplies(Set<ActTopicReply> actTopicReplies) {
		this.actTopicReplies = actTopicReplies;
	}
	@ManyToOne
	@JoinColumn(name="activityId")
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

}
