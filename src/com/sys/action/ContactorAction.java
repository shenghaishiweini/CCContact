package com.sys.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sys.model.Contactor;
import com.sys.serviceInterface.IContactorService;

@Controller @Scope("prototype")
public class ContactorAction extends ActionSupport {
	
	@Resource IContactorService contactorService;
	
	private int id;
	private String name;
	private String gender;
	private String picture_url;
	private String cellphoneNumber;
	private String telephoneNumber;
	private String email;
	private String address;
	private String QQ;
	private String comments;//±¸×¢
	private String other1;//À©Õ¹ÓÃ
	private String other2;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPicture_url() {
		return picture_url;
	}
	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}
	public String getCellphoneNumber() {
		return cellphoneNumber;
	}
	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getOther1() {
		return other1;
	}
	public void setOther1(String other1) {
		this.other1 = other1;
	}
	public String getOther2() {
		return other2;
	}
	public void setOther2(String other2) {
		this.other2 = other2;
	}
	
	public String addContactor() throws Exception
	{
		Contactor contactor = new Contactor();

		contactor.setName(name);
		contactor.setGender(gender);
		contactor.setPicture_url(picture_url);
		contactor.setCellphoneNumber(cellphoneNumber);
		contactor.setTelephoneNumber(telephoneNumber);
		contactor.setEmail(email);
		contactor.setAddress(address);
		contactor.setQQ(QQ);
		contactor.setComments(comments);
		contactor.setOther1(other1);
		contactor.setOther2(other2);

//		contactorService.addContactor(contactor);

		return SUCCESS;
	}	
	
	public String findAllContactorsByUserId() throws Exception
	{
//		List<Contactor> list = contactorService.listAllContactors();
//		HttpServletRequest request = ServletActionContext.getRequest();
//		request.setAttribute("list", list);
		
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
