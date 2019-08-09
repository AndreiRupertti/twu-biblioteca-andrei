package com.twu.biblioteca.view.menu;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.books.Book;
import com.twu.biblioteca.resources.Strings;
import com.twu.biblioteca.storage.StorageManager;
import com.twu.biblioteca.view.PromptView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuView extends PromptView {
    List<MenuOption> options;
    public MenuView() {
        super(new Scanner(System.in));
        setup();
    }

    private void setup() {
        StorageManager storageManager = BibliotecaApp.getStorageManager();
        this.options = new ArrayList<>(Arrays.asList(
                new MenuOption(1, Strings.get("menu.optionOne"), () -> storageManager.getBooks().stream().forEach(super::show)),
                new MenuOption(0, Strings.get("menu.optionExit"), super::close)
        ));
    }

    public void init() {
        options.stream().forEach(option -> super.show(option.toString()));
        int selectedOption = super.askAndParse(Strings.get("menu.question"), Integer::parseInt);
        handleSelectedOption(selectedOption);
    }

    public MenuOption getMenuOption(int optionNumber) {
        return options.stream()
                .filter(option -> option.getOptionNumber() == optionNumber)
                .findFirst()
                .orElse(null);
    }

    public void handleSelectedOption(int selectedOptionNum) {
        MenuOption menuOption = getMenuOption(selectedOptionNum);
        menuOption.getHandler().apply();
    }

}