package com.grswebservices.views;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;

import com.grswebservices.constants.Constants;
import com.grswebservices.model.Status;
import com.grswebservices.model.Student;
import com.grswebservices.services.StudentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;

@PageTitle(value = "Home")
@Route(value = "/ui")
@PermitAll
public class MainView extends VerticalLayout {
	
	// constructor injection
	private final StudentService studentService;
	private LogoLayout logoLayout;
	private Grid<Student> grid;
	private TextField filterField;
	private Checkbox themeToggle;
	private static boolean isChecked;
	
	public MainView(StudentService studentService) {
		this.studentService = studentService;
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		
		createFieldVariables();
		configureGrid();
		
		add(logoLayout, createToolbar(), grid);
		
		loadStudents();
	}

	private Checkbox createToggle() {
		themeToggle = new Checkbox("Dark Mode");
		themeToggle.setValue(isChecked);
		themeToggle.addValueChangeListener(e -> {
			MainView.isChecked = !isChecked;
			setTheme(isChecked);
		});
		
		return themeToggle;
	}

	private void setTheme(boolean dark) {
		var js = MessageFormat.format("""
				document.documentElement.setAttribute("theme", "{0}")
				""", dark ? Lumo.DARK : Lumo.LIGHT);
		getElement().executeJs(js);
	}

	private void configureGrid() {
		grid.setSizeFull();
		grid.setColumns("country", "zipCode");
		grid.addColumn(s -> s.getName()).setHeader(Constants.NAME);
		grid.addColumn(s -> s.getAge()).setHeader(Constants.AGE);
		
		grid.addComponentColumn(s -> {
			// different colour icons for different status values
			Icon icon;
			
			if (s.getStatus().getName().equals("ACTIVE")) {
				icon = VaadinIcon.CIRCLE.create();
				icon.setColor("green");
			} else if (s.getStatus().getName().equals("PASSIVE")) {
				icon = VaadinIcon.CLOSE_CIRCLE.create();
				icon.setColor("red");
			} else {
				icon = VaadinIcon.CHECK_CIRCLE.create();
				icon.setColor("orange");
			}
			
			return icon;
		}).setHeader(Constants.STATUS);
		
		
		grid.getColumns().forEach(col -> col.setAutoWidth(true));
	}

	private void loadStudents() {		
		grid.setItems(studentService.findAll());
	}

	private void createFieldVariables() {
		this.logoLayout = new LogoLayout();
		this.grid = new Grid<>(Student.class);
		this.filterField = new TextField();
	}
	
	private Component createToolbar() {
		filterField.setPlaceholder(Constants.FILTER_NAME);
		filterField.setClearButtonVisible(true);
		filterField.setValueChangeMode(ValueChangeMode.LAZY);
		filterField.addValueChangeListener(e -> updateStudents());
		
		Button addStudentButton = new Button(Constants.ADD_STUDENT);
		Button removeStudentButton = new Button(Constants.REMOVE_STUDENT);
		
		addStudentButton.addClickListener(e ->
			// http://localhost:9090/add-student
			getUI().ifPresent(ui -> ui.navigate("add-student")));
		
		removeStudentButton.addClickListener(e ->
			// http://localhost:9090/add-student
			getUI().ifPresent(ui -> ui.navigate("remove-student")));
		
		return new HorizontalLayout(filterField, addStudentButton, removeStudentButton, createToggle());
	}

	private void updateStudents() {
		grid.setItems(studentService.find(filterField.getValue()));
	}
}
