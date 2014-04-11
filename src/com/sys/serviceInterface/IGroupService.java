package com.sys.serviceInterface;

import java.util.List;

import com.sys.model.Contactor;
import com.sys.model.Group;


/**
 * @author Gui Junfei
 * 2014.2.7
 */
public interface IGroupService {
	/**

	 * @param group
	 * @return boolean
	 */
	public boolean newGroup(Group group);
	
	/**

	 * @param int fromGroupID
	 * @param int toGroupID
	 * @param Contactor item 
	 * @return boolean
	 */
	public boolean moveGroupContactorItem(int fromGroupID,int toGroupID,Contactor item);

	/**
	 * @param int GroupID,int ContactorID
	 * @return boolean
	 */
	public boolean removeGroupContactorItem(int GroupID,int ContactorID);
	
	/**
	 * @param contactorId 
	 * @return
	 * add by Fu Yu ,2014/2/22
	 */
	public boolean removeGroupContactorItemOfContactor(int contactorId);
	
	/**
	 * @param groupID
	 * @return boolean
	 */
	public boolean deleteGroup(int groupID); 
	
	/**
	 * @param group
	 * @return boolean
	 */
	public boolean alterGroup(Group group);
	
	/**
	 * @param groupID
	 * @return  Group
	 */
	public Group getGroupByGroupId(int groupID);
	
	/**
	 * @param groupName
	 * @return
	 * add by Fu Yu, 2014/2/22
	 */
	public Group getGroupByGroupName(String groupName);
	
	/**
	 * @param userid
	 * @return List<Group>
	 */
	public List<Group> getAllGroupByUserId(int userid);
	
	/**
	 * @param contactorId
	 * @return List<Group>
	 */
	public List<Group> getAllGroupsByContactorId(int contactorId);
	
	/**
	 * @param groupID
	 * @return  Set<Contactor> 
	 */
	public List<Contactor> getGroupContactors(int groupID);
}
