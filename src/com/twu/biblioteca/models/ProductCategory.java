package com.twu.biblioteca.models;

public enum ProductCategory {
    MOVIE("Movie"),
    BOOK("Book");
    private final String label;

    ProductCategory(final String label) {
        this.label = label;
    }

    public String getValue() { return label; }
    @Override
    public String toString() {
        return label;
    }

}
