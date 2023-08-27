package prestashop.pages.orderIsConfirmed;

import static org.testng.AssertJUnit.assertEquals;

public class OrderIsConfirmedPageAction {
    private final OrderIsConfirmedPage orderIsConfirmedPage = new OrderIsConfirmedPage();

    /**
     * Actions
     **/

    public String getOrderConfirmedText() {
        return orderIsConfirmedPage.getOrderIsConfirmed();
    }

    public String geTotalSum() {
        return orderIsConfirmedPage.getTotalSum();
    }

    /**
     * Asserts
     **/
    public OrderIsConfirmedPageAction assertOrderIsConfirmedText(String text) {
        String orderConfirmedText = getOrderConfirmedText();
        assertEquals("Actual order confirmed text is: " + orderConfirmedText, orderConfirmedText, text);
        return this;
    }

    public OrderIsConfirmedPageAction assertTotalSum(String totalSum) {
        String actualSum = geTotalSum();
        assertEquals("Actual sum is: " + actualSum, actualSum, totalSum);
        return this;
    }
}
