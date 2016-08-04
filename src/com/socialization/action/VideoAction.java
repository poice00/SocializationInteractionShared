package com.socialization.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialization.base.BaseAction;
import com.socialization.domain.Forum;
import com.socialization.domain.Topic;
import com.socialization.domain.User;

@Controller
@Scope("prototype")
public class VideoAction extends BaseAction<Topic>{
	
	private Long forumId; //所属的论坛
	private File videoFile;
	private String videoFileFileName;
	private String videoFileContentType;
	
	
    public String video() throws Exception {
    		  System.out.println("111111111111111111111");
            ServletActionContext.getResponse().setContentType(  
                    "text/html; charset=UTF-8");  
            // 文件保存目录路径  
            String savePath = ServletActionContext.getServletContext().getRealPath(  
                    "/")  
                    + "video/user/";  
            System.out.println("=======savePath========" + savePath);
            // 文件保存目录URL  
            String saveUrl = ServletActionContext.getRequest().getContextPath()  
                    + "/video/user/";  
            System.out.println("==========saveUrl=============" + saveUrl);
           // String myPath = "" ;
            // 定义允许上传的文件扩展名  
      
            HashMap<String, String> extMap = new HashMap<String, String>();  
      
            extMap.put("image", "gif,jpg,jpeg,png,bmp");  
            extMap.put("flash", "swf,flv");  
            extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mkv");  
            extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");  
            // 最大文件大小  
            long maxSize = 100 * 1024 * 1024 ;  
            PrintWriter out = null;  
            try {  
                out = ServletActionContext.getResponse().getWriter();  
            } catch (IOException e1) {  
      
            }  
      
            if (videoFile == null) {  
                out.println(getError("请选择文件。"));  
                return null;  
            }  
      
            // 检查目录  
            File uploadDir = new File(savePath);  
            if (!uploadDir.isDirectory()) {  
            	uploadDir.mkdirs();  
            }  
            
//            File myUploadPath = new File(myPath);  
//            if (!myUploadPath.isDirectory()) {  
//            	myUploadPath.mkdirs();  
//            }  
            // 检查目录写权限  
            if (!uploadDir.canWrite()) {  
                out.println(getError("上传目录没有写权限。"));  
                return null;  
            }  
            String dirName = ServletActionContext.getRequest().getParameter("dir");  
            if (dirName == null) {  
                dirName = "media";  
            }  
            if (!extMap.containsKey(dirName)) {  
                System.out.println(getError("目录名不正确。"));  
                return null;  
            }  
            // 创建文件夹  
            savePath += dirName + "/";  
            saveUrl += dirName + "/";  
            File saveDirFile = new File(savePath);  
            if (!saveDirFile.exists()) {  
                saveDirFile.mkdirs();  
            }  
      
            // 创建文件夹  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
            String ymd = sdf.format(new Date());  
            savePath += ymd + "/";  
            saveUrl += ymd + "/";  
            File dirFile = new File(savePath);  
            if (!dirFile.exists()) {  
                dirFile.mkdirs();  
            }  
            String fileExt = videoFileFileName.substring(  
            		videoFileFileName.lastIndexOf(".") + 1).toLowerCase();  
            if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(  
                    fileExt)) {  
                System.out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许"  
                        + extMap.get(dirName) + "格式。"));  
                return null;  
            }  
            if (videoFile.length() > maxSize) {  
                out.println(getError("[ " + videoFileFileName + " ]超过单个文件大小限制，文件大小[ "  
                        + videoFile.length() + " ]，限制为[ " + maxSize + " ] "));  
                return null;  
            }  
           
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
            String newFileName = df.format(new Date()) + "_"  
                    + new Random().nextInt(1000) + "." + fileExt;  
            
            File uploadedFile = new File(savePath, newFileName);  
            //File uploadedFile = new File(savePath, videoFileFileName);  
            try {  
                FileUtil.copyFile(videoFile, uploadedFile);  
                System.out.println("========videoFileFileName==========" + saveUrl +  videoFileFileName);
                String url = saveUrl +  newFileName ;
                
                System.out.println("======forumId=====" +forumId);
                Forum forum = forumService.getById(getForumId());
                
                System.out.println("1111"+model.getTitle());
                if(model.getTitle()==null){
                	model.setTitle(videoFileFileName.split(".")[0]);
                }
        		model.setForum(forum);
        		//--〉〉直接获取的信息
        		model.setUser(getCurrentUser());
        		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
        		model.setPostTime(new Date());
        		model.setContent(url);
        		
        		//保存
        		this.topicService.save(model);
                
      
            } catch (IOException e) {  
            }  
            return "toShow";  
        }  
      
    
    
    
    private String getError(String message) {
    	JSONObject obj = new JSONObject();
    	obj.put("error", 1);
    	obj.put("message", message);
    	return obj.toJSONString();
    }




    
    
    
    
    //=======================
    public File getVideoFile() {
    	return videoFile;
    }
    
    
    
    
    public void setVideoFile(File videoFile) {
    	this.videoFile = videoFile;
    }
    
    
    
    
    public String getVideoFileFileName() {
    	return videoFileFileName;
    }
    
    
    
    
    public void setVideoFileFileName(String videoFileFileName) {
    	this.videoFileFileName = videoFileFileName;
    }




	public String getVideoFileContentType() {
		return videoFileContentType;
	}




	public void setVideoFileContentType(String videoFileContentType) {
		this.videoFileContentType = videoFileContentType;
	}




	public Long getForumId() {
		return forumId;
	}




	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
    
    
    

}
