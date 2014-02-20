package com.sys.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.sys.model.Contactor;
import com.sys.model.Group;
import com.sys.model.Group_Contactor;
import com.sys.model.User;
import com.sys.serviceInterface.IUserService;

/**
 * 用户接口实现类
 * 
 * @author Gui Junfei 2014.2.7
 */
@Transactional
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
			sessionFactory.getCurrentSession().getTransaction().rollback();
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
		try{
			User temp = user;			
			if (temp !=null){
				sessionFactory.getCurrentSession().saveOrUpdate(temp);
				return true;
			}else{
				return false;
			}
		}catch (Exception e){
			System.out.print(e.getMessage());
			return false;
		}
	}

	public User checkLogin(User user) {
		String hql="select * from Users where username=:username and password=:password";
		Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
				.addEntity(User.class);		
		q.setParameter("username", user.getUsername());
		q.setParameter("password",user.getPassword());
		
		List<User> res = q.list();
		
		if(res != null && res.size()>0)
			return res.get(0);
		else  
			return null;
	}

	public boolean deleteUser(int userid) {
		try{
			//删除Contactor表
			String hql0="select * from Contactors where userID=:userid ";
			Query q = sessionFactory.getCurrentSession().createSQLQuery(hql0)
					.addEntity(Contactor.class);		
			q.setParameter("userid", userid);
			List<Contactor> res0 = q.list();
			for(int i = 0; i < res0.size(); i++)
			{
				sessionFactory.getCurrentSession().delete(res0.get(i));
			}	
			 //删除Groups表和Group_Contactor表
			String hql1="select * from Groups where userID=:userid ";
			Query r = sessionFactory.getCurrentSession().createSQLQuery(hql1)
					.addEntity(Group.class);		
			r.setParameter("userid", userid);
			List<Group> res1 = r.list();
			for(int i = 0; i < res1.size(); i++)
			{
				Group_Contactor group_contactor = (Group_Contactor)sessionFactory.getCurrentSession().get(Group_Contactor.class,res1.get(i).getId());
				if(group_contactor != null)
					sessionFactory.getCurrentSession().delete(group_contactor);
				sessionFactory.getCurrentSession().delete(res1.get(i));
			}	
			
			
			 //删除Users表
			User user=(User)sessionFactory.getCurrentSession().get(User.class,userid);
			
			sessionFactory.getCurrentSession().delete(user);
			return true;
		}catch(Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
		
	}

}
