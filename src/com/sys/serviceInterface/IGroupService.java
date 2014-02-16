package com.sys.serviceInterface;

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
	 * @param int GroupID,Contactor item
	 * @return boolean
	 */
	public boolean addGroupContactorItem(int GroupID,Contactor item);

	/**
	 *  ɾ�������Ա
	 * @param int GroupID,int ContactorID
	 * @return boolean
	 */
	public boolean removeGroupContactorItem(int GroupID,int ContactorID);
	
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
	 * ����ĳһ�û����еķ���
	 * @param userid
	 * @return List<Group>
	 */
	public List<Group> getAllGroupByUserId(int userid);
	
	/**
	 * �õ�ĳһ������������ϵ�˵���Ϣ
	 * @param groupID
	 * @return  Set<Contactor> 
	 */
	public Set<Contactor> getGroupContactors(int groupID);
}
