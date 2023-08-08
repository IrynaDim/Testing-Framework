package prestashop;

import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import prestashop.config.DriverProvider;
import prestashop.config.Reporting;
import prestashop.config.SoftAssertion;
import prestashop.pages.MainPage;

import java.lang.reflect.Method;

public class BaseTest extends Reporting {
    protected MainPage mainPage;
    protected final SoftAssertion softAssertion = new SoftAssertion();

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

    @AfterMethod
    public void afterTest() {
        DriverProvider.getInstance().removeDriver();
        DriverProvider.logger.remove();
    }
}
