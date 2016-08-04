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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

/**

 * 回复
 *
 * @author socializationComputing
 *
 */


@Entity
public class Reply implements Serializable{
	
	private Long id;
	private String title;
	private String content; //回复内容
	private Date postTime; //回复时间
	private String ipAddr;
	
	
	private User user;
	private Topic topic;
	private Reply parent;
	private Set<Reply> childrens = new HashSet<Reply>();
	
	
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
	@ManyToOne
	@JoinColumn(name="topicId")
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@ManyToOne
	@JoinColumn(name="parentId")
	public Reply getParent() {
		return parent;
	}
	public void setParent(Reply parent) {
		this.parent = parent;
	}
	@OneToMany(mappedBy="parent",cascade=CascadeType.REMOVE)
	@OrderBy("postTime ASC")
	public Set<Reply> getChildrens() {
		return childrens;
	}
	public void setChildrens(Set<Reply> childrens) {
		this.childrens = childrens;
	}
	
	
}
