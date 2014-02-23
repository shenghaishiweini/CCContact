package com.test.group;

import java.util.List;

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
 * 分组单元测试
 * @author Gui Junfei
 * 2014.2.12
 *
 */
public class GroupTest {

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
	public void testNewGroup()
	{
		User user=_userService.findUserById(1); 
		Group gp=new Group();
		gp.setGroupName("aaa");
		gp.setOwner(user);
		_groupService.newGroup(gp);
	}
	

	@Test
	public void testDeleteGroup()
	{
		_groupService.deleteGroup(1);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testAlterGroup()
	{
		Group gp=_groupService.getGroupByGroupId(1);
		gp.setGroupName("alterTest-default");
		_groupService.alterGroup(gp);
	}
	
	@Test
	public void testMoveGroupContactorItem()
	{

		Contactor contactor=_contactorService.findContactorById(2);

		_groupService.moveGroupContactorItem(3, 2, contactor);

//		System.out.print("a");
		
	}
	
	@Test
	public void testRemoveGroupContactorItem()
	{
		_groupService.removeGroupContactorItem(1, 2);
//		System.out.print("a");
	}
	
	@Test
	public void testGetGroupContactors()
	{
		List<Contactor> list=_groupService.getGroupContactors(1);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getName());
		}
	}
	
	@Test
	public void testGetAllGroupByUserId()
	{
		List<Group> list=_groupService.getAllGroupByUserId(1);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getGroupName());
		}
	}
	
	@Test
	public void testGetAllGroupsByContactorId()
	{
		int contactorId = 11;
		List<Group> list = _groupService.getAllGroupsByContactorId(contactorId);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getGroupName());
		}
	}
	
	@Test
	public void testGetGroupByGroupName()
	{
		String str = "aaaa";
		System.out.println(_groupService.getGroupByGroupName(str).getId());
	}
}
