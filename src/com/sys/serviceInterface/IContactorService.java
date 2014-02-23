package com.sys.serviceInterface;

import java.util.List;

import com.sys.model.Contactor;
import com.sys.model.Group;



/**
 * �ӿ�,�������û���Ӧ�Ĳ���
 * @author Gui Junfei
 * 2014.2.7
 */
public interface IContactorService {
	
	/**
	 * ������ϵ�˵�Contactor���У�����������Ӧ��Group_Contactor����Group_Contactor����
	 * @param contactor ��������ϵ�� 
	 * @return boolean
	 * �ɹ�����true��ʧ�ܷ���false
	 * 
	 */
	public boolean addContactorDefault(Contactor contactor);
	
	/**
	 * ������ϵ�˵�Contactor����
	 * @param contactor ��������ϵ��
	 * @return
	 */
	public boolean addContactor(Contactor contactor);
	
	/**
	 * ������ϵ�˵�Contactor���У�����������Ӧ��Group_Contactor����Group_Contactor����
	 * @param contactor ��������ϵ��
	 * @param group ��ϵ�˶�Ӧ�ķ��飬������default����
	 * @return
	 * add by Fu Yu, 2014/2/22
	 */
//	public boolean addContactorToGroup(Contactor contactor,Group group);
	
	/**
	 * �޸���ϵ����Ϣ
	 * @param contactor �޸ĵ���ϵ��
	 * @return boolean
	 * �ɹ�����true��ʧ�ܷ���false
	 */
	public boolean alterContactor(Contactor contactor);
	
	/**
	 * ɾ��Contator�еļ�¼,���һ���ɾ��Group_Contactor���еĶ�Ӧ��¼
	 * @param contactorID ɾ��ֻ�ܰ���ID��ɾ��
	 * @return boolean
	 * �ɹ�����true��ʧ�ܷ���false
	 */
	public boolean deleteContactor(int contactorID);
	
	
	/**
	 * ����ϵ����������
	 * @param contactor ��������ϵ�� 
	 * @param group ���������
	 * @return boolean
	 * �ɹ�����true��ʧ�ܷ���false
	 * 
	 */
	public boolean addContactorToNewGroup(Contactor contactor,Group group);
	
	
	/**
	 * ������ϵ��ID ����
	 * @param contactorID 
	 * @return Contactor 
	 * ��ϵ��ID��Ψһ�ģ�����Ψһ��NULL ��ʾ������ָ��ID���û�
	 */
	public Contactor findContactorById(int contactorID);
	
	/**
	 * ������ϵ�����ֲ���
	 *  �ڲ���ѯ���ƣ�Ӧ�����Ȳ��ĳ�û������з������ϵ�ˣ������û��Ѿ�ָ���˷���
	 * 	Ȼ�����������в��ҡ����Խ��û�ID����SESSION�У���̨ʹ��ʱ��ȡ��
	 *  ���߽��û�ID��Ϊ�������룬���߽����ȡ�����ٱȽ��û�ID��
	 *  �ٻ��߸�ÿ���û������ݿ��н�һ����ͼ������������������
	 * @param contactorName
	 * @return List<Contactor> 
	 * ���ܴ���ͬ������ϵ�ˣ�����ģ������
	 *        
	 */
	public List<Contactor> findContactorByName(String contactorName,int userid);
	
	/**
	 * ������ϵ���ֻ��������
	 * 	 �ڲ���ѯ���ƣ�Ӧ�����Ȳ��ĳ�û������з������ϵ�ˣ������û��Ѿ�ָ���˷���
	 * 	Ȼ�����������в��ҡ����Խ��û�ID����SESSION�У���̨ʹ��ʱ��ȡ��
	 *  ���߽��û�ID��Ϊ�������룬���߽����ȡ�����ٱȽ��û�ID��
	 *  �ٻ��߸�ÿ���û������ݿ��н�һ����ͼ������������������
	 * @param contactorCellphoneNumber
	 * @return List<Contactor> 
	 * ��������˵һ�������Ӧһ����ϵ�ˣ����Ǵ����û�������벻��ȫ�����Կ��ܴ��ڶ�����������ģ������
	 */
	public List<Contactor> findContactorByCellphoneNumber(String contactorCellphoneNumber,int userid);
	
	/**
	 * ������ϵ�˵绰�������
	 * 	�ڲ���ѯ���ƣ�Ӧ�����Ȳ��ĳ�û������з������ϵ�ˣ������û��Ѿ�ָ���˷���
	 * 	Ȼ�����������в��ҡ����Խ��û�ID����SESSION�У���̨ʹ��ʱ��ȡ��
	 *  ���߽��û�ID��Ϊ�������룬���߽����ȡ�����ٱȽ��û�ID��
	 *  �ٻ��߸�ÿ���û������ݿ��н�һ����ͼ������������������
	 * @param contactorTelephoneNumber
	 * @return List<Contactor>
	 */
	public List<Contactor> findContactorByTelephoneNumber(String contactorTelephoneNumber,int userid);
	
	/**
	 * ������ϵ��������������
	 * 	�ڲ���ѯ���ƣ�Ӧ�����Ȳ��ĳ�û������з������ϵ�ˣ������û��Ѿ�ָ���˷���
	 * 	Ȼ�����������в��ҡ����Խ��û�ID����SESSION�У���̨ʹ��ʱ��ȡ��
	 *  ���߽��û�ID��Ϊ�������룬���߽����ȡ�����ٱȽ��û�ID��
	 *  �ٻ��߸�ÿ���û������ݿ��н�һ����ͼ������������������
	 * @param GroupName,userId ���ҷ�����ϵ�˵�ʱ�� ��Ҫ�����û�������
	 * @return List<Contactor>
	 */
	public List<Contactor> findContactorByGroupName(String GroupName,int userId);
	
	/**
	 * ������ϵ���������ID����
	 * 	 �ڲ���ѯ���ƣ�Ӧ�����Ȳ��ĳ�û������з������ϵ�ˣ������û��Ѿ�ָ���˷���
	 * 	Ȼ�����������в��ҡ����Խ��û�ID����SESSION�У���̨ʹ��ʱ��ȡ��
	 *  ���߽��û�ID��Ϊ�������룬���߽����ȡ�����ٱȽ��û�ID��
	 *  �ٻ��߸�ÿ���û������ݿ��н�һ����ͼ������������������
	 * @param GroupId,userId ���ҷ�����ϵ�˵�ʱ�� ��Ҫ�����û�������
	 * @return List<Contactor>
	 */
	public List<Contactor> findContactorByGroupId(int GroupId,int userId);
	
	/**
	 * �����û�ID�г���Ӧ�����е���ϵ��
	 * @param userId
	 * @return List<Contactor>
	 */
	public List<Contactor> findAllContactorsByUserId(int userId);
	
	
	/**
	 * �����û������ѯ ��Ҫ��ѯ �������绰���룬�ֻ����룬QQ����
	 * @param searchStr �û��������������
	 * @return List<Contactor>
	 */
	public List<Contactor> findSearchContactors(String searchStr,int userid);
	

}

