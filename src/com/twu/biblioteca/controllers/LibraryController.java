package com.twu.biblioteca.controllers;

import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class LibraryController {
    private Storage storage;

    public LibraryController(Storage storage) {
        this.storage = storage;
    }

    public List<Book> getBooks() { return storage.getItems(); }

    public List<Book> getAvailableBooks() {
        return getBooks().stream()
                .filter(book -> !book.isRented())
                .collect(Collectors.toList());
    }

    public Book getBookByTitle(String title) {
        return getBooks().stream()
                .filter(book -> book.getTitle().equals(title))
                .findAny()
                .orElse(null);
    }

    public Book rentBookByTitle(String title) {
        for (Book book : getAvailableBooks()) {
            if (book.getTitle().equals(title) && !book.isRented()) {
                book.setRented(true);
                System.out.println(getAvailableBooks());
                return book;
            }
        }

        throw new NoSuchElementException("Book not found");
    }
}