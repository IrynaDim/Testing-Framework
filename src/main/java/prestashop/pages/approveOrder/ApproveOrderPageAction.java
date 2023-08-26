package prestashop.pages.approveOrder;

import prestashop.pages.createOrder.AddToCartPageAction;
import prestashop.pages.shoppingCart.ShoppingCartPageAction;

public class ApproveOrderPageAction {
    private final ApproveOrderPage approveOrderPage = new ApproveOrderPage();

    /** Actions **/
    public AddToCartPageAction clickContinueShopping() {
        approveOrderPage.clickContinueShoppingButton();
        return new AddToCartPageAction();
    }

    public ShoppingCartPageAction clickProceedToCheckoutButton() {
        approveOrderPage.clickProceedToCheckoutButton();
        return new ShoppingCartPageAction();
    }
}
