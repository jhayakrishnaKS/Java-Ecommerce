package org.example.utils;

import java.io.File;

public class FileUtils {
    private static File credentailsFile;
    private static File productFile;
    private static final String CART_FILE_PATH = "src/main/java/org/example/Assets/Cart.csv";

    public static File getCredentialsFile() {
        if (credentailsFile == null)
            credentailsFile = new File("src/main/java/org/example/Assets/credentailsFile.txt");
        return credentailsFile;
    }
    public static File ProductFile(){
        if (productFile == null) {
            productFile = new File("src/main/java/org/example/Assets/Product.csv");
        }
        return productFile;
    }

    public static File CartFile() {
        return new File(CART_FILE_PATH);
    }
}
