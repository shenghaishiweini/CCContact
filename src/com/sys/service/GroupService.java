package com.sys.service;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.sys.model.Contactor;
import com.sys.model.Group;
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
	public Set<Contactor> getGroupContactors(int groupID) {

		Group gp = (Group) sessionFactory.getCurrentSession().get(Group.class,
				groupID);
		if (gp == null)
			return null;
		else
			return gp.getContacts();

	}

	public boolean alterGroup(Group group) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Group temp = (Group) session.get(Group.class, group.getId());
			if (temp != null) {
				temp = copyNewEntiy(temp, group);
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
		temp.setContacts(group.getContacts());
		temp.setCreateTime(group.getCreateTime());
		temp.setGroupName(group.getGroupName());
		temp.setMemberNum(group.getContacts().size());
		temp.setOwner(group.getOwner());
		return temp;
	}

	public boolean deleteGroup(int groupID) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Group temp = (Group) session.get(Group.class, groupID);
			if (temp != null) {
				session.delete(temp);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

	public List<Group> getAllGroupByUserId(int userid) {
		try {
			User temp = (User) sessionFactory.getCurrentSession().get(
					User.class, userid);
			return (List<Group>) temp.getGroups();
		} catch (Exception e) {
			return null;
		}

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

		try {
			Group temp = (Group) sessionFactory.getCurrentSession().get(
					Group.class, GroupID);
			Set gpset = temp.getContacts();
			gpset.add(item);
			temp.setContacts(gpset);
			temp.setMemberNum(gpset.size());
			item.setGroup(temp);
			sessionFactory.getCurrentSession().save(item);
			sessionFactory.getCurrentSession().saveOrUpdate(temp);
			return true;
		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
			return false;
		}

	}

	public boolean removeGroupContactorItem(int GroupID, int ContactorID) {
		try {
			Group group = (Group) sessionFactory.getCurrentSession().get(
					Group.class, GroupID);
			if (group == null) {
				return false;
			}
			String hql = "select * from Contactors where id=:cid and groupsID=:gid";
			Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
					.addEntity(Contactor.class);
			q.setParameter("cid", ContactorID);
			q.setParameter("gid", GroupID);
			List<Contactor> res = q.list();

			if (res.size() <= 0) {
				return false;
			}
			Contactor contactor = res.get(0);

			if (contactor.getGroup().getId() == GroupID) {
				sessionFactory.getCurrentSession().delete(contactor);
				Set gpset = group.getContacts();
				gpset.remove(contactor);
				group.setMemberNum(gpset.size());
				sessionFactory.getCurrentSession().saveOrUpdate(group);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
			return false;
		}
	}
}
