package config;

public enum BrowserType {
    CHROME("chrome");

    private final String value;

    BrowserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BrowserType fromString(String value) {
        for (BrowserType browser : values()) {
            if (browser.getValue().equalsIgnoreCase(value)) {
                return browser;
            }
        }
        return CHROME;
    }
}