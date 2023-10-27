package org.example.utils;

import java.util.InputMismatchException;

import static org.example.utils.AppScanner.getScanner;
import static org.example.utils.Utils.print;

public class Appinput {
   public  static String enterString(String msg){
       print(msg);
       return getScanner().nextLine();
   }
   public static int enterInt(String msg) throws AppExecption {
       print(msg);
       int input;
       try {
          input= Integer.parseInt(getScanner().nextLine());
       }catch (Exception ex){
           throw new AppExecption("Invaild Input!!! please enter a valid input");
       }
       return input;
   }
}
