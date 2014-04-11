package com.sys.serviceInterface;

import java.util.List;

import com.sys.model.Contactor;
import com.sys.model.Group;



/**
 * @author Gui Junfei
 * 2014.2.7
 */
public interface IContactorService {
	
	/**
	 * @param contactor 
	 * @return boolean

	 * 
	 */
	public boolean addContactorDefault(Contactor contactor);
	
	/**

	 * @param contactor 
	 * @return
	 */
	public boolean addContactor(Contactor contactor);
	
	
	/**

	 * @param contactor 
	 * @return boolean

	 */
	public boolean alterContactor(Contactor contactor);
	
	/**

	 * @param contactorID
	 * @return boolean
	 */
	public boolean deleteContactor(int contactorID);
	
	
	/**

	 * @param contactor 
	 * @param group 
	 * @return boolean
	 * 
	 */
	public boolean addContactorToNewGroup(Contactor contactor,Group group);
	
	
	/**

	 * @param contactorID 
	 * @return Contactor 

	 */
	public Contactor findContactorById(int contactorID);
	
	/**

	 * @param contactorName
	 * @return List<Contactor> 

	 * 
	 */
	public List<Contactor> findContactorByName(String contactorName,int userid);
	
	/**

	 * @param contactorCellphoneNumber
	 * @return List<Contactor> 

	 */
	public List<Contactor> findContactorsByCellphoneNumber(String contactorCellphoneNumber,int userid);
	
	
	/**

	 * @param contactorCellphoneNumber
	 * @return Contactor 

	 */
	public Contactor findContactorByCellphoneNumber(String contactorCellphoneNumber,int userid);
	
	
	/**

	 * @param contactorTelephoneNumber
	 * @return List<Contactor>
	 */
	public List<Contactor> findContactorByTelephoneNumber(String contactorTelephoneNumber,int userid);
	
	/**

	 * @param GroupName,userId 
	 * @return List<Contactor>
	 */
	public List<Contactor> findContactorByGroupName(String GroupName,int userId);
	
	/**

	 * @param GroupId,userId 
	 * @return List<Contactor>
	 */
	public List<Contactor> findContactorByGroupId(int GroupId,int userId);
	
	/**

	 * @param userId
	 * @return List<Contactor>
	 */
	public List<Contactor> findAllContactorsByUserId(int userId);
	
	
	/**

	 * @param searchStr 
	 * @return List<Contactor>
	 */
	public List<Contactor> findSearchContactors(String searchStr,int userid);
	

}

