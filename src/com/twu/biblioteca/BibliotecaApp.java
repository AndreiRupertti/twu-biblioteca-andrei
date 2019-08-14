package com.twu.biblioteca;

import com.twu.biblioteca.controllers.AuthController;
import com.twu.biblioteca.dao.ProductDAO;
import com.twu.biblioteca.dao.UserDAO;
import com.twu.biblioteca.resources.Strings;
import com.twu.biblioteca.controllers.ProductController;
import com.twu.biblioteca.view.login.LoginView;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println(Strings.get("welcomeMessage"));
        LoginView loginView = new LoginView(new Scanner(System.in));
        loginView.initialize();
    }

    public static ProductController getProductController() {
        return new ProductController(new ProductDAO());
    }

    public static AuthController getAuthController() {
        return new AuthController(new UserDAO());
    }

}
