package com.twu.biblioteca.view.books;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.controllers.ProductController;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.ProductCategory;
import com.twu.biblioteca.view.PromptView;
import com.twu.biblioteca.view.components.TableList;
import com.twu.biblioteca.view.menu.MenuView;

import java.util.List;
import java.util.Scanner;

public class BookListView extends PromptView {

    public BookListView(Scanner reader) {
        super(reader);
    }

    @Override
    public void initialize() {
        ProductController productController = BibliotecaApp.getProductController();
        List<Book> books = productController.getAvailableProductsByCategory(ProductCategory.BOOK);
        TableList tableList = getBooksTableList(books);

        super.show(tableList.getTable());
        super.goTo(MenuView.class);
    }

    private TableList getBooksTableList(List<Book> books) {
        TableList tableList = new TableList("Cod", "Title", "Year", "Author");
        for (Book book : books) {
            tableList.addRow(
                    book.getProductCod().toString(),
                    book.getTitle(),
                    Integer.toString(book.getYear()),
                    book.getAuthor()
            );
        }
        return tableList;
    }
}
