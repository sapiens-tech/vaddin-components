package com.example.application.views.form.component;

import com.example.application.views.form.model.UserDetails;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.List;

@Tag("users-list-component")
@JsModule("themes/vaddincomponents/htmltemplate/users-list-component.js")
public class UserComponent extends PolymerTemplate<UserComponent.UserDetailsModel> {

    public interface UserDetailsModel extends TemplateModel {
        @Include({ "firstname", "lastname", "handle", "email" })
        void setUsers(List<UserDetails> employees);

        List<UserDetails> getUsers();
    }

    public void setUsers(List<UserDetails> users) {
        getModel().setUsers(users);
    }

    public List<UserDetails> getUsers() {
        return getModel().getUsers();
    }
}
