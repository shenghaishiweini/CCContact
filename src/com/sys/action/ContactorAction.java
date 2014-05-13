package com.sys.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.model.Contactor;
import com.sys.model.Group;
import com.sys.model.ShortMsg;
import com.sys.model.User;
import com.sys.service.Interface.IContactorService;
import com.sys.service.Interface.IGroupService;
import com.sys.service.Interface.IShortMsgService;
import com.sys.utils.Constants;

@Controller
@Scope("prototype")
public class ContactorAction extends ActionSupport {

	@Resource
	IContactorService contactorService;
	@Resource
	IGroupService groupService;
	@Resource
	IShortMsgService iShortMsgService;

	private Contactor contactor;
	private int id;
	private String[] selectedGroups;
	private List<String> selectedContactor = new ArrayList<String>();

	public List<String> getSelectedContactor() {
		return selectedContactor;
	}

	public void setSelectedContactor(List<String> selectedContactor) {
		this.selectedContactor = selectedContactor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getSelectedGroups() {
		return selectedGroups;
	}

	public void setSelectedGroups(String[] selectedGroups) {
		this.selectedGroups = selectedGroups;
	}

	public Contactor getContactor() {
		return contactor;
	}

	public void setContactor(Contactor contactor) {
		this.contactor = contactor;
	}

	public String addContactor1() throws Exception {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		User owner = (User) session.get("user");

		List<Group> groups = groupService.getAllGroupByUserId(owner.getId());
		if(groups!=null)
			groups.remove(0); // ��groups��ɾ��default���飬��Ϊ�������ϵ��ҳ���в���Ҫ��ʾ��δ���顱���ѡ��

		session.put("groups", groups);

		return SUCCESS;
	}

	public String addContactor2() throws Exception {

		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		User owner = (User) session.get("user");

		contactor.setOwner(owner);

		if (selectedGroups.length == 0) {
			contactorService.addContactorDefault(contactor);
		} else {
			contactorService.addContactor(contactor);
			for (int i = 0; i < selectedGroups.length; i++) {
				Group temp = groupService
						.getGroupByGroupName(selectedGroups[i]);
				contactorService.addContactorToNewGroup(contactor, temp);
			}
		}
        
		this.updateShortMsg(contactor, owner);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("tipMessage", "��ӳɹ���");
		// session.put("tipMessage", "��ӳɹ���");

		return SUCCESS;
	}

	public String getSingleContactor() throws Exception {
		Contactor temp = contactorService.findContactorById(id);

		List<Group> groupsOfContactor = groupService
				.getAllGroupsByContactorId(id);

		List<Group> groups = groupService.getAllGroupByUserId(temp.getOwner()
				.getId());
		groups.remove(0); // ��groups��ɾ��default���飬��Ϊ����ϵ����Ϣҳ���в���Ҫ��ʾ��δ���顱���ѡ��

		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("groups", groups);

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("contactor", temp);
		request.setAttribute("groups", groupsOfContactor);

		return SUCCESS;
	}

	public String updateContactor() throws Exception {
		Contactor temp = contactorService.findContactorById(id);
		temp.setName(contactor.getName());
		temp.setGender(contactor.getGender());
		temp.setCellphoneNumber(contactor.getCellphoneNumber());
		temp.setTelephoneNumber(contactor.getTelephoneNumber());
		temp.setEmail(contactor.getEmail());
		temp.setAddress(contactor.getAddress());
		temp.setQQ(contactor.getQQ());
		temp.setComments(contactor.getComments());
		temp.setOther1(contactor.getOther1());
		temp.setOther2(contactor.getOther2());

		contactorService.alterContactor(temp);

		groupService.removeGroupContactorItemOfContactor(id);

		if (selectedGroups.length == 0) {
			Group temp1 = groupService.getGroupByGroupName("default");
			contactorService.addContactorToNewGroup(temp, temp1);
		} else {
			for (int i = 0; i < selectedGroups.length; i++) {
				Group temp2 = groupService
						.getGroupByGroupName(selectedGroups[i]);
				contactorService.addContactorToNewGroup(temp, temp2);
			}
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("tipMessage", "��ĳɹ���");

		return SUCCESS;
	}

	public String deleteContactor() throws Exception {
		for (int i = 0; i < selectedContactor.size(); i++) {
			contactorService.deleteContactor(Integer.parseInt(selectedContactor
					.get(i)));
		}

		return SUCCESS;
	}

	public String findAllContactorsByUserId() throws Exception {

		// List<Contactor> list = contactorService.listAllContactors();
		// HttpServletRequest request = ServletActionContext.getRequest();
		// request.setAttribute("list", list);

		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		User user = (User) session.get("user");
		if (user == null) {
			return INPUT;
		}

		List<Contactor> list = contactorService.findAllContactorsByUserId(user
				.getId());

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("contatorList", list);

		Map<Integer, String> groupsOfContactor = new HashMap<Integer, String>();
		for (int i = 0; i < list.size(); i++) {
			List<Group> groups = groupService.getAllGroupsByContactorId(list
					.get(i).getId());
			StringBuffer str = new StringBuffer();
			for (int j = 0; j < groups.size(); j++) {
				String temp = groups.get(j).getGroupName();
				if (!"default".equals(temp)) {
					str.append(temp);
					str.append("   "); // ��3���ո���Ϊ�����
				}
			}
			groupsOfContactor.put(list.get(i).getId(), str.toString());
		}
		request.setAttribute("groupsOfContactor", groupsOfContactor);

		return SUCCESS;
	}

	private void updateShortMsg(Contactor contactor, User user) {

		List<ShortMsg> list = iShortMsgService.readConversationDetailMsgs(
				contactor.getCellphoneNumber(), user.getId());
		for (int i = 0; list != null && i < list.size(); i++) {
			ShortMsg t = list.get(i);
			if (t.getIfSender() == 1
					&& !t.getToName().equals(contactor.getName())) {
				{
					t.setToName(contactor.getName());
					t.setFromName(user.getDetailInfor().getName());
				}
			} else {
				t.setFromName(t.getFromName());
				t.setToName(user.getDetailInfor().getName());
			}
			iShortMsgService.updateShortMsg(t);
		}
	}

	public String  backupContactor()
	{
		
		return Constants.RETURN_JSON;
	}
	
	public String synchronousContactor()
	{
		JSONArray jsonArray=new JSONArray();
		return Constants.RETURN_JSON;
	}

}
