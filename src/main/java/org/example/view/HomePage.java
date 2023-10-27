package org.example.view;

import org.example.utils.StringUtils;

import static org.example.utils.Utils.println;

public class HomePage {
    public void printMenu() {
        try {
            println("#---------------------#");
            println(StringUtils.HOME_MENU);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    }

