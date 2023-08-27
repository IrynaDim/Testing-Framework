package prestashop;

import org.testng.annotations.Test;
import prestashop.model.AddressData;
import prestashop.model.User;
import prestashop.model.enums.Color;
import prestashop.model.enums.DeliveryType;
import prestashop.model.enums.PaymentOptions;
import prestashop.model.enums.SocialTitle;
import prestashop.pages.mainPage.MainPageAction;
import prestashop.pages.shoppingCart.ShoppingCartPageAction;

public class CreateOrderTest extends BaseTest {

    @Test
    public void createOrder_withTwoProducts() {
        clearShoppingCartFromMainPage(getMainPageAction()); // todo without this clickOnSearchProduct("Hummingbird Printed T-Shirt") doesnt work and i dont know why
// in firefox it didnt click on any product at all. timeout falls
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
                        "teEst7889&", "05/31/1970"))
                .fillAllCheckboxes()
                .pressContinueButton(0)
                .insertAddressData(new AddressData("Adress", "02084", "Paris", "France"))
                .pressContinueButton(2)
                .chooseDeliveryType(DeliveryType.DELIVERY)
                .pressContinueButton(2)
                .choosePaymentOption(PaymentOptions.BY_CHECK)
                .assertThatAmountEqualSubtotalPlusShipping()
                .clickIAgreeCheckBox()
                .clickConfirmOrder()
                .assertOrderIsConfirmedText("\uE876YOUR ORDER IS CONFIRMED")
                .assertTotalSum("€48.02");
    }

    private void clearShoppingCartFromMainPage(MainPageAction mainPageAction) {
        ShoppingCartPageAction shoppingCartPageAction = mainPageAction.clickCartButton();
        if (shoppingCartPageAction != null) {
            shoppingCartPageAction.clearShoppingCart().refreshPage();
        }
    }
}
