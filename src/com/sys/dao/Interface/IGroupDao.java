package com.sys.dao.Interface;

import java.util.List;

import com.sys.model.Contactor;
import com.sys.model.Group;
import com.sys.model.Group_Contactor;

public interface IGroupDao {
	/**
	 * 
	 * @param group
	 */
	public void add(Group group);
	
	/**
	 * 
	 * @param group_contactor
	 */
	public void add(Group_Contactor group_contactor);
	
	
	/**
	 * 
	 * @param group
	 */
	public void update(Group group);
	/**
	 * 
	 * @param group
	 */
	public void delete(Group group);
	
	/**
	 * 
	 * @param groupId
	 */
	public void delete(int groupId);
	/**
	 * 
	 * @param groupId
	 */
	public Group find(int groupId);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public Group findDefaultGroup(int userId);
	
	/**
	 * 
	 * @param groupId
	 * @return
	 */
	public List<Integer> findContactorId(int groupId);
	
	/**
	 * 
	 * @param fromGroupID
	 * @param toGroupID
	 * @param item
	 * @throws Exception
	 */
	public void moveGroupContactorItem(int fromGroupID,int toGroupID,Contactor item)throws Exception ;


	/**
	 * 
	 * @param userId
	 * @param groupName
	 * @return
	 */
	public List<Group> getByUserIdGroupName(int userId,String groupName);
	
}
