package com.socialization.action.resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionContext;
import com.socialization.base.BaseAction;
import com.socialization.domain.ResType;
import com.socialization.domain.Resource;
import com.socialization.domain.User;
import com.socialization.resourceutil.TimeCount;
import com.socialization.util.QueryHelper;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ResourceAction extends BaseAction<Resource> {

	/*---资源页面首页----*/
	public String homePage() {
		List<Resource> resList = this.updowncollectResService.findAll();
		Map<String, Integer> resTagMap = new TimeCount().resToMap(resList);
		if (resTagMap.size() <= 5) {
			resTagMap.put("其他", 0);
		} else {
			List<Resource> resTgList = this.updowncollectResService.findByResMap(resTagMap);
			resTagMap.put("其他", resTgList.size());
		}
		ActionContext.getContext().getSession().put("resTagMap", resTagMap);
		new QueryHelper(Resource.class, "r").addOrderProperty("r.postTime", false)
				.perparePageBean(updowncollectResService, currentPage, pageSize);
		return "homePage";
	}

	/*---高级搜索页面----*/
	public String highsearchUI() throws Exception {
		List<ResType> resTypeList = this.resStyleService.findAll();
		ActionContext.getContext().put("resTypeList", resTypeList);
		return "highsearchUI";
	}

	/*---高级搜索结果----*/
	public String highsearch() throws Exception {
		Date searchDate = new TimeCount().timeCount(resUpTime);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String seaDate = formatter.format(searchDate);
		int kz = 1; // 判断有几个空值，作为比对的标准，至少满足一个条件
		if (resDescription.isEmpty()) {
			kz += 1;
		}
		if (resname.isEmpty()) {
			kz += 1;
		}
		if (resourceTags.isEmpty()) {
			kz += 1;
		}
		if (resUpTime.equals("请选择日期")) {
			kz += 1;
		}
		if (resTypeId == null) {
			kz += 1;
		}
		resDescription = new String(resDescription.getBytes("iso-8859-1"), "UTF-8");
		resname = new String(resname.getBytes("iso-8859-1"), "UTF-8");
		resourceTags = new String(resourceTags.getBytes("iso-8859-1"), "UTF-8");
		List<Resource> highresLevelList = this.updowncollectResService.highLevelSearch(resDescription, resname,
				resourceTags, seaDate, resTypeId, kz);
		if (highresLevelList.size() == 0) {
			this.setResTvalue("大侠，暂无你想要的资源，还请换个条件再搜一次吧~");
			addActionMessage("大侠，暂无你想要的资源，还请换个条件再搜一次吧~");
			this.setResname(null);
			this.setResDescription(null);
			this.setResourceTags(null);
			this.setResUpTime(null);
			List<ResType> resTypeList = this.resStyleService.findAll();
			ActionContext.getContext().put("resTypeList", resTypeList);
			return "highsearchFai";
		} else {
			ActionContext.getContext().put("highresLevelList", highresLevelList);
			return "highsearchSuc";
		}
	}

	/*---资源分类页面----*/
	public String resTypeUI() throws Exception {
		List<Resource> resList = this.updowncollectResService.findAll();
		Map<String, Integer> resTagMap = new TimeCount().resToMap(resList);
		ActionContext.getContext().put("resTagMap", resTagMap);
		if (resTagMap.size() <= 5) {
			resTvalue = "0";
			return "resTypeUI";
		} else {
			List<Resource> resTgList = this.updowncollectResService.findByResMap(resTagMap);
			resTvalue = String.valueOf(resTgList.size());
			return "resTypeUI";
		}
	}
	   /*---资源标签方法调用----*/
	public  void resType() throws Exception {
		List<Resource> resList = this.updowncollectResService.findAll();
		Map<String, Integer> resTagMap = new TimeCount().resToMap(resList);
		ActionContext.getContext().put("resTagMap", resTagMap);
		if (resTagMap.size() <= 5) {
			resTvalue = "0";
			return ;
		} else {
			List<Resource> resTgList = this.updowncollectResService.findByResMap(resTagMap);
			resTvalue = String.valueOf(resTgList.size());
			return ;
		}
	}
	/*---帮助页面----*/
	public String resHelpUI() throws Exception {
		return "resHelpUI";
	}

	/*---排行耪页面----*/
	public String resRankUI() throws Exception { // 准备各种回显数据
		String ss = "总榜单";
		this.setNumRank(1);
		List<Resource> resCounList = this.updowncollectResService.OrderByCount(ss); // 按下载数量排行
		ActionContext.getContext().put("resCounList", resCounList);
		List<Resource> resColleList = this.updowncollectResService.OrderByCollect(ss); // 按收藏数量排行
		ActionContext.getContext().put("resColleList", resColleList);
		List<User> userupCountList = this.userService.OrderByupCount(ss); // 按上传数量对用户排行
		ActionContext.getContext().put("userupCountList", userupCountList);
		return "resRankUI";
	}

	/*---单个排行耪页面----*/
	public String singalresRankUI() throws Exception {
		resTvalue = new String(resTvalue.getBytes("iso-8859-1"), "UTF-8");
		System.out.println(resTvalue);
		switch (resTvalue) {
		case "下载量":
			/*
			 * List<Resource>
			 * resCounList=this.updowncollectResService.OrderByCount(resTvalue);
			 * //按下载数量排行 ActionContext.getContext().put("resCounList",
			 * resCounList);
			 */
			new QueryHelper(Resource.class, "r").addOrderProperty("r.downCount", false)
					.perparePageBean(updowncollectResService, currentPage, pageSize);
			resTvalue = "下载量最多的资源：";
			break;
		case "收藏量":
			/*
			 * List<Resource>
			 * resColleList=this.updowncollectResService.OrderByCollect(
			 * resTvalue); //按收藏数量排行
			 * ActionContext.getContext().put("resColleList", resColleList);
			 */
			new QueryHelper(Resource.class, "r").addOrderProperty("r.collectionCount", false)
					.perparePageBean(updowncollectResService, currentPage, pageSize);
			resTvalue = "收藏量最多的资源：";
			break;
		case "上传量":
			/*
			 * List<User>
			 * userupCountList=this.userService.OrderByupCount(resTvalue);
			 * //按上传数量对用户排行 ActionContext.getContext().put("userupCountList",
			 * userupCountList);
			 */
			new QueryHelper(User.class, "u").addOrderProperty("u.resUpCount", false).perparePageBean(userService,
					currentPage, pageSize);
			resTvalue = "上传量最多的用户排行：";
			break;
		default:
			break;
		}
		return "singalresRankUI";
	}

	/*---单个分类页面----*/
	public String singalresTagUI() throws Exception {
		resTg = new String(resTg.getBytes("iso-8859-1"), "UTF-8");
		if (resTg.matches("其他")) {
			List<Resource> resList = this.updowncollectResService.findAll();
			Map<String, Integer> resTagMap = new TimeCount().resToMap(resList);
			List<Resource> resTgList = this.updowncollectResService.findByResMap(resTagMap);
			if (resTgList.size() == 0) {
				resTvalue = "暂无其他资源";
			} else {
				ActionContext.getContext().put("resTgList", resTgList);
				resTvalue = "其他资源：";
			}
		} else {
			List<Resource> resTgList = this.updowncollectResService.findByTag(resTg);
			ActionContext.getContext().put("resTgList", resTgList);
			resTvalue = "包含标签" + resTg + "的资源：";
		}
		return "singalresTagUI";
	}

	/*---得到高级搜索界面的各种数据----*/
	private String resname;
	private String resDescription;
	private String resourceTags;
	private String resUpTime;
	private Long resTypeId;
	private String resTvalue;
	private String resTg;
	private int numRank;
	public String getResname() {
		return resname;
	}

	public void setResname(String resname) {
		this.resname = resname;
	}

	public String getResDescription() {
		return resDescription;
	}

	public void setResDescription(String resDescription) {
		this.resDescription = resDescription;
	}

	public String getResourceTags() {
		return resourceTags;
	}

	public void setResourceTags(String resourceTags) {
		this.resourceTags = resourceTags;
	}

	public String getResUpTime() {
		return resUpTime;
	}

	public void setResUpTime(String resUpTime) {
		this.resUpTime = resUpTime;
	}

	public Long getResTypeId() {
		return resTypeId;
	}

	public void setResTypeId(Long resTypeId) {
		this.resTypeId = resTypeId;
	}

	public String getResTvalue() {
		return resTvalue;
	}

	public void setResTvalue(String resTvalue) {
		this.resTvalue = resTvalue;
	}

	public String getResTg() {
		return resTg;
	}

	public void setResTg(String resTg) {
		this.resTg = resTg;
	}

	public int getNumRank() {
		return numRank;
	}

	public void setNumRank(int numRank) {
		this.numRank = numRank;
	}
}
