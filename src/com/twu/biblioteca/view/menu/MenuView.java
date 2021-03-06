package com.twu.biblioteca.view.menu;

import com.twu.biblioteca.resources.Strings;
import com.twu.biblioteca.view.PromptView;
import com.twu.biblioteca.view.products.*;
import com.twu.biblioteca.view.user.UserProfileView;

import static com.twu.biblioteca.view.menu.MenuOptionFactory.*;

import java.util.*;
import java.util.List;


public class MenuView extends PromptView {
    private List<MenuOption> options;

    public MenuView(Scanner scan) {
        super(scan);
        this.options = getOptionsList(
                option(1, Strings.get("menu.viewBookListOption"), () -> super.goTo(BookListView.class)),
                option(2, Strings.get("menu.viewMovieListOption"), () -> super.goTo(MovieListView.class)),
                option(3, Strings.get("menu.rentBookOption"), () -> super.goTo(RentView.class)),
                option(4, Strings.get("menu.devolutionBookOption"), () -> super.goTo(DevolutionView.class)),
                option(5, Strings.get("menu.viewRentedProducts"), () -> super.goTo(UserProductsView.class)),
                option(6, Strings.get("menu.userInformation"), () -> super.goTo(UserProfileView.class)),
                option(0, Strings.get("menu.exitOption"), super::close)
        );
    }

    @Override
    public void initialize() {
        options.stream().forEach(option -> super.show(option.toString()));
        try {
            int selectedOption = super.askAndParse(Strings.get("menu.question"), Integer::parseInt);
            if (!isValidOption(selectedOption)) throw new Exception("invalid option number");
            handleSelectedOption(selectedOption);
        } catch (Exception e) {
            e.printStackTrace();
            handleInvalidAnswer();
        }
    }


    public MenuOption getMenuOption(int optionNumber) {
        return options.stream()
                .filter(option -> option.getOptionNumber() == optionNumber)
                .findFirst()
                .orElse(null);
    }

    public void handleSelectedOption(int selectedOption) {
        MenuOption menuOption = getMenuOption(selectedOption);
        menuOption.getHandler().apply();
    }

    public boolean isValidOption(int selectedOption) { return options.stream().anyMatch(option -> option.getOptionNumber() == selectedOption); }


    public void handleInvalidAnswer() {
        super.show(Strings.get("menu.invalidAnswer"));
        super.show("\n");
        refresh();
    }


}