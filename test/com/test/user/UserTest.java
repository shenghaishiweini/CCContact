package com.test.user;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sys.model.Contactor;
import com.sys.model.Group;
import com.sys.model.ShortMsg;
import com.sys.model.User;
import com.sys.service.Interface.IContactorService;
import com.sys.service.Interface.IGroupService;
import com.sys.service.Interface.IUserService;
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
		User user=new User();
		user.setUsername("gjf");
		user.setPassword("123456");
		Contactor contactor=new Contactor();
		contactor.setName("myself");
		user.setDetailInfor(contactor);
		user.getDetailInfor().setCellphoneNumber("15056998911");
//		ShortMsg sh=new ShortMsg();
//		sh.setContent("hello !");
//		sh.setOwner(user);
//		Set mset=new HashSet();
//		mset.add(sh);
//		user.getMessages().add(mset);
		_userService.registerUser(user);//保存用户
	}
	@Test
	public void tsetAlterUser()
	{
		User user=_userService.findUserById(3);
		
		user.getDetailInfor().setAddress("ustc");
		user.getDetailInfor().setName("aaa");
		user.getDetailInfor().setCellphoneNumber("15056998911");
		_userService.alterUser(user);
	}
	@Test
	public void testCheckLogin()
	{
		User user = new User();
		user.setUsername("gjf02");
		user.setPassword("3456");
		User res=_userService.checkLogin(user);
		System.out.print(res);
		
	}
	
	@Test
	public void testDeleteUser()
	{
		_userService.deleteUser(3);
	}
	
//	@Test
//	public void testRegisterUser()
//	{
//		//User user=_userService.findUserById(2);
//		User user=new User();
//		user.setUsername("gjf02");
//		user.setPassword("123456");
//		user.setCellphoneNumber("987654321");
//		_userService.registerUser(user);//保存用户
//		
////		Group gp=new Group();
////		gp.setGroupName("test123");
////		
////		Contactor ct=new Contactor();
////		ct.setName("lisi");
////		///_contactorService.addContactor(ct);//保存联系人
////		
////		Set contact=new HashSet<Contactor>();
////		contact.add(ct);
////		
////		gp.setContacts(contact);
////		gp.setOwner(user);
////		//_userService.registerUser(user);//保存用户
////		_groupService.newGroup(gp);//保存分组
//		
//	}
	
	@Test
	public void testGetGroupContactors()
	{
		User user=_userService.findUserById(1);
		Set group=user.getGroups();
		
	Set	list=(Set) _groupService.getGroupContactors(4);
	for(int i=0;i<list.size();i++)
	{
		//list[i];
		//System.out.println(list.get(i).getName()+", "+list.get(i).getGroups());
	}
	
	}
	
}
