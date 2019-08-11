package com.twu.biblioteca.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PromptViewTest {

    @Mock private Scanner scannerMock = mock(Scanner.class);

    private PromptView promptViewWrapper;

    @Before
    public void before() {
        promptViewWrapper = spy(new PromptViewWrapper(scannerMock));
    }

    @Test
    public void shouldAskQuestionAndReturnAnswer() {
       when(scannerMock.nextLine()).thenReturn("Awesome answer");
       assertThat(promptViewWrapper.ask("Question?"), is("Awesome answer"));
       verify(promptViewWrapper, times(1)).show("Question?");
    }

    @Test
    public void shouldAskAndParseResult() {
        when(scannerMock.nextLine()).thenReturn("5");
        Integer resultAsInt = promptViewWrapper.askAndParse("Question?", Integer::parseInt);
        assertThat(resultAsInt, is(5));
    }

    @Test
    public void shouldInitGivenViewClass() {
       promptViewWrapper.goTo(PromptViewWrapper.class);
       assertTrue(PromptViewWrapper.isViewInitialized);
    }

    @Test
    public void shouldClosePromptWhenUnableToInitialize() {
        promptViewWrapper.goTo(null);
        verify(scannerMock, times(1)).close();
    }


    @Test
    public void shouldGoToCurrentViewWhenRefreshing() {
        doAnswer((param) -> null).when(promptViewWrapper).initialize();
        promptViewWrapper.refresh();
        verify(promptViewWrapper, times(1)).goTo(PromptViewWrapper.class);
    }
}