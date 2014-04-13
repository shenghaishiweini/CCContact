package com.sys.serviceInterface;

import java.util.List;

import com.sys.model.ShortMsg;
/**
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
	public List<ShortMsg> readConversationDetailMsgs(String recipipentCellphoneNumber,int userid);
	
	/**
	 * 
	 * @param recipipentId
	 * @param userid
	 * @return
	 */
	public ShortMsg readFirstConversationMsgs(int recipipentId,int userid);
	
	/**
	 * 
	 * @param shortmsgid
	 * @return
	 */
	public ShortMsg getShortMsgById(int shortmsgid);
	
	/**

	 * @param shortMsg
	 * @return
	 */
	public boolean deleteShortMsg(ShortMsg shortMsg);
	
	/**

	 * @param userid
	 * @param talkerCellphoneNumber
	 * @return
	 */
    public boolean deleteConversation(int userid,String talkerCellphoneNumber);
    
    /**
     * @param shortMsg
     * @return
     */
    public boolean updateShortMsg(ShortMsg shortMsg);
	
}
