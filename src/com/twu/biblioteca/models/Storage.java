package com.twu.biblioteca.models;


import java.util.List;

public class Storage {
    List<Book> items;


    public Storage(List<Book> items) {
        this.items = items;
    }

    public List<Book> getItems() {
        return items;
    }

}
