package com.twu.biblioteca.view.books;

import com.twu.biblioteca.view.books.RentView;
import com.twu.biblioteca.view.menu.MenuView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RentViewTest {

    @Mock
    private Scanner scannerMock = mock(Scanner.class);
    RentView rentView;

    @Before
    public void before() {
        rentView = spy(new RentView(scannerMock));
        doAnswer((param) -> null).when(rentView).goTo(any(Class.class));
    }

    @Test
    public void shouldCallInitializeOnFailure() {
        doAnswer((param) -> null).when(rentView).initialize();
        rentView.failureHandler();

        verify(rentView, times(1)).show(any(String.class));
        verify(rentView, times(1)).refresh();
    }

    @Test
    public void shouldGoToMenuViewOnSuccess() {
        rentView.successHandler();
        verify(rentView, times(1)).show(any(String.class));
        verify(rentView, times(1)).goTo(MenuView.class);
    }


}