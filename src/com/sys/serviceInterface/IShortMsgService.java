package com.sys.serviceInterface;

import java.util.List;

import com.sys.model.ShortMsg;
/**
 * ���ŷ���ӿ�
 * @author Gui Junfei
 *
 */
public interface IShortMsgService {
	
	/**
	 * 
	 * @param shortmsg
	 * @return
	 */
	public boolean add(ShortMsg shortmsg);
	
	/**
	 * 
	 * @param userid
	 * @return
	 */
	public List<ShortMsg> readAllConversations(int userid);
	
	/**
	 * 
	 * @param recipipentId
	 * @param userid
	 * @return
	 */
	public List<ShortMsg> readConversationDetailMsgs(int recipipentId,int userid);
	
	/**
	 * 
	 * @param recipipentId
	 * @param userid
	 * @return
	 */
	public ShortMsg readFirstConversationMsgs(int recipipentId,int userid);
	
}
