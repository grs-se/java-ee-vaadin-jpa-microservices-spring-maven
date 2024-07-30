package com.grswebservices.views;

import java.util.List;

import com.grswebservices.constants.Constants;
import com.grswebservices.model.Status;
import com.grswebservices.model.Student;
import com.grswebservices.services.StatusService;
import com.grswebservices.services.StudentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Add Students")
// http://localhost:9090/add-student
@Route(value = "add-student")
public class AddStudentView extends VerticalLayout {
	
	private final StatusService statusService;
	private final StudentService studentService;
	
	private TextField age;
	private TextField zipCode;
	private TextField name;
	private TextField country;
	private ComboBox<Status> status;
	private LogoLayout image;
	
	private Button save;
	private Button close;
	
	private Student student;
	private Binder<Student> binder;

	public AddStudentView(StatusService statusService, StudentService studentService) {
		this.statusService = statusService;
		this.studentService = studentService;

		setAlignItems(Alignment.CENTER);
		createVariables();
		createStatus();
		createBinder();
		
		add(image);
		add(createFormLayout());
	}

	private void createBinder() {
		student = new Student();
		binder = new BeanValidationBinder<>(Student.class);
		binder.bindInstanceFields(this);
	}

	private Component createFormLayout() {
		FormLayout formLayout = new FormLayout();
		formLayout.add(name, age, country, zipCode, status, createButtons());
		formLayout.setColspan(image, 2);
		formLayout.setColspan(name, 2);
		return formLayout;
	}

	private Component createButtons() {
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		
		close.addClickListener(e -> closeView());
		save.addClickListener(e -> saveStudent());
		
		return new HorizontalLayout(save, close);
	}

	private void saveStudent() {
		try {
			binder.writeBean(student);
			// automates the following:
			// student.setAge(age.getValue());
			// student.setName(name.getValue());
			studentService.save(student);
			clearFields();
			Notification notification = Notification.show(Constants.STUDENT_SAVED);
			notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
			notification.setPosition(Position.TOP_CENTER);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	private void clearFields() {
		// reinitialise the student to be a new object because otherwise it would update the same student object in the database
		student = new Student();
		status.setValue(statusService.findAll().get(0));
		binder.getFields().forEach(HasValue::clear);
	}

	private void closeView() {
		// navigate to home page
		getUI().ifPresent(ui -> ui.navigate(""));
	}

	private void createVariables() {
		age = new TextField(Constants.AGE);
		name = new TextField(Constants.NAME);
		country = new TextField(Constants.COUNTRY);
		zipCode = new TextField(Constants.ZIP_CODE);
		status = new ComboBox<Status>(Constants.ZIP_CODE);
		image = new LogoLayout();
		save = new Button(Constants.SAVE);
		close = new Button(Constants.CANCEL);
	}
	
	private void createStatus() {
		List<Status> statusItems = statusService.findAll();
		status.setItems(statusItems);
		status.setValue(statusItems.get(0));
		status.setItemLabelGenerator(Status::getName);
	}
	
}
