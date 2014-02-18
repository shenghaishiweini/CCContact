package com.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.sys.model.Contactor;
import com.sys.model.Group;
import com.sys.model.Group_Contactor;
import com.sys.model.User;
import com.sys.serviceInterface.IGroupService;

/**
 * 组接口实现类
 * 
 * @author Gui Junfei 2014.2.7
 */
@Transactional
public class GroupService implements IGroupService {

	@Resource
	private SessionFactory sessionFactory;

	public boolean newGroup(Group group) {
		try {
			sessionFactory.getCurrentSession().persist(group);
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Contactor> getGroupContactors(int groupID) {
		Group group = (Group) sessionFactory.getCurrentSession().get(
				Group.class, groupID);
		String hql = "select id from Group_Contactor where groupId=:gid";
		Query q = sessionFactory.getCurrentSession().createSQLQuery(hql);
		q.setParameter("gid", group.getId());
		List<Integer> contactids = q.list();
		if (contactids.size() <= 0) {
			return null;
		}
		List<Contactor> res = new ArrayList<Contactor>();
		for (int i = 0; i < contactids.size(); i++) {
			Contactor temp = (Contactor) sessionFactory.getCurrentSession()
					.get(Contactor.class, contactids.get(i));
			res.add(temp);
		}
		return res;
	}

	public boolean alterGroup(Group group) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Group temp = group;// (Group) session.get(Group.class,
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
			return false;
		}
	}

	private Group copyNewEntiy(Group temp, Group group) {
		// temp.setContacts(group.getContacts());
		temp.setCreateTime(group.getCreateTime());
		temp.setGroupName(group.getGroupName());
		// temp.setMemberNum(group.getContacts().size());
		temp.setOwner(group.getOwner());
		return temp;
	}

	public boolean deleteGroup(int groupID) {
		try {
			Group group = (Group) sessionFactory.getCurrentSession().get(
					Group.class, groupID);
			sessionFactory.getCurrentSession().delete(group);
			List<Group_Contactor> list = sessionFactory.getCurrentSession()
					.createSQLQuery(
							"select * from group_contactor where groupid='"
									+ groupID + "'").addEntity(
							Group_Contactor.class).list();
			for (int i = 0; i < list.size(); i++) {
				// sessionFactory.getCurrentSession().delete(list.get(i));
				Group_Contactor gc = list.get(i);
				gc.setGroupId(0);
				sessionFactory.getCurrentSession().merge(gc);
			}

			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Group> getAllGroupByUserId(int userid) {

		return sessionFactory.getCurrentSession().createSQLQuery(
				"select * from groups where userID='" + userid + "'")
				.addEntity(Group.class).list();

	}

	public Group getGroupByGroupId(int groupID) {
		try {
			Group temp = (Group) sessionFactory.getCurrentSession().get(
					Group.class, groupID);
			return temp;
		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
			return null;
		}
	}

	public boolean addGroupContactorItem(int GroupID, Contactor item) {

		// try {
		// Group temp = (Group) sessionFactory.getCurrentSession().get(
		// Group.class, GroupID);
		// Set gpset = temp.getContacts();
		// gpset.add(item);
		// temp.setContacts(gpset);
		// temp.setMemberNum(gpset.size());
		//			
		// Set cset=item.getGroups();
		// cset.add(temp);
		// item.setGroups(cset);
		// // item.setGroup(cset);
		// // sessionFactory.getCurrentSession().save(item);
		// sessionFactory.getCurrentSession().saveOrUpdate(temp);
		// return true;
		// } catch (Exception e) {
		// System.out.print(e.getLocalizedMessage());
		// return false;
		// }
		return false;

	}

	public boolean removeGroupContactorItem(int GroupID, int ContactorID) {
		// try {
		// Group group = (Group) sessionFactory.getCurrentSession().get(
		// Group.class, GroupID);
		// if (group == null) {
		// return false;
		// }
		// String hql =
		// "select * from Contactors where id=:cid and groupsID=:gid";
		// Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
		// .addEntity(Contactor.class);
		// q.setParameter("cid", ContactorID);
		// q.setParameter("gid", GroupID);
		// List<Contactor> res = q.list();
		//
		// if (res.size() <= 0) {
		// return false;
		// }
		// Contactor contactor = res.get(0);
		//
		// if (contactor.getGroup().getId() == GroupID) {
		// sessionFactory.getCurrentSession().delete(contactor);
		// Set gpset = group.getContacts();
		// gpset.remove(contactor);
		// group.setMemberNum(gpset.size());
		// sessionFactory.getCurrentSession().saveOrUpdate(group);
		// return true;
		// } else {
		// return false;
		// }
		//
		// } catch (Exception e) {
		// System.out.print(e.getLocalizedMessage());
		// return false;
		// }
		return false;
	}
}
