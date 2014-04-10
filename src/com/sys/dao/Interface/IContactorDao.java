package com.sys.dao.Interface;

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
	public void add(Contactor contactor);
	
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
	
	public Contactor findByCellphoneNumber(String contactorTelephoneNumber);
	
}
