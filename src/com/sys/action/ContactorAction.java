package com.sys.action;

import java.util.ArrayList;
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
public class ContactorAction extends ActionSupport {
	
	@Resource IContactorService contactorService;
	@Resource IGroupService groupService;

	private Contactor contactor;
	private int id;
	private String[] selectedGroups;
	private List<String> selectedContactor = new ArrayList<String>();
		
	public List<String> getSelectedContactor() {
		return selectedContactor;
	}

	public void setSelectedContactor(List<String> selectedContactor) {
		this.selectedContactor = selectedContactor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getSelectedGroups() {
		return selectedGroups;
	}

	public void setSelectedGroups(String[] selectedGroups) {
		this.selectedGroups = selectedGroups;
	}

	public Contactor getContactor() {
		return contactor;
	}

	public void setContactor(Contactor contactor) {
		this.contactor = contactor;
	}

	
	public String addContactor1() throws Exception
	{
		ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> session = actionContext.getSession();
		User owner = (User)session.get("user");
		
		List<Group> groups = groupService.getAllGroupByUserId(owner.getId());
		groups.remove(0); //在groups中删除default分组，因为在添加联系人页面中不需要显示“未分组”这个选项
		
		session.put("groups", groups);
		
		return SUCCESS;
	}
	
	public String addContactor2() throws Exception
	{
		
		ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> session = actionContext.getSession();
		User owner = (User)session.get("user");
		
		contactor.setOwner(owner);

		if(selectedGroups.length==0){
			contactorService.addContactorDefault(contactor);
		}else{
			contactorService.addContactor(contactor);
			for(int i=0;i<selectedGroups.length;i++){
				Group temp = groupService.getGroupByGroupName(selectedGroups[i]);
				contactorService.addContactorToNewGroup(contactor, temp);
			}
		}	
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("tipMessage", "添加成功！");
		//session.put("tipMessage", "添加成功！");

		return SUCCESS;
	}	
	
	public String getSingleContactor() throws Exception
	{
		Contactor temp = contactorService.findContactorById(id);
		
		List<Group> groups = groupService.getAllGroupsByContactorId(id);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("contactor", temp);
		request.setAttribute("groups", groups);
		
		return SUCCESS;
	}
	
	public String updateContactor() throws Exception
	{
		Contactor temp = contactorService.findContactorById(id);
		temp.setName(contactor.getName());
		temp.setGender(contactor.getGender());
		temp.setCellphoneNumber(contactor.getCellphoneNumber());
		temp.setTelephoneNumber(contactor.getTelephoneNumber());
		temp.setEmail(contactor.getEmail());
		temp.setAddress(contactor.getAddress());
		temp.setQQ(contactor.getQQ());
		temp.setComments(contactor.getComments());
		temp.setOther1(contactor.getOther1());
		temp.setOther2(contactor.getOther2());
		
		contactorService.alterContactor(temp);
		
		groupService.removeGroupContactorItemOfContactor(id);
		
		if(selectedGroups.length==0){
			Group temp1 = groupService.getGroupByGroupName("default");
			contactorService.addContactorToNewGroup(temp,temp1);
		}else{
			for(int i=0;i<selectedGroups.length;i++){
				Group temp2 = groupService.getGroupByGroupName(selectedGroups[i]);
				contactorService.addContactorToNewGroup(temp, temp2);
			}
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("tipMessage", "更改成功！");
		
		return SUCCESS;
	}
	
	public String deleteContactor() throws Exception
	{
		System.out.println(selectedContactor.size());
		System.out.println(selectedContactor.get(0));
		return SUCCESS;
	}
	
	public String findAllContactorsByUserId() throws Exception
	{

//		List<Contactor> list = contactorService.listAllContactors();
//		HttpServletRequest request = ServletActionContext.getRequest();
//		request.setAttribute("list", list);

		ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> session = actionContext.getSession();
        User user = (User)session.get("user");
        
		List<Contactor> list = contactorService.findAllContactorsByUserId(user.getId());
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);

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
		
		return SUCCESS;
	}
	
//	public String getSingleContactor() throws Exception
//	{
//		Contactor contactor = contactorService.getSingleContactorById(id);
//
//		HttpServletRequest request = ServletActionContext.getRequest();
//
//		request.setAttribute("contactor", contactor);
//
//		return SUCCESS;
//	}
//	
//	public String updateContactor() throws Exception
//	{
//		Contactor contactor = contactorService.getSingleContactorById(id);
//		
//		contactor.setName(name);
//		contactor.setCellphoneNumber(cellphoneNumber);
//		contactor.setQQ(QQ);
//		
//		contactorService.updateContactor(contactor);
//		
//		return SUCCESS;
//	}
//	
//	public String deleteContactor() throws Exception
//	{
//		contactorService.delete(id);
//
//		return SUCCESS;
//	}
//	
//	public String listSearchResults() throws Exception
//	{
//		List<Contactor> list = contactorService.listSearchResults(name);
//		HttpServletRequest request = ServletActionContext.getRequest();
//		request.setAttribute("list", list);
//		
//		return SUCCESS;
//	}
	
	
}
