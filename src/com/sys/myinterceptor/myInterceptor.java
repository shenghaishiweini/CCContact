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
		 * System.out.println("�����ڽ��е�¼���ع���"); String result = ""; result =
		 * invocation.invoke(); // �жϵ�ǰҪ���õ�Actionʵ�������� �Ƿ��� ��¼��֤��Action if
		 * (result.equals("userLogin")) { } else {
		 */
		// ��ǰҪ���õĲ��ǵ�¼��Action
		// ��session��ȡֵ���ж��Ƿ��ǿգ���¼��
		// ��¼��Action�н���¼�û���Ϣ�洢��session��,keyֵ�ǵ�ǰ��sessionID
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
			// ��ȡ������ĵ�ַ�������ַ����application name������subString������ȥ��application name
			String path = req.getRequestURI().substring(17);
			// ��������еĲ���
			String queryString = req.getQueryString();
			// Ԥ����ָ��
			if (queryString == null) {
				queryString = "";
			}
			// ƴ�յõ���½֮ǰ�ĵ�ַ
			String realPath = path;
			// ����session���������
			session.setAttribute("prePage", realPath);
			return "user_login";
		}
		// }
		// return result;
	}

}
