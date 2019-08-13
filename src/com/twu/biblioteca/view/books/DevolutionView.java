package com.twu.biblioteca.view.books;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.controllers.ProductController;
import com.twu.biblioteca.models.ProductCategory;
import com.twu.biblioteca.resources.Strings;
import com.twu.biblioteca.view.PromptView;
import com.twu.biblioteca.view.InputHandler;
import com.twu.biblioteca.view.menu.MenuView;

import java.util.Scanner;

public class DevolutionView extends PromptView implements InputHandler {

    public DevolutionView(Scanner scan) {
        super(scan);
    }

    @Override
    public void initialize() {
        ProductController bookController = BibliotecaApp.getProductController();
        if (returnBook(bookController)) successHandler();
        else failureHandler();
    }

    public void successHandler() {
        super.show(Strings.get("devolution.successMessage"));
        super.goTo(MenuView.class);
    }

    public void failureHandler() {
        super.show(Strings.get("devolution.failureMessage"));
        refresh();
    }

    private boolean returnBook(ProductController bookController) {
        Integer bookCod = super.askAndParse(Strings.get("devolution.question"), Integer::parseInt);
        return bookController.returnProductByCod(bookCod);
    }

}
