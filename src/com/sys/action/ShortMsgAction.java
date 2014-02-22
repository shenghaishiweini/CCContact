package com.sys.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.model.Group;
import com.sys.model.ShortMsg;
import com.sys.model.User;
import com.sys.serviceInterface.IShortMsgService;

public class ShortMsgAction extends ActionSupport{

	@Resource
	private IShortMsgService _isIShortMsgService;
	
	public String getConversationList()
	{
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		List<ShortMsg> list=_isIShortMsgService.readAllConversations(3);
		session.put("conversationlist", list);
		
		return SUCCESS;
	}
	
	
}
