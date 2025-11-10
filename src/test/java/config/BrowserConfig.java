package config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BrowserConfig {

    public static void setupBrowser() {
        Configuration.browser = ConfigManager.getBrowser();
        Configuration.headless = false;
        Configuration.baseUrl = ConfigManager.getBaseUrl();
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 30000;
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.browserSize = RealUserDataManager.getViewportWidth() + "x" + RealUserDataManager.getViewportHeight();

        switch (BrowserType.fromString(ConfigManager.getBrowser())) {
            case CHROME:
            default:
                setupChrome();
                break;
        }

    }

    public static void setupChrome() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments(Arrays.asList(
                "--disable-blink-features=AutomationControlled",
                "--no-default-browser-check",
                "--no-first-run",
                "--disable-default-apps",
                "--disable-extensions",
                "--disable-web-security",
                "--disable-dev-shm-usage",
                "--no-sandbox",
                "--ignore-certificate-errors",
                "--ignore-ssl-errors",
                "--start-maximized",
                "--disable-infobars",
                "--allow-running-insecure-content",
                "--user-agent=" + RealUserDataManager.getRealUserAgent(),
                "--window-size=" + RealUserDataManager.getViewportWidth() + "," + RealUserDataManager.getViewportHeight()
        ));

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("download.prompt_for_download", false);
        prefs.put("safebrowsing.enabled", true);

        options.setExperimentalOption("prefs", prefs);
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        Configuration.browserCapabilities = options;
    }

    public static void applyRealCookies() {
        try {
            String realCookies = RealUserDataManager.getRealCookies();
            if (realCookies.isEmpty()) {
                return;
            }

            String[] cookiePairs = realCookies.split(";");
            int appliedCookies = 0;

            for (String cookiePair : cookiePairs) {
                String[] parts = cookiePair.trim().split("=", 2);
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    String value = parts[1].trim();

                    if (!name.isEmpty()) {
                        try {
                            Cookie cookie = new Cookie.Builder(name, value)
                                    .domain("rozetka.com.ua")
                                    .path("/")
                                    .isHttpOnly(false)
                                    .isSecure(true)
                                    .build();

                            com.codeborne.selenide.WebDriverRunner.getWebDriver().manage().addCookie(cookie);
                            appliedCookies++;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            com.codeborne.selenide.Selenide.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void applyRealFingerprinting() {
        try {
            String languagesArray = String.join("\", \"", RealUserDataManager.getRealLanguages());

            String fingerprintScript =
                    "// Скрыть webdriver\n" +
                            "Object.defineProperty(navigator, 'webdriver', { get: () => false });\n" +
                            "\n" +
                            "Object.defineProperty(navigator, 'language', { get: () => '" + RealUserDataManager.getRealLanguage() + "' });\n" +
                            "Object.defineProperty(navigator, 'languages', { get: () => [\"" + languagesArray + "\"] });\n" +
                            "\n" +
                            "Object.defineProperty(navigator, 'platform', { get: () => '" + RealUserDataManager.getRealPlatform() + "' });\n" +
                            "\n" +
                            "// Удалить automation痕迹\n" +
                            "delete window.cdc_adoQpoasnfa76pfcZLmcfl_Array;\n" +
                            "delete window.cdc_adoQpoasnfa76pfcZLmcfl_Promise;\n" +
                            "delete window.cdc_adoQpoasnfa76pfcZLmcfl_Symbol;\n" +
                            "\n" +
                            "Object.defineProperty(screen, 'width', { get: () => " + RealUserDataManager.getScreenWidth() + " });\n" +
                            "Object.defineProperty(screen, 'height', { get: () => " + RealUserDataManager.getScreenHeight() + " });\n" +
                            "Object.defineProperty(screen, 'colorDepth', { get: () => " + RealUserDataManager.getScreenColorDepth() + " });\n" +
                            "\n" +
                            "console.log('✅ Real fingerprinting applied with your Mac data');";

            com.codeborne.selenide.Selenide.executeJavaScript(fingerprintScript);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}