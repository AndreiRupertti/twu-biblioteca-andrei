package com.twu.biblioteca.controllers;

import com.twu.biblioteca.dao.ProductDAO;
import com.twu.biblioteca.models.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class ProductControllerTest {
    @Mock ProductDAO mockProductDAO;
    @Mock User mockUser;
    private ProductController productController;
    private ArrayList<Product> stubBookList;
    private ArrayList<Product> stubMoviesList;
    private ArrayList<Product> stubFullProducts;

    @Before
    public void before() {
        mockProductDAO = mock(ProductDAO.class);
        mockUser = mock(User.class);
        stubFullProducts = new ArrayList<>(Arrays.asList(
                new Book("Book 1", 1999, "Some Author"),
                new Book("Book 2", 1999, "Some Author"),
                new Movie("Movie 1", 1999, "Some Director", 8.0),
                new Movie("Movie 2", 1999, "Some Director"))
        );

        stubBookList = new ArrayList<>(Arrays.asList(stubFullProducts.get(0), stubFullProducts.get(1)));
        stubMoviesList = new ArrayList<>(Arrays.asList(stubFullProducts.get(2), stubFullProducts.get(3)));
        when(mockProductDAO.getAll()).thenReturn(stubFullProducts);
        productController = new ProductController(mockProductDAO);
    }

    @Test
    public void shouldGetProductsByCategoryBook() {
        assertThat(productController.getProductsByCategory(ProductCategory.BOOK), is(stubBookList));
        verify(mockProductDAO, times(1)).getAll();
    }
    @Test
    public void shouldGetProductsByCategoryMovie() {
        assertThat(productController.getProductsByCategory(ProductCategory.MOVIE), is(stubMoviesList));
        verify(mockProductDAO, times(1)).getAll();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldNotRentTheSameProductTwice() {
        Integer prodCod = stubFullProducts.get(0).getProductCod();
        productController.rentProductByCod(prodCod, mockUser);
        productController.rentProductByCod(prodCod, mockUser);
    }

    @Test
    public void shouldCheckProductAsRentedByUser() {
        Integer prodCod = stubFullProducts.get(0).getProductCod();
        Product product = productController.rentProductByCod(prodCod, mockUser);
        assertThat(product.isRented(), is(true));
        assertThat(product.getRentedBy(), is(mockUser));
    }

    @Test
    public void shouldNotReturnProductThatIsNotRented() {
        Integer prodCod = stubFullProducts.get(0).getProductCod();
        assertThat(productController.returnProductByCod(prodCod, mockUser), is(false));
        assertNull(stubFullProducts.get(0).getRentedBy());
    }

    @Test
    public void shouldFindProductsByFilter() {
        List<Product> products = productController.getProductsWhere(product -> product.getCategory() == ProductCategory.MOVIE);
        assertThat(products, is(stubMoviesList));
    }
}