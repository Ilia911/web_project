package com.epam.jwd.web.resource;

import java.util.ResourceBundle;

public class GeneralKeysManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("generalKeys.properties");

    private GeneralKeysManager(){}

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
