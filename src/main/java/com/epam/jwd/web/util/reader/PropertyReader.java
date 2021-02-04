package com.epam.jwd.web.util.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public enum PropertyReader {
    INSTANCE;
    private Properties properties;
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyReader.class);

    public void loadProperties(){

        properties = new Properties();
        final String propertiesFileName = "src/main/resources/application.properties";

        try (FileInputStream inputStream = new FileInputStream(new File(propertiesFileName))) {
            properties.load(inputStream);
            LOGGER.info("Properties were successfully loaded");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.error("application.properties file not found!");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Properties weren't loaded!");
        }
    }

    public Properties getProperties() {
        if (properties == null) {
            loadProperties();
        }
        return properties;
    }
}
