package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.SearchSuggestPanel;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {

    private final SelenideElement header = $("rz-main-header").as("Header");
    private final SelenideElement searchInput = getLocatorByDataTestId("search-suggest-input");
    private final SelenideElement searchSubmitButton = getLocatorByDataTestId("search-suggest-submit");
    private final SelenideElement searchMicButton = getLocatorByDataTestId("search-suggest-mic");

    protected BasePage shouldSeeHeader() {
        header.shouldBe(visible);
        return this;
    }

    public SearchSuggestPanel fillSearchInput(String searchText) {
        searchInput.shouldBe(visible).setValue(searchText);
        searchInput.shouldHave(value(searchText).because("Filled text " + searchText + " does not display into input field"));
        return new SearchSuggestPanel();
    }

    public SearchResultPage clickSubmitButton() {
        searchSubmitButton.shouldBe(visible).click();
        return new SearchResultPage();
    }

    public SearchSuggestPanel clickProductSearchInput() {
        searchInput.shouldBe(visible).click();
        return new SearchSuggestPanel();
    }

    protected SelenideElement getLocatorByDataTestId(String dataTestID) {
        return $("[data-testid=" + dataTestID + "]");
    }
}
