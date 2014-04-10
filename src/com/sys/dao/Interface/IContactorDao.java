package com.sys.dao.Interface;

import java.util.List;

import com.sys.model.Contactor;
/**
 * 
 * @author gjf
 *
 */
public interface IContactorDao {

	/**
	 * 
	 * @param contactor
	 */
	public boolean add(Contactor contactor);
	
	/**
	 * 
	 * @param contactor
	 */
	public void update(Contactor contactor);
	
	/**
	 * 
	 * @param contactorId
	 */
	public Contactor findById(int contactorId);
	
	/**
	 * 
	 * @param contactorId
	 */
	public  void deleteById(int contactorId);
	
	/**
	 * 
	 * @param contactorTelephoneNumber
	 * @return
	 */
	public Contactor findByCellphoneNumber(String contactorTelephoneNumber);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<Contactor> findAllContactorsByUserId(int userId);
	
	
}
