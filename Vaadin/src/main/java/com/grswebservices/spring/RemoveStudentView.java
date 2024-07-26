package com.grswebservices.spring;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/remove")
@PageTitle("Remove")
public class RemoveStudentView extends VerticalLayout {
	
	public RemoveStudentView() {
		add(new Text("Removing students..."));
	}

}
