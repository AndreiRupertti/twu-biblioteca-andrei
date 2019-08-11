package com.twu.biblioteca.view.menu;

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
        verify(devolutionView, times(1)).initialize();
    }

    @Test
    public void shouldGoToMenuViewOnSuccess() {
        devolutionView.successHandler();
        verify(devolutionView, times(1)).show(any(String.class));
        verify(devolutionView, times(1)).goTo(MenuView.class);
    }

    @Test
    public void shouldGoToCurrentViewWhenRefreshing() {
        doAnswer((param) -> null).when(devolutionView).initialize();
        devolutionView.refresh();
        verify(devolutionView, times(1)).goTo(DevolutionView.class);
    }


}