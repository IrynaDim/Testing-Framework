package prestashop.pages.searchProduct;

import prestashop.pages.createOrder.AddToCartPageAction;

public class SearchAllProductPageAction {
    private final SearchAllProductPage searchProductPage = new SearchAllProductPage();

    /**
     * Actions
     **/
    public AddToCartPageAction clickOnSearchProduct(String name) {
        return searchProductPage.clickOnSearchProduct(name);
    }
}
