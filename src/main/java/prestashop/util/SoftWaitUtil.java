package prestashop.util;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import prestashop.config.Driver;
import prestashop.config.Reporting;
import prestashop.exception.FailTest;

import java.time.Duration;
import java.util.List;

// todo is this  impl correct? I mean using of try catch everywhere?
//  I want to write in logs everything
// In future I want to do it with AspectJ. Not to duplicate code

public class SoftWaitUtil {
    private final long waitDuration = 20;
    protected WebDriverWait wait;

    public SoftWaitUtil() {
        wait = new WebDriverWait(Driver.getInstance().getDriver(), Duration.ofSeconds(waitDuration));
    }

    public ExtentTest getReport() {
        return Reporting.threadReport.get();
    }


    public WebDriver getDriver() {
        return Driver.getInstance().getDriver();
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
            getReport().info("Successful waiting of element: " + elementName);
        } catch (TimeoutException e) {
            getReport().fail("Time out of waiting clickable of element: " + elementName);
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
            getReport().info("Successful waiting of element: " + elementName);
        } catch (TimeoutException e) {
            getReport().fail("Time out of waiting invisibility of element: " + elementName);
            throw new FailTest(e);
        }
        enableImplicitWait();
        return ele;

    }

    public WebElement elementDisplayed(WebElement element, String elementName) {
        disableImplicitWait();
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            getReport().info("Successful waiting of element: " + elementName);
        } catch (TimeoutException e) {
            getReport().fail("Time out of waiting element displayed: " + elementName);
            throw new FailTest(e);
        }
        enableImplicitWait();
        return element;
    }

    public List<WebElement> elementsDisplayed(List<WebElement> elements, String elementsName) {
        disableImplicitWait();
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            getReport().info("Successful waiting of elements: " + elementsName);
        } catch (TimeoutException e) {
            getReport().fail("Time out of waiting visibility of list elements: " + elementsName);
            throw new FailTest(e);
        }
        enableImplicitWait();
        return elements;
    }
}
