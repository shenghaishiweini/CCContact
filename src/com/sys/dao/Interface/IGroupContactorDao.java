package com.sys.dao.Interface;

import java.util.List;

import com.sys.model.Group_Contactor;

public interface IGroupContactorDao {

	/**
	 * 
	 * @param groupContactorId
	 * @return
	 */
	public Group_Contactor getGroup_ContactorById(int groupContactorId);
	
	/**
	 * 
	 * @param groupContactorId
	 */
	public void delete(Group_Contactor groupContactor);
	
	/**
	 * 
	 * @param groupContactor
	 */
	public void update(Group_Contactor groupContactor);
	
	/**
	 * 
	 * @param contactorID
	 * @return
	 */
	public List<Group_Contactor> getGroup_ContactorByContactorID(int contactorID);
	

}
