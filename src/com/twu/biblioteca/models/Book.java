package com.twu.biblioteca.models;

public class Book extends Product {
    private String title;
    private int year;
    private String author;

    public Book(String title, int year, String author) {
        super(ProductCategory.valueOf("BOOK"));
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

    @Override
    public String toString() {
        return getProductCod() + " - " + title + " (" + year + ") - By: " + author;
    }
}
