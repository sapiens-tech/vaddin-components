package com.example.application;

import com.example.application.views.table.model.User;
import com.example.application.views.table.TableView;
import com.example.application.views.table.service.UserService;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.vaadin.artur.helpers.LaunchUtil;
import com.vaadin.flow.theme.Theme;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "vaddincomponents")
@PWA(name = "Vaddin Components", shortName = "Vaddin Components", offlineResources = {"images/logo.png"})
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

    public static void main(String[] args) {
        for(int i = 0; i < 240; i++) {
            User user = new User((long) (i + 1), i + "firstname", i + "lastname", "member" + i + "@sapiens.com");
            UserService.users.add(user);
        }
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
    }

}
