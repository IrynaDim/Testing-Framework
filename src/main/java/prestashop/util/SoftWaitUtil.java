package prestashop.util;

import com.aventstack.extentreports.ExtentTest;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import prestashop.config.Driver;
import prestashop.config.Reporting;
import prestashop.exception.FailTest;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

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

    public <T> WebElement clickableElement(T element, String elementName) {
        disableImplicitWait();
        WebElement webElement;
        try {
            if (element.getClass().getName().contains("By")) {
                webElement = wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement((By) element)));
            } else {
                webElement = wait.until(ExpectedConditions.elementToBeClickable((WebElement) element));
            }
            getReport().info("Successful waiting of element: " + elementName);
        } catch (TimeoutException e) {
            getReport().fail("Time out of waiting clickable of element: " + elementName);
            throw new FailTest(e);
        }
        enableImplicitWait();
        return webElement;

    }

    public <T> Boolean invisibleElement(T element, String elementName) {
        disableImplicitWait();
        Boolean ele;
        try {
            if (element.getClass().getName().contains("By")) {
                ele = wait.until(ExpectedConditions.invisibilityOf(getDriver().findElement((By) element)));
            } else {
                ele = wait.until(ExpectedConditions.invisibilityOf((WebElement) element));
            }
            getReport().info("Successful waiting of element: " + elementName);
        } catch (TimeoutException e) {
            getReport().fail("Time out of waiting invisibility of element: " + elementName);
            throw new FailTest(e);
        }
        enableImplicitWait();
        return ele;

    }

    public <T> WebElement elementDisplayed(T element, String elementName) {
        disableImplicitWait();
        WebElement webElement;
        try {
            if (element.getClass().getName().contains("By")) {
                webElement = wait.until(ExpectedConditions.visibilityOf(getDriver().findElement((By) element)));
            } else {
                webElement = wait.until(ExpectedConditions.visibilityOf((WebElement) element));
            }
            getReport().info("Successful waiting of element: " + elementName);
        } catch (TimeoutException e) {
            getReport().fail("Time out of waiting element displayed: " + elementName);
            throw new FailTest(e);
        }
        enableImplicitWait();
        return webElement;
    }

    public <T> List<WebElement> elementsDisplayed(T elements, String elementsName) {
        disableImplicitWait();
        List<WebElement> webElements;
        try {
            if (elements.getClass().getName().contains("By")) {
                webElements = wait.until(ExpectedConditions.visibilityOfAllElements(getDriver().findElements((By) elements)));
            } else {
                webElements = wait.until(ExpectedConditions.visibilityOfAllElements((List<WebElement>) elements));
            }
            getReport().info("Successful waiting of elements: " + elementsName);
        } catch (TimeoutException e) {
            getReport().fail("Time out of waiting visibility of list elements: " + elementsName);
            throw new FailTest(e);
        }
        enableImplicitWait();
        return webElements;
    }
}
