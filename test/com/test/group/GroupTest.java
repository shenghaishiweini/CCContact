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
		gp.setGroupName("testNewGroup");
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
		Group gp=_groupService.getGroupByGroupId(2);
		gp.setGroupName("thisIsATest");
		//Contactor contactor=_contactorService.findContactorById(4);
//		Contactor contactor1=_contactorService.findContactorById(1);
		//Contactor temp=new Contactor();
//		temp=Contactor.copyContactor(temp,contactor);
//		System.out.print(temp==contactor);
//		HashSet set=new HashSet<Contactor>();
//		set.add(temp);
		//Set gpset=gp.getContacts();
		//gpset.add(contactor);
//		gpset.add(contactor1);
		//gp.setContacts(gpset);
		//_contactorService.addContactor(temp);
		_groupService.alterGroup(gp);
		
		
	}
	
	@Test
	public void testAddGroupContactorItem()
	{

		Contactor contactor=_contactorService.findContactorById(1);
//		Contactor contactor1=_contactorService.findContactorById(1);
		//Contactor temp=new Contactor();
//		temp=Contactor.copyContactor(temp,contactor);
		_groupService.addGroupContactorItem(2, contactor);
//		_groupService.addGroupContactorItem(1, contactor1);
		System.out.print("a");
		
	}
	
	@Test
	public void testRemoveGroupContactorItem()
	{
		_groupService.removeGroupContactorItem(2, 8);
//		System.out.print("a");
	}
	
	
	@Test
	public void testGetGroupContactors()
	{
		List<Contactor> list=_groupService.getGroupContactors(2);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getName());
		}
	}
	
}
