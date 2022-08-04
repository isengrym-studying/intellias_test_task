package ua.klieshchunov.task.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
    public static Properties loadProperties(String path) {
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream(path)) {
            properties.load(input);

        } catch (IOException ioException) {
            return properties;
        }

        return properties;
    }
}
