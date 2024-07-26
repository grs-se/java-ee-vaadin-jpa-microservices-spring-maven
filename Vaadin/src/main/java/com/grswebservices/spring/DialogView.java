package com.grswebservices.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/dialog")
@PageTitle("Title")
public class DialogView extends VerticalLayout {
	
	private TextField nameField;
	private TextField ageField;
	
	public DialogView() {
		
		Dialog dialog = new Dialog();
		dialog.setHeaderTitle("Add New Student");
		
		VerticalLayout dialogLayout = createDialogLayout();
		dialog.add(dialogLayout);
		
		Button cancel = new Button("Cancel", event -> dialog.close());
		Button save = new Button("Save", event -> {
			System.out.println(nameField.getValue() + "-" + ageField.getValue());
			dialog.close();
		});
		
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		
		dialog.getFooter().add(cancel);
		dialog.getFooter().add(save);
		
		Button button = new Button("Show Dialog", event -> dialog.open());
		
		add(dialog, button);
	}
	
	private VerticalLayout createDialogLayout() {
		nameField = new TextField("Student Name");
		ageField = new TextField("Student Age");
		
		VerticalLayout layout = new VerticalLayout(nameField, ageField);
		layout.getStyle().set("width", "250px").set("max-width", "100%");
		
		return layout;
	}

}
