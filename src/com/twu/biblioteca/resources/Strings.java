package com.twu.biblioteca.resources;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Strings {
    private static final String STRINGS_BUNDLE = "strings.Strings";

    public static String get(String key) {
        ResourceBundle.clearCache();
        try {
            return ResourceBundle.getBundle(STRINGS_BUNDLE).getString(key);
        } catch (MissingResourceException e) {
            e.printStackTrace();
            return "";
        }
    }
}
