package com.sys.model;

import java.util.HashSet;
import java.util.Set;






/**
 * 联系人类
 * @author Gui Junfei
 * 2014.2.7
 */
public class Contactor {
	private int id;
	private String name;
	private String gender;
	/**
	 * 图片存放在指定的目录下，在数据库中，只存放图片的URL
	 */
	private String picture_url;
	private String cellphoneNumber;
	private String telephoneNumber;
	private String email;
	private String address;
	private String QQ;
	private String comments;//备注
	private String other1;//扩展用
	private String other2;
	
	/**
	 * 外键，关联到Group类的主键
	 * many to one 
	 * 一个联系人可以属于多个分组
	 */
//	private Set groups=new HashSet(0);
//	private Group group;
	
	private User owner; 
	
	
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
	public void setPicture_url(String pictureUrl) {
		picture_url = pictureUrl;
	}
//	public Group getGroup() {
//		return group;
//	}
//	public void setGroup(Group group) {
//		this.group = group;
//	}
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
	
	public static Contactor copyContactor(Contactor newContactor,Contactor oldContactor)
	{
		newContactor.setAddress(oldContactor.getAddress());
		newContactor.setCellphoneNumber(oldContactor.getCellphoneNumber());
		newContactor.setComments(oldContactor.getComments());
		newContactor.setEmail(oldContactor.getEmail());
		newContactor.setGender(oldContactor.getGender());
//		newContactor.setGroup(oldContactor.getGroup());
//		newContactor.setGroups(oldContactor.getGroups());
		newContactor.setName(oldContactor.getName());
		newContactor.setOther1(oldContactor.getOther1());
		newContactor.setOther2(oldContactor.getOther2());
		return newContactor;
	}
//	public void setGroups(Set groups) {
//		this.groups = groups;
//	}
//	public Set getGroups() {
//		return groups;
//	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public User getOwner() {
		return owner;
	}
}
