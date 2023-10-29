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

    public void chooseCategory() {
        try {
            int categoryChoice = 0;
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

    // Method to display products based on a specified category
    private void displayProductsByCategory(String category) {
        // Print header for the category
        println("Products in " + category + ":");

        // Read products from file and store in ArrayList
        ArrayList<String[]> products = (ArrayList<String[]>) readProductsFromFile();

        // Iterate through each product
        for (String[] product : products) {
            // Check if the product has enough elements and belongs to the specified category
            if (product.length >= 5 && product[4].equalsIgnoreCase(category)) {
                // Print product information
                println(product[0] + ". " + product[1] + " Price: $" + product[2] + " " + product[3]);
            }
        }
    }

    // Method to read products from a file and return a list of string arrays
    private List<String[]> readProductsFromFile() {
        // ArrayList to store products
        ArrayList<String[]> products = new ArrayList<>();

        // Try to read from the file
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/org/example/Assets/Product.csv"))) {
            String line;
            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                // Split the line into an array using commas as delimiters
                String[] productArray = line.split(",");
                // Add the product array to the list
                products.add(productArray);
            }
        } catch (IOException e) {
            // Handle IO exception by printing the stack trace
            e.printStackTrace();
        }

        // Return the list of products
        return products;
    }


    private void checkout() {


    }

    private void invalidChoice(AppExecption e) {
        println(e.getMessage());
        chooseCategory();
    }
}
