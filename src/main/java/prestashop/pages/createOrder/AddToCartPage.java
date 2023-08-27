package prestashop.pages.createOrder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import prestashop.exception.FailTest;
import prestashop.model.enums.Color;
import prestashop.pages.BasePage;
import prestashop.pages.approveOrder.ApproveOrderPage;
import prestashop.pages.searchProduct.SearchAllProductPage;

import java.util.List;

public class AddToCartPage extends BasePage {

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

    protected AddToCartPage chooseColor(Color color) {
        switch (color) {
            case BLACK:
                operation.clickElement(colors.get(1), "black color", false); // todo dont know why but if using operation and waitUtil - fails on wait visibility of element
                return this;
            case WHITE:
                operation.clickElement(colors.get(0), "black color", false);
                return this;
        }
        throw new FailTest("Color was not picked");
    }

    protected AddToCartPage addProductCustomization(String text) {
        operation.insertTextToElement(productCustomizationElement,
                text,
                "product customization field");
        return this;
    }

    protected AddToCartPage clickSaveCustomizationButton() {
        operation.clickElement(saveProductCustomizationButton,
                "save product customization button", true);
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
        operation.clickElement(addToCartButton, "add to cart button", true);
        return new ApproveOrderPage();
    }

    protected SearchAllProductPage insertTextInSearchFieldAndPressEnter(String text) {
        operation.insertTextToElement(searchField, text, "search field");
        operation.insertTextToElement(searchField, Keys.ENTER, "search field");
        return new SearchAllProductPage();
    }
}

