package com.ibm.project.service.container.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvironmentReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream stream = EnvironmentReader.class.getResourceAsStream("/testConfiguration.properties")) {
            properties.load(stream);
        } catch (NullPointerException e) {
            throw new NullPointerException("Can't find .properties file!");
        } catch (IOException e) {
            throw new RuntimeException(".properties file can't be loaded properly!");
        }
    }

    public static String getRoot() {

        if (System.getProperty("api.root") != null) {
            return System.getProperty("api.root");
        } else {
            return properties.getProperty("api.root");
        }
    }
}
