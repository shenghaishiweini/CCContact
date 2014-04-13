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
import com.sys.model.Group;
import com.sys.model.Group_Contactor;
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

	@SuppressWarnings("unchecked")
	public void deleteById(int contactorId) {
		Contactor contactor=this.findById(contactorId);
		if(contactor==null) return;
		
		sessionFactory.getCurrentSession().delete(contactor);
		
		String sql="select * from Group_Contactors where contactorid='"+ contactorId + "'";
		List<Group_Contactor> list = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Group_Contactor.class).list();
		Group group = null;
		int memberNumber = 0;
		for (int i = 0; i < list.size(); i++) {
			sessionFactory.getCurrentSession().delete(list.get(i));
			group = (Group) sessionFactory.getCurrentSession().get(Group.class, list.get(i).getGroupId());
			memberNumber = group.getMemberNum();
			group.setMemberNum(memberNumber-1);
			sessionFactory.getCurrentSession().update(group);
		}
		
	}

	public void update(Contactor contactor) {
	
			Session session = sessionFactory.getCurrentSession();
			Contactor temp = contactor;// (Group) session.get(Group.class,
			if (temp != null) 
				session.update(temp);

	}

	public Contactor findById(int contactorId) {
		Contactor temp = (Contactor) sessionFactory.getCurrentSession()
				.get(Contactor.class, contactorId);
		return temp==null?null:temp;
	}

	public Contactor findByCellphoneNumber(String contactorTelephoneNumber) {
			String hql = "select * from Contactors where cellphoneNumber =:cnumber";

				Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
						.addEntity(Contactor.class);
				q.setParameter("cnumber", contactorTelephoneNumber,
						Hibernate.STRING);
				@SuppressWarnings("unchecked")
				List<Contactor> res =(List<Contactor>) q.list();
				return res==null?null:res.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Contactor> findAllContactorsByUserId(int userId) {
		
		return sessionFactory.getCurrentSession().createSQLQuery(
				"select * from Contactors where userID='" + userId + "'")
				.addEntity(Contactor.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Contactor> findByCellphoneNumber(
			String contactorTelephoneNumber, int userId) {

			String hql = "select * from Contactors where cellphoneNumber like :cnumber and userID=:userid";
			Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
					.addEntity(Contactor.class);
			q.setParameter("cnumber", "%" + contactorTelephoneNumber + "%",
					Hibernate.STRING);
			q.setParameter("userid", userId);
			List<Contactor> res = q.list();
			return res==null?null:res;
	}

	@SuppressWarnings("unchecked")
	public List<Contactor> findContactorByName(String name,int userid) {
		String hql = "select * from Contactors where name like :cname and userID=:userid";
		Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Contactor.class);
		q.setParameter("cname", "%" + name + "%",Hibernate.STRING);
		q.setParameter("userid", userid);
		List<Contactor> res = q.list();
		return res==null?null:res;
	}

}
