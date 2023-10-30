package org.example.view;

import org.example.utils.StringUtils;

import static org.example.utils.Utils.println;

public class CartPage {
    public static void CartMenu() {
        try {
            println("#---------------------#");
            println(StringUtils.CART_MENU);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
