package com.sys.dao.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.sys.dao.Interface.IGroupDao;
import com.sys.model.Contactor;
import com.sys.model.Group;
import com.sys.model.Group_Contactor;

public class GroupDaoImpl implements IGroupDao {

	@Resource
	private SessionFactory sessionFactory;
	
	
	public void add(Group group) {
		sessionFactory.getCurrentSession().persist(group);
	}

	public void delete(Group group) {
		sessionFactory.getCurrentSession().delete(group);

	}

	@SuppressWarnings("unchecked")
	public void delete(int groupId) {
		Group group = (Group) sessionFactory.getCurrentSession().get(Group.class, groupId);
		sessionFactory.getCurrentSession().delete(group);
		List<Group_Contactor> list = sessionFactory.getCurrentSession()
				.createSQLQuery(
						"select * from group_contactors where groupid='"
								+ groupId + "'").addEntity(
						Group_Contactor.class).list();
		
		String hql = "select * from Groups where groupName='default' and userId=:uid";
		Query q = sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Group.class); 		
		q.setParameter("uid", group.getOwner().getId());	
		Group defaultGroup = (Group) q.list().get(0);
		
		for (int i = 0; i < list.size(); i++) {
			sessionFactory.getCurrentSession().delete(list.get(i));
		}
		
		for (int i = 0; i < list.size(); i++) {
			List<Group_Contactor> temp = sessionFactory.getCurrentSession()
			.createSQLQuery("select * from group_contactors where contactorid='"
							+ list.get(i).getContactorId() + "'").addEntity(
					Group_Contactor.class).list();
			if(temp.size() == 0){
				Group_Contactor gc = list.get(i);
				gc.setGroupId(defaultGroup.getId());
				sessionFactory.getCurrentSession().merge(gc);
				int memberNum = defaultGroup.getMemberNum();
				defaultGroup.setMemberNum(++memberNum);
			}
		}
		sessionFactory.getCurrentSession().merge(defaultGroup);

	}

	public Group find(int groupId) {
		return (Group) sessionFactory.getCurrentSession().get(
				Group.class, groupId);
	}

	public void update(Group group) {
		try {
		sessionFactory.getCurrentSession().update(group);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public Group findDefaultGroup(int userId) {
		
		String hql = "select * from Groups where userID=:userid and groupName='default'";
		try{
		Query q = sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Group.class);
		q.setParameter("userid", userId);
		
		@SuppressWarnings("unchecked")
		List<Group> res = q.list();
		return res==null?null:res.get(0);
		}catch(Exception e)
		{
			System.err.println(e);
			return null;
		}
	}

	public void add(Group_Contactor group_contactor) {
		
		try {
			sessionFactory.getCurrentSession().persist(group_contactor);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Integer> findContactorId(int groupId) {
		
		String hql = "select contactorid from Group_Contactors where groupId=:gid";
		Query q = sessionFactory.getCurrentSession().createSQLQuery(hql); 		
		q.setParameter("gid", groupId);		
		List<Integer> contactids = q.list(); 		
		return contactids;
	}

	@SuppressWarnings("unchecked")
	public void moveGroupContactorItem(int fromGroupID,int toGroupID,Contactor item) throws Exception {
		
		String hql = "select * from Group_Contactors where groupId=:gid and contactorId=:cid";
		Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Group_Contactor.class);
		q.setParameter("gid", fromGroupID);
		q.setParameter("cid", item.getId());
		List<Group_Contactor> group_conContactors = q.list();
		if (group_conContactors.size() <= 0) {
			throw new Exception();
		}
		Group_Contactor group_conContactor=group_conContactors.get(0);
		group_conContactor.setGroupId(toGroupID);	
		sessionFactory.getCurrentSession().update(group_conContactor);
	}

	@SuppressWarnings("unchecked")
	public List<Group_Contactor> getGroup_ContactorByContactorID(int contactorID) {
		String hql = "select * from Group_Contactors where contactorId=:cid";
		Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Group_Contactor.class);
		q.setParameter("cid", contactorID);
		return  q.list();
	
	}

	@SuppressWarnings("unchecked")
	public List<Group> getByUserIdGroupName(int userId, String groupName) {
		String hql2 = "select * from Groups where userId=:uid and groupName=:gname";
		Query qq = sessionFactory.getCurrentSession().createSQLQuery(hql2)
				.addEntity(Group.class);
		qq.setParameter("uid", userId);
		qq.setParameter("gname", groupName);

		return qq.list();
		
	}

}
