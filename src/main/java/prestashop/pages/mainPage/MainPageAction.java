package prestashop.pages.mainPage;

import com.aventstack.extentreports.ExtentTest;
import prestashop.config.Driver;
import prestashop.config.Reporting;
import prestashop.model.Product;
import prestashop.pages.searchProduct.SearchAllProductPageAction;
import prestashop.pages.shoppingCart.ShoppingCartPageAction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import static org.testng.AssertJUnit.*;

public class MainPageAction {
    private final MainPage mainPage = new MainPage();
    private final String startPage = "https://demo.prestashop.com/#/en/front";

    public ExtentTest getLog() {
        return Reporting.threadReport.get();
    }

    /**
     * Action on page
     **/
    public SearchAllProductPageAction enterTextInSearchFieldAndPressEnter(String text) {
        mainPage.enterTextInSearchFieldAndPressEnter(text);
        return new SearchAllProductPageAction();
    }

    public MainPageAction goToHomePage() {
        Driver.getInstance().getDriver().get(startPage);
        getLog().info("Switch to home page");
        return this;
    }

    public ShoppingCartPageAction clickCartButton() {
        if (!mainPage.getAmountOfProductsInShoppingCart().equals("(0)")) {
            mainPage.clickCartButton();
            return new ShoppingCartPageAction();
        }
        return null;
    }

    public List<String> getLanguages() {
        return mainPage.getLanguages();
    }

    public List<String> getArtHoverElements() {
        List<String> artHoverElements = mainPage.getArtHoverElements();
        artHoverElements.removeIf(String::isEmpty);
        return artHoverElements;
    }

    public List<String> getAccessoriesHoverElements() {
        List<String> accessoriesHoverElements = mainPage.getAccessoriesHoverElements();
        accessoriesHoverElements.removeIf(String::isEmpty);
        return accessoriesHoverElements;
    }

    public List<String> getClothesHoverElements() {
        List<String> clothesHoverElements = mainPage.getClothesHoverElements();
        clothesHoverElements.removeIf(String::isEmpty);
        return clothesHoverElements;
    }

    public String getUnsubscribeText() {
        return mainPage.getUnsubscribeText();
    }

    public String getEmailSubscribeText() {
        return mainPage.getEmailSubscribeText();
    }

    public String getSubscribeButtonText() {
        return mainPage.getSubscribeButtonText();
    }

    public List<Product> getPopularProducts() {
        return mainPage.getPopularClothes();
    }

    /**
     * Asserts
     **/

    //todo is it ok to make this methods return MainPageAction? I did it to make possibility to call chains of asserts
    // or to do other action on the main page
    public MainPageAction assertThatLanguageIsPresent(String language) {
        long size = getLanguages().stream()
                .filter(l -> l
                        .equals("Українська"))
                .count();
        assertEquals("No " + language + " language in the list.", size, 1L);
        return this;
    }

    public MainPageAction assertLanguageSize(int size) {
        List<String> languages = getLanguages();
        assertEquals("Language size is: " + languages.size(), languages.size(), 46);
        return this;
    }

    public MainPageAction assertThatClothesHoverArtIsEmpty() {
        List<String> clothesHoverElements = getArtHoverElements();
        assertEquals("ClothesHoverElements contains " + createStringFromList(clothesHoverElements),
                clothesHoverElements.size(), 0);
        return this;
    }

    public MainPageAction assertThatClothesHoverAccessoriesIsNotEmpty() {
        List<String> clothesHoverElements = getAccessoriesHoverElements();
        assertEquals("Clothes hover element list contains " + createStringFromList(clothesHoverElements), clothesHoverElements.size(), 2);
        assertEquals("First element is " + clothesHoverElements.get(0), clothesHoverElements.get(0), "STATIONERY");
        assertEquals("Second element is" + clothesHoverElements.get(1), clothesHoverElements.get(1), "HOME ACCESSORIES");
        return this;
    }

    public MainPageAction assertThatClothesHoverIsNotEmpty() {
        List<String> clothesHoverElements = getClothesHoverElements();
        assertEquals("Clothes hover element list contains: " + createStringFromList(clothesHoverElements), clothesHoverElements.size(), 2);
        assertEquals("First element is " + clothesHoverElements.get(0), clothesHoverElements.get(0), "MEN");
        assertEquals("Second element is" + clothesHoverElements.get(1), clothesHoverElements.get(1), "WOMEN");
        return this;
    }

    public MainPageAction assertUnsubscribeText(String text) {
        String unsubscribeElement = getUnsubscribeText();
        assertEquals("Unsubscribe element text is " + unsubscribeElement, text, unsubscribeElement);
        return this;
    }

    public MainPageAction assertEmailSubscribeText(String text) {
        String emailSubscribeElement = getEmailSubscribeText();
        assertEquals("Email subscribe element text is " + emailSubscribeElement, text, emailSubscribeElement);
        return this;
    }

    public MainPageAction assertSubscribeButtonText(String text) {
        String textFromButton = getSubscribeButtonText();
        assertEquals("Text on subscribe button is " + textFromButton, text, textFromButton);
        return this;
    }

    public MainPageAction assertPopularClothes(int size) {
        List<Product> popularClothes = getPopularProducts();
        Set<Product> uniqueProducts = new HashSet<>(popularClothes);
        assertEquals(popularClothes.size(), uniqueProducts.size());
        assertEquals(popularClothes.size(), size);
        popularClothes.forEach(p -> {
            assertNotNull("Currency is null", p.getCurrency());
            assertNotNull("Product name is null", p.getName());
            assertNotNull("Price is null", p.getPrice());
            assertTrue("Price is lower than 0.00: " + p.getPrice(), p.getPrice() > 0.00);
        });
        return this;
    }

    /**
     * Private methods
     **/
    private String createStringFromList(List<String> list) {
        StringJoiner joiner = new StringJoiner(", ");
        for (String element : list) {
            joiner.add(element);
        }
        return joiner.toString();
    }
}
