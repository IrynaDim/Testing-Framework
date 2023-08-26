package prestashop.pages.createOrder;

import prestashop.model.enums.Color;
import prestashop.pages.approveOrder.ApproveOrderPageAction;
import prestashop.pages.searchProduct.SearchAllProductPageAction;

public class AddToCartPageAction {
    private final AddToCartPage addToCartPage = new AddToCartPage();

    /**
     * Actions
     **/

    public AddToCartPageAction enterTextToCustomizationField(String text) {
        addToCartPage.addProductCustomization(text);
        return this;
    }

    public AddToCartPageAction clickSaveCustomizationButton() {
        addToCartPage.clickSaveCustomizationButton();
        return this;
    }

    public AddToCartPageAction insertQuantity(int quantity) {
        addToCartPage.insertQuantity(quantity);
        return this;
    }

    public ApproveOrderPageAction clickAddToCartButton() {
        addToCartPage.pressAddToCartButton();
        return new ApproveOrderPageAction();
    }

    public SearchAllProductPageAction insertTextInSearchFieldAndPressEnter(String text) {
        addToCartPage.insertTextInSearchFieldAndPressEnter(text);
        return new SearchAllProductPageAction();
    }

    public AddToCartPageAction chooseColor(Color color) {
        addToCartPage.chooseColor(color);
        return this;
    }
}
