package com.grswebservices.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/ui")
@PageTitle("Title")
public class MainView extends VerticalLayout {

	public MainView() {
		
		Span span = new Span("This is a span in Vaadin...");
		span.getElement().getStyle().set("font-size","23px");
		span.getElement().getStyle().set("font-weight","bold");
		
		Button button = new Button("Click me!", event -> {
			System.out.println("Button has been clicked...");
		});
		
		add(span);
		add(button);
	}
}
