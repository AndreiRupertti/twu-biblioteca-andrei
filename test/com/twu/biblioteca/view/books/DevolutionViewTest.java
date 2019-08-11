package com.twu.biblioteca.view.books;

import com.twu.biblioteca.view.books.DevolutionView;
import com.twu.biblioteca.view.menu.MenuView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Scanner;

import static org.mockito.Mockito.*;

public class DevolutionViewTest {
    @Mock
    private Scanner scannerMock = mock(Scanner.class);
    DevolutionView devolutionView;

    @Before
    public void before() {
        devolutionView = spy(new DevolutionView(scannerMock));
        doAnswer((param) -> null).when(devolutionView).goTo(any(Class.class));
    }

    @Test
    public void shouldCallInitializeOnFailure() {
        doAnswer((param) -> null)
        .when(devolutionView).initialize();
        devolutionView.failureHandler();

        verify(devolutionView, times(1)).show(any(String.class));
        verify(devolutionView, times(1)).refresh();
    }

    @Test
    public void shouldGoToMenuViewOnSuccess() {
        devolutionView.successHandler();
        verify(devolutionView, times(1)).show(any(String.class));
        verify(devolutionView, times(1)).goTo(MenuView.class);
    }


}