package com.sys.service;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.sys.model.User;
import com.sys.serviceInterface.IUserService;

/**
 * 用户接口实现类
 * @author Gui Junfei
 * 2014.2.7
 */
public class UserService implements IUserService{

	@Resource
	private SessionFactory sessionFactory;
	
	@Transactional
	public boolean registerUser(User user) {
		
		try {
			sessionFactory.getCurrentSession().persist(user);
			
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
		
	}

	@Transactional
	public User findUserById(int userid) {
		User user = (User) sessionFactory.getCurrentSession().get(User.class,
				userid);
		return user;
	}

	public boolean alterUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	public User checkLogin(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteUser(int userid) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
