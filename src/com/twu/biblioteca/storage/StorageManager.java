package com.twu.biblioteca.storage;

import com.twu.biblioteca.books.Book;
import java.util.List;

public class StorageManager {
    private Storage storage;

    public StorageManager(Storage storage) {
        this.storage = storage;
    }

    public List<Book> getBooks() { return storage.getItems(); }


}