package prestashop.util;

import com.aventstack.extentreports.ExtentTest;
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

    public ExtentTest getLog() {
        return LoggerFactory.logger.get();
    }


    public WebDriver getDriver() {
        return DriverFactory.getInstance().getDriver();
    }

    public void disableImplicitWait() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    public void enableImplicitWait() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(waitDuration));
    }

    public WebElement clickableElement(WebElement element, String elementName) {
        disableImplicitWait();
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            getLog().info("Successful waiting of element: " + elementName);
        } catch (TimeoutException e) {
            getLog().fail("Time out of waiting clickable of element: " + elementName);
            throw new FailTest(e);
        }
        enableImplicitWait();
        return element;

    }

    public Boolean invisibleElement(WebElement element, String elementName) {
        disableImplicitWait();
        Boolean ele;
        try {
            ele = wait.until(ExpectedConditions.invisibilityOf(element));
            getLog().info("Successful waiting of element: " + elementName);
        } catch (TimeoutException e) {
            getLog().fail("Time out of waiting invisibility of element: " + elementName);
            throw new FailTest(e);
        }
        enableImplicitWait();
        return ele;

    }

    public WebElement elementDisplayed(WebElement element, String elementName) {
        disableImplicitWait();
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            getLog().info("Successful waiting of element: " + elementName);
        } catch (TimeoutException e) {
            getLog().fail("Time out of waiting element displayed: " + elementName);
            throw new FailTest(e);
        }
        enableImplicitWait();
        return element;
    }

    public List<WebElement> elementsDisplayed(List<WebElement> elements, String elementsName) {
        disableImplicitWait();
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            getLog().info("Successful waiting of elements: " + elementsName);
        } catch (TimeoutException e) {
            getLog().fail("Time out of waiting visibility of list elements: " + elementsName);
            throw new FailTest(e);
        }
        enableImplicitWait();
        return elements;
    }
}
