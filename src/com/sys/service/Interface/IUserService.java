package com.sys.service.Interface;

import com.sys.model.User;



/**
 * 用户接口
 * @author Gui Junfei
 * 2014.2.7
 */
public interface IUserService {
	/**
	 *ע
	 * @param user
	 * @return  boolean
	 *
	 * 
	 */
	public boolean addUser(User user);
	
	/**
	 *֤
	 * @param user 
	 * @return User
	 * 
	 * 
	 */
	public User checkLogin(User user);
	
	/**
	 * 
	 * @param user 
	 * @return boolean
	 * 
	 * 
	 */
	public boolean alterUser(User user);
	
	/**
	 *
	 * @param userid
	 * @return boolean
	 * 
	 * 
	 */
	public boolean deleteUser(int userid);
	
	/**
	 * 
	 * @param userid ����ID
	 * @return User
	 * 
	 * 
	 */
	public User findUserById(int userid);
	
	
}
