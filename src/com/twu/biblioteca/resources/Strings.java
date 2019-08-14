package com.twu.biblioteca.resources;


import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Strings {
    private static final String STRINGS_BUNDLE = "strings.Strings";

    public static String get(String key) {
        try {
            return getResourceBundle().getString(key);
        } catch (MissingResourceException e) {
            return "";
        }
    }

    public static ResourceBundle getResourceBundle() { return ResourceBundle.getBundle(STRINGS_BUNDLE); }

}
