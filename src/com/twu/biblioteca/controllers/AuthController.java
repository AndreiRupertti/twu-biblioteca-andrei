package com.twu.biblioteca.controllers;

import com.twu.biblioteca.dao.UserDAO;
import com.twu.biblioteca.models.User;
import javax.naming.AuthenticationException;

public class AuthController {

    private static User currentUser;
    private static UserDAO userDAO;

    public AuthController (UserDAO userDAO) { this.userDAO = userDAO; }

    private void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public User login(String libraryNumber, String password) throws AuthenticationException {
        User loggedUser = userDAO.getAll().stream().filter(user -> authenticate(user, libraryNumber, password)).findAny().orElse(null);
        if (loggedUser == null) throw new AuthenticationException("Wrong library number or password");
        setCurrentUser(loggedUser);

        return loggedUser;
    }

    public boolean authenticate(User user, String libraryNumber, String password) {
        return user.getLibraryNumber().equals(libraryNumber) && user.getPassword().equals(password);
    }
}
