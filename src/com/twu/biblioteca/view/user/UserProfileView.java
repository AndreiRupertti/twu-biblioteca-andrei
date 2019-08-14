package com.twu.biblioteca.view.user;

import com.twu.biblioteca.controllers.AuthController;
import com.twu.biblioteca.models.User;
import com.twu.biblioteca.resources.Strings;
import com.twu.biblioteca.view.PromptView;
import com.twu.biblioteca.view.menu.MenuView;

import java.util.Scanner;

public class UserProfileView extends PromptView {

    public UserProfileView(Scanner reader) {
        super(reader);
    }

    @Override
    public void initialize() {
        User user = AuthController.getCurrentUser();
        super.show(Strings.get("user.profileBox", user.toString()));
        super.goTo(MenuView.class);
    }
}
