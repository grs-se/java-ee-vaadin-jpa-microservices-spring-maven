package com.grswebservices.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.grswebservices.services.SecurityService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

@org.springframework.stereotype.Component
public class SignupViewFactory {
	
	@Autowired
	private SecurityService securityService;
	
	private class SignupForm {
		
		private VerticalLayout root;
		private TextField username;
		private PasswordField password;
		private PasswordField passwordAgain;
		private Button signup;
		private Button cancel;
		
		public SignupForm init() {
			username = new TextField("Username");
			password = new PasswordField("Password");
			passwordAgain = new PasswordField("Password Again");
			signup = new Button("Signup");
			cancel = new Button("Cancel");
			
			signup.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
			cancel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
			
			root = new VerticalLayout();
			root.setAlignItems(Alignment.CENTER);
			root.setJustifyContentMode(JustifyContentMode.CENTER);
			root.setMargin(true);
			
			return this;
		}
		
		public Component layout() {
			root.add(username);
			root.add(password);
			root.add(passwordAgain);
			root.add(new HorizontalLayout(signup, cancel));
			return root;
		}
	}
	
	public Component create() {
		return new SignupForm().init().layout();
	}

}
