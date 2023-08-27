package prestashop.pages.deliveryPaymentInfo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import prestashop.config.Driver;
import prestashop.model.enums.DeliveryType;
import prestashop.model.enums.PaymentOptions;
import prestashop.model.enums.SocialTitle;
import prestashop.pages.BasePage;
import prestashop.pages.mainPage.MainPage;
import prestashop.pages.orderIsConfirmed.OrderIsConfirmedPage;

import java.util.List;

public class AddDeliveryPaymentInfoPage extends BasePage {

    public AddDeliveryPaymentInfoPage() {
        PageFactory.initElements(Driver.getInstance().getDriver(), this);
    }

    @FindBy(xpath = "//input[@name='id_gender']")
    private List<WebElement> genders;

    @FindBy(xpath = "//input[@id='field-firstname']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@id='field-lastname']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@id='field-email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='field-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='field-birthday']")
    private WebElement birthdayField;

    @FindBy(xpath = "//input[@id='field-address1']")
    private WebElement addressField;

    @FindBy(xpath = "//input[@id='field-postcode']")
    private WebElement postCodeField;

    @FindBy(xpath = "//input[@id='field-city']")
    private WebElement cityField;

    @FindBy(xpath = "//span[@class='custom-checkbox']")
    private List<WebElement> checkboxes;

    @FindBy(xpath = "//div[@class='row delivery-option js-delivery-option']")
    private List<WebElement> deliveryType;

    @FindBy(xpath = "//input[@class='ps-shown-by-js ']")
    private List<WebElement> paymentOptions;

    @FindBy(xpath = "//button[@class='continue btn btn-primary float-xs-right']")
    private List<WebElement> continueButtons;

    @FindBy(xpath = "//label[@class='js-terms']")
    private WebElement termsAndConditionsCheckBox;

    @FindBy(xpath = "//div[@class='cart-summary-line cart-summary-subtotals']//span[@class='value']")
    private List<WebElement> prices;

    @FindBy(xpath = "//div[@id='payment-option-3-additional-information']//dd")
    private List<WebElement> ddElements;

    @FindBy(xpath = "//label[@class='js-terms']")
    private WebElement iAgreeCheckBox;

    @FindBy(xpath = "//button[@class='btn btn-primary center-block']")
    private WebElement submitOrderButton;

    public String getAmount() {
        return operation.getTextFromElement(ddElements.get(0), "amount");
    }

    public String getSubTotal() {
        return operation.getTextFromElement(prices.get(0), "sub total price");
    }

    public String getShipping() {
        return operation.getTextFromElement(prices.get(1), "shipping price");
    }

    public AddDeliveryPaymentInfoPage choosePaymentOptions(PaymentOptions option) {
        switch (option) {
            case BY_BANK_WRITE:
                operation.clickElement(paymentOptions.get(0), "payment option by bank write");
                break;
            case BY_CASH_ON_DELIVERY:
                operation.clickElement(paymentOptions.get(1), "payment option by cash on delivery");
                break;
            case BY_CHECK:
                operation.clickElement(paymentOptions.get(2), "payment option by check");
                break;
        }
        return this;
    }

    public AddDeliveryPaymentInfoPage clickIAgreeCheckBox() {
        operation.clickElement(iAgreeCheckBox, "i agree checkbox");
        return this;
    }

    public OrderIsConfirmedPage clickISubmitOrderButton() {
        operation.clickElement(submitOrderButton, "submit order button");
        return new OrderIsConfirmedPage();
    }

    public AddDeliveryPaymentInfoPage chooseDeliveryType(DeliveryType type) {
        switch (type) {
            case PICKUP:
                operation.clickElement(deliveryType.get(0), "pickup delivery type");
                break;
            case DELIVERY:
                operation.clickElement(deliveryType.get(1), "delivery type");
                break;
        }
        return this;
    }

    public AddDeliveryPaymentInfoPage pressContinueButton(int count) {
        operation.clickElement(continueButtons.get(count), "continue button with index " + count);
        return this;
    }

    public AddDeliveryPaymentInfoPage fillAllCheckbox() {
        for (WebElement webElement : checkboxes) {
            operation.clickElement(webElement, "click checkbox element");
        }
        return this;
    }

    public AddDeliveryPaymentInfoPage chooseGender(SocialTitle socialTitle) {
        switch (socialTitle) {
            case MR:
                operation.clickElement(genders.get(0), "MR social title");
                break;
            case MRS:
                operation.clickElement(genders.get(1), "MRS social title");
                break;
        }
        return this;
    }

    public MainPage refreshPage() {
        return operation.refreshPage(new MainPage(), "delivery payment info page");
    }

    public AddDeliveryPaymentInfoPage fillFirstName(String name) {
        operation.insertTextToElement(firstNameField, name, "first name field");
        return this;
    }

    public AddDeliveryPaymentInfoPage fillAddress(String address) {
        operation.insertTextToElement(address, address, "address field");
        return this;
    }

    public AddDeliveryPaymentInfoPage fillCity(String city) {
        operation.insertTextToElement(cityField, city, "city field");
        return this;
    }

    public AddDeliveryPaymentInfoPage fillPostCode(String postCode) {
        operation.insertTextToElement(postCodeField, postCode, "postcode field");
        return this;
    }

    public AddDeliveryPaymentInfoPage fillLastName(String name) {
        operation.insertTextToElement(lastNameField, name, "last name field");
        return this;
    }

    public AddDeliveryPaymentInfoPage fillEmail(String email) {
        operation.insertTextToElement(emailField, email, "email field");
        return this;
    }

    public AddDeliveryPaymentInfoPage fillBirthrate(String birthrate) {
        operation.insertTextToElement(birthdayField, birthrate, "birthrate field");
        return this;
    }

}
