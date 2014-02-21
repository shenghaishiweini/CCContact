package com.sys.action;

import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.model.User;
import com.sys.serviceInterface.IUserService;

public class UserAction extends ActionSupport {

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
	
	public String logout()
	{
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.remove("user");
		return LOGIN;
	}

	// public String checklogin()
	// {
	// System.out.print("got a message from android client");
	// System.out.print(user);
	// //ActionContext.getContext().put("a", "hello");
	// try {
	// PrintWriter pw=ServletActionContext.getResponse().getWriter();
	// pw.print(user)
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return "ok";
	// }


}
