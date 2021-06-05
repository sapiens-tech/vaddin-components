package com.example.application.views.radiobutton;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;

@Route(value = "radio-button", layout = MainView.class)
@PageTitle("Radio Button")
public class RadioButtonView extends HorizontalLayout {

    public RadioButtonView() {
        addClassName("radio-button-view");

        // Radio button group
        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
        radioGroup.setLabel("Address Type");
        radioGroup.setItems("Home", "Apartment", "Company");
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        radioGroup.setValue("Home");
        radioGroup.setHelperComponent(new Span(radioGroup.getValue()));;

        radioGroup.addValueChangeListener(event -> {
            if (event.getValue() == null) {
                radioGroup.setHelperComponent(new Span("No option selected"));
            } else {
                radioGroup.setHelperComponent(new Span("Selected: " + event.getValue()));
            }
        });
        add(radioGroup);

        // disable radio group
        RadioButtonGroup<String> disabledRadioGroup = new RadioButtonGroup<>();
        disabledRadioGroup.setLabel("Disabled");
        disabledRadioGroup.setItems("Option one", "Option two", "Option three");
        disabledRadioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        disabledRadioGroup.setValue("Option one");
        disabledRadioGroup.setEnabled(false);

        // disable radio item
        RadioButtonGroup<String> disableRadioItem = new RadioButtonGroup<>();
        disableRadioItem.setLabel("Disabled item");
        disableRadioItem.setItems("Enable option 1", "Enable option 2", "Disable option 3");
        disableRadioItem.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        disableRadioItem.setItemEnabledProvider(item -> !"Disable option 3".equals(item));

        add(disabledRadioGroup, disableRadioItem);
    }

}
