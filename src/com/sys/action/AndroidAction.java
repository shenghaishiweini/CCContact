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
 * 专门接受android的请求
 * @author user
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
	 * 同步联系人
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
		
		return Constants.RETURN_SYN_Contactor;
	}

	
	public JSONObject contactorListToJson(List<Contactor> ContactorList){
		if(ContactorList == null || ContactorList.isEmpty()){
			return null;
		}
		
		JSONObject result = new JSONObject();
		
		JSONArray dataArray = new JSONArray();
		
		for(Contactor themeVo : ContactorList){
			JSONArray array = new JSONArray();
			
//			array.add(themeVo.getThemeId());				//0
//			array.add(themeVo.getThemeName());			//1
//			array.add(themeVo.getStartTime());				//2
//			array.add(themeVo.getEndTime());				//3
//			array.add(themeVo.getApplyDeadline());		//4
//			array.add(themeVo.getTagId());					//5
//			array.add(themeVo.getThemeRequest());		//6
//			array.add(themeVo.getThemeContent());		//7
//			
			dataArray.add(array);
		}
		
		result.accumulate("theme", dataArray);
		
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
