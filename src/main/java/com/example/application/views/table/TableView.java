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

@Route(value = "table", layout = MainView.class)
@PageTitle("Table")
@CssImport("themes/vaddincomponents/views/pagination-view.css")
public class TableView extends VerticalLayout {

    private final UserService userService;

    private final Grid<User> userGrid = new Grid<>(User.class);
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

    public TableView(@Autowired UserService userService) {

        this.userService = userService;

        page = 1;
        limit = 12;
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
        lastButton = new Button(">>");
        previousButton = new Button("<");
        nextButton = new Button(">");
        gotoPage = new Button("Go to");

        setSizeFull();
        add(userGrid);
        checkStyleButton();

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(selectPage);
        horizontalLayout.add(firstButton);
        horizontalLayout.add(previousButton);
        horizontalLayout.add(pageField);
        horizontalLayout.add(gotoPage);
        horizontalLayout.add(nextButton);;
        horizontalLayout.add(lastButton);
        add(horizontalLayout);

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

    public void buttonEvent() {
        checkStyleButton();
        pageField.setValue(page);
        userGrid.setItems(userService.getUserPerPage(page, limit));
        userGrid.scrollToStart();
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
}
