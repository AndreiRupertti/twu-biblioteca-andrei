package com.twu.biblioteca.models;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Product {
    private static final AtomicInteger atomicInteger = new AtomicInteger(100);
    private Integer productCod;
    private User rentedBy;
    private ProductCategory category;

    public Product(ProductCategory category) {
        productCod = atomicInteger.getAndIncrement();
        this.category = category;
    }


    public Integer getProductCod() {
        return productCod;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setRentedBy(User rentedBy) {
        this.rentedBy = rentedBy;
    }

    public User getRentedBy() {
        return rentedBy;
    }

    public boolean isRented() {
        return rentedBy != null;
    }
}
