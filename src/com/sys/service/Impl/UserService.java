package com.sys.service.Impl;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.sys.dao.Interface.IUserDao;
import com.sys.model.User;
import com.sys.service.Interface.IUserService;

/**
 * 用户
 * 
 * @author Gui Junfei 2014.2.7
 */
@Transactional
public class UserService implements IUserService {

	@Resource
	private IUserDao userDao;
	
	public boolean addUser(User user) {

		try {
			userDao.add(user);
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
		
	}

	public User findUserById(int userid) {
		try{
			return  userDao.find(userid);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return null;
		}
	}

	public boolean alterUser(User user) {
		try{		
			if (user !=null){
				userDao.update(user);
				return true;
			}else{
				return false;
			}
		}catch (Exception e){
			System.out.print(e.getMessage());
			return false;
		}
	}

	public User checkLogin(User user) {
		try{		
			if (user !=null&&user.getUsername()!=null&&user.getPassword()!=null&&!user.getUsername().equals("")&&!user.getPassword().equals("")){
				return 	userDao.find(user.getUsername(),user.getPassword());
			}else{
				return null;
			}
		}catch (Exception e){
			System.out.print(e.getMessage());
			return null;
		}
	}

	public boolean deleteUser(int userid) {
		try{
			userDao.delete(userid);
			return true;
		}catch(Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
		
	}

}
