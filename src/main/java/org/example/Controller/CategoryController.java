package org.example.Controller;

import org.example.utils.AppExecption;
import org.example.utils.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.Appinput.enterInt;
import static org.example.utils.Utils.println;

public class CategoryController {
    private final HomeController homeController;

    public CategoryController(HomeController homeController) {
        this.homeController = homeController;
    }

    public void displayCategories() {
        println("1. Phone");
        println("2. Accessories");

    }

    public void chooseCategory() {

        try {
            int categoryChoice= 0;
            categoryChoice = enterInt(StringUtils.ENTER_CHOICE);
            switch (categoryChoice) {
                case 1:
                    displayProductsByCategory("phone");
                    break;
                case 2:
                    displayProductsByCategory("accessories");
                    break;
                case 88:
                    checkout();
                    break;
                case 99:
                    homeController.printMenu();
                    break;
                default:
                    invalidChoice(new AppExecption("Invalid category choice."));
            }
        } catch (AppExecption appException) {
            invalidChoice(appException);
        }

    }

    private void displayProductsByCategory(String category) {
        println("Products in " + category + ":");

        List<String[]> products = readProductsFromFile();

        for (String[] product : products) {
            if (product.length >= 5 && product[4].equalsIgnoreCase(category)) {
                println( product[0]  +". "+ product[1] + " Price: $" + product[2] +" "+ product[3]);
            }
        }
    }

    private List<String[]> readProductsFromFile() {
        List<String[]> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/org/example/Assets/Product.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] productArray = line.split(",");
                products.add(productArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    private void checkout() {

    }

    private void invalidChoice(AppExecption e) {
        println(e.getMessage());
    }

}
