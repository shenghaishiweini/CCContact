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

	public boolean newGroup(Group group) {// test ok 2014.2.19
		try {
			sessionFactory.getCurrentSession().persist(group);
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			sessionFactory.getCurrentSession().getTransaction().rollback();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Contactor> getGroupContactors(int groupID) {// test ok 2014.2.19
		Group group = (Group) sessionFactory.getCurrentSession().get(
				Group.class, groupID);
		String hql = "select id from Group_Contactors where groupId=:gid";
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

	public boolean alterGroup(Group group) {// test ok 2014.2.19
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
			sessionFactory.getCurrentSession().getTransaction().rollback();
			return false;
		}
	}

	@SuppressWarnings("unused")
	private Group copyNewEntiy(Group temp, Group group) {
		// temp.setContacts(group.getContacts());
		temp.setCreateTime(group.getCreateTime());
		temp.setGroupName(group.getGroupName());
		// temp.setMemberNum(group.getContacts().size());
		temp.setOwner(group.getOwner());
		return temp;
	}

	@SuppressWarnings("unchecked")
	public boolean deleteGroup(int groupID) {
		try {
			Group group = (Group) sessionFactory.getCurrentSession().get(
					Group.class, groupID);
			sessionFactory.getCurrentSession().delete(group);
			List<Group_Contactor> list = sessionFactory.getCurrentSession()
					.createSQLQuery(
							"select * from group_contactors where groupid='"
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
			sessionFactory.getCurrentSession().getTransaction().rollback();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Group> getAllGroupByUserId(int userid) {// test ok
		String hql = "select * from Groups where userId=:uid";
		Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Group.class);
		q.setParameter("uid", userid);
		List<Group> groups = q.list();
		if (groups.size() <= 0) {
			return null;
		}
		return groups;

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

	public boolean moveGroupContactorItem(int fromGroupID,int toGroupID,Contactor item){//test ok

		try {
			String hql = "select * from Group_Contactors where groupId=:gid and contactorId=:cid";
			Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
					.addEntity(Group_Contactor.class);
			q.setParameter("gid", fromGroupID);
			q.setParameter("cid", item.getId());
			List<Group_Contactor> group_conContactors = q.list();
			if (group_conContactors.size() <= 0) {
				return false;
			}
			Group_Contactor group_conContactor=group_conContactors.get(0);
			group_conContactor.setGroupId(toGroupID);	
			sessionFactory.getCurrentSession().update(group_conContactor);
			return true;
		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
			sessionFactory.getCurrentSession().getTransaction().rollback();
			return false;
		}

	}

	//存在一个联系人在多个组中，则直接从该组中移除，否则移到默认分组
	public boolean removeGroupContactorItem(int GroupID, int ContactorID) {
		
		try {
			String hql = "select * from Group_Contactors where contactorId=:cid";
			Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
					.addEntity(Group_Contactor.class);
			q.setParameter("cid", ContactorID);
			List<Group_Contactor> group_conContactors = q.list();
			if (group_conContactors.size() <= 0) {
				return false;
			}
			if (group_conContactors.size() > 1) {//存在于多个分组
				int i;
				for(i=0;i<group_conContactors.size();i++)
				{
					Group_Contactor temp=group_conContactors.get(i);
					if(temp.getGroupId()==GroupID)
					{
						sessionFactory.getCurrentSession().delete(temp);
						return true;
					}
				}
				if(i==group_conContactors.size())//联系人不在指定的分组中
				{
					return false;
				}
				
			}
			//联系人只存在一个分组中
			Group_Contactor group_conContactor=group_conContactors.get(0);
			
			Group group=(Group) sessionFactory.getCurrentSession().get(Group.class,GroupID);
			
			String hql2 = "select * from Groups where userId=:uid and groupName=:gname";
			Query qq = sessionFactory.getCurrentSession().createSQLQuery(hql2)
					.addEntity(Group.class);
			qq.setParameter("uid", group.getOwner().getId());
			qq.setParameter("gname", "default");
			List<Group> groups = qq.list();
			if (groups.size() <= 0) {
				return false;
			}
			group_conContactor.setGroupId(groups.get(0).getId());//找到默认分组，并将移到默认分组	
			sessionFactory.getCurrentSession().update(group_conContactor);
			
			return true;
		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
			sessionFactory.getCurrentSession().getTransaction().rollback();
			return false;
		}
	}

	public List<Group> getAllGroupsByContactorId(int contactorId) {
		
		return null;
	}
}
