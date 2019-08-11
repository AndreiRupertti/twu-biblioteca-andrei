package com.twu.biblioteca.view.menu;


public class MenuOption {
    private int optionNumber;
    private String label;
    private OptionHandler handler;

    @Override
    public String toString() {
        return optionNumber + " - " + label;
    }

    public MenuOption(int optionNumber, String label, OptionHandler handler) {
        this.optionNumber = optionNumber;
        this.label = label;
        this.handler = handler;
    }


    public int getOptionNumber() {
        return optionNumber;
    }

    public OptionHandler getHandler() {
        return handler;
    }
}