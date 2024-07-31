package com.grswebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@SpringBootApplication
@Theme(themeClass = Lumo.class)
public class StudentManagerApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagerApplication.class, args);
	}
	
	@Bean // injected into the IoC container which means we can use it in other parts of the application
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
