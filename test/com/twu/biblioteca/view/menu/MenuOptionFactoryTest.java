package com.twu.biblioteca.view.menu;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MenuOptionFactoryTest {

    @Test
    public void shouldReturnInstanceOfMenuOption() {
        MenuOption menuOption = MenuOptionFactory.option(1, "Label", () -> {});
        assertTrue(menuOption instanceof MenuOption);
        assertThat(menuOption.getOptionNumber(), is(1));
    }

    @Test
    public void shouldReturnListOfGivenOptions() {
        MenuOption option1 = MenuOptionFactory.option(1, "Label 1", () -> {});
        MenuOption option2 = MenuOptionFactory.option(2, "Label 2", () -> {});
        List<MenuOption> optionList = MenuOptionFactory.getOptionsList(option1, option2);
        assertThat(optionList.get(0), is(option1));
        assertThat(optionList.get(1), is(option2));
    }
}