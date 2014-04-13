package com.sys.service.Interface;

import java.util.List;

import com.sys.model.ShortMsg;
/**
 * 短信服务接口
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
	 * 删除一条短信记录
	 * @param shortMsg
	 * @return
	 */
	public boolean deleteShortMsg(ShortMsg shortMsg);
	
	/**
	 * 删除一个会话，包括会话的所有短信
	 * @param userid
	 * @param talkerCellphoneNumber
	 * @return
	 */
    public boolean deleteConversation(int userid,String talkerCellphoneNumber);
    
    /**
     * 更新短信，主要是新增联系人时
     * @param shortMsg
     * @return
     */
    public boolean updateShortMsg(ShortMsg shortMsg);
	
}
