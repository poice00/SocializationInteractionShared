package com.socialization.action.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.service.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionContext;
import com.socialization.base.BaseAction;
import com.socialization.domain.ActAlbum;
import com.socialization.domain.ActAlbumPhoto;
import com.socialization.domain.ActAlbumReply;
import com.socialization.domain.Activity;
import com.socialization.service.resource.ActAlbumReplyService;


@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ActAlbumAction extends BaseAction<ActAlbum>{
	
	
	private Long activityId;
	private Long idd;
	private Long photoId;
	private Long photoNum;
	private String picControl;
	private String photoDescription;
	private Long actAlbumId;
	
	
	/*---活动相册创建页面----*/
	public String addUI() throws Exception{		
		Activity activity=this.activiService.getById(activityId);
		ActionContext.getContext().getValueStack().push(activity);
		return "AddUI";
	}
	/*---活动相册创建成功----*/
	public String add() throws Exception{
		Activity activity=this.activiService.getById(activityId);
		model.setActivity(activity);
		model.setPostTime(new Date());
		model.setUser(getCurrentUser());
		String str=model.getDescription();
		str=str.replaceAll("\r\n", "<br/>");
		str=str.replaceAll(" ", "&nbsp;");
		model.setDescription(str);   //为空时是个问题
		if (files.isEmpty()) {
			model.setImgNum(0);
			this.actAlbumService.save(model);
			return "add";
		}else{
			model.setImgNum(files.size());
			this.actAlbumService.save(model);
			upload(files, this.getFilesFileName(), model.getId(), getCurrentUser().getId());
			ActAlbum actAlbum=this.actAlbumService.getById(model.getId());
			ActAlbumPhoto actAlbumPhoto=this.actAlbumPhotoService.findOneForCover(actAlbum.getId());
			actAlbum.setAlbumCover(actAlbumPhoto.getPhotoUrl());
			this.actAlbumService.update(actAlbum);
			return "add";
		}				
	}
	/*---增加照片的方法----*/
	public String photoadd() throws Exception{
		upload(files, this.getFilesFileName(), idd, getCurrentUser().getId());
		ActAlbum actAlbum=this.actAlbumService.getById(idd);
		List<ActAlbumPhoto> actAlbumPhotos=this.actAlbumPhotoService.findByActAlbId(idd);
		actAlbum.setImgNum(actAlbumPhotos.size());
		this.actAlbumService.update(actAlbum);
		return "photoadd";
	}
	/*---增加照片的方法----*/
	public String photoaddTwo() throws Exception{
		upload(files, this.getFilesFileName(), actAlbumId, getCurrentUser().getId());
		ActAlbum actAlbum=this.actAlbumService.getById(actAlbumId);
		List<ActAlbumPhoto> actAlbumPhotos=this.actAlbumPhotoService.findByActAlbId(idd);
		actAlbum.setImgNum(actAlbumPhotos.size());
		this.actAlbumService.update(actAlbum);
		return "photoaddTwo";
	}
	/*---活动相册显示----*/
	public String showActAlbum() throws Exception{
		ActAlbum actAlbum=this.actAlbumService.getById(idd);
	    ActionContext.getContext().getValueStack().push(actAlbum);
	    List<ActAlbumPhoto> actAlbumPhotosList=this.actAlbumPhotoService.findByActAlbId(idd);
	    ActionContext.getContext().put("actAlbumPhotosList", actAlbumPhotosList);
		return "showActAlbum";
	}	
	/*---返回活动的方法----*/
	public String backToAct() throws Exception{
		ActAlbum actAlbum=this.actAlbumService.getById(idd);
	    this.setActivityId(actAlbum.getActivity().getId());
		return "backToAct";
	}
	/*---显示单个图片方法----*/
	public String ShowalbumPhoto() throws Exception{
		ActAlbumPhoto actAlbumPhoto=this.actAlbumPhotoService.getById(photoId);
		this.setIdd(actAlbumPhoto.getActAlbum().getId());
		List<ActAlbumPhoto> actAlbumPhotosList=this.actAlbumPhotoService.findByActAlbId(idd);
		Long minId=actAlbumPhotosList.get(0).getId();
		for (int i = 0; i < actAlbumPhotosList.size(); i++) {
			Long ggg=actAlbumPhotosList.get(i).getId();
			if (photoId==ggg) {
				this.setPhotoNum((long) (i+1));
			}
		}
		ActionContext.getContext().getValueStack().push(actAlbumPhoto);
		List<ActAlbumReply> actAlbumReplyList= this.actAlbumReplyService.findByPhotoId(photoId);
		ActionContext.getContext().put("actAlbumReplyList", actAlbumReplyList);
		return "ShowalbumPhoto";
	}
	/*---显示上个图片方法----*/
	public String privew() {
		List<ActAlbumPhoto> actAlbumPhotosList = this.actAlbumPhotoService.findByActAlbId(idd);
		int size = actAlbumPhotosList.size();
		Long maxId = actAlbumPhotosList.get(size - 1).getId();
		Long minId = actAlbumPhotosList.get(0).getId();
		photoNum=photoNum-1;
		if (photoNum < 1) {
			photoNum = (long) size;
			photoId=actAlbumPhotosList.get(size-1).getId();
		}else {
			photoId=actAlbumPhotosList.get((int)(photoNum-1)).getId();
		}
		ActAlbumPhoto actAlbumPhoto = this.actAlbumPhotoService.getById(photoId);
		ActionContext.getContext().getValueStack().push(actAlbumPhoto);
		List<ActAlbumReply> actAlbumReplyList = this.actAlbumReplyService.findByPhotoId(photoId);
		ActionContext.getContext().put("actAlbumReplyList", actAlbumReplyList);
		return "privew";
	}
	/*---显示下个图片方法----*/
	public String Next() {
		List<ActAlbumPhoto> actAlbumPhotosList = this.actAlbumPhotoService.findByActAlbId(idd);
		int size = actAlbumPhotosList.size();
		Long maxId = actAlbumPhotosList.get(size - 1).getId();
		Long minId = actAlbumPhotosList.get(0).getId();
		photoNum=photoNum+1;
		if (photoNum > size) {
			photoNum=(long) 1;
			photoId = minId;
		}else{
			photoId=actAlbumPhotosList.get((int)(photoNum-1)).getId();	
		}
		
		ActAlbumPhoto actAlbumPhoto = this.actAlbumPhotoService.getById(photoId);
		ActionContext.getContext().getValueStack().push(actAlbumPhoto);
		List<ActAlbumReply> actAlbumReplyList = this.actAlbumReplyService.findByPhotoId(photoId);
		ActionContext.getContext().put("actAlbumReplyList", actAlbumReplyList);
		return "next";
	}
	/*---返回相册方法----*/
	public String backToAlbum() throws Exception{
		ActAlbum actAlbum=this.actAlbumService.getById(idd);
		ActionContext.getContext().getValueStack().push(actAlbum);
		return "backToAlbum";
	}
	/*---添加照片描述的方法----*/
	public String addDescription() throws Exception{
		ActAlbumPhoto actAlbumPhoto=this.actAlbumPhotoService.getById(photoId);
		actAlbumPhoto.setDescription(photoDescription);
		this.actAlbumPhotoService.update(actAlbumPhoto);		
		return "addDescription";
	}
	/*---设为封面的方法----*/
	public String updateAlbumCover() throws Exception{
		ActAlbumPhoto actAlbumPhoto=this.actAlbumPhotoService.getById(photoId);
		ActAlbum actAlbum=this.actAlbumService.getById(idd);
		actAlbum.setAlbumCover(actAlbumPhoto.getPhotoUrl());
		this.actAlbumService.update(actAlbum);
		return "updateAlbumCover";
	}
	/*---删除照片的方法----*/
	public String photoDelete() throws Exception{
		List<ActAlbumPhoto> actAlbumPhotosList = this.actAlbumPhotoService.findByActAlbId(idd);
		int size = actAlbumPhotosList.size();
		Long maxId = actAlbumPhotosList.get(size - 1).getId();
		Long minId = actAlbumPhotosList.get(0).getId();
		ActAlbumPhoto actAlbumPhoto=this.actAlbumPhotoService.getById(photoId);
		ActAlbum actAlbum=this.actAlbumService.getById(idd);
		System.out.println(photoId);
		this.actAlbumPhotoService.delete(photoId);
		photoNum=photoNum+1;
		if (photoNum > actAlbum.getImgNum()) {
			photoNum=(long) 1;
			photoId = minId;
		}else{
			photoId=actAlbumPhotosList.get((int)(photoNum-1)).getId();
		}
		actAlbum.setImgNum(actAlbum.getImgNum()-1);
		this.actAlbumService.update(actAlbum);	
		System.out.println(photoId);
		return "photoDelete";
	}
	
	/*---图片上传的方法----*/
	public void upload(List file, List fileName, Long id, Long userId) throws IOException {
		// String root = ServletActionContext.getRequest().getRealPath(path);
		// String root =
		// ServletActionContext.getServletContext().getRealPath(path);
		String root = "E:\\workspace\\SocializationInteractionShared\\WebContent\\album\\";
		File dir = new File(root);
		if (dir.exists() == false) {
			dir.mkdirs();
		}
		System.out.println(root);
		ArrayList list = new ArrayList();
		for (int i = 0; i < file.size(); ++i) {
			InputStream is = new FileInputStream(file.get(i).toString());
			/*
			 * SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//
			 * 格式化时间输出 String Rname = sdf.format(new Date());//
			 * 取得当前时间，Date()是java.util包里的，这作为真实名称 name = Rname + name;//
			 * 重命名文件名称,命名规则为：时间+原文件名称
			 */
			String name = fileName.get(i).toString();// 得到上传文件的原名称
			File destFile = new File(root, name);
			OutputStream os = new FileOutputStream(destFile);
			byte[] buffer = new byte[1000];
			int length = 0;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			actAlbumPhotoService.insert(id, userId, name, "album\\"+ name, new Date());
			//list.add(path + name);
			is.close();
			os.close();
		}
		//return list;
	}
	 private List<File> files;
	 private List<String> filesFileName;
	 private List<String> filesContentType;
	 public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	public List<String> getFilesFileName() {
		return filesFileName;
	}
	public void setFilesFileName(List<String> filesFileName) {
		this.filesFileName = filesFileName;
	}
	public List<String> getFilesContentType() {
		return filesContentType;
	}
	public void setFilesContentType(List<String> filesContentType) {
		this.filesContentType = filesContentType;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public Long getIdd() {
		return idd;
	}
	public void setIdd(Long idd) {
		this.idd = idd;
	}
	public Long getPhotoId() {
		return photoId;
	}
	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}
	public Long getPhotoNum() {
		return photoNum;
	}
	public void setPhotoNum(Long photoNum) {
		this.photoNum = photoNum;
	}
	public String getPicControl() {
		return picControl;
	}
	public void setPicControl(String picControl) {
		this.picControl = picControl;
	}
	public String getPhotoDescription() {
		return photoDescription;
	}
	public void setPhotoDescription(String photoDescription) {
		this.photoDescription = photoDescription;
	}
	public Long getActAlbumId() {
		return actAlbumId;
	}
	public void setActAlbumId(Long actAlbumId) {
		this.actAlbumId = actAlbumId;
	}
}
