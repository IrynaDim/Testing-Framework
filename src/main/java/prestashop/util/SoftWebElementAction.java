package prestashop.util;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import prestashop.config.Driver;
import prestashop.config.Reporting;
import prestashop.exception.FailTest;

public class SoftWebElementAction {
    private final SoftWaitUtil waitUtil = new SoftWaitUtil();
    private final Actions actions = new Actions(Driver.getInstance().getDriver());

    private ExtentTest getReport() {
        return Reporting.threadReport.get();
    }

    private WebDriver getDriver() {
        return Driver.getInstance().getDriver();
    }

    public <T> T refreshPage(T page, String pageName) {
        try {
            getDriver().navigate().refresh();
        } catch (Exception e) {
            getReport().error("Error while refreshing page: " + pageName);
            throw new FailTest(e);
        }
        return page;
    }

    public <T> void clickElement(T element, String elementName) {

        try {
            WebElement ele = waitUtil.clickableElement(waitUtil.elementDisplayed(element, elementName), elementName);
            ele.click();
            getReport().info("Clicked element : " + elementName);
        } catch (NoSuchElementException e) {
            getReport().fail("Element is not found :" + elementName);
            throw new FailTest(e);
        } catch (Exception e1) {
            getReport().fail("Unable to click element :" + elementName);
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
            getReport().info("Typed this text : " + textToEnter + ", to the element " + elementName);
        } catch (NoSuchElementException e) {
            getReport().fail("Element is not found: " + elementName);
            throw new FailTest(e);
        } catch (Exception e1) {
            getReport().fail("Unable to click element: " + elementName);
            throw new FailTest(e1);
        }
    }

    public <T> String getTextFromElement(T element, String elementName) {
        try {
            String text = waitUtil.elementDisplayed(element, elementName).getText();
            getReport().info("Fetched this text : \"" + text + "\" from the element: " + elementName);
            return text;
        } catch (NoSuchElementException e) {
            getReport().fail("Text in element: " + elementName + " is not found.");
            throw new FailTest(e);
        } catch (Exception e1) {
            getReport().fail("Unable to fetch text from the element: " + elementName);
            throw new FailTest(e1);
        }

    }

    public <T> void switchToFrame(T element, String elementName) {

        try {
            getDriver().switchTo().defaultContent();
            getReport().info("Switched to default frame");
            getDriver().switchTo().frame(waitUtil.elementDisplayed(element, elementName));
            getReport().info("Switched to frame " + elementName);
        } catch (NoSuchElementException e) {
            getReport().fail("This frame is not Found: " + elementName);
            throw new FailTest(e);
        } catch (Exception e1) {
            getReport().fail("Unable to switched to the frame: " + elementName);
            throw new FailTest(e1);
        }
    }

    public <T> void moveToElement(T element, String elementName) {
        try {
            actions.moveToElement(waitUtil.elementDisplayed(element, elementName)).perform();
            getReport().info("Moved to element: " + elementName);

        } catch (NoSuchElementException e) {
            getReport().fail("No such element: " + elementName);
            throw new FailTest(e);
        } catch (Exception e1) {
            getReport().fail("Unable to move to the element: " + elementName);
            throw new FailTest(e1);
        }
    }

    public <T> void javaScriptInsertText(T element, String javaScript, String textToInsert, String elementName) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        WebElement foundElement;
        try {
            foundElement = waitUtil.elementDisplayed(element, elementName);
            getReport().info("Found element: " + elementName);
        } catch (NoSuchElementException e) {
            getReport().fail("No such element: " + elementName);
            throw new FailTest(e);
        } catch (Exception e1) {
            getReport().fail("Unable to move to the element: " + elementName);
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
}
