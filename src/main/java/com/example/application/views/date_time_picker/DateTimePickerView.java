package com.example.application.views.date_time_picker;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDateTime;

@Route(value = "date-time-picker", layout = MainView.class)
@PageTitle("Date Time Picker")
public class DateTimePickerView extends HorizontalLayout {
    DateTimePicker labelDateTimePicker;

    public DateTimePickerView() {
        labelDateTimePicker = new DateTimePicker();
        LocalDateTime now = LocalDateTime.now();
        labelDateTimePicker.setValue(now);
        add(labelDateTimePicker);
    }
}
