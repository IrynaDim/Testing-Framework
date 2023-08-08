package prestashop.config;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Synchronize {
    private final long waitDuration = 30;
    protected WebDriverWait wait;
    protected Actions actions;

    public Synchronize() {
        wait = new WebDriverWait(Factory.getInstance().getDriver(), Duration.ofSeconds(waitDuration));
        actions = new Actions(Factory.getInstance().getDriver());
    }

    private void disableImplicitWait() {
        Factory.getInstance().getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    private void enableImplicitWait() {
        Factory.getInstance().getDriver().manage().timeouts().implicitlyWait(waitDuration, TimeUnit.SECONDS);
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
        System.out.println("Synchronize class " + toString() + " Tread " + Thread.currentThread().getId());
        disableImplicitWait();
        WebElement ele = wait.until(ExpectedConditions.visibilityOf(element));
        enableImplicitWait();
        return ele;
    }

    public List<WebElement> elementsDisplayed(List<WebElement> elements) {
        System.out.println("Synchronize class " + toString() + " Tread " + Thread.currentThread().getId());
        disableImplicitWait();
        List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        enableImplicitWait();
        return results;
    }

}
