package com.sys.dao.Interface;

import com.sys.model.Group;
import com.sys.model.Group_Contactor;

public interface IGroupDao {
	/**
	 * 
	 * @param group
	 */
	public void add(Group group);
	
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
}
