package com.twu.biblioteca.view.menu;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.resources.Strings;
import com.twu.biblioteca.controllers.LibraryController;
import com.twu.biblioteca.view.PromptView;
import com.twu.biblioteca.view.View;

import java.util.*;
import java.util.List;

public class MenuView extends PromptView {
    List<MenuOption> options;
    public MenuView(Scanner scan) {
        super(scan);
        setup();
    }

    private void setup() {
        LibraryController libraryController = BibliotecaApp.getLibraryController();
        this.options = new ArrayList<>(Arrays.asList(
                new MenuOption(1, Strings.get("menu.viewListOption"), () -> { libraryController.getAvailableBooks().stream().forEach(super::show); refresh();}),
                new MenuOption(2, Strings.get("menu.rentBookOption"), () -> super.goTo(RentView.class)),
                new MenuOption(3, Strings.get("menu.devolutionBookOption"), () -> super.goTo(DevolutionView.class)),
                new MenuOption(0, Strings.get("menu.exitOption"), super::close)
        ));
    }

    @Override
    public void initialize() {
        options.stream().forEach(option -> super.show(option.toString()));
        try {
            int selectedOption = super.askAndParse(Strings.get("menu.question"), Integer::parseInt);
            if (!isValidOption(selectedOption)) throw new Exception("invalid option number");
            handleSelectedOption(selectedOption);
        } catch (Exception e) {
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

    @Override
    public void refresh() { this.goTo(this.getClass());}

}