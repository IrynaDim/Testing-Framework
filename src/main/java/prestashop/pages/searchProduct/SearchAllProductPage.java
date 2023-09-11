package prestashop.pages.searchProduct;

import lombok.SneakyThrows;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import prestashop.exception.FailTest;
import prestashop.pages.BasePage;
import prestashop.pages.createOrder.AddToCartPageAction;

import java.util.List;

public class SearchAllProductPage extends BasePage {

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

    @FindBy(xpath = ".//h2[@class='h3 product-title']/a")
    private List<WebElement> searchProducts;

    @SneakyThrows
    protected AddToCartPageAction clickOnSearchProduct(String name) {
        Thread.sleep(300);
        waitUtil.elementsDisplayed(searchProducts, "search product " + name);
        for (WebElement p : searchProducts) {
            if (p.getText().equalsIgnoreCase(name)) {
                operation.clickElementWithoutWait(p, name);
                return new AddToCartPageAction();
            }
        }
        throw new FailTest("Product was not clicked: " + name);
    }
}
