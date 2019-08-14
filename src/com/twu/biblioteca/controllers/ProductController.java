package com.twu.biblioteca.controllers;

import com.twu.biblioteca.dao.ProductDAO;
import com.twu.biblioteca.models.Product;
import com.twu.biblioteca.models.ProductCategory;
import com.twu.biblioteca.models.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductController {
    private ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getProductsByCategory(ProductCategory category) {
        return productDAO.getAll().stream()
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
        return productDAO.getAll().stream()
                .filter(filter)
                .findAny()
                .orElse(null);
    }

    public List<Product> getProductsRentedByUser(User user) {
        return productDAO.getAll().stream()
                .filter(product -> user.equals(product.getRentedBy()))
                .collect(Collectors.toList());
    }

    public Product rentProductByCod(Integer productCod, User rentedBy) {
        Product product = findProductWhere((prod) -> prod.getProductCod() == productCod && !prod.isRented());
        if (product == null) throw new NoSuchElementException("Invalid product to rent");
        product.setRentedBy(rentedBy);
        return product;
    }

    public boolean returnProductByCod(Integer productCod, User returnedBy) {
        Product product = findProductWhere((prod) -> prod.getProductCod() == productCod && returnedBy.equals(prod.getRentedBy()));
        if (product == null) return false;
        product.setRentedBy(null);
        return true;
    }

}