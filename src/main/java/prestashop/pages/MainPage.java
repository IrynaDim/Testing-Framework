package prestashop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import prestashop.config.DriverFactory;
import prestashop.util.ProductConverter;
import prestashop.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class MainPage extends BasePage {

    public MainPage() {
        PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
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
        operation.switchToFrame(framelive, "iframe element");
        return operation.getTextFromElement(emailSubscribeText, "email subscribe text", "emailSubscribe");
    }

    public String getUnsubscribeText() {
        operation.switchToFrame(framelive, "iframe element");
        return operation.getTextFromElement(unsubscribeText, "unsubscribe text", "unsubscribe");
    }

    public String getSubscribeButtonText() {
        operation.switchToFrame(framelive, "iframe element");
        WebElement sb = waitUtil.elementDisplayed(subscribeButton, "iframe element");
        String textTransform = sb.getCssValue("text-transform");
        String text = sb.getAttribute("defaultValue");
        if (textTransform.equalsIgnoreCase("uppercase")) {
            return text.toUpperCase();
        } else {
            return text;
        }
    }

    public List<WebElement> getLanguages() {
        operation.switchToFrame(framelive, "iframe element");
        operation.clickElement(languageButton, "language button");
        return waitUtil.elementsDisplayed(languages, "languages list");
    }

    public List<Product> getPopularClothes() {
        operation.switchToFrame(framelive, "iframe element");
        return waitUtil.elementsDisplayed(popularClothes, "popular clothes list").stream().map(p -> {
            String name = p.findElement(By.xpath(".//h3[@class='h3 product-title']/a")).getText();
            String[] prices = p.findElement(By.xpath(".//div[@class='product-price-and-shipping']")).getText().split(" ");
            return ProductConverter.createProduct(prices, name);
        }).collect(Collectors.toList());
    }

    public List<WebElement> getClothesHoverElements() {
        return checkHoverButton(0);
    }

    public List<WebElement> getAccessoriesHoverElements() {
        return checkHoverButton(1);
    }

    public List<WebElement> getArtHoverElements() {
        return checkHoverButton(2);
    }

    private List<WebElement> checkHoverButton(int index) {
        operation.switchToFrame(framelive, "iframe element");
        operation.moveToElement(hoverButtons.get(index), "hover button with index " + index);
        return dropDownMenu;
    }
}
