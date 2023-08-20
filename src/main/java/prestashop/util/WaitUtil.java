package prestashop.util;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import prestashop.config.DriverFactory;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaitUtil {
    private final long waitDuration = 50;
    protected WebDriverWait wait;
    protected Actions actions;

    public WaitUtil() {
        wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(waitDuration));
        actions = new Actions(DriverFactory.getInstance().getDriver());
    }

    private void disableImplicitWait() {
        DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    private void enableImplicitWait() {
        DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(waitDuration, TimeUnit.SECONDS);
    }


    protected WebElement clickableCustomWait(WebElement element) {
        disableImplicitWait();
        WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));
        enableImplicitWait();
        return ele;

    }

    public Boolean invisibleElement(WebElement element) {
        disableImplicitWait();
        Boolean ele = wait.until(ExpectedConditions.invisibilityOf(element));
        enableImplicitWait();
        return ele;

    }

    public WebElement elementDisplayed(WebElement element) {
        disableImplicitWait();
        WebElement ele = wait.until(ExpectedConditions.visibilityOf(element));
        enableImplicitWait();
        return ele;
    }

    public List<WebElement> elementsDisplayed(List<WebElement> elements) {
        disableImplicitWait();
        List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        enableImplicitWait();
        return results;
    }

}
