package com.sys.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.sys.model.Contactor;
import com.sys.model.Group;
import com.sys.serviceInterface.IContactorService;

/**
 * 联系人接口实现类
 * 
 * @author Gui Junfei 2014.2.7
 */
@Transactional
public class ContactorService implements IContactorService {

	@Resource
	private SessionFactory sessionFactory;

	
	public boolean addContactor(Contactor contactor) {

		try {
			sessionFactory.getCurrentSession().persist(contactor);
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}

	}

	public boolean alterContactor(Contactor contactor) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delterContactor(int contactorID) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Contactor> findContactorByCellphoneNumber(
			String contactorCellphoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Contactor> findContactorByGroupId(int GroupId, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Contactor> findContactorByGroupName(String GroupName, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Contactor findContactorById(int contactorID) {
		try {
			Contactor temp = (Contactor) sessionFactory.getCurrentSession()
					.get(Contactor.class, contactorID);
			return temp;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Contactor> findContactorByName(String contactorName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Contactor> findContactorByTelephoneNumber(
			String contactorTelephoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
