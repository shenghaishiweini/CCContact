package com.sys.action;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.model.Group;
import com.sys.model.User;
import com.sys.serviceInterface.IGroupService;

@Controller @Scope("prototype")
public class GroupAction extends ActionSupport {
	
	@Resource IGroupService groupService;
	
	private Group group;

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String listAllGroups()
	{
        Map<String, Object> session = ActionContext.getContext().getSession();
        User user = (User)session.get("user");
		List<Group> list = groupService.getAllGroupByUserId(user.getId());
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		
		return SUCCESS;
	}
	
	public String newGroup()
	{
		Map<String, Object> session = ActionContext.getContext().getSession();
		User owner = (User)session.get("user");
		
		Group group = new Group();
		group.setGroupName("bbbbb");
		group.setOwner(owner);
		
		groupService.newGroup(group);
		
		return SUCCESS;
	}
	
}
