package com.sys.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.sys.dao.Interface.IContactorDao;
import com.sys.dao.Interface.IGroupContactorDao;
import com.sys.dao.Interface.IGroupDao;
import com.sys.model.Contactor;
import com.sys.model.Group;
import com.sys.model.Group_Contactor;
import com.sys.service.Interface.IGroupService;
import com.sys.utils.Constants;

/**
 * 分组
 * 2014.2.7
 * @author Gui Junfei 
 */
@Transactional
public class GroupService implements IGroupService {

	@Resource
	private SessionFactory sessionFactory;
	
	@Resource
	private IGroupDao groupDao;
	
	@Resource
	private IContactorDao contactorDao;
	
	@Resource
	private IGroupContactorDao groupContactorDao;

	public boolean newGroup(Group group) {// test ok 2014.2.19
		try {
			groupDao.add(group);
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

	@Transactional
	public List<Contactor> getGroupContactors(int groupID) {// test ok 2014.2.19
		
		if(groupID<Constants.INVALID_ID)
			return null;
		
		Group group = groupDao.find(groupID);
		if(group==null)
			return null;
		
		List<Integer> contactids = groupDao.findContactorId(group.getId()); 		
		if (contactids.size() <= 0) {
			return null;
		}
		List<Contactor> res = new ArrayList<Contactor>();
		for (int i = 0; i < contactids.size(); i++) {
			Contactor temp = contactorDao.findById(contactids.get(i));
			res.add(temp);
		}
		return res;
	}

	public boolean alterGroup(Group group) {// test ok 2014.2.19
		try {
			if (group != null) {
				groupDao.update(group);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

	public boolean deleteGroup(int groupID) {
		try {
			groupDao.delete(groupID);
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
			return groupDao.find(groupID);
		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
			return null;
		}
	}

	public boolean moveGroupContactorItem(int fromGroupID,int toGroupID,Contactor item){//test ok

		try {
			groupDao.moveGroupContactorItem(fromGroupID, toGroupID, item);
			return true;
		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
			return false;
		}

	}

	public boolean removeGroupContactorItem(int GroupID, int ContactorID) {
		
		try {

			List<Group_Contactor> group_conContactors =groupContactorDao.getGroup_ContactorByContactorID(ContactorID);
			if (group_conContactors.size() <= 0) {
				return false;
			}
			if (group_conContactors.size() > 1) {
				int i;
				for(i=0;i<group_conContactors.size();i++)
				{
					Group_Contactor temp=group_conContactors.get(i);
					if(temp.getGroupId()==GroupID)
					{
						groupContactorDao.delete(temp);
						return true;
					}
				}
				if(i==group_conContactors.size())
				{
					return false;
				}
			}
			Group_Contactor group_conContactor=group_conContactors.get(0);
			
			Group group=groupDao.find(GroupID);
			
			List<Group> groups = groupDao.getByUserIdGroupName(group.getOwner().getId(), "default");
			if (groups.size() <= 0) {
				return false;
			}
			group_conContactor.setGroupId(groups.get(0).getId());
			groupContactorDao.update(group_conContactor);
			return true;
		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
			return false;
		}
	}

	public List<Group> getAllGroupsByContactorId(int contactorId) {
		
		String hql = "select * from Group_Contactors where contactorId='"+contactorId+"'";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Group_Contactor.class);
		List<Group_Contactor> gc = query.list();
		List<Group> groups = new ArrayList<Group>();
		for(int i=0;i<gc.size();i++){
			Group group = (Group) sessionFactory.getCurrentSession().get(Group.class, gc.get(i).getGroupId());
			groups.add(group);
		}
		return groups;
	}

	public Group getGroupByGroupName(String groupName) {		
		try {
			String hql = "select * from Groups where groupName='"+groupName+"'";
			Group temp = (Group) sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Group.class).list().get(0);
			return temp;
		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
			return null;
		}
	}

	public boolean removeGroupContactorItemOfContactor(int contactorId) {
		try {
			String hql = "select * from Group_Contactors where contactorId='"+contactorId+"'";
			List<Group_Contactor> temp = sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Group_Contactor.class).list();
			Group group = null;
			int memberNumber = 0;
			for(int i=0;i<temp.size();i++){
				sessionFactory.getCurrentSession().delete(temp.get(i));
				group = (Group) sessionFactory.getCurrentSession().get(Group.class, temp.get(i).getGroupId());
				memberNumber = group.getMemberNum();
				group.setMemberNum(memberNumber-1);
				sessionFactory.getCurrentSession().update(group);
			}			
			return true;
		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
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
}
