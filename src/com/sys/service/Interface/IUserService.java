package com.sys.service.Interface;

import com.sys.model.User;



/**
 * �ӿ�,�������û���Ӧ�Ĳ���
 * @author Gui Junfei
 * 2014.2.7
 */
public interface IUserService {
	/**
	 * ע�����û�
	 * @param user�����û���ʵ��
	 * @return  boolean
	 * �ɹ�����true��ʧ�ܷ���false
	 * 
	 */
	public boolean registerUser(User user);
	
	/**
	 * ��¼��֤
	 * @param user ����֤�û���ʵ��
	 * @return User
	 * �ɹ�����һ��USERʵ����ʧ�ܷ���NULL
	 * 
	 */
	public User checkLogin(User user);
	
	/**
	 * �޸��û���Ϣ
	 * @param user �޸ĺ���û�ʵ��
	 * @return boolean
	 * �ɹ�����true��ʧ�ܷ���false
	 * 
	 */
	public boolean alterUser(User user);
	
	/**
	 * ɾ���û�
	 * @param userid��ɾ���û���ID
	 * @return boolean
	 * �ɹ�����true��ʧ�ܷ���false
	 * 
	 */
	public boolean deleteUser(int userid);
	
	/**
	 * ����ID ����
	 * @param userid ����ID
	 * @return User
	 * �ɹ�����һ��USERʵ����ʧ�ܷ���NULL
	 * 
	 */
	public User findUserById(int userid);
	
	
}
