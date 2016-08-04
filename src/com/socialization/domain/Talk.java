package com.socialization.domain;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

/**
 * 说说
 * @author socializationComputing
 *
 */
@Entity
public class Talk implements Serializable{
	
	private Long id;
	private String content; //说说内容
	private Date postTime;
	
	private User user;
	private Set<TalkReply> talkReplys = new HashSet<TalkReply>();
	private int replyCount; //回复数量
	private Date lastUpdateTime;
	private int count;
	private Set<User> users=new HashSet<User>(); //点赞人员
	

	private TalkReply lastReply;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Type(type="text")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Temporal(TemporalType.TIMESTAMP)
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
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	
	@OneToMany(mappedBy="talk",cascade=CascadeType.REMOVE)
	@OrderBy("postTime ASC")
	public Set<TalkReply> getTalkReplys() {
		return talkReplys;
	}
	public void setTalkReplys(Set<TalkReply> talkReplys) {
		this.talkReplys = talkReplys;
	}
	
	@ManyToOne
	@JoinColumn(unique=true,name="lastReplyId")
	public TalkReply getLastReply() {
		return lastReply;
	}
	public void setLastReply(TalkReply lastReply) {
		this.lastReply = lastReply;
	}
	@ManyToMany(mappedBy="talkzan",fetch=FetchType.EAGER)
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
