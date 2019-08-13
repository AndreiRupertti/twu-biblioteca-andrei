package com.twu.biblioteca.models;


import java.util.List;

public class Storage {
    List<Product> items;


    public Storage(List<Product> items) {
        this.items = items;
    }

    public List<Product> getItems() {
        return items;
    }

}
