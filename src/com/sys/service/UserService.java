package com.sys.service;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.sys.model.Group;
import com.sys.model.User;
import com.sys.serviceInterface.IUserService;

/**
 * 用户接口实现类
 * 
 * @author Gui Junfei 2014.2.7
 */
public class UserService implements IUserService {

	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public boolean registerUser(User user) {

		try {
			Group defaultGroup = new Group();
			defaultGroup.setGroupName("default");
			defaultGroup.setOwner(user);
			sessionFactory.getCurrentSession().persist(user);
			sessionFactory.getCurrentSession().persist(defaultGroup);
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

	@Transactional
	public boolean alterUser(User user) {
		try {
			Session session = sessionFactory.getCurrentSession();
			User temp = user;// (Group) session.get(Group.class,
								// group.getId());
			
			temp.setPassword("123456");
//			temp.setAddress("hefeiikk");
			if (temp != null) {
//				 temp = copyNewEntiy(temp,user);
				session.update(temp);
				session.flush();
				System.out.println(temp.getPassword());
//				System.out.println(temp.getAddress());
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

//	private User copyNewEntiy(User temp, User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public User checkLogin(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteUser(int userid) {
		// TODO Auto-generated method stub
		return false;
	}

}
