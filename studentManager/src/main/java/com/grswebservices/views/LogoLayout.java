package com.grswebservices.views;

import com.grswebservices.constants.Constants;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class LogoLayout extends HorizontalLayout {

	private Image image;
	
	public LogoLayout() {
		image = new Image(Constants.LOGO_URL, "My Image");
		
		setJustifyContentMode(JustifyContentMode.CENTER);
		
		add(image);
	}
	
}
