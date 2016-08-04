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

/**
 * 
 * 资源
 * 
 * @author socializationComputing
 *
 */
@SuppressWarnings("serial")
@Entity
	
public class Resource implements Serializable {

	private Long id;
	private String name;
	private String description;
	private String path;// 资源路径
	private Date postTime; // 时间
	private String ipAddr; // IP
	private int repCount; // 评论数量
	private float score; // 资源评分
	private int totalScore; //资源总分
	private int haopingCount; // 好评数量
	private int chapingCount; // 差评数量
	private int downCount; // 下载次数
    private String resSize;    //资源大小
	private int collectionCount;// 收藏数量
	private String resTags;    //用户自定义标签
	private ResType resType;   //资源类型
	private User user;
	private String resContentType;  //文件类型
	private String resFilename;   //文件名字
	private String resIcon; //文件图标

	private Set<ResReply> resReplys = new HashSet<ResReply>(); //该资源的评论
	private Set<User> users = new HashSet<User>(); // 收藏资源的用户

	private Set<Tag> tags = new HashSet<Tag>();  //该资源的标签
	private Set<User> userdownloads = new HashSet<User>(); // 下载资源的用户
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

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	@ManyToMany
	@JoinTable(name = "resouce_tag", joinColumns = { @JoinColumn(name = "resouceId") }, inverseJoinColumns = {
			@JoinColumn(name = "tagId") }

	)
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@ManyToOne
	@JoinColumn(name = "resTypeId")
	public ResType getResType() {
		return resType;
	}

	public void setResType(ResType resType) {
		this.resType = resType;
	}

	@ManyToOne
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(mappedBy = "resource",cascade=CascadeType.REMOVE)
	public Set<ResReply> getResReplys() {
		return resReplys;
	}

	public void setResReplys(Set<ResReply> resReplys) {
		this.resReplys = resReplys;
	}

	@ManyToMany(mappedBy = "collectionResources",fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@ManyToMany(mappedBy = "downsResources",cascade=CascadeType.REMOVE)
	public Set<User> getUserdownloads() {
		return userdownloads;
	}

	public void setUserdownloads(Set<User> userdownloads) {
		this.userdownloads = userdownloads;
	}

	public int getHaopingCount() {
		return haopingCount;
	}

	public void setHaopingCount(int haopingCount) {
		this.haopingCount = haopingCount;
	}

	public int getRepCount() {
		return repCount;
	}

	public void setRepCount(int repCount) {
		this.repCount = repCount;
	}


	public int getChapingCount() {
		return chapingCount;
	}

	public void setChapingCount(int chapingCount) {
		this.chapingCount = chapingCount;
	}

	public int getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(int collectionCount) {
		this.collectionCount = collectionCount;
	}
	public int getDownCount() {
		return downCount;
	}

	public void setDownCount(int downCount) {
		this.downCount = downCount;
	}

	public String getResSize() {
		return resSize;
	}

	public void setResSize(String resSize) {
		this.resSize = resSize;
	}

	public String getResTags() {
		return resTags;
	}

	public void setResTags(String resTags) {
		this.resTags = resTags;
	}

	public String getResContentType() {
		return resContentType;
	}

	public void setResContentType(String resContentType) {
		this.resContentType = resContentType;
	}

	public String getResFilename() {
		return resFilename;
	}

	public void setResFilename(String resFilename) {
		this.resFilename = resFilename;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public String getResIcon() {
		return resIcon;
	}

	public void setResIcon(String resIcon) {
		this.resIcon = resIcon;
	}
}
