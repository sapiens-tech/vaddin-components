package com.example.application.views.table;


import com.example.application.views.main.MainView;
import com.example.application.views.table.model.User;
import com.example.application.views.table.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Route(value = "table", layout = MainView.class)
@PageTitle("Table")
@CssImport("themes/vaddincomponents/views/pagination-view.css")
public class TableView extends VerticalLayout {

    private final UserService userService;

    private final Grid<User> userGrid = new Grid<>(User.class);
    private GridButton gridButton;
    private Select<Integer> selectPage;
    private IntegerField pageField;
    private Button firstButton;
    private Button lastButton;
    private Button previousButton;
    private Button nextButton;
    private Button gotoPage;

    private int page;
    private int limit;
    private int total;
    private int numberOfDisplayPage;

    public TableView(@Autowired UserService userService) {

        this.userService = userService;

        page = 1;
        limit = 12;
        numberOfDisplayPage = 5;
        total = userService.getTotal();

        userGrid.setSizeFull();
        userGrid.setItems(userService.getUserPerPage(page, limit));
        userGrid.setColumns("id", "firstname", "lastname", "email");

        userGrid.addItemClickListener(e -> {
            Notification.show("Item have email " + e.getItem().getEmail() + "is selected!");
        });

        selectPage = new Select<>();
        selectPage.setItems(12, 24, 36);
        selectPage.setValue(limit);
        selectPage.setHelperText("Choose number items per page");
        pageField = new IntegerField();
        pageField.setValue(page);
        pageField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        firstButton = new Button("<<");
        firstButton.addClassNames("page-number-button");
        lastButton = new Button(">>");
        lastButton.addClassNames("page-number-button");
        previousButton = new Button("<");
        previousButton.addClassNames("page-number-button");
        nextButton = new Button(">");
        nextButton.addClassNames("page-number-button");
        gotoPage = new Button("Go to");
        gridButton = new GridButton(pageChange(), page);

        setSizeFull();
        add(userGrid);
        checkStyleButton();

        HorizontalLayout selectAndGotoLayout = new HorizontalLayout();
        selectAndGotoLayout.add(selectPage);
        selectAndGotoLayout.add(pageField);
        selectAndGotoLayout.add(gotoPage);

        HorizontalLayout buttonsPageNumberLayout = new HorizontalLayout();
        buttonsPageNumberLayout.add(firstButton);
        buttonsPageNumberLayout.add(previousButton);
        buttonsPageNumberLayout.add(gridButton);
        buttonsPageNumberLayout.add(nextButton);
        buttonsPageNumberLayout.add(lastButton);

        add(selectAndGotoLayout);
        add(buttonsPageNumberLayout);

        eventButtonPage(gridButton.getItems());

        buttonClick();

        selectPage.addValueChangeListener(e -> {
            setLimit(e.getValue());
            setPage(1);
            buttonEvent();
        });
    }

    private void buttonClick() {
        firstButton.addClickListener(e -> {
            setPage(1);
            buttonEvent();
        });

        previousButton.addClickListener(e ->{
            if(!hasPrevious()) {
                setPage(page - 1);
            }
            buttonEvent();
        });

        nextButton.addClickListener(e -> {
            if(hasNext()) {
                setPage(page + 1);
            }
            buttonEvent();
        });

        lastButton.addClickListener(e -> {
            int lastPageNumber = totalPage() > 0 ? totalPage() : 1;
            setPage(lastPageNumber);
            buttonEvent();
        });

        gotoPage.addClickListener(e -> {
            int gotoPage = pageField.getValue();
            if(gotoPage > totalPage() || gotoPage < 1 ) {
                Notification.show("Sorry, out of page! limit " + totalPage());
            } else {
                setPage(gotoPage);
                buttonEvent();
            }
        });
    }

    public void eventButtonPage(ArrayList<Button> buttons) {
        for (Button button: buttons) {
            button.addClickListener(e -> {
                int pageCurrent = Integer.parseInt(button.getText());
                setPage(pageCurrent);
                buttonEvent();
            });
        }
    }

    public void buttonEvent() {
        checkStyleButton();
        pageField.setValue(page);
        gridButton.setPage(page);
        gridButton.setItems(pageChange());
        userGrid.setItems(userService.getUserPerPage(page, limit));
        userGrid.scrollToStart();
    }

    public int totalPage() {
        int totalPage = (limit == 0) ? 1 : (int) Math.ceil((double) total / (double) limit);
        return totalPage == 0 ? 1 : totalPage;
    }

    public boolean hasPrevious() {
        return page <= 1;
    }

    public boolean hasNext() {
        return page < totalPage();
    }

    public ArrayList<Integer> pageChange() {
        ArrayList<Integer> items = new ArrayList<>();
        int firstIndex = Math.max(page - (numberOfDisplayPage / 2), 1);
        int lastIndex = Math.min(Math.max(page + (numberOfDisplayPage / 2), numberOfDisplayPage), totalPage());
        if(page == totalPage() || page == (totalPage() - 1)) {
            firstIndex = totalPage() - numberOfDisplayPage + 1;
            lastIndex = totalPage();
        }
        for(int i = firstIndex; i <= lastIndex; i++) {
            items.add(i);
        }
        return items;
    }


    public void checkStyleButton() {
        if(hasPrevious()) {
            firstButton.setEnabled(false);
            previousButton.setEnabled(false);
        } else {
            firstButton.setEnabled(true);
            previousButton.setEnabled(true);
        }

        if(!hasNext()) {
            nextButton.setEnabled(false);
            lastButton.setEnabled(false);
        } else {
            nextButton.setEnabled(true);
            lastButton.setEnabled(true);
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNumberOfDisplayPage() {
        return numberOfDisplayPage;
    }

    public void setNumberOfDisplayPage(int numberOfDisplayPage) {
        this.numberOfDisplayPage = numberOfDisplayPage;
    }

    public GridButton getGridButton() {
        return gridButton;
    }

    public void setGridButton(GridButton gridButton) {
        this.gridButton = gridButton;
    }
}
