package org.example.Controller;

import org.example.Model.Category;

import java.util.ArrayList;

import static org.example.utils.LoadUtils.getCategories;

public class CategoryController {
    public CategoryController(HomeController homeController) {

    }

    public void printMenu() {
        ArrayList<Category> categories = getCategories();
    }
}
