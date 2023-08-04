package prestashop.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseProduct {
    private String currency;
    private Double oldPrice;
    private String oldCurrency;
    private Integer discount;

    public Product(String name, Double price, String currency) {
        super(name, price);
        this.currency = currency;
    }

    public Product(String name, Double price) {
        super(name, price);
    }
}
