package com.twu.biblioteca.models;

import java.util.Objects;

public class Book {
    private String title;
    private int year;
    private String author;
    private boolean rented;

    public Book(String title, int year, String author) {
        this.title = title;
        this.year = year;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    @Override
    public String toString() {
        return title + " (" + year + ") - By: " + author + "Rented:"+isRented();
    }
}
