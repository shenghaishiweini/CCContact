package com.sys.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.sys.model.User;
import com.sys.service.Interface.IUserService;
import com.sys.utils.Constants;

public class LoginAction extends BaseAction{

	
	@Resource
	IUserService _userService;
	
	public String login() {
		return Constants.LOGIN;
	}

	public String checkLogin() {
		 session=ActionContext.getContext().getSession();
		 
		if (user == null) {
			session.put(Constants.MSG,Constants.LOGIN_ERROR);
			return Constants.LOGIN;
		}
		User temp = _userService.checkLogin(user);
		if (temp == null) {
			session.put(Constants.MSG, Constants.LOGIN_ERROR);
			return Constants.LOGIN;
		}
		session.put(Constants.USER_KEY, temp);
		return Constants.SUCCESS;
	}
	
		public String registerUser() {
			
			if (_userService.registerUser(this.user))

				return Constants.SUCCESS;
			else
				return Constants.INPUT;
		}

		public String registerUserGet() {
				return Constants.SUCCESS;
		}
	 
	 
}
