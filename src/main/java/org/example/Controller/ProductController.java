package org.example.Controller;

import org.example.Controller.impl.IHomeController;
import org.example.Model.Category;
import org.example.Model.Product;
import org.example.utils.FileUtils;
import org.example.utils.Utils;

import java.util.Arrays;
import java.util.Scanner;

public class ProductController implements IHomeController {

   private HomeController cartController;

   // Fix the constructor to receive CartController
   public ProductController(HomeController cartController) {
      this.cartController = cartController;
   }

   @Override
   public void printMenu() {
      try {
         loadProduct();
         selectProductAndAddToCart();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void loadProduct() {
      try {
         Scanner scanner = new Scanner(FileUtils.ProductFile());
         while (scanner.hasNext()) {
            String product = scanner.next().trim();
            if (!product.startsWith("id")) {
               String[] productArray = product.split(",");
               Utils.println(productArray[0] + ". " + productArray[1] + " $" + productArray[2] + " " + productArray[3]);
            }
         }
      } catch (Exception e) {
         invalidChoice(e);
      }
   }

   public void selectProductAndAddToCart() {
      try {
         Scanner inputScanner = new Scanner(System.in);

         // Prompt the user to select a product
         Utils.print("Enter the ID of the product to add to the cart (or enter 0 to go back to the main menu): ");
         int selectedProductId = inputScanner.nextInt();

         if (selectedProductId == 0) {
            // Go back to the main menu
            return;
         }

         Scanner scanner = new Scanner(FileUtils.ProductFile());
         while (scanner.hasNext()) {
            String productString = scanner.next().trim();
            if (!productString.startsWith("id")) {
               String[] productArray = productString.split(",");

               // Print the content of productArray for debugging
               System.out.println("Product Array in CSV: " + Arrays.toString(productArray));

               // Add the check for a valid product ID here
               if (!productArray[0].matches("\\d+")) {
                  // Handle the case where the product ID is not a valid integer
                  System.err.println("Invalid product ID: " + productArray[0]);
                  continue; // Skip this product and move to the next one
               }

               int productId = Integer.parseInt(productArray[0]);
               String title = productArray[1];
               double price = Double.parseDouble(productArray[2]);
               int stocks = Integer.parseInt(productArray[3]);
               String category = productArray[4];

               if (productId == selectedProductId) {
                  Product selectedProduct = new Product(
                          productId,
                          title,
                          "", // You may want to add a description field to your CSV
                          price,
                          stocks,
                          new Category(category)
                  );

                  // Add the selected product to the cart
                  cartController.addToCart(selectedProduct);

                  // Print a confirmation message
                  Utils.println("Product added to the cart: " + selectedProduct.getTitle());
                  break;
               }
            }
         }
      } catch (Exception e) {
         invalidChoice(e);
      }
   }



   private void invalidChoice(Exception e) {
      e.printStackTrace();
   }
}
