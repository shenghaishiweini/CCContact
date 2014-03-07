package com.sys.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import sun.nio.cs.ext.MS932DB.Decoder;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.model.Contactor;
import com.sys.model.ShortMsg;
import com.sys.model.User;
import com.sys.serviceInterface.IContactorService;
import com.sys.serviceInterface.IShortMsgService;
import com.sys.utils.TimeUtils;

public class ShortMsgAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private IShortMsgService _isIShortMsgService;
	@Resource
	private IContactorService _iContactorService;
	private ShortMsg shortMsg;
	private String selectedShortMsg;

	public String getSelectedShortMsg() {
		return selectedShortMsg;
	}

	public void setSelectedShortMsg(String selectedShortMsg) {
		this.selectedShortMsg = selectedShortMsg;
	}

	private List<String> selectedShortMsgs = new ArrayList<String>();

	public String getConversationList() {

		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		if (user != null) {
			List<ShortMsg> list = _isIShortMsgService.readAllConversations(user
					.getId());
			List<ShortMsg> res = new ArrayList<ShortMsg>();
			for (int i = 0; list != null && i < list.size(); i++) {
				if (!ifExist(list.get(i), res)) {
					if (list.get(i).getIfSender() == 1) {
						list.get(i).setConversation(list.get(i).getToName());
					} else {
						list.get(i).setConversation(list.get(i).getFromName());
					}
					res.add(list.get(i));
				}
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("conversationlist", res);
			request.setAttribute("conversationDetailMsgs", null);
		}
		return SUCCESS;
	}

	private boolean ifExist(ShortMsg target, List<ShortMsg> sourceList) {
		for (int i = 0; i < sourceList.size(); i++) {
			if ((sourceList.get(i).getFrom().equals(target.getFrom()) && sourceList
					.get(i).getTo().equals(target.getTo()))
					|| (sourceList.get(i).getFrom().equals(target.getTo()) && sourceList
							.get(i).getTo().equals(target.getFrom()))) {
				return true;
			}
		}
		return false;
	}

	public String getConversationDetailMsgs() {
		System.out.print(this.shortMsg.getId());
		ShortMsg sMsg = _isIShortMsgService.getShortMsgById(this.shortMsg
				.getId());
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		String recipipentCellphoneNumber = "";
		if (sMsg.getIfSender() == 1) {
			recipipentCellphoneNumber = sMsg.getTo();
		} else {
			recipipentCellphoneNumber = sMsg.getFrom();
		}
		List<ShortMsg> list = _isIShortMsgService.readConversationDetailMsgs(
				recipipentCellphoneNumber, user.getId());

		HttpSession httpSession = ServletActionContext.getRequest()
				.getSession();
		httpSession.removeAttribute("conversationDetailMsgs");
		httpSession.setAttribute("conversationDetailMsgs", list);
		return "success";
	}

	public String sendShortMsg() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		String sender = user.getDetailInfor().getCellphoneNumber();
		String reciver = shortMsg.getTo();

		shortMsg.setFromName(user.getDetailInfor().getName());
		Contactor contactor = _iContactorService
				.findContactorByCellphoneNumber(reciver, user.getId());
		if (contactor != null)
			shortMsg.setToName(contactor.getName());
		else
			shortMsg.setToName(reciver);
		shortMsg.setCreateTime(TimeUtils.getNowTime());
		shortMsg.setOwner(user);
		if (_isIShortMsgService.add(shortMsg))
			return "success";
		else
			return "failed";
	}

	public String deleteConversation() {

		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		int id = Integer.valueOf(ServletActionContext.getRequest()
				.getParameter("shortMsgid"));
		ShortMsg msg = _isIShortMsgService.getShortMsgById(id);
		String talkerCellphoneNumber = "";
		if (msg != null) {
			if (msg.getIfSender() == 1)
				talkerCellphoneNumber = msg.getTo();
			else
				talkerCellphoneNumber = msg.getTo();
		}
		if (_isIShortMsgService.deleteConversation(user.getId(),
				talkerCellphoneNumber))
			return "success";
		else
			return "failed";
	}

	public String deleteShortMsg() {
		String str[] = this.selectedShortMsg.split(",");
		for (int i = 0; i < str.length; i++) {
			if (str[i].trim() == "" || str[i].trim().equals(""))
				continue;
			ShortMsg t = _isIShortMsgService.getShortMsgById(Integer
					.valueOf(str[i].trim()));
			_isIShortMsgService.deleteShortMsg(t);
		}

		return "success";
	}

	public void setShortMsg(ShortMsg shortMsg) {
		this.shortMsg = shortMsg;
	}

	public ShortMsg getShortMsg() {
		return shortMsg;
	}

	public void setSelectedShortMsgs(List<String> selectedShortMsgs) {
		this.selectedShortMsgs = selectedShortMsgs;
	}

	public List<String> getSelectedShortMsgs() {
		return selectedShortMsgs;
	}

}
