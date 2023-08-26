package prestashop;

import org.testng.annotations.Test;
import prestashop.model.enums.Color;

public class CreateOrderTest extends BaseTest {

    @Test
    public void createOrder_withTwoProducts() {
        getMainPageAction()
                .clearShoppingCart()
                .enterTextInSearchFieldAndPressEnter("Mug")
                .clickOnSearchProduct("Customizable Mug")
                .enterTextToCustomizationField("Best mug ever")
                .clickSaveCustomizationButton()
                .insertQuantity(1)
                .clickAddToCartButton()
                .clickContinueShopping()
                .insertTextInSearchFieldAndPressEnter("Hummingbird Printed T-Shirt")
                .clickOnSearchProduct("Hummingbird Printed T-Shirt")
                .chooseColor(Color.BLACK)
                .clickAddToCartButton()
                .clickProceedToCheckoutButton()
                .assertTotalPrice("€39.62");
    }
}
