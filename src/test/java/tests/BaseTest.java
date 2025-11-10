package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigManager;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.*;

public abstract class BaseTest {

    @BeforeSuite
    public void setUpSuite() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));

        Configuration.browser = ConfigManager.getBrowser();
        Configuration.baseUrl = ConfigManager.getBaseUrl();
        Configuration.headless = ConfigManager.isHeadlessModeOn();
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        Configuration.pageLoadTimeout = 15000;
    }

    @BeforeMethod
    public void setUp() {
        open("/");

    }

    @Step("{stepName}")
    public static void step(String stepName) {
        System.out.println("➡  " + stepName);
    }

    @AfterMethod
    public void tearDown() {
        closeWebDriver();
    }
}