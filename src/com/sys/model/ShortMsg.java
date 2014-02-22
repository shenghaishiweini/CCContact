package com.sys.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 短信类主要是临时使用，如果用户选择备份，则需要存入数据库
 * @author Gui Junfei
 * 2014.2.7
 */

public class ShortMsg {
	private int id;
	/*
	 * 发短信者的号码
	 * 外键，Contact的cellphoneNumber
	 */
	private String from;
	/*
	 * 收短信者的号码
	 * 外键，Contact的cellphoneNumber
	 */
	private String to;
	
	/*
	 * 发短信者的姓名
	 * 外键，Contact的name
	 * 如果不存在，则为空
	 */
	private String fromName;
	private String toName;
	private Timestamp createTime;
	private String content;
	private int msgType;//短信类型，可能是彩信或语音
	
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
