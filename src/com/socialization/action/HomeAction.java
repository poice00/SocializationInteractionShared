package com.socialization.action;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialization.domain.Activity;
import com.socialization.domain.Topic;
import com.socialization.service.TopicService;
import com.socialization.service.resource.ActiviService;
import com.socialization.service.resource.UpdowncollectResService;
import com.socialization.util.FullTextIndex;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class HomeAction extends ActionSupport{
	
	@Resource
	private TopicService topicService;
	@Resource
	private UpdowncollectResService resourceService;
	@Resource
	private ActiviService activityService;
	
	private String keywords;		//模糊搜索的关键字

	/**
	 * @desc 为网站首页准备数据
	 * @return 论坛、资源、活动的集合
	 * @throws Exception
	 * @author yanbaobin@yeah.net
	 * @date Aug 14, 2015 9:17:01 AM
	 */
	public String index() throws Exception {
		List<Topic> topicList = topicService.findhotTopics();
		Topic topic1 = topicList.get(0);
		ActionContext.getContext().put("topic1", topic1);
		ActionContext.getContext().put("topicList", topicList);
		
		
		
		List<com.socialization.domain.Resource> resourceList = resourceService.getTop10();
		com.socialization.domain.Resource resource1 = resourceList.get(0);
		ActionContext.getContext().put("resource1", resource1);
		ActionContext.getContext().put("resourceList", resourceList);
		
		List<Activity> activityList = activityService.getTop10();
		Activity activity1 = activityList.get(0);
		ActionContext.getContext().put("activity1", activity1);
		ActionContext.getContext().put("activityList", activityList);

		return "index";
	}
	public String mid() throws Exception {
		return "mid";
	}
	public String top() throws Exception {
		return "top";
	}
	public String bottom() throws Exception {
		return "bottom";
	}
	public String blank() throws Exception {
		return "blank";
	}
	public String title() throws Exception {
		return "title";
	}
	
	/**
	 * @desc 模糊搜索
	 * @author yanbaobin@yeah.net
	 * @date Aug 14, 2015 9:21:26 AM
	 */
	public void fuzzySearch() {
		
		HashMap<String, String> topics = FullTextIndex.search(getKeywords(), "forum");			//尼玛，又报异常，要气死爹吗
		HashMap<String, String > resources = FullTextIndex.search(getKeywords(), "resource");	//一句话救了我，web-inf\lib
		HashMap<String, String> activities = FullTextIndex.search(getKeywords(), "activity");
		
		int order = 0;	//序号
		StringBuilder sb = new StringBuilder();
		
		/*论坛*/
		if(topics.size() == 0)
			sb.append("<tr><td><b>搜索结果为空</b></td></tr>");
		else{
			Set<String> ids = topics.keySet();
			for (String id : ids) {
				sb
				.append("<tr>")
					.append("<td>")
						.append(++order)
					.append("</td>")
					.append("<td>")
						.append("<a href='topic_show.action?id=").append(id).append("'>").append(topics.get(id)).append("</a>")
					.append("</td>")
				.append("</tr>");
			}
		}
		
		sb.append(",");		//添加分隔符
		
		/*资源*/
		if(resources.size() == 0)
			sb.append("<tr><td><b>搜索结果为空</b></td></tr>");
		
		else{
			order = 0;
			Set<String> ids = resources.keySet();
			for (String id : ids) {
				sb
				.append("<tr>")
					.append("<td>")
						.append(++order)
					.append("</td>")
					.append("<td>")
						.append("<a href='resource_showSingleRes.action?idd=").append(id).append("'>").append(resources.get(id)).append("</a>")
					.append("</td>")
				.append("</tr>");
			}
		}
		
		sb.append(",");		//添加分隔符
		
		/*活动*/
		if(activities.size() == 0)
			sb.append("<tr><td><b>搜索结果为空</b></td></tr>");
		
		else{
			order = 0;
			Set<String> ids = activities.keySet();
			for (String id : ids) {
				sb
				.append("<tr>")
					.append("<td>")
						.append(++order)
					.append("</td>")
					.append("<td>")
						.append("<a href='activity_activityDetails.action?idd=").append(id).append("'>").append(activities.get(id)).append("</a>")
					.append("</td>")
				.append("</tr>");
			}
		}
		
		/*向浏览器输出*/
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		try {
			response.getWriter().write(sb.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
