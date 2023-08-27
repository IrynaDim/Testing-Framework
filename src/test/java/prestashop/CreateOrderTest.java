package prestashop;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import prestashop.model.User;
import prestashop.model.enums.Color;
import prestashop.model.enums.SocialTitle;
import prestashop.pages.mainPage.MainPageAction;
import prestashop.pages.shoppingCart.ShoppingCartPageAction;

public class CreateOrderTest extends BaseTest {

  //  @BeforeTest
    public void clearShoppingCart() {
        clearShoppingCartFromMainPage(getMainPageAction());
    }

    @Test
    public void createOrder_withTwoProducts() {
        getMainPageAction()
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
                .assertTotalPrice("€39.62")
                .clickProsedButton()
                .insertPersonalData(new User(SocialTitle.MR, "FirstName", "LastName", "email@email.com",
                        "teEst7889&", "05/31/1970", "Adress", "02084", "Paris", "France"))
                .fillAllCheckboxes();
    }

    private void clearShoppingCartFromMainPage(MainPageAction mainPageAction) {
        ShoppingCartPageAction shoppingCartPageAction = mainPageAction.clickCartButton();
        if (shoppingCartPageAction != null) {
            shoppingCartPageAction.clearShoppingCart().refreshPage();
        }
    }
}
