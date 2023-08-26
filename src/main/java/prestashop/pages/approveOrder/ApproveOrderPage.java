package prestashop.pages.approveOrder;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import prestashop.config.Driver;
import prestashop.pages.BasePage;
import prestashop.pages.createOrder.AddToCartPageAction;
import prestashop.pages.shoppingCart.ShoppingCartPage;

public class ApproveOrderPage extends BasePage {

    public ApproveOrderPage() {
        PageFactory.initElements(Driver.getInstance().getDriver(), this);
    }

    @FindBy(xpath = "//h4[@class='modal-title h6 text-sm-center']")
    private WebElement productAddedText;

    @FindBy(xpath = "//h4[@class='modal-title h6 text-sm-center']")
    private WebElement paperTypeElement;

    @FindBy(xpath = "//h4[@class='modal-title h6 text-sm-center']")
    private WebElement quantityElement;

    @FindBy(xpath = "//span[@class='subtotal value']")
    private WebElement subTotalPrice;

    @FindBy(xpath = "//span[@class='shipping value']")
    private WebElement shippingPrice;

    @FindBy(xpath = "//button[@class='btn btn-secondary']")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//div[@class='cart-content-btn']/a[@class='btn btn-primary']")
    private WebElement proceedToCheckoutButton;


    protected AddToCartPageAction clickContinueShoppingButton() {
        operation.clickElement(continueShoppingButton, "continue shopping button");
        return new AddToCartPageAction();
    }

    protected ShoppingCartPage clickProceedToCheckoutButton() {
        operation.clickElement(proceedToCheckoutButton, "proceed  to checkout button");
        return new ShoppingCartPage();
    }
}
