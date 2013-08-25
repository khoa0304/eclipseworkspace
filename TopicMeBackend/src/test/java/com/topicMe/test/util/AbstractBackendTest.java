package com.topicMe.test.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeSuite;

public class AbstractBackendTest {

	protected ApplicationContext context = null;
	
	@BeforeSuite
    public void initBackendApplicationContext(){
		context =  new ClassPathXmlApplicationContext("backend-spring.xml");
		assert context !=null;
		
	}
}
