package com.socialization.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * 
 * 标签
 * @author socializationComputing
 *
 */


@SuppressWarnings("serial")
@Entity
public class Tag implements Serializable{
	private Long id;
	private String name; //标签名
	private String description; //标签描述
	/*private Tag parent;
	private Set<Tag> children=new HashSet<Tag>();*/
	
	private Set<User> users = new HashSet<User>();
	private Set<Resource> resources = new HashSet<Resource>();
	
	
	
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
	@ManyToMany
	@JoinTable(name="tag_user",
		joinColumns={@JoinColumn(name="tagId")},
		inverseJoinColumns={@JoinColumn(name="userId")}
		
	)
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	@ManyToMany(mappedBy="tags")
	public Set<Resource> getResources() {
		return resources;
	}
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
/*	public Tag getParent() {
		return parent;
	}
	public void setParent(Tag parent) {
		this.parent = parent;
	}
	public Set<Tag> getChildren() {
		return children;
	}
	public void setChildren(Set<Tag> children) {
		this.children = children;
	}*/
	
	
}
