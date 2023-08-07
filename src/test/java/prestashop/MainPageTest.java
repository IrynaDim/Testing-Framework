package prestashop;

import com.aventstack.extentreports.ExtentTest;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import prestashop.config.DriverProvider;
import prestashop.config.Reporting;
import prestashop.config.SoftAssertion;
import prestashop.pages.MainPage;

import java.lang.reflect.Method;

import static org.testng.AssertJUnit.assertTrue;


public class MainPageTest extends Reporting {
    private MainPage mainPage;
    private final SoftAssertion softAssertion = new SoftAssertion();

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
        mainPage = new MainPage();
        DriverProvider.getInstance().getDriver().get("https://demo.prestashop.com/#/en/front");
        ExtentTest test = report.createTest(result.getName());
        DriverProvider.logger.set(test);
    }

    @Test
    public void checkSubscribeText() {
        String emailSubscribeElement = mainPage.getEmailSubscribeElement();
        assertTrue(softAssertion.compareObjects("Get our latest news and special sales", emailSubscribeElement));
    }

    @Test
    public void checkUnSubscribeText() {
        String unsubscribeElement = mainPage.getUnsubscribeElement();
        assertTrue(softAssertion.compareObjects("You may unsubscribe at any moment. " +
                "For that purpose, please find our contact info in the legal notice.", unsubscribeElement));
    }

    @AfterMethod
    public void afterTest() {
        DriverProvider.getInstance().removeDriver();
        DriverProvider.logger.remove();
    }
}

