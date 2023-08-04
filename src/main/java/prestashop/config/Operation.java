package prestashop.config;

import com.aventstack.extentreports.ExtentTest;
import prestashop.exception.FailTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class Operation extends Synchronize {

    public ExtentTest getLog() {
        return DriverProvider.logger.get();
    }

    public <T> void clickElement(T element, String elementName) {

        try {
            if (element.getClass().getName().contains("By")) {
                WebElement foundElement = DriverProvider.getInstance().getDriver().findElement((By) element);
                WebElement ele = clickableCustomWait(foundElement);
                ele.click();
                getLog().info("Clicked element : " + elementName);
            } else {
                WebElement ele = clickableCustomWait((WebElement) element);
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

}
