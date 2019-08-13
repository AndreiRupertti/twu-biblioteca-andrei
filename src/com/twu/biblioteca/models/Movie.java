package com.twu.biblioteca.models;

import static java.lang.Double.NaN;
import static java.lang.Double.isNaN;

public class Movie extends Product {
    private String name;
    private int year;
    private String director;
    private Double rating;

    public Movie(String name, int year, String director, Double rating) {
        super(ProductCategory.valueOf("MOVIE"));
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public Movie(String name, int year, String director) {
        this(name, year, director, NaN);
    }

    public String getRating() {
        if (isNaN(rating)) return "Unrated";
        else return rating.toString();
    }
    @Override
    public String toString() {
        return ""+name+" ("+year+") - Directed By: " + director + "|| Rating: "+ getRating();
    }
}
