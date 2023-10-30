package org.example.Controller;

import org.example.Model.Category;
import org.example.Model.Product;
import org.example.utils.AppExecption;
import org.example.utils.FileUtils;
import org.example.utils.StringUtils;
import org.example.utils.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
            if (product.length >= 5 && product[4].equalsIgnoreCase(category)) {
                // Print product information
                println(product[0] + ". " + product[1] + " Price: $" + product[2] + " " + product[3]);
            }
        } selectProductAndAddToCart();
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
    private void selectProductAndAddToCart() {
        try {
            displayProductsByCategory();
            Scanner inputScanner = new Scanner(System.in);

            Utils.print("Enter the ID of the product to add to the cart: ");
            int selectedProductId = inputScanner.nextInt();

            if (selectedProductId == 0) {
                homeController.printMenu();
                return;
            }

            Scanner scanner = new Scanner(FileUtils.ProductFile());
            while (scanner.hasNext()) {
                String productString = scanner.next().trim();
                if (!productString.startsWith("id")) {
                    String[] productArray = productString.split(",");

                    if (!productArray[0].matches("\\d+")) {
                        System.err.println("Invalid product ID: " + productArray[0]);
                        continue;
                    }

                    int productId = Integer.parseInt(productArray[0]);

                    if (productId == selectedProductId) {
                        Product selectedProduct = new Product(
                                productId,
                                productArray[1],
                                "",
                                Double.parseDouble(productArray[2]),
                                Integer.parseInt(productArray[3]),
                                new Category(productArray[4])
                        );

                        homeController.addToCart(selectedProduct);
//                  Utils.println("Product added to the cart: " + selectedProduct.getTitle());

                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void displayProductsByCategory() {
    }

    private void checkout() {

    }

    private void invalidChoice(AppExecption e) {
        println(e.getMessage());
        chooseCategory();
    }
    
}
