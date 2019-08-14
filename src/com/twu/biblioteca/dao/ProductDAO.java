package com.twu.biblioteca.dao;

import com.twu.biblioteca.models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDAO implements BaseDAO<Product> {

    private static List<Product> ALL_PRODUCTS = new ArrayList<>(Arrays.asList(
            new Book("Things Fall Apart", 1958, "Chinua Achebe"),
            new Book("Fairy tales", 1836, "Hans Christian Andersen"),
            new Book("The Divine Comedy", 1315, "Dante Alighieri"),
            new Movie("Avengers", 2012, "Joss Whedon",7.5),
            new Movie("Avengers Infinity War", 2018, "Russo Brothers",9.0),
            new Movie("Thor Ragnarok", 2018, "Taika Watiti")
    ));

    public List<Product> getAll() {
        return ALL_PRODUCTS;
    }
}
