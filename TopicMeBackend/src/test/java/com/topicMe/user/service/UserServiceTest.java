package com.topicMe.user.service;

import java.util.List;

import org.testng.annotations.Test;

import com.topicMe.model.businessdomain.User;
import com.topicMe.test.util.AbstractBackendTest;

@Test(suiteName="UserService")
public class UserServiceTest extends AbstractBackendTest{

	
	@Test
	public void firstTest(){
		User user = new User();
		user.setName("Khoa");
		user.setSurname("Tran");
	
		
		IUserService userService =  (IUserService) context.getBean("userService");
		userService.addUser(user);
		
		List<User> users = userService.getUsers();
		
		user = users.get(0);
		System.out.println(">>> User name "+ user.getName());
		
		assert user.getName().equals("Khoa");
	}
}
