package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Movie;
import com.twu.biblioteca.resources.Strings;
import com.twu.biblioteca.models.Storage;
import com.twu.biblioteca.controllers.ProductController;
import com.twu.biblioteca.view.menu.MenuView;

import java.util.Arrays;
import java.util.Scanner;

public class BibliotecaApp {

    private static Storage storage = new Storage(Arrays.asList(
            new Book("Things Fall Apart", 1958, "Chinua Achebe"),
            new Book("Fairy tales", 1836, "Hans Christian Andersen"),
            new Book("The Divine Comedy", 1315, "Dante Alighieri"),
            new Movie("Avengers", 2012, "Joss Whedon",7.5),
            new Movie("Avengers Infinity War", 2018, "Russo Brothers",9.0),
            new Movie("Thor Ragnarok", 2018, "Taika Watiti")
    ));

    public static void main(String[] args) {
        System.out.println(Strings.get("welcomeMessage"));
        MenuView menuView = new MenuView(new Scanner(System.in));
        menuView.initialize();
    }

    public static Storage getFakeStorage() {
        return storage;
    }

    public static ProductController getProductController() {
        return new ProductController(getFakeStorage());
    }

}
