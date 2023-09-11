package prestashop.util;

import com.aventstack.extentreports.ExtentTest;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import prestashop.config.Driver;
import prestashop.config.Reporting;
import prestashop.exception.FailTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class SoftWebElementAction {
    private final SoftWaitUtil waitUtil = new SoftWaitUtil();
    private final Actions actions = new Actions(Driver.getInstance().getDriver());
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private ExtentTest getReport() {
        return Reporting.threadReport.get();
    }

    private WebDriver getDriver() {
        return Driver.getInstance().getDriver();
    }

    public <T> T refreshPage(T page, String pageName) {
        try {
            getDriver().navigate().refresh();
            logActionInfo("Refresh " + pageName);
        } catch (Exception e) {
            logActionError("Error while refreshing page: " + pageName);
            throw new FailTest(e);
        }
        return page;
    }

    public <T> void clickElement(T element, String elementName) {
        try {
            WebElement ele = waitUtil.clickableElement(waitUtil.elementDisplayed(element, elementName), elementName);
            ele.click();
            logActionInfo("Clicked element : " + elementName);
        } catch (NoSuchElementException e) {
            logActionError("Element is not found :" + elementName);
            throw new FailTest(e);
        } catch (Exception e1) {
            logActionError("Unable to click element :" + elementName);
            throw new FailTest(e1);
        }
    }

    public <T> void clickElementWithoutWait(T element, String elementName) {
        try {
            if (element.getClass().getName().contains("By")) {
                getDriver().findElement((By) element).click();
            } else {
                WebElement ele = (WebElement) element;
                ele.click();
            }
            logActionInfo("Clicked element without waiting: " + elementName);
        } catch (NoSuchElementException e) {
            logActionError("Element is not found :" + elementName);
            throw new FailTest(e);
        } catch (Exception e1) {
            logActionError("Unable to click element :" + elementName);
            throw new FailTest(e1);
        }
    }

    public <T, K> void insertTextToElement(T element, K textToEnter, String elementName) {
        try {
            WebElement foundElement = waitUtil.elementDisplayed(element, elementName);
            if (textToEnter.getClass().getName().contains("String")) {
                foundElement.sendKeys((String) textToEnter);
            } else {
                foundElement.sendKeys((Keys) textToEnter);
            }
            logActionInfo("Typed this text : " + textToEnter + ", to the element " + elementName);
        } catch (NoSuchElementException e) {
            logActionError("Element is not found: " + elementName);
            throw new FailTest(e);
        } catch (Exception e1) {
            logActionError("Unable to click element: " + elementName);
            throw new FailTest(e1);
        }
    }

    public <T> String getTextFromElement(T element, String elementName) {
        try {
            String text = waitUtil.elementDisplayed(element, elementName).getText();
            logActionInfo("Fetched this text : \"" + text + "\" from the element: " + elementName);
            return text;
        } catch (NoSuchElementException e) {
            logActionError("Text in element: " + elementName + " is not found.");
            throw new FailTest(e);
        } catch (Exception e1) {
            logActionError("Unable to fetch text from the element: " + elementName);
            throw new FailTest(e1);
        }

    }

    public <T> void switchToFrame(T element, String elementName) {

        try {
            getDriver().switchTo().defaultContent();
            logActionInfo("Switched to default frame");
            getDriver().switchTo().frame(waitUtil.elementDisplayed(element, elementName));
            logActionInfo("Switched to frame " + elementName);
        } catch (NoSuchElementException e) {
            logActionError("This frame is not Found: " + elementName);
            throw new FailTest(e);
        } catch (Exception e1) {
            logActionError("Unable to switched to the frame: " + elementName);
            throw new FailTest(e1);
        }
    }

    public <T> void moveToElement(T element, String elementName) {
        try {
            actions.moveToElement(waitUtil.elementDisplayed(element, elementName)).perform();
            logActionInfo("Moved to element: " + elementName);

        } catch (NoSuchElementException e) {
            logActionError("No such element: " + elementName);
            throw new FailTest(e);
        } catch (Exception e1) {
            logActionError("Unable to move to the element: " + elementName);
            throw new FailTest(e1);
        }
    }

    public <T> void javaScriptInsertText(T element, String javaScript, String textToInsert, String elementName) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        WebElement foundElement;
        try {
            foundElement = waitUtil.elementDisplayed(element, elementName);
            logActionInfo("Found element: " + elementName);
        } catch (NoSuchElementException e) {
            logActionError("No such element: " + elementName);
            throw new FailTest(e);
        } catch (Exception e1) {
            logActionError("Unable to move to the element: " + elementName);
            throw new FailTest(e1);
        }
        try {
            executor.executeScript(javaScript, foundElement, textToInsert);
        } catch (Exception e) {
            getReport().fail("Unable to insert text in element \"" + elementName + "\" using javaScript " + javaScript);
            throw new FailTest(e);
        }
        getReport().info("Insert text using javascript to element: " + elementName);
    }
    private void logActionInfo(String text) {
        log.info(text + " with Thread id " + Thread.currentThread().getId() + " and time " + timeFormat.format(new Date()));
        getReport().info(text);
    }

    private void logActionError(String text) {

        log.error(text + " with Thread id " + Thread.currentThread().getId() + " and time " + timeFormat.format(new Date()));
        getReport().fail(text);
    }
}
