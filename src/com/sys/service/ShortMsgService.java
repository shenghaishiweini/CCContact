package com.sys.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.sys.model.ShortMsg;
import com.sys.serviceInterface.IShortMsgService;
/**
 * 短信接口实现类
 * @author Gui Junfei
 * 2014.2.7
 */
@Transactional
public class ShortMsgService implements IShortMsgService {

	@Resource
	private SessionFactory sessionFactory;
	
	public boolean add(ShortMsg shortmsg) {
	
		try {
			sessionFactory.getCurrentSession().persist(shortmsg);
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
		
	}

	public List<ShortMsg> readMsgsByRecipient(int recipipentId, int userid) {
		// TODO Auto-generated method stub
		
		return null;
	}

	public List<ShortMsg> readMsgsByUserid(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

}
