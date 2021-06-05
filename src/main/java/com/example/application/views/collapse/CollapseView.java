package com.example.application.views.collapse;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "collapse", layout = MainView.class)
@PageTitle("Collapse")
public class CollapseView extends HorizontalLayout {
    private H1 h1Collapse;
    private Accordion accordion;
    private Span name, email, phone, street, zipCode, city, cardBrand, cardNumber, expiryDate;
    private VerticalLayout personalInformationLayout, billingAddressLayout, paymentLayout;
    private AccordionPanel personalInfoPanel, billingAddressPanel, paymentPanel;

    public CollapseView() {
        addClassName("checkbox-view");
        h1Collapse = new H1("Collapse Example");
        accordion = new Accordion();

        name = new Span("Sophia Williams");
        email = new Span("sophia.williams@company.com");
        phone = new Span("(501) 555-9128");

        personalInformationLayout = new VerticalLayout(name, email, phone);
        personalInformationLayout.setSpacing(false);
        personalInformationLayout.setPadding(false);

        personalInfoPanel = accordion.add("Personal information", personalInformationLayout);
        personalInfoPanel.setThemeName("filled");

        street = new Span("4027 Amber Lake Canyon");
        zipCode = new Span("72333-5884 Cozy Nook");
        city = new Span("Arkansas");

        billingAddressLayout = new VerticalLayout();
        billingAddressLayout.setSpacing(false);
        billingAddressLayout.setPadding(false);
        billingAddressLayout.add(street, zipCode, city);

        billingAddressPanel = accordion.add("Billing address", billingAddressLayout);
        billingAddressPanel.setThemeName("filled");

        cardBrand = new Span("Mastercard");
        cardNumber = new Span("1234 5678 9012 3456");
        expiryDate = new Span("Expires 06/21");

        paymentLayout = new VerticalLayout();
        paymentLayout.setSpacing(false);
        paymentLayout.setPadding(false);
        paymentLayout.add(cardBrand, cardNumber, expiryDate);

        paymentPanel = accordion.add("Payment", paymentLayout);
        paymentPanel.setThemeName("filled");
        add(h1Collapse, accordion);
    }

}

