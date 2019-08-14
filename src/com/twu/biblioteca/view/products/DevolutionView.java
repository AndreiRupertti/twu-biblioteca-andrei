package com.twu.biblioteca.view.products;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.controllers.AuthController;
import com.twu.biblioteca.controllers.ProductController;
import com.twu.biblioteca.models.User;
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
        User user =  AuthController.getCurrentUser();
        ProductController productController = BibliotecaApp.getProductController();
        if (returnProduct(productController, user)) successHandler();
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

    private boolean returnProduct(ProductController productController, User user) {
        Integer productCod = super.askAndParse(Strings.get("devolution.question"), Integer::parseInt);
        return productController.returnProductByCod(productCod, user);
    }

}
