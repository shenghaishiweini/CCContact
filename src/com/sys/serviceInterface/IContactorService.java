package com.sys.serviceInterface;

import java.util.List;

import com.sys.model.Contactor;
import com.sys.model.Group;



/**
 * 接口,声明对用户相应的操作
 * @author Gui Junfei
 * 2014.2.7
 */
public interface IContactorService {
	
	/**
	 * 新增联系人到Contactor表中，并且新增对应的Group_Contactor对象到Group_Contactor表中
	 * @param contactor 新增的联系人 
	 * @return boolean
	 * 成功返回true，失败返回false
	 * 
	 */
	public boolean addContactorDefault(Contactor contactor);
	
	/**
	 * 新增联系人到Contactor表中
	 * @param contactor 新增的联系人
	 * @return
	 */
	public boolean addContactor(Contactor contactor);
	
	/**
	 * 新增联系人到Contactor表中，并且新增对应的Group_Contactor对象到Group_Contactor表中
	 * @param contactor 新增的联系人
	 * @param group 联系人对应的分组，不包含default分组
	 * @return
	 * add by Fu Yu, 2014/2/22
	 */
//	public boolean addContactorToGroup(Contactor contactor,Group group);
	
	/**
	 * 修改联系人信息
	 * @param contactor 修改的联系人
	 * @return boolean
	 * 成功返回true，失败返回false
	 */
	public boolean alterContactor(Contactor contactor);
	
	/**
	 * 删除Contator中的记录,并且还会删除Group_Contactor表中的对应记录
	 * @param contactorID 删除只能按照ID来删除
	 * @return boolean
	 * 成功返回true，失败返回false
	 */
	public boolean deleteContactor(int contactorID);
	
	
	/**
	 * 给联系人新增分组
	 * @param contactor 新增的联系人 
	 * @param group 欲加入的组
	 * @return boolean
	 * 成功返回true，失败返回false
	 * 
	 */
	public boolean addContactorToNewGroup(Contactor contactor,Group group);
	
	
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
	public List<Contactor> findContactorByName(String contactorName,int userid);
	
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
	public List<Contactor> findContactorByCellphoneNumber(String contactorCellphoneNumber,int userid);
	
	/**
	 * 根据联系人电话号码查找
	 * 	内部查询机制，应该是先查出某用户的所有分组的联系人，或者用户已经指定了分组
	 * 	然后再在这其中查找。可以将用户ID放入SESSION中，后台使用时再取出
	 *  或者将用户ID作为参数传入，或者将结果取出，再比较用户ID；
	 *  再或者给每个用户在数据库中建一个视图。相关情况，再做讨论
	 * @param contactorTelephoneNumber
	 * @return List<Contactor>
	 */
	public List<Contactor> findContactorByTelephoneNumber(String contactorTelephoneNumber,int userid);
	
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
	
	/**
	 * 根据用户ID列出对应的所有的联系人
	 * @param userId
	 * @return List<Contactor>
	 */
	public List<Contactor> findAllContactorsByUserId(int userId);
	
	
	/**
	 * 根据用户输入查询 主要查询 姓名，电话号码，手机号码，QQ号码
	 * @param searchStr 用户输入的搜索条件
	 * @return List<Contactor>
	 */
	public List<Contactor> findSearchContactors(String searchStr,int userid);
	

}

