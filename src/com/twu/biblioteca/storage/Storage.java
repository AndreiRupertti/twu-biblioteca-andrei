package com.twu.biblioteca.storage;

import com.twu.biblioteca.books.Book;

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
