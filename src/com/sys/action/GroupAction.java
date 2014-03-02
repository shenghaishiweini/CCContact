package com.sys.action;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.model.Contactor;
import com.sys.model.Group;
import com.sys.model.User;
import com.sys.serviceInterface.IContactorService;
import com.sys.serviceInterface.IGroupService;

@Controller @Scope("prototype")
public class GroupAction extends ActionSupport {
	
	@Resource IGroupService groupService;
	@Resource IContactorService contactorService;
	
	private int id;
	private String groupName;
	private Group group;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
        
        if(user == null){
        	return INPUT;
        }
        
		List<Group> list = groupService.getAllGroupByUserId(user.getId());
		
		Map<Integer,Integer> contactorNumbers = new HashMap<Integer,Integer>();
		List<Contactor> allContactors = contactorService.findAllContactorsByUserId(user.getId());
		contactorNumbers.put(0, allContactors.size());
		for(int i=0;i<list.size();i++){
			int groupId = list.get(i).getId();
			List<Contactor> temp = groupService.getGroupContactors(groupId);
			int number = 0;
			if(temp != null){
				number = temp.size();
			}
			contactorNumbers.put(groupId,number);
		}
		System.out.println(contactorNumbers.get(0));
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		request.setAttribute("numbers", contactorNumbers);
		
		return SUCCESS;
	}
	
	public String newGroup1()
	{
		Map<String, Object> session = ActionContext.getContext().getSession();
        User user = (User)session.get("user");
        
        if(user == null){
        	return INPUT;
        }
        
		List<Group> list = groupService.getAllGroupByUserId(user.getId());
		
		Map<Integer,Integer> contactorNumbers = new HashMap<Integer,Integer>();
		List<Contactor> allContactors = contactorService.findAllContactorsByUserId(user.getId());
		contactorNumbers.put(0, allContactors.size());
		for(int i=0;i<list.size();i++){
			int groupId = list.get(i).getId();
			List<Contactor> temp = groupService.getGroupContactors(groupId);
			int number = 0;
			if(temp != null){
				number = temp.size();
			}
			
			contactorNumbers.put(groupId,number);
		}
		System.out.println(contactorNumbers.get(0));
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		request.setAttribute("numbers", contactorNumbers);
		
		return SUCCESS;
	}
	
	public String newGroup2()
	{
		Map<String, Object> session = ActionContext.getContext().getSession();
		User owner = (User)session.get("user");
		
		Group temp = groupService.getGroupByGroupName(groupName);
		if(temp == null){
			Group group = new Group();
			group.setGroupName(groupName);
			group.setOwner(owner);
			
			groupService.newGroup(group);
		}else if(temp != null){
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("tipMessage", "分组名重复！");
			return INPUT;
		}
		
		return SUCCESS;
	}
	
	public String listContactorsOfGroup() throws Exception
	{
		List<Contactor> list = groupService.getGroupContactors(id);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		
		if(list != null){
			Map<Integer,String> groupsOfContactor = new HashMap<Integer,String>();
			for(int i=0;i<list.size();i++){
				List<Group> groups = groupService.getAllGroupsByContactorId(list.get(i).getId());
				StringBuffer str = new StringBuffer();
				for(int j=0;j<groups.size();j++){
					String temp = groups.get(j).getGroupName();
					if(!"default".equals(temp)){
						str.append(temp);
						str.append("   "); //用3个空格作为间隔符
					}				
				}
				groupsOfContactor.put(list.get(i).getId(), str.toString());
			}
			request.setAttribute("groupsOfContactor", groupsOfContactor);
		}		
		
		return SUCCESS;
	}
	
}
