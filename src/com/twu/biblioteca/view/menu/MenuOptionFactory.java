package com.twu.biblioteca.view.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuOptionFactory {

    public static MenuOption option(Integer num, String label, OptionHandler handler) {
        return new MenuOption(num, label, handler);
    }

    public static List<MenuOption> getOptionsList(MenuOption... options) {
        return new ArrayList<>(Arrays.asList(options));
    }
}
