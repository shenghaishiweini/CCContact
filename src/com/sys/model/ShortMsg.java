package com.sys.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * ��������Ҫ����ʱʹ�ã�����û�ѡ�񱸷ݣ�����Ҫ�������ݿ�
 * @author Gui Junfei
 * 2014.2.7
 */

public class ShortMsg {
	private int id;
	/*
	 * �������ߵĺ���
	 * �����Contact��cellphoneNumber
	 */
	private String from;
	/*
	 * �ն����ߵĺ���
	 * �����Contact��cellphoneNumber
	 */
	private String to;
	
	/*
	 * �������ߵ�����
	 * �����Contact��name
	 * ��������ڣ���Ϊ��
	 */
	private String fromName;
	private String toName;
	private Timestamp createTime;
	private String content;
	private int msgType;//�������ͣ������ǲ��Ż�����
	
	private User owner;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public User getOwner() {
		return owner;
	}
	
}
