package prestashop.util;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import prestashop.config.DriverFactory;
import prestashop.config.LoggerFactory;
import prestashop.exception.FailTest;

public class SoftWebElementAction {
    private final SoftWaitUtil waitUtil = new SoftWaitUtil();
    private final Actions actions = new Actions(DriverFactory.getInstance().getDriver());

    public ExtentTest getLog() {
        return LoggerFactory.logger.get();
    }

    public WebDriver getDriver() {
        return DriverFactory.getInstance().getDriver();
    }

    public <T> void clickElement(T element, String elementName) {

        try {
            if (element.getClass().getName().contains("By")) {
                WebElement foundElement = waitUtil.elementDisplayed(getDriver().findElement((By) element));
                WebElement ele = waitUtil.clickableElement(foundElement);
                ele.click();
                getLog().info("Clicked element : " + elementName);
            } else {
                WebElement ele = waitUtil.clickableElement(waitUtil.elementDisplayed((WebElement) element));
                ele.click();
                getLog().info("Clicked element : " + elementName);
            }
        } catch (NoSuchElementException e) {
            getLog().fail("Element is not Found :" + elementName);
            throw new FailTest(e);
        } catch (Exception e1) {
            getLog().fail("Unable to click element :" + elementName);
            throw new FailTest(e1);
        }
    }

    public <T> void insertTextToElement(T element, String textToEnter) {

        try {
            if (element.getClass().getName().contains("By")) {
                WebElement foundElement = waitUtil.elementDisplayed(getDriver().findElement((By) element));
                foundElement.sendKeys(textToEnter);
                getLog().info("Typed this text : " + textToEnter);
            } else {
                waitUtil.elementDisplayed((WebElement) element).sendKeys(textToEnter);
                getLog().info("Typed this text : " + textToEnter);
            }
        } catch (NoSuchElementException e) {
            getLog().fail("Element is not Found");
            throw new FailTest(e);
        } catch (Exception e1) {
            getLog().fail("Unable to click element");
            throw new FailTest(e1);
        }
    }

    public <T> String getTextFromElement(T element, String textToEnter) {
        try {
            if (element.getClass().getName().contains("By")) {
                WebElement foundElement = waitUtil.elementDisplayed(getDriver().findElement((By) element));
                String text = foundElement.getText();
                getLog().info("Fetched this text : " + text);
                return text;
            } else {
                String text = waitUtil.elementDisplayed((WebElement) element).getText();
                getLog().info("Fetched this text : " + text);
                return text;
            }
        } catch (NoSuchElementException e) {
            getLog().fail("Element with text is not found: " + textToEnter);
            throw new FailTest(e);
        } catch (Exception e1) {
            getLog().fail("Unable to fetch text: " + textToEnter);
            throw new FailTest(e1);
        }

    }

    public <T> void switchToFrame(T element, String elementName) {

        try {
            DriverFactory.getInstance().getDriver().switchTo().defaultContent();
            getLog().info("Switched to default frame");

            if (element.getClass().getName().contains("By")) {
                getDriver().switchTo().frame(waitUtil.elementDisplayed(getDriver().findElement((By) element)));
            } else {
                getDriver().switchTo().frame(waitUtil.elementDisplayed((WebElement) element));
            }
            getLog().info("Switched to frame " + elementName);
        } catch (NoSuchElementException e) {
            getLog().fail("This frame is not Found: " + elementName);
            throw new FailTest(e);
        } catch (Exception e1) {
            getLog().fail("Unable to switched to the frame: " + elementName);
            throw new FailTest(e1);
        }
    }

    public <T> void moveToElement(T element, String elementName) {
        try {
            if (element.getClass().getName().contains("By")) {
                WebElement foundElement = waitUtil.elementDisplayed(getDriver().findElement((By) element));
                actions.moveToElement(waitUtil.elementDisplayed(foundElement)).perform();
            } else {
                actions.moveToElement(waitUtil.elementDisplayed((WebElement) element)).perform();
            }
            getLog().info("Moved to element: " + elementName);

        } catch (NoSuchElementException e) {
            getLog().fail("No such element: " + elementName);
            throw new FailTest(e);
        } catch (Exception e1) {
            getLog().fail("Unable to move to the element: " + elementName);
            throw new FailTest(e1);
        }
    }
}
