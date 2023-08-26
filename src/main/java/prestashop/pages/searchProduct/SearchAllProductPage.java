package prestashop.pages.searchProduct;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import prestashop.config.Driver;
import prestashop.pages.BasePage;
import prestashop.pages.createOrder.AddToCartPageAction;;

import java.util.List;

public class SearchAllProductPage extends BasePage {

    public SearchAllProductPage() {
        PageFactory.initElements(Driver.getInstance().getDriver(), this);
    }

    @FindBy(xpath = "//button[@class='btn-unstyle select-title']")
    private WebElement sortByButton;

    @FindBy(xpath = "//a[contains(text(), 'Name, A to Z')]")
    private WebElement sortByAtoZButton;

    @FindBy(xpath = "//a[contains(text(), 'Name, Z to A')]")
    private WebElement sortByZtoAButton;

    @FindBy(xpath = "//a[contains(text(), 'Price, low to high')]")
    private WebElement sortByPriceIncreaseButton;

    @FindBy(xpath = "//a[contains(text(), 'Price, high to low')]")
    private WebElement sortByPriceDecreaseButton;

    @FindBy(xpath = "//div[@class='js-product product col-xs-12 col-sm-6 col-xl-4']")
    private List<WebElement> products;

    @FindBy(xpath = "//h2[@class='h3 product-title']/a")
    private List<WebElement> searchProducts;

    protected AddToCartPageAction clickOnSearchProduct(String name) {
        waitUtil.elementsDisplayed(searchProducts, "search product " + name);

        for (WebElement p : searchProducts) {
            if (p.getText().equalsIgnoreCase(name)) {
                operation.clickElement(p, name);
                break;
            }
        }
        return new AddToCartPageAction();
    }
}
