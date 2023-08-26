package prestashop.pages.shoppingCart;

import static org.testng.AssertJUnit.assertEquals;

public class ShoppingCartPageAction {
    private ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

    /**
     * Actions
     **/
    public String getTotalPrice() {
        return shoppingCartPage.getTotalPrice();
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
