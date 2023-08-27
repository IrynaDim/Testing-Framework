package prestashop.util;

import com.aventstack.extentreports.ExtentTest;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import prestashop.config.Driver;
import prestashop.config.Reporting;
import prestashop.exception.FailTest;

//todo is it good idea to add to all methods where I use wait - boolean field isWaitNeed?
// so If element dont need to be waited - we wont do it? I have several places in the code where
// I cant use ExpectedConditions.visibilityOf or other wait because I receive error. But without wait it works
// For example in choosePaymentOptions method or in chooseColor.
// Maybe I can add this Boolean to all methods?


//todo also I dont understand logging. I should double log and report information like I do in the first tree methods
// for demonstration in this class using private method? Also I have an idea with AOP but im too lazy to implement it)
// Or how add logger and report in project in correct way?
// Also should I add logger @Slf4j to all other classes: tests, actions, utils, config? Or not to all classes?
// I don't really understand if I'm doing logging/reporting it right or not.


//todo Does I have enough text information in reports? Or i should write bigger messages?

@Slf4j
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
            logActionInfo("Refresh " + pageName);
        } catch (Exception e) {
            logActionError("Error while refreshing page: " + pageName);
            throw new FailTest(e);
        }
        return page;
    }
// todo is it ok to use Boolean isWaitNeed?
    public <T> void clickElement(T element, String elementName, Boolean isWaitNeed) {

        try {
            if (isWaitNeed) {
                WebElement ele = waitUtil.clickableElement(waitUtil.elementDisplayed(element, elementName), elementName);
                ele.click();
                logActionInfo("Clicked element : " + elementName);
            } else {
                if (element.getClass().getName().contains("By")) {
                    getDriver().findElement((By) element).click();
                } else {
                    WebElement ele = (WebElement) element;
                    ele.click();
                }
                logActionInfo("Clicked element without waiting: " + elementName);
            }
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

    private void logActionInfo(String text) {
        log.info(text);
        getReport().info(text);
    }

    private void logActionError(String text) {
        log.error(text);
        getReport().fail(text);
    }
}
