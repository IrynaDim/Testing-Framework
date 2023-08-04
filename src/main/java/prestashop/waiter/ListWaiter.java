package prestashop.waiter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.stream.Collectors;

public class ListWaiter implements ExpectedCondition<Boolean> {
    private final List<WebElement> list;

    public ListWaiter(List<WebElement> list) {
        this.list = list;
    }

    @Override
    public Boolean apply(WebDriver webDriver) {
        List<String> productName = list.stream()
                .map(WebElement::getText).collect(Collectors.toList());
        return productName.size() > 0;
    }
}
