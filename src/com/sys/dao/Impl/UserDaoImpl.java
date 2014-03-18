package com.sys.dao.Impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

import com.sys.dao.Interface.IUserDao;
import com.sys.model.User;

public class UserDaoImpl implements IUserDao {

	@Resource
	private SessionFactory sessionFactory;
	
	public void add(User user) {
		// TODO Auto-generated method stub

	}

	public void delete(User user) {
		// TODO Auto-generated method stub

	}

	public void delete(int userId) {
		// TODO Auto-generated method stub

	}

	public User find(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(User user) {
		// TODO Auto-generated method stub

	}

}
