package com.grswebservices.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
	@GetMapping(value="/index")
	private String sayHello() {
		return "Hello world from Spring Boot...";		
	}
}


