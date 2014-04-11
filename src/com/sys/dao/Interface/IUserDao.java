package com.sys.dao.Interface;

import com.sys.model.User;

public interface IUserDao {

	/**
	 * 
	 * @param user
	 */
	public void add(User user);
	/**
	 * 
	 * @param user
	 */
	public void update(User user);
	/**
	 * 
	 * @param user
	 */
	public void delete(User user);
	/**
	 * 
	 * @param userId
	 */
	public void delete(int userId);
	
	/**
	 * 
	 * @param userid
	 */
	public User find(int userid);
	
	public User find(String username,String password);
}
