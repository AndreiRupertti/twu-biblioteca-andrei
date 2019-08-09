package com.twu.biblioteca.view.menu;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.controllers.LibraryController;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.resources.Strings;
import com.twu.biblioteca.view.PromptView;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class RentView extends PromptView implements InputHandler {

    public RentView(Scanner scan) {
        super(scan);
    }

    @Override
    public void initialize() {
        LibraryController libraryController = BibliotecaApp.getLibraryController();
        try {
            Book book = checkoutBook(libraryController);
            successHandler();
        } catch (NoSuchElementException e) {
            failureHandler();
        }
    }

    @Override
    public void refresh() { this.goTo(this.getClass());}

    public void successHandler() {
        super.show(Strings.get("rent.successMessage"));
        super.goTo(MenuView.class);
    }

    public void failureHandler() {
        super.show(Strings.get("rent.failureMessage"));
        initialize();
    }

    private Book checkoutBook(LibraryController libraryController) {
        String bookTitle = super.ask(Strings.get("rent.question"));
        return libraryController.checkoutBookByTitle(bookTitle);
    }
}