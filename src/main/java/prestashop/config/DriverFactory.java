package prestashop.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import prestashop.model.Browser;
import prestashop.util.PropertyReader;

import java.io.File;

public class DriverFactory {
    private static final String DRIVER_PATH = "src/main/resources/";
    private static final String RESOLUTION_REGEX = "\\d+x\\d+";
    private static final String browserProperty = "browser";
    private static final String resolutionProperty = "resolution";
    private static final DriverFactory instance = new DriverFactory();

    private DriverFactory() {

    }

    public static DriverFactory getInstance() {
        return instance;
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void removeDriver() {
        driver.get().quit();
        driver.remove();
    }

    private static final ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> {
        String resolution = PropertyReader.getProperty(resolutionProperty);
        String browserValue = PropertyReader.getProperty(browserProperty);

        checkResolution(resolution);
        checkBrowser(browserValue);

        Browser browser = Browser.valueOf(browserValue);
        File file;
        if (browser == Browser.CHROME) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            chromeOptions.addArguments("--window-size=" + resolution);
            file = new File(DRIVER_PATH + "drivers/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            return new ChromeDriver(chromeOptions);
        }
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addPreference("resolution", resolution);
        file = new File(DRIVER_PATH + "drivers/geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
        return new FirefoxDriver(firefoxOptions);
    });

    private static void checkResolution(String resolution) {
        if (!resolution.matches(RESOLUTION_REGEX)) {
            throw new RuntimeException("You pass not correct resolution to driver: " + resolution);
        }
    }

    private static void checkBrowser(String browserValue) {
        try {
            Browser.valueOf(browserValue);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("You pass not correct browser value: " + browserValue);
        }
    }
}
