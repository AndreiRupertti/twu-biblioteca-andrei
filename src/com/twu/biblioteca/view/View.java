package com.twu.biblioteca.view;


public interface View {
    void show(String message);
    void initialize();
    void refresh();
    void close();
}
