package com.example.application.views.table;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.ArrayList;
import java.util.Objects;

public class GridButton extends HorizontalLayout {

    private final ArrayList<Button> buttons;
    private int page;

    public GridButton(ArrayList<Integer> buttonLabels, int page) {
        this.page = page;
        this.buttons = new ArrayList<>();
        setItems(buttonLabels);
        for (Button button: buttons) {
            add(button);
        }
    }

    public void setItems(ArrayList<Integer> buttonLabels) {
        if(buttons.isEmpty()) {
            for (Integer integer : buttonLabels) {
                buttons.add(new Button(String.valueOf(integer)));
                buttons.get(buttonLabels.indexOf(integer)).addClassNames("page-number-button");
            }
        }
        for (int i = 0; i < buttonLabels.size(); i++) {
            Objects.requireNonNull(buttons).get(i).setText(String.valueOf(buttonLabels.get(i)));
            if (Integer.parseInt(buttons.get(i).getText()) == page) {
                buttons.get(i).setEnabled(false);
                buttons.get(i).addClassNames("active");
            } else {
                buttons.get(i).setEnabled(true);
                buttons.get(i).removeClassName("active");
            }
        }
    }

    public ArrayList<Button> getItems() {
        return buttons;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
