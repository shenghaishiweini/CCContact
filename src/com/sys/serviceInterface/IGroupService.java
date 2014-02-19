package com.sys.serviceInterface;

import java.util.List;
import java.util.Set;

import com.sys.model.Contactor;
import com.sys.model.Group;


/**
 * 接口,声明对用户相应的操作
 * @author Gui Junfei
 * 2014.2.7
 */
public interface IGroupService {
	/**
	 *  新增用户分组
	 * @param group
	 * @return boolean
	 */
	public boolean newGroup(Group group);
	
	/**
	 *  新增分组成员
	 * @param int fromGroupID 源组ID 
	 * @param int toGroupID 目的组ID
	 * @param Contactor item 联系人已经存在
	 * @return boolean
	 */
	public boolean moveGroupContactorItem(int fromGroupID,int toGroupID,Contactor item);

	/**
	 *  删除分组成员
	 * @param int GroupID,int ContactorID
	 * @return boolean
	 */
	public boolean removeGroupContactorItem(int GroupID,int ContactorID);
	
	/**
	 * 删除用户分组
	 * @param groupID
	 * @return boolean
	 */
	public boolean deleteGroup(int groupID); 
	
	/**
	 * 修改用户分组，参数是修改后的分组实例
	 * @param group
	 * @return boolean
	 */
	public boolean alterGroup(Group group);
	
	/**
	 * 根据分组ID查找分组
	 * @param groupID
	 * @return  Group
	 */
	public Group getGroupByGroupId(int groupID);
	
	/**
	 * 查找某一用户所有的分组
	 * @param userid
	 * @return List<Group>
	 */
	public List<Group> getAllGroupByUserId(int userid);
	
	/**
	 * 得到某一分组中所有联系人的信息
	 * @param groupID
	 * @return  Set<Contactor> 
	 */
	public List<Contactor> getGroupContactors(int groupID);
}
