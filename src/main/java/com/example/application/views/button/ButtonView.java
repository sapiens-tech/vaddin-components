package com.example.application.views.button;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "button", layout = MainView.class)
@PageTitle("Button")
public class ButtonView extends VerticalLayout {

    public ButtonView() {
        // default button
        HorizontalLayout defaultButton = new HorizontalLayout();
        defaultButton.setWidthFull();
        Button button = new Button("Vaadin button");
        button.addClickListener(this::showButtonClickedMessage);
        defaultButton.add(button);
        add(defaultButton);

        // icon button
        HorizontalLayout iconButton = new HorizontalLayout();
        iconButton.setWidthFull();
        Button leftButton = new Button("Left", new Icon(VaadinIcon.ARROW_LEFT));
        Button rightButton = new Button("Right",
                new Icon(VaadinIcon.ARROW_RIGHT));
        rightButton.setIconAfterText(true);
        Button youTubeButton = new Button(new Icon(VaadinIcon.YOUTUBE));
        iconButton.add(leftButton, rightButton, youTubeButton);
        add(iconButton);

        // image button
        HorizontalLayout imageButtonLayout = new HorizontalLayout();
        imageButtonLayout.setWidthFull();
        Button imageButton = new Button(
                new Image("images/logo.png", "Vaadin logo"));
        imageButton.setAutofocus(true);
        imageButton.addClassNames("image-button");
        imageButton.addClickListener(this::showButtonClickedMessage);
        imageButtonLayout.add(imageButton);
        add(imageButtonLayout);

        //primary button
        HorizontalLayout primaryButton = new HorizontalLayout();
        primaryButton.setWidthFull();
        Button primaryThemeButton = new Button("Primary");
        primaryThemeButton.addThemeVariants(ButtonVariant.LUMO_SMALL,
                ButtonVariant.LUMO_PRIMARY);
        primaryButton.add(primaryThemeButton);
        add(primaryButton);

        // disable button
        HorizontalLayout disableButtonLayout = new HorizontalLayout();
        disableButtonLayout.setWidthFull();
        Button disableButton = new Button("Disabled");
        disableButton.setEnabled(false);
        disableButtonLayout.add(disableButton);
        add(disableButtonLayout);

        // button shortcut
        HorizontalLayout shortcutButton = new HorizontalLayout();
        shortcutButton.setWidthFull();
        TextField firstName = new TextField("First name");
        firstName.setValueChangeMode(ValueChangeMode.EAGER);
        TextField lastName = new TextField("Last name");
        lastName.setValueChangeMode(ValueChangeMode.EAGER);
        Button clearButton = new Button("Clear fields", event -> {
            firstName.clear();
            lastName.clear();
        });
        VerticalLayout container = new VerticalLayout(firstName, lastName,
                clearButton);
        clearButton.addClickShortcut(Key.KEY_L, KeyModifier.ALT)
                .listenOn(container);

        Paragraph paragraph = new Paragraph("Button \"Clean fields\"'s "
                + "shortcut ALT+L works only within the text fields.");
        container.add(paragraph);
        shortcutButton.add(container);
        add(shortcutButton);
    }

    private void showButtonClickedMessage(ClickEvent<Button> buttonClickEvent) {
        Notification.show("Button clicked!");
    }
}
