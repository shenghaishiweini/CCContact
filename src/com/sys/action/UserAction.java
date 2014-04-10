package com.sys.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.model.User;
import com.sys.service.Interface.IUserService;

public class UserAction extends ActionSupport {

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

	public String alterUser() {
		HttpServletRequest req = ServletActionContext.getRequest();
		// 获取此请求的地址，请求地址包含application name，进行subString操作，去除application name
		String path = req.getRequestURI();

		System.out.print(user);
		User olduser = _userService.findUserById(user.getId());
		olduser.setDetailInfor(user.getDetailInfor());
		if (_userService.alterUser(olduser)) {
			ActionContext actionContext = ActionContext.getContext();
			Map<String, Object> session = actionContext.getSession();
			session.put("user", olduser);// update the user in session
			session.put("prePage", path);
		}
		return "alterFail";
	}

	public String modifyPwd() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		User olduser = _userService.findUserById(user.getId());
		if (user.getPassword().equals(olduser.getPassword())) {
			olduser.setPassword(user.getDetailInfor().getOther1());
			if (_userService.alterUser(olduser)) {
				session.put("user", olduser);// update the user in session
				session.put("msg", "修改成功");
			}
		} else {
			session.put("msg", "您的原密码输入有误，请重新输入！");

		}
		return "modifyPwd";
	}

	public String checkUserName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String reString = (String) request.getParameter("u");
		System.out.print(reString);
		return "ok";
	}

	public String logout() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.remove("user");
		session.clear();
		return LOGIN;
	}

	
}
