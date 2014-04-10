package com.sys.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.sys.dao.Interface.IContactorDao;
import com.sys.model.Contactor;
import com.sys.model.Group;
import com.sys.model.Group_Contactor;
import com.sys.model.User;
import com.sys.service.Interface.IContactorService;

/**
 * 联系人接口实现类
 * 
 * @author Gui Junfei 2014.2.7
 */
@Transactional
public class ContactorService implements IContactorService {

	@Resource
	private SessionFactory sessionFactory;
	
	@Resource
	private IContactorDao contactorDao;

	public boolean addContactorToNewGroup(Contactor contactor, Group group) {

		try {
				Group_Contactor group_contactor = new Group_Contactor();
				group_contactor.setGroupId(group.getId());
				group_contactor.setContactorId(contactor.getId());
				sessionFactory.getCurrentSession().persist(group_contactor);
				
				int memberNumber = group.getMemberNum();
				group.setMemberNum(memberNumber+1);
				sessionFactory.getCurrentSession().update(group);
				
				return true;

		} catch (Exception e) {
			System.out.print(e.getMessage());
			sessionFactory.getCurrentSession().getTransaction().rollback();
			return false;
		}

	}

	public boolean addContactorDefault(Contactor contactor) {

		try {
			sessionFactory.getCurrentSession().persist(contactor);
			// throw new Exception();
			User owner = contactor.getOwner();
			
//			String hql = "select id from Groups where userID=:userid and groupName='default'";
//			Query q = sessionFactory.getCurrentSession().createSQLQuery(hql);
//			q.setParameter("userid", owner.getId());// owner.getId()
//			List<Integer> res = q.list();
			
			String hql = "select * from Groups where userID=:userid and groupName='default'";
			Query q = sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Group.class);
			q.setParameter("userid", owner.getId());// owner.getId()
			List<Group> res = q.list();
			
			if (res.size() <= 0) {
				return false;
			}
			Group_Contactor group_contactor = new Group_Contactor();
			//group_contactor.setGroupId(res.get(0));// default
			group_contactor.setGroupId(res.get(0).getId());
			group_contactor.setContactorId(contactor.getId());

			sessionFactory.getCurrentSession().persist(group_contactor);
			
			Group group = res.get(0);
			int memberNumber = group.getMemberNum();
			group.setMemberNum(memberNumber+1);
			sessionFactory.getCurrentSession().update(group);
			
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			sessionFactory.getCurrentSession().getTransaction().rollback();
			return false;
		}

	}

	public boolean alterContactor(Contactor contactor) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Contactor temp = contactor;// (Group) session.get(Group.class,
			// group.getId());
			if (temp != null) {
				// temp = copyNewEntiy(temp, group);
				session.update(temp);
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

	@SuppressWarnings("unchecked")
	public boolean deleteContactor(int contactorID) {
		try {
			sessionFactory.getCurrentSession().delete(
					sessionFactory.getCurrentSession().get(Contactor.class,
							contactorID));
			List<Group_Contactor> list = sessionFactory.getCurrentSession()
					.createSQLQuery(
							"select * from Group_Contactors where contactorid='"
									+ contactorID + "'").addEntity(
							Group_Contactor.class).list();
			Group group = null;
			int memberNumber = 0;
			for (int i = 0; i < list.size(); i++) {
				sessionFactory.getCurrentSession().delete(list.get(i));
				group = (Group) sessionFactory.getCurrentSession().get(Group.class, list.get(i).getGroupId());
				memberNumber = group.getMemberNum();
				group.setMemberNum(memberNumber-1);
				sessionFactory.getCurrentSession().update(group);
			}

			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			sessionFactory.getCurrentSession().getTransaction().rollback();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Contactor> findContactorsByCellphoneNumber(
			String contactorCellphoneNumber, int userid) {
		try {
			String hql = "select * from Contactors where cellphoneNumber like :cnumber and userID=:userid";
			Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
					.addEntity(Contactor.class);
			q.setParameter("cnumber", "%" + contactorCellphoneNumber + "%",
					Hibernate.STRING);
			q.setParameter("userid", userid);
			List<Contactor> res = q.list();
			return res;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Contactor> findContactorByGroupId(int GroupId, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Contactor> findContactorByGroupName(String GroupName, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Contactor findContactorById(int contactorID) {
		try {
			Contactor temp = (Contactor) sessionFactory.getCurrentSession()
					.get(Contactor.class, contactorID);
			return temp;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Contactor> findContactorByName(String contactorName, int userid) {
		try {
			String hql = "select * from Contactors where name like :cname and userID=:userid";
			Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
					.addEntity(Contactor.class);
			q
					.setParameter("cname", "%" + contactorName + "%",
							Hibernate.STRING);
			q.setParameter("userid", userid);
			List<Contactor> res = q.list();
			return res;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Contactor> findContactorByTelephoneNumber(
			String contactorTelephoneNumber, int userid) {
		try {
			String hql = "select * from Contactors where telephoneNumber like :tnumber and userID=:userid";
			Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
					.addEntity(Contactor.class);
			q.setParameter("tnumber", "%" + contactorTelephoneNumber + "%",
					Hibernate.STRING);
			q.setParameter("userid", userid);
			List<Contactor> res = q.list();
			return res;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Contactor> findAllContactorsByUserId(int userId) {
		return sessionFactory.getCurrentSession().createSQLQuery(
				"select * from Contactors where userID='" + userId + "'")
				.addEntity(Contactor.class).list();
	}

	public List<Contactor> findSearchContactors(String searchStr, int userid) {
		try {
			String hql = "select * from Contactors "
					+ "where ( telephoneNumber like :str or name like :str or cellphoneNumber like :str or QQ like :str )"
					+ "and userID=:userid";
			Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
					.addEntity(Contactor.class);
			q.setParameter("str", "%" + searchStr + "%", Hibernate.STRING);
			q.setParameter("userid", userid);
			List<Contactor> res = q.list();
			return res;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean addContactor(Contactor contactor) {
		try {
			sessionFactory.getCurrentSession().persist(contactor);
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			sessionFactory.getCurrentSession().getTransaction().rollback();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public Contactor findContactorByCellphoneNumber(
			String contactorCellphoneNumber, int userid) {
		try {
			String hql = "select * from Contactors where cellphoneNumber =:cnumber and userID=:userid";
			Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
					.addEntity(Contactor.class);
			q.setParameter("cnumber", contactorCellphoneNumber,
					Hibernate.STRING);
			q.setParameter("userid", userid);
			List<Contactor> res =(List<Contactor>) q.list();
			if(res!=null&&res.size()==1)
				return res.get(0);
			return null;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}

	}

	public Contactor findContactorByCellphpneNumber(
			String contactorTelephoneNumber) {
		
		if(contactorTelephoneNumber==null||contactorTelephoneNumber.equals(""))
			return null;
		
		Contactor contactor = contactorDao.findByCellphoneNumber(contactorTelephoneNumber);
		
		return contactor==null?null:contactor;
	}


}
