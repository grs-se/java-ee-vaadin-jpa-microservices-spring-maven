package com.grswebservices.spring;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/ui")
@PageTitle("Title")
public class MainView extends VerticalLayout {

	public MainView() {
		
		Person person = new Person();

		TextField nameField = new TextField("Name: ");
		nameField.setMaxLength(10);
		nameField.setMinLength(3);
		
		// Data binding: when we bind a UI component with a standard Java Object
		Binder<Person> binder = new Binder<>(Person.class);
		binder.bind(nameField, Person::getName, Person::setName);
		
		Button saveButton = new Button("Save",event -> {
			try {
				// update Person object
				binder.writeBean(person);
				System.out.println(person);
			} catch (ValidationException e) {
				e.printStackTrace();
			}
		});
		
		add(nameField);
		add(saveButton);
	}
}
