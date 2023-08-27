package prestashop.pages.deliveryPaymentInfo;

import prestashop.model.AddressData;
import prestashop.model.User;
import prestashop.model.enums.DeliveryType;
import prestashop.model.enums.PaymentOptions;
import prestashop.pages.orderIsConfirmed.OrderIsConfirmedPageAction;

import static org.testng.AssertJUnit.assertEquals;

public class AddDeliveryPaymentInfoPageAction {
    private final AddDeliveryPaymentInfoPage deliveryPaymentInfoPage = new AddDeliveryPaymentInfoPage();

    /**
     * Actions
     **/
    public AddDeliveryPaymentInfoPageAction insertPersonalData(User user) {
        deliveryPaymentInfoPage.chooseGender(user.getSocialTitle());
        deliveryPaymentInfoPage.fillFirstName(user.getFirstName());
        deliveryPaymentInfoPage.fillLastName(user.getLastName());
        deliveryPaymentInfoPage.fillEmail(user.getEmail());
        deliveryPaymentInfoPage.fillBirthrate(user.getBirthday());
        return this;
    }

    public AddDeliveryPaymentInfoPageAction insertAddressData(AddressData addressData) {
        deliveryPaymentInfoPage.fillAddress(addressData.getAddress());
        deliveryPaymentInfoPage.fillPostCode(addressData.getZip());
        deliveryPaymentInfoPage.fillCity(addressData.getCity());
        return this;
    }

    public AddDeliveryPaymentInfoPageAction fillAllCheckboxes() {
        deliveryPaymentInfoPage.fillAllCheckbox();
        return this;
    }

    public AddDeliveryPaymentInfoPageAction pressContinueButton(int count) {
        deliveryPaymentInfoPage.pressContinueButton(count);
        return this;
    }

    public AddDeliveryPaymentInfoPageAction choosePaymentOption(PaymentOptions paymentOptions) {
        deliveryPaymentInfoPage.choosePaymentOptions(paymentOptions);
        return this;
    }

    public AddDeliveryPaymentInfoPageAction chooseDeliveryType(DeliveryType deliveryType) {
        deliveryPaymentInfoPage.chooseDeliveryType(deliveryType);
        return this;
    }

    public AddDeliveryPaymentInfoPageAction clickIAgreeCheckBox() {
        deliveryPaymentInfoPage.clickIAgreeCheckBox();
        return this;
    }

    public OrderIsConfirmedPageAction clickConfirmOrder() {
        deliveryPaymentInfoPage.clickISubmitOrderButton();
        return new OrderIsConfirmedPageAction();
    }

    /**
     * Asserts
     **/
    public AddDeliveryPaymentInfoPageAction assertThatAmountEqualSubtotalPlusShipping() {
        String replaceTextRegex = "[^\\d.]";
        String emptyString = "";

        double subtotal = Double.parseDouble(deliveryPaymentInfoPage.getSubTotal().replaceAll(replaceTextRegex, emptyString));
        double shipping = Double.parseDouble(deliveryPaymentInfoPage.getShipping().replaceAll(replaceTextRegex, emptyString));
        String amount = deliveryPaymentInfoPage.getAmount().replaceAll(replaceTextRegex, emptyString);

        double actualAmount = Double.parseDouble(amount.substring(0, amount.length() - 1));
        double result = Math.floor((subtotal + shipping) * 100) / 100;
        assertEquals("Amount is not equal. Actual amount is " + amount, actualAmount, result);
        return this;
    }
}
