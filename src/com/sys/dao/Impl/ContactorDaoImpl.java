package com.sys.dao.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
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
	
	public boolean add(Contactor contactor) {
		try {
			sessionFactory.getCurrentSession().persist(contactor);
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return false;
	}

	public void deleteById(int contactorId) {
		// TODO Auto-generated method stub
		
	}

	public void update(Contactor contactor) {
	
			Session session = sessionFactory.getCurrentSession();
			Contactor temp = contactor;// (Group) session.get(Group.class,
			if (temp != null) 
				session.update(temp);

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
				@SuppressWarnings("unchecked")
				List<Contactor> res =(List<Contactor>) q.list();
				return res==null?null:res.get(0);
			} catch (Exception e) {
				System.err.println(e);
				return null;
			}
	}

	@SuppressWarnings("unchecked")
	public List<Contactor> findAllContactorsByUserId(int userId) {
		
		return sessionFactory.getCurrentSession().createSQLQuery(
				"select * from Contactors where userID='" + userId + "'")
				.addEntity(Contactor.class).list();
	}

}
