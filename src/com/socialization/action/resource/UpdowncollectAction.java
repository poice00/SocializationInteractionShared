package com.socialization.action.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.opensymphony.xwork2.ActionContext;
import com.socialization.base.BaseAction;
import com.socialization.domain.ResReply;
import com.socialization.domain.ResType;
import com.socialization.domain.Resource;
import com.socialization.domain.User;
import com.socialization.resourceutil.TimeCount;
import com.socialization.util.QueryHelper;


@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UpdowncollectAction extends BaseAction<Resource> {
	

	/*---普通搜索方法----*/
	public String normalSearch() throws Exception {
		searchName = new String(searchName.getBytes("iso-8859-1"), "UTF-8");
		List<Resource> highresLevelList=updowncollectResService.findByName(searchName);
		//List<Resource> upresList=new ArrayList<Resource>(getCurrentUser().getResources());
		if (highresLevelList.size()!=0) {
			ActionContext.getContext().put("highresLevelList", highresLevelList);
			return "normalSearch";
		} else{
			ActionContext.getContext().put("highresLevelList", highresLevelList);
			return "normalSearch";
		}	
	}
	/*---上传列表数据----*/
	public String uplist() throws Exception {    
		List<Resource> upresList=updowncollectResService.getByuserupId(getCurrentUser());
		//List<Resource> upresList=new ArrayList<Resource>(getCurrentUser().getResources());
		if (upresList.size()!=0) {
			ActionContext.getContext().put("upresList", upresList);
			return "upresList";
		} else{
			return "toupresList";
		}	
	}
	/*---我的资源---*/
	public String myRes() throws Exception{
		List<Resource> upresList=updowncollectResService.getByuserupId(getCurrentUser());
		ActionContext.getContext().put("upresList", upresList);
		List<Resource> downReslist=updowncollectResService.getByuserdownId(getCurrentUser());
		ActionContext.getContext().put("downReslist", downReslist);
		List<Resource> collResList=updowncollectResService.getByusercolleId(getCurrentUser());
		ActionContext.getContext().put("collResList", collResList);
		//ActionContext.getContext().getValueStack().push(warnMessage);
		return "myRes";		
	}
	/*---显示上传界面----*/
	public String showupload() throws Exception{  //显示上传界面
		/*List<Resource> resList = this.updowncollectResService.findAll();
		Map<String, Integer> resTagMap = new TimeCount().resToMap(resList);
		//ActionContext.getContext().put("resTagMap", resTagMap);
		ActionContext.getContext().getSession().put("resTagMap", resTagMap);
		if (resTagMap.size() <= 5) {
			resTagUseToShow = "0";
		} else {
			List<Resource> resTgList = this.updowncollectResService.findByResMap(resTagMap);
			resTagUseToShow = String.valueOf(resTgList.size());
		}*/
		List<ResType> resTypes=this.resStyleService.findAll();
		ActionContext.getContext().put("resTypes", resTypes);
		if (warnMessage==null) {
			return "showupload";
		} else {
			this.setWarnMessage("文件太大，请重新选择！");
			return "showupload";
		}	
	}
	/*---下载列表数据 ----*/
	public String downlist() throws Exception{     //下载列表数据           
		List<Resource> downReslist=updowncollectResService.getByuserdownId(getCurrentUser());
		if (downReslist.size()!=0) {
			ActionContext.getContext().put("downReslist", downReslist);
			return "downlist";
		} else {
			return "todownlist";
		}
	}
	/*---收藏列表数据----*/
	public String collectlist() throws Exception{  //收藏列表数据
		List<Resource> collResList=updowncollectResService.getByusercolleId(getCurrentUser());
		if (collResList.size()!=0) {
			ActionContext.getContext().put("collResList", collResList);
			return "collectlist";
		} else {
			return "tocollectlist";
		}
		
	}
	/*---准备单个资源的页面数据----*/
	public String showSingleRes() throws Exception{  //准备单个资源的页面数据
		Resource resource=this.updowncollectResService.getById(idd);
		ActionContext.getContext().getValueStack().push(resource);
		this.setWarnMessage("notCollect");
		boolean checkResult=this.updowncollectResService.checkCollectExist(resource,getCurrentUser());
		if (checkResult==false) {
		this.setWarnMessage("collected");	
		}
		new QueryHelper(ResReply.class, "r")
		.addCondition("r.resource=?", resource)
		.addOrderProperty("r.postTime", false)
		.perparePageBean(resReplyService, currentPage, pageSize);
		if (warnMessage==null) {
			return "showSingleRes";	
		} else {
	        this.warnMessage=URLDecoder.decode(warnMessage,"UTF-8"); //解决乱码问题
			return "showSingleRes";	
		}		
	}
	/*---删除资源的数据----*/
     public String delete() throws Exception{  //删除资源的数据
    	Resource resource=this.updowncollectResService.getById(idd);
		this.resReplyService.deleteByResId(resource);
		this.updowncollectResService.delete(resource);
		File file=new File(resource.getPath());
		if (file.exists()) {
			file.delete();
		}
		return "delete";
	}
	
     /*---资源上传提交，同时写入数据库----*/
	public String upResCommit() throws Exception{   //资源上传提交，同时写入数据库
		byte[] buffer=new byte[102400];
		InputStream in=new FileInputStream(upFile);
		String reSize;
		float aa=(float)in.available();
		if (aa/1024/1024>=100) { //如果文件过大，则关闭文件流，向上传页面返回文件过大的提醒
			in.close();
			return "fileTooBig";
		}else
		{
			if (aa/1024<1) {      //转化成B
			     reSize=String.valueOf((int)aa)+"B";
				} else if (aa/1024/1024<1) {    //转化成KB
					reSize=String.valueOf((int)(aa/1024))+"KB";
				} else {      //转化成MB
		           reSize=String.valueOf(((float)(Math.round(aa/1024/1024*100)))/100)+"MB";
				}
				String filepath=ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload/");	
				File tmpFile = new File(filepath);
				if (!tmpFile.exists()) {   //如果文件名不存在则创建新的文件名
				 tmpFile.mkdir();
				 }
				OutputStream out=new FileOutputStream(new File(filepath+"/"+upFileFileName));
				int length= in.read(buffer);
				while (length>0){
					out.write(buffer);
					length=in.read(buffer);
				}
				in.close();
				out.flush();
				out.close();
				model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
				model.setPath(filepath+"/"+upFileFileName);
				String str=new TimeCount().name(upFileFileName);
				model.setResIcon(str);
				model.setPostTime(new Date());
				model.setUser(getCurrentUser());
				model.setResSize(reSize);
				model.setResFilename(upFileFileName);
				model.setResTags(model.getResTags());
				model.setResType(this.resStyleService.getById(resTypeId));
				model.setResContentType(upFileContentType);
				this.updowncollectResService.save(model);
				List<Resource> resList = this.updowncollectResService.findAll();
				Map<String, Integer> resTagMap = new TimeCount().resToMap(resList);
				if (resTagMap.size() <= 5) {
					resTagMap.put("其他", 0);
				} else {
					List<Resource> resTgList = this.updowncollectResService.findByResMap(resTagMap);
					resTagMap.put("其他", resTgList.size());
				}
				ActionContext.getContext().getSession().put("resTagMap", resTagMap);
				return "upResCommit";
		}
	}
	
	/*---点击收藏时触发的动作----*/
	 public String resCollectSuc() throws Exception{  //删除资源的数据
		 Resource resource=this.updowncollectResService.getById(idd);
		 boolean checkResult=this.updowncollectResService.checkCollectExist(resource,getCurrentUser());
		 if (checkResult) {
			 if (resource.getCollectionCount()==0) {
					resource.setCollectionCount(1);
				}else
				{
					resource.setCollectionCount(resource.getCollectionCount()+1);
				}
				this.updowncollectResService.update(resource);
				/*User user=this.userService.getById(getCurrentUser().getId());
			    user.getCollectionResources().add(resource);*/
				this.updowncollectResService.collectsave(resource, getCurrentUser());
				this.setWarnMessage("收藏成功！");
				return "resCollectSuc";
		} else {    
			this.setWarnMessage("你已经收藏过该资源，请去你的资源收藏列表查看吧~");
			return "resCollectSuc";
		}
			
    }
	 /*---详细页面点击收藏时触发的动作----*/
	 public void collectadd() throws Exception{
		Resource resource = this.updowncollectResService.getById(idd);
		resource.setCollectionCount(resource.getCollectionCount() + 1);
		this.updowncollectResService.update(resource);
		/*
		 * User user=this.userService.getById(getCurrentUser().getId());
		 * user.getCollectionResources().add(resource);
		 */
		this.updowncollectResService.collectsave(resource, getCurrentUser());
		this.setWarnMessage("收藏成功！");
		httpSr(warnMessage);
		return;
	}	 
	 /*---详细页面点击取消收藏时触发的动作----*/
	 public void collectRemove() throws Exception{
		 this.updowncollectResService.deleteCollect(idd,getCurrentUser().getId());
		 //维护相关数据，更新资源的收藏数量
		 Resource resource=this.updowncollectResService.getById(idd);
		 resource.setCollectionCount(resource.getCollectionCount()-1);
		 this.updowncollectResService.update(resource);
		 this.setWarnMessage("取消成功！");
		 httpSr(warnMessage);
		 return;
	}	 
	 /*---点击取消收藏时触发的动作----*/
	 public String CancelCollect() throws Exception{
		 this.updowncollectResService.deleteCollect(idd,getCurrentUser().getId());
		 //维护相关数据，更新资源的收藏数量
		 Resource resource=this.updowncollectResService.getById(idd);
		 resource.setCollectionCount(resource.getCollectionCount()-1);
		 this.updowncollectResService.update(resource);
		 this.setWarnMessage("CancelCollect");
		 ActionContext.getContext().getValueStack().push(warnMessage);
		return "CancelCollect";
	}
	/*---向页面传值的方法----*/
	public void httpSr(String str) throws Exception {
		HttpServletResponse hsr = ServletActionContext.getResponse();
		hsr.setContentType("test/html;charset=UTF-8");
		hsr.getWriter().write(str);
	}
	/*---评论成功后触发的动作见ResReply----*/
	
	private Long idd;
	private File upFile;
	private String upFileFileName;
	private String upFileContentType;
	private String warnMessage;
	private String searchName;
	private String resTagUseToShow;
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public File getUpFile() {
		return upFile;
	}


	public void setUpFile(File upFile) {
		this.upFile = upFile;
	}


	public String getUpFileFileName() {
		return upFileFileName;
	}


	public void setUpFileFileName(String upFileFileName) {
		this.upFileFileName = upFileFileName;
	}


	public String getUpFileContentType() {
		return upFileContentType;
	}


	public void setUpFileContentType(String upFileContentType) {
		this.upFileContentType = upFileContentType;
	}


	public Long getIdd() {
		return idd;
	}


	public void setIdd(Long idd) {
		this.idd = idd;
	}
    private Long resTypeId;
	public Long getResTypeId() {
		return resTypeId;
	}
	public void setResTypeId(Long resTypeId) {
		this.resTypeId = resTypeId;
	}
	public String getWarnMessage() {
		return warnMessage;
	}
	public void setWarnMessage(String warnMessage) {
		this.warnMessage = warnMessage;
	}
	public String getResTagUseToShow() {
		return resTagUseToShow;
	}
	public void setResTagUseToShow(String resTagUseToShow) {
		this.resTagUseToShow = resTagUseToShow;
	}

}
