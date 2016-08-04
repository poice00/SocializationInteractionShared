package com.socialization.action.resource;

import java.io.InputStream;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import com.socialization.base.BaseAction;
import com.socialization.domain.Resource;

@SuppressWarnings("serial")
@Controller
public class DownloadAction extends BaseAction<Resource> {

    private Long idd;
    private String filename;
    private InputStream targetFile;

	public InputStream getTargetFile() {
		return targetFile;
	}

	public void setTargetFile(InputStream targetFile) {
		this.targetFile = targetFile;
	}
	/*---资源下载方法----*/
	public String execute() throws Exception {
     	Resource resource=this.updowncollectResService.getById(idd);  //找到当前资源
     	this.filename=resource.getResFilename();//获得当前资源的文件名字
	    String inputpath="WEB-INF/upload/"+filename;   //定义当前文件的路径
	    //String inputpath=resource.getPath();
	    targetFile=ServletActionContext.getServletContext().getResourceAsStream(inputpath);//放入输出流
     	this.filename = new String(this.filename.getBytes("GBK"),"ISO-8859-1");  //中文字符转码
     	//检查是否已经下载过，若没有下载过，则更新下载列表和资源下载量
     	boolean checkResult=this.updowncollectResService.checkDownExist(resource,getCurrentUser());
		 if (checkResult) {
			 if (resource.getCollectionCount()==0) {
					resource.setDownCount(1);
				}else
				{
					resource.setDownCount(resource.getDownCount()+1);
				}
				this.updowncollectResService.update(resource);
				this.updowncollectResService.downsave(resource, getCurrentUser());
				return "success";
		} else {    
			return "success";
		}
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Long getIdd() {
		return idd;
	}
	public void setIdd(Long idd) {
		this.idd = idd;
	}
}
