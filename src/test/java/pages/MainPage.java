package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage{
    private final SelenideElement mainPageForm = $("rz-main-page");
    private final SelenideElement topPageBanner = $("rz-top-page-banner");
    private final SelenideElement pageContent = $("rz-main-page-content");
    private final SelenideElement pageSidebar = $("rz-main-page-sidebar");

    public MainPage shouldSeeMainPage() {
        shouldSeeHeader();
        mainPageForm.shouldBe(visible);
        topPageBanner.shouldBe(visible);
        pageContent.shouldBe(visible);
        pageSidebar.shouldBe(visible);
        return this;
    }
}
