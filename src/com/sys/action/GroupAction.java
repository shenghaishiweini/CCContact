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
	
	private int id;
	private String groupName;
	private Date createTime;
	private int memberNum=0;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
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
		String str = "aaaa";
		Group group = new Group();
		group.setGroupName(str);
		groupService.newGroup(group);
		
		return SUCCESS;
	}
	
}
