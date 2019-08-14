package com.twu.biblioteca.view.menu;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Scanner;
import java.util.function.Function;

import static org.mockito.Mockito.*;

public class MenuViewTest {

    @Mock
    private Scanner scannerMock = mock(Scanner.class);

    private MenuView menuView;

    @Before
    public void before() {
        menuView = spy(new MenuView(scannerMock));
        doAnswer((i) -> null).when(menuView).show(anyString());
    }

    @Test
    public void shouldHandleInvalidAnswer() {
        doAnswer((i) -> -1).when(menuView).askAndParse(anyString(), any(Function.class));
        doAnswer((i) -> null).when(menuView).handleInvalidAnswer();

        menuView.initialize();
        verify(menuView, times(1)).handleInvalidAnswer();
    }

    @Test
    public void shouldHandleValidAnswer() {
        doAnswer((i) -> 1).when(menuView).askAndParse(anyString(), any(Function.class));
        doAnswer((i) -> null).when(menuView).handleSelectedOption(anyInt());

        menuView.initialize();
        verify(menuView, times(1)).handleSelectedOption(1);
    }

}