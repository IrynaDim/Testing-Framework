package prestashop.pages.shoppingCart;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import prestashop.pages.BasePage;
import prestashop.pages.deliveryPaymentInfo.AddDeliveryPaymentInfoPage;
import prestashop.pages.mainPage.MainPage;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    @FindBy(xpath = "//div[@class='cart-summary-line cart-total']/span[@class='value']")
    private WebElement totalPriceElement;

    @FindBy(xpath = "//div[@class='text-sm-center']/a[@class='btn btn-primary']")
    private WebElement proceedButton;

    @FindBy(xpath = "//li[@class='cart-item']//i[@class=\"material-icons float-xs-left\"]")
    private List<WebElement> deleteIcons;

    public String getTotalPrice() {
        return operation.getTextFromElement(totalPriceElement, "total price element");
    }

    protected AddDeliveryPaymentInfoPage clickProceedButton() {
        operation.clickElement(proceedButton, "proceed button", true);
        return new AddDeliveryPaymentInfoPage();
    }

    protected ShoppingCartPage clearShoppingCart() {
        waitUtil.elementsDisplayed(deleteIcons, "delete icons");
        for (WebElement icon : deleteIcons) {
            icon.click();
        }
        return this;
    }

    public MainPage refreshPage() {
        return operation.refreshPage(new MainPage(), "shopping cart");
    }
}

