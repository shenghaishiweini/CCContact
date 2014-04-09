package com.sys.dao.Interface;

import com.sys.model.Group;

public interface IGroupDao {
	/**
	 * 
	 * @param group
	 */
	public void add(Group group);
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
}
