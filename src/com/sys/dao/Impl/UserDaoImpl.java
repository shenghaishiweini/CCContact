package com.sys.dao.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.sys.dao.Interface.IUserDao;
import com.sys.model.Contactor;
import com.sys.model.Group;
import com.sys.model.Group_Contactor;
import com.sys.model.User;

public class UserDaoImpl implements IUserDao {

	@Resource
	private SessionFactory sessionFactory;
	
	public void add(User user) {

			Group defaultGroup = new Group();
			defaultGroup.setGroupName("default");
			defaultGroup.setOwner(user);
			sessionFactory.getCurrentSession().persist(user);
			sessionFactory.getCurrentSession().persist(defaultGroup);

	}

	public void delete(User user) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	public void delete(int userId) {
		String hql0="select * from Contactors where userID=:userid ";
		Query q = sessionFactory.getCurrentSession().createSQLQuery(hql0)
				.addEntity(Contactor.class);		
		q.setParameter("userid", userId);
		List<Contactor> res0 = q.list();
		for(int i = 0; i < res0.size(); i++)
		{
			sessionFactory.getCurrentSession().delete(res0.get(i));
		}	
		 
		String hql1="select * from Groups where userID=:userid ";
		Query r = sessionFactory.getCurrentSession().createSQLQuery(hql1)
				.addEntity(Group.class);		
		r.setParameter("userid", userId);
		List<Group> res1 = r.list();
		for(int i = 0; i < res1.size(); i++)
		{
			Group_Contactor group_contactor = (Group_Contactor)sessionFactory.getCurrentSession().get(Group_Contactor.class,res1.get(i).getId());
			if(group_contactor != null)
				sessionFactory.getCurrentSession().delete(group_contactor);
			sessionFactory.getCurrentSession().delete(res1.get(i));
		}	
		User user=(User)sessionFactory.getCurrentSession().get(User.class,userId);
		sessionFactory.getCurrentSession().delete(user);
	}

	public User find(int userid) {
		User user = (User) sessionFactory.getCurrentSession().get(User.class,
				userid);
		return user==null?null:user;
	}

	public void update(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@SuppressWarnings("unchecked")
	public User find(String username, String password) {
		String hql="select * from Users where username=:username and password=:password";
		Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
				.addEntity(User.class);		
		q.setParameter("username", username);
		q.setParameter("password",password);
		
		List<User> res = q.list();
		
		return res==null?null:res.get(0);
	
	}
	
}
