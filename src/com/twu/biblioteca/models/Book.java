package com.twu.biblioteca.models;

public class Book {
    private String title;
    private int year;
    private String author;
    private boolean checkouted;

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

    public boolean isCheckouted() {
        return checkouted;
    }

    public void setCheckouted(boolean checkouted) {
        this.checkouted = checkouted;
    }

    @Override
    public String toString() {
        return title + " (" + year + ") - By: " + author;
    }
}
