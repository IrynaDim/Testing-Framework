package prestashop;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import prestashop.config.DriverProvider;

public class BaseTest {
    protected static WebDriver driver;
    protected String baseUrl = "https://demo.prestashop.com/#/en/front";

    @BeforeMethod
    public void setUp() {
        driver = DriverProvider.getInstance().getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverProvider.getInstance().removeDriver();
    }
}
