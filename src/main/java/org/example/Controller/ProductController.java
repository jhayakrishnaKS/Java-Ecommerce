package org.example.Controller;

import org.example.Controller.impl.IHomeController;
import org.example.Model.Product;

import org.example.utils.FileUtils;
import org.example.utils.Utils;

import java.util.Scanner;



public class ProductController implements IHomeController {

   public ProductController(HomeController homeController) {
   }

   public Product loadproduct(){
      try {
         Scanner scanner = new Scanner(FileUtils.ProductFile());
         while (scanner.hasNext()) {
            String product = scanner.next().trim();
            if (!product.startsWith("id")) {
               String[] userArray = product.split(",");
               Utils.println(userArray[0] +". " +userArray[1] + " $" + userArray[2] + " " + userArray[3]);
            }
         }
      }catch (Exception e){

      }
      return null;
   }

   @Override
   public void printMenu() {

   }
}