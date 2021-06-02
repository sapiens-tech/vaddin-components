package com.example.application.views.textbox;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Route(value = "textbox", layout = MainView.class)
@PageTitle("Text Box")
@CssImport("themes/vaddincomponents/views/text-box-view.css")
public class TextBoxView extends VerticalLayout {

    public TextBoxView() {
        HorizontalLayout passwordFieldLayout = new HorizontalLayout();
        passwordFieldLayout.setWidthFull();
        Span span = new Span("Password field");
        span.addClassName("title-text-box-view");

        PasswordField passwordField = new PasswordField();
        passwordField.setLabel("Password basic");
        passwordField.setPlaceholder("Enter password");
        passwordField.setValue("secret1");

        PasswordField passwordField2 = new PasswordField();
        passwordField2.setLabel("Password hide the reveal button");
        passwordField2.setValue("secret1");
        passwordField2.setRevealButtonVisible(false);

        add(passwordField);

        passwordFieldLayout.add(span);
        passwordFieldLayout.add(passwordField);
        passwordFieldLayout.add(passwordField2);
        add(passwordFieldLayout);

        HorizontalLayout numberFieldLayout = new HorizontalLayout();
        numberFieldLayout.setWidthFull();
        Span span1 = new Span("Number field");
        span1.addClassName("title-text-box-view");

        NumberField numberField = new NumberField("Basic number field");

        IntegerField integerField = new IntegerField("Integer");
        integerField.setHasControls(true);
        integerField.setMin(1);
        integerField.setMax(32);
        integerField.setHelperText("Integer field with has control button and min, max value");

        BigDecimalField bigDecimalField = new BigDecimalField("Big decimal view");
        bigDecimalField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        bigDecimalField.setPrefixComponent(new Icon(VaadinIcon.DOLLAR));

        Paragraph tax = new Paragraph();
        bigDecimalField.addValueChangeListener(e -> {
            BigDecimal taxValue;
            if (e.getValue() == null) {
                taxValue = BigDecimal.ZERO;
            } else {
                taxValue = e.getValue().multiply(new BigDecimal("0.24"))
                        .setScale(2, RoundingMode.HALF_EVEN);
            }
            tax.setText("VAT 10%: $" + taxValue);
        });
        bigDecimalField.setValue(new BigDecimal(15).setScale(2));


        NumberField decimalField = new NumberField("Number field with step");
        decimalField.setHasControls(true);
        decimalField.setValue(0.0);
        decimalField.setStep(0.1);
        decimalField.setMin(0);
        decimalField.setMax(10);

        numberFieldLayout.add(span1);
        numberFieldLayout.add(numberField);
        numberFieldLayout.add(integerField);
        numberFieldLayout.add(bigDecimalField, tax);
        numberFieldLayout.add(decimalField);
        add(numberFieldLayout);

        HorizontalLayout emailFieldLayout = new HorizontalLayout();
        Span span2 = new Span("Email Field");
        span2.addClassName("title-text-box-view");

        EmailField emailField = new EmailField("Email");
        emailField.setClearButtonVisible(true);
        emailField.setErrorMessage("Please enter a valid email address");

        emailFieldLayout.add(span2, emailField);
        add(emailFieldLayout);

        HorizontalLayout textAreaLayout = new HorizontalLayout();
        Span span3 = new Span("Text Area");
        span3.addClassName("title-text-box-view");

        TextArea textArea = new TextArea("Description");
        textArea.getStyle().set("minWidth", "350px");
        textArea.getStyle().set("minHeight", "350px");
        textArea.setPlaceholder("Write here ...");
        textArea.setHelperComponent(new Span(
                "Here you can share what you've liked and "
                        + "what can be improved in the next lesson"));

        textAreaLayout.add(span3, textArea);
        add(textAreaLayout);

        HorizontalLayout textFieldLayout = new HorizontalLayout();
        Span span4 = new Span("Text field");
        span4.addClassName("title-text-box-view");

        TextField placeholderField = new TextField();
        placeholderField.setLabel("Text field with placeholder");
        placeholderField.setPlaceholder("Placeholder");

        TextField disabledField = new TextField();
        disabledField.setValue("Value");
        disabledField.setLabel("Disabled");
        disabledField.setEnabled(false);

        TextField readonlyField = new TextField();
        readonlyField.setValue("Value");
        readonlyField.setLabel("Read-only");
        readonlyField.setReadOnly(true);

        TextField textField = new TextField();
        textField.setLabel("Display the clear button");
        textField.setValue("Value");
        textField.setClearButtonVisible(true);

        TextField helperFieldText = new TextField("Text Field with helper text");
        helperFieldText.setHelperText("Text Field with helper text");

        textFieldLayout.add(span4, placeholderField, disabledField, readonlyField, textField, helperFieldText);
        add(textFieldLayout);
    }
}
