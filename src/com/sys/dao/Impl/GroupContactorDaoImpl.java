package com.sys.dao.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.sys.dao.Interface.IGroupContactorDao;
import com.sys.model.Group_Contactor;

public class GroupContactorDaoImpl implements IGroupContactorDao {

	@Resource
	private SessionFactory sessionFactory;
	
	public void delete(Group_Contactor groupContactor) {
		sessionFactory.getCurrentSession().delete(groupContactor);
	}

	@SuppressWarnings("unchecked")
	public List<Group_Contactor> getGroup_ContactorByContactorID(int contactorID) {
		String hql = "select * from Group_Contactors where contactorId=:cid";
		Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Group_Contactor.class);
		q.setParameter("cid", contactorID);
		return  q.list();
	}

	public Group_Contactor getGroup_ContactorById(int groupContactorId) {
		return (Group_Contactor) sessionFactory.getCurrentSession().get(
				Group_Contactor.class, groupContactorId);
	}

	public void update(Group_Contactor groupContactor) {
		sessionFactory.getCurrentSession().update(groupContactor);
		
	}

}
