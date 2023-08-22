package prestashop.pages.mainPage;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebElement;
import prestashop.config.Driver;
import prestashop.config.Reporting;
import prestashop.model.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.*;

public class MainPageAction {
    private final MainPage mainPage = new MainPage();
    private final String startPage = "https://demo.prestashop.com/#/en/front";

    public ExtentTest getLog() {
        return Reporting.threadReport.get();
    }

    public MainPageAction goToHomePage() {
        Driver.getInstance().getDriver().get(startPage);
        getLog().info("Switch to home page");
        return this;
    }

    /**
     * Asserts
     **/
    public void assertThatLanguageIsPresent(String language) {
        List<WebElement> languages = mainPage.getLanguages();
        long size = languages.stream()
                .filter(l -> l.getText()
                        .equals("Українська"))
                .count();
        assertEquals("No " + language + " language in the list.", size, 1L);
    }

    public void assertLanguageSize() {
        List<WebElement> languages = mainPage.getLanguages();
        assertEquals("Language size is: " + languages.size(), languages.size(), 46);
    }

    public void assertThatClothesHoverArtIsEmpty() {
        List<String> clothesHoverElements = convertWebElementsToStrings(mainPage.getArtHoverElements());
        assertEquals("ClothesHoverElements contains " + createStringFromList(clothesHoverElements),
                clothesHoverElements.size(), 0);
    }

    public void assertThatClothesHoverAccessoriesIsNotEmpty() {
        List<String> clothesHoverElements = convertWebElementsToStrings(mainPage.getAccessoriesHoverElements());
        assertEquals("Clothes hover element list contains " + createStringFromList(clothesHoverElements), clothesHoverElements.size(), 2);
        assertEquals("First element is " + clothesHoverElements.get(0), clothesHoverElements.get(0), "STATIONERY");
        assertEquals("Second element is" + clothesHoverElements.get(1), clothesHoverElements.get(1), "HOME ACCESSORIES");
    }

    public void assertThatClothesHoverIsNotEmpty() {
        List<String> clothesHoverElements = convertWebElementsToStrings(mainPage.getClothesHoverElements());
        assertEquals("Clothes hover element list contains: " + createStringFromList(clothesHoverElements), clothesHoverElements.size(), 2);
        assertEquals("First element is " + clothesHoverElements.get(0), clothesHoverElements.get(0), "MEN");
        assertEquals("Second element is" + clothesHoverElements.get(1), clothesHoverElements.get(1), "WOMEN");
    }

    public void assertUnsubscribeText() {
        String unsubscribeElement = mainPage.getUnsubscribeText();
        assertEquals("Unsubscribe element text is " + unsubscribeElement, "You may unsubscribe at any moment. " +
                "For that purpose, please find our contact info in the legal notice.", unsubscribeElement);
    }

    public void assertEmailSubscribeText() {
        String emailSubscribeElement = mainPage.getEmailSubscribeText();
        assertEquals("Email subscribe element text is " + emailSubscribeElement, "Get our latest news and special sales", emailSubscribeElement);
    }

    public void assertSubscribeButtonText() {
        String text = mainPage.getSubscribeButtonText();
        assertEquals("Text on subscribe button is " + text, "SUBSCRIBE", text);
    }

    public void assertPopularClothes() {
        List<Product> popularClothes = mainPage.getPopularClothes();
        Set<Product> uniqueProducts = new HashSet<>(popularClothes);
        assertEquals(popularClothes.size(), uniqueProducts.size());
        assertEquals(popularClothes.size(), 8);
        popularClothes.forEach(p -> {
            assertNotNull("Currency is null", p.getCurrency());
            assertNotNull("Product name is null", p.getName());
            assertNotNull("Price is null", p.getPrice());
            assertTrue("Price is lower than 0.00: " + p.getPrice(), p.getPrice() > 0.00);
        });
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

    private List<String> convertWebElementsToStrings(List<WebElement> webElements) {
        List<String> result = webElements.stream().map(WebElement::getText)
                .collect(Collectors.toList());
        result.removeIf(String::isEmpty);
        return result;
    }
}
