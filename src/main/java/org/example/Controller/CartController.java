package org.example.Controller;

import org.example.Model.Product;
import org.example.utils.AppExecption;
import org.example.utils.FileUtils;
import org.example.utils.StringUtils;
import org.example.view.CartPage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.example.utils.Appinput.enterInt;
import static org.example.utils.Utils.println;
public class CartController {

    private ArrayList<Product> cart;
   private final HomeController homeController;

   public CartController(HomeController homeController) {
      this.cart = new ArrayList<>();
      this.homeController = homeController;
   }


    public void addToCart(Product product) {
        cart.add(product);
        System.out.println("Product added to the cart: " + product.getTitle());
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            try {
                CartMenu();
                int choice = enterInt(StringUtils.ENTER_CHOICE);
                if (choice == 88) {
                    checkout();
                } else if (choice == 99) {
                    homeController.printMenu();
                } else {
                    invalidChoice(new AppExecption(StringUtils.INVALID_CHOICE));
                }

            }catch (AppExecption appExecption){
                invalidChoice(appExecption);
            }
        } else {
            System.out.println("Your cart:");
            for (int i = 0; i < cart.size(); i++) {
                System.out.println((i + 1) + ". " + cart.get(i).getTitle() +
                        " $" + cart.get(i).getPrice() +
                        " " + cart.get(i).getDescription());
            }try {
                CartMenu();
                int choice = enterInt(StringUtils.ENTER_CHOICE);
                if (choice == 88) {
                    checkout();
                } else if (choice == 99) {
                    homeController.printMenu();
                } else if (choice==01) {
                    System.exit(0);
                } else {
                    invalidChoice(new AppExecption(StringUtils.INVALID_CHOICE));
                }

            }catch (AppExecption appExecption){
                invalidChoice(appExecption);
            }

        }
    }

    private void CartMenu() {
        CartPage.CartMenu();
    }

    private void checkout() {
    }

    public void clearCart() {
        cart.clear();
        System.out.println("Cart cleared.");
    }

    public void saveCartToCSV() {
      try (FileWriter writer = new FileWriter(FileUtils.CartFile())) {
         writer.write("id,title,description,price,stocks,category\n");
         for (Product product : cart) {
            writer.write(product.getId() + "," +
                    product.getTitle() + "," +
                    product.getDescription() + "," +
                    product.getPrice() + "," +
                    product.getStocks() + "," +
                    product.getCategory().getName() + "\n");
         }

         System.out.println("Cart saved");

      } catch (IOException e) {
         System.out.println("Error saving cart: " + e.getMessage());
      }
   }
    private void invalidChoice(AppExecption appException) {
        println(appException.getMessage());
    }

}
