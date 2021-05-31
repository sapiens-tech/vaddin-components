package com.example.application.views.checkbox;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.*;

@Route(value = "checkbox", layout = MainView.class)
@PageTitle("Checkbox")
public class CheckboxView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;
    private Checkbox singleCheckbox, selectAll;
    private CheckboxGroup<String> checkboxGroup;
    private CheckboxGroup<Product> checkboxProductGroup, checkboxSelectAllGroup;
    private H1 h1Single, h1CheckboxGroup, h1EntityList, h1SelectAll;
    private List<Product> products;

    public CheckboxView() {
        addClassName("checkbox-view");
        h1Single = new H1("Single Checkbox");
        singleCheckbox = new Checkbox();
        singleCheckbox.setLabel("Single checkbox");
        singleCheckbox.setValue(true);

        h1CheckboxGroup = new H1("Checkbox group");
        checkboxGroup = new CheckboxGroup<>();
        checkboxGroup.setLabel("Group");
        checkboxGroup.setItems("Option 1", "Option 2", "Option 3", "Option 4");
        checkboxGroup.setValue(Collections.singleton("Option 1"));
        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

        h1EntityList = new H1("Entity List");
        products = initProducts();
        checkboxProductGroup = new CheckboxGroup<>();
        checkboxProductGroup.setLabel("Select Product");
        checkboxProductGroup.setItems(products);
        checkboxProductGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

        h1SelectAll = new H1("Select all");
        selectAll = new Checkbox("Select all");
        products = initProducts();
        Set<Product> items = new LinkedHashSet<>(products);
        checkboxSelectAllGroup = new CheckboxGroup<>();
        checkboxSelectAllGroup.setLabel("Select Product");
        checkboxSelectAllGroup.setItems(items);
        checkboxSelectAllGroup.addValueChangeListener(event -> {
            if (event.getValue().size() == items.size()) {
                selectAll.setValue(true);
                selectAll.setIndeterminate(false);
            } else if (event.getValue().size() == 0) {
                selectAll.setValue(false);
                selectAll.setIndeterminate(false);
            } else
                selectAll.setIndeterminate(true);

        });
        selectAll.addValueChangeListener(event -> {

            if (selectAll.getValue()) {
                checkboxSelectAllGroup.setValue(items);
            } else {
                checkboxSelectAllGroup.deselectAll();
            }
        });
        checkboxSelectAllGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

        add(h1Single, singleCheckbox, h1CheckboxGroup, checkboxGroup, h1EntityList, checkboxProductGroup, h1SelectAll, selectAll, checkboxSelectAllGroup);
//        name = new TextField("Your name");
//        sayHello = new Button("Say hello");
//        add(name, sayHello);
//        setVerticalComponentAlignment(Alignment.END, name, sayHello);
//        sayHello.addClickListener(e -> {
//            Notification.show("Hello " + name.getValue());
//        });
    }

    public List<Product> initProducts() {
        products = new ArrayList<>();
        Product p1 = new Product(1, "Iphone");
        Product p2 = new Product(2, "Samsung");
        Product p3 = new Product(3, "Oppo");
        products.add(p1);
        products.add(p2);
        products.add(p3);
        return products;
    }

}

class Product {
    private int id;
    private String name;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
