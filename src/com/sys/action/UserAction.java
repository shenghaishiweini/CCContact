package com.sys.action;

import com.sys.model.User;

public class UserAction{

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
