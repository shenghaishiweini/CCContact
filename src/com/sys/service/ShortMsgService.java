package com.sys.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.sys.model.ShortMsg;
import com.sys.model.User;
import com.sys.serviceInterface.IShortMsgService;

/**
 * 短信接口实现类
 * 
 * @author Gui Junfei 2014.2.7
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

	@SuppressWarnings("unchecked")
	public List<ShortMsg> readAllConversations(int userid) {
		String hql = "select * from ShortMsgs where userID=:userid order by createTime desc";
		Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
				.addEntity(ShortMsg.class);
		q.setParameter("userid", userid);
		List<ShortMsg> res = q.list();
		if (res.size() <= 0) {
			return null;
		} else {
			return res;
		}
	}

	public List<ShortMsg> readConversationDetailMsgs(String recipipentCellphoneNumber,
			int userid) {
//		Contactor contactor = (Contactor) sessionFactory.getCurrentSession().get(Contactor.class, 
//							recipipentId);
		User user = (User) sessionFactory.getCurrentSession().get(User.class,
					userid);
		
		
		String hql="select * from ShortMsgs where (fromnumber=:usernumber and tonumber=:recnumber) or (tonumber=:usernumber and fromnumber=:recnumber) order by createTime";
		Query q = sessionFactory.getCurrentSession().createSQLQuery(hql)
				.addEntity(ShortMsg.class);		
		q.setParameter("usernumber", user.getDetailInfor().getCellphoneNumber());
		q.setParameter("recnumber",recipipentCellphoneNumber);
		
		List<ShortMsg> res = q.list();
		if (res.size() <= 0) {
			return null;
		} else {
			return res;
		}
		
	
	}
	

	public ShortMsg readFirstConversationMsgs(int recipipentId, int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public ShortMsg getShortMsgById(int shortmsgid) {
		try {
			ShortMsg temp = (ShortMsg) sessionFactory.getCurrentSession().get(
					ShortMsg.class, shortmsgid);
			return temp;
		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
			return null;
		}
	}

	public boolean deleteShortMsg(ShortMsg shortMsg) {
		
		try {
			sessionFactory.getCurrentSession().delete(shortMsg);
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			sessionFactory.getCurrentSession().getTransaction().rollback();
			return false;
		}
	}
	
	
	

}
