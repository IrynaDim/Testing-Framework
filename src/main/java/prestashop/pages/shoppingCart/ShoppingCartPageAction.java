package prestashop.pages.shoppingCart;

import prestashop.pages.deliveryPaymentInfo.AddDeliveryPaymentInfoPageAction;
import prestashop.pages.mainPage.MainPageAction;

import static org.testng.AssertJUnit.assertEquals;

public class ShoppingCartPageAction {
    private final ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

    /**
     * Actions
     **/
    public String getTotalPrice() {
        return shoppingCartPage.getTotalPrice();
    }

    public ShoppingCartPageAction clearShoppingCart() {
        shoppingCartPage.clearShoppingCart();
        return this;
    }

    public AddDeliveryPaymentInfoPageAction clickProsedButton() {
        shoppingCartPage.clickProceedButton();
        return new AddDeliveryPaymentInfoPageAction();
    }

    public MainPageAction refreshPage() {
        shoppingCartPage.refreshPage();
        return new MainPageAction();
    }
    /**
     * Asserts
     **/
    public ShoppingCartPageAction assertTotalPrice(String price) {
        String totalPriceActual = getTotalPrice();
        assertEquals("Total price is not equals. Actual price is " + totalPriceActual + ". Expected price is " + price, price, totalPriceActual);
        return this;
    }
}
