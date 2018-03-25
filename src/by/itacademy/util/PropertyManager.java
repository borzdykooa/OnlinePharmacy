package by.itacademy.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by 1 on 11.03.2018.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertyManager {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadApplicationProperties();
    }

    private static void loadApplicationProperties() {
        try {
            PROPERTIES.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
