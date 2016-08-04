package com.socialization.domain;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.text.AbstractDocument.Content;

import org.hibernate.annotations.Type;

import com.sun.xml.internal.bind.v2.runtime.Name;

public class Social implements Serializable{
	
	private Long id;
	private String content; 
	private Date postTime;
	private Name name; 

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id= id;
	}
	
	@ManyToOne
	@JoinColumn(name="userId")
	public Name getname() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
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
}