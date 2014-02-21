package com.sys.action;

import javax.annotation.Resource;

import com.sys.serviceInterface.IShortMsgService;

public class ShortMsgAction {

	@Resource
	private IShortMsgService _isIShortMsgService;
	
	public String getConversationList()
	{
		
		return "";
	}
	
}
