package by.itacademy.pharmacy.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertyUtil {

    private static final Properties PROPERTIES;

    static {
        Path resourcesFilePath = Paths.get("resources", "application.properties");
        PROPERTIES = new Properties();
        try {
            PROPERTIES.load(new FileReader(resourcesFilePath.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}

