package com.sys.myinterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sys.action.LoginAction;
import com.sys.model.User;

public class myInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		/*
		 * System.out.println("我正在进行登录拦截功能"); String result = ""; result =
		 * invocation.invoke(); // 判断当前要调用的Action实例（对象） 是否是 登录验证的Action if
		 * (result.equals("userLogin")) { } else {
		 */
		// 当前要调用的不是登录的Action
		// 从session中取值，判断是否是空（登录）
		// 登录的Action中将登录用户信息存储到session中,key值是当前的sessionID
		if (invocation.getAction() instanceof LoginAction) {
			return invocation.invoke();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		if (user != null) {
			return invocation.invoke();
		} else {
			HttpServletRequest req = ServletActionContext.getRequest();
			// 获取此请求的地址，请求地址包含application name，进行subString操作，去除application name
			String path = req.getRequestURI().substring(17);
			// 获得请求中的参数
			String queryString = req.getQueryString();
			// 预防空指针
			if (queryString == null) {
				queryString = "";
			}
			// 拼凑得到登陆之前的地址
			String realPath = path;
			// 存入session，方便调用
			session.setAttribute("prePage", realPath);
			return "user_login";
		}
		// }
		// return result;
	}

}
