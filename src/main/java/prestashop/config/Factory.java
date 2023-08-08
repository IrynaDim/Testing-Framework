package prestashop.config;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Factory {
    private Factory() {

    }

    private static final Factory instance = new Factory();

    public static final ThreadLocal<ExtentTest> logger = new ThreadLocal<>();

    private static final ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        return new ChromeDriver(chromeOptions);
    });

    public static Factory getInstance() {
        return instance;
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void removeDriver() {
        driver.get().quit();
        driver.remove();
    }
}
