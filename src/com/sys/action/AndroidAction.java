package com.sys.action;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import com.sys.model.User;
import com.sys.service.Impl.ContactorService;
import com.sys.utils.Constants;

public class AndroidAction {

	@Resource
	private ContactorService contactorService;
	
	private User user;
	
	
	
	public String  backupContactor()
	{
		
		return Constants.RETURN_JSON;
	}
	
	public String synchronousContactor()
	{
		if(user==null||user.getDetailInfor().getCellphoneNumber()==null||user.getDetailInfor().getCellphoneNumber().endsWith(""))
		{
			
			return Constants.RETURN_JSON;
		}
		
		contactorService.findContactorByCellphpneNumber(null);
		JSONArray jsonArray=new JSONArray();
		return Constants.RETURN_JSON;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}
