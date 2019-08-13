package com.twu.biblioteca.controllers;

import com.twu.biblioteca.models.Product;
import com.twu.biblioteca.models.ProductCategory;
import com.twu.biblioteca.models.Storage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductController {
    private Storage storage;

    public ProductController(Storage storage) {
        this.storage = storage;
    }

    public List<Product> getProductsByCategory(ProductCategory category) {
        return storage.getItems().stream()
                .filter(item -> item.getCategory() == category)
                .collect(Collectors.toList());
    }

    public <T extends Product> List<T> getAvailableProductsByCategory(ProductCategory category) {
        return getProductsByCategory(category).stream()
                .map(product -> (T) product)
                .filter(product -> !product.isRented())
                .collect(Collectors.toList());
    }

    public Product findProductWhere(Predicate<Product> filter) {
        return this.storage.getItems().stream()
                .filter(filter)
                .findAny()
                .orElse(null);
    }

    public Product rentProductByCod(Integer productCod) {
        Product product = findProductWhere((prod) -> prod.getProductCod() == productCod && !prod.isRented());
        if (product == null) throw new NoSuchElementException("Invalid product to rent");
        product.setRented(true);
        return product;
    }

    public boolean returnProductByCod(Integer productCod) {
        Product product = findProductWhere((prod) -> prod.getProductCod() == productCod && prod.isRented());
        if (product == null) return false;
        product.setRented(false);
        return true;
    }

}