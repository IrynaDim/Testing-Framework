package prestashop.util;

import prestashop.model.Product;

public class ProductConverter {
    public static Product createProduct(String[] prices, String name) {
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
