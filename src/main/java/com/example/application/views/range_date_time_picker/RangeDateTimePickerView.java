package com.example.application.views.range_date_time_picker;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.Duration;
import java.time.LocalDateTime;

@Route(value = "range-date-time-picker", layout = MainView.class)
@PageTitle("Range Date Time Picker")
public class RangeDateTimePickerView extends HorizontalLayout {
    private H1 h1RangeDateTimePicker;
    private DateTimePicker startDateTimePicker, endDateTimePicker;

    public RangeDateTimePickerView() {
        addClassName("checkbox-view");
        h1RangeDateTimePicker = new H1("Range Date Time Picker");
        LocalDateTime now = LocalDateTime.now();
        startDateTimePicker = new DateTimePicker();
        endDateTimePicker = new DateTimePicker();

        startDateTimePicker.setLabel("Start date and time");
        startDateTimePicker.setStep(Duration.ofMinutes(30));
        endDateTimePicker.setStep(Duration.ofMinutes(30));
        startDateTimePicker.addValueChangeListener(e -> endDateTimePicker.setMin(e.getValue()));
//        startDateTimePicker.setValue(LocalDateTime.of(2020, 8, 25, 20, 0, 0));
        startDateTimePicker.setValue(LocalDateTime.now());
        endDateTimePicker.setLabel("Start date and time");
        endDateTimePicker.setValue(LocalDateTime.now());

        add(h1RangeDateTimePicker, startDateTimePicker, endDateTimePicker);
    }
}
