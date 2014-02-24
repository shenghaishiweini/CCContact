package com.sys.action;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletRequestContext;
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
	private ShortMsg shortMsg;
	
	public String getConversationList()
	{
		
	      	Map<String, Object> session = ActionContext.getContext().getSession();
	        User user = (User)session.get("user");
	        List<ShortMsg> list=_isIShortMsgService.readAllConversations(user.getId());
	        List<ShortMsg> res = new ArrayList<ShortMsg>();
	        for(int i=0;i<list.size();i++)
	        {
	        	if(!ifExist(list.get(i), res))
	        	{
	        		if(list.get(i).getFrom().equals(user.getDetailInfor().getCellphoneNumber()))
	        		{
	        			list.get(i).setConversation(list.get(i).getToName());
	        		}
	        		else
	        		{
	        			list.get(i).setConversation(list.get(i).getFromName());
	        		}
	        		res.add(list.get(i));
	        	}
	        }
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("conversationlist", res);
			return SUCCESS;
	}
	
	private boolean ifExist(ShortMsg target,List<ShortMsg> sourceList)
	{
		for(int i=0;i<sourceList.size();i++)
		{
			if((sourceList.get(i).getFrom().equals(target.getFrom())&&sourceList.get(i).getTo().equals(target.getTo()))
					||(sourceList.get(i).getFrom().equals(target.getTo())&&sourceList.get(i).getTo().equals(target.getFrom())))
			{
				return true;
			}
		}
		return false;
	}
	
	public String getConversationDetailMsgs()
	{
		System.out.print(this.shortMsg.getId());
		ShortMsg sMsg=_isIShortMsgService.getShortMsgById(this.shortMsg.getId());
		Map<String, Object> session = ActionContext.getContext().getSession();
        User user = (User)session.get("user");
		String recipipentCellphoneNumber="";
		if(sMsg.getFrom().equals(user.getDetailInfor().getCellphoneNumber()))
		{
			recipipentCellphoneNumber=sMsg.getTo();
		}
		else
		{
			recipipentCellphoneNumber=sMsg.getFrom();
		}
        List<ShortMsg> list=_isIShortMsgService.readConversationDetailMsgs(recipipentCellphoneNumber, user.getId());
        
        HttpSession httpSession=ServletActionContext.getRequest().getSession();
        httpSession.removeAttribute("conversationDetailMsgs");
        httpSession.setAttribute("conversationDetailMsgs", list);
		return "success";
	}

	public void setShortMsg(ShortMsg shortMsg) {
		this.shortMsg = shortMsg;
	}

	public ShortMsg getShortMsg() {
		return shortMsg;
	}
	
}
