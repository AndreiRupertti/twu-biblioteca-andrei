package com.twu.biblioteca.resources;

import org.junit.Test;

import java.util.ResourceBundle;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StringsTest {

    private ResourceBundle fakeBundle = mock(ResourceBundle.class);

    private Strings StringsSpy = spy(Strings.class);

    @Test
    public void shouldReturnEmptyStringWhenKeyIsNotFound() {
        assertThat(StringsSpy.get("invalid.key"), is(""));
    }

}