package com.sys.service.Interface;

import java.util.List;
import java.util.Set;

import com.sys.model.Contactor;
import com.sys.model.Group;


/**
 * �ӿ�,�������û���Ӧ�Ĳ���
 * @author Gui Junfei
 * 2014.2.7
 */
public interface IGroupService {
	/**
	 *  �����û�����
	 * @param group
	 * @return boolean
	 */
	public boolean newGroup(Group group);
	
	/**
	 *  ���������Ա
	 * @param int fromGroupID Դ��ID 
	 * @param int toGroupID Ŀ����ID
	 * @param Contactor item ��ϵ���Ѿ�����
	 * @return boolean
	 */
	public boolean moveGroupContactorItem(int fromGroupID,int toGroupID,Contactor item);

	/**
	 *  ɾ�������Ա
	 * @param int GroupID,int ContactorID
	 * @return boolean
	 */
	public boolean removeGroupContactorItem(int GroupID,int ContactorID);
	
	/**
	 * ɾ��ĳ����ϵ�˶�Ӧ������Group_Contactor��Ŀ
	 * @param contactorId ��ϵ��ID
	 * @return
	 * add by Fu Yu ,2014/2/22
	 */
	public boolean removeGroupContactorItemOfContactor(int contactorId);
	
	/**
	 * ɾ���û�����
	 * @param groupID
	 * @return boolean
	 */
	public boolean deleteGroup(int groupID); 
	
	/**
	 * �޸��û����飬�������޸ĺ�ķ���ʵ��
	 * @param group
	 * @return boolean
	 */
	public boolean alterGroup(Group group);
	
	/**
	 * ���ݷ���ID���ҷ���
	 * @param groupID
	 * @return  Group
	 */
	public Group getGroupByGroupId(int groupID);
	
	/**
	 * ���ݷ�������������ҷ��飬��Ϊÿ���û��ķ������������ظ���
	 * @param groupName
	 * @return
	 * add by Fu Yu, 2014/2/22
	 */
	public Group getGroupByGroupName(String groupName);
	
	/**
	 * ����ĳһ�û����еķ���
	 * @param userid
	 * @return List<Group>
	 */
	public List<Group> getAllGroupByUserId(int userid);
	
	/**
	 * ������ϵ��ID�ҵ���Ӧ�����з���
	 * @param contactorId ��ϵ��ID
	 * @return List<Group>
	 */
	public List<Group> getAllGroupsByContactorId(int contactorId);
	
	/**
	 * �õ�ĳһ������������ϵ�˵���Ϣ
	 * @param groupID
	 * @return  Set<Contactor> 
	 */
	public List<Contactor> getGroupContactors(int groupID);
}
