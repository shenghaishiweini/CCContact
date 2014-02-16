package com.sys.serviceInterface;

import java.util.List;

import com.sys.model.Contactor;



/**
 * 接口,声明对用户相应的操作
 * @author Gui Junfei
 * 2014.2.7
 */
public interface IContactorService {
	
	/**
	 * 新增联系人
	 * @param contactor 新增的联系人
	 * @return boolean
	 * 成功返回true，失败返回false
	 */
	public boolean addContactor(Contactor contactor);
	
	/**
	 * 修改联系人信息
	 * @param contactor 修改的联系人
	 * @return boolean
	 * 成功返回true，失败返回false
	 */
	public boolean alterContactor(Contactor contactor);
	
	/**
	 * 删除联系人
	 * @param contactorID 删除只能按照ID来删除
	 * @return boolean
	 * 成功返回true，失败返回false
	 */
	public boolean delterContactor(int contactorID);
	
	/**
	 * 根据联系人ID 查找
	 * @param contactorID 
	 * @return Contactor 
	 * 联系的ID是唯一的，返回唯一。NULL 表示不存在指定ID的用户
	 */
	public Contactor findContactorById(int contactorID);
	
	/**
	 * 根据联系人名字查找
	 *  内部查询机制，应该是先查出某用户的所有分组的联系人，或者用户已经指定了分组
	 * 	然后再在这其中查找。可以将用户ID放入SESSION中，后台使用时再取出
	 *  或者将用户ID作为参数传入，或者将结果取出，再比较用户ID；
	 *  再或者给每个用户在数据库中建一个视图。相关情况，再做讨论
	 * @param contactorName
	 * @return List<Contactor> 
	 * 可能存在同名的联系人，采用模糊查找
	 *        
	 */
	public List<Contactor> findContactorByName(String contactorName);
	
	/**
	 * 根据联系人手机号码查找
	 * 	 内部查询机制，应该是先查出某用户的所有分组的联系人，或者用户已经指定了分组
	 * 	然后再在这其中查找。可以将用户ID放入SESSION中，后台使用时再取出
	 *  或者将用户ID作为参数传入，或者将结果取出，再比较用户ID；
	 *  再或者给每个用户在数据库中建一个视图。相关情况，再做讨论
	 * @param contactorCellphoneNumber
	 * @return List<Contactor> 
	 * 理论上来说一个号码对应一个联系人，但是存在用户输入号码不完全，所以可能存在多个结果。采用模糊查找
	 */
	public List<Contactor> findContactorByCellphoneNumber(String contactorCellphoneNumber);
	
	/**
	 * 根据联系人电话号码查找
	 * 	内部查询机制，应该是先查出某用户的所有分组的联系人，或者用户已经指定了分组
	 * 	然后再在这其中查找。可以将用户ID放入SESSION中，后台使用时再取出
	 *  或者将用户ID作为参数传入，或者将结果取出，再比较用户ID；
	 *  再或者给每个用户在数据库中建一个视图。相关情况，再做讨论
	 * @param contactorTelephoneNumber
	 * @return List<Contactor>
	 */
	public List<Contactor> findContactorByTelephoneNumber(String contactorTelephoneNumber);
	
	/**
	 * 根据联系人所在组名查找
	 * 	内部查询机制，应该是先查出某用户的所有分组的联系人，或者用户已经指定了分组
	 * 	然后再在这其中查找。可以将用户ID放入SESSION中，后台使用时再取出
	 *  或者将用户ID作为参数传入，或者将结果取出，再比较用户ID；
	 *  再或者给每个用户在数据库中建一个视图。相关情况，再做讨论
	 * @param GroupName,userId 查找分组联系人的时候 需要根据用户来查找
	 * @return List<Contactor>
	 */
	public List<Contactor> findContactorByGroupName(String GroupName,int userId);
	
	/**
	 * 根据联系人所在组的ID查找
	 * 	 内部查询机制，应该是先查出某用户的所有分组的联系人，或者用户已经指定了分组
	 * 	然后再在这其中查找。可以将用户ID放入SESSION中，后台使用时再取出
	 *  或者将用户ID作为参数传入，或者将结果取出，再比较用户ID；
	 *  再或者给每个用户在数据库中建一个视图。相关情况，再做讨论
	 * @param GroupId,userId 查找分组联系人的时候 需要根据用户来查找
	 * @return List<Contactor>
	 */
	public List<Contactor> findContactorByGroupId(int GroupId,int userId);
}

