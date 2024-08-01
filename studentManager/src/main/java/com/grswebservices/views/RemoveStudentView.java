package com.grswebservices.views;

import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import com.grswebservices.constants.Constants;
import com.grswebservices.model.Student;
import com.grswebservices.services.StudentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Remove Student")
@Route(value = "remove-student")
@RolesAllowed("ROLE_ADMIN")
public class RemoveStudentView extends VerticalLayout implements SelectionListener<Grid<Student>, Student> {
	
	private Grid<Student> grid;
	private final StudentService studentService;
	
	private Button remove;
	private Button cancel;
	private Set<Student> selected;
	
	public RemoveStudentView(StudentService studentService) {
		this.studentService = studentService;
		
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		
		createFieldVariables();
		configureGrid();
		
		add(grid, createButtonLayout());
		
		loadStudents();
	}

	private void loadStudents() {
		grid.setItems(studentService.findAll());
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.addSelectionListener(this);
	}

	private Component createButtonLayout() {
		remove.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		
		cancel.addClickListener(e -> closeView());
		cancel.addClickListener(e -> removeSelectedItems());
		
		return new HorizontalLayout(remove, cancel);
	}

	private void removeSelectedItems() {
		selected.forEach(studentService::remove);
		Notification notification = Notification.show(Constants.REMOVE_STUDENT);
		notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
		// remove the students from the grid (update the grid)
		grid.setItems(studentService.findAll());
	}

	private void closeView() {
		getUI().ifPresent(ui -> ui.navigate(""));
	}

	private void createFieldVariables() {
		grid = new Grid<>(Student.class);
		remove = new Button(Constants.REMOVE);
		cancel = new Button(Constants.CANCEL);
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

	@Override
	public void selectionChange(SelectionEvent<Grid<Student>, Student> event) {
		selected = event.getAllSelectedItems();
	}
	
}
