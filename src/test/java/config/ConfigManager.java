package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ConfigManager {
    private static final String configFilePath = "src/test/java/config.properties";
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static Boolean isHeadlessModeOn() {
        return Boolean.parseBoolean(properties.getProperty("headless"));
    }
}
