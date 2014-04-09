package com.sys.dao.Interface;
/**
 * 
 * @author gjf
 *
 */
public interface CommonMethodInterface<T> {

	public boolean saveObject(T obj);
	
	public boolean updateObject(T obj);
	
}
