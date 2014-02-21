package com.sys.action;

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
import com.sys.model.User;
import com.sys.serviceInterface.IContactorService;

@Controller @Scope("prototype")
public class ContactorAction extends ActionSupport {
	
	@Resource IContactorService contactorService;

	private Contactor contactor;
	
	public Contactor getContactor() {
		return contactor;
	}

	public void setContactor(Contactor contactor) {
		this.contactor = contactor;
	}

	public String addContactor() throws Exception
	{
		Contactor contactor = new Contactor();

		contactor.setName("fuyu");
//		contactor.setGender(gender);
//		contactor.setPicture_url(picture_url);
		contactor.setCellphoneNumber("123456");
//		contactor.setTelephoneNumber(telephoneNumber);
//		contactor.setEmail(email);
//		contactor.setAddress(address);
		contactor.setQQ("741232163");
//		contactor.setComments(comments);
//		contactor.setOther1(other1);
//		contactor.setOther2(other2);
		
		ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> session = actionContext.getSession();
		User owner = (User)session.get("user");
		
		contactor.setOwner(owner);


		contactorService.addContactorDefault(contactor);

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

		
		for(int i=0;i<list.size();i++){
			
		}
		
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
