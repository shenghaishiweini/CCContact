package com.test.user;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sys.model.Contactor;
import com.sys.model.Group;
import com.sys.model.User;
import com.sys.serviceInterface.IContactorService;
import com.sys.serviceInterface.IGroupService;
import com.sys.serviceInterface.IUserService;
/**
 * 用户 单元测试
 * @author Gui Junfei
 * 2014.2.9
 */

public class UserTest {

	private static IUserService _userService;
	private static IGroupService _groupService;
	private static IContactorService _contactorService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
		_userService=(IUserService) ac.getBean("IUserService");
		_groupService=(IGroupService) ac.getBean("IGroupService");
		_contactorService=(IContactorService) ac.getBean("IContactorService");
	}
	
	
	@Test
	public void testRegisterUser()
	{
		//User user=_userService.findUserById(2);
		User user=new User();
		user.setUsername("gjf12");
		user.setPassword("123456");
		user.setCellphoneNumber("987654321");
		_userService.registerUser(user);//保存用户
		
		Group gp=new Group();
		gp.setGroupName("test123");
		
		Contactor ct=new Contactor();
		ct.setName("lisi");
		///_contactorService.addContactor(ct);//保存联系人
		
		Set contact=new HashSet<Contactor>();
		contact.add(ct);
		
		gp.setContacts(contact);
		gp.setOwner(user);
		//_userService.registerUser(user);//保存用户
		_groupService.newGroup(gp);//保存分组
		
	}
	
	@Test
	public void testGetGroupContactors()
	{
	Set	list=(Set) _groupService.getGroupContactors(4);
	for(int i=0;i<list.size();i++)
	{
		//list[i];
		//System.out.println(list.get(i).getName()+", "+list.get(i).getGroups());
	}
	
	}
	
}
