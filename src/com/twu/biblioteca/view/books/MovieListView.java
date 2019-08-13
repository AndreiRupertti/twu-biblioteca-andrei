package com.twu.biblioteca.view.books;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.controllers.ProductController;
import com.twu.biblioteca.models.Movie;
import com.twu.biblioteca.models.ProductCategory;
import com.twu.biblioteca.view.PromptView;
import com.twu.biblioteca.view.components.TableList;
import com.twu.biblioteca.view.menu.MenuView;

import java.util.List;
import java.util.Scanner;

public class MovieListView extends PromptView {

    public MovieListView(Scanner scan) { super(scan); }

    @Override
    public void initialize() {
        ProductController productController = BibliotecaApp.getProductController();
        List<Movie> movies = productController.getAvailableProductsByCategory(ProductCategory.MOVIE);
        TableList tableList = getMoviesTableList(movies);

        super.show(tableList.getTable());
        super.goTo(MenuView.class);
    }

    private TableList getMoviesTableList(List<Movie> movies) {
        TableList tableList = new TableList("Cod", "Name", "Director", "Year", "Rating");
        for (Movie movie : movies) {
            tableList.addRow(
                    movie.getProductCod().toString(),
                    movie.getName(),
                    movie.getDirector(),
                    Integer.toString(movie.getYear()),
                    movie.getRating()
            );
        }
        return tableList;
    }
}
