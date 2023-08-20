package prestashop.util;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import prestashop.exception.FailTest;
import prestashop.config.DriverFactory;
import prestashop.config.LoggerFactory;

public class SoftWebElementAction extends WaitUtil {

    public ExtentTest getLog() {
        return LoggerFactory.logger.get();
    }

    public <T> void clickElement(T element, String elementName) {

        try {
            if (element.getClass().getName().contains("By")) {
                WebElement foundElement = elementDisplayed(DriverFactory.getInstance().getDriver().findElement((By) element));
                WebElement ele = clickableCustomWait(foundElement);
                ele.click();
                getLog().info("Clicked element : " + elementName);
            } else {
                WebElement ele = clickableCustomWait(elementDisplayed((WebElement) element));
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
                WebElement foundElement = elementDisplayed(DriverFactory.getInstance().getDriver().findElement((By) element));
                foundElement.sendKeys(textToEnter);
                getLog().info("Typed this text : " + textToEnter);
            } else {
                elementDisplayed((WebElement) element).sendKeys(textToEnter);
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
                WebElement foundElement = elementDisplayed(DriverFactory.getInstance().getDriver().findElement((By) element));
                String text = foundElement.getText();
                getLog().info("Fetched this text : " + text);
                return text;
            } else {
                String text = elementDisplayed((WebElement) element).getText();
                getLog().info("Fetched this text : " + text);
                return text;
            }

        } catch (NoSuchElementException e) {
            getLog().fail("This element is not Found");
            throw new FailTest(e);
        } catch (Exception e1) {
            getLog().fail("Unable to fetch text to this element");
            throw new FailTest(e1);
        }

    }

    public void switchToFrameLive() {

        try {
            DriverFactory.getInstance().getDriver().switchTo().defaultContent();
            getLog().info("Switched to default frame");
            DriverFactory.getInstance().getDriver().switchTo().frame(elementDisplayed(DriverFactory.getInstance().getDriver()
                    .findElement(By.id("framelive"))));
            getLog().info("Switched to framelive");

        } catch (NoSuchElementException e) {
            getLog().fail("This frame is not Found");
            throw new FailTest(e);
        } catch (Exception e1) {
            getLog().fail("Unable to switched to the frame");
            throw new FailTest(e1);
        }
    }

    public <T> void moveToElement(T element) {
        try {
            if (element.getClass().getName().contains("By")) {
                WebElement foundElement = elementDisplayed(DriverFactory.getInstance().getDriver().findElement((By) element));
                actions.moveToElement(elementDisplayed(foundElement)).perform();
            } else {
                actions.moveToElement(elementDisplayed((WebElement) element)).perform();

            }
            getLog().info("Moved to element");

        } catch (NoSuchElementException e) {
            getLog().fail("No such element");
            throw new FailTest(e);
        } catch (Exception e1) {
            getLog().fail("Unable to move to the element");
            throw new FailTest(e1);
        }
    }
}
