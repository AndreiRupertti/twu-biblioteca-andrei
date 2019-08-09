package com.twu.biblioteca.view.menu;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.controllers.LibraryController;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.resources.Strings;
import com.twu.biblioteca.view.PromptView;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class RentView extends PromptView {

    public RentView(Scanner scan) {
        super(scan);
    }

    @Override
    public void initialize() {
        LibraryController libraryController = BibliotecaApp.getLibraryController();
        try {
            Book book = rentBook(libraryController);
            successHandler();
        } catch (NoSuchElementException e) {
            failureHandler();
        }
    }

    @Override
    public void refresh() { this.goTo(this.getClass());}

    private Book rentBook(LibraryController libraryController) {
        String bookTitle = super.ask(Strings.get("rent.question"));
        return libraryController.rentBookByTitle(bookTitle);
    }

    private void successHandler() {
        super.show(Strings.get("rent.successMessage"));
        super.goTo(MenuView.class);
    }

    private void failureHandler() {
        super.show(Strings.get("rent.failureMessage"));
        initialize();
    }

}
