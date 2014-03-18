package com.sys.dao.Impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.sys.dao.Interface.CommonMethodInterface;

public class CommonMethodImpl<T> implements CommonMethodInterface<T>{
	@Resource
	private SessionFactory sessionFactory;
	
	@Transactional
	public boolean saveObject(T obj) {
		
		try {
			sessionFactory.getCurrentSession().persist(obj);
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			sessionFactory.getCurrentSession().getTransaction().rollback();
			return false;
		}
	}
	
	@Transactional
	public boolean updateObject(T obj) {
		try {
			Session session = sessionFactory.getCurrentSession();
			if (obj != null) {
				session.update(obj);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
			sessionFactory.getCurrentSession().getTransaction().rollback();
			return false;
		}
	}
	
	
	

	
	
}
