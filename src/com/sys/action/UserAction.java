package com.sys.action;

import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.model.User;
import com.sys.serviceInterface.IUserService;

public class UserAction extends ActionSupport{

	@Resource IUserService userService;
	
	private  User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String login()
	{
		return "userLogin";
	}
	
	public String login1()
	{
		ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> session = actionContext.getSession();
        
        User user = new User();
        user.setId(1);
        user.setUsername("fuyu");
        
        session.put("user", user);
        
        return SUCCESS;
	}
	
//	public String checklogin()
//	{
//		System.out.print("got a message from android client");
//		System.out.print(user);
//		//ActionContext.getContext().put("a", "hello");
//		try {
//			PrintWriter pw=ServletActionContext.getResponse().getWriter();
//			pw.print(user)
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "ok";
//	}
	
}
