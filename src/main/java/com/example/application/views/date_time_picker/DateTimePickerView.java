package com.example.application.views.date_time_picker;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDateTime;

@Route(value = "date-time-picker", layout = MainView.class)
@PageTitle("Date Time Picker")
public class DateTimePickerView extends HorizontalLayout {
    private H1 h1DateTimePicker;
    private DateTimePicker labelDateTimePicker;

    public DateTimePickerView() {
        addClassName("checkbox-view");
        h1DateTimePicker = new H1("Date Time Picker");
        labelDateTimePicker = new DateTimePicker();
        LocalDateTime now = LocalDateTime.now();
        labelDateTimePicker.setValue(now);
        add(h1DateTimePicker, labelDateTimePicker);
    }
}
