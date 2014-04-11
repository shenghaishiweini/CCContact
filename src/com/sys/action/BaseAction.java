package com.sys.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.sys.model.User;
import com.sys.utils.Constants;
import com.sys.utils.PageInfo;

public class BaseAction {

	/**
	 *
	 */
	public String msg;
	
	 /**
     * 界面信息
     */
    protected PageInfo pageInfo; 
    
    /**
     * 
     */
	protected int pageIndex = 1; 
	
	/**
	 *
	 */
	protected  int pageSize = Constants.PAGE_SIZE;

	
	protected  ActionContext actionContext = ActionContext.getContext();
	
	protected  Map<String, Object> session=actionContext.getSession();

	/**
	 * 当前用户
	 */
	public User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	} 
	
	
}
