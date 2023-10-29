package org.example.Controller;

import org.example.Model.Product;
import org.example.utils.FileUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartController {

    private List<Product> cart;

    public CartController(HomeController homeController) {
        this.cart = new ArrayList<>();
    }


    public void addToCart(Product product) {
        cart.add(product);
        System.out.println("Product added to the cart: " + product.getTitle());
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Your cart:");
            for (int i = 0; i < cart.size(); i++) {
                System.out.println((i + 1) + ". " + cart.get(i).getTitle() +
                        " $" + cart.get(i).getPrice() +
                        " " + cart.get(i).getDescription());
            }
        }
    }

    public void clearCart() {
        cart.clear();
        System.out.println("Cart cleared.");
    }

    public void saveCartToCSV() {
        try (FileWriter writer = new FileWriter(FileUtils.CartFile())) {
            // Write header to the CSV file
            writer.write("id,title,description,price,stocks,category\n");

            // Write each product in the cart to the CSV file
            for (Product product : cart) {
                writer.write(product.getId() + "," +
                        product.getTitle() + "," +
                        product.getDescription() + "," +
                        product.getPrice() + "," +
                        product.getStocks() + "," +
                        product.getCategory().getName() + "\n");
            }

            System.out.println("Cart saved to CSV file.");
        } catch (IOException e) {
            System.out.println("Error saving cart to CSV file: " + e.getMessage());
        }
    }
}
