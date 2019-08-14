package com.twu.biblioteca.view.products;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.controllers.AuthController;
import com.twu.biblioteca.controllers.ProductController;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Movie;
import com.twu.biblioteca.models.Product;
import com.twu.biblioteca.models.User;
import com.twu.biblioteca.view.PromptView;
import com.twu.biblioteca.view.components.TableList;
import com.twu.biblioteca.view.menu.MenuView;

import java.util.List;
import java.util.Scanner;

public class UserProductsView extends PromptView {

    public UserProductsView(Scanner reader) {
        super(reader);
    }

    @Override
    public void initialize() {
        ProductController productController = BibliotecaApp.getProductController();
        List<Product> rentedProducts = productController.getProductsWhere(product -> product.isRented());
        TableList tableList = getProductsTableList(rentedProducts);

        super.show(tableList.getTable());
        super.goTo(MenuView.class);
    }

    private TableList getProductsTableList(List<Product> products) {
        TableList tableList = new TableList("Cod", "Category", "Name", "Checked out By");
        for (Product product : products) {
            tableList.addRow(
                    product.getProductCod().toString(),
                    product.getCategory().getValue(),
                    getProductName(product),
                    product.getRentedBy().getLibraryNumber()
            );
        }
        return tableList;
    }

    private String getProductName(Product product) {
        if (product instanceof Book) return ((Book) product).getTitle();
        if (product instanceof Movie) return ((Movie) product).getName();
        return "No Name";
    }
}
