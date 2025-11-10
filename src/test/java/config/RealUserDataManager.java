package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public final class RealUserDataManager {
    private static final String configFileFullName = "real-user-data.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(configFileFullName)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getRealUserAgent() {
        return properties.getProperty("user.agent");
    }

    public static String getRealCookies() {
        return properties.getProperty("user.cookies");
    }

    public static String getRealPlatform() {
        return properties.getProperty("user.platform");
    }

    public static String getRealLanguage() {
        return properties.getProperty("user.language");
    }

    public static List<String> getRealLanguages() {
        String languages = properties.getProperty("user.languages");
        return Arrays.asList(languages.split(","));
    }

    public static int getViewportWidth() {
        return Integer.parseInt(properties.getProperty("user.viewport.width"));
    }

    public static int getViewportHeight() {
        return Integer.parseInt(properties.getProperty("user.viewport.height"));
    }

    public static int getScreenWidth() {
        return Integer.parseInt(properties.getProperty("user.screen.width"));
    }

    public static int getScreenHeight() {
        return Integer.parseInt(properties.getProperty("user.screen.height"));
    }

    public static int getScreenColorDepth() {
        return Integer.parseInt(properties.getProperty("user.screen.colorDepth"));
    }

    public static int countCookies() {
        String cookies = getRealCookies();
        return cookies.isEmpty() ? 0 : cookies.split(";").length;
    }
}