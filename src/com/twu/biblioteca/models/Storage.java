package com.twu.biblioteca.models;

import com.twu.biblioteca.models.Book;

import java.util.List;

public class Storage {
    List<Book> items;


    public Storage(List<Book> items) {
        this.items = items;
    }

    public List<Book> getItems() {
        return items;
    }

    public Book updateItem(Book item) {
        return this.items.set(this.items.indexOf(item), item);
    }
}
