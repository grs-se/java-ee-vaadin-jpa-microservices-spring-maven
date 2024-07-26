package com.grswebservices.spring;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/update")
@PageTitle("Update")
public class UpdateStudentView extends VerticalLayout implements BeforeEnterObserver {
	
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		add(new Text("Updating student..."));
	}
}
