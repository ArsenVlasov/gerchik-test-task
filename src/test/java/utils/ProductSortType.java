package utils;

public enum ProductSortType {
    CHEAP_TO_EXPENSIVE("Від дешевих до дорогих", "cheap"),
    EXPENSIVE_TO_CHEAP("Від дорогих до дешевих", "expensive"),
    BY_NOVELTY("Новинки", "novelty"),
    BY_RANK("За рейтингом", "rank");

    private final String displayName;
    private final String cssSelector;

    ProductSortType(String displayName, String cssSelector) {
        this.displayName = displayName;
        this.cssSelector = "option[value='" + cssSelector + "']";
    }

    public String getDisplayName() { return displayName; }
    public String getCssSelector() { return cssSelector; }
}

