package com.sys.action;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sys.model.Contactor;
import com.sys.model.User;
import com.sys.service.Impl.ContactorService;
import com.sys.utils.Constants;

/**
 * 
 * @author gjf
 *
 */
public class AndroidAction extends BaseAction{

	@Resource
	private ContactorService contactorService;
	
	/**
	 * 
	 */
	private JSONObject contactorsList;
	
	public String  backupContactor()
	{
		return Constants.RETURN_JSON;
	}
	
	/**
	 * @return
	 */
	public String synchronousContactor()
	{
		if(user==null||user.getDetailInfor().getCellphoneNumber()==null||user.getDetailInfor().getCellphoneNumber().endsWith(""))
		{
			msg=Constants.TIP_LOGIN_MSG;
			return Constants.RETURN_JSON;
		}
		
		List<Contactor> contactors=contactorService.findAllContactorsByUserId(user.getId());

		contactorsList=contactorListToJson(contactors);
		
		return Constants.RETURN_SYN_CONTACTOR;
	}

	
	public JSONObject contactorListToJson(List<Contactor> ContactorList){
		if(ContactorList == null || ContactorList.isEmpty()){
			return null;
		}
		
		JSONObject result = new JSONObject();
		
		JSONArray dataArray = new JSONArray();
		
		for(Contactor contactor : ContactorList){
			JSONArray array = new JSONArray();
			
			array.add(contactor.getId());				//0
			array.add(contactor.getAddress());				
			array.add(contactor.getCellphoneNumber());				
			array.add(contactor.getComments());				
			array.add(contactor.getEmail());
			array.add(contactor.getGender());
			array.add(contactor.getName());
			array.add(contactor.getOther1());
			array.add(contactor.getOther2());
			array.add(contactor.getOwner());
			array.add(contactor.getPicture_url());
			array.add(contactor.getQQ());
			array.add(contactor.getTelephoneNumber());
			
			dataArray.add(array);
		}
		
		result.accumulate("contactors", dataArray);
		
		return result;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ContactorService getContactorService() {
		return contactorService;
	}

	public void setContactorService(ContactorService contactorService) {
		this.contactorService = contactorService;
	}

	public JSONObject getContactorsList() {
		return contactorsList;
	}

	public void setContactorsList(JSONObject contactorsList) {
		this.contactorsList = contactorsList;
	}

	
	
}
