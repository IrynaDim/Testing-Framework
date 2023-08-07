package prestashop.config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SoftWebElementAction {

    public Boolean isElementExist(By element) {
        List<WebElement> elements = DriverProvider.getInstance().getDriver().findElements(element);
        if (elements.isEmpty()) {
            DriverProvider.logger.get().fail("Web element does not exist");
            return false;
        }
        DriverProvider.logger.get().pass("Web element exist");
        return true;
    }

}
