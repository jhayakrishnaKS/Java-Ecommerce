package org.example.Controller;

import org.example.Controller.impl.IProductController;
import org.example.Model.Product;
import org.example.utils.AppExecption;
import org.example.utils.AppScanner;
import org.example.utils.FileUtils;

import java.util.Scanner;

import static org.example.utils.Utils.println;

public class ProductController {
   public ProductController(HomeController homeController) {
   }

   private Product loadproduct(){
      try {
         Scanner scanner= AppScanner.getScanner(FileUtils.ProductFile());
         while (scanner.hasNext()){
            String product=scanner.next().trim();
            if(!product.startsWith("id")){

            }
         }
      }catch (Exception e){

      }
       return null;
   }
   private void invalidChoice(AppExecption e) {
      println(e.getMessage());
   }
}