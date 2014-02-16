package com.sys.model;

import java.util.Set;

/**
 * 用户类
 * @author Gui Junfei
 * 2014.2.7
 */
public class User extends Contactor{
    private int id;
    private String username;
    private String password;
    
    private Contactor detailInfor;
    @SuppressWarnings("unchecked")
	private Set groups;
    @SuppressWarnings("unchecked")
	private Set messages;
    
    /*
     * android端当前的IP地址
     * 用于WEB和android通信使用
     * WEB向android端发送消息时需要使用
     */
    private String ipAdress;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setDetailInfor(Contactor detailInfor) {
		this.detailInfor = detailInfor;
	}
	public Contactor getDetailInfor() {
		return detailInfor;
	}
	@SuppressWarnings("unchecked")
	public void setGroups(Set groups) {
		this.groups = groups;
	}
	@SuppressWarnings("unchecked")
	public Set getGroups() {
		return groups;
	}
	@SuppressWarnings("unchecked")
	public void setMessages(Set messages) {
		this.messages = messages;
	}
	@SuppressWarnings("unchecked")
	public Set getMessages() {
		return messages;
	}
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}
	public String getIpAdress() {
		return ipAdress;
	}

}
