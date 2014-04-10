package com.test.contactor;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sys.model.Contactor;
import com.sys.model.Group;
import com.sys.model.User;
import com.sys.service.Interface.IContactorService;
import com.sys.service.Interface.IGroupService;
import com.sys.service.Interface.IUserService;

public class ContactorTest {

	private static IUserService _userService;
	private static IGroupService _groupService;
	private static IContactorService _contactorService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		_userService = (IUserService) ac.getBean("IUserService");
		_groupService = (IGroupService) ac.getBean("IGroupService");
		_contactorService = (IContactorService) ac.getBean("IContactorService");
	}

	@Test
	public void testAddContactorToNewGroup() {
		User user = _userService.findUserById(1);

		Contactor contactor = _contactorService.findContactorById(2);
		contactor.setOwner(user);

		Group group1 = _groupService.getGroupByGroupId(1);

		_contactorService.addContactorToNewGroup(contactor, group1);
	}

	@Test
	public void testAddContactorDefault() {
		
		User user = _userService.findUserById(1);
		Contactor contactor = new Contactor();
		contactor.setName("liMing");
		contactor.setCellphoneNumber("15056998910");
		contactor.setOwner(user);

		_contactorService.addContactorDefault(contactor);
	}

	@Test
	public void testFindAllContactorsByUserId() {
		int userID = 1;

		List<Contactor> list = _contactorService
				.findAllContactorsByUserId(userID);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	@Test
	public void testDeleteContactor() {
		int contactorId = 6;
		_contactorService.deleteContactor(contactorId);
	}
	
	@Test
	public void testFindContactorByCellphoneNumber()
	{
		List<Contactor> res=_contactorService.findContactorsByCellphoneNumber("1", 1);
		for(int i=0;i<res.size();i++)
		{
			System.out.println(res.get(i).getName());
		}
		
		
	}
	
	@Test
	public void testFindContactorByName()
	{
		List<Contactor> res=_contactorService.findContactorByName("e", 1);
		for(int i=0;i<res.size();i++)
		{
			System.out.println(res.get(i).getName());
		}
		
		
	}
	
	@Test
	public void testFindSearchContactors()
	{
		List<Contactor> res=_contactorService.findSearchContactors("4", 1);
		for(int i=0;i<res.size();i++)
		{
			System.out.println(res.get(i).getName());
		}
		
		
	}
	

}
