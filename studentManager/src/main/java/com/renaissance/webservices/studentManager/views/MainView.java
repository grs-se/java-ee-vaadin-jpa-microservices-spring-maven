package com.renaissance.webservices.studentManager.views;

import java.util.ArrayList;
import java.util.List;

import com.renaissance.webservices.studentManager.model.Status;
import com.renaissance.webservices.studentManager.model.Student;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle(value = "Home")
@Route(value = "/ui")
// http://localhost:8080/ui
public class MainView extends VerticalLayout {
	
	private LogoLayout logoLayout;
	private Grid<Student> grid;
	
	public MainView() {
		
		setSizeFull();
		setAlignItems(Alignment.CENTER);

		
		createFieldVariables();

		
		add(logoLayout, grid);
		
		loadStudents();
	}

	private void loadStudents() {
		List<Student> students = new ArrayList<>();
		students.add(new Student("Adam", 24, 1675, "UK", new Status("ACTIVE")));
		grid.setItems(students);
	}

	private void createFieldVariables() {
		logoLayout = new LogoLayout();
		grid = new Grid<>(Student.class);
	}
}
