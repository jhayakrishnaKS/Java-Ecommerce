package org.example.Controller;

import org.example.Controller.impl.IHomeController;
import org.example.Model.Category;
import org.example.Model.Product;

import org.example.utils.FileUtils;

import org.example.utils.Utils;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ProductController implements IHomeController {

   private final HomeController homeController;

   // Fix the constructor to receive HomeController
   public ProductController(HomeController homeController) {
      this.homeController = homeController;
   }

   @Override
   public void printMenu() {
      try {
         loadProduct();
         selectProductAndAddToCart();
      } catch (Exception e) {
         invalidChoice(e);
      }
   }

   public void loadProduct() {
      try {
         Scanner scanner = new Scanner(FileUtils.ProductFile());
         while (scanner.hasNext()) {
            String productString = scanner.next().trim();
            if (!productString.startsWith("id")) {
               String[] productArray = productString.split(",");
               Utils.println(productArray[0] + ". " + productArray[1] + " $" + productArray[2] + " " + productArray[3]);
            }
         }selectProductAndAddToCart();
      } catch (Exception e) {
         invalidChoice(e);
      }
   }

   public void selectProductAndAddToCart() {
      try {
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
                  loadProduct();
               }
            }
         }

      } catch (InputMismatchException e) {
         invalidChoice(e);
      } catch (FileNotFoundException e) {
         throw new RuntimeException(e);
      }
   }

   private void checkout() {
   }

   private void invalidChoice(Exception e) {
      e.printStackTrace();
   }
}