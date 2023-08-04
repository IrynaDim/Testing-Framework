package prestashop.service;

import lombok.extern.slf4j.Slf4j;
import prestashop.model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import prestashop.pages.MainPage;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class MainService {
    private final MainPage mainPage = new MainPage();

    public List<String> getLanguages() {
        List<String> result = mainPage.getLanguages().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        log.info("getLanguages method return list with size " + result.size());
        return result;
    }

    public List<Product> getPopularClothes() {
        List<Product> result = mainPage.getPopularClothes().stream().map(p -> {
            String name = p.findElement(By.xpath(".//h3[@class='h3 product-title']/a")).getText();
            String[] prices = p.findElement(By.xpath(".//div[@class='product-price-and-shipping']")).getText().split(" ");
            return createProduct(prices, name);
        }).collect(Collectors.toList());
        log.info("getPopularClothes method return list with size " + result.size());
        return result;
    }

    public String getSubscribeButtonText() {
        WebElement subscribeButton = mainPage.getSubscribeButtonElement();
        String textTransform = subscribeButton.getCssValue("text-transform");
        String text = subscribeButton.getAttribute("defaultValue");
        log.info("getSubscribeButtonText method return subscribe button text: " + text);
        if (textTransform.equalsIgnoreCase("uppercase")) {
            return text.toUpperCase();
        } else {
            return text;
        }
    }

    public String getEmailSubscribeText() {
        String text = mainPage.getEmailSubscribeElement().getText();
        log.info("getEmailSubscribeText method return: " + text);
        return text;
    }

    public String getUnsubscribeText() {
        String text = mainPage.getUnsubscribeElement().getText();
        log.info("getUnsubscribeText method return: " + text);
        return text;
    }

    public List<String> getClothesHoverElements() {
        List<String> result = mainPage.getClothesHoverElements().stream().map(WebElement::getText)
                .filter(i -> !i.equals("")).collect(Collectors.toList());
        log.info("getClothesHoverElements method return list with size: " + result.size());
        return result;
    }

    public List<String> getAccessoriesHoverElements() {
        List<String> result = mainPage.getAccessoriesHoverElements().stream().map(WebElement::getText)
                .filter(i -> !i.equals("")).collect(Collectors.toList());
        log.info("getAccessoriesHoverElements method return list with size: " + result.size());
        return result;
    }

    public List<String> getArtHoverElements() {
        List<String> result = mainPage.getArtHoverElements().stream().map(WebElement::getText)
                .filter(i -> !i.equals("")).collect(Collectors.toList());
        log.info("getArtHoverElements method return list with size: " + result.size());
        return result;
    }

    private Product createProduct(String[] prices, String name) {
        String currentPriceValue = prices[0];
        String currentCurrency = currentPriceValue.substring(0, 1);
        Double currentPrice = Double.parseDouble(currentPriceValue.substring(1));

        Product product = new Product(name, currentPrice, currentCurrency);

        if (prices.length > 1) {
            String oldPriceValue = prices[1];
            String oldCurrency = oldPriceValue.substring(0, 1);
            Double oldPrice = Double.parseDouble(oldPriceValue.substring(1));
            product.setOldPrice(oldPrice);
            product.setOldCurrency(oldCurrency);
        }
        return product;
    }
}
