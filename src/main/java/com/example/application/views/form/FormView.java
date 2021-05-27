package com.example.application.views.form;

import com.example.application.views.form.component.AvatarField;
import com.example.application.views.form.component.UserComponent;
import com.example.application.views.form.model.UserDetails;
import com.example.application.views.form.service.UserDetailsService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

@Route(value = "form-base", layout = MainView.class)
@PageTitle("Form Base")
public class FormView extends VerticalLayout {

    private Checkbox allowEmailBox;
    private PasswordField passwordField1;
    private PasswordField passwordField2;

    private UserDetailsService service;
    private BeanValidationBinder<UserDetails> binder;
    private List<UserDetails> users = new ArrayList();
    private UserComponent userComponent = new UserComponent();
    private boolean enablePasswordValidation;

    public FormView(@Autowired UserDetailsService service) {

        this.service = service;

        H3 title = new H3("Signup form");

        TextField firstnameField = new TextField("First name");
        TextField lastnameField = new TextField("Last name");
        TextField handleField = new TextField("User handle");

        AvatarField avatarField = new AvatarField("Select Avatar image");

        allowEmailBox = new Checkbox("Email?");
        allowEmailBox.getStyle().set("padding-top", "10px");
        EmailField emailField = new EmailField("Email");
        emailField.setVisible(false);

        passwordField1 = new PasswordField("Wanted password");
        passwordField2 = new PasswordField("Password again");

        Span errorMessage = new Span();

        Button submitButton = new Button("Adding user");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        FormLayout formLayout = new FormLayout(title, firstnameField, lastnameField, handleField, avatarField, passwordField1, passwordField2,
                allowEmailBox, emailField, errorMessage, submitButton);

        formLayout.setMaxWidth("500px");
        formLayout.getStyle().set("margin", "0 auto");

        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("490px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        formLayout.setColspan(title, 2);
        formLayout.setColspan(avatarField, 2);
        formLayout.setColspan(errorMessage, 2);
        formLayout.setColspan(submitButton, 2);

        errorMessage.getStyle().set("color", "var(--lumo-error-text-color)");
        errorMessage.getStyle().set("padding", "15px 0");

        add(formLayout);

        binder = new BeanValidationBinder<>(UserDetails.class);
        binder.forField(firstnameField).asRequired().bind("firstname");
        binder.forField(lastnameField).asRequired().bind("lastname");
        binder.forField(handleField).withValidator(this::validateHandle).asRequired().bind("handle");
        binder.forField(avatarField).bind("avatar");
        binder.forField(allowEmailBox).bind("allowsEmail");
        binder.forField(emailField).asRequired(new VisibilityEmailValidator("Value is not a valid email address")).bind("email");
        allowEmailBox.addValueChangeListener(e -> {
            emailField.setVisible(allowEmailBox.getValue());
            if (!allowEmailBox.getValue()) {
                emailField.setValue("No email");
            }
        });
        binder.forField(passwordField1).asRequired().withValidator(this::passwordValidator).bind("password");

        passwordField2.addValueChangeListener(e -> {
            enablePasswordValidation = true;
            binder.validate();
        });

        binder.setStatusLabel(errorMessage);

        submitButton.addClickListener(e -> {
            try {
                UserDetails detailsBean = new UserDetails();
                binder.writeBean(detailsBean);
                service.store(detailsBean);
                users.add(detailsBean);
                userComponent.setUsers(users);
                showSuccess(detailsBean);
            } catch (ValidationException ignored) {
            } catch (UserDetailsService.ServiceException e2) {
                e2.printStackTrace();
                errorMessage.setText("Saving the data failed, please try again");
            }
        });
        userComponent.setUsers(users);
        add(userComponent);
    }

    private void showSuccess(UserDetails detailsBean) {
        Notification notification = Notification.show("Successfully, welcome " + detailsBean.getHandle() + "!");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

    private ValidationResult passwordValidator(String pass1, ValueContext ctx) {
        if (pass1 == null || pass1.length() < 8) {
            return ValidationResult.error("Password should be at least 8 characters long");
        }
        if (!enablePasswordValidation) {
            enablePasswordValidation = true;
            return ValidationResult.ok();
        }
        String pass2 = passwordField2.getValue();
        if (pass1.equals(pass2)) {
            return ValidationResult.ok();
        }
        return ValidationResult.error("Passwords do not match");
    }

    private ValidationResult validateHandle(String handle, ValueContext ctx) {
        String errorMsg = service.validateHandle(handle);
        if (errorMsg == null) {
            return ValidationResult.ok();
        }
        return ValidationResult.error(errorMsg);
    }

    public class VisibilityEmailValidator extends EmailValidator {
        public VisibilityEmailValidator(String errorMessage) {
            super(errorMessage);
        }

        @Override
        public ValidationResult apply(String value, ValueContext context) {
            if (!allowEmailBox.getValue()) {
                return ValidationResult.ok();
            } else {
                return super.apply(value, context);
            }
        }
    }
}
