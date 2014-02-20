package com.sys.serviceInterface;

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
	
	public List<ShortMsg> readMsgsByUserid(int userid);
	
	public List<ShortMsg> readMsgsByRecipient(int recipipentId,int userid);
	
}
