package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import utils.PriceUtils;
import utils.ProductSortType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class SearchResultPage extends BasePage {
    private final SelenideElement breadcrumbs = $("rz-default-breadcrumbs");
    private final SelenideElement autoPortal = $("rz-auto-portal");
    private final SelenideElement selectedFilters = $("rz-selected-filters");
    private final SelenideElement sortTypeDropdown = $("select[id='sort']");
    private final SelenideElement viewSwitcher = $("rz-view-switch");
    private final SelenideElement filterStackSideBar = $("rz-filter-stack");
    private final SelenideElement goodsCatalog = $("rz-catalog-goods");

    public SearchResultPage shouldSeeSearchResultPage() {
        shouldSeeHeader();
        breadcrumbs.shouldBe(visible);
        autoPortal.shouldBe(visible);
        selectedFilters.shouldBe(visible);
        sortTypeDropdown.shouldBe(visible);
        viewSwitcher.shouldBe(visible);
        filterStackSideBar.shouldBe(visible);
        goodsCatalog.shouldBe(visible);
        return this;
    }

    public SearchResultPage setSortType(ProductSortType sortType) {
        sortTypeDropdown.shouldBe(visible).click();
        $(sortType.getCssSelector()).shouldBe(visible).click();
        sortTypeDropdown.shouldHave(value(sortType.getDisplayName())
                .because("Sort type dropdown should has value '" + sortType.getDisplayName() + "'"));
        return this;
    }

    private ElementsCollection getAllAvailableProducts() {
        return goodsCatalog.$$("rz-product-tile:not(.tile-disabled)")
                .shouldHave(sizeGreaterThan(0));
    }

    private List<Integer> getAllAvailableProductsPrices() {
        return getAllAvailableProducts().stream()
                .map(item -> item.$(".price").getText())
                .map(PriceUtils::parsePrice)
                .collect(Collectors.toList());
    }

    public SearchResultPage isPricesSortedAscending() {
        List<Integer> actualPrices = getAllAvailableProductsPrices();
        List<Integer> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Comparator.naturalOrder());
        Assert.assertEquals(actualPrices, expectedPrices);
        return this;
    }
}
