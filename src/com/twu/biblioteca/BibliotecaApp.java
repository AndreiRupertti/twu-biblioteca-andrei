package com.twu.biblioteca;

import com.twu.biblioteca.books.Book;
import com.twu.biblioteca.resources.Strings;
import com.twu.biblioteca.storage.Storage;
import com.twu.biblioteca.storage.StorageManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println(Strings.get("app.welcomeMessage"));
        StorageManager storageManager = new StorageManager(getFakeStorage());
        storageManager.getBooks().stream().forEach((book) -> System.out.println(
                book.getTitle() + " - " +
                book.getYear() + " - " +
                book.getAuthor()
        ));
    }

    public static Storage getFakeStorage() {
        List<Book> items = new ArrayList<>();
        items.addAll(Arrays.asList(
                new Book("Things Fall Apart", 1958, "Chinua Achebe"),
                new Book("Fairy tales", 1836, "Hans Christian Andersen"),
                new Book("The Divine Comedy", 1315, "Dante Alighieri")));

        return new Storage(items);
    }}
