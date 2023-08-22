package prestashop.util;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import prestashop.config.Driver;
import prestashop.config.Reporting;
import prestashop.exception.FailTest;

public class SoftWebElementAction {
    private final SoftWaitUtil waitUtil = new SoftWaitUtil();
    private final Actions actions = new Actions(Driver.getInstance().getDriver());

    public ExtentTest getReport() {
        return Reporting.threadReport.get();
    }

    public WebDriver getDriver() {
        return Driver.getInstance().getDriver();
    }

    public <T> void clickElement(T element, String elementName) {

        try {
            if (element.getClass().getName().contains("By")) {
                WebElement foundElement = waitUtil.elementDisplayed(getDriver().findElement((By) element), elementName);
                WebElement ele = waitUtil.clickableElement(foundElement, elementName);
                ele.click();
                getReport().info("Clicked element : " + elementName);
            } else {
                WebElement ele = waitUtil.clickableElement(waitUtil.elementDisplayed((WebElement) element, elementName), elementName);
                ele.click();
                getReport().info("Clicked element : " + elementName);
            }
        } catch (NoSuchElementException e) {
            getReport().fail("Element is not found :" + elementName);
            throw new FailTest(e);
        } catch (Exception e1) {
            getReport().fail("Unable to click element :" + elementName);
            throw new FailTest(e1);
        }
    }

    public <T> void insertTextToElement(T element, String textToEnter, String elementName) {

        try {
            if (element.getClass().getName().contains("By")) {
                WebElement foundElement = waitUtil.elementDisplayed(getDriver().findElement((By) element), elementName);
                foundElement.sendKeys(textToEnter);
                getReport().info("Typed this text : " + textToEnter + ", to the element " + elementName);
            } else {
                waitUtil.elementDisplayed((WebElement) element, elementName).sendKeys(textToEnter);
                getReport().info("Typed this text : " + textToEnter + ", to the element " + elementName);
            }
        } catch (NoSuchElementException e) {
            getReport().fail("Element is not found: " + elementName);
            throw new FailTest(e);
        } catch (Exception e1) {
            getReport().fail("Unable to click element: " + elementName);
            throw new FailTest(e1);
        }
    }

    public <T> String getTextFromElement(T element, String textToEnter, String elementName) {
        try {
            if (element.getClass().getName().contains("By")) {
                WebElement foundElement = waitUtil.elementDisplayed(getDriver().findElement((By) element), elementName);
                String text = foundElement.getText();
                getReport().info("Fetched this text : \"" + text + "\" from the element: " + elementName);
                return text;
            } else {
                String text = waitUtil.elementDisplayed((WebElement) element, textToEnter).getText();
                getReport().info("Fetched this text : \"" + text + "\" from the element: " + elementName);
                return text;
            }
        } catch (NoSuchElementException e) {
            getReport().fail("Element: " + elementName + ", with the next text is not found: " + textToEnter);
            throw new FailTest(e);
        } catch (Exception e1) {
            getReport().fail("Unable to fetch text: \"" + textToEnter + "\" from the element: " + elementName);
            throw new FailTest(e1);
        }

    }

    public <T> void switchToFrame(T element, String elementName) {

        try {
            Driver.getInstance().getDriver().switchTo().defaultContent();
            getReport().info("Switched to default frame");

            if (element.getClass().getName().contains("By")) {
                getDriver().switchTo().frame(waitUtil.elementDisplayed(getDriver().findElement((By) element), elementName));
            } else {
                getDriver().switchTo().frame(waitUtil.elementDisplayed((WebElement) element, elementName));
            }
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
            if (element.getClass().getName().contains("By")) {
                WebElement foundElement = waitUtil.elementDisplayed(getDriver().findElement((By) element), elementName);
                actions.moveToElement(waitUtil.elementDisplayed(foundElement, elementName)).perform();
            } else {
                actions.moveToElement(waitUtil.elementDisplayed((WebElement) element, elementName)).perform();
            }
            getReport().info("Moved to element: " + elementName);

        } catch (NoSuchElementException e) {
            getReport().fail("No such element: " + elementName);
            throw new FailTest(e);
        } catch (Exception e1) {
            getReport().fail("Unable to move to the element: " + elementName);
            throw new FailTest(e1);
        }
    }
}
