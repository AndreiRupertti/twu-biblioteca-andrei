package com.twu.biblioteca.view.login;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.controllers.AuthController;
import com.twu.biblioteca.models.User;
import com.twu.biblioteca.resources.Strings;
import com.twu.biblioteca.view.InputHandler;
import com.twu.biblioteca.view.PromptView;
import com.twu.biblioteca.view.menu.MenuView;

import javax.naming.AuthenticationException;
import java.util.Scanner;

public class LoginView extends PromptView implements InputHandler {

    public LoginView(Scanner reader) {
        super(reader);
    }

    @Override
    public void initialize() {
        String username = super.ask(Strings.get("login.usernameQuestion"));
        String password = super.ask(Strings.get("login.passwordQuestion"));
        AuthController authController = BibliotecaApp.getAuthController();
        try {
           authController.login(username, password);
           successHandler();
        } catch (AuthenticationException e) {
            failureHandler();
        }
    }

    @Override
    public void successHandler() {
        super.goTo(MenuView.class);
    }

    @Override
    public void failureHandler() {
        super.show(Strings.get("login.failureMessage"));
        super.refresh();
    }
}
