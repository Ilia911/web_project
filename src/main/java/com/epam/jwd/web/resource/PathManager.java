package com.epam.jwd.web.resource;

import java.util.ResourceBundle;

public final class PathManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("path.properties");

    private PathManager(){}

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
