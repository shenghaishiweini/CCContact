package com.sys.service.Interface;

import com.sys.model.User;



/**
 * 接口,声明对用户相应的操作
 * @author Gui Junfei
 * 2014.2.7
 */
public interface IUserService {
	/**
	 * 注册新用户
	 * @param user新增用户的实例
	 * @return  boolean
	 * 成功返回true，失败返回false
	 * 
	 */
	public boolean registerUser(User user);
	
	/**
	 * 登录验证
	 * @param user 待验证用户的实例
	 * @return User
	 * 成功返回一个USER实例，失败返回NULL
	 * 
	 */
	public User checkLogin(User user);
	
	/**
	 * 修改用户信息
	 * @param user 修改后的用户实例
	 * @return boolean
	 * 成功返回true，失败返回false
	 * 
	 */
	public boolean alterUser(User user);
	
	/**
	 * 删除用户
	 * @param userid待删除用户的ID
	 * @return boolean
	 * 成功返回true，失败返回false
	 * 
	 */
	public boolean deleteUser(int userid);
	
	/**
	 * 根据ID 查找
	 * @param userid 查找ID
	 * @return User
	 * 成功返回一个USER实例，失败返回NULL
	 * 
	 */
	public User findUserById(int userid);
	
	
}
