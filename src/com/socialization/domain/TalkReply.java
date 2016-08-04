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
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.hibernate.annotations.Type;

/**
 * 说说回复
 * @author socializationComputing
 *
 */


@Entity
public class TalkReply implements Serializable{
	
	private Long id;
	private String content; //回复内容
	private Date postTime;
	private String ipAddr;
	
	private Talk talk;
	private User user;
	private TalkReply parent;
	private Set<TalkReply> childrens = new HashSet<TalkReply>();
	
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
	@JoinColumn(name="talkId")
	public Talk getTalk() {
		return talk;
	}
	public void setTalk(Talk talk) {
		this.talk = talk;
	}
	@ManyToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
	@ManyToOne
	@JoinColumn(name="parentId")
	public TalkReply getParent() {
		return parent;
	}
	public void setParent(TalkReply parent) {
		this.parent = parent;
	}
	
	@OneToMany(mappedBy="parent",cascade=CascadeType.REMOVE)
	@OrderBy("postTime ASC")
	public Set<TalkReply> getChildrens() {
		return childrens;
	}
	public void setChildrens(Set<TalkReply> childrens) {
		this.childrens = childrens;
	}
	
}
