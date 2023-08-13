package prestashop;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import prestashop.config.Factory;
import prestashop.model.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.*;


public class MainPageTest extends BaseTest {

    @Test
    public void checkUkrainianLanguageExist() {
        Factory.getInstance().getDriver().navigate().refresh();
        List<WebElement> languages = getMainPage().getLanguages();
        long size = languages.stream()
                .filter(l -> l.getText()
                        .equals("Українська"))
                .count();
        //не очень понятно что там сравнивается,
        // я бы использовал или AssertJ c сообщением в случае ошибки
        // или же сделал метод с понятным названием и туда помеситл ассерт
        assertTrue(softAssertion.compareObjects(size, 1L, size + ""));
    }

    @Test
    public void checkLanguageSize() {
        Factory.getInstance().getDriver().navigate().refresh();
        List<WebElement> languages = getMainPage().getLanguages();
        assertTrue(softAssertion.compareObjects(languages.size(), 46, languages.size() + ""));
    }

    @Test
    public void checkArtClothesButton_emptyListOfArts() {
        Factory.getInstance().getDriver().navigate().refresh();
        List<String> clothesHoverElements = getMainPage().getArtHoverElements().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        clothesHoverElements.removeIf(String::isEmpty);
        System.out.println(clothesHoverElements.size());
        assertTrue(softAssertion.compareObjects(clothesHoverElements.size(), 0, clothesHoverElements.size() + ""));
    }

    @Test
    public void checkUnSubscribeText() {
        Factory.getInstance().getDriver().navigate().refresh();
        String unsubscribeElement = getMainPage().getUnsubscribeText();
        assertTrue(softAssertion.compareObjects("You may unsubscribe at any moment. " +
                "For that purpose, please find our contact info in the legal notice.", unsubscribeElement, unsubscribeElement));
    }

    @Test
    public void checkSubscribeText() {
        Factory.getInstance().getDriver().navigate().refresh();
        String emailSubscribeElement = getMainPage().getEmailSubscribeText();
        assertTrue(softAssertion.compareObjects("Get our latest news and special sales", emailSubscribeElement, emailSubscribeElement));
    }

    @Test
    public void checkAccessoriesClothesButton_notEmptyListOfAccessories() {
        Factory.getInstance().getDriver().navigate().refresh();
        List<String> clothesHoverElements = getMainPage().getAccessoriesHoverElements().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        clothesHoverElements.removeIf(String::isEmpty);
        assertTrue(softAssertion.compareObjects(clothesHoverElements.size(), 2, clothesHoverElements.size() + ""));
        assertTrue(softAssertion.compareObjects(clothesHoverElements.get(0), "STATIONERY", clothesHoverElements.get(0)));
        assertTrue(softAssertion.compareObjects(clothesHoverElements.get(1), "HOME ACCESSORIES", clothesHoverElements.get(1)));
    }

    @Test
    public void checkHoverClothesButton_notEmptyListOfClothes() {
        Factory.getInstance().getDriver().navigate().refresh();
        List<String> clothesHoverElements = getMainPage().getClothesHoverElements().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        clothesHoverElements.removeIf(String::isEmpty);
        assertTrue(softAssertion.compareObjects(clothesHoverElements.size(), 2, clothesHoverElements.size() + ""));
        assertTrue(softAssertion.compareObjects(clothesHoverElements.get(0), "MEN", clothesHoverElements.get(0)));
        assertTrue(softAssertion.compareObjects(clothesHoverElements.get(1), "WOMEN", clothesHoverElements.get(1)));
    }

    @Test
    public void checkSubscribeButtonText_upperCase() {
        Factory.getInstance().getDriver().navigate().refresh();
        String text = getMainPage().getSubscribeButtonText();
        assertTrue(softAssertion.compareObjects("SUBSCRIBE", text, text));
    }

    @Test
    public void checkPopularClothes_CurrencyNameAndPriceNotNull() {
        Factory.getInstance().getDriver().navigate().refresh();
        List<Product> popularClothes = getMainPage().getPopularClothes();
        Set<Product> uniqueProducts = new HashSet<>(popularClothes);
        assertEquals(popularClothes.size(), uniqueProducts.size());
        assertEquals(popularClothes.size(), 8);
        popularClothes.forEach(p -> {
            assertNotNull(p.getCurrency());
            assertNotNull(p.getName());
            assertNotNull(p.getPrice());
            assertTrue(p.getPrice() > 0.00);
        });
    }
}
