package com.twu.biblioteca.view.menu;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.controllers.LibraryController;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.resources.Strings;
import com.twu.biblioteca.view.PromptView;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class DevolutionView extends PromptView implements InputHandler {

    public DevolutionView(Scanner scan) {
        super(scan);
    }

    @Override
    public void initialize() {
        LibraryController libraryController = BibliotecaApp.getLibraryController();
        if (returnBook(libraryController)) successHandler();
        else failureHandler();
    }

    @Override
    public void refresh() { this.goTo(this.getClass());}

    public void successHandler() {
        super.show(Strings.get("devolution.successMessage"));
        super.goTo(MenuView.class);
    }

    public void failureHandler() {
        super.show(Strings.get("devolution.failureMessage"));
        initialize();
    }

    private boolean returnBook(LibraryController libraryController) {
        String bookTitle = super.ask(Strings.get("devolution.question"));
        return libraryController.returnBook(bookTitle);
    }

}