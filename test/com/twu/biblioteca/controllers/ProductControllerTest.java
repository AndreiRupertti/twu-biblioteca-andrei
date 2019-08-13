package com.twu.biblioteca.controllers;

import com.twu.biblioteca.models.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class ProductControllerTest {
    @Mock Storage storage;
    private ProductController productController;
    private ArrayList<Product> stubBookList;
    private ArrayList<Product> stubMoviesList;
    private ArrayList<Product> stubFullProducts;
    @Before
    public void before() {
        storage = mock(Storage.class);
        stubFullProducts = new ArrayList<>(Arrays.asList(
                new Book("Book 1", 1999, "Some Author"),
                new Book("Book 2", 1999, "Some Author"),
                new Movie("Movie 1", 1999, "Some Director", 8.0),
                new Movie("Movie 2", 1999, "Some Director"))
        );

        stubBookList = new ArrayList<>(Arrays.asList(stubFullProducts.get(0), stubFullProducts.get(1)));
        stubMoviesList = new ArrayList<>(Arrays.asList(stubFullProducts.get(2), stubFullProducts.get(3)));
        when(storage.getItems()).thenReturn(stubFullProducts);
        productController = new ProductController(storage);
    }

    @Test
    public void shouldGetProductsByCategoryBook() {
        assertThat(productController.getProductsByCategory(ProductCategory.BOOK), is(stubBookList));
        verify(storage, times(1)).getItems();
    }
    @Test
    public void shouldGetProductsByCategoryMovie() {
        assertThat(productController.getProductsByCategory(ProductCategory.MOVIE), is(stubMoviesList));
        verify(storage, times(1)).getItems();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldNotRentTheSameProductTwice() {
        Integer prodCod = stubFullProducts.get(0).getProductCod();
        productController.rentProductByCod(prodCod);
        productController.rentProductByCod(prodCod);
    }

    @Test
    public void shouldCheckProductAsRented() {
        Integer prodCod = stubFullProducts.get(0).getProductCod();
        Product product = productController.rentProductByCod(prodCod);
        assertThat(product.isRented(), is(true));
    }

    @Test
    public void shouldNotReturnProductThatIsNotRented() {
        Integer prodCod = stubFullProducts.get(0).getProductCod();
        assertThat(productController.returnProductByCod(prodCod), is(false));
    }

    @Test
    public void WhenReturningProductShouldSetAsNotRented() {
        stubFullProducts.get(0).setRented(true);
        Integer prodCod = stubFullProducts.get(0).getProductCod();
        assertThat(productController.returnProductByCod(prodCod), is(true));
        assertThat(stubFullProducts.get(0).isRented(), is(false));
    }
}