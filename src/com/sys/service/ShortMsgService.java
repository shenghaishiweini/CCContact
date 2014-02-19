package com.sys.service;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.sys.model.Group;
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

}
