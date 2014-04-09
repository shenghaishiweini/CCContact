package com.sys.dao.Interface;

import com.sys.model.ShortMsg;

public interface IShortMsgDao {
	/**
	 * 
	 * @param shortMsg
	 */
	public void add(ShortMsg shortMsg);
	/**
	 * 
	 * @param shortMsg
	 */
	public void update(ShortMsg shortMsg);
	/**
	 * 
	 * @param shortMsg
	 */
	public void delete(ShortMsg shortMsg);
	/**
	 * 
	 * @param shortMsgId
	 */
	public void delete(int shortMsgId);
	/**
	 * 
	 * @param shortMsgId
	 */
	public ShortMsg find(int shortMsgId);
}
