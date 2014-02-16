package com.sys.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * 分组类
 * @author Gui Junfei
 * 2014.2.7
 */
public class Group {
	private int id;
	private String groupName;
	private Date createTime;
	private int memberNum=0;
	
	/*
	 * 分组包含的联系人
	 * 关联到Contact的ID
	 * many to one
	 */
	@SuppressWarnings("unchecked")
	private Set contacts=new HashSet(0);
	
	/*
	 * 分组的拥有者
	 * 关联到User ID
	 * many to one
	 */
	private User owner;
	
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
	@SuppressWarnings("unchecked")
	public Set getContacts() {
		return contacts;
	}
	@SuppressWarnings("unchecked")
	public void setContacts(Set contacts) {
		this.contacts = contacts;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	
}
