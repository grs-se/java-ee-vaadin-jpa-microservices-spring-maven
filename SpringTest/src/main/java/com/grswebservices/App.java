package com.grswebservices;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		// IoC container - this is where the beans are located
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		// we do not get a NULL exception because the Spring container does the instantiation under the hood
		Student s = (Student) context.getBean("student_bean");
		s.setStudentName("Kevin");
		System.out.println(s.getStudentName());
		
		Student s2 = (Student) context.getBean("student_bean");
		System.out.println(s2.getStudentName());
		
		// this is how to close the context
		((ConfigurableApplicationContext) context).close();
	}

}
