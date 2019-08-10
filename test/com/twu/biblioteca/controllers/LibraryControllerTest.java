package com.twu.biblioteca.controllers;

import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Storage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class LibraryControllerTest {
    @Mock Storage storage;
    private LibraryController libraryController;
    private ArrayList<Book> stubBookList;

    @Before
    public void before() {
        storage = mock(Storage.class);
        stubBookList = new ArrayList<>(Arrays.asList(
                new Book("Book 1", 1999, "Some Author"),
                new Book("Book 2", 1999, "Some Author"))
        );
        when(storage.getItems()).thenReturn(stubBookList);
        libraryController = new LibraryController(storage);
    }

    @Test
    public void shouldGetStorageItems() {
        assertThat(libraryController.getBooks(), is(stubBookList));
        verify(storage, times(1)).getItems();
    }

    @Test
    public void shouldFindItemByFilter() {
        Book foundBook = libraryController.findBook((book) -> book.getTitle().equals("Book 1"));
        assertThat(foundBook instanceof Book, is(true));
        assertThat(foundBook.getTitle(), is("Book 1"));
    }

    @Test
    public void shouldReturnNullWhenItemNotFound() {
        Book nullableBook = libraryController.findBook((book) -> book.getTitle().equals("Invalid Book"));
        assertEquals(nullableBook,  null);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldNotCheckoutTheSameBookTwice() {
        libraryController.checkoutBookByTitle("Book 1");
        libraryController.checkoutBookByTitle("Book 1");
    }

    @Test
    public void shouldCheckBookAsCheckouted() {
        Book book = libraryController.checkoutBookByTitle("Book 1");
        assertThat(book.isCheckouted(), is(true));
    }

    @Test
    public void shouldNotReturnBookThatIsNotCheckouted() {
        assertThat(libraryController.returnBook("Book 1"), is(false));
    }

    @Test
    public void WhenReturningBookShouldSetAsNotCheckouted() {
        Book book = libraryController.checkoutBookByTitle("Book 1");
        assertThat(libraryController.returnBook("Book 1"), is(true));
        assertThat(book.isCheckouted(), is(false));
    }
}