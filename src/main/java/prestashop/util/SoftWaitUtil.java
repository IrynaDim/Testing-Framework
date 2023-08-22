package prestashop.util;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import prestashop.config.DriverFactory;
import prestashop.config.LoggerFactory;
import prestashop.exception.FailTest;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

// todo is this  impl correct? I mean using of try catch everywhere?
//  I want to write in logs everything
// In future I want to do it with AspectJ. Not to duplicate code

public class SoftWaitUtil {
    private final long waitDuration = 20;
    protected WebDriverWait wait;

    public SoftWaitUtil() {
        wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(waitDuration));
    }

    public WebDriver getDriver() {
        return DriverFactory.getInstance().getDriver();
    }

    public void disableImplicitWait() {
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public void enableImplicitWait() {
        getDriver().manage().timeouts().implicitlyWait(waitDuration, TimeUnit.SECONDS);
    }


    public WebElement clickableElement(WebElement element) {
        disableImplicitWait();
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            LoggerFactory.logger.get().fail("Time out of waiting clickable of element.");
            throw new FailTest(e);
        }
        enableImplicitWait();
        return element;

    }

    public Boolean invisibleElement(WebElement element) {
        disableImplicitWait();
        Boolean ele;
        try {
            ele = wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException e) {
            LoggerFactory.logger.get().fail("Time out of waiting invisibility of element.");
            throw new FailTest(e);
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
            throw new FailTest(e);
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
            throw new FailTest(e);
        }
        enableImplicitWait();
        return elements;
    }
}
