package com.sys.action;

import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.model.User;
import com.sys.serviceInterface.IUserService;

public class LoginAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	IUserService _userService;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String login() {
		return LOGIN;
	}

	public String checkLogin() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();

		if (user == null) {
			session.put("mesg", "µ«¬º ß∞‹£¨«ÎºÏ≤È”√ªß√˚√‹¬Î£°");
			return LOGIN;
		}
		User temp = _userService.checkLogin(user);
		if (temp == null) {
			session.put("mesg", "µ«¬º ß∞‹£¨«ÎºÏ≤È”√ªß√˚√‹¬Î£°");
			return LOGIN;
		}
		session.put("user", temp);
		return SUCCESS;
	}
	

	
	 public String register()
	 {
		 System.out.print(user);
		 if(_userService.registerUser(user))
		 return "ok";
		 else
			 return "fail";
	 }
}
