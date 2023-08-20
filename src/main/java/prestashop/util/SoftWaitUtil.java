package prestashop.util;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import prestashop.config.DriverFactory;
import prestashop.config.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

// todo is this  impl correct? I mean using of try catch everywhere and return null?
//  I want to write in logs everything
// In future I want to do it with AspectJ. Not to duplicate code

public class SoftWaitUtil {
    private final long waitDuration = 20;
    protected WebDriverWait wait;

    public SoftWaitUtil() {
        wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(waitDuration));
    }

    public void disableImplicitWait() {
        DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public void enableImplicitWait() {
        DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(waitDuration, TimeUnit.SECONDS);
    }


    public WebElement clickableElement(WebElement element) {
        disableImplicitWait();
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            LoggerFactory.logger.get().fail("Time out of waiting clickable of element.");
            return null;
        }
        enableImplicitWait();
        return element;

    }

    public Boolean invisibleElement(WebElement element) {
        disableImplicitWait();
        Boolean ele = false;
        try {
            ele = wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException e) {
            LoggerFactory.logger.get().fail("Time out of waiting invisibility of element.");
        }
        enableImplicitWait();
        return ele;

    }

    public WebElement elementDisplayed(WebElement element) {
        disableImplicitWait();
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            LoggerFactory.logger.get().fail("Time out of waiting element displayed.");
            return null;
        }
        enableImplicitWait();
        return element;
    }

    public List<WebElement> elementsDisplayed(List<WebElement> elements) {
        disableImplicitWait();
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (TimeoutException e) {
            LoggerFactory.logger.get().fail("Time out of waiting visibility of list elements.");
            return null;
        }
        enableImplicitWait();
        return elements;
    }
}
