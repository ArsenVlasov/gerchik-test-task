package tests;

import config.ConfigManager;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.SearchResultPage;
import utils.ProductSortType;

public class ProductFilterTest extends BaseTest {

    MainPage mainPage = new MainPage();
    SearchResultPage searchResultPage = new SearchResultPage();

    @Test
    public void productFilterTest()  {
        step("1. Войти на сайт " + ConfigManager.getBaseUrl());
        mainPage.shouldSeeMainPage();

        step("2. Найти какой-нибудь популярный товар (например Iphone 15)");
        searchResultPage = mainPage.clickProductSearchInput()
                .shouldSeeProductSearchPanel()
                .fillSearchInput("IPhone 15")
                .clickSubmitButton()
                .shouldSeeSearchResultPage();

        step("3. Применить сортировку по цене");
        searchResultPage.setSortType(ProductSortType.CHEAP_TO_EXPENSIVE);

        step("4. Проверить, что цены на самом деле отсортированы");
        searchResultPage.isPricesSortedAscending();
    }
}
