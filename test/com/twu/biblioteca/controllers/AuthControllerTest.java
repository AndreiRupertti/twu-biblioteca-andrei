package com.twu.biblioteca.controllers;

import com.twu.biblioteca.dao.UserDAO;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Movie;
import com.twu.biblioteca.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthControllerTest {
    private AuthController authController;
    @Mock UserDAO mockUserDAO;
    List<User> stubUsers = new ArrayList<>(Arrays.asList(
            new User("111-2222", "123456"),
            new User("333-4444", "654321")
    ));

    @Before
    public void before() {
        mockUserDAO = mock(UserDAO.class);
        when(mockUserDAO.getAll()).thenReturn(stubUsers);
        authController = new AuthController(mockUserDAO);
    }

    @Test
    public void shouldReturnTrueToAuthenticateUserWithRightPasswordAndUsername() {
        assertTrue(authController.authenticate(stubUsers.get(0), "111-2222", "123456"));
    }

    @Test
    public void shouldReturnFalseToAuthenticateUserWithWrongPassword() {
        assertFalse(authController.authenticate(stubUsers.get(0), "111-2222", "wrong-password"));
    }

    @Test
    public void shouldReturnFalseToAuthenticateUserWithWrongUsername() {
        assertFalse(authController.authenticate(stubUsers.get(0), "wrong-username", "123456"));
    }

    @Test(expected = AuthenticationException.class)
    public void shouldThrowAuthenticationExceptionWithInvalidLogin() throws AuthenticationException{
        authController.login("wrong-username", "wrong-password");
    }

    @Test
    public void shouldReturnUserWhenLoginIsValid() throws AuthenticationException {
        User user = authController.login("111-2222", "123456");
        assertThat(user, is(stubUsers.get(0)));
    }

    @Test
    public void shouldSetUserAsCurrentAfterValidLogin() throws AuthenticationException {
        User user = authController.login("111-2222", "123456");
        assertThat(AuthController.getCurrentUser(), is(user));
    }

}