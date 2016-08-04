package com.socialization.action.zone;

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

import com.opensymphony.xwork2.ActionSupport;
import com.socialization.base.BaseAction;
import com.socialization.domain.User;
import com.socialization.service.UserService;
@Controller
@Scope("prototype")
public class UploadAction extends BaseAction<User>{
		private File imgFile;
	    /**
	     * 文件名称
	     */
	    private String imgFileFileName;
	
	    /**
	     * 图片宽度
	     */
	    private String imgWidth;
	
	    /**
	     * 
	     */
	    private String imgHeight;
	
	    /**
	     * 图片对齐方式
	     */
	    private String align;
	
	    /**
	     * 图片标题
	     */
	    private String imgTitle;

		
	    public String img() throws Exception {
	    		  
	            ServletActionContext.getResponse().setContentType(  
	                    "text/html; charset=UTF-8");  
	            System.out.println("111111111111111");
	            // 文件保存目录路径  
	            String savePath = ServletActionContext.getServletContext().getRealPath(  
	                    "/")  
	                    + "img/user/";  
//	            String savePath ="C:\\Users\\qun\\Desktop\\新建文件夹\\SocializationInteractionShared\\SocializationInteractionShared\\WebContent\\img\\user";
	            System.out.println("=======savePath========" + savePath);
	            // 文件保存目录URL  
	            String saveUrl = ServletActionContext.getRequest().getContextPath()  
	                    + "/img/user/";  
//	            String saveUrl = "img/user/";
	            System.out.println("==========saveUrl=============" + saveUrl);
	           // String myPath = "" ;
	            // 定义允许上传的文件扩展名  
	      
	            HashMap<String, String> extMap = new HashMap<String, String>();  
	      
	            extMap.put("image", "gif,jpg,jpeg,png,bmp");  
	            extMap.put("flash", "swf,flv");  
	            extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");  
	            extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");  
	            // 最大文件大小  
	            long maxSize = 2 * 1024 * 1024 * 100;  
	            PrintWriter out = null;  
	            try {  
	                out = ServletActionContext.getResponse().getWriter();  
	            } catch (IOException e1) {  
	      
	            }  
	      
	            if (imgFile == null) {  
	                out.println(getError("请选择文件。"));  
	                return null;  
	            }  
	      
	            // 检查目录  
	            File uploadDir = new File(savePath);  
	            if (!uploadDir.isDirectory()) {  
	            	uploadDir.mkdirs();  
	            }  
	            
//	            File myUploadPath = new File(myPath);  
//	            if (!myUploadPath.isDirectory()) {  
//	            	myUploadPath.mkdirs();  
//	            }  
	            // 检查目录写权限  
	            if (!uploadDir.canWrite()) {  
	                out.println(getError("上传目录没有写权限。"));  
	                return null;  
	            }  
	            String dirName = ServletActionContext.getRequest().getParameter("dir");  
	            if (dirName == null) {  
	                dirName = "image";  
	            }  
	            if (!extMap.containsKey(dirName)) {  
	                System.out.println(getError("目录名不正确。"));  
	                return null;  
	            }  
	            // 创建文件夹  
	            File dirFile = new File(savePath);  
	            if (!dirFile.exists()) {  
	                dirFile.mkdirs();  
	            }  
	           /* savePath += dirName + "/";  
	            saveUrl += dirName + "/";  
	            File saveDirFile = new File(savePath);  
	            if (!saveDirFile.exists()) {  
	                saveDirFile.mkdirs();  
	            }  */
	      
	            // 创建文件夹  
	            /*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
	            String ymd = sdf.format(new Date());  
	            savePath += ymd + "/";  
	            saveUrl += ymd + "/";  
	            File dirFile = new File(savePath);  
	            if (!dirFile.exists()) {  
	                dirFile.mkdirs();  
	            }  
	            String fileExt = imgFileFileName.substring(  
	                    imgFileFileName.lastIndexOf(".") + 1).toLowerCase();  
	            if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(  
	                    fileExt)) {  
	                System.out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许"  
	                        + extMap.get(dirName) + "格式。"));  
	                return null;  
	            }  
	            if (imgFile.length() > maxSize) {  
	                out.println(getError("[ " + imgFileFileName + " ]超过单个文件大小限制，文件大小[ "  
	                        + imgFile.length() + " ]，限制为[ " + maxSize + " ] "));  
	                return null;  
	            }  */
	            //获取当前用户
	            User user = userService.getById(getCurrentUser().getId());
	            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
	            String newFileName = user.getLoginName()+".jpg";  
	            File uploadedFile = new File(savePath, newFileName);  
	            try {  
	                FileUtil.copyFile(imgFile, uploadedFile);  
	                JSONObject obj = new JSONObject();  
	                obj.put("error", 0);  
	                obj.put("url", saveUrl + newFileName);  
	                //out.println(obj.toString());  
	                user.setHeadImage(saveUrl + newFileName);
	                userService.update(user);
	                
	            } catch (IOException e) {  
	      
	            }  
	            return "toList"; 
	           
	        }  
	      
	    
	    
	    
	    private String getError(String message) {
	    	JSONObject obj = new JSONObject();
	    	obj.put("error", 1);
	    	obj.put("message", message);
	    	return obj.toJSONString();
	    }
	    
	    
	    
	    
	    
	    
	    //=======================
	    public File getImgFile() {
			return imgFile;
		}

		public void setImgFile(File imgFile) {
			this.imgFile = imgFile;
		}

		public String getImgFileFileName() {
			return imgFileFileName;
		}

		public void setImgFileFileName(String imgFileFileName) {
			this.imgFileFileName = imgFileFileName;
		}

		public String getImgWidth() {
			return imgWidth;
		}

		public void setImgWidth(String imgWidth) {
			this.imgWidth = imgWidth;
		}

		public String getImgHeight() {
			return imgHeight;
		}

		public void setImgHeight(String imgHeight) {
			this.imgHeight = imgHeight;
		}

		public String getAlign() {
			return align;
		}

		public void setAlign(String align) {
			this.align = align;
		}

		public String getImgTitle() {
			return imgTitle;
		}

		public void setImgTitle(String imgTitle) {
			this.imgTitle = imgTitle;
		}
	    
}
