package prestashop;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import prestashop.model.AddressData;
import prestashop.model.User;
import prestashop.model.enums.Color;
import prestashop.model.enums.DeliveryType;
import prestashop.model.enums.PaymentOptions;
import prestashop.model.enums.SocialTitle;
import prestashop.pages.mainPage.MainPageAction;
import prestashop.pages.shoppingCart.ShoppingCartPageAction;
import prestashop.util.TestListener;

@Listeners(TestListener.class)
@Slf4j
public class CreateOrderTest extends BaseTest {

    //todo is it ok to has several assesrts in the test and in the middle of test?
    // .assertThatAmountEqualSubtotalPlusShipping() then do another actions than assert again?
    // I did everything like in test case 10. So is this correct implementation?

    @Test
    public void createOrder_withTwoProducts() {
        //todo is it ok to separate test methods like this in logger file?
        log.info("\n ------------------- createOrder_withTwoProducts test start --------------------------");
        clearShoppingCartFromMainPage(getMainPageAction()); // todo i did it to be sure that shipping cart in empty so it
        // dont affect on the test. is it ok?

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
        log.info("------------------- createOrder_withTwoProducts test end --------------------------");
    }

    private void clearShoppingCartFromMainPage(MainPageAction mainPageAction) {
        ShoppingCartPageAction shoppingCartPageAction = mainPageAction.clickCartButton();
        if (shoppingCartPageAction != null) {
            shoppingCartPageAction.clearShoppingCart().refreshPage();
        }
    }
}
