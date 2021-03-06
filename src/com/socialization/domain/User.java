package com.socialization.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

import java.util.Set;

import javax.persistence.Column;
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
 * 用户
 * @author socializationComputing
 *
 */
@SuppressWarnings("serial")
@Entity
public class User implements Serializable{
	
	private Long id;
	private String name; //昵称
	private String loginName; //登录名
	private String password; //密码
	private Date birthday; //生日
	private String education; //学历
	private String email; //
	private String telephone;
	private Long qq;
	private Date registTime; //注册时间
	private String headImage; //头像
	private String usTags;  //标签
	private int resUpCount;  //资源上传数量
	private Long attentionCount;
	private Long fansCount;
	private String description; //自我描述
	private Long zoneVisitCount;
	private Role role;

	private Set<Reply> replys = new HashSet<Reply>();
	private Set<Topic> topics = new HashSet<Topic>();
	private Set<Talk> talks = new HashSet<Talk>();
	private Set<TalkReply> talksReplys = new HashSet<TalkReply>();
	private Set<Resource> resources = new HashSet<Resource>();
	private Set<ResReply> resReplys = new HashSet<ResReply>();
	private Set<Notice> notices = new HashSet<Notice>();
	private Set<Message> oneMsgs = new HashSet<Message>(); //当前用户发的消息
	private Set<Message> anotherMsgs = new HashSet<Message>(); //好友发的消息
	
	private Set<User> users = new HashSet<User>();
	private Set<User> friends = new HashSet<User>();
	private Set<Tag> tags = new HashSet<Tag>();
	private Set<Topic> collectionTopics = new HashSet<Topic>();
	private Set<Resource> collectionResources = new HashSet<Resource>();
	/*private List<Resource> collectionResources;*/
	private Set<Resource> downsResources=new HashSet<Resource>();
	
	private Set<Activity> activities=new HashSet<Activity>();  //创建的活动
	private Set<Activity> partActivities=new HashSet<Activity>();  //参加的活动
	private Set<Activity> userIntrestActy=new HashSet<Activity>();  //感兴趣的活动
	private Set<Activity> userWantInAct=new HashSet<Activity>();   //申请的活动
	private Set<ActApply> actApplies=new HashSet<ActApply>();   //用户和申请表之间的关系
	private Set<ActTopic> actTopics=new HashSet<ActTopic>();  //活动帖子和作者之间的关系
	private Set<ActTopicReply> actTopicReplies=new HashSet<ActTopicReply>(); //活动帖子回复和作者之间的关系	
	private Set<ActAlbum> actAlbums=new HashSet<ActAlbum>(); //用户和活动相册之间的关系
	private Set<ActAlbumPhoto> actAlbumPhotos=new HashSet<ActAlbumPhoto>(); //用户和照片之间的关系
	private Set<ActAlbumReply> actAlbumPhotoReplys=new HashSet<ActAlbumReply>(); //用户和照片回复之间的关系
	
	private Set<Talk> talkzan=new HashSet<Talk>();
	
	@OneToMany(mappedBy="user",fetch=FetchType.EAGER)
	public Set<Activity> getActivities() {
		return activities;
	}
	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	@OrderBy("startTime ASC")
	@JoinTable(name="user_activity",
		joinColumns={@JoinColumn(name="userId")},
		inverseJoinColumns={@JoinColumn(name="activityId")}
	)
	public Set<Activity> getPartActivities() {
		return partActivities;
	}
	public void setPartActivities(Set<Activity> partActivities) {
		this.partActivities = partActivities;
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "name", length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@ManyToMany(mappedBy="users")
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	@OneToMany(mappedBy="user")
	public Set<Topic> getTopics() {
		return topics;
	}
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}
	@OneToMany(mappedBy="user")
	public Set<Reply> getReplys() {
		return replys;
	}
	public void setReplys(Set<Reply> replys) {
		this.replys = replys;
	}
	@ManyToOne
	@JoinColumn(name="roleId")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@OneToMany(mappedBy="user")
	public Set<Talk> getTalks() {
		return talks;
	}
	public void setTalks(Set<Talk> talks) {
		this.talks = talks;
	}
	@OneToMany(mappedBy="user")
	public Set<TalkReply> getTalksReplys() {
		return talksReplys;
	}
	public void setTalksReplys(Set<TalkReply> talksReplys) {
		this.talksReplys = talksReplys;
	}
	@OneToMany(mappedBy="user")
	public Set<Resource> getResources() {
		return resources;
	}
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	@OneToMany(mappedBy="user")
	public Set<ResReply> getResReplys() {
		return resReplys;
	}
	public void setResReplys(Set<ResReply> resReplys) {
		this.resReplys = resReplys;
	}
	@OneToMany(mappedBy="user")
	public Set<Notice> getNotices() {
		return notices;
	}
	public void setNotices(Set<Notice> notices) {
		this.notices = notices;
	}
	@ManyToMany
	@OrderBy("id ASC")
	@JoinTable(name="user_friend",
			joinColumns={@JoinColumn(name="userId")},
			inverseJoinColumns={@JoinColumn(name="friendId")}
			)
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	@ManyToMany
	@OrderBy("id ASC")
	@JoinTable(name="user_friend",
			joinColumns={@JoinColumn(name="friendId")},
			inverseJoinColumns={@JoinColumn(name="userId")}
			)
	public Set<User> getFriends() {
		return friends;
	}
	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}
	@ManyToMany(fetch=FetchType.EAGER)
	@OrderBy("postTime DESC")
	@JoinTable(name="topic_user",
			joinColumns={@JoinColumn(name="userId")},
			inverseJoinColumns={@JoinColumn(name="topicId")}
			
		)
	public Set<Topic> getCollectionTopics() {
		return collectionTopics;
	}
	public void setCollectionTopics(Set<Topic> collectionTopics) {
		this.collectionTopics = collectionTopics;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Long getQq() {
		return qq;
	}
	public void setQq(Long qq) {
		this.qq = qq;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	@ManyToMany(fetch=FetchType.EAGER)
	@OrderBy("postTime DESC")
	@JoinTable(name="user_resource",
		joinColumns={@JoinColumn(name="userId")},
		inverseJoinColumns={@JoinColumn(name="resourceId")}
	)
	public Set<Resource> getCollectionResources() {
		return collectionResources;
	}
	public void setCollectionResources(Set<Resource> collectionResources) {
		this.collectionResources = collectionResources;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	@OneToMany(mappedBy="user")
	@OrderBy("id ASC")
	public Set<Message> getOneMsgs() {
		return oneMsgs;
	}
	public void setOneMsgs(Set<Message> oneMsgs) {
		this.oneMsgs = oneMsgs;
	}
	@OneToMany(mappedBy="friend")
	@OrderBy("id ASC")
	public Set<Message> getAnotherMsgs() {
		return anotherMsgs;
	}
	public void setAnotherMsgs(Set<Message> anotherMsgs) {
		this.anotherMsgs = anotherMsgs;
	}
	
	
	@ManyToMany
	@JoinTable(name="user_downresource",
		joinColumns={@JoinColumn(name="userId")},
		inverseJoinColumns={@JoinColumn(name="resourceId")}
	)
	public Set<Resource> getDownsResources() {
		return downsResources;
	}
	public void setDownsResources(Set<Resource> downsResources) {
		this.downsResources = downsResources;
	}
	public String getUsTags() {
		return usTags;
	}
	public void setUsTags(String usTags) {
		this.usTags = usTags;
	}

	public int getResUpCount() {
		return resUpCount;
	}
	public void setResUpCount(int resUpCount) {
		this.resUpCount = resUpCount;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	@OrderBy("startTime ASC")
	@JoinTable(name="user_interacty",
		joinColumns={@JoinColumn(name="userId")},
		inverseJoinColumns={@JoinColumn(name="activityId")}
	)
	public Set<Activity> getUserIntrestActy() {
		return userIntrestActy;
	}
	public void setUserIntrestActy(Set<Activity> userIntrestActy) {
		this.userIntrestActy = userIntrestActy;
	}
	@ManyToMany(fetch=FetchType.EAGER)
	@OrderBy("startTime ASC")
	@JoinTable(name="user_wantinacty",
		joinColumns={@JoinColumn(name="userId")},
		inverseJoinColumns={@JoinColumn(name="activityId")}
	)
	public Set<Activity> getUserWantInAct() {
		return userWantInAct;
	}
	public void setUserWantInAct(Set<Activity> userWantInAct) {
		this.userWantInAct = userWantInAct;
	}
	public Long getAttentionCount() {
		return attentionCount;
	}
	public void setAttentionCount(Long attentionCount) {
		this.attentionCount = attentionCount;
	}
	public Long getFansCount() {
		return fansCount;
	}
	public void setFansCount(Long fansCount) {
		this.fansCount = fansCount;
	}
	@OneToMany(mappedBy="user")
	public Set<ActApply> getActApplies() {
		return actApplies;
	}
	public void setActApplies(Set<ActApply> actApplies) {
		this.actApplies = actApplies;
	}
	@OneToMany(mappedBy="user")
	public Set<ActTopic> getActTopics() {
		return actTopics;
	}
	public void setActTopics(Set<ActTopic> actTopics) {
		this.actTopics = actTopics;
	}
	@OneToMany(mappedBy="user")
	public Set<ActTopicReply> getActTopicReplies() {
		return actTopicReplies;
	}
	public void setActTopicReplies(Set<ActTopicReply> actTopicReplies) {
		this.actTopicReplies = actTopicReplies;
	}
	@OneToMany(mappedBy="user")
	public Set<ActAlbumPhoto> getActAlbumPhotos() {
		return actAlbumPhotos;
	}
	public void setActAlbumPhotos(Set<ActAlbumPhoto> actAlbumPhotos) {
		this.actAlbumPhotos = actAlbumPhotos;
	}
	@OneToMany(mappedBy="user")
	public Set<ActAlbum> getActAlbums() {
		return actAlbums;
	}
	public void setActAlbums(Set<ActAlbum> actAlbums) {
		this.actAlbums = actAlbums;
	}
	@OneToMany(mappedBy="user")
	public Set<ActAlbumReply> getActAlbumPhotoReplys() {
		return actAlbumPhotoReplys;
	}
	public void setActAlbumPhotoReplys(Set<ActAlbumReply> actAlbumPhotoReplys) {
		this.actAlbumPhotoReplys = actAlbumPhotoReplys;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getZoneVisitCount() {
		return zoneVisitCount;
	}
	public void setZoneVisitCount(Long zoneVisitCount) {
		this.zoneVisitCount = zoneVisitCount;
	}
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_talkzan",
	joinColumns={@JoinColumn(name="userId")},
	inverseJoinColumns={@JoinColumn(name="talkId")}
    )
	public Set<Talk> getTalkzan() {
		return talkzan;
	}
	public void setTalkzan(Set<Talk> talkzan) {
		this.talkzan = talkzan;
	}
	

}
