package prestashop;


import com.aventstack.extentreports.ExtentTest;
import prestashop.config.Reporting;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import prestashop.config.DriverProvider;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;


public class MainPageTest extends Reporting {

    @BeforeSuite
    public void startReporting() {
        intializeReport();
    }


    @AfterSuite
    public void endReporting() {
        generateReport();
    }

    @BeforeMethod
    public void beforeTest(Method result) {
        DriverProvider.getInstance().getDriver().get("https://demo.prestashop.com/#/en/front");
        DriverProvider.getInstance().getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        ExtentTest test = report.createTest(result.getName());
        DriverProvider.logger.set(test);
    }

    @Test
    @SneakyThrows
    public void checkSubscribeText() {
        Thread.sleep(11000);
        DriverProvider.logger.get().info("Start test execution 'checkSubscribeText'");
        DriverProvider.getInstance().getDriver().switchTo().frame(DriverProvider.getInstance().getDriver()
                .findElement(By.id("framelive")));
        System.out.println(Thread.currentThread().getId());
        assertEquals("Get our latest news and special sales",
                DriverProvider.getInstance().getDriver()
                        .findElement(By.xpath("//p[@id='block-newsletter-label']"))
                        .getText());
        DriverProvider.logger.get().info("Test 'checkSubscribeText' execute successfully");
    }

    @Test
    @SneakyThrows
    public void checkUnSubscribeText() {
        System.out.println(Thread.currentThread().getId());
        Thread.sleep(11000);
        DriverProvider.getInstance().getDriver().switchTo().frame(DriverProvider.getInstance().getDriver()
                .findElement(By.id("framelive")));
        DriverProvider.logger.get().info("Start test execution 'checkUnSubscribeText'");
        assertEquals("You may unsubscribe at any moment. For that purpose, please find our contact info in the legal notice.",
                DriverProvider.getInstance().getDriver()
                        .findElement(By.xpath("//div[@class='col-xs-12']/p"))
                        .getText());
        DriverProvider.logger.get().info("Test 'checkUnSubscribeText' execute successfully");
    }

    @AfterMethod
    public void afterTest() {
        DriverProvider.getInstance().removeDriver();
        DriverProvider.logger.remove();
    }
}

