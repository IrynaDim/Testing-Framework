package prestashop.pages.deliveryPaymentInfo;

import prestashop.model.User;

public class AddDeliveryPaymentInfoPageAction {
    private final AddDeliveryPaymentInfoPage deliveryPaymentInfoPage = new AddDeliveryPaymentInfoPage();

    public AddDeliveryPaymentInfoPageAction insertPersonalData(User user) {
        deliveryPaymentInfoPage.chooseGender(user.getSocialTitle());
        deliveryPaymentInfoPage.fillFirstName(user.getFirstName());
        deliveryPaymentInfoPage.fillLastName(user.getLastName());
        deliveryPaymentInfoPage.fillEmail(user.getEmail());
        deliveryPaymentInfoPage.fillBirthrate(user.getBirthdate());
        return this;
    }

    public AddDeliveryPaymentInfoPageAction fillAllCheckboxes() {
        deliveryPaymentInfoPage.fillAllCheckbox();
        return this;

    }
}
