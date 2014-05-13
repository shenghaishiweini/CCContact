package com.sys.dao.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.sys.dao.Interface.IGroupDao;
import com.sys.model.Group;
import com.sys.model.Group_Contactor;

public class GroupDaoImpl implements IGroupDao {

	@Resource
	private SessionFactory sessionFactory;
	
	
	public void add(Group group) {
		// TODO Auto-generated method stub

	}

	public void delete(Group group) {
		// TODO Auto-generated method stub

	}

	public void delete(int groupId) {
		// TODO Auto-generated method stub

	}

	public Group find(int groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Group group) {
		try {
		sessionFactory.getCurrentSession().update(group);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public Group findDefaultGroup(int userId) {
		
		String hql = "select * from Groups where userID=:userid and groupName='default'";
		try{
		Query q = sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Group.class);
		q.setParameter("userid", userId);
		
		@SuppressWarnings("unchecked")
		List<Group> res = q.list();
		return res==null?null:res.get(0);
		}catch(Exception e)
		{
			System.err.println(e);
			return null;
		}
	}

	public void add(Group_Contactor group_contactor) {
		
		try {
			sessionFactory.getCurrentSession().persist(group_contactor);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

}
