package com.epam.jwd.web.resource;

import java.util.ResourceBundle;

public class ApplicationManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("application.properties");

    private ApplicationManager() {}

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
