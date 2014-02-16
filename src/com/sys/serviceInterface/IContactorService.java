package com.sys.serviceInterface;

import java.util.List;

import com.sys.model.Contactor;



/**
 * �ӿ�,�������û���Ӧ�Ĳ���
 * @author Gui Junfei
 * 2014.2.7
 */
public interface IContactorService {
	
	/**
	 * ������ϵ��
	 * @param contactor ��������ϵ��
	 * @return boolean
	 * �ɹ�����true��ʧ�ܷ���false
	 */
	public boolean addContactor(Contactor contactor);
	
	/**
	 * �޸���ϵ����Ϣ
	 * @param contactor �޸ĵ���ϵ��
	 * @return boolean
	 * �ɹ�����true��ʧ�ܷ���false
	 */
	public boolean alterContactor(Contactor contactor);
	
	/**
	 * ɾ����ϵ��
	 * @param contactorID ɾ��ֻ�ܰ���ID��ɾ��
	 * @return boolean
	 * �ɹ�����true��ʧ�ܷ���false
	 */
	public boolean delterContactor(int contactorID);
	
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
	public List<Contactor> findContactorByName(String contactorName);
	
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
	public List<Contactor> findContactorByCellphoneNumber(String contactorCellphoneNumber);
	
	/**
	 * ������ϵ�˵绰�������
	 * 	�ڲ���ѯ���ƣ�Ӧ�����Ȳ��ĳ�û������з������ϵ�ˣ������û��Ѿ�ָ���˷���
	 * 	Ȼ�����������в��ҡ����Խ��û�ID����SESSION�У���̨ʹ��ʱ��ȡ��
	 *  ���߽��û�ID��Ϊ�������룬���߽����ȡ�����ٱȽ��û�ID��
	 *  �ٻ��߸�ÿ���û������ݿ��н�һ����ͼ������������������
	 * @param contactorTelephoneNumber
	 * @return List<Contactor>
	 */
	public List<Contactor> findContactorByTelephoneNumber(String contactorTelephoneNumber);
	
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
}

