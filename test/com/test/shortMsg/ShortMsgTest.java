package com.test.shortMsg;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sys.model.Contactor;
import com.sys.model.ShortMsg;
import com.sys.model.User;
import com.sys.serviceInterface.IContactorService;
import com.sys.serviceInterface.IGroupService;
import com.sys.serviceInterface.IShortMsgService;
import com.sys.serviceInterface.IUserService;

/**
 * 
 * @author Gui Junfei 2014.2.20
 */
public class ShortMsgTest {
	private static IUserService _userService;
	private static IGroupService _groupService;
	private static IContactorService _contactorService;
	private static IShortMsgService _ishortMsgService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		_userService = (IUserService) ac.getBean("IUserService");
		_groupService = (IGroupService) ac.getBean("IGroupService");
		_contactorService = (IContactorService) ac.getBean("IContactorService");
		_ishortMsgService = (IShortMsgService) ac.getBean("IShortMsgService");
	}

	@Test
	public void testAdd() {
		User user = _userService.findUserById(1);
		String sender = "15056998911";//
		String receiver = "15056998912";
		// User receiver= _userService.findUserById(4);
		ShortMsg shortmsg = new ShortMsg();
		shortmsg.setContent("me too");
		shortmsg.setFrom(receiver);
		shortmsg.setTo(sender);
//		shortmsg.setFrom(sender);
//		shortmsg.setTo(receiver);
		
		Contactor contactor=_contactorService.findContactorByCellphoneNumber(receiver, user.getId());
		if(contactor!=null)
		{
			shortmsg.setFromName(contactor.getName());
			shortmsg.setToName(user.getDetailInfor().getName());
		}
		else
		{
			shortmsg.setFromName(receiver);
			shortmsg.setToName(user.getDetailInfor().getName());
		}
		
		
		shortmsg.setOwner(user);
		java.util.Date utilDate = new java.util.Date();
		Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
		shortmsg.setCreateTime(sqlDate);
		_ishortMsgService.add(shortmsg);
	}

	@Test
	public void testReadAllConversations() {
		List<ShortMsg> list = _ishortMsgService.readAllConversations(3);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("from: "+list.get(i).getFrom() + " to: "
					+ list.get(i).getTo() + " content: "
					+ list.get(i).getContent());
		}
	}
	
	@Test
	public void testReadConversationDetailMsgs(){
		List<ShortMsg> list = _ishortMsgService.readConversationDetailMsgs("911",1);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("from: "+list.get(i).getFrom() + " to: "
					+ list.get(i).getTo() + " content: "
					+ list.get(i).getContent());
		}
		
	}
	
}
