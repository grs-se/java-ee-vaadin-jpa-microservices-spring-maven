package com.grswebservices.spring;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("/ui")
@PageTitle("Title")
public class MainView extends VerticalLayout {

	public MainView() {
		
		Person person = new Person();
		
		List<Person> list = new ArrayList<>();
		list.add(new Person("Adam", 33));
		list.add(new Person("Anna", 12));
		list.add(new Person("Kevin", 67));
		list.add(new Person("Steven", 45));

		TextField nameField = new TextField("Name: ");
		nameField.setMaxLength(10);
		nameField.setMinLength(3);
		nameField.setPrefixComponent(new Icon("vaadin", "building"));
		nameField.setSuffixComponent(new Icon("vaadin", "user"));
	
		TextArea textArea = new TextArea();
		textArea.setLabel("Description");
		textArea.setValue("This is going to be a very long text");
		textArea.setHeight("200px");
		textArea.setWidth("200px");
		textArea.setMaxLength(20);
		
		Image image = new Image("images/algorhym_logo.png", "Alt Text");
		image.setWidth("300px");
		image.setHeight("300px");
		
		// eager = synchronizes value to the server every time it is changed on the client side
		textArea.setValueChangeMode(ValueChangeMode.EAGER);
		
		textArea.addValueChangeListener(event -> {
			// this is called every time there is a change in the text area
			System.out.println("Something has been changed");
		});
		
		Checkbox check = new Checkbox();
		check.setLabel("I agree to the terms");
		
		check.addValueChangeListener(event -> {
			System.out.println(check.getValue());
		});
		
		check.setEnabled(false);
		
		CheckboxGroup<String> group = new CheckboxGroup<>();
		group.setLabel("Days");
		group.setItems("Monday", "Tuesday", "Wednesday");
		group.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
		
		group.addValueChangeListener(event -> {
			System.out.println(group.getValue());
		});
		
		ComboBox<Person> box = new ComboBox<>("Employee");
		box.setItems(list);
		box.setPlaceholder("Select an employee");
		// what we want to show in the combo box
		box.setItemLabelGenerator(pers -> pers.getName() + " / " + pers.getAge());
		box.addValueChangeListener(event -> {
			System.out.println(box.getValue());
		});
		
		List<Person> employees = new ArrayList<>();
		
		// autoCreateColumns - cols for all properties of bean generated in alphabetical order
		// default height of grid = 400px
		Grid<Person> grid = new Grid<>(Person.class, false);
		employees.add(new Person("Adam", "adam@gmail.com", 35));
		employees.add(new Person("Anna", "anna@gmail.com", 15));
		employees.add(new Person("Daniel", "daniel@gmail.com", 78));
		employees.add(new Person("Lucy", "lucy@gmail.com", 65));
		employees.add(new Person("Betty", "betty@gmail.com", 34));
		
		grid.addColumn(Person::getName).setHeader("Name").setSortable(true).setTextAlign(ColumnTextAlign.CENTER);
		grid.addColumn(Person::getEmail).setHeader("Email");
		grid.addColumn(Person::getAge).setHeader("Age");
		
		grid.setAllRowsVisible(true);
		grid.setItems(employees);
		
		grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
		grid.setSelectionMode(Grid.SelectionMode.MULTI);
		
		Button remove = new Button("Remove");
		
		remove.addClickListener(event -> {
			System.out.println(grid.getSelectedItems());
			System.out.println(employees.size());
			employees.removeAll(grid.getSelectedItems());
			System.out.println(employees.size());
			// refresh grid
			grid.getDataProvider().refreshAll();
		});
		
		Tab tab1 = new Tab(VaadinIcon.BELL.create(), new Span("Orders"));
		Tab tab2 = new Tab(VaadinIcon.USER.create(), new Span("Payments"));
		Tab tab3 = new Tab(VaadinIcon.COG.create(), new Span("Services"));
		
		tab1.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
		tab2.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
		tab3.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
		
		tab3.setEnabled(false);
		
		Tabs mainTab = new Tabs(tab1, tab2, tab3);
		mainTab.setSelectedTab(tab2);
		
		mainTab.addThemeVariants(TabsVariant.LUMO_EQUAL_WIDTH_TABS);
		mainTab.setWidth("100%");
//		mainTab.setOrientation(Tabs.Orientation.VERTICAL);
		
//		setHeight("100%");
//		setJustifyContentMode(FlexComponent.JustifyContentMode.EVENLY);
//		setAlignItems(FlexComponent.Alignment.STRETCH);
		
		Icon icon = new Icon("vaadin", "academy-cap");
		
		
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
		
		ContactForm form = new ContactForm();
		
		add(nameField);
		add(saveButton);
		add(textArea);
		add(check);
		add(group);
		add(box);
		add(grid);
		add(remove);
		add(mainTab);
		add(icon);
		add(image);
		add(form);
		
		// you can use the RouterLink component to create links pointing
		// to route targets (@Route) in your application
		//
		// Navigation with RouterLink fetches the content of the new component
		// WITHOUT RELOADING the page - the page is updated in place
		add(new RouterLink("Show Students", ShowStudentsView.class));
		add(new RouterLink("Update Students", UpdateStudentView.class));
		
		// you can trigger navigation from the server side using UI.navigate(String)
		Button removeButton = new Button("Remove Student View", event -> {
			getUI().ifPresent(ui -> ui.navigate(RemoveStudentView.class));
		});
	}
}
