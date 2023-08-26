package prestashop.pages.shoppingCart;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import prestashop.config.Driver;
import prestashop.pages.BasePage;
import prestashop.pages.mainPage.MainPage;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    public ShoppingCartPage() {
        PageFactory.initElements(Driver.getInstance().getDriver(), this);
    }

    @FindBy(xpath = "//div[@class='cart-summary-line cart-total']/span[@class='value']")
    private WebElement totalPriceElement;

    @FindBy(xpath = "//div[@class='text-sm-center']/a[@class='btn btn-primary']")
    private WebElement proceedButton;

    @FindBy(xpath = "//li[@class='cart-item']//i[@class=\"material-icons float-xs-left\"]")
    private List<WebElement> deleteIcons;

    public String getTotalPrice() {
        return operation.getTextFromElement(totalPriceElement, "total price element");
    }

//    public AddDeliveryPaymentInfoPage clickProceedButton() {
//        wait.until(ExpectedConditions.visibilityOf(proceedButton)).click();
//        return new AddDeliveryPaymentInfoPage();
//    }

    public ShoppingCartPage clearShoppingCart() {
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

