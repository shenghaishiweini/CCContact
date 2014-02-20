package com.test.shortMsg;

import java.sql.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sys.model.ShortMsg;
import com.sys.model.User;
import com.sys.serviceInterface.IContactorService;
import com.sys.serviceInterface.IGroupService;
import com.sys.serviceInterface.IShortMsgService;
import com.sys.serviceInterface.IUserService;

/**
 * 
 * @author Gui Junfei
 * 2014.2.20
 */
public class ShortMsgTest {
	private static IUserService _userService;
	private static IGroupService _groupService;
	private static IContactorService _contactorService;
	private static IShortMsgService _ishortMsgService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
		_userService=(IUserService) ac.getBean("IUserService");
		_groupService=(IGroupService) ac.getBean("IGroupService");
		_contactorService=(IContactorService) ac.getBean("IContactorService");
		_ishortMsgService=(IShortMsgService) ac.getBean("IShortMsgService");
	}
	
	@Test
	public void testAdd()
	{
		User user = _userService.findUserById(1);
		ShortMsg shortmsg=new ShortMsg();
		shortmsg.setContent("this is a test!");
		shortmsg.setFrom("123");
		shortmsg.setOwner(user);
		_ishortMsgService.add(shortmsg);
	}
	
}
