package com.twu.biblioteca.controllers;

import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Storage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LibraryController {
    private Storage storage;

    public LibraryController(Storage storage) {
        this.storage = storage;
    }

    public List<Book> getBooks() { return storage.getItems(); }

    public List<Book> getAvailableBooks() {
        return getBooks().stream()
                .filter(book -> !book.isCheckouted())
                .collect(Collectors.toList());
    }

    public Book findBook(Predicate<Book> filter) {
        return getBooks().stream()
                .filter(filter)
                .findAny()
                .orElse(null);
    }

    public Book checkoutBookByTitle(String title) {
        Book checkoutedBook = findBook(book -> book.getTitle().equals(title) && !book.isCheckouted());
        if (checkoutedBook == null) throw new NoSuchElementException("Not found");
        checkoutedBook.setCheckouted(true);
        return checkoutedBook;
    }

    public boolean returnBook(String title) {
        Book bookToBeReturned = findBook(book -> book.getTitle().equals(title) && book.isCheckouted());
        if (bookToBeReturned == null) return false;
        bookToBeReturned.setCheckouted(false);
        return true;
    }
}