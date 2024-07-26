package com.grswebservices.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.data.binder.ValidationException;

public class ContactForm extends VerticalLayout {
	
	@PropertyId("name")
	private TextField nameField;
	@PropertyId("email")
	private TextField emailField;
	@PropertyId("age")
	private TextField ageField;
	
	private Button saveButton;
	private Person person;
	private Binder<Person> binder;
	
	public ContactForm() {
		initComponents();
		initBinder();
		addComponents();
	}
	
	private void addComponents() {
		add(nameField, emailField, ageField, saveButton);
	}
	
	public void setPerson(Person person) {
		this.person = person;
		// set the values of the java object to the fields (UI components)
		binder.readBean(person);
	}
	
	private void initBinder() {
		binder = new BeanValidationBinder<>(Person.class);
		binder.bindInstanceFields(this);
	}
	
	private void initComponents() {
		person = new Person();
		
		nameField = new TextField("Name");
		emailField = new TextField("Email");
		ageField = new TextField("Age");
		
		saveButton = new Button("Save", event -> {
			try {
				// this is when the field (UI) values are injected into the java object (Person) 
				binder.writeBean(person);
				System.out.println("Data saved ... " + person);
			} catch (ValidationException e) {
				Notification.show("Validation error!");
			}
		});
	}
	

}
