import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sys.model.Contactor;
import com.sys.model.User;
import com.sys.service.Interface.IContactorService;
import com.sys.service.Interface.IGroupService;
import com.sys.service.Interface.IUserService;


public class test {
	private static IUserService _userService;
	private static IGroupService _groupService;
	private static IContactorService _contactorService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
		_userService=(IUserService) ac.getBean("IUserService");
		_groupService=(IGroupService) ac.getBean("IGroupService");
		_contactorService=(IContactorService) ac.getBean("IContactorService");
	}
	
	@Test
	public void testRegisterUser()
	{
		User user=new User();
		user.setUsername("gjf2");
		user.setPassword("123456");
		Contactor contactor=new Contactor();
		contactor.setName("myself");
		user.setDetailInfor(contactor);
		user.getDetailInfor().setCellphoneNumber("15056998912");
		_userService.addUser(user);//�����û�
	}
}
