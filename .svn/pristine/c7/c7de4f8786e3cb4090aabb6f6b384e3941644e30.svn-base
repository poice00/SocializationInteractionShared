package com.socialization.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.relation.RoleList;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.socialization.base.BaseAction;
import com.socialization.domain.Privilege;
import com.socialization.domain.Role;



@SuppressWarnings("serial")
@Controller
@Scope("prototype")		//坑死爹了
public class PrivilegeAction extends BaseAction<Privilege> {
	
	private long roleId;	//角色id
	private String ids;		//权限id的集合，从请求中获取
	private HttpServletResponse response;
	
	public PrivilegeAction(){
		response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
	}
	
	/**
	 * @desc 为权限管理页面准备数据
	 * @return 跳转到显示数据的页面
	 * @throws Exception
	 * @author yanbaobin@yeah.net
	 * @date Aug 11, 2015 9:13:09 PM
	 */
	public String list() throws Exception {
		
		/*获取角色列表，并移除游客*/
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		/*获取权限列表*/
		List<Privilege> privilegeList = privilegeService.findRealPrivilege();
		ActionContext.getContext().put("privilegeList",privilegeList);
		
		return "list";
	}
	
	/**
	 * @desc 判断角色名是否已经存在
	 * @author yanbaobin@yeah.net
	 * @date Aug 11, 2015 9:14:51 PM
	 */
	public void roleNameIsExist(){
		
	}

	/**
	 * @desc 读取角色的权限
	 * @author yanbaobin@yeah.net
	 * @date Aug 12, 2015 10:22:55 AM
	 */
	public void readPrivilege(){
		Role role = roleService.getById(getRoleId());
		Set<Privilege> privileges = role.getPrivileges();
		
		StringBuilder sb = new StringBuilder();
		for (Privilege p : privileges) {
			sb.append(p.getId()).append(",");
		}
		
		try {
			response.getWriter().write(sb.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * @desc 更新角色权限
	 * @author yanbaobin@yeah.net
	 * @date Aug 12, 2015 10:23:40 AM
	 */
	public void update() {
		Set<Privilege> privileges = new HashSet<>();
		
		if(!"".equals(getIds())){
			String[] ids = getIds().split(",");
			System.out.println("ids" + getIds());
			for (String s : ids)
				privileges.add(privilegeService.getById((long) Integer.parseInt(s)));
		}
		Role role = roleService.getById(roleId);
		role.setPrivileges(privileges);
		
		roleService.update(role);
		
		try {
			response.getWriter().write("更新成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/*************************getter和setter*************************/
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
