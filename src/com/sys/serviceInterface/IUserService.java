package com.sys.serviceInterface;

import com.sys.model.User;



/**
 * 
 * @author Gui Junfei
 * 2014.2.7
 */
public interface IUserService {
	/**
	 * ע�����û�
	 * @param user
	 * @return  boolean
	 * 
	 * 
	 */
	public boolean registerUser(User user);
	
	/**
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
	 */
	public boolean alterUser(User user);
	
	/**
	 * @param userid
	 * @return boolean
	 * 
	 */
	public boolean deleteUser(int userid);
	
	/**
	 * @param userid 
	 * @return User
	 * 
	 */
	public User findUserById(int userid);
	
	
}
