package com.sys.dao.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.sys.dao.Interface.IContactorDao;
import com.sys.model.Contactor;
/**
 * 
 * @author gjf
 *
 */
@Transactional
public class ContactorDaoImpl implements IContactorDao{

	@Resource
	private SessionFactory sessionFactory;
	
	public void add(Contactor contactor) {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(int contactorId) {
		// TODO Auto-generated method stub
		
	}

	public void update(Contactor contactor) {
		// TODO Auto-generated method stub
		
	}

	public Contactor findById(int contactorId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Contactor findByCellphoneNumber(String contactorTelephoneNumber) {
			String hql = "select * from Contactors where cellphoneNumber =:cnumber";
			try {
				Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
						.addEntity(Contactor.class);
				q.setParameter("cnumber", contactorTelephoneNumber,
						Hibernate.STRING);
				List<Contactor> res =(List<Contactor>) q.list();
				return res==null?null:res.get(0);
			} catch (Exception e) {
				System.err.println(e);
				return null;
			}
	}

}
