package org.example.view;
import org.example.utils.StringUtils;

import static org.example.utils.Utils.println;
import org.example.utils.StringUtils;

public class WelcomePage {
    public void welcome() {
        try {
            System.out.println("*************************************");
            println(StringUtils.WELCOME_MESSAGE);
            System.out.println("*************************************");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void printAuthMenu() {
        System.out.println("************");
        println(StringUtils.AUTH_MENU);
        System.out.println("************");
    }
    void check(){
        System.out.println();
    }
}
