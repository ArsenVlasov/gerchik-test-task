package pages.components;

import com.codeborne.selenide.SelenideElement;
import pages.BasePage;
import pages.MainPage;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SearchSuggestPanel extends BasePage {
    private final SelenideElement panelContent = $("rz-search-suggest-content");

    public SearchSuggestPanel shouldSeeProductSearchPanel() {
        panelContent.shouldBe(visible);
        return this;
    }

    public SearchSuggestPanel shouldNotSeeProductSearchPanel() {
        panelContent.shouldNotBe(visible);
        return this;
    }

}
