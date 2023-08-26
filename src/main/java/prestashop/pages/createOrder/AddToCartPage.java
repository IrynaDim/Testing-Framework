package prestashop.pages.createOrder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import prestashop.config.Driver;
import prestashop.model.enums.Color;
import prestashop.pages.BasePage;
import prestashop.pages.approveOrder.ApproveOrderPage;
import prestashop.pages.searchProduct.SearchAllProductPage;

import java.util.List;

public class AddToCartPage extends BasePage {

    public AddToCartPage() {
        PageFactory.initElements(Driver.getInstance().getDriver(), this);
    }

    @FindBy(xpath = "//select[@class='form-control form-control-select']")
    private WebElement paperTypeComboBoxButton;

    @FindBy(xpath = "//option[@value='25']")
    private WebElement dotedPageType;

    @FindBy(xpath = "//option[@value='22']")
    private WebElement ruledPageType;

    @FindBy(xpath = "//option[@value='23']")
    private WebElement plainPageType;

    @FindBy(xpath = "//input[@name='s']")
    private WebElement searchField;

    @FindBy(xpath = "//option[@value='24']")
    private WebElement squarredPageType;

    @FindBy(xpath = "//input[@id='quantity_wanted']")
    private WebElement quantityElement;

    @FindBy(xpath = "//button[@class='btn btn-primary add-to-cart']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//textarea[@class='product-message']")
    private WebElement productCustomizationElement;

    @FindBy(xpath = "//button[@class='btn btn-primary float-xs-right']")
    private WebElement saveProductCustomizationButton;

    @FindBy(xpath = "//input[@class='input-color']")
    private List<WebElement> colors;

    @FindBy(xpath = "//label[@class='customization-label']")
    private WebElement yourCustomizationText;

    @FindBy(xpath = "//div[@class='header']/a[@rel='nofollow']")
    private WebElement shoppingCartButton;

    public AddToCartPage chooseColor(Color color) {
        switch (color) {
            case BLACK:
                colors.get(1).click(); // todo dont know why but if using operation and waitUtil - fails on wait visibility of element
                break;
            case WHITE:
                colors.get(0).click();
                break;
        }
        return this;
    }

    protected AddToCartPage addProductCustomization(String text) {
        operation.insertTextToElement(productCustomizationElement,
                text,
                "product customization field");
        return this;
    }

    protected AddToCartPage clickSaveCustomizationButton() {
        operation.clickElement(saveProductCustomizationButton,
                "save product customization button");
        waitUtil.elementDisplayed(yourCustomizationText,
                "Your customization text");
        return this;
    }

    protected AddToCartPage insertQuantity(int quantity) {
        operation.javaScriptInsertText(quantityElement,
                "arguments[0].value = arguments[1]",
                String.valueOf(quantity),
                "quantity");
        return this;
    }

    protected ApproveOrderPage pressAddToCartButton() {
        operation.clickElement(addToCartButton, "add to cart button");
        return new ApproveOrderPage();
    }

    public SearchAllProductPage insertTextInSearchFieldAndPressEnter(String text) {
        operation.insertTextToElement(searchField, text, "search field");
        operation.insertTextToElement(searchField, Keys.ENTER, "search field");
        return new SearchAllProductPage();
    }
}

