package prestashop.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import prestashop.config.Factory;

import java.util.List;

public class MainPage extends BasePage {

    public MainPage() {
        PageFactory.initElements(Factory.getInstance().getDriver(), this);
    }

    @FindBy(xpath = "//p[@id='block-newsletter-label']")
    private WebElement emailSubscribeText;

    @FindBy(xpath = "//input[@name='s']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='col-xs-12']/p") //
    private WebElement unsubscribeText;

    @FindBy(xpath = "//input[@class='btn btn-primary float-xs-right hidden-xs-down']")
    private WebElement subscribeButton;

    @FindBy(xpath = "//button[@data-toggle='dropdown']")
    private WebElement languageButton;

    @FindBy(xpath = "//ul[@class='dropdown-menu hidden-sm-down']/li")
    private List<WebElement> languages;

    @FindBy(xpath = "//li[@class='category']/a[@class='dropdown-item']")
    private List<WebElement> hoverButtons;

    @FindBy(xpath = "//a[@class='dropdown-item dropdown-submenu']")
    private List<WebElement> dropDownMenu;

    @FindBy(xpath = "//div[@class='products row']//article[@class='product-miniature js-product-miniature']//div[@class='product-description']")
    private List<WebElement> popularClothes;

    @FindBy(xpath = "//ul[@id='footer_sub_menu_1']/li[1]/a[@id='link-product-page-prices-drop-1']")
    private WebElement priceDropButton;

    @FindBy(xpath = "//iframe[@id='framelive']")
    private WebElement framelive;

    @FindBy(xpath = "(//a[@class='all-product-link float-xs-left float-md-right h4'])[1]")
    private WebElement allProductsButton;

    @FindBy(xpath = "//span[contains(text(), 'Cart')]")
    private WebElement cartButton;

    @FindBy(xpath = "//a/span[@class='hidden-sm-down']")
    private WebElement signInButton;

    @FindBy(xpath = "//a/span[@class='hidden-sm-down']")
    private WebElement nameNearShoppingCart;

    @FindBy(xpath = "//a[@class='logout hidden-sm-down']")
    private WebElement signOutButton;

    @FindBy(xpath = "//span[@class='cart-products-count']")
    private WebElement productsInShoppingCart;


    public String getEmailSubscribeText() {
        operation.switchToFrameLive();
        return operation.getTextFromElement(emailSubscribeText, "email subscribe text");
    }

    public String getUnsubscribeText() {
        operation.switchToFrameLive();
        return operation.getTextFromElement(unsubscribeText, "unsubscribe text");
    }

    public String getSubscribeButtonText() {
        operation.switchToFrameLive();
        WebElement sb = operation.elementDisplayed(subscribeButton);
        String textTransform = sb.getCssValue("text-transform");
        String text = sb.getAttribute("defaultValue");
        if (textTransform.equalsIgnoreCase("uppercase")) {
            return text.toUpperCase();
        } else {
            return text;
        }
    }

    public List<WebElement> getLanguages() {
        operation.switchToFrameLive();
        operation.clickElement(languageButton, "language button");
        return operation.elementsDisplayed(languages);
    }

//
//    public WebElement getAmountOfProductsInShoppingCart() {
//        driver.switchTo().defaultContent();
//        driver.switchTo().frame(framelive);
//        return wait.until(ExpectedConditions.visibilityOf(productsInShoppingCart));
//    }
//
//    public MainPage refreshPage() {
//        driver.navigate().refresh();
//        return this;
//    }
//
//    public void clickSignOutButton() {
//        signOutButton.click();
//    }
//
//    public WebElement getElementNearShoppingCart(String text) {
//        wait.until(ExpectedConditions.textToBePresentInElement(nameNearShoppingCart, text));
//        return nameNearShoppingCart;
//    }
//
//    public void clickSignInButton() {
//        driver.switchTo().defaultContent();
//        driver.switchTo().frame(framelive);
//        wait.until(ExpectedConditions.visibilityOf(signInButton)).click();
//    }
//
//    public void clickPriceDropButton() {
//        driver.switchTo().defaultContent();
//        driver.switchTo().frame(framelive);
//        wait.until(ExpectedConditions.visibilityOf(priceDropButton)).click();
//    }
//
//    public void clickCartButton() {
//        driver.switchTo().defaultContent();
//        driver.switchTo().frame(framelive);
//        wait.until(ExpectedConditions.visibilityOf(cartButton)).click();
//    }
//
//    public void enterTextInSearchFieldAndPressEnter(String text) {
//        driver.switchTo().defaultContent();
//        driver.switchTo().frame(framelive);
//        wait.until(ExpectedConditions.visibilityOf(searchButton)).sendKeys(text);
//        searchButton.sendKeys(Keys.ENTER);
//    }
//
//
//    public List<WebElement> getPopularClothes() {
//        driver.switchTo().defaultContent();
//        driver.switchTo().frame(framelive);
//        wait.until(ExpectedConditions.visibilityOfAllElements(popularClothes));
//        return popularClothes;
//    }
//
//
//    public List<WebElement> getClothesHoverElements() {
//        return checkHoverButton(0);
//    }
//
//    public List<WebElement> getAccessoriesHoverElements() {
//        return checkHoverButton(1);
//    }
//
//    public List<WebElement> getArtHoverElements() {
//        return checkHoverButton(2);
//    }
//
//    public void clickAllClothesButton() {
//        driver.switchTo().defaultContent();
//        driver.switchTo().frame(framelive);
//        wait.until(ExpectedConditions.visibilityOf(allProductsButton)).click();
//    }
//
//    private List<WebElement> checkHoverButton(int index) {
//        driver.switchTo().defaultContent();
//        driver.switchTo().frame(framelive);
//        wait.until(ExpectedConditions.visibilityOfAllElements(hoverButtons));
//        action.moveToElement(hoverButtons.get(index)).perform();
//        return dropDownMenu;
//    }
}
