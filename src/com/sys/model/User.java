package com.sys.model;

import java.util.Set;

/**
 * �û���
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
     * android�˵�ǰ��IP��ַ
     * ����WEB��androidͨ��ʹ��
     * WEB��android�˷�����Ϣʱ��Ҫʹ��
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
