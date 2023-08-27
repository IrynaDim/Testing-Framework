package prestashop.pages.orderIsConfirmed;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import prestashop.pages.BasePage;

public class OrderIsConfirmedPage extends BasePage {

    @FindBy(xpath = "//h3[@class='h1 card-title']")
    private WebElement orderIsConfirmedText;

    @FindBy(xpath = "//tbody/tr[last()]/td[last()]")
    private WebElement totalSum;

    public String getOrderIsConfirmed() {
        return operation.getTextFromElement(orderIsConfirmedText, "order is confirmed text");
    }

    public String getTotalSum() {
        return operation.getTextFromElement(totalSum, "total sum");
    }
}
