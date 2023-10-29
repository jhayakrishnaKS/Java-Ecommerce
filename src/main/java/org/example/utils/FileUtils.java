package org.example.utils;

import java.io.File;
import java.io.FileNotFoundException;

public class FileUtils {
    private static File credentailsFile;
    private static File productFile;

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

    public static String CartFile() {
        return null;
    }
}
