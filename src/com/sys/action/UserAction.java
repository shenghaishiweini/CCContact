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
			session.put("mesg", "��¼ʧ�ܣ������û������룡");
			return LOGIN;
		}
		User temp = _userService.checkLogin(user);
		if (temp == null) {
			session.put("mesg", "��¼ʧ�ܣ������û������룡");
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
		session.clear();
		return LOGIN;
	}
	
	public String alterUser()
	{
		System.out.print(user);
		User olduser=_userService.findUserById(user.getId());
		olduser.setDetailInfor(user.getDetailInfor());
		if(_userService.alterUser(olduser))
		{
			ActionContext actionContext = ActionContext.getContext();
			Map<String, Object> session = actionContext.getSession();
			session.put("user", olduser);//update the user in session
			
		}
		return "alterFail";
	}
	
	public String modifyPwd()
	{
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		User olduser=_userService.findUserById(user.getId());
		if(user.getPassword().equals(olduser.getPassword()))
		{
			olduser.setPassword(user.getDetailInfor().getOther1());
			if(_userService.alterUser(olduser))
			{
				session.put("user", olduser);//update the user in session
				session.put("msg", "�޸ĳɹ�");
			}
		}
		else
		{
			session.put("msg", "����ԭ���������������������룡");
			
		}
		return "modifyPwd";
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
